package uniquindio.edu.co;

public class EntregaSegura implements ServicioDomicilio {
    private double tarifaBase = 7000;
    private double valorKilometro = 1000;
    private double distanciaMaxima = 20;
    private double recargo = 3000;
    private double limiteRecargo = 5;

    public EntregaSegura() {
    }

    public double calcularCosto(double distanciaKm) {
        if (distanciaKm < 0) {
            throw new IllegalArgumentException("La distancia no puede ser negativa");
        }
        double costo = tarifaBase + valorKilometro * distanciaKm;
        if (distanciaKm > limiteRecargo) {
            costo += recargo;
        }
        return costo;
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

    public double getRecargo() {
        return recargo;
    }

    public String toString() {
        return "EntregaSegura";
    }
}
