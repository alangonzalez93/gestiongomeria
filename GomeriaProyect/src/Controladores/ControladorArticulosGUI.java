/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ABMs.ABMArticulos;
import Busqueda.Busqueda;
import Interfaz.ArticulosGUI;
import Modelos.Articulo;
import Modelos.Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;

/**
 *
 * @author alan
 */
public class ControladorArticulosGUI implements ActionListener{
    
    ArticulosGUI articulosGUI;
    ABMArticulos abmArticulos;
    boolean apreteModificar=false;
    int valoresCorrectos = 1;
    Busqueda busquedaArticulos;
    
    public ControladorArticulosGUI(ArticulosGUI ag){
        articulosGUI = ag;
        articulosGUI.setActionListener(this);
        abmArticulos = new ABMArticulos();
        busquedaArticulos = new Busqueda();
        articulosGUI.getArticuloBox().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(articulosGUI.getArticuloBox().getSelectedItem().equals("CAMARA")){
                    articulosGUI.getDisenioTxt().setEnabled(false);
                    articulosGUI.getDisenioTxt().setText("-");
                }else{
                   articulosGUI.getDisenioTxt().setEnabled(true);
                   articulosGUI.getDisenioTxt().setText("");
                }
            }
        });
        articulosGUI.getBusquedaArticuloBox().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BusquedaDeArticulos();
            }
        });
        articulosGUI.getBusquedaMarcaBox().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BusquedaDeArticulos();
            }
        });
        articulosGUI.getBusquedaVehiculoBox().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BusquedaDeArticulos();
            }
        });
       /* articulosGUI.getBusquedaStockSp().addChangeListener(new ChangeListener() {      
            @Override
            public void stateChanged(ChangeEvent e) {
                // handle click
            }
        });*/
        articulosGUI.getTablaArticulos().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(articulosGUI.getTablaArticulos().getSelectedRow() != -1){
                    tablaArticulosMouseClicked(null);
                }else{
                    articulosGUI.EstadoInicial();
                    articulosGUI.LimpiarCampos();
                    articulosGUI.getDisenioTxt().setEditable(false);
                }
                
            }
        });
    }
    
    private void tablaArticulosMouseClicked(MouseEvent evt) {
            int r = articulosGUI.getTablaArticulos().getSelectedRow();
            CargarDatosArticuloSeleccionado(String.valueOf(articulosGUI.getTablaArticulosDefault().getValueAt(r, 0)));
            articulosGUI.EstadoArticuloSeleccionado();
    }
    
    private void BusquedaDeArticulos(){
        abrirBase();
        articulosGUI.getTablaArticulosDefault().setRowCount(0);
        int stock = (int) articulosGUI.getBusquedaStockSp().getValue();
        String vehiculo= (String) articulosGUI.getBusquedaVehiculoBox().getSelectedItem();
        String medida = articulosGUI.getBusquedaMedidaTxt().getText();
        String marca = (String) articulosGUI.getBusquedaMarcaBox().getSelectedItem();
        String tipo = (String) articulosGUI.getBusquedaArticuloBox().getSelectedItem();
        List<Articulo> result = busquedaArticulos.BuscarArticulos(vehiculo, marca, medida, tipo,stock);
        for(Articulo articulo: result){
            Object row[] = new String[6];
            row[0] = articulo.getString("id");
            row[1] = articulo.getString("marca");
            row[2] = articulo.getString("disenio");
            row[3] = articulo.getString("medida");
            row[4] = articulo.getString("stock");
            row[5] = articulo.getBigDecimal("precio_venta").setScale(2, RoundingMode.CEILING).toString();
            articulosGUI.getTablaArticulosDefault().addRow(row);
        }
        Base.close();
    }

    //Trae de la base de datos todos los datos del articulo seleccionado en la tabla y los pone en la interfaz
    public void CargarDatosArticuloSeleccionado(String idArticulo){
        abrirBase();
        Articulo articulo = Articulo.first("id = ?", idArticulo);
        if(articulo != null){
            articulosGUI.getArticuloBox().setSelectedItem(articulo.getString("tipo"));
            articulosGUI.getVehiculoBox().setSelectedItem(articulo.getString("vehiculo"));
            articulosGUI.getMarcaBox().setSelectedItem(articulo.getString("marca"));
            articulosGUI.getMedidaTxt().setText(articulo.getString("medida"));
            articulosGUI.getDisenioTxt().setText(articulo.getString("disenio"));
            articulosGUI.getPrecioCompraTxt().setText(articulo.getBigDecimal("precio_compra").setScale(2, RoundingMode.CEILING).toString());
            articulosGUI.getPrecioVentaTxt().setText(articulo.getBigDecimal("precio_venta").setScale(2, RoundingMode.CEILING).toString());
            articulosGUI.getStockSp().setValue(articulo.getInteger("stock"));
            articulosGUI.getDescripcionArea().setText(articulo.getString("descripcion"));
            articulosGUI.getVendidosTxt().setText(articulo.getString("vendidos"));
            articulosGUI.getIdTxt().setText(articulo.getString("id"));
        }else{
            JOptionPane.showMessageDialog(articulosGUI, "Ocurrio un error inesperado, intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        Base.close();
    }
    
    public boolean DatosObligatoriosOK(){
        if((!articulosGUI.getVehiculoBox().getSelectedItem().equals("Seleccionar")) && 
           (!articulosGUI.getArticuloBox().getSelectedItem().equals("Seleccionar")) &&
           (!articulosGUI.getMarcaBox().getSelectedItem().equals("Seleccionar")) &&
            (!articulosGUI.getMedidaTxt().getText().equals("")) &&
             (!articulosGUI.getDisenioTxt().getText().equals("")) &&
              (!articulosGUI.getPrecioVentaTxt().getText().equals(""))){
          return true;  
        }else{
            return false;
        }
    }
    
    private Articulo ObtenerDatosArticulo(String id){
        abrirBase();
        Articulo articulo;
        if(id == null){
            articulo = new Articulo();
            articulo.set("vendidos", 0);
        }else{
            articulo = Articulo.first("id = ?", id);
        }
        articulo.set("tipo", articulosGUI.getArticuloBox().getSelectedItem());
        articulo.set("vehiculo", articulosGUI.getVehiculoBox().getSelectedItem());
        articulo.set("marca", articulosGUI.getMarcaBox().getSelectedItem());
        articulo.set("medida", articulosGUI.getMedidaTxt().getText().toUpperCase());
        articulo.set("disenio", articulosGUI.getDisenioTxt().getText().toUpperCase());
        Double precioCompra = Double.valueOf(articulosGUI.getPrecioCompraTxt().getText());
        articulo.set("precio_compra", BigDecimal.valueOf(precioCompra).setScale(2, RoundingMode.CEILING));
        Double precioVenta = Double.valueOf(articulosGUI.getPrecioVentaTxt().getText());
        articulo.set("precio_venta", BigDecimal.valueOf(precioVenta).setScale(2, RoundingMode.CEILING));
        articulo.set("stock", articulosGUI.getStockSp().getValue());
        articulo.set("descripcion", articulosGUI.getDescripcionArea().getText().toUpperCase());
           
        Base.close();
        return articulo;
    }
    
    private boolean FormatoOK(){
        Articulo articulo = new Articulo();
        try{
            Double precioCompra = Double.valueOf(articulosGUI.getPrecioCompraTxt().getText());
            articulo.set("precio_compra", BigDecimal.valueOf(precioCompra).setScale(2, RoundingMode.CEILING));
        } catch (NumberFormatException | ClassCastException e) {
           JOptionPane.showMessageDialog(articulosGUI, "Error en precio de compra. Solo se admiten numeros. Los decimales se escriben despues de un . (punto)", "Error de formato", JOptionPane.ERROR_MESSAGE);
           return false;
        }
        try{
            Double precioVenta = Double.valueOf(articulosGUI.getPrecioVentaTxt().getText());
            articulo.set("precio_venta", BigDecimal.valueOf(precioVenta).setScale(2, RoundingMode.CEILING));
        } catch (NumberFormatException | ClassCastException e){
            JOptionPane.showMessageDialog(articulosGUI, "Error en precio de venta. Solo se admiten numeros. Los decimales se escriben despues de un . (punto)", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try{
            articulo.set("stock", articulosGUI.getStockSp().getValue());
            articulo.set("descripcion", articulosGUI.getDescripcionArea().getText());
        }catch(NumberFormatException | ClassCastException e){
            JOptionPane.showMessageDialog(articulosGUI, "Error en el numero de stock. Solo se admiten numeros.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private void ActualizarLista(){
        abrirBase();
        articulosGUI.getTablaArticulosDefault().setRowCount(0);
        LazyList<Articulo> listaArticulos = Articulo.findAll();
        articulosGUI.getEncontradosLbl().setText(String.valueOf(listaArticulos.size()));
        for(Articulo articulo: listaArticulos){
            Object row[] = new String[6];
            row[0] = articulo.getString("id");
            row[1] = articulo.getString("marca");
            row[2] = articulo.getString("disenio");
            row[3] = articulo.getString("medida");
            row[4] = articulo.getString("stock");
            row[5] = articulo.getBigDecimal("precio_venta").setScale(2, RoundingMode.CEILING).toString();
            articulosGUI.getTablaArticulosDefault().addRow(row);
        }
        Base.close();
    }
    
    public void abrirBase() {
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/gomeria", "root", "root");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == articulosGUI.getNuevoBtn()){
            if(articulosGUI.getNuevoBtn().getText().equals("Nuevo")){ //Si el nombre del btn es "nuevo" entonces solo habilito los campos
                articulosGUI.ApreteBtnNuevoModificar();
                articulosGUI.LimpiarCampos();
            }else{
                if(articulosGUI.getNuevoBtn().getText().equals("Guardar") && !apreteModificar){
                    if(DatosObligatoriosOK()){
                        if(FormatoOK()){
                            if(abmArticulos.Alta(ObtenerDatosArticulo(null))){
                                JOptionPane.showMessageDialog(articulosGUI, "Articulo registrado exitosamente!");
                                articulosGUI.LimpiarCampos();
                                articulosGUI.EstadoInicial();
                                ActualizarLista();
                            }else{
                                JOptionPane.showMessageDialog(articulosGUI, "Ocurrio un error, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(articulosGUI, "Hay campos obligatorios sin completar.", "Atencion!", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    if(articulosGUI.getNuevoBtn().getText().equals("Guardar") && apreteModificar){
                        if(DatosObligatoriosOK()){
                            if(abmArticulos.Modificar(ObtenerDatosArticulo(articulosGUI.getIdTxt().getText()))){
                                JOptionPane.showMessageDialog(articulosGUI, "Articulo modificado exitosamente!");
                                articulosGUI.EstadoLuegoDeModificar();
                                ActualizarLista();
                                apreteModificar = false;
                            }else{
                                JOptionPane.showMessageDialog(articulosGUI, "Ocurrio un error, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(articulosGUI, "Hay campos obligatorios sin completar.", "Atencion!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        }
        if(e.getSource() == articulosGUI.getModificarBtn()){
            apreteModificar = true;
            articulosGUI.ApreteBtnNuevoModificar();
        }
        if(e.getSource() == articulosGUI.getEliminarBtn()){
            if(articulosGUI.getEliminarBtn().getText().equals("Eliminar")){
                Integer resp = JOptionPane.showConfirmDialog(articulosGUI, "¿Desea borrar el articulo " + articulosGUI.getMarcaBox().getSelectedItem()+" "+articulosGUI.getDisenioTxt().getText()+" "+articulosGUI.getMedidaTxt().getText()+"?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    if(abmArticulos.Eliminar(ObtenerDatosArticulo(articulosGUI.getIdTxt().getText()))){
                        JOptionPane.showMessageDialog(articulosGUI, "Articulo eliminado correctamente!");
                        articulosGUI.EstadoInicial();
                        articulosGUI.LimpiarCampos();
                        ActualizarLista();
                    }else{
                        JOptionPane.showMessageDialog(articulosGUI, "Ocurrio un error, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                if(articulosGUI.getEliminarBtn().getText().equals("Cancelar")){
                    articulosGUI.EstadoInicial();
                    articulosGUI.LimpiarCampos();
                    articulosGUI.getDisenioTxt().setEnabled(false);//por alguna razon tengo que hacer esto aca, porque en EstadoInicial() no lo hace -.-
                }
            }
        }
       
    }
    
}
