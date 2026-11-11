package uniquindio.edu.co;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlatoFuerteTest {
    PlatoFuerte plato = new PlatoFuerte("P01", "Bandeja paisa", "Frijoles y carne", 50000, 30, 400,
            TipoPreparacion.ASADO, ControlCoccion.SI);

    //Pruebas con el assertEquals
    @Test
    void debeCalcularElPrecioConElIncrementoDel20() {
        assertEquals(60000.0, plato.calcularPrecioVenta(), 0.01);
    }

    @Test
    void debeCumplirLaCalidadConLosDatosCorrectos() {
        assertTrue(plato.cumpleCalidad());
    }

    //Pruebas con el assertTrue y assertFalse
    @Test
    void noDebeCumplirSiPesaMenosDe200Gramos() {
        PlatoFuerte liviano = new PlatoFuerte("P02", "Porcion", "Pequeña", 20000, 20, 150,
                TipoPreparacion.FRITO, ControlCoccion.SI);
        assertFalse(liviano.cumpleCalidad());
    }

    @Test
    void noDebeCumplirSiElTiempoEstaFueraDelRango() {
        PlatoFuerte lento = new PlatoFuerte("P03", "Guiso", "Lento", 20000, 90, 400,
                TipoPreparacion.HORNEADO, ControlCoccion.SI);
        assertFalse(lento.cumpleCalidad());
    }

    //Pruebas con el assertThrows
    @Test
    void noDebePermitirPrecioBaseNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PlatoFuerte("P04", "Mal", "Mal", -1000, 20, 300, TipoPreparacion.ASADO, ControlCoccion.SI);
        });
    }
}
