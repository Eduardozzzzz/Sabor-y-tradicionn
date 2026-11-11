package uniquindio.edu.co;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {
    Cliente cliente;
    PlatoFuerte plato;
    Pedido pedido;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Ana Gómez", "1091", "3001234567", "ana@correo.com", "Calle 10");
        plato = new PlatoFuerte("P01", "Bandeja paisa", "Frijoles y carne", 50000, 30, 400,
                TipoPreparacion.ASADO, ControlCoccion.SI);
        pedido = new Pedido("10", LocalDate.now(), cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarProducto(plato, 2);
    }

    //Pruebas con el assertEquals
    @Test
    void debeCalcularElTotalSinDomicilio() {
        assertEquals(120000.0, pedido.calcularTotal(), 0.01);
    }

    @Test
    void debeCalcularElTotalConElCostoDelDomicilio() {
        Pedido aDomicilio = new Pedido("11", LocalDate.now(), cliente, ModalidadEntrega.DOMICILIO);
        aDomicilio.agregarProducto(plato, 2);
        aDomicilio.asignarDomicilio(new RapidoExpress(), 4);
        assertEquals(131000.0, aDomicilio.calcularTotal(), 0.01);
    }

    @Test
    void debeConfirmarUnPedidoValido() {
        pedido.confirmar();
        assertEquals(EstadoPedido.CONFIRMADO, pedido.getEstado());
    }

    //Pruebas con el assertThrows
    @Test
    void noDebeConfirmarUnPedidoSinProductos() {
        Pedido vacio = new Pedido("12", LocalDate.now(), cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        assertThrows(IllegalStateException.class, () -> {
            vacio.confirmar();
        });
    }

    @Test
    void noDebeEntregarUnPedidoCancelado() {
        pedido.cancelar();
        assertThrows(IllegalStateException.class, () -> {
            pedido.marcarEntregado();
        });
    }
}
