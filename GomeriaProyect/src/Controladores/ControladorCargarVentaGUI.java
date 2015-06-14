/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ABMs.ABMCobros;
import ABMs.ABMVentas;
import Busqueda.Busqueda;
import Interfaz.AplicacionGUI;
import Interfaz.CargarVentaGUI;
import Interfaz.CargarCobrosGUI;
import Modelos.Articulo;
import Modelos.Cliente;
import Modelos.Cobro;
import Modelos.Venta;
import gomeriaproyect.Triple;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.sf.jasperreports.engine.JRException;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

/**
 *
 * @author alan
 */
public class ControladorCargarVentaGUI implements ActionListener, CellEditorListener {

    CargarVentaGUI cargarVentaGUI;
    Busqueda busqueda;
    ABMVentas abmVentas;
    //ABMCobros abmCobro;
    CargarCobrosGUI cargarCobrosGUI;
    ControladorCargarCobrosGUI controladorCargarCobrosGUI;
    AplicacionGUI aplicacionGUI;

    public ControladorCargarVentaGUI(CargarVentaGUI cv, AplicacionGUI ap) throws JRException, ClassNotFoundException, SQLException {
        aplicacionGUI = ap;
        cargarVentaGUI = cv;
        cargarVentaGUI.setActionListener(this);
        busqueda = new Busqueda();
        abmVentas = new ABMVentas();

        ///////////////////Cobros//////////////////
       // abmCobro = new ABMCobros();
        cargarCobrosGUI = new CargarCobrosGUI(aplicacionGUI, true);
        controladorCargarCobrosGUI = new ControladorCargarCobrosGUI(cargarCobrosGUI, abmVentas, cargarVentaGUI);
        //cargarCobrosGUI.setActionListener(this);

        
        ///////////////////FIN COBROS///////////////////

       /* cargarVentaGUI.getFormaPagoBox().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String item = (String) cargarVentaGUI.getFormaPagoBox().getSelectedItem();
                cargarVentaGUI.getNroChequeTxt().setVisible(false);
                cargarVentaGUI.getNroChequeLbl().setVisible(false);
                if (cargarVentaGUI.getFormaPagoBox().getSelectedItem() == "CHEQUE") {
                    cargarVentaGUI.getNroChequeTxt().setVisible(true);
                    cargarVentaGUI.getNroChequeLbl().setVisible(true);
                }
            }
        });*/
        cargarVentaGUI.getBusquedaNombreTxt().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaKeyReleased(evt);
            }
        });

        cargarVentaGUI.getTablaClientes().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClienteMouseClicked(evt);
            }
        });

        cargarVentaGUI.getTablaArticulos().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaArticulosMouseClicked(evt);
            }
        });
    }

    private void busquedaKeyReleased(KeyEvent evt) {
        abrirBase();
        List<Cliente> listaClientes = busqueda.BuscarClientes(cargarVentaGUI.getBusquedaNombreTxt().getText(), "NOMBRE");
        cargarVentaGUI.getTablaClientesDefault().setRowCount(0);
        for (Cliente cliente : listaClientes) {
            Object row[] = new String[3];
            row[0] = cliente.getString("id");
            row[1] = /*cliente.getString("apellido")+", "+*/ cliente.getString("nombre");
            row[2] = cliente.getString("direccion") + " - " + cliente.getString("ciudad");
            cargarVentaGUI.getTablaClientesDefault().addRow(row);
        }
        Base.close();
    }

    private void tablaClienteMouseClicked(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            int r = cargarVentaGUI.getTablaClientes().getSelectedRow();
            cargarVentaGUI.getNombreClienteTxt().setText(String.valueOf(cargarVentaGUI.getTablaClientesDefault().getValueAt(r, 1)));
            cargarVentaGUI.getIdClienteTxt().setText(String.valueOf(cargarVentaGUI.getTablaClientesDefault().getValueAt(r, 0)));
        }
    }

    private void tablaArticulosMouseClicked(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            int r = cargarVentaGUI.getTablaArticulos().getSelectedRow();
            int lineaArticulo = articuloYaCargado(String.valueOf(cargarVentaGUI.getTablaArticulosDefault().getValueAt(r, 0)));
            // si el articulo no estaba en el carrito se agrega el articulo si no se le suma uno a la cantidad
            if (lineaArticulo == -1) {
                abrirBase();
                Articulo articulo = Articulo.first("id = ?", String.valueOf(cargarVentaGUI.getTablaArticulosDefault().getValueAt(r, 0)));
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
                cargarVentaGUI.getTablaVentaDefault().addRow(row);
                Base.close();
            } else {
                // Lo que se hace dentro de este else es sumar en uno a la cantidad del articulo si ya estaba en el carrito.
                Double viejaCantidad = new Double(String.valueOf(cargarVentaGUI.getTablaVentaDefault().getValueAt(lineaArticulo, 2)));
                BigDecimal viejaCantidadBD = BigDecimal.valueOf(viejaCantidad);
                BigDecimal uno = new BigDecimal(1);
                BigDecimal nuevaCantidad = viejaCantidadBD.add(uno);
                cargarVentaGUI.getTablaVentaDefault().setValueAt(nuevaCantidad, lineaArticulo, 2);
            }
            setCellEditor();
            actualizarMonto();

        }
    }

    private int articuloYaCargado(String id) {
        for (int i = 0; i < cargarVentaGUI.getTablaVenta().getRowCount(); i++) {
            if (String.valueOf(cargarVentaGUI.getTablaVentaDefault().getValueAt(i, 0)).equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public Venta ObtenerDatosVenta() {
        abrirBase();
        boolean stockNegativo = false;
        boolean stockCero = false;
        String articulosNegativos = "";
        String articulosCero = "";
        String msg = "";
        BigDecimal cero = new BigDecimal(0);
        LinkedList<Triple> listaArticulos = new LinkedList();
        for (int i = 0; i < cargarVentaGUI.getTablaVentaDefault().getRowCount(); i++) {
            Object idArticulo = cargarVentaGUI.getTablaVentaDefault().getValueAt(i, 0);
            Double doubleCantidad = Double.valueOf(String.valueOf(cargarVentaGUI.getTablaVentaDefault().getValueAt(i, 2)));
            BigDecimal cantidad = BigDecimal.valueOf(doubleCantidad);
            Double doublePrecioFinal = Double.valueOf(String.valueOf(cargarVentaGUI.getTablaVentaDefault().getValueAt(i, 3)));
            BigDecimal precioFinal = BigDecimal.valueOf(doublePrecioFinal);
            listaArticulos.add(new Triple(idArticulo, cantidad, precioFinal));
            /*Checkea y notifica por pantalla si alguno de los productos a vender, luego de realizar la venta, el stock
             *de los mismos queda en cero o negativo.
             */
            Articulo a = Articulo.first("id = ?", idArticulo);
            if ((a.getBigDecimal("stock").subtract(cantidad)).compareTo(cero) == -1) {
                stockNegativo = true;
                if (articulosNegativos.equals("")) {
                    articulosNegativos = String.valueOf(cargarVentaGUI.getTablaVenta().getValueAt(i, 1));
                } else {
                    articulosNegativos = articulosNegativos + ", " + String.valueOf(cargarVentaGUI.getTablaVenta().getValueAt(i, 1));
                }
            }
            if ((a.getBigDecimal("stock").subtract(cantidad)).compareTo(cero) == 0) {
                stockCero = true;
                if (articulosCero.equals("")) {
                    articulosCero = String.valueOf(cargarVentaGUI.getTablaVenta().getValueAt(i, 1));
                } else {
                    articulosCero = articulosCero + ", " + String.valueOf(cargarVentaGUI.getTablaVenta().getValueAt(i, 1));
                }
            }
        }
        Base.close();
        if (stockCero || stockNegativo) {
            if (stockCero) {
                msg = "El stock de el/los articulo/s " + articulosCero + ", una vez realizada esta venta quedara/n en cero (0)!. ";
            }
            if (stockNegativo) {
                msg = msg + "El stock de el/los articulo/s " + articulosNegativos + ", una vez realizada esta venta quedara/n en negativo!.";
            }
            JOptionPane.showMessageDialog(cargarVentaGUI, msg, "Atencion!", JOptionPane.WARNING_MESSAGE);
        }
        /*
         Fin de checkeo de stock 
         */
        Venta v = new Venta(listaArticulos);
        v.set("cliente_id", cargarVentaGUI.getIdClienteTxt().getText());
        v.set("fecha", dateToMySQLDate(cargarVentaGUI.getCalendario().getDate(), false));
        v.setBigDecimal("monto", cargarVentaGUI.getTotalTxt().getText());
        v.set("descripcion", cargarVentaGUI.getDescripcionArea().getText());
        v.set("forma_pago", cargarVentaGUI.getFormaPagoBox().getSelectedItem());
        return v;
    }

    public boolean DatosOK() {
        if ((cargarVentaGUI.getNombreClienteTxt().getText().equals("")) || (cargarVentaGUI.getTablaVenta().getRowCount() == 0)) {
            return false;
        }
        return true;
    }

    public void actualizarMonto() {
        BigDecimal importe;
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < cargarVentaGUI.getTablaVenta().getRowCount(); i++) {
            BigDecimal precio_unit = new BigDecimal(String.valueOf(cargarVentaGUI.getTablaVenta().getValueAt(i, 3)));
            importe = ((BigDecimal) cargarVentaGUI.getTablaVenta().getValueAt(i, 2)).multiply(precio_unit).setScale(2, RoundingMode.CEILING);
            cargarVentaGUI.getTablaVentaDefault().setValueAt(importe, i, 4);
            total = total.add((BigDecimal) cargarVentaGUI.getTablaVentaDefault().getValueAt(i, 4)).setScale(2, RoundingMode.CEILING);
        }
        cargarVentaGUI.getTotalTxt().setText(total.toString());

    }

    public void abrirBase() {
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/gomeria", "root", "root");
        }
    }

    public void setCellEditor() {
        for (int i = 0; i < cargarVentaGUI.getTablaVenta().getRowCount(); i++) {
            cargarVentaGUI.getTablaVenta().getCellEditor(i, 2).addCellEditorListener(this);
            cargarVentaGUI.getTablaVenta().getCellEditor(i, 3).addCellEditorListener(this);
        }
    }

    private void VerCargarCuotasGUI() {
        cargarCobrosGUI.getCuotasTableDefault().setRowCount(0);
        cargarCobrosGUI.getLblNombre().setText(cargarVentaGUI.getNombreClienteTxt().getText());
        cargarCobrosGUI.getLblFormaDePago().setText((String) cargarVentaGUI.getFormaPagoBox().getSelectedItem());
        cargarCobrosGUI.setVisible(true);
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cargarVentaGUI.getQuitarBtn())) {
            if (cargarVentaGUI.getTablaVenta().getSelectedRow() != -1) {//-1 retorna getSelectedRow si no hay fila seleccionada
                cargarVentaGUI.getTablaVentaDefault().removeRow(cargarVentaGUI.getTablaVenta().getSelectedRow());
                actualizarMonto();
            }
        }
        if (e.getSource().equals(cargarVentaGUI.getRegistrarVentaBtn())) {
            if (DatosOK()) {
                if (abmVentas.Alta(ObtenerDatosVenta())) {
                    JOptionPane.showMessageDialog(cargarVentaGUI, "Venta registrada exitosamente!");
                    VerCargarCuotasGUI();
                } else {
                    JOptionPane.showMessageDialog(cargarVentaGUI, "Ocurrio un error, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(cargarVentaGUI, "No se selecciono un cliente o la lista de productos esta vacia.", "Atencion!", JOptionPane.WARNING_MESSAGE);
            }
        }
        ///////////Controlador CargarCobrosGUI//////////////////

        

        /////////////Fin Controlador CargarCobrosGUI/////////////////
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

    @Override
    public void editingStopped(ChangeEvent e) {
        actualizarMonto();
    }

    @Override
    public void editingCanceled(ChangeEvent e) {
    }
}
