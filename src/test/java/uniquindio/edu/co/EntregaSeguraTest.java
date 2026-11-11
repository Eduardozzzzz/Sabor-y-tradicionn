package uniquindio.edu.co;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntregaSeguraTest {
    EntregaSegura servicio = new EntregaSegura();

    //Pruebas con el assertEquals
    @Test
    void debeCalcularElCostoSinRecargoHasta5Kilometros() {
        assertEquals(12000.0, servicio.calcularCosto(5), 0.01);
    }

    @Test
    void debeSumarElRecargoCuandoSupera5Kilometros() {
        assertEquals(16000.0, servicio.calcularCosto(6), 0.01);
    }

    @Test
    void debeCalcularElCostoParaElMaximoDe20Kilometros() {
        assertEquals(30000.0, servicio.calcularCosto(20), 0.01);
    }

    //Pruebas con el assertTrue y assertFalse
    @Test
    void noDebeAceptarUnRecorridoMayorA20Kilometros() {
        assertFalse(servicio.aceptaRecorrido(21));
    }

    //Pruebas con el assertThrows
    @Test
    void noDebePermitirDistanciaNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            servicio.calcularCosto(-5);
        });
    }
}
