package uniquindio.edu.co;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private String nombreComercial;
    private String nit;
    private String direccion;
    private List<Producto> menu;
    private List<Cliente> listaClientes;
    private List<Pedido> listaPedidos;
    private List<ServicioDomicilio> listaServicios;

    public Restaurante(String nombreComercial, String nit, String direccion) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.menu = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
        this.listaPedidos = new ArrayList<>();
        this.listaServicios = new ArrayList<>();
    }

    public boolean registrarProducto(Producto producto) {
        if (buscarProducto(producto.getCodigo()) != null) {
            return false;
        }
        menu.add(producto);
        return true;
    }

    public Producto buscarProducto(String codigo) {
        for (Producto producto : menu) {
            if (producto.getCodigo().equalsIgnoreCase(codigo)) {
                return producto;
            }
        }
        return null;
    }

    public boolean registrarCliente(Cliente cliente) {
        if (buscarCliente(cliente.getDocumento()) != null) {
            return false;
        }
        listaClientes.add(cliente);
        return true;
    }

    public Cliente buscarCliente(String documento) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getDocumento().equalsIgnoreCase(documento)) {
                return cliente;
            }
        }
        return null;
    }

    public boolean registrarPedido(Pedido pedido) {
        if (buscarPedido(pedido.getCodigo()) != null) {
            return false;
        }
        listaPedidos.add(pedido);
        return true;
    }

    public Pedido buscarPedido(String codigo) {
        for (Pedido pedido : listaPedidos) {
            if (pedido.getCodigo().equalsIgnoreCase(codigo)) {
                return pedido;
            }
        }
        return null;
    }

    public void agregarServicio(ServicioDomicilio servicio) {
        listaServicios.add(servicio);
    }

    public void confirmarPedido(String codigo) {
        Pedido pedido = buscarPedido(codigo);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido no encontrado");
        }
        for (DetallePedido detalle : pedido.getListaDetalles()) {
            if (!menu.contains(detalle.getProducto())) {
                throw new IllegalStateException("El producto " + detalle.getProducto().getNombre() + " no pertenece al menú");
            }
        }
        pedido.confirmar();
    }

    public List<Producto> consultarProductosDisponibles() {
        return menu;
    }

    public List<Producto> productosNoCumplenCalidad() {
        List<Producto> rechazados = new ArrayList<>();
        for (Producto producto : menu) {
            if (!producto.cumpleCalidad()) {
                rechazados.add(producto);
            }
        }
        return rechazados;
    }

    public List<Pedido> consultarPedidosCliente(String documento) {
        Cliente cliente = buscarCliente(documento);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }
        return cliente.getListaPedidos();
    }

    public double calcularIngresos() {
        double total = 0;
        for (Pedido pedido : listaPedidos) {
            if (pedido.getEstado() == EstadoPedido.CONFIRMADO || pedido.getEstado() == EstadoPedido.ENTREGADO) {
                total += pedido.calcularTotalProductos();
            }
        }
        return total;
    }

    public double calcularIngresosDomicilios() {
        double total = 0;
        for (Pedido pedido : listaPedidos) {
            if (pedido.getEstado() == EstadoPedido.CONFIRMADO || pedido.getEstado() == EstadoPedido.ENTREGADO) {
                total += pedido.calcularCostoDomicilio();
            }
        }
        return total;
    }

    public Producto productoMasVendido() {
        Producto masVendido = null;
        int mayorCantidad = 0;
        for (Pedido pedido : listaPedidos) {
            if (pedido.getEstado() == EstadoPedido.CANCELADO) {
                continue;
            }
            for (DetallePedido detalle : pedido.getListaDetalles()) {
                int unidades = 0;
                for (Pedido otroPedido : listaPedidos) {
                    if (otroPedido.getEstado() == EstadoPedido.CANCELADO) {
                        continue;
                    }
                    for (DetallePedido otroDetalle : otroPedido.getListaDetalles()) {
                        if (otroDetalle.getProducto().getCodigo().equalsIgnoreCase(detalle.getProducto().getCodigo())) {
                            unidades += otroDetalle.getCantidad();
                        }
                    }
                }
                if (unidades > mayorCantidad) {
                    mayorCantidad = unidades;
                    masVendido = detalle.getProducto();
                }
            }
        }
        return masVendido;
    }

    public String categoriaMayorIngreso() {
        double platos = 0;
        double bebidas = 0;
        double postres = 0;
        for (Pedido pedido : listaPedidos) {
            if (pedido.getEstado() == EstadoPedido.CANCELADO) {
                continue;
            }
            for (DetallePedido detalle : pedido.getListaDetalles()) {
                Producto producto = detalle.getProducto();
                if (producto instanceof PlatoFuerte) {
                    platos += detalle.calcularSubtotal();
                } else if (producto instanceof Bebida) {
                    bebidas += detalle.calcularSubtotal();
                } else if (producto instanceof Postre) {
                    postres += detalle.calcularSubtotal();
                }
            }
        }
        if (platos >= bebidas && platos >= postres) {
            return "Plato fuerte";
        }
        if (bebidas >= postres) {
            return "Bebida";
        }
        return "Postre";
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Producto> getMenu() {
        return menu;
    }

    public void setMenu(List<Producto> menu) {
        this.menu = menu;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public List<ServicioDomicilio> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<ServicioDomicilio> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public String toString() {
        return nombreComercial + " NIT " + nit + " " + direccion;
    }
}
