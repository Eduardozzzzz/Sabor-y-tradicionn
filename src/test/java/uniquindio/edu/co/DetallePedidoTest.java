package uniquindio.edu.co;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DetallePedidoTest {
    PlatoFuerte plato = new PlatoFuerte("P01", "Bandeja paisa", "Frijoles y carne", 50000, 30, 400,
            TipoPreparacion.ASADO, ControlCoccion.SI);

    //Pruebas con el assertEquals
    @Test
    void debeCalcularElSubtotalConLaCantidad() {
        DetallePedido detalle = new DetallePedido(plato, 2);
        assertEquals(120000.0, detalle.calcularSubtotal(), 0.01);
    }

    @Test
    void debeCalcularElSubtotalConUnaUnidad() {
        DetallePedido detalle = new DetallePedido(plato, 1);
        assertEquals(60000.0, detalle.calcularSubtotal(), 0.01);
    }

    //Pruebas con el assertSame
    @Test
    void debeGuardarElProductoDelDetalle() {
        DetallePedido detalle = new DetallePedido(plato, 2);
        assertSame(plato, detalle.getProducto());
    }

    //Pruebas con el assertThrows
    @Test
    void noDebePermitirCantidadCero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DetallePedido(plato, 0);
        });
    }

    @Test
    void noDebePermitirCantidadNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DetallePedido(plato, -3);
        });
    }
}
