/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ABMs.ABMCompras;
import ABMs.ABMPagos;
import Interfaz.AplicacionGUI;
import Interfaz.CargarCompraGUI;
import Modelos.Articulo;
import Modelos.Compra;
import gomeriaproyect.Triple;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import org.javalite.activejdbc.Base;

/**
 *
 * @author alan
 */
public class ControladorCargarCompraGUI implements ActionListener, CellEditorListener {
    
    CargarCompraGUI cargarCompraGUI;
    //Busqueda busqueda;
    ABMCompras abmCompras;
    ABMPagos abmPago;
    //CargarPagosGUI cargarPagosGUI;
    AplicacionGUI aplicacionGUI;

    public ControladorCargarCompraGUI(CargarCompraGUI cv, AplicacionGUI ap) {
        aplicacionGUI = ap;
        cargarCompraGUI = cv;
        cargarCompraGUI.setActionListener(this);
        //busqueda = new Busqueda();
        abmCompras = new ABMCompras();

        ///////////////////Cobros//////////////////
        abmPago = new ABMPagos();
      /*  cargarPagosGUI = new CargarPagosGUI(aplicacionGUI, true);
        cargarPagosGUI.setActionListener(this);

        cargarPagosGUI.getCuotasTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (cargarPagosGUI.getCuotasTable().getSelectedRowCount() == 1) {
                    int row = cargarPagosGUI.getCuotasTable().getSelectedRow();
                    if (String.valueOf(cargarPagosGUI.getCuotasTableDefault().getValueAt(row, 3)).equals("IMPAGO")) {
                        cargarPagosGUI.getBtnPagarCuota().setEnabled(true);
                    } else {
                        cargarPagosGUI.getBtnPagarCuota().setEnabled(false);
                    }
                } else {
                    cargarPagosGUI.getBtnPagarCuota().setEnabled(false);
                }
            }
        });
        ///////////////////FIN COBROS///////////////////
        */
        cargarCompraGUI.getFormaPagoBox().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String item = (String) cargarCompraGUI.getFormaPagoBox().getSelectedItem();
                cargarCompraGUI.getNroChequeTxt().setVisible(false);
                cargarCompraGUI.getNroChequeLbl().setVisible(false);
                if (cargarCompraGUI.getFormaPagoBox().getSelectedItem() == "CHEQUE") {
                    cargarCompraGUI.getNroChequeTxt().setVisible(true);
                    cargarCompraGUI.getNroChequeLbl().setVisible(true);
                }
            }
        });
      /*  cargarCompraGUI.getBusquedaNombreTxt().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaKeyReleased(evt);
            }
        });*/

        cargarCompraGUI.getTablaProveedores().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProveedorMouseClicked(evt);
            }
        });

        cargarCompraGUI.getTablaArticulos().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaArticulosMouseClicked(evt);
            }
        });
    }

   /* private void busquedaKeyReleased(KeyEvent evt) {
        abrirBase();
        List<Proveedor> listaProveedors = busqueda.BuscarProveedors(cargarCompraGUI.getBusquedaNombreTxt().getText(), "NOMBRE");
        cargarCompraGUI.getTablaProveedorsDefault().setRowCount(0);
        for (Proveedor cliente : listaProveedors) {
            Object row[] = new String[3];
            row[0] = cliente.getString("id");
            row[1] = cliente.getString("nombre");
            row[2] = cliente.getString("direccion") + " - " + cliente.getString("ciudad");
            cargarCompraGUI.getTablaProveedorsDefault().addRow(row);
        }
        Base.close();
    }*/

    private void tablaProveedorMouseClicked(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            int r = cargarCompraGUI.getTablaProveedores().getSelectedRow();
            cargarCompraGUI.getNombreProveedorTxt().setText(String.valueOf(cargarCompraGUI.getTablaProveedoresDefault().getValueAt(r, 1)));
            cargarCompraGUI.getIdProveedorTxt().setText(String.valueOf(cargarCompraGUI.getTablaProveedoresDefault().getValueAt(r, 0)));
        }
    }

    private void tablaArticulosMouseClicked(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
        int r = cargarCompraGUI.getTablaArticulos().getSelectedRow();
            int lineaArticulo = articuloYaCargado(String.valueOf(cargarCompraGUI.getTablaArticulosDefault().getValueAt(r, 0)));
            // si el articulo no estaba en el carrito se agrega el articulo si no se le suma uno a la cantidad
            if (lineaArticulo == -1) {
                abrirBase();
                Articulo articulo = Articulo.first("id = ?", String.valueOf(cargarCompraGUI.getTablaArticulosDefault().getValueAt(r, 0)));
                Object row[] = new Object[6];
                row[0] = articulo.getString("id");
                if ("CAMARA".equals(articulo.getString("tipo"))) {
                    row[1] = articulo.getString("tipo") + " " + articulo.getString("marca") + " " + articulo.getString("medida");
                } else {
                    row[1] = articulo.getString("tipo") + " " + articulo.getString("marca") + " " + articulo.getString("disenio") + " " + articulo.getString("medida");
                }
                row[2] = BigDecimal.valueOf(1.00);
                row[3] = articulo.getBigDecimal("precio_venta").setScale(2, RoundingMode.CEILING).toString();
                row[4] = articulo.getBigDecimal("precio_venta").setScale(2, RoundingMode.CEILING).toString();
                cargarCompraGUI.getTablaCompraDefault().addRow(row);
                Base.close();
            } else {
                // Lo que se hace dentro de este else es sumar en uno a la cantidad del articulo si ya estaba en el carrito.
                Double viejaCantidad = new Double(String.valueOf(cargarCompraGUI.getTablaCompraDefault().getValueAt(lineaArticulo, 2)));
                BigDecimal viejaCantidadBD = BigDecimal.valueOf(viejaCantidad);
                BigDecimal uno = new BigDecimal(1);
                BigDecimal nuevaCantidad = viejaCantidadBD.add(uno);
                cargarCompraGUI.getTablaCompraDefault().setValueAt(nuevaCantidad, lineaArticulo, 2);
            }
            setCellEditor();
            actualizarMonto();
        }
    }
    
    private int articuloYaCargado(String id) {
        for (int i = 0; i < cargarCompraGUI.getTablaCompra().getRowCount(); i++) {
            if (String.valueOf(cargarCompraGUI.getTablaCompraDefault().getValueAt(i, 0)).equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public Compra ObtenerDatosCompra() {
        abrirBase();
        LinkedList<Triple> listaArticulos = new LinkedList();
        for (int i = 0; i < cargarCompraGUI.getTablaCompraDefault().getRowCount(); i++) {
            Object idArticulo = cargarCompraGUI.getTablaCompraDefault().getValueAt(i, 0);
            Double doubleCantidad = Double.valueOf(String.valueOf(cargarCompraGUI.getTablaCompraDefault().getValueAt(i, 2)));
            BigDecimal cantidad = BigDecimal.valueOf(doubleCantidad);
            Double doublePrecioFinal = Double.valueOf(String.valueOf(cargarCompraGUI.getTablaCompraDefault().getValueAt(i, 3)));
            BigDecimal precioFinal = BigDecimal.valueOf(doublePrecioFinal);
            listaArticulos.add(new Triple(idArticulo, cantidad, precioFinal));
        }
        Compra v = new Compra(listaArticulos);
        v.set("proveedor_id", cargarCompraGUI.getIdProveedorTxt().getText());
        v.set("fecha", dateToMySQLDate(cargarCompraGUI.getCalendarioTxt().getDate(), false));
        v.setBigDecimal("monto", cargarCompraGUI.getTotalTxt().getText());
        v.set("descripcion", cargarCompraGUI.getDescripcionArea().getText());
        Base.close();
        return v;
    }

    public boolean DatosOK() {
        if ((cargarCompraGUI.getNombreProveedorTxt().getText().equals("")) || (cargarCompraGUI.getTablaCompra().getRowCount() == 0)) {
            return false;
        }
        return true;
    }

    public void actualizarMonto() {
        BigDecimal importe;
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < cargarCompraGUI.getTablaCompra().getRowCount(); i++) {
            BigDecimal precio_unit = new BigDecimal(String.valueOf(cargarCompraGUI.getTablaCompra().getValueAt(i, 3)));
            importe = ((BigDecimal) cargarCompraGUI.getTablaCompra().getValueAt(i, 2)).multiply(precio_unit).setScale(2, RoundingMode.CEILING);
            cargarCompraGUI.getTablaCompraDefault().setValueAt(importe, i, 4);
            total = total.add((BigDecimal) cargarCompraGUI.getTablaCompraDefault().getValueAt(i, 4)).setScale(2, RoundingMode.CEILING);
        }
        cargarCompraGUI.getTotalTxt().setText(total.toString());

    }

    public void abrirBase() {
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/gomeria", "root", "root");
        }
    }

    public void setCellEditor() {
        for (int i = 0; i < cargarCompraGUI.getTablaCompra().getRowCount(); i++) {
            cargarCompraGUI.getTablaCompra().getCellEditor(i, 2).addCellEditorListener(this);
            cargarCompraGUI.getTablaCompra().getCellEditor(i, 3).addCellEditorListener(this);
        }
    }

    /*private void VerCargarCuotasGUI() {
        cargarPagosGUI.getCuotasTableDefault().setRowCount(0);
        cargarPagosGUI.getLblNombre().setText(cargarCompraGUI.getNombreProveedorTxt().getText());
        cargarPagosGUI.getLblFormaDePago().setText((String) cargarCompraGUI.getFormaPagoBox().getSelectedItem());
        cargarPagosGUI.setVisible(true);
    }

    private boolean PagarCuota(int row) {
        boolean result = true;
        abrirBase();
        Base.openTransaction();
        Cobro cobro = Cobro.first("id = ?", cargarPagosGUI.getCuotasTableDefault().getValueAt(row, 0));
        cobro.set("estado", "PAGO");
        cobro.setDate("fecha_pago", dateToMySQLDate(Calendar.getInstance().getTime(), false));
        result = result && cobro.saveIt();
        Base.commitTransaction();
        Base.close();
        return result;

    }

    private Cobro ObtenerDatosCobro() {
        Cobro c = new Cobro();
        c.setString("estado", "IMPAGO");
        c.setDate("fecha", dateToMySQLDate(cargarPagosGUI.getTxtCalendario().getDate(), false));
        c.setBigDecimal("monto", cargarPagosGUI.getTxtMonto().getText());
        c.set("venta_id", abmCompras.getIdCompra());
        return c;
    }

    private void CargarCobros() {
        abrirBase();
        cargarPagosGUI.getCuotasTableDefault().setRowCount(0);
        LazyList<Cobro> listCobros = Cobro.where("venta_id = ?", abmCompras.getIdCompra());
        for (Cobro c : listCobros) {
            Object[] row = new Object[5];
            row[0] = c.get("id");
            row[1] = dateToMySQLDate(c.getDate("fecha"), true);
            if (c.getDate("fecha_pago") != null) {
                row[2] = dateToMySQLDate(c.getDate("fecha_pago"), true);
            }
            row[3] = c.getString("estado");
            row[4] = c.getBigDecimal("monto").setScale(2, RoundingMode.CEILING).toString();
            cargarPagosGUI.getCuotasTableDefault().addRow(row);
        }
        Base.close();

    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cargarCompraGUI.getQuitarBtn())) {
            if (cargarCompraGUI.getTablaCompra().getSelectedRow() != -1) {//-1 retorna getSelectedRow si no hay fila seleccionada
                cargarCompraGUI.getTablaCompraDefault().removeRow(cargarCompraGUI.getTablaCompra().getSelectedRow());
                actualizarMonto();
            }
        }
        if (e.getSource().equals(cargarCompraGUI.getRegistrarCompraBtn())) {
            if (DatosOK()) {
                if (abmCompras.Alta(ObtenerDatosCompra())) {
                    JOptionPane.showMessageDialog(cargarCompraGUI, "Compra registrada exitosamente!");
                   // VerCargarCuotasGUI();
                } else {
                    JOptionPane.showMessageDialog(cargarCompraGUI, "Ocurrio un error, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(cargarCompraGUI, "No se selecciono un cliente o la lista de productos esta vacia.", "Atencion!", JOptionPane.WARNING_MESSAGE);
            }
        }
        ///////////Controlador CargarPagosGUI//////////////////

       /* if (e.getSource().equals(cargarPagosGUI.getBtnPagarCuota())) {
            int row = cargarPagosGUI.getCuotasTable().getSelectedRow();
            Integer resp = JOptionPane.showConfirmDialog(cargarPagosGUI, "¿Esta seguro que decea pagar la cuota?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                if (row != -1) {
                    if (PagarCuota(row)) {
                        JOptionPane.showMessageDialog(cargarPagosGUI, "Cuota pagada exitosamente!");
                        CargarCobros();
                    } else {
                        JOptionPane.showMessageDialog(cargarPagosGUI, "Error, no se pudo ejecutar la operacion.", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(cargarPagosGUI, "Debe seleccionar un pago!", "Atencion!", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        if (e.getSource().equals(cargarPagosGUI.getBtnCancelar())) {
            cargarPagosGUI.setVisible(false);
            cargarCompraGUI.setVisible(false);

        }
        if (e.getSource().equals(cargarPagosGUI.getBtnCrearCuota())) {
            if (FormatoOK()) {
                Integer resp = JOptionPane.showConfirmDialog(cargarPagosGUI, "¿Esta seguro que decea crear una nueva cuota?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    if (abmPago.Alta(ObtenerDatosCobro())) {
                        JOptionPane.showMessageDialog(cargarPagosGUI, "Cuota creada exitosamente!");
                        CargarCobros();
                    } else {
                        JOptionPane.showMessageDialog(cargarPagosGUI, "Ocurrio un error al intentar crear la cuota.", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }*/

        /////////////Fin Controlador CargarPagosGUI/////////////////
    }
/*
    private boolean FormatoOK() {
        try {
            Double monto = Double.valueOf(cargarPagosGUI.getTxtMonto().getText());
        } catch (NumberFormatException | ClassCastException e) {
            JOptionPane.showMessageDialog(cargarPagosGUI, "Error en el monto de la cuota. Solo se admiten numeros. Los decimales se escriben despues de un . (punto)", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
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

    @Override
    public void editingStopped(ChangeEvent e) {
        actualizarMonto();
    }

    @Override
    public void editingCanceled(ChangeEvent e) {
    }

    
}
