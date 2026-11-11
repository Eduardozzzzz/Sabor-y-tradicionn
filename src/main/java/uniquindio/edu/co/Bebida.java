package uniquindio.edu.co;

public class Bebida extends Producto {
    private double mililitros;
    private double temperaturaServicio;
    private Temperatura temperatura;
    private Preparacion preparacion;
    private double incremento = 0.10;

    public Bebida(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacionMin,
                  double mililitros, double temperaturaServicio, Temperatura temperatura, Preparacion preparacion) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacionMin);
        this.mililitros = mililitros;
        this.temperaturaServicio = temperaturaServicio;
        this.temperatura = temperatura;
        this.preparacion = preparacion;
    }

    public double calcularPrecioVenta() {
        double precio = getPrecioBase() * (1 + incremento);
        if (preparacion == Preparacion.ESPECIAL) {
            precio += 3000;
        }
        return Math.round(precio);
    }

    public boolean cumpleCalidad() {
        if (mililitros < 150 || mililitros > 1500) {
            return false;
        }
        if (temperatura == Temperatura.FRIA) {
            return temperaturaServicio >= 0 && temperaturaServicio <= 10;
        }
        return temperaturaServicio >= 55 && temperaturaServicio <= 85;
    }

    public double getMililitros() {
        return mililitros;
    }

    public void setMililitros(double mililitros) {
        this.mililitros = mililitros;
    }

    public double getTemperaturaServicio() {
        return temperaturaServicio;
    }

    public void setTemperaturaServicio(double temperaturaServicio) {
        this.temperaturaServicio = temperaturaServicio;
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }

    public Preparacion getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(Preparacion preparacion) {
        this.preparacion = preparacion;
    }

    public double getIncremento() {
        return incremento;
    }

    public String toString() {
        return super.toString() + " " + mililitros + " ml " + temperatura + " " + preparacion;
    }
}
