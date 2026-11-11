package uniquindio.edu.co;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {
    private String direccion;
    private List<Pedido> listaPedidos;

    public Cliente(String nombreCompleto, String documento, String telefono, String correo, String direccion) {
        super(nombreCompleto, documento, telefono, correo);
        this.direccion = direccion;
        this.listaPedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        if (!listaPedidos.contains(pedido)) {
            listaPedidos.add(pedido);
        }
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public String toString() {
        return getDocumento() + " - " + getNombreCompleto() + " " + direccion;
    }
}
