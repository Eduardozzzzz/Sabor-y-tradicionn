package uniquindio.edu.co;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BebidaTest {
    Bebida bebida = new Bebida("B01", "Limonada", "Bebida fría", 8000, 5, 500, 4,
            Temperatura.FRIA, Preparacion.COMUN);

    //Pruebas con el assertEquals
    @Test
    void debeCalcularElPrecioConElIncrementoDel10() {
        assertEquals(8800.0, bebida.calcularPrecioVenta(), 0.01);
    }

    @Test
    void debeSumarElRecargoDe3000SiEsEspecial() {
        Bebida especial = new Bebida("B02", "Café especial", "Caliente", 8000, 5, 300, 70,
                Temperatura.CALIENTE, Preparacion.ESPECIAL);
        assertEquals(11800.0, especial.calcularPrecioVenta(), 0.01);
    }

    //Pruebas con el assertTrue y assertFalse
    @Test
    void debeCumplirLaCalidadSiEsFriaYEstaEntre0Y10Grados() {
        assertTrue(bebida.cumpleCalidad());
    }

    @Test
    void noDebeCumplirSiEsCalienteYEstaA50Grados() {
        Bebida tibia = new Bebida("B03", "Chocolate", "Caliente", 7000, 5, 300, 50,
                Temperatura.CALIENTE, Preparacion.COMUN);
        assertFalse(tibia.cumpleCalidad());
    }

    @Test
    void noDebeCumplirSiElVolumenEsMenorA150Mililitros() {
        Bebida pequena = new Bebida("B04", "Shot", "Pequeña", 3000, 2, 100, 4,
                Temperatura.FRIA, Preparacion.COMUN);
        assertFalse(pequena.cumpleCalidad());
    }
}
