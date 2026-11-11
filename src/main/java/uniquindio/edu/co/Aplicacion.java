package uniquindio.edu.co;

import javax.swing.*;
import java.time.LocalDate;

public class Aplicacion {
    static void main() {
        Restaurante res = new Restaurante("Sabor & tradición", "1", "Armenia, quindío");
        res.agregarServicio(new RapidoExpress());
        res.agregarServicio(new EntregaSegura());
        int opcion;
        do {
            String entradaMenu = JOptionPane.showInputDialog(null, "Restaurante Sabor & tradición\n" +
                    "1. Registrar plato fuerte\n" +
                    "2. Registrar bebida\n" +
                    "3. Registrar postre\n" +
                    "4. Mostrar productos disponibles\n" +
                    "5. Mostrar productos que no cumplen calidad\n" +
                    "\n" +
                    "6. Registrar cliente\n" +
                    "\n" +
                    "7. Registrar pedido\n" +
                    "8. Asignar servicio de domicilio\n" +
                    "9. Confirmar pedido\n" +
                    "10. Consultar pedidos de un cliente\n" +
                    "11. Marcar pedido como entregado\n" +
                    "12. Cancelar pedido\n" +
                    "\n" +
                    "13. Reportes de ventas\n" +
                    "\n" +
                    "0. Salir del programa\n");
            if (entradaMenu == null) {
                JOptionPane.showMessageDialog(null, "Saliendo, gracias por usar.");
                break;
            }
            try {
                opcion = Integer.parseInt(entradaMenu);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opcion inválida");
                opcion = -1;
            }
            switch (opcion) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema, gracias por usar.");
                    break;

                case 1: {
                    //Registrar plato fuerte



                case 2: {
                    //Registrar bebida


                case 3: {
                    //Registrar postre



                case 4:
                    //Mostrar productos disponibles


                case 5:
                    //Productos que no cumplen calidad



                case 6: {
                    //Registrar cliente
                    String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre completo del cliente: ");
                    if (nombreCliente == null) break;
                    String documento = JOptionPane.showInputDialog("Ingrese el número de documento: ");
                    if (documento == null) break;
                    String telefono = JOptionPane.showInputDialog("Ingrese el teléfono: ");
                    if (telefono == null) break;
                    String correo = JOptionPane.showInputDialog("Ingrese el correo electrónico: ");
                    if (correo == null) break;
                    String direccion = JOptionPane.showInputDialog("Ingrese la dirección de residencia: ");
                    if (direccion == null) break;
                    Cliente cliente = new Cliente(nombreCliente, documento, telefono, correo, direccion);
                    if (res.registrarCliente(cliente)) {
                        JOptionPane.showMessageDialog(null, "Cliente registrado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Este cliente ya está registrado");
                    }
                    break;
                }

                case 7: {
                    //Registrar pedido
                    String documento = JOptionPane.showInputDialog("Ingrese el documento del cliente: ");
                    if (documento == null) break;
                    Cliente cliente = res.buscarCliente(documento);
                    if (cliente == null) {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado");
                        break;
                    }
                    ModalidadEntrega modalidad = null;
                    while (modalidad == null) {
                        String entrada = JOptionPane.showInputDialog("Ingrese la modalidad de entrega:\n- Consumo en restaurante\n- Domicilio");
                        if (entrada == null) break;
                        try {
                            modalidad = ModalidadEntrega.valueOf(entrada.trim().toUpperCase().replace(" ", "_"));
                        } catch (IllegalArgumentException e) {
                            JOptionPane.showMessageDialog(null, "'" + entrada + "' no es una modalidad válida.");
                        }
                    }
                    if (modalidad == null) break;
                    String codigo;
                    do {
                        codigo = "" + (int) (Math.random() * 90 + 10);
                    } while (res.buscarPedido(codigo) != null);
                    Pedido pedido = new Pedido(codigo, LocalDate.now(), cliente, modalidad);
                    String codigoProducto;
                    do {
                        codigoProducto = JOptionPane.showInputDialog("Productos del menú:\n" +
                                res.consultarProductosDisponibles().toString().replace(", ", "\n").replace("[", "").replace("]", "") + "\n" +
                                "Ingrese el código del producto que desea agregar.\n" +
                                "Deje el campo vacío para terminar el pedido: ");
                        if (codigoProducto == null || codigoProducto.trim().isEmpty()) {
                            break;
                        }
                        Producto producto = res.buscarProducto(codigoProducto.trim());
                        if (producto == null) {
                            JOptionPane.showMessageDialog(null, "Producto no encontrado");
                            continue;
                        }
                        Integer cantidad = null;
                        while (cantidad == null) {
                            String entrada = JOptionPane.showInputDialog("Ingrese la cantidad de " + producto.getNombre() + ": ");
                            if (entrada == null) break;
                            try {
                                cantidad = Integer.parseInt(entrada.trim());
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Cantidad inválida, intente de nuevo.");
                            }
                        }
                        if (cantidad == null) break;
                        pedido.agregarProducto(producto, cantidad);
                        if (!producto.cumpleCalidad()) {
                            JOptionPane.showMessageDialog(null, "Aviso: este producto no cumple las condiciones de calidad, el pedido no podrá confirmarse.");
                        }
                    } while (true);
                    if (pedido.getListaDetalles().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Pedido vacío, ha sido cancelado.");
                        break;
                    }
                    res.registrarPedido(pedido);
                    JOptionPane.showMessageDialog(null, "Pedido registrado con el código " + codigo + ".\n" +
                            "Modalidad: " + modalidad + "\n" +
                            "Productos solicitados: \n" + pedido.getListaDetalles().toString().replace(", ", "\n").replace("[", "").replace("]", "") + "\n" +
                            "Valor de los productos: $" + pedido.calcularTotalProductos());
                    break;
                }

                case 8: {
                    //Asignar servicio de domicilio
                    String codigo = JOptionPane.showInputDialog("Lista de pedidos registrados:\n" +
                            res.getListaPedidos().toString().replace(", ", "\n").replace("[", "").replace("]", "") + "\n\n" +
                            "Ingrese el código del pedido: ");
                    if (codigo == null) break;
                    Pedido pedido = res.buscarPedido(codigo);
                    if (pedido == null) {
                        JOptionPane.showMessageDialog(null, "Pedido no encontrado");
                        break;
                    }
                    if (pedido.getModalidad() != ModalidadEntrega.DOMICILIO) {
                        JOptionPane.showMessageDialog(null, "El pedido es de consumo en el restaurante");
                        break;
                    }
                    Double distancia = null;
                    while (distancia == null) {
                        String entrada = JOptionPane.showInputDialog("Ingrese la distancia del recorrido en kilómetros: ");
                        if (entrada == null) break;
                        try {
                            distancia = Double.parseDouble(entrada.trim());
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Distancia inválida, intente de nuevo.");
                        }
                    }
                    if (distancia == null) break;
                    String entradaEmpresa = JOptionPane.showInputDialog("Seleccione la empresa de domicilio:\n1. RápidoExpress\n2. EntregaSegura");
                    if (entradaEmpresa == null) break;
                    ServicioDomicilio servicio;
                    if (entradaEmpresa.trim().equals("1")) {
                        servicio = new RapidoExpress();
                    } else if (entradaEmpresa.trim().equals("2")) {
                        servicio = new EntregaSegura();
                    } else {
                        JOptionPane.showMessageDialog(null, "Opción inválida");
                        break;
                    }
                    try {
                        pedido.asignarDomicilio(servicio, distancia);
                        if (servicio.aceptaRecorrido(distancia)) {
                            JOptionPane.showMessageDialog(null, "Servicio asignado: " + servicio + "\nCosto del domicilio: $" + servicio.calcularCosto(distancia));
                        } else {
                            JOptionPane.showMessageDialog(null, servicio + " no acepta el recorrido de " + distancia + " km");
                        }
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    } catch (IllegalStateException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;
                }

                case 9: {
                    //Confirmar pedido
                    String codigo = JOptionPane.showInputDialog("Lista de pedidos registrados:\n" +
                            res.getListaPedidos().toString().replace(", ", "\n").replace("[", "").replace("]", "") + "\n\n" +
                            "Ingrese el código del pedido a confirmar: ");
                    if (codigo == null) break;
                    try {
                        res.confirmarPedido(codigo);
                        Pedido pedido = res.buscarPedido(codigo);
                        JOptionPane.showMessageDialog(null, "Pedido confirmado correctamente.\n" +
                                "Productos: $" + pedido.calcularTotalProductos() + "\n" +
                                "Domicilio: $" + pedido.calcularCostoDomicilio() + "\n" +
                                "Total: $" + pedido.calcularTotal());
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    } catch (IllegalStateException e) {
                        JOptionPane.showMessageDialog(null, "El pedido no puede confirmarse: " + e.getMessage());
                    }
                    break;
                }

                case 10: {
                    //Consultar pedidos de un cliente
                    String documento = JOptionPane.showInputDialog("Ingrese el documento del cliente: ");
                    if (documento == null) break;
                    try {
                        if (res.consultarPedidosCliente(documento).isEmpty()) {
                            JOptionPane.showMessageDialog(null, "El cliente no tiene pedidos registrados");
                        } else {
                            JOptionPane.showMessageDialog(null, "Pedidos del cliente:\n" +
                                    res.consultarPedidosCliente(documento).toString().replace(", ", "\n").replace("[", "").replace("]", ""));
                        }
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;
                }

                case 11: {
                    //Marcar pedido como entregado
                    String codigo = JOptionPane.showInputDialog("Lista de pedidos registrados:\n" +
                            res.getListaPedidos().toString().replace(", ", "\n").replace("[", "").replace("]", "") + "\n\n" +
                            "Ingrese el código del pedido entregado: ");
                    if (codigo == null) break;
                    Pedido pedido = res.buscarPedido(codigo);
                    if (pedido == null) {
                        JOptionPane.showMessageDialog(null, "Pedido no encontrado");
                        break;
                    }
                    try {
                        pedido.marcarEntregado();
                        JOptionPane.showMessageDialog(null, "Pedido marcado como entregado");
                    } catch (IllegalStateException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;
                }

                case 12: {
                    //Cancelar pedido


                case 13: {
                    //Reportes de ventas
                    Producto masVendido = res.productoMasVendido();
                    JOptionPane.showMessageDialog(null, "Reporte de ventas\n\n" +
                            "Ingresos por productos: $" + res.calcularIngresos() + "\n" +
                            "Ingresos por domicilios: $" + res.calcularIngresosDomicilios() + "\n" +
                            "Producto más vendido: " + (masVendido == null ? "Sin ventas" : masVendido.getNombre()) + "\n" +
                            "Categoría con mayores ingresos: " + res.categoriaMayorIngreso());
                    break;
                }

                default:
                    break;
            }
        } while (opcion != 0);
    }
}