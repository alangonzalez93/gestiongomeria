/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Controladores.ControladorArticulosGUI;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alan
 */
public class ArticulosGUI extends javax.swing.JInternalFrame {

     private DefaultTableModel TablaArticulosDefault;
    /**
     * Creates new form ArticulosGUI
     */
    public ArticulosGUI() {
        initComponents();
        TablaArticulosDefault = (DefaultTableModel) TablaArticulos.getModel();
    }

    public DefaultTableModel getTablaArticulosDefault() {
        return TablaArticulosDefault;
    }

    public JTable getTablaArticulos() {
        return TablaArticulos;
    }

    public JTextField getIdTxt() {
        return IdTxt;
    }

    public JComboBox getArticuloBox() {
        return articuloBox;
    }

    public JComboBox getBusquedaArticuloBox() {
        return busquedaArticuloBox;
    }

    public JComboBox getBusquedaMarcaBox() {
        return busquedaMarcaBox;
    }

    public JTextField getBusquedaMedidaTxt() {
        return busquedaMedidaTxt;
    }

    public JSpinner getBusquedaStockSp() {
        return busquedaStockSp;
    }

    public JComboBox getBusquedaVehiculoBox() {
        return busquedaVehiculoBox;
    }

    public JTextArea getDescripcionArea() {
        return descripcionArea;
    }

    public JTextField getDisenioTxt() {
        return DisenioTxt;
    }

    public JButton getEliminarBtn() {
        return eliminarBtn;
    }

    public JLabel getEncontradosLbl() {
        return encontradosLbl;
    }

    public JComboBox getMarcaBox() {
        return marcaBox;
    }

    public JTextField getMedidaTxt() {
        return medidaTxt;
    }

    public JButton getModificarBtn() {
        return modificarBtn;
    }

    public JButton getNuevoBtn() {
        return nuevoBtn;
    }

    public JTextField getPrecioCompraTxt() {
        return precioCompraTxt;
    }

    public JTextField getPrecioVentaTxt() {
        return precioVentaTxt;
    }

    public JSpinner getStockSp() {
        return stockSp;
    }

    public JComboBox getVehiculoBox() {
        return vehiculoBox;
    }

    public JTextField getVendidosTxt() {
        return vendidosTxt;
    }

    public void ApreteBtnNuevoModificar(){
        articuloBox.setEnabled(true);
        vehiculoBox.setEnabled(true);
        marcaBox.setEnabled(true);
        medidaTxt.setEnabled(true);
        DisenioTxt.setEnabled(true);
        DisenioTxt.setEnabled(true);
        precioCompraTxt.setEnabled(true);
        precioVentaTxt.setEnabled(true);
        stockSp.setEnabled(true);
        descripcionArea.setEnabled(true);
        modificarBtn.setEnabled(false);
        nuevoBtn.setText("Guardar");
        eliminarBtn.setText("Cancelar");
        eliminarBtn.setEnabled(true);
    }
    
    public void EstadoInicial(){
        articuloBox.setEnabled(false);
        vehiculoBox.setEnabled(false);
        marcaBox.setEnabled(false);
        medidaTxt.setEnabled(false);
        DisenioTxt.setEnabled(false);
        DisenioTxt.setEnabled(false);
        precioCompraTxt.setEnabled(false);
        precioVentaTxt.setEnabled(false);
        stockSp.setEnabled(false);
        descripcionArea.setEnabled(false);
        nuevoBtn.setText("Nuevo");
        eliminarBtn.setText("Eliminar");
        eliminarBtn.setEnabled(false);
        modificarBtn.setEnabled(false);
    }
    
    public void reClick(){
        EstadoInicial();
        LimpiarCampos();
        DisenioTxt.setEnabled(false);
    }
    
    public void EstadoLuegoDeModificar(){
        articuloBox.setEnabled(false);
        vehiculoBox.setEnabled(false);
        marcaBox.setEnabled(false);
        medidaTxt.setEnabled(false);
        DisenioTxt.setEnabled(false);
        DisenioTxt.setEnabled(false);
        precioCompraTxt.setEnabled(false);
        precioVentaTxt.setEnabled(false);
        stockSp.setEnabled(false);
        descripcionArea.setEnabled(false);
        nuevoBtn.setText("Nuevo");
        eliminarBtn.setText("Eliminar");
        eliminarBtn.setEnabled(true);
        modificarBtn.setEnabled(true);
    }
    
    public void EstadoArticuloSeleccionado(){
        articuloBox.setEnabled(false);
        vehiculoBox.setEnabled(false);
        marcaBox.setEnabled(false);
        medidaTxt.setEnabled(false);
        DisenioTxt.setEnabled(false);
        DisenioTxt.setEnabled(false);
        precioCompraTxt.setEnabled(false);
        precioVentaTxt.setEnabled(false);
        stockSp.setEnabled(false);
        descripcionArea.setEnabled(false);
        nuevoBtn.setText("Nuevo");
        eliminarBtn.setText("Eliminar");
        eliminarBtn.setEnabled(true);
        modificarBtn.setEnabled(true);
    }
    
