package uniquindio.edu.co;

public class Postre extends Producto {
    private double gramos;
    private double temperaturaAlmacenamiento;
    private Refrigeracion refrigeracion;
    private double incremento = 0.15;

    public Postre(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacionMin,
                  double gramos, double temperaturaAlmacenamiento, Refrigeracion refrigeracion) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacionMin);
        this.gramos = gramos;
        this.temperaturaAlmacenamiento = temperaturaAlmacenamiento;
        this.refrigeracion = refrigeracion;
    }

    public double calcularPrecioVenta() {
        double precio = getPrecioBase() * (1 + incremento);
        if (refrigeracion == Refrigeracion.SI) {
            precio += getPrecioBase() * 0.05;
        }
        return Math.round(precio);
    }

    public boolean cumpleCalidad() {
        if (gramos < 80 || gramos > 500) {
            return false;
        }
        if (refrigeracion == Refrigeracion.SI) {
            return temperaturaAlmacenamiento >= 0 && temperaturaAlmacenamiento <= 6;
        }
        return temperaturaAlmacenamiento >= 15 && temperaturaAlmacenamiento <= 30;
    }

    public double getGramos() {
        return gramos;
    }

    public void setGramos(double gramos) {
        this.gramos = gramos;
    }

    public double getTemperaturaAlmacenamiento() {
        return temperaturaAlmacenamiento;
    }

    public void setTemperaturaAlmacenamiento(double temperaturaAlmacenamiento) {
        this.temperaturaAlmacenamiento = temperaturaAlmacenamiento;
    }

    public Refrigeracion getRefrigeracion() {
        return refrigeracion;
    }

    public void setRefrigeracion(Refrigeracion refrigeracion) {
        this.refrigeracion = refrigeracion;
    }

    public double getIncremento() {
        return incremento;
    }

    public String toString() {
        return super.toString() + " " + gramos + " g " + temperaturaAlmacenamiento + " °C refrigera " + refrigeracion;
    }
}
