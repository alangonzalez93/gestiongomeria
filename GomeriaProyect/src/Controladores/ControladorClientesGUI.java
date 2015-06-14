package Controladores;

import ABMs.ABMClientes;
import ABMs.ABMCobros;
import ABMs.ABMVentas;
import Busqueda.Busqueda;
import Interfaz.AplicacionGUI;
import Interfaz.CargarVentaGUI;
import Interfaz.ClientesGUI;
import Interfaz.DetalleVentaGUI;
import Interfaz.NuevoCobroGUI;
import Modelos.Articulo;
import Modelos.ArticulosVentas;
import Modelos.Cliente;
import Modelos.Cobro;
import Modelos.Venta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alan
 */
public class ControladorClientesGUI implements ActionListener {

    ClientesGUI clientesGUI;
    ABMClientes abmClientes;
    ABMCobros abmCobros;
    ABMVentas abmVentas;
    Busqueda busquedaClientes;
    boolean apreteModificar = false;
    NuevoCobroGUI nuevoPagoGUI;
    DetalleVentaGUI detallesVentaGUI;
    AplicacionGUI aplicacionGUI;

    public ControladorClientesGUI(ClientesGUI cg, AplicacionGUI apliGUI) {
        this.clientesGUI = cg;
        clientesGUI.setActionListener(this);
        abmClientes = new ABMClientes();
        abmCobros = new ABMCobros();
        abmVentas = new ABMVentas();
        busquedaClientes = new Busqueda();
        nuevoPagoGUI = new NuevoCobroGUI(apliGUI, true);
        nuevoPagoGUI.setActionListener(this);
        aplicacionGUI = apliGUI;
        this.clientesGUI.getRazonBox().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (clientesGUI.getRazonBox().getSelectedItem().equals("RESP. INSC.")) {
                    clientesGUI.getCuitTxt().setEnabled(true);
                    clientesGUI.getCuitLbl().setEnabled(true);
                } else {
                    clientesGUI.getCuitTxt().setEnabled(false);
                    clientesGUI.getCuitLbl().setEnabled(false);
                    clientesGUI.getCuitTxt().setText("");
                }
            }
        });
        //////////////Actualizar datos con click/////////////////////
        /*clientesGUI.getTablaClientes().addMouseListener(new java.awt.event.MouseAdapter() {
         @Override
         public void mouseClicked(java.awt.event.MouseEvent evt) {
         if (clientesGUI.getTablaClientes().getSelectedRowCount() == 1){
         tablaClienteMouseClicked(evt);
         }else{
         clientesGUI.getTablaVentaClientesDefault().setRowCount(0);
         clientesGUI.getTablaCuotasVentasClientesDefault().setRowCount(0);
         clientesGUI.LimpiarCampos();
         clientesGUI.EstadoInicial();
         clientesGUI.getLblCompoPor().setText("0.00");
         clientesGUI.getLblSaldo().setText("0.00");
         }
         }
         });*/
        ///////////////////////////////////////////////////////////////////////
        /////////Actualizar datos cuando cambie la seleccion (Ej. flechas)/////
        clientesGUI.getTablaClientes().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (clientesGUI.getTablaClientes().getSelectedRowCount() == 1) {
                    tablaClienteMouseClicked(null);
                } else {
                    clientesGUI.getTablaVentaClientesDefault().setRowCount(0);
                    clientesGUI.getTablaCuotasVentasClientesDefault().setRowCount(0);
                    clientesGUI.LimpiarCampos();
                    clientesGUI.EstadoInicial();
                    clientesGUI.getLblCompoPor().setText("0.00");
                    clientesGUI.getLblSaldo().setText("0.00");
                }
            }
        });
        ///////////////////////////////////////////////////////////////////////
        //////////////Actualizar datos con click//////////////////////////////
        clientesGUI.getTablaVentasCliente().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (clientesGUI.getTablaVentasCliente().getSelectedRowCount() == 1) {
                    clientesGUI.getBtnCrearNuevoPago().setEnabled(true);
                    clientesGUI.getBtnEliminarVenta().setEnabled(true);
                    clientesGUI.getBtnModificarVenta().setEnabled(true);
                    clientesGUI.getBtnDetalles().setEnabled(true);
                    tablaVentaClienteMouseClicked(evt);
                } else {
                    clientesGUI.getBtnCrearNuevoPago().setEnabled(false);
                    clientesGUI.getBtnEliminarVenta().setEnabled(false);
                    clientesGUI.getBtnModificarVenta().setEnabled(false);
                    clientesGUI.getBtnDetalles().setEnabled(false);
                    clientesGUI.getTablaCuotasVentasClientesDefault().setRowCount(0);
                }
            }
        });
        //////////////////////////////////////////////////////////////////////
        ///////Actualizar datos cuando cambie de seleccion///////////////////
        clientesGUI.getTablaVentasCliente().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (clientesGUI.getTablaVentasCliente().getSelectedRowCount() == 1) {
                    clientesGUI.getBtnCrearNuevoPago().setEnabled(true);
                    clientesGUI.getBtnEliminarVenta().setEnabled(true);
                    clientesGUI.getBtnModificarVenta().setEnabled(true);
                    clientesGUI.getBtnDetalles().setEnabled(true);
                    tablaVentaClienteMouseClicked(null);
                } else {
                    clientesGUI.getBtnCrearNuevoPago().setEnabled(false);
                    clientesGUI.getBtnEliminarVenta().setEnabled(false);
                    clientesGUI.getBtnModificarVenta().setEnabled(false);
                    clientesGUI.getBtnDetalles().setEnabled(false);
                }
            }
        });
        ///////////////////////////////////////////////////////////////////
        ///////////////////Actualiza tabla con click///////////////////////
        /*clientesGUI.getTableCuotasVentaCliente().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (clientesGUI.getTableCuotasVentaCliente().getSelectedRowCount() == 1) {
                    clientesGUI.getBtnPagarCuota().setEnabled(false);
                    clientesGUI.getBtnEliminarPago().setEnabled(true);
                    clientesGUI.getBtnModificarPago().setEnabled(true);
                    int r = clientesGUI.getTableCuotasVentaCliente().getSelectedRow();
                    if (String.valueOf(clientesGUI.getTablaCuotasVentasClientesDefault().getValueAt(r, 2)).equals("IMPAGO")) {
                        clientesGUI.getBtnPagarCuota().setEnabled(true);
                    }
                } else {
                    clientesGUI.getBtnEliminarPago().setEnabled(false);
                    clientesGUI.getBtnModificarPago().setEnabled(false);
                    clientesGUI.getBtnPagarCuota().setEnabled(false);
                }
            }
        });*/
        ///////////////////////////////////////////////////////////////////
        ///////Actualizar datos cuando cambie de seleccion///////////////////
        clientesGUI.getTableCuotasVentaCliente().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (clientesGUI.getTableCuotasVentaCliente().getSelectedRowCount() == 1) {
                    clientesGUI.getBtnPagarCuota().setEnabled(false);
                    clientesGUI.getBtnEliminarPago().setEnabled(true);
                    clientesGUI.getBtnModificarPago().setEnabled(true);
                    int r = clientesGUI.getTableCuotasVentaCliente().getSelectedRow();
                    if (String.valueOf(clientesGUI.getTablaCuotasVentasClientesDefault().getValueAt(r, 2)).equals("IMPAGO")) {
                        clientesGUI.getBtnPagarCuota().setEnabled(true);
                    }
                } else {
                    clientesGUI.getBtnEliminarPago().setEnabled(false);
                    clientesGUI.getBtnModificarPago().setEnabled(false);
                    clientesGUI.getBtnPagarCuota().setEnabled(false);
                }
            }
        });
        ///////////////////////////////////////////////////////////////////
        clientesGUI.getBusquedaClientesTxt().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaKeyReleased(evt);
            }
        });

    }

    private void tablaVentaClienteMouseClicked(MouseEvent evt) {
        abrirBase();
        clientesGUI.getTablaCuotasVentasClientesDefault().setRowCount(0);
        int r = clientesGUI.getTablaVentasCliente().getSelectedRow();
        LazyList<Cobro> listCobros = Cobro.where("venta_id = ?", clientesGUI.getTablaVentaClientesDefault().getValueAt(r, 0));
        for (Cobro c : listCobros) {
            Object[] row = new Object[4];
            row[0] = c.get("id");
            row[1] = dateToMySQLDate(c.getDate("fecha"), true);
            if (c.getDate("fecha_pago") != null) {
                row[2] = dateToMySQLDate(c.getDate("fecha_pago"), true);
            }else{
                row[2] = "IMPAGO";
            }
            //row[3] = c.getString("estado");
            row[3] = c.getBigDecimal("monto").setScale(2, RoundingMode.CEILING).toString();
            clientesGUI.getTablaCuotasVentasClientesDefault().addRow(row);
        }
        Base.close();
    }

    private void tablaClienteMouseClicked(MouseEvent evt) {
        System.out.println("cuantas veces me ejecuto?");
        CargarDatosClienteSeleccionado();
        CargarVentasClienteSeleccionado();
        clientesGUI.getTablaCuotasVentasClientesDefault().setRowCount(0);
        clientesGUI.EstadoClienteSeleccionado(); //cambio vista
    }

    private void busquedaKeyReleased(KeyEvent evt) {
        abrirBase();
        List<Cliente> listaClientes = busquedaClientes.BuscarClientes(clientesGUI.getBusquedaClientesTxt().getText(), String.valueOf(clientesGUI.getBuscarBox().getSelectedItem()));
        clientesGUI.getTablaClientesDefault().setRowCount(0);
        clientesGUI.getEncontradosLbl().setText(String.valueOf(listaClientes.size()));
        for (Cliente cliente : listaClientes) {
            Object row[] = new String[3];
            row[0] = cliente.getString("id");
            row[1] = /*cliente.getString("apellido")+", "+*/ cliente.getString("nombre");
            row[2] = cliente.getString("ciudad");
            clientesGUI.getTablaClientesDefault().addRow(row);
        }
        Base.close();
    }

    //Trae de la base de datos todos los datos del cliente seleccionado en la tabla y los pone en la interfaz
    public void CargarDatosClienteSeleccionado() {
        int r = clientesGUI.getTablaClientes().getSelectedRow();
        String idCliente = (String) clientesGUI.getTablaClientesDefault().getValueAt(r, 0);
        abrirBase();
        Cliente cliente = Cliente.first("id = ?", idCliente);
        if (cliente != null) {
            String array[] = cliente.getString("nombre").split(", ");
            clientesGUI.getApellidoTxt().setText(array[0]);
            clientesGUI.getNombreTxt().setText(array[1]);
            clientesGUI.getIdTxt().setText(cliente.getString("id"));
            clientesGUI.getTelTxt().setText(cliente.getString("telefono"));
            clientesGUI.getCelularTxt().setText(cliente.getString("celular"));
            clientesGUI.getDireccionTxt().setText(cliente.getString("direccion"));
            clientesGUI.getCiudadBox().setSelectedItem(cliente.getString("ciudad"));
            clientesGUI.getZonaBox().setSelectedItem(cliente.getString("zona"));
            clientesGUI.getMailTxt().setText(cliente.getString("email"));
            clientesGUI.getRazonBox().setSelectedItem(cliente.getString("razon"));
            clientesGUI.getCuitTxt().setText(cliente.getString("cuit"));
            clientesGUI.getTipoClienteBox().setSelectedItem(cliente.getString("tipo"));
        } else {
            JOptionPane.showMessageDialog(clientesGUI, "Ocurrio un error inesperado, intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        Base.close();
    }

    public void CargarVentasClienteSeleccionado() {
        abrirBase();
        clientesGUI.getTablaVentaClientesDefault().setRowCount(0);
        BigDecimal totalVentas = new BigDecimal(0);
        BigDecimal totalPago = new BigDecimal(0);
        LazyList<Venta> ventasCliente = Venta.where("cliente_id = ?", clientesGUI.getIdTxt().getText());
        for (Venta v : ventasCliente) {
            Object[] row = new Object[4];
            row[0] = v.get("id");
            row[1] = dateToMySQLDate(v.getDate("fecha"), true);
            row[2] = v.getBigDecimal("monto").setScale(2, RoundingMode.CEILING).toString();
            row[3] = v.getString("forma_pago");
            clientesGUI.getTablaVentaClientesDefault().addRow(row);
            totalVentas = totalVentas.add(v.getBigDecimal("monto"));

            LazyList<Cobro> listaCobros = Cobro.where("venta_id = ?", v.get("id"));
            for (Cobro c : listaCobros) {
                if (c.getDate("fecha_pago") != null) {
                    totalPago = totalPago.add(c.getBigDecimal("monto"));
                }
            }
        }

        clientesGUI.getLblCompoPor().setText(totalVentas.setScale(2, RoundingMode.CEILING).toString());
        clientesGUI.getLblSaldo().setText(totalVentas.subtract(totalPago).setScale(2, RoundingMode.CEILING).toString());
        Base.close();
    }

    //Retorna un cliente con todos los datos traidos de los campos de texto de la interfaz
    public Cliente ObtenerDatosCliente(String id) {
        Cliente cliente;
        if (id == null) {
            cliente = new Cliente();
        } else {
            abrirBase();
            cliente = Cliente.first("id = ?", clientesGUI.getIdTxt().getText());
            Base.close();
        }
        cliente.set("nombre", clientesGUI.getApellidoTxt().getText().toUpperCase() + ", " + clientesGUI.getNombreTxt().getText().toUpperCase());
        cliente.set("telefono", clientesGUI.getTelTxt().getText());
        cliente.set("celular", clientesGUI.getCelularTxt().getText());
        cliente.set("direccion", clientesGUI.getDireccionTxt().getText().toUpperCase());
        cliente.set("ciudad", clientesGUI.getCiudadBox().getSelectedItem());
        cliente.set("zona", clientesGUI.getZonaBox().getSelectedItem());
        cliente.set("email", clientesGUI.getMailTxt().getText());
        cliente.set("razon", clientesGUI.getRazonBox().getSelectedItem());
        cliente.set("cuit", clientesGUI.getCuitTxt().getText());
        cliente.set("tipo", clientesGUI.getTipoClienteBox().getSelectedItem());
        return cliente;
    }

    private void ActualizarLista() {
        abrirBase();
        clientesGUI.getTablaClientesDefault().setRowCount(0);
        LazyList<Cliente> listaClientes = Cliente.findAll();
        clientesGUI.getEncontradosLbl().setText(String.valueOf(listaClientes.size()));
        for (Cliente cliente : listaClientes) {
            Object row[] = new String[3];
            row[0] = cliente.getString("id");
            row[1] = /*cliente.getString("apellido")+", "+*/ cliente.getString("nombre");
            row[2] = cliente.getString("ciudad");
            clientesGUI.getTablaClientesDefault().addRow(row);
        }
        Base.close();
    }

    public boolean DatosObligatoriosOK() {
        if ((!clientesGUI.getApellidoTxt().getText().equals(""))
                && (!clientesGUI.getNombreTxt().getText().equals(""))
                && ((!clientesGUI.getTelTxt().getText().equals(""))
                || (!clientesGUI.getCelularTxt().getText().equals("")))
                && (!clientesGUI.getDireccionTxt().getText().equals(""))) {
            return true;
        } else {
            return false;
        }
    }

    /*paraMostrar == true: retorna la fecha en formato dd/mm/yyyy (formato pantalla)
     * paraMostrar == false: retorna la fecha en formato yyyy/mm/dd (formato SQL)
     */
    public String dateToMySQLDate(Date fecha, boolean paraMostrar) {
        if (paraMostrar) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(fecha);
        } else {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(fecha);
        }
    }

    public void abrirBase() {
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/gomeria", "root", "root");
        }
    }

    private boolean PagarCuota(int row) {
        boolean result = true;
        abrirBase();
        Base.openTransaction();
        Cobro cobro = Cobro.first("id = ?", clientesGUI.getTablaCuotasVentasClientesDefault().getValueAt(row, 0));
        //cobro.set("estado", "PAGO");
        cobro.setDate("fecha_pago", dateToMySQLDate(Calendar.getInstance().getTime(), false));
        result = result && cobro.saveIt();
        Base.commitTransaction();
        Base.close();
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clientesGUI.getBtnNuevo()) {
            if (clientesGUI.getBtnNuevo().getText().equals("Nuevo")) { //Si el nombre del btn es "nuevo" entonces solo habilito los campos
                clientesGUI.ApreteBtnNuevoModificar();
                clientesGUI.LimpiarCampos();
            } else {
                if (clientesGUI.getBtnNuevo().getText().equals("Guardar") && !apreteModificar) {
                    if (DatosObligatoriosOK()) {
                        if (abmClientes.Alta(ObtenerDatosCliente(null))) {
                            JOptionPane.showMessageDialog(clientesGUI, "Cliente registrado exitosamente!");
                            clientesGUI.LimpiarCampos();
                            clientesGUI.EstadoInicial();
                            ActualizarLista();
                        } else {
                            JOptionPane.showMessageDialog(clientesGUI, "Ocurrio un error, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(clientesGUI, "Los campos: Nombre, Apellido, Telefono (fijo o celular) y Direccion son abligatorios.", "Atencion!", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    if (clientesGUI.getBtnNuevo().getText().equals("Guardar") && apreteModificar) {
                        if (DatosObligatoriosOK()) {
                            if (abmClientes.Modificar(ObtenerDatosCliente(clientesGUI.getIdTxt().getText()))) {
                                JOptionPane.showMessageDialog(clientesGUI, "Cliente modificado exitosamente!");
                                clientesGUI.EstadoLuegoDeModificar();
                                ActualizarLista();
                                apreteModificar = false;
                            } else {
                                JOptionPane.showMessageDialog(clientesGUI, "Ocurrio un error, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(clientesGUI, "Los campos: Nombre, Apellido, Telefono (fijo o celular) y Direccion son abligatorios.", "Atencion!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        }
        if (e.getSource() == clientesGUI.getBtnModificar()) {
            apreteModificar = true;
            clientesGUI.ApreteBtnNuevoModificar();
        }
        if (e.getSource() == clientesGUI.getBtnEliminar()) {
            if (clientesGUI.getBtnEliminar().getText().equals("Eliminar")) {
                Integer resp = JOptionPane.showConfirmDialog(clientesGUI, "¿Desea borrar el cliente " + clientesGUI.getApellidoTxt().getText() + ", " + clientesGUI.getNombreTxt().getText() + "?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    if (abmClientes.Eliminar(ObtenerDatosCliente(clientesGUI.getIdTxt().getText()))) {
                        JOptionPane.showMessageDialog(clientesGUI, "Cliente eliminado correctamente!");
                        clientesGUI.EstadoInicial();
                        clientesGUI.LimpiarCampos();
                        ActualizarLista();
                    } else {
                        JOptionPane.showMessageDialog(clientesGUI, "Ocurrio un error, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                if (clientesGUI.getBtnEliminar().getText().equals("Cancelar")) {
                    clientesGUI.EstadoInicial();
                    clientesGUI.LimpiarCampos();
                }
            }
        }
        //////////////Botones ventas//////////////////
        if (e.getSource().equals(clientesGUI.getBtnDetalles())) {
            if (clientesGUI.getTablaVentasCliente().getSelectedRowCount() == 1) {
                int r = clientesGUI.getTablaVentasCliente().getSelectedRow();
                detallesVentaGUI = new DetalleVentaGUI(aplicacionGUI, true);
                ObtenerDetallesVenta(r);
                detallesVentaGUI.setLocationRelativeTo(null);
                detallesVentaGUI.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(clientesGUI, "Debe seleccionar una venta!", "Atencion!", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource().equals(clientesGUI.getBtnEliminarVenta())) {
            if (clientesGUI.getTablaVentasCliente().getSelectedRowCount() == 1) {
                Integer resp = JOptionPane.showConfirmDialog(clientesGUI, "¿Desea borrar la venta seleccionada? Esta operacion repondra stock de los articulos vendidos y eliminara todos sus pagos y cuotas.", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    int row = clientesGUI.getTablaVentasCliente().getSelectedRow();
                    abrirBase();
                    Venta v = Venta.first("id = ?", clientesGUI.getTablaVentaClientesDefault().getValueAt(row, 0));
                    Base.close();
                    if (abmVentas.Eliminar(v)) {
                        clientesGUI.getTablaVentasCliente().clearSelection();
                        JOptionPane.showMessageDialog(clientesGUI, "Venta eliminada exitosamente!", "Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);
                        CargarVentasClienteSeleccionado();
                        clientesGUI.getTablaCuotasVentasClientesDefault().setRowCount(0);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(clientesGUI, "Debe seleccionar una venta!", "Atencion!", JOptionPane.WARNING_MESSAGE);
            }
        }
        //////////////Fin botones ventas/////////////

        //////////////Botones Cuotas/////////////////
        if (e.getSource().equals(clientesGUI.getBtnPagarCuota())) {
            Integer resp = JOptionPane.showConfirmDialog(clientesGUI, "¿Esta seguro que decea pagar la cuota?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                int row = clientesGUI.getTableCuotasVentaCliente().getSelectedRow();
                if (row != -1) {
                    if (String.valueOf(clientesGUI.getTablaCuotasVentasClientesDefault().getValueAt(row, 2)).equals("IMPAGO")) {
                        if (PagarCuota(row)) {
                            JOptionPane.showMessageDialog(clientesGUI, "Cuota pagada exitosamente!");
                            tablaVentaClienteMouseClicked(null);
                        } else {
                            JOptionPane.showMessageDialog(clientesGUI, "Error, no se pudo ejecutar la operacion.", "Error!", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(clientesGUI, "Esta cuota ya fue pagada.", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(clientesGUI, "Debe seleccionar una cuota!", "Atencion!", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        if (e.getSource().equals(clientesGUI.getBtnCrearNuevoPago())) {
            if (clientesGUI.getTablaVentasCliente().getSelectedRowCount() == 1) {
                nuevoPagoGUI.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(clientesGUI, "Debe seleccionar una venta a la cual crear la cuota!", "Atencion!", JOptionPane.WARNING_MESSAGE);
            }
        }
        ////////////// Fin botones cuotas//////////////////////////////

        ////////////// Botones crear nueva cuota///////////////////////
        if (e.getSource().equals(nuevoPagoGUI.getBtnCancelarCrearCuota())) {
            nuevoPagoGUI.setVisible(false);
            nuevoPagoGUI.getTxtMonto().setText("0.00");
            nuevoPagoGUI.getTxtCalendario().setDate(Calendar.getInstance().getTime());
        }
        if (e.getSource().equals(nuevoPagoGUI.getBtnCrearCuota())) {
            if(FormatoOK()){
            Integer resp = JOptionPane.showConfirmDialog(clientesGUI, "¿Esta seguro que decea crear una nueva cuota?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                
                if (abmCobros.Alta(ObtenerDatosCobro())) {
                    JOptionPane.showMessageDialog(clientesGUI, "Cuota creada exitosamente!");
                    tablaVentaClienteMouseClicked(null);
                } else {
                    JOptionPane.showMessageDialog(clientesGUI, "Ocurrio un error al intentar crear la cuota.", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
            }
        }
    }

    private boolean FormatoOK() {
        try {
            Double monto = Double.valueOf(nuevoPagoGUI.getTxtMonto().getText());
        } catch (NumberFormatException | ClassCastException e) {
            JOptionPane.showMessageDialog(clientesGUI, "Error en el monto de la cuota. Solo se admiten numeros. Los decimales se escriben despues de un . (punto)", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private Cobro ObtenerDatosCobro() {
        Cobro c = new Cobro();
       // c.setString("estado", "IMPAGO");
        c.setDate("fecha", dateToMySQLDate(nuevoPagoGUI.getTxtCalendario().getDate(), false));
        c.setBigDecimal("monto", nuevoPagoGUI.getTxtMonto().getText());
        int row = clientesGUI.getTablaVentasCliente().getSelectedRow();
        c.set("venta_id", clientesGUI.getTablaVentaClientesDefault().getValueAt(row, 0));
        return c;
    }

    private void ObtenerDetallesVenta(int row) {
        abrirBase();
        int idVenta = (int) clientesGUI.getTablaVentaClientesDefault().getValueAt(row, 0);
        Venta v = Venta.first("id = ?", idVenta);
        detallesVentaGUI.getLblNombreCliente().setText(clientesGUI.getApellidoTxt().getText() + ", " + clientesGUI.getNombreTxt().getText());
        detallesVentaGUI.getLblFecha().setText(dateToMySQLDate(v.getDate("fecha"), true));
        detallesVentaGUI.getLblFormaPago().setText(v.getString("forma_pago"));
        detallesVentaGUI.getLblMonto().setText(v.getBigDecimal("monto").setScale(2, RoundingMode.CEILING).toString());
        LazyList<Cobro> listaCobros = Cobro.where("venta_id = ?", idVenta);
        BigDecimal montoPagado = new BigDecimal(0);
        for (Cobro c : listaCobros) {
            if (c.getDate("fecha_pago") != null) {
                montoPagado = montoPagado.add(c.getBigDecimal("monto"));
            }
        }
        detallesVentaGUI.getLblMontoPagado().setText(montoPagado.setScale(2, RoundingMode.CEILING).toString());
        BigDecimal adeuda = v.getBigDecimal("monto").subtract(montoPagado);
        detallesVentaGUI.getLblAdeuda().setText(adeuda.setScale(2, RoundingMode.CEILING).toString());
        LazyList<ArticulosVentas> listaArticulosVentas = ArticulosVentas.where("venta_id = ?", idVenta);
        detallesVentaGUI.getTablaProductosVendidosDefault().setRowCount(0);
        for (ArticulosVentas av : listaArticulosVentas) {
            Object[] fila = new Object[5];
            fila[0] = av.get("articulo_id");
            fila[2] = av.getBigDecimal("cantidad");
            fila[3] = av.getBigDecimal("precio_final").setScale(2, RoundingMode.CEILING).toString();
            fila[4] = av.getBigDecimal("cantidad").multiply(av.getBigDecimal("precio_final"));
            Articulo articulo = Articulo.first("id = ?", av.get("articulo_id"));
            if ("CAMARA".equals(articulo.getString("tipo"))) {
                fila[1] = articulo.getString("tipo") + " " + articulo.getString("marca") + " " + articulo.getString("medida");
            } else {
                fila[1] = articulo.getString("tipo") + " " + articulo.getString("marca") + " " + articulo.getString("disenio") + " " + articulo.getString("medida");
            }
            detallesVentaGUI.getTablaProductosVendidosDefault().addRow(fila);
        }
    }

}
