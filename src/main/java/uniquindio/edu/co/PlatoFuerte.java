package uniquindio.edu.co;

public class PlatoFuerte extends Producto {
    private double gramos;
    private TipoPreparacion tipoPreparacion;
    private ControlCoccion controlCoccion;
    private double incremento = 0.20;

    public PlatoFuerte(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacionMin,
                       double gramos, TipoPreparacion tipoPreparacion, ControlCoccion controlCoccion) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacionMin);
        this.gramos = gramos;
        this.tipoPreparacion = tipoPreparacion;
        this.controlCoccion = controlCoccion;
    }

    public double calcularPrecioVenta() {
        return Math.round(getPrecioBase() * (1 + incremento));
    }

    public boolean cumpleCalidad() {
        return gramos >= 200 && gramos <= 900
                && getTiempoPreparacionMin() >= 10 && getTiempoPreparacionMin() <= 60
                && controlCoccion == ControlCoccion.SI;
    }

    public double getGramos() {
        return gramos;
    }

    public void setGramos(double gramos) {
        this.gramos = gramos;
    }

    public TipoPreparacion getTipoPreparacion() {
        return tipoPreparacion;
    }

    public void setTipoPreparacion(TipoPreparacion tipoPreparacion) {
        this.tipoPreparacion = tipoPreparacion;
    }

    public ControlCoccion getControlCoccion() {
        return controlCoccion;
    }

    public void setControlCoccion(ControlCoccion controlCoccion) {
        this.controlCoccion = controlCoccion;
    }

    public double getIncremento() {
        return incremento;
    }

    public String toString() {
        return super.toString() + " " + gramos + " g " + tipoPreparacion + " cocción " + controlCoccion;
    }
}
