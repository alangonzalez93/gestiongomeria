/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import gomeriaproyect.Triple;
import java.util.LinkedList;
import org.javalite.activejdbc.Model;

/**
 *
 * @author alan
 */
public class Compra extends Model{
    private LinkedList<Triple> articulos;
    
    public Compra(){
        articulos = null;
    }
    
    public Compra(LinkedList<Triple> l){
        articulos = l;
    }
    
    public LinkedList<Triple> getArticulos() {
        return articulos;
    }

    public void setArticulos(LinkedList<Triple> articulos) {
        this.articulos = articulos;
    }
    
}
