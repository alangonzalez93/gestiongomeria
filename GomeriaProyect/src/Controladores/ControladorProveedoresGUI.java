/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ABMs.ABMCompras;
import ABMs.ABMProveedores;
import ABMs.ABMPagos;
import Interfaz.AplicacionGUI;
import Interfaz.DetalleVentaGUI;
import Interfaz.NuevoCobroGUI;
import Interfaz.ProveedoresGUI;
import Modelos.Articulo;
import Modelos.ArticulosCompras;
import Modelos.Compra;
import Modelos.Pago;
import Modelos.Proveedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;

/**
 *
 * @author alan
 */
public class ControladorProveedoresGUI implements ActionListener {

    ProveedoresGUI proveedoresGUI;
    ABMProveedores abmProveedores;
    ABMPagos abmPagos;
    ABMCompras abmCompras;
    //Busqueda busquedaProveedors;
    boolean apreteModificar = false;
    NuevoCobroGUI nuevoPagoGUI;
    DetalleVentaGUI detallesCompraGUI;
    AplicacionGUI aplicacionGUI;

    public ControladorProveedoresGUI(ProveedoresGUI cg, AplicacionGUI apliGUI) {
        this.proveedoresGUI = cg;
        proveedoresGUI.setActionListener(this);
        abmProveedores = new ABMProveedores();
        abmPagos = new ABMPagos();
        abmCompras = new ABMCompras();
        //busquedaProveedors = new Busqueda();
        nuevoPagoGUI = new NuevoCobroGUI(apliGUI, true);
        nuevoPagoGUI.setActionListener(this);
        aplicacionGUI = apliGUI;
        
        proveedoresGUI.getTablaProveedores().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (proveedoresGUI.getTablaProveedores().getSelectedRowCount() == 1) {
                    tablaProveedorMouseClicked(null);
                } else {
                    proveedoresGUI.getTablaComprasDefault().setRowCount(0);
                    proveedoresGUI.getTablaCuotasDefault().setRowCount(0);
                    proveedoresGUI.LimpiarCampos();
                    proveedoresGUI.EstadoInicial();
                    proveedoresGUI.getLblCompoPor().setText("0.00");
                    proveedoresGUI.getLblSaldo().setText("0.00");
                }
            }
        });
        
        //////////////////////////////////////////////////////////////////////
        ///////Actualizar datos cuando cambie de seleccion///////////////////
        proveedoresGUI.getTablaComprasProveedor().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (proveedoresGUI.getTablaComprasProveedor().getSelectedRowCount() == 1) {
                    proveedoresGUI.getBtnCrearNuevoPago().setEnabled(true);
                    proveedoresGUI.getBtnEliminarCompra().setEnabled(true);
                    proveedoresGUI.getBtnModificarCompra().setEnabled(true);
                    proveedoresGUI.getBtnDetalles().setEnabled(true);
                    tablaCompraProveedorMouseClicked(null);
                } else {
                    proveedoresGUI.getBtnCrearNuevoPago().setEnabled(false);
                    proveedoresGUI.getBtnEliminarCompra().setEnabled(false);
                    proveedoresGUI.getBtnModificarCompra().setEnabled(false);
                    proveedoresGUI.getBtnDetalles().setEnabled(false);
                }
            }
        });
        
        ///////////////////////////////////////////////////////////////////
        ///////Actualizar datos cuando cambie de seleccion///////////////////
        proveedoresGUI.getTableCuotasCompra().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (proveedoresGUI.getTableCuotasCompra().getSelectedRowCount() == 1) {
                    proveedoresGUI.getBtnPagarCuota().setEnabled(false);
                    proveedoresGUI.getBtnEliminarPago().setEnabled(true);
                    proveedoresGUI.getBtnModificarPago().setEnabled(true);
                    int r = proveedoresGUI.getTableCuotasCompra().getSelectedRow();
                    if (String.valueOf(proveedoresGUI.getTablaCuotasDefault().getValueAt(r, 3)).equals("IMPAGO")) {
                        proveedoresGUI.getBtnPagarCuota().setEnabled(true);
                    }
                } else {
                    proveedoresGUI.getBtnEliminarPago().setEnabled(false);
                    proveedoresGUI.getBtnModificarPago().setEnabled(false);
                    proveedoresGUI.getBtnPagarCuota().setEnabled(false);
                }
            }
        });
        ///////////////////////////////////////////////////////////////////
       /* proveedoresGUI.getBusquedaProveedorsTxt().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaKeyReleased(evt);
            }
        });*/

    }

    private void tablaCompraProveedorMouseClicked(MouseEvent evt) {
        abrirBase();
        proveedoresGUI.getTablaCuotasDefault().setRowCount(0);
        int r = proveedoresGUI.getTablaComprasProveedor().getSelectedRow();
        LazyList<Pago> listPagos = Pago.where("venta_id = ?", proveedoresGUI.getTablaComprasDefault().getValueAt(r, 0));
        for (Pago c : listPagos) {
            Object[] row = new Object[5];
            row[0] = c.get("id");
            row[1] = dateToMySQLDate(c.getDate("fecha"), true);
            if (c.getDate("fecha_pago") != null) {
                row[2] = dateToMySQLDate(c.getDate("fecha_pago"), true);
            }
            row[3] = c.getString("estado");
            row[4] = c.getBigDecimal("monto").setScale(2, RoundingMode.CEILING).toString();
            proveedoresGUI.getTablaCuotasDefault().addRow(row);
        }
        Base.close();
    }

    private void tablaProveedorMouseClicked(MouseEvent evt) {
        CargarDatosProveedorSeleccionado();
        CargarComprasProveedorSeleccionado();
        proveedoresGUI.getTablaCuotasDefault().setRowCount(0);
        proveedoresGUI.EstadoProveedorSeleccionado(); //cambio vista
    }

   /* private void busquedaKeyReleased(KeyEvent evt) {
        abrirBase();
        List<Proveedor> listaProveedors = busquedaProveedors.BuscarProveedors(proveedoresGUI.getBusquedaProveedorsTxt().getText(), String.valueOf(proveedoresGUI.getBuscarBox().getSelectedItem()));
        proveedoresGUI.getTablaProveedorsDefault().setRowCount(0);
        proveedoresGUI.getEncontradosLbl().setText(String.valueOf(listaProveedors.size()));
        for (Proveedor proveedor : listaProveedors) {
            Object row[] = new String[3];
            row[0] = proveedor.getString("id");
            row[1] = proveedor.getString("nombre");
            row[2] = proveedor.getString("ciudad");
            proveedoresGUI.getTablaProveedorsDefault().addRow(row);
        }
        Base.close();
    }*/

    //Trae de la base de datos todos los datos del proveedor seleccionado en la tabla y los pone en la interfaz
    public void CargarDatosProveedorSeleccionado() {
        int r = proveedoresGUI.getTablaProveedores().getSelectedRow();
        String idProveedor = (String) proveedoresGUI.getTablaProveedoresDefault().getValueAt(r, 0);
        abrirBase();
        Proveedor proveedor = Proveedor.first("id = ?", idProveedor);
        if (proveedor != null) {
            proveedoresGUI.getNombreTxt().setText(proveedor.getString("nombre"));
            proveedoresGUI.getIdTxt().setText(proveedor.getString("id"));
            proveedoresGUI.getTelTxt().setText(proveedor.getString("telefono"));
            proveedoresGUI.getCelularTxt().setText(proveedor.getString("celular"));
            proveedoresGUI.getDireccionTxt().setText(proveedor.getString("direccion"));
            proveedoresGUI.getCiudadBox().setSelectedItem(proveedor.getString("ciudad"));
            proveedoresGUI.getMailTxt().setText(proveedor.getString("email"));
        } else {
            JOptionPane.showMessageDialog(proveedoresGUI, "Ocurrio un error inesperado, intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        Base.close();
    }

    public void CargarComprasProveedorSeleccionado() {
        abrirBase();
        proveedoresGUI.getTablaComprasDefault().setRowCount(0);
        BigDecimal totalCompras = new BigDecimal(0);
        BigDecimal totalPago = new BigDecimal(0);
        LazyList<Compra> ventasProveedor = Compra.where("proveedor_id = ?", proveedoresGUI.getIdTxt().getText());
        for (Compra v : ventasProveedor) {
            Object[] row = new Object[4];
            row[0] = v.get("id");
            row[1] = dateToMySQLDate(v.getDate("fecha"), true);
            row[2] = v.getBigDecimal("monto").setScale(2, RoundingMode.CEILING).toString();
            row[3] = v.getString("forma_pago");
            proveedoresGUI.getTablaComprasDefault().addRow(row);
            totalCompras = totalCompras.add(v.getBigDecimal("monto"));

            LazyList<Pago> listaPagos = Pago.where("venta_id = ?", v.get("id"));
            for (Pago c : listaPagos) {
                if (c.getDate("fecha_pago") != null) {
                    totalPago = totalPago.add(c.getBigDecimal("monto"));
                }
            }
        }

        proveedoresGUI.getLblCompoPor().setText(totalCompras.setScale(2, RoundingMode.CEILING).toString());
        proveedoresGUI.getLblSaldo().setText(totalCompras.subtract(totalPago).setScale(2, RoundingMode.CEILING).toString());
        Base.close();
    }

    //Retorna un proveedor con todos los datos traidos de los campos de texto de la interfaz
    public Proveedor ObtenerDatosProveedor(String id) {
        abrirBase();
        Proveedor proveedor;
        if (id == null) {
            proveedor = new Proveedor();
        } else {
            
            proveedor = Proveedor.first("id = ?", proveedoresGUI.getIdTxt().getText());
           
        }
        proveedor.set("nombre", proveedoresGUI.getNombreTxt().getText());
        proveedor.set("telefono", proveedoresGUI.getTelTxt().getText());
        proveedor.set("celular", proveedoresGUI.getCelularTxt().getText());
        proveedor.set("direccion", proveedoresGUI.getDireccionTxt().getText().toUpperCase());
        proveedor.set("ciudad", proveedoresGUI.getCiudadBox().getSelectedItem());
        proveedor.set("email", proveedoresGUI.getMailTxt().getText());
         Base.close();
        return proveedor;
    }

    private void ActualizarLista() {
        abrirBase();
        proveedoresGUI.getTablaProveedoresDefault().setRowCount(0);
        LazyList<Proveedor> listaProveedors = Proveedor.findAll();
        proveedoresGUI.getEncontradosLbl().setText(String.valueOf(listaProveedors.size()));
        for (Proveedor proveedor : listaProveedors) {
            Object row[] = new String[3];
            row[0] = proveedor.getString("id");
            row[1] = /*proveedor.getString("apellido")+", "+*/ proveedor.getString("nombre");
            row[2] = proveedor.getString("ciudad");
            proveedoresGUI.getTablaProveedoresDefault().addRow(row);
        }
        Base.close();
    }

    public boolean DatosObligatoriosOK() {
        if ((!proveedoresGUI.getNombreTxt().getText().equals(""))
                && ((!proveedoresGUI.getTelTxt().getText().equals(""))
                || (!proveedoresGUI.getCelularTxt().getText().equals("")))) {
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
        Pago cobro = Pago.first("id = ?", proveedoresGUI.getTablaCuotasDefault().getValueAt(row, 0));
        cobro.set("estado", "PAGO");
        cobro.setDate("fecha_pago", dateToMySQLDate(Calendar.getInstance().getTime(), false));
        result = result && cobro.saveIt();
        Base.commitTransaction();
        Base.close();
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == proveedoresGUI.getBtnNuevo()) {
            if (proveedoresGUI.getBtnNuevo().getText().equals("Nuevo")) { //Si el nombre del btn es "nuevo" entonces solo habilito los campos
                proveedoresGUI.ApreteBtnNuevoModificar();
                proveedoresGUI.LimpiarCampos();
            } else {
                if (proveedoresGUI.getBtnNuevo().getText().equals("Guardar") && !apreteModificar) {
                    if (DatosObligatoriosOK()) {
                        if (abmProveedores.Alta(ObtenerDatosProveedor(null))) {
                            JOptionPane.showMessageDialog(proveedoresGUI, "Proveedor registrado exitosamente!");
                            proveedoresGUI.LimpiarCampos();
                            proveedoresGUI.EstadoInicial();
                            ActualizarLista();
                        } else {
                            JOptionPane.showMessageDialog(proveedoresGUI, "Ocurrio un error, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(proveedoresGUI, "Los campos: Nombre y Telefono (fijo o celular) son abligatorios.", "Atencion!", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    if (proveedoresGUI.getBtnNuevo().getText().equals("Guardar") && apreteModificar) {
                        if (DatosObligatoriosOK()) {
                            if (abmProveedores.Modificar(ObtenerDatosProveedor(proveedoresGUI.getIdTxt().getText()))) {
                                JOptionPane.showMessageDialog(proveedoresGUI, "Proveedor modificado exitosamente!");
                                proveedoresGUI.EstadoLuegoDeModificar();
                                ActualizarLista();
                                apreteModificar = false;
                            } else {
                                JOptionPane.showMessageDialog(proveedoresGUI, "Ocurrio un error, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(proveedoresGUI, "Los campos: Nombre, Apellido, Telefono (fijo o celular) y Direccion son abligatorios.", "Atencion!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        }
        if (e.getSource() == proveedoresGUI.getBtnModificar()) {
            apreteModificar = true;
            proveedoresGUI.ApreteBtnNuevoModificar();
        }
        if (e.getSource() == proveedoresGUI.getBtnEliminar()) {
            if (proveedoresGUI.getBtnEliminar().getText().equals("Eliminar")) {
                Integer resp = JOptionPane.showConfirmDialog(proveedoresGUI, "¿Desea borrar el proveedor " + proveedoresGUI.getNombreTxt().getText() + "?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    if (abmProveedores.Eliminar(ObtenerDatosProveedor(proveedoresGUI.getIdTxt().getText()))) {
                        JOptionPane.showMessageDialog(proveedoresGUI, "Proveedor eliminado correctamente!");
                        proveedoresGUI.EstadoInicial();
                        proveedoresGUI.LimpiarCampos();
                        ActualizarLista();
                    } else {
                        JOptionPane.showMessageDialog(proveedoresGUI, "Ocurrio un error, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                if (proveedoresGUI.getBtnEliminar().getText().equals("Cancelar")) {
                    proveedoresGUI.EstadoInicial();
                    proveedoresGUI.LimpiarCampos();
                }
            }
        }
        //////////////Botones ventas//////////////////
        if (e.getSource().equals(proveedoresGUI.getBtnDetalles())) {
            if (proveedoresGUI.getTablaComprasProveedor().getSelectedRowCount() == 1) {
                int r = proveedoresGUI.getTablaComprasProveedor().getSelectedRow();
                detallesCompraGUI = new DetalleVentaGUI(aplicacionGUI, true);
                ObtenerDetallesCompra(r);
                detallesCompraGUI.setLocationRelativeTo(null);
                detallesCompraGUI.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(proveedoresGUI, "Debe seleccionar una venta!", "Atencion!", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource().equals(proveedoresGUI.getBtnEliminarCompra())) {
            if (proveedoresGUI.getTablaComprasProveedor().getSelectedRowCount() == 1) {
                Integer resp = JOptionPane.showConfirmDialog(proveedoresGUI, "¿Desea borrar la venta seleccionada? Esta operacion repondra stock de los articulos vendidos y eliminara todos sus pagos y cuotas.", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    int row = proveedoresGUI.getTablaComprasProveedor().getSelectedRow();
                    abrirBase();
                    Compra v = Compra.first("id = ?", proveedoresGUI.getTablaComprasDefault().getValueAt(row, 0));
                    Base.close();
                    if (abmCompras.Eliminar(v)) {
                        proveedoresGUI.getTablaComprasProveedor().clearSelection();
                        JOptionPane.showMessageDialog(proveedoresGUI, "Compra eliminada exitosamente!", "Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);
                        CargarComprasProveedorSeleccionado();
                        proveedoresGUI.getTablaCuotasDefault().setRowCount(0);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(proveedoresGUI, "Debe seleccionar una venta!", "Atencion!", JOptionPane.WARNING_MESSAGE);
            }
        }
        //////////////Fin botones ventas/////////////

        //////////////Botones Cuotas/////////////////
        if (e.getSource().equals(proveedoresGUI.getBtnPagarCuota())) {
            Integer resp = JOptionPane.showConfirmDialog(proveedoresGUI, "¿Esta seguro que decea pagar la cuota?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                int row = proveedoresGUI.getTableCuotasCompra().getSelectedRow();
                if (row != -1) {
                    if (String.valueOf(proveedoresGUI.getTablaCuotasDefault().getValueAt(row, 3)).equals("IMPAGO")) {
                        if (PagarCuota(row)) {
                            JOptionPane.showMessageDialog(proveedoresGUI, "Cuota pagada exitosamente!");
                            tablaCompraProveedorMouseClicked(null);
                        } else {
                            JOptionPane.showMessageDialog(proveedoresGUI, "Error, no se pudo ejecutar la operacion.", "Error!", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(proveedoresGUI, "Esta cuota ya fue pagada.", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(proveedoresGUI, "Debe seleccionar una cuota!", "Atencion!", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        if (e.getSource().equals(proveedoresGUI.getBtnCrearNuevoPago())) {
            if (proveedoresGUI.getTablaComprasProveedor().getSelectedRowCount() == 1) {
                nuevoPagoGUI.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(proveedoresGUI, "Debe seleccionar una venta a la cual crear la cuota!", "Atencion!", JOptionPane.WARNING_MESSAGE);
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
            Integer resp = JOptionPane.showConfirmDialog(proveedoresGUI, "¿Esta seguro que decea crear una nueva cuota?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                
                if (abmPagos.Alta(ObtenerDatosPago())) {
                    JOptionPane.showMessageDialog(proveedoresGUI, "Cuota creada exitosamente!");
                    tablaCompraProveedorMouseClicked(null);
                } else {
                    JOptionPane.showMessageDialog(proveedoresGUI, "Ocurrio un error al intentar crear la cuota.", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
            }
        }
    }

    private boolean FormatoOK() {
        try {
            Double monto = Double.valueOf(nuevoPagoGUI.getTxtMonto().getText());
        } catch (NumberFormatException | ClassCastException e) {
            JOptionPane.showMessageDialog(proveedoresGUI, "Error en el monto de la cuota. Solo se admiten numeros. Los decimales se escriben despues de un . (punto)", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private Pago ObtenerDatosPago() {
        Pago c = new Pago();
        c.setString("estado", "IMPAGO");
        c.setDate("fecha", dateToMySQLDate(nuevoPagoGUI.getTxtCalendario().getDate(), false));
        c.setBigDecimal("monto", nuevoPagoGUI.getTxtMonto().getText());
        int row = proveedoresGUI.getTablaComprasProveedor().getSelectedRow();
        c.set("venta_id", proveedoresGUI.getTablaComprasDefault().getValueAt(row, 0));
        return c;
    }

    private void ObtenerDetallesCompra(int row) {
        abrirBase();
        int idCompra = (int) proveedoresGUI.getTablaComprasDefault().getValueAt(row, 0);
        Compra v = Compra.first("id = ?", idCompra);
        detallesCompraGUI.getLblNombreCliente().setText(proveedoresGUI.getNombreTxt().getText());
        detallesCompraGUI.getLblFecha().setText(dateToMySQLDate(v.getDate("fecha"), true));
        detallesCompraGUI.getLblFormaPago().setText(v.getString("forma_pago"));
        detallesCompraGUI.getLblMonto().setText(v.getBigDecimal("monto").setScale(2, RoundingMode.CEILING).toString());
        LazyList<Pago> listaPagos = Pago.where("venta_id = ?", idCompra);
        BigDecimal montoPagado = new BigDecimal(0);
        for (Pago c : listaPagos) {
            if (c.getString("estado").equals("PAGO")) {
                montoPagado = montoPagado.add(c.getBigDecimal("monto"));
            }
        }
        detallesCompraGUI.getLblMontoPagado().setText(montoPagado.setScale(2, RoundingMode.CEILING).toString());
        BigDecimal adeuda = v.getBigDecimal("monto").subtract(montoPagado);
        detallesCompraGUI.getLblAdeuda().setText(adeuda.setScale(2, RoundingMode.CEILING).toString());
        LazyList<ArticulosCompras> listaArticulosCompras = ArticulosCompras.where("venta_id = ?", idCompra);
        detallesCompraGUI.getTablaProductosVendidosDefault().setRowCount(0);
        for (ArticulosCompras av : listaArticulosCompras) {
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
            detallesCompraGUI.getTablaProductosVendidosDefault().addRow(fila);
        }
    }
    
}
