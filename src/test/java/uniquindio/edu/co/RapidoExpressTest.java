package uniquindio.edu.co;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RapidoExpressTest {
    RapidoExpress servicio = new RapidoExpress();

    //Pruebas con el assertEquals
    @Test
    void debeCalcularElCostoPara5Kilometros() {
        assertEquals(12500.0, servicio.calcularCosto(5), 0.01);
    }

    @Test
    void debeCalcularElCostoParaElMaximoDe12Kilometros() {
        assertEquals(23000.0, servicio.calcularCosto(12), 0.01);
    }

    //Pruebas con el assertTrue y assertFalse
    @Test
    void debeAceptarUnRecorridoDe12Kilometros() {
        assertTrue(servicio.aceptaRecorrido(12));
    }

    @Test
    void noDebeAceptarUnRecorridoMayorA12Kilometros() {
        assertFalse(servicio.aceptaRecorrido(13));
    }

    //Pruebas con el assertThrows
    @Test
    void noDebePermitirDistanciaNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            servicio.calcularCosto(-1);
        });
    }
}
