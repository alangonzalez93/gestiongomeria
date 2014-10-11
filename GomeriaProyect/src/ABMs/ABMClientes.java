/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ABMs;

import Modelos.Cliente;

/**
 *
 * @author alan
 */
public class ABMClientes {
    
    public boolean Alta(Cliente c){
        if(c.saveIt()){
            return true;
        }
        return false;
    }
    
    public boolean Modificar(Cliente c){
        if(c.saveIt()){
            return true;
        }
        return false;
    }
    
    public boolean Eliminar(Cliente c){
        if(c.delete()){
            return true;
        }
        return false;
    }
}
