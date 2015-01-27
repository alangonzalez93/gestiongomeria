/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Busqueda;

import Modelos.Articulo;
import Modelos.Cliente;
import java.util.ArrayList;
import java.util.List;
import org.javalite.activejdbc.Base;

/**
 *
 * @author alan
 */
public class Busqueda {

    public List<Cliente> BuscarClientes(String buscar, String queBuscar) {
        List<Cliente> result = null;
        if (queBuscar.equals("NOMBRE")) {
            result = Cliente.where("nombre like ?", "%" + buscar + "%");
        }
        if (queBuscar.equals("CIUDAD")) {
            result = Cliente.where("ciudad like ?", "%" + buscar + "%");
        }
        if (queBuscar.equals("ZONA")) {
            result = Cliente.where("zona like ?", "%" + buscar + "%");
        }
        if (queBuscar.equals("TIPO")) {
            result = Cliente.where("tipo like ?", "%" + buscar + "%");
        }
        if (queBuscar.equals("RAZON")) {
            result = Cliente.where("razon like ?", "%" + buscar + "%");
        }

        return result;
    }

    public List<Articulo> BuscarArticulos(String vehiculo, String marca, String medida, String articulo, int stock) {
        System.out.println("Entre a buscar articulos");
        List<Articulo> result = null;
        ArrayList<Object> params = new ArrayList<Object>();
        String query = "";
        if (vehiculo != "TODOS") {
            System.out.println("Entre a vehiculos");
            System.out.println(vehiculo);
            params.add(vehiculo);
            if (query != "") {
                query += " and vehiculo = ?";
            } else {
                query += "vehiculo = ?";
            }
        }
        if (marca != "TODAS") {
            params.add(marca);
            if (query != "") {
                query += " and marca = ?";
            } else {
                query += "marca = ?";
            }
        }
        if (medida != "") {
            params.add(medida);
            if (query != "") {
                query += " and medida = ?";
            } else {
                query += "medida = ?";
            }
        }
        if (articulo != "TODOS") {
            params.add(articulo);
            if (query != "") {
                query += " and tipo = ?";
            } else {
                query += "tipo = ?";
            }
        }
        if (stock != -1) {
            params.add(stock);
            if (query != "") {
                query += " and stock >= ?";
            } else {
                query += "stock >= ?";
            }
        }
        switch (params.size()) {
            case 1:
                System.out.println(query+" "+params.get(0));
                result = Articulo.where(query, params.get(0));
                break;
            case 2:
                result = Articulo.where(query, params.get(0), params.get(1));
                break;
            case 3:
                result = Articulo.where(query, params.get(0), params.get(1), params.get(2));
                break;
            case 4:
                result = Articulo.where(query, params.get(0), params.get(1), params.get(2), params.get(3));
                break;
            case 5:
                result = Articulo.where(query, params.get(0), params.get(1), params.get(2), params.get(3), params.get(4));
                break;
            default:
                System.out.println("error");
                break;
        }
        
        return result;
    }

    public void abrirBase() {
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/gomeria", "root", "root");
        }
    }
}
