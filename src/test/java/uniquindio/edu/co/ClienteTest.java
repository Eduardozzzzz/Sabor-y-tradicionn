package uniquindio.edu.co;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {
    Cliente cliente = new Cliente("Ana Gómez", "1091", "3001234567", "ana@correo.com", "Calle 10");

    //Pruebas con el assertEquals
    @Test
    void debeGuardarLosDatosDelCliente() {
        assertEquals("Ana Gómez", cliente.getNombreCompleto());
        assertEquals("1091", cliente.getDocumento());
        assertEquals("3001234567", cliente.getTelefono());
        assertEquals("ana@correo.com", cliente.getCorreo());
        assertEquals("Calle 10", cliente.getDireccion());
    }

    @Test
    void debeIniciarSinPedidosEnElHistorial() {
        assertEquals(0, cliente.getListaPedidos().size());
    }

    @Test
    void debeAgregarUnPedidoAlHistorial() {
        Pedido pedido = new Pedido("10", LocalDate.now(), cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        cliente.agregarPedido(pedido);
        assertEquals(1, cliente.getListaPedidos().size());
    }

    @Test
    void noDebeRepetirElMismoPedidoEnElHistorial() {
        Pedido pedido = new Pedido("10", LocalDate.now(), cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        cliente.agregarPedido(pedido);
        cliente.agregarPedido(pedido);
        assertEquals(1, cliente.getListaPedidos().size());
    }

    @Test
    void debeMostrarElDocumentoEnElToString() {
        assertTrue(cliente.toString().contains("1091"));
    }
}
