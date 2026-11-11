package uniquindio.edu.co;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteTest {
    Restaurante restaurante;
    Cliente cliente;
    PlatoFuerte plato;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante("Sabor & Tradición", "890123", "Armenia");
        cliente = new Cliente("Ana Gómez", "1091", "3001234567", "ana@correo.com", "Calle 10");
        plato = new PlatoFuerte("P01", "Bandeja paisa", "Frijoles y carne", 20000, 30, 400,
                TipoPreparacion.ASADO, ControlCoccion.SI);
    }

    //Pruebas con el assertTrue y assertFalse
    @Test
    void noDebeRegistrarDosProductosConElMismoCodigo() {
        assertTrue(restaurante.registrarProducto(plato));
        PlatoFuerte repetido = new PlatoFuerte("P01", "Otro", "Otro", 10000, 20, 300,
                TipoPreparacion.FRITO, ControlCoccion.SI);
        assertFalse(restaurante.registrarProducto(repetido));
    }

    @Test
    void noDebeRegistrarDosClientesConElMismoDocumento() {
        assertTrue(restaurante.registrarCliente(cliente));
        Cliente repetido = new Cliente("Otra", "1091", "300", "otra@correo.com", "Calle 20");
        assertFalse(restaurante.registrarCliente(repetido));
    }

    //Pruebas con el assertEquals
    @Test
    void debeEncontrarElProductoPorSuCodigo() {
        restaurante.registrarProducto(plato);
        assertSame(plato, restaurante.buscarProducto("P01"));
    }

    @Test
    void debeCalcularLosIngresosSinElDomicilio() {
        restaurante.registrarProducto(plato);
        restaurante.registrarCliente(cliente);
        Pedido pedido = new Pedido("10", LocalDate.now(), cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarProducto(plato, 2);
        restaurante.registrarPedido(pedido);
        restaurante.confirmarPedido("10");
        assertEquals(48000.0, restaurante.calcularIngresos(), 0.01);
    }

    @Test
    void debeIdentificarElProductoMasVendido() {
        Bebida bebida = new Bebida("B01", "Limonada", "Fría", 8000, 5, 500, 4,
                Temperatura.FRIA, Preparacion.COMUN);
        restaurante.registrarProducto(plato);
        restaurante.registrarProducto(bebida);
        restaurante.registrarCliente(cliente);
        Pedido pedido = new Pedido("10", LocalDate.now(), cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarProducto(plato, 1);
        pedido.agregarProducto(bebida, 3);
        restaurante.registrarPedido(pedido);
        restaurante.confirmarPedido("10");
        assertSame(bebida, restaurante.productoMasVendido());
    }
}
