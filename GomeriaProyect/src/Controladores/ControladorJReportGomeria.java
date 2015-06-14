/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Alan
 */
public class ControladorJReportGomeria {
    
    private JasperReport reporte;
    //private final String logo = "/reporte/logo.png";

    public ControladorJReportGomeria(String jasper) throws JRException, ClassNotFoundException, SQLException {
//        reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/reporte/reporte.jasper"));//cargo el reporte
    }
    
    public void mostrarFactura(int idVenta) throws ClassNotFoundException, SQLException, JRException {
        /*Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/gomeria", "root", "root");
        Map parametros = new HashMap();
        parametros.clear();
        parametros.put("idVenta", idVenta);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, connection);
        JasperViewer.viewReport(jasperPrint, false);
        connection.close();*/
    }
}
