/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Interfaz.AplicacionGUI;
import Interfaz.ArticulosGUI;
import Interfaz.CargarVentaGUI;
import Interfaz.ClientesGUI;
import Modelos.Articulo;
import Modelos.Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;

/**
 *
 * @author alan
 */
public class ControladorPrincipal implements ActionListener{
    AplicacionGUI aplicacion;
    ClientesGUI clientesGUI;
    CargarVentaGUI cargarVentaGUI;
    ArticulosGUI articulosGUI;
    ControladorClientesGUI controladorClientesGUI;
    ControladorArticulosGUI controladorArticulosGUI;
    ControladorCargarVentaGUI controladorCargarVentaGUI;

    public ControladorPrincipal() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        aplicacion = new AplicacionGUI();
        clientesGUI = new ClientesGUI();
        cargarVentaGUI = new CargarVentaGUI();
        articulosGUI = new ArticulosGUI();
        
        controladorClientesGUI = new ControladorClientesGUI(clientesGUI,aplicacion);
        controladorArticulosGUI = new ControladorArticulosGUI(articulosGUI);
        controladorCargarVentaGUI = new ControladorCargarVentaGUI(cargarVentaGUI,aplicacion);
        aplicacion.setActionListener(this);
        
        aplicacion.getContenedorDesk().add(clientesGUI);
        aplicacion.getContenedorDesk().add(cargarVentaGUI);
        aplicacion.getContenedorDesk().add(articulosGUI);
        
        aplicacion.setVisible(true);
        aplicacion.setExtendedState(JFrame.MAXIMIZED_BOTH);
                
    }
    private void ActualizarListaClientes(){
        abrirBase();
        clientesGUI.getTablaClientesDefault().setRowCount(0);
        LazyList<Cliente> listaClientes = Cliente.findAll();
        clientesGUI.getEncontradosLbl().setText(String.valueOf(listaClientes.size()));
        for(Cliente cliente: listaClientes){
            Object row[] = new String[3];
            row[0] = cliente.getString("id");
            row[1] = /*cliente.getString("apellido")+", "+*/cliente.getString("nombre");
            row[2] = cliente.getString("ciudad");
            clientesGUI.getTablaClientesDefault().addRow(row);
        }
        Base.close();
    }
    
    private void ActualizarListaArticulos(){
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
    
    private void ActualizarListaClientesEnCargarVenta(){
        abrirBase();
        cargarVentaGUI.getTablaClientesDefault().setRowCount(0);
        LazyList<Cliente> listaClientes = Cliente.findAll();
        for(Cliente cliente: listaClientes){
            Object row[] = new String[3];
            row[0] = cliente.getString("id");
            row[1] = /*cliente.getString("apellido")+", "+*/cliente.getString("nombre");
            row[2] = cliente.getString("direccion")+" - "+cliente.getString("ciudad");
            cargarVentaGUI.getTablaClientesDefault().addRow(row);
        }
        Base.close();
    }
    
    private void ActualizarListaArticulosEnCargarVenta(){
        abrirBase();
        cargarVentaGUI.getTablaArticulosDefault().setRowCount(0);
        LazyList<Articulo> listaArticulos = Articulo.findAll();
        for(Articulo articulo: listaArticulos){
            Object row[] = new String[6];
            row[0] = articulo.getString("id");
            row[1] = articulo.getString("marca");
            row[2] = articulo.getString("disenio");
            row[3] = articulo.getString("medida");
            row[4] = articulo.getString("stock");
          //  row[5] = articulo.getBigDecimal("precio_venta").setScale(2, RoundingMode.CEILING).toString();;
            cargarVentaGUI.getTablaArticulosDefault().addRow(row);
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
        if(e.getSource() == aplicacion.getClientesBtn()){
            ActualizarListaClientes();
            clientesGUI.setVisible(true);
            clientesGUI.toFront();
        }
        if(e.getSource() == aplicacion.getCargarVentaBtn()){
            cargarVentaGUI.setVisible(true);
            ActualizarListaClientesEnCargarVenta();
            ActualizarListaArticulosEnCargarVenta();
            cargarVentaGUI.getCalendario();
            cargarVentaGUI.toFront();
        }
        if(e.getSource() == aplicacion.getArticulosBtn()){
            ActualizarListaArticulos();
            articulosGUI.setVisible(true);
            articulosGUI.toFront();
        }
    }
    
    public static void main(String[] args){
        ControladorPrincipal controladorPrincipal = new ControladorPrincipal();
    }
    
    
}
