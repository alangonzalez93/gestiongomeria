/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ABMs;

import Modelos.Articulo;
import Modelos.ArticulosVentas;
import Modelos.Cobro;
import Modelos.Venta;
import gomeriaproyect.Triple;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

/**
 *
 * @author alan
 */
public class ABMVentas {
    private int idVenta;
    
    public int getIdVenta(){
        return idVenta;
    }
    
    public boolean Alta(Venta v){
        boolean result = true;
        abrirBase();
        Base.openTransaction();
        Venta venta = Venta.create("cliente_id", v.get("cliente_id"), "fecha", v.get("fecha"), "monto", v.get("monto"), "descripcion", v.get("descripcion")/*, "cant_cuotas", v.get("cant_cuotas")*/, "forma_pago", v.get("forma_pago"));
        if(venta.saveIt()){
            idVenta = venta.getInteger("id");
            Base.commitTransaction();
            Base.close();
            result = result && cargarArticulosVentas(v.getArticulos());
            result = result && actualizarStock(v.getArticulos());
           // result = result && crearCobros(v);
            return result;
        }
        Base.commitTransaction();
        Base.close();
        return false;
    }
    
    public boolean Modificar(Venta v){
        abrirBase();
        Base.openTransaction();
        if(v.saveIt()){
            Base.commitTransaction();
            Base.close();
            return true;
        }
        Base.commitTransaction();
        Base.close();
        return false;
    }
    
    public boolean Eliminar(Venta v){
        abrirBase();
        Base.openTransaction();
        int id = v.getInteger("id");
        if(v.delete()){
            eliminarArticulosVentas(id);
            Cobro.delete("venta_id = ?", id);
            Base.commitTransaction();
            Base.close();
            return true;
        }
        Base.commitTransaction();
        Base.close();
        return false;
    }
    
    public void eliminarArticulosVentas(int ventaId){
        LazyList<ArticulosVentas> lista = ArticulosVentas.where("venta_id = ?", ventaId);
        for(ArticulosVentas av : lista){
            Articulo a = Articulo.first("id = ?", av.get("articulo_id"));
            if(a!= null){
                int nuevoStock = a.getInteger("stock") + av.getInteger("cantidad");
                a.setInteger("stock", nuevoStock);
                a.saveIt();
            }
        }
        ArticulosVentas.delete("venta_id = ?", ventaId);
    }
    
    public boolean cargarArticulosVentas(LinkedList<Triple> listaArticulos){
        abrirBase();
        Base.openTransaction();
        boolean result = true;
        for(Triple t: listaArticulos){
            ArticulosVentas articulosVentas = ArticulosVentas.create("venta_id", idVenta, "articulo_id", t.getIdArticulo(), "cantidad", t.getCantidad(), "precio_final", t.getPrecioFinal());
            result = result && articulosVentas.saveIt();
        }
        Base.commitTransaction();
        Base.close();
        return result;
    }
    
    public boolean actualizarStock(LinkedList<Triple> listaArticulos){
        abrirBase();
        Base.openTransaction();
        boolean result = true;
        BigDecimal newStock;
        for(Triple t: listaArticulos){
            Articulo a = Articulo.first("id = ?", t.getIdArticulo());
            if(a != null){
                System.out.println(a.get("id"));
                newStock = a.getBigDecimal("stock").subtract(t.getCantidad());
                a.set("stock",newStock);
                result = result && a.saveIt();
                System.out.println(newStock);
            }else{
                Base.commitTransaction();
                Base.close();
                return false;
            } 
        }
        Base.commitTransaction();
        Base.close();
        return result;
    }
    
    public boolean crearCobros(Venta v){
        abrirBase();
        Base.openTransaction();
        int cantCuotas = v.getInteger("cant_cuotas");
        String forma_pago = v.getString("forma_pago");
        Date fecha = v.getDate("fecha");
        boolean result = true;
        if(forma_pago.equals("POR MES") || forma_pago.equals("POR QUINCENA") || forma_pago.equals("POR SEMANA")){
            for (int i = 0; i < cantCuotas ; i++) {
                Cobro c = new Cobro();
                c.set("estado","IMPAGO");
                c.set("monto", v.getBigDecimal("monto_cuotas"));
                c.set("venta_id", idVenta);
                c.set("fecha", fecha);
                if(forma_pago.equals("POR MES")){
                    fecha = sumarRestarDiasFecha(fecha, 30);
                }
                if(forma_pago.equals("POR QUINCENA")){
                    fecha = sumarRestarDiasFecha(fecha, 15);
                }
                if(forma_pago.equals("POR SEMANA")){
                    fecha = sumarRestarDiasFecha(fecha, 7);
                }
                result = result && c.saveIt();
            }
        }else{
            Cobro c = new Cobro();
            c.set("estado","IMPAGO");
            c.set("monto", v.getBigDecimal("monto"));
            c.set("venta_id", idVenta);
            c.set("fecha", fecha);
            result = result && c.saveIt();
        }
        return result;
    }
    
    // Suma los días recibidos a la fecha  
    public Date sumarRestarDiasFecha(Date fecha, int dias){
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(fecha); // Configuramos la fecha que se recibe
	calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
	return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }
    
    public void abrirBase() {
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/gomeria", "root", "root");
        }
    }
}
