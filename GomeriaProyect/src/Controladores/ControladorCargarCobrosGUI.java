/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ABMs.ABMCobros;
import ABMs.ABMVentas;
import Interfaz.CargarCobrosGUI;
import Interfaz.CargarVentaGUI;
import Modelos.Cobro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.sf.jasperreports.engine.JRException;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;

/**
 *
 * @author alan
 */
public class ControladorCargarCobrosGUI implements ActionListener {

    CargarCobrosGUI cargarCobrosGUI;
    ABMVentas abmVentas;
    ABMCobros abmCobro;
    CargarVentaGUI cargarVentaGUI;
    boolean apreteModificar = false;
    String idCuotaSeleccionada = null;
    private ControladorJReportGomeria reporte;

    public ControladorCargarCobrosGUI(CargarCobrosGUI ccg, ABMVentas abm, CargarVentaGUI cvg) throws JRException, ClassNotFoundException, SQLException {
        cargarCobrosGUI = ccg;
        cargarCobrosGUI.setActionListener(this);
        abmVentas = abm;
        abmCobro = new ABMCobros();
        cargarVentaGUI = cvg;
        cargarCobrosGUI.getCuotasTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (cargarCobrosGUI.getCuotasTable().getSelectedRowCount() == 1) {
                    int row = cargarCobrosGUI.getCuotasTable().getSelectedRow();
                    idCuotaSeleccionada = String.valueOf(cargarCobrosGUI.getCuotasTableDefault().getValueAt(row, 0));
                    abrirBase();

                    Cobro c = Cobro.first("id = ?", cargarCobrosGUI.getCuotasTableDefault().getValueAt(row, 0));
                    cargarCobrosGUI.getTxtCalendario().setDate(c.getDate("fecha"));
                    cargarCobrosGUI.getTxtFechaPago().setDate(c.getDate("fecha_pago"));
                    cargarCobrosGUI.getTxtMonto().setText(c.getBigDecimal("monto").setScale(2, RoundingMode.CEILING).toString());
                    cargarCobrosGUI.getBoxTipo().setSelectedItem(c.getString("tipo"));

                    Base.close();
                    ///////ImPORTANTE!
                    cargarCobrosGUI.SeleccioneElementoTabla();
                    apreteModificar = false;
                    //////////////

                    if (c.getDate("fecha_pago") == null) {
                        cargarCobrosGUI.getBtnPagarCuota().setEnabled(true);
                    } else {
                        cargarCobrosGUI.getBtnPagarCuota().setEnabled(false);
                    }
                } else {
                    cargarCobrosGUI.getBtnPagarCuota().setEnabled(false);
                    cargarCobrosGUI.ApreteBtnGuardarEliminarCancelar();
                    idCuotaSeleccionada = null;
                }
            }
        });
        reporte = new ControladorJReportGomeria("reporte.jasper");
    }

    private boolean PagarCuota(int row) {
        boolean result = true;
        abrirBase();
        Base.openTransaction();
        Cobro cobro = Cobro.first("id = ?", cargarCobrosGUI.getCuotasTableDefault().getValueAt(row, 0));
        cobro.setDate("fecha_pago", dateToMySQLDate(Calendar.getInstance().getTime(), false));
        result = result && cobro.saveIt();
        Base.commitTransaction();
        Base.close();
        return result;

    }

    private Cobro ObtenerDatosCobro(String id) {
        Cobro c = new Cobro();
        if (id != null) {
            abrirBase();
            c = Cobro.first("id = ?", id);
            Base.close();
        }
        //c.setDate("fecha_pago", dateToMySQLDate(cargarCobrosGUI.getTxtFechaPago().getDate(), false));
        c.setString("tipo", cargarCobrosGUI.getBoxTipo().getSelectedItem());
        c.setDate("fecha", dateToMySQLDate(cargarCobrosGUI.getTxtCalendario().getDate(), false));
        c.setBigDecimal("monto", cargarCobrosGUI.getTxtMonto().getText());
        c.set("venta_id", abmVentas.getIdVenta());
        return c;
    }

    private void CargarCobros() {
        abrirBase();
        cargarCobrosGUI.getCuotasTableDefault().setRowCount(0);
        LazyList<Cobro> listCobros = Cobro.where("venta_id = ?", abmVentas.getIdVenta());
        for (Cobro c : listCobros) {
            Object[] row = new Object[5];
            row[0] = c.get("id");
            row[1] = dateToMySQLDate(c.getDate("fecha"), true);
            if (c.getDate("fecha_pago") != null) {
                row[2] = dateToMySQLDate(c.getDate("fecha_pago"), true);
            }
            row[4] = c.getString("tipo");
            row[3] = c.getBigDecimal("monto").setScale(2, RoundingMode.CEILING).toString();
            cargarCobrosGUI.getCuotasTableDefault().addRow(row);
        }
        Base.close();

    }

    private boolean FormatoOK() {
        try {
            Double monto = Double.valueOf(cargarCobrosGUI.getTxtMonto().getText());
        } catch (NumberFormatException | ClassCastException e) {
            JOptionPane.showMessageDialog(cargarCobrosGUI, "Error en el monto de la cuota. Solo se admiten numeros. Los decimales se escriben despues de un . (punto)", "Error de formato", JOptionPane.ERROR_MESSAGE);
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

    public void abrirBase() {
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/gomeria", "root", "root");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cargarCobrosGUI.getBtnPagarCuota())) {
            int row = cargarCobrosGUI.getCuotasTable().getSelectedRow();
            Integer resp = JOptionPane.showConfirmDialog(cargarCobrosGUI, "¿Esta seguro que decea pagar la cuota?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                if (row != -1) {
                    if (PagarCuota(row)) {
                        JOptionPane.showMessageDialog(cargarCobrosGUI, "Cuota pagada exitosamente!");
                        CargarCobros();
                    } else {
                        JOptionPane.showMessageDialog(cargarCobrosGUI, "Error, no se pudo ejecutar la operacion.", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(cargarCobrosGUI, "Debe seleccionar un pago!", "Atencion!", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        if (e.getSource().equals(cargarCobrosGUI.getBtnCancelar())) {
            cargarCobrosGUI.setVisible(false);
            cargarVentaGUI.setVisible(false);

        }
        if(e.getSource().equals(cargarCobrosGUI.getBtnModificar())){
            cargarCobrosGUI.ApreteBtnModificar();
            apreteModificar = true;
        }
        if(e.getSource().equals(cargarCobrosGUI.getBtnEliminar())){
            if(cargarCobrosGUI.getBtnEliminar().getText().equals("Cancelar")){
                cargarCobrosGUI.ApreteBtnGuardarEliminarCancelar();
                apreteModificar = false;
            }else{
                if(cargarCobrosGUI.getBtnEliminar().getText().equals("Eliminar")){
                    Integer resp = JOptionPane.showConfirmDialog(cargarCobrosGUI, "¿Esta seguro que decea eliminar la cuota?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (resp == JOptionPane.YES_OPTION) {
                        abrirBase();
                        Cobro c = Cobro.first("id = ?", idCuotaSeleccionada);
                        Base.close();
                        if(abmCobro.Eliminar(c)){
                            JOptionPane.showMessageDialog(cargarCobrosGUI, "Cuota eliminada.");
                            cargarCobrosGUI.ApreteBtnGuardarEliminarCancelar();
                            CargarCobros();
                        }else{
                            JOptionPane.showMessageDialog(cargarCobrosGUI, "Ocurrio un error.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
        
        if (e.getSource().equals(cargarCobrosGUI.getBtnCrearCuota())) {
            if (cargarCobrosGUI.getBtnCrearCuota().getText().equals("Nuevo")) {
                cargarCobrosGUI.ApreteBtnNuevo();
            } else {
                if (FormatoOK()) {
                    if (cargarCobrosGUI.getBtnCrearCuota().getText().equals("Guardar") && !apreteModificar) {
                        Integer resp = JOptionPane.showConfirmDialog(cargarCobrosGUI, "¿Esta seguro que decea crear una nueva cuota?", "Confirmar", JOptionPane.YES_NO_OPTION);
                        if (resp == JOptionPane.YES_OPTION) {
                            if (abmCobro.Alta(ObtenerDatosCobro(null))) {
                                JOptionPane.showMessageDialog(cargarCobrosGUI, "Cuota creada exitosamente!");
                                CargarCobros();
                                cargarCobrosGUI.ApreteBtnGuardarEliminarCancelar();
                            } else {
                                JOptionPane.showMessageDialog(cargarCobrosGUI, "Ocurrio un error al intentar crear la cuota.", "Error!", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        if (cargarCobrosGUI.getBtnCrearCuota().getText().equals("Guardar") && apreteModificar) {
                            Integer resp = JOptionPane.showConfirmDialog(cargarCobrosGUI, "¿Esta seguro que decea modificar la cuota?", "Confirmar", JOptionPane.YES_NO_OPTION);
                            if (resp == JOptionPane.YES_OPTION) {
                                if (abmCobro.Modificar(ObtenerDatosCobro(idCuotaSeleccionada))) {
                                    JOptionPane.showMessageDialog(cargarCobrosGUI, "Cuota modificada exitosamente!");
                                    CargarCobros();
                                    cargarCobrosGUI.ApreteBtnGuardarEliminarCancelar();
                                } else {
                                    JOptionPane.showMessageDialog(cargarCobrosGUI, "Ocurrio un error al intentar modificar la cuota.", "Error!", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            apreteModificar = false;
                        }
                    }
                }

            }

        }
        if(e.getSource().equals(cargarCobrosGUI.getBtnGenFactura())){
            try {
                reporte.mostrarFactura(abmVentas.getIdVenta());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorCargarCobrosGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorCargarCobrosGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JRException ex) {
                Logger.getLogger(ControladorCargarCobrosGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
