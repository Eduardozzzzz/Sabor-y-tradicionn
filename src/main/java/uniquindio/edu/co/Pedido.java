package uniquindio.edu.co;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String codigo;
    private LocalDate fecha;
    private Cliente cliente;
    private ModalidadEntrega modalidad;
    private EstadoPedido estado;
    private List<DetallePedido> listaDetalles;
    private ServicioDomicilio servicioDomicilio;
    private double distanciaKm;

    public Pedido(String codigo, LocalDate fecha, Cliente cliente, ModalidadEntrega modalidad) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.cliente = cliente;
        this.modalidad = modalidad;
        this.estado = EstadoPedido.PENDIENTE;
        this.listaDetalles = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        if (estado != EstadoPedido.PENDIENTE) {
            throw new IllegalStateException("Solo se pueden agregar productos a un pedido pendiente");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }
        listaDetalles.add(new DetallePedido(producto, cantidad));
    }

    public void asignarDomicilio(ServicioDomicilio servicioDomicilio, double distanciaKm) {
        if (modalidad != ModalidadEntrega.DOMICILIO) {
            throw new IllegalStateException("El pedido no es a domicilio");
        }
        if (estado != EstadoPedido.PENDIENTE) {
            throw new IllegalStateException("Solo un pedido pendiente puede recibir un domicilio");
        }
        if (distanciaKm < 0) {
            throw new IllegalArgumentException("La distancia no puede ser negativa");
        }
        this.servicioDomicilio = servicioDomicilio;
        this.distanciaKm = distanciaKm;
    }

    public double calcularTotalProductos() {
        double total = 0;
        for (DetallePedido detalle : listaDetalles) {
            total += detalle.calcularSubtotal();
        }
        return total;
    }

    public double calcularCostoDomicilio() {
        if (modalidad != ModalidadEntrega.DOMICILIO || servicioDomicilio == null) {
            return 0;
        }
        return servicioDomicilio.calcularCosto(distanciaKm);
    }

    public double calcularTotal() {
        return calcularTotalProductos() + calcularCostoDomicilio();
    }

    public void confirmar() {
        if (estado != EstadoPedido.PENDIENTE) {
            throw new IllegalStateException("Solo un pedido pendiente puede confirmarse");
        }
        if (listaDetalles.isEmpty()) {
            throw new IllegalStateException("El pedido no tiene productos");
        }
        for (DetallePedido detalle : listaDetalles) {
            if (!detalle.getProducto().cumpleCalidad()) {
                throw new IllegalStateException("El producto " + detalle.getProducto().getNombre() + " no cumple las condiciones de calidad");
            }
        }
        if (modalidad == ModalidadEntrega.DOMICILIO) {
            if (servicioDomicilio == null) {
                throw new IllegalStateException("El pedido a domicilio no tiene empresa asignada");
            }
            if (!servicioDomicilio.aceptaRecorrido(distanciaKm)) {
                throw new IllegalStateException("La empresa no acepta el recorrido de " + distanciaKm + " km");
            }
        }
        estado = EstadoPedido.CONFIRMADO;
        cliente.agregarPedido(this);
    }

    public void marcarEntregado() {
        if (estado != EstadoPedido.CONFIRMADO) {
            throw new IllegalStateException("Solo un pedido confirmado puede marcarse como entregado");
        }
        estado = EstadoPedido.ENTREGADO;
    }

    public void cancelar() {
        if (estado == EstadoPedido.ENTREGADO) {
            throw new IllegalStateException("Un pedido entregado no puede cancelarse");
        }
        estado = EstadoPedido.CANCELADO;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ModalidadEntrega getModalidad() {
        return modalidad;
    }

    public void setModalidad(ModalidadEntrega modalidad) {
        this.modalidad = modalidad;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public List<DetallePedido> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<DetallePedido> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    public ServicioDomicilio getServicioDomicilio() {
        return servicioDomicilio;
    }

    public void setServicioDomicilio(ServicioDomicilio servicioDomicilio) {
        this.servicioDomicilio = servicioDomicilio;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public String toString() {
        return codigo + " " + fecha + " " + cliente.getNombreCompleto() + " " + modalidad + " " + estado + " total $" + calcularTotal();
    }
}
