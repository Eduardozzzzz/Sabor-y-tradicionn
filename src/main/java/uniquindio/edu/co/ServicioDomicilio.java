package uniquindio.edu.co;

public interface ServicioDomicilio {
    double calcularCosto(double distanciaKm);

    boolean aceptaRecorrido(double distanciaKm);
}
