/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Interfaz.AplicacionGUI;
import Interfaz.ClientesGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alan
 */
public class ControladorPrincipal implements ActionListener{
    AplicacionGUI aplicacion;
    ClientesGUI clientesGUI;
    ControladorClientesGUI controladorClientesGUI;

    public ControladorPrincipal() {
        aplicacion = new AplicacionGUI();
        clientesGUI = new ClientesGUI();
        controladorClientesGUI = new ControladorClientesGUI(clientesGUI);
        aplicacion.setActionListener(this);
        aplicacion.getContenedorDesk().add(clientesGUI);
        
        
        aplicacion.setVisible(true);
                
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == aplicacion.getClientesBtn()){
            clientesGUI.setVisible(true);
        }
    }
    
    public static void main(String[] args){
        ControladorPrincipal controladorPrincipal = new ControladorPrincipal();
    }
    
    
}
