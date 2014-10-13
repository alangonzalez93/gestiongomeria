package Controladores;
import ABMs.ABMClientes;
import Busqueda.Busqueda;
import Interfaz.ClientesGUI;
import Modelos.Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import org.javalite.activejdbc.Base;
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
    Busqueda busquedaClientes;
    boolean apreteModificar = false;
    
    public ControladorClientesGUI(ClientesGUI cg){
        this.clientesGUI = cg;
        clientesGUI.setActionListener(this);
        abmClientes  = new ABMClientes();
        busquedaClientes = new Busqueda();
        this.clientesGUI.getRazonBox().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(clientesGUI.getRazonBox().getSelectedItem().equals("RESP. INSC.")){
                   clientesGUI.getCuitTxt().setEnabled(true);
                   clientesGUI.getCuitLbl().setEnabled(true);
                }else{
                   clientesGUI.getCuitTxt().setEnabled(false); 
                   clientesGUI.getCuitLbl().setEnabled(false);
                   clientesGUI.getCuitTxt().setText("");
                }
            }
        });
        clientesGUI.getTablaClientes().addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tablaClienteMouseClicked(evt);
                }
        });
        clientesGUI.getBusquedaClientesTxt().addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    busquedaKeyReleased(evt);
                }
});
    }
    
    
    private void tablaClienteMouseClicked(MouseEvent evt) {
        if(evt.getClickCount() == 2){
            int r = clientesGUI.getTablaClientes().getSelectedRow();
            CargarDatosClienteSeleccionado(String.valueOf(clientesGUI.getTablaClientesDefault().getValueAt(r, 0)));
            clientesGUI.EstadoClienteSeleccionado();
        }
    }
    
    private void busquedaKeyReleased(KeyEvent evt) {
        abrirBase();
        List<Cliente> listaClientes = busquedaClientes.BuscarClientes(clientesGUI.getBusquedaClientesTxt().getText(), String.valueOf(clientesGUI.getBuscarBox().getSelectedItem()));
        clientesGUI.getTablaClientesDefault().setRowCount(0);
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
    
    //Trae de la base de datos todos los datos del cliente seleccionado en la tabla y los pone en la interfaz
    public void CargarDatosClienteSeleccionado(String idCliente){
        abrirBase();
        Cliente cliente = Cliente.first("id = ?", idCliente);
        if(cliente != null){
            String array[] = cliente.getString("nombre").split(", ");
            clientesGUI.getApellidoTxt().setText(array[0]);
            clientesGUI.getNombreTxt().setText(array[1]);
            clientesGUI.getIdTxt().setText(cliente.getString("id"));
            clientesGUI.getTelTxt().setText(cliente.getString("telefono"));
            clientesGUI.getCelularTxt().setText(cliente.getString("celular"));
            clientesGUI.getDireccionTxt().setText(cliente.getString("direccion"));
            clientesGUI.getCiudadBox().setSelectedItem(cliente.getString("ciudad"));
            clientesGUI.getZonaBox().setSelectedItem(cliente.getString("zona"));
            clientesGUI.getMailTxt().setText(cliente.getString("email"));
            clientesGUI.getRazonBox().setSelectedItem(cliente.getString("razon"));
            clientesGUI.getCuitTxt().setText(cliente.getString("cuit"));
            clientesGUI.getTipoClienteBox().setSelectedItem(cliente.getString("tipo"));
        }else{
            JOptionPane.showMessageDialog(clientesGUI, "Ocurrio un error inesperado, intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        Base.close();
    }
    //Retorna un cliente con todos los datos traidos de los campos de texto de la interfaz
    public Cliente ObtenerDatosCliente(String id){
        Cliente cliente;
        if(id == null){
            cliente = new Cliente();
        }else{
            abrirBase();
            cliente = Cliente.first("id = ?", clientesGUI.getIdTxt().getText());
            Base.close();
        }
        cliente.set("nombre", clientesGUI.getApellidoTxt().getText().toUpperCase()+", "+clientesGUI.getNombreTxt().getText().toUpperCase());
        cliente.set("telefono", clientesGUI.getTelTxt().getText());
        cliente.set("celular", clientesGUI.getCelularTxt().getText());
        cliente.set("direccion", clientesGUI.getDireccionTxt().getText().toUpperCase());
        cliente.set("ciudad", clientesGUI.getCiudadBox().getSelectedItem());
        cliente.set("zona", clientesGUI.getZonaBox().getSelectedItem());
        cliente.set("email",clientesGUI.getMailTxt().getText());
        cliente.set("razon", clientesGUI.getRazonBox().getSelectedItem());
        cliente.set("cuit", clientesGUI.getCuitTxt().getText());
        cliente.set("tipo", clientesGUI.getTipoClienteBox().getSelectedItem());
        return cliente;
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
    
    public boolean DatosObligatoriosOK(){
        if((!clientesGUI.getApellidoTxt().getText().equals("")) && 
           (!clientesGUI.getNombreTxt().getText().equals("")) &&
           ((!clientesGUI.getTelTxt().getText().equals("")) ||
            (!clientesGUI.getCelularTxt().getText().equals(""))) &&
             (!clientesGUI.getDireccionTxt().getText().equals(""))){
          return true;  
        }else{
            return false;
        }
    }
    public void abrirBase() {
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/gomeria", "root", "root");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == clientesGUI.getBtnNuevo()){
            if(clientesGUI.getBtnNuevo().getText().equals("Nuevo")){ //Si el nombre del btn es "nuevo" entonces solo habilito los campos
                clientesGUI.ApreteBtnNuevoModificar();
                clientesGUI.LimpiarCampos();
            }else{
                if(clientesGUI.getBtnNuevo().getText().equals("Guardar") && !apreteModificar){
                    if(DatosObligatoriosOK()){
                        if(abmClientes.Alta(ObtenerDatosCliente(null))){
                            JOptionPane.showMessageDialog(clientesGUI, "Cliente registrado exitosamente!");
                            clientesGUI.LimpiarCampos();
                            clientesGUI.EstadoInicial();
                            ActualizarLista();
                        }else{
                            JOptionPane.showMessageDialog(clientesGUI, "Ocurrio un error, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(clientesGUI, "Los campos: Nombre, Apellido, Telefono (fijo o celular) y Direccion son abligatorios.", "Atencion!", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    if(clientesGUI.getBtnNuevo().getText().equals("Guardar") && apreteModificar){
                        if(DatosObligatoriosOK()){
                            if(abmClientes.Modificar(ObtenerDatosCliente(clientesGUI.getIdTxt().getText()))){
                                JOptionPane.showMessageDialog(clientesGUI, "Cliente modificado exitosamente!");
                                clientesGUI.EstadoLuegoDeModificar();
                                ActualizarLista();
                                apreteModificar = false;
                            }else{
                                JOptionPane.showMessageDialog(clientesGUI, "Ocurrio un error, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(clientesGUI, "Los campos: Nombre, Apellido, Telefono (fijo o celular) y Direccion son abligatorios.", "Atencion!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        }
        if(e.getSource() == clientesGUI.getBtnModificar()){
            apreteModificar = true;
            clientesGUI.ApreteBtnNuevoModificar();
        }
        if(e.getSource() == clientesGUI.getBtnEliminar()){
            if(clientesGUI.getBtnEliminar().getText().equals("Eliminar")){
                Integer resp = JOptionPane.showConfirmDialog(clientesGUI, "¿Desea borrar el cliente " + clientesGUI.getApellidoTxt().getText()+", "+clientesGUI.getNombreTxt().getText()+"?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    if(abmClientes.Eliminar(ObtenerDatosCliente(clientesGUI.getIdTxt().getText()))){
                        JOptionPane.showMessageDialog(clientesGUI, "Cliente eliminado correctamente!");
                        clientesGUI.EstadoInicial();
                        clientesGUI.LimpiarCampos();
                        ActualizarLista();
                    }else{
                        JOptionPane.showMessageDialog(clientesGUI, "Ocurrio un error, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                if(clientesGUI.getBtnEliminar().getText().equals("Cancelar")){
                    clientesGUI.EstadoInicial();
                    clientesGUI.LimpiarCampos();
                }
            }
        }
    }
    
}
