/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ABMs;

import Modelos.Proveedor;
import org.javalite.activejdbc.Base;

/**
 *
 * @author alan
 */
public class ABMProveedores {
 
    public boolean Alta(Proveedor c){
        abrirBase();
        Base.openTransaction();
        if(c.saveIt()){
            Base.commitTransaction();
            Base.close();
            return true;
        }
        Base.commitTransaction();
            Base.close();
        return false;
    }
    
    public boolean Modificar(Proveedor c){
        abrirBase();
        Base.openTransaction();
        if(c.saveIt()){
            Base.commitTransaction();
            Base.close();
            return true;
        }
        Base.commitTransaction();
        Base.close();
        return false;
    }
    
    public boolean Eliminar(Proveedor c){
        abrirBase();
        Base.openTransaction();
        if(c.delete()){
            Base.commitTransaction();
            Base.close();
            return true;
        }
        Base.commitTransaction();
        Base.close();
        return false;
    }
    
    public void abrirBase() {
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/gomeria", "root", "root");
        }
    }
}
