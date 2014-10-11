package Controladores;
import ABMs.ABMClientes;
import Interfaz.ClientesGUI;
import Modelos.Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import org.javalite.activejdbc.LazyList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alan
 */
public class ControladorClientesGUI implements ActionListener{
    ClientesGUI clientesGUI;
    ABMClientes abmClientes;
    boolean apreteModificar;
    
    public ControladorClientesGUI(ClientesGUI clientesGUI){
        this.clientesGUI = clientesGUI;
        abmClientes  = new ABMClientes();
    }

    public Cliente ObtenerDatosCliente(String id){
        Cliente cliente;
        if(id == null){
            cliente = new Cliente();
        }else{
            cliente = Cliente.first("id = ?", clientesGUI.getIdTxt().getText());
        }
        cliente.set("nombre", clientesGUI.getApellidoTxt().getText()+", "+clientesGUI.getNombreTxt().getText());
        cliente.set("telefono", clientesGUI.getTelTxt().getText());
        cliente.set("celular", clientesGUI.getCelularTxt().getText());
        cliente.set("direccion", clientesGUI.getDireccionTxt().getText());
        cliente.set("ciudad", clientesGUI.getCiudadBox().getSelectedItem());
        cliente.set("zona", clientesGUI.getZonaBox().getSelectedItem());
        cliente.set("email",clientesGUI.getMailTxt().getText());
        cliente.set("razon", clientesGUI.getRazonBox().getSelectedItem());
        cliente.set("cuit", clientesGUI.getCuitTxt().getText());
        cliente.set("tipo", clientesGUI.getTipoClienteBox().getSelectedItem());
        return cliente;
    }
    
    private void ActualizarLista(){
        LazyList<Cliente> listaClientes = Cliente.findAll();
        for(Cliente cliente: listaClientes){
            Object row[] = new String[3];
            row[0] = cliente.getString("id");
            row[1] = cliente.getString("apellido")+", "+cliente.getString("nombre");
            row[2] = cliente.getString("ciudad");
            clientesGUI.getTablaClientesDefault().addRow(row);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == clientesGUI.getBtnNuevo()){
            if(clientesGUI.getBtnNuevo().getName().equals("Nuevo")){ //Si el nombre del btn es "nuevo" entonces solo habilito los campos
                clientesGUI.ApreteBtnNuevo();
            }
            if(clientesGUI.getBtnNuevo().getName().equals("Guardar") && !apreteModificar){
                if(abmClientes.Alta(ObtenerDatosCliente(null))){
                    JOptionPane.showMessageDialog(clientesGUI, "Cliente registrado exitosamente!");
                    clientesGUI.LimpiarCampos();
                    clientesGUI.EstadoInicial();
                    ActualizarLista();
                }
            }
            if(clientesGUI.getBtnNuevo().getName().equals("Guardar") && apreteModificar){
                if(abmClientes.Modificar(ObtenerDatosCliente(clientesGUI.getIdTxt().getText()))){
                    JOptionPane.showMessageDialog(clientesGUI, "Cliente modificado exitosamente!");
                    clientesGUI.EstadoLuegoDeModificar();
                    ActualizarLista();
                }
                apreteModificar = false;
            }
        }
    }
    
    
}
