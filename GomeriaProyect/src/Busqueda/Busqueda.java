/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Busqueda;

import Modelos.Cliente;
import java.util.List;
import org.javalite.activejdbc.Base;

/**
 *
 * @author alan
 */
public class Busqueda {
    
    
    public List<Cliente> BuscarClientes(String buscar, String queBuscar) {
        
        List<Cliente> result=null;
        if(queBuscar.equals("NOMBRE")){
           result = Cliente.where("nombre like ?", "%"+buscar+"%"); 
        }
        if(queBuscar.equals("CIUDAD")){
           result = Cliente.where("ciudad like ?", "%"+buscar+"%"); 
        }
        if(queBuscar.equals("ZONA")){
            result = Cliente.where("zona like ?", "%"+buscar+"%");
        }
        if(queBuscar.equals("TIPO")){
            result = Cliente.where("tipo like ?", "%"+buscar+"%");
        }
        if(queBuscar.equals("RAZON")){
            result = Cliente.where("razon like ?", "%"+buscar+"%");
        }
        
        return result;
    }
    
    public void abrirBase() {
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/gomeria", "root", "root");
        }
    }
}
