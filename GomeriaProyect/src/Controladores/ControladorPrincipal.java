/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Interfaz.AplicacionGUI;
import Interfaz.ClientesGUI;
import Modelos.Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    ControladorClientesGUI controladorClientesGUI;

    public ControladorPrincipal() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        aplicacion = new AplicacionGUI();
        clientesGUI = new ClientesGUI();
        controladorClientesGUI = new ControladorClientesGUI(clientesGUI);
        aplicacion.setActionListener(this);
        aplicacion.getContenedorDesk().add(clientesGUI);
        
        
        aplicacion.setVisible(true);
                
    }
    private void ActualizarLista(){
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
    public void abrirBase() {
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/gomeria", "root", "root");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == aplicacion.getClientesBtn()){
            ActualizarLista();
            clientesGUI.setVisible(true);
        }
    }
    
    public static void main(String[] args){
        ControladorPrincipal controladorPrincipal = new ControladorPrincipal();
    }
    
    
}
