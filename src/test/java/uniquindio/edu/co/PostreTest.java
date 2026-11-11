package uniquindio.edu.co;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostreTest {
    Postre postre = new Postre("D01", "Flan", "Postre de caramelo", 10000, 10, 200, 4, Refrigeracion.SI);

    //Pruebas con el assertEquals
    @Test
    void debeCalcularElPrecioConElIncrementoDel15() {
        Postre sinRefrigeracion = new Postre("D02", "Galleta", "Postre seco", 10000, 5, 100, 20, Refrigeracion.NO);
        assertEquals(11500.0, sinRefrigeracion.calcularPrecioVenta(), 0.01);
    }

    @Test
    void debeSumarEl5AdicionalSiEsRefrigerado() {
        assertEquals(12000.0, postre.calcularPrecioVenta(), 0.01);
    }

    //Pruebas con el assertTrue y assertFalse
    @Test
    void debeCumplirLaCalidadSiEstaRefrigerado() {
        assertTrue(postre.cumpleCalidad());
    }

    @Test
    void noDebeCumplirSiNoEstaRefrigeradoYA40Grados() {
        Postre caliente = new Postre("D03", "Torta", "Sin refrigeración", 9000, 5, 200, 40, Refrigeracion.NO);
        assertFalse(caliente.cumpleCalidad());
    }

    @Test
    void noDebeCumplirSiPesaMasDe500Gramos() {
        Postre grande = new Postre("D04", "Pastel", "Grande", 20000, 15, 600, 4, Refrigeracion.SI);
        assertFalse(grande.cumpleCalidad());
    }
}
