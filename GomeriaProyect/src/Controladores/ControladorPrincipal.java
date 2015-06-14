/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Interfaz.AplicacionGUI;
import Interfaz.ArticulosGUI;
import Interfaz.CargarCompraGUI;
import Interfaz.CargarVentaGUI;
import Interfaz.ClientesGUI;
import Interfaz.ProveedoresGUI;
import Modelos.Articulo;
import Modelos.Cliente;
import Modelos.Proveedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.RoundingMode;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import net.sf.jasperreports.engine.JRException;
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
    ProveedoresGUI proveedoresGUI;
    CargarCompraGUI cargarCompraGUI;
    ControladorClientesGUI controladorClientesGUI;
    ControladorArticulosGUI controladorArticulosGUI;
    ControladorCargarVentaGUI controladorCargarVentaGUI;
    ControladorProveedoresGUI controladorProveedoresGUI;
    ControladorCargarCompraGUI controladorCargarCompraGUI;
    GestionBackupBD gestionBackup;

    public ControladorPrincipal() throws JRException, ClassNotFoundException, SQLException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        aplicacion = new AplicacionGUI();
        clientesGUI = new ClientesGUI();
        cargarVentaGUI = new CargarVentaGUI();
        articulosGUI = new ArticulosGUI();
        proveedoresGUI = new ProveedoresGUI();
        cargarCompraGUI = new CargarCompraGUI();
        
        controladorClientesGUI = new ControladorClientesGUI(clientesGUI,aplicacion);
        controladorArticulosGUI = new ControladorArticulosGUI(articulosGUI);
        controladorCargarVentaGUI = new ControladorCargarVentaGUI(cargarVentaGUI,aplicacion);
        controladorProveedoresGUI = new ControladorProveedoresGUI(proveedoresGUI, aplicacion);
        controladorCargarCompraGUI = new ControladorCargarCompraGUI(cargarCompraGUI, aplicacion);
        
        aplicacion.setActionListener(this);
        
        aplicacion.getContenedorDesk().add(clientesGUI);
        aplicacion.getContenedorDesk().add(cargarVentaGUI);
        aplicacion.getContenedorDesk().add(articulosGUI);
        aplicacion.getContenedorDesk().add(proveedoresGUI);
        aplicacion.getContenedorDesk().add(cargarCompraGUI);
        
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
    
    private void ActualizarListaProveedores(){
        abrirBase();
        proveedoresGUI.getTablaProveedoresDefault().setRowCount(0);
        LazyList<Proveedor> listaProveedores = Proveedor.findAll();
        proveedoresGUI.getEncontradosLbl().setText(String.valueOf(listaProveedores.size()));
        for(Proveedor prov: listaProveedores){
            Object row[] = new String[3];
            row[0] = prov.getString("id");
            row[1] = prov.getString("nombre");
            row[2] = prov.getString("ciudad");
            proveedoresGUI.getTablaProveedoresDefault().addRow(row);
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
    private void ActualizarListaProveedoresEnCargarCompra(){
        abrirBase();
        cargarCompraGUI.getTablaProveedoresDefault().setRowCount(0);
        LazyList<Proveedor> listaProveedores = Proveedor.findAll();
        for(Proveedor prov: listaProveedores){
            Object row[] = new String[3];
            row[0] = prov.getString("id");
            row[1] = prov.getString("nombre");
            row[2] = prov.getString("direccion")+" - "+prov.getString("ciudad");
            cargarCompraGUI.getTablaProveedoresDefault().addRow(row);
        }
        Base.close();
    }
    
    private void ActualizarListaArticulosEnCargarVentaCompra(boolean venta){
        abrirBase();
        if(venta)
            cargarVentaGUI.getTablaArticulosDefault().setRowCount(0);
        else
            cargarCompraGUI.getTablaArticulosDefault().setRowCount(0);
        LazyList<Articulo> listaArticulos = Articulo.findAll();
        for(Articulo articulo: listaArticulos){
            Object row[] = new String[6];
            row[0] = articulo.getString("id");
            row[1] = articulo.getString("marca");
            row[2] = articulo.getString("disenio");
            row[3] = articulo.getString("medida");
            row[4] = articulo.getString("stock");
            if(venta)
                cargarVentaGUI.getTablaArticulosDefault().addRow(row);
            else
                cargarCompraGUI.getTablaArticulosDefault().addRow(row);
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
            clientesGUI.reClick();
            controladorClientesGUI.apreteModificar = false;
            clientesGUI.setVisible(true);
            clientesGUI.toFront();
        }
        if(e.getSource() == aplicacion.getCargarVentaBtn()){
            cargarVentaGUI.setVisible(true);
            ActualizarListaClientesEnCargarVenta();
            ActualizarListaArticulosEnCargarVentaCompra(true);
            //cargarVentaGUI.getCalendario();
            cargarVentaGUI.reClick();
            cargarVentaGUI.toFront();
        }
        if(e.getSource() == aplicacion.getArticulosBtn()){
            ActualizarListaArticulos();
            articulosGUI.reClick();
            articulosGUI.setVisible(true);
            articulosGUI.toFront();
        }
        if(e.getSource() == aplicacion.getProveedoresBtn()){
           ActualizarListaProveedores();
           proveedoresGUI.reClick();
            proveedoresGUI.setVisible(true);
            proveedoresGUI.toFront();
        }
        if(e.getSource() == aplicacion.getCargarCompraBtn()){
            ActualizarListaArticulosEnCargarVentaCompra(false);
            ActualizarListaProveedoresEnCargarCompra();
            cargarCompraGUI.reClick();
            cargarCompraGUI.setVisible(true);
            cargarCompraGUI.toFront();
        }
        if(e.getSource() == aplicacion.getCrearBackup()){
            gestionBackup = new GestionBackupBD();
            gestionBackup.conectar();
            gestionBackup.GuardarRutaBackup();
            gestionBackup.CrearBackup();
            String dir = (new File(System.getProperty("user.dir")).getAbsolutePath());
            System.out.println(dir);
        }
        
    }
    
    public static void main(String[] args) throws JRException, ClassNotFoundException, SQLException{
        ControladorPrincipal controladorPrincipal = new ControladorPrincipal();
    }
    
    
}