    public void LimpiarCampos(){
        articuloBox.setSelectedIndex(0);
        vehiculoBox.setSelectedIndex(0);
        marcaBox.setSelectedIndex(0);
        medidaTxt.setText("");
        DisenioTxt.setText("");
        descripcionArea.setText("");
        precioCompraTxt.setText("");
        precioVentaTxt.setText("");
        stockSp.setValue(0);
        vendidosTxt.setText("");
        IdTxt.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaArticulos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        busquedaVehiculoBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        busquedaMarcaBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        busquedaMedidaTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        busquedaStockSp = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        encontradosLbl = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        busquedaArticuloBox = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        articuloBox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        vehiculoBox = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        marcaBox = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        medidaTxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        precioVentaTxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        precioCompraTxt = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        stockSp = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descripcionArea = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        vendidosTxt = new javax.swing.JTextField();
        nuevoBtn = new javax.swing.JButton();
        modificarBtn = new javax.swing.JButton();
        eliminarBtn = new javax.swing.JButton();
        IdTxt = new javax.swing.JTextField();
        DisenioTxt = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestion de articulos");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Articulos"));

        TablaArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Marca", "Disenio", "Medida", "Stock", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaArticulos);

        jLabel1.setText("Vehiculo");

        busquedaVehiculoBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS", "AUTO", "CAMIONETA", "CAMION", "MOTO", "VIAL", "AGRICOLA DELANTERO", "AGRICOLA TRASERO" }));

        jLabel2.setText("Marca");

        busquedaMarcaBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODAS", "PIRELLI", "FATE", "MICHELIN", "FIRESTONE", "BRIDGESTONE", "KUMHO", "SUMITOMO", "DUNLOP" }));

        jLabel3.setText("Medida");

        jLabel4.setText("Stock menor o igual a");

        busquedaStockSp.setMaximumSize(new java.awt.Dimension(1, 10));
        busquedaStockSp.setMinimumSize(new java.awt.Dimension(1, 10));

        jLabel5.setText("Encontrados: ");

        encontradosLbl.setText("xxx");

        jLabel15.setText("Articulo");

        busquedaArticuloBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS", "NEUMATICO", "RUEDA", "CAMARA" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(busquedaArticuloBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(busquedaVehiculoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(encontradosLbl)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(busquedaMarcaBox, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(busquedaMedidaTxt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(busquedaStockSp, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(busquedaVehiculoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(busquedaMarcaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(busquedaMedidaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(busquedaStockSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(busquedaArticuloBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(encontradosLbl))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addGap(33, 33, 33))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del articulo"));

        jLabel6.setText("Articulo");

        articuloBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "NEUMATICO", "RUEDA", "CAMARA" }));
        articuloBox.setEnabled(false);

        jLabel7.setText("Vehiculo");

        vehiculoBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "AUTO", "CAMIONETA", "CAMION", "MOTO", "VIAL", "AGRICOLA DELANTERO", "AGRICOLA TRASERO" }));
        vehiculoBox.setEnabled(false);

        jLabel8.setText("Marca");

        marcaBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "PIRELLI", "FATE", "MICHELIN", "FIRESTONE", "BRIDGESTONE", "KUMHO", "SUMITOMO", "DUNLOP" }));
        marcaBox.setEnabled(false);

        jLabel9.setText("Disenio");

        jLabel10.setText("Medida");

        medidaTxt.setEnabled(false);

        jLabel11.setText("Precio de compra");

        precioVentaTxt.setEnabled(false);

        jLabel12.setText("Precio de venta");

        precioCompraTxt.setEnabled(false);
        precioCompraTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precioCompraTxtActionPerformed(evt);
            }
        });

        jLabel13.setText("Stock");

        stockSp.setEnabled(false);

        jLabel14.setText("Descripcion");

        descripcionArea.setColumns(20);
        descripcionArea.setRows(5);
        descripcionArea.setEnabled(false);
        jScrollPane2.setViewportView(descripcionArea);

        jLabel16.setText("Cantidad vendida");

        vendidosTxt.setEnabled(false);

        nuevoBtn.setText("Nuevo");

        modificarBtn.setText("Modificar");
        modificarBtn.setEnabled(false);

        eliminarBtn.setText("Eliminar");
        eliminarBtn.setEnabled(false);

        IdTxt.setEnabled(false);

        DisenioTxt.setEnabled(false);
        DisenioTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisenioTxtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(articuloBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(vehiculoBox, 0, 172, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(precioCompraTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(IdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(marcaBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(DisenioTxt))
                                .addGap(20, 20, 20))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vendidosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(medidaTxt))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stockSp, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(precioVentaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(jLabel14))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(modificarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .addComponent(eliminarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nuevoBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(articuloBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(IdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(vehiculoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(precioCompraTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(marcaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(precioVentaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(medidaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(DisenioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel13)
                                            .addComponent(stockSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(12, 12, 12))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel16)
                                            .addComponent(vendidosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(nuevoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modificarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(eliminarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void precioCompraTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precioCompraTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precioCompraTxtActionPerformed

    private void DisenioTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisenioTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DisenioTxtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DisenioTxt;
    private javax.swing.JTextField IdTxt;
    private javax.swing.JTable TablaArticulos;
    private javax.swing.JComboBox articuloBox;
    private javax.swing.JComboBox busquedaArticuloBox;
    private javax.swing.JComboBox busquedaMarcaBox;
    private javax.swing.JTextField busquedaMedidaTxt;
    private javax.swing.JSpinner busquedaStockSp;
    private javax.swing.JComboBox busquedaVehiculoBox;
    private javax.swing.JTextArea descripcionArea;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JLabel encontradosLbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox marcaBox;
    private javax.swing.JTextField medidaTxt;
    private javax.swing.JButton modificarBtn;
    private javax.swing.JButton nuevoBtn;
    private javax.swing.JTextField precioCompraTxt;
    private javax.swing.JTextField precioVentaTxt;
    private javax.swing.JSpinner stockSp;
    private javax.swing.JComboBox vehiculoBox;
    private javax.swing.JTextField vendidosTxt;
    // End of variables declaration//GEN-END:variables

    public void setActionListener(ActionListener lis) {
        nuevoBtn.addActionListener(lis);
        modificarBtn.addActionListener(lis);
        eliminarBtn.addActionListener(lis);
    }
}