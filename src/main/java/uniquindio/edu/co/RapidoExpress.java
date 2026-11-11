package uniquindio.edu.co;

public class RapidoExpress implements ServicioDomicilio {
    private double tarifaBase = 5000;
    private double valorKilometro = 1500;
    private double distanciaMaxima = 12;

    public RapidoExpress() {
    }

    public double calcularCosto(double distanciaKm) {
        if (distanciaKm < 0) {
            throw new IllegalArgumentException("La distancia no puede ser negativa");
        }
        return tarifaBase + valorKilometro * distanciaKm;
    }

    public boolean aceptaRecorrido(double distanciaKm) {
        return distanciaKm >= 0 && distanciaKm <= distanciaMaxima;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public double getValorKilometro() {
        return valorKilometro;
    }

    public double getDistanciaMaxima() {
        return distanciaMaxima;
    }

    public String toString() {
        return "RapidoExpress";
    }
}
