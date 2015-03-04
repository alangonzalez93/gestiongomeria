/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alan
 */
public class ProveedoresGUI extends javax.swing.JInternalFrame {

    DefaultTableModel tablaProveedoresDefault;
    DefaultTableModel tablaComprasDefault;
    DefaultTableModel tablaCuotasDefault;
    
    public ProveedoresGUI() {
        initComponents();
        tablaProveedoresDefault = (DefaultTableModel) TablaProveedores.getModel();
        tablaComprasDefault = (DefaultTableModel) TablaComprasProveedor.getModel();
        tablaCuotasDefault = (DefaultTableModel) TableCuotasCompra.getModel();
    }

    public DefaultTableModel getTablaProveedoresDefault() {
        return tablaProveedoresDefault;
    }

    public DefaultTableModel getTablaComprasDefault() {
        return tablaComprasDefault;
    }

    public DefaultTableModel getTablaCuotasDefault() {
        return tablaCuotasDefault;
    }

    public JButton getBtnCrearNuevoPago() {
        return BtnCrearNuevoPago;
    }

    public JButton getBtnDetalles() {
        return BtnDetalles;
    }

    public JButton getBtnEliminar() {
        return BtnEliminar;
    }

    public JButton getBtnEliminarPago() {
        return BtnEliminarPago;
    }

    public JButton getBtnEliminarCompra() {
        return BtnEliminarCompra;
    }

    public JButton getBtnModificar() {
        return BtnModificar;
    }

    public JButton getBtnModificarPago() {
        return BtnModificarPago;
    }

    public JButton getBtnModificarCompra() {
        return BtnModificarCompra;
    }

    public JButton getBtnNuevo() {
        return BtnNuevo;
    }

    public JButton getBtnPagarCuota() {
        return BtnPagarCuota;
    }

    public JButton getBtnVerTodosPagos() {
        return BtnVerTodosPagos;
    }

    public JTable getTablaComprasProveedor() {
        return TablaComprasProveedor;
    }

    public JTable getTablaProveedores() {
        return TablaProveedores;
    }

    public JTable getTableCuotasCompra() {
        return TableCuotasCompra;
    }

    public JTextField getBusquedaProveedoresTxt() {
        return busquedaProveedoresTxt;
    }

    public JComboBox getComboVerCompras() {
        return comboVerCompras;
    }

    public JLabel getLblCompoPor() {
        return LblCompoPor;
    }

    public JLabel getLblSaldo() {
        return LblSaldo;
    }

    public JComboBox getBuscarBox() {
        return buscarBox;
    }

    public JTextField getCelularTxt() {
        return celularTxt;
    }

    public JComboBox getCiudadBox() {
        return ciudadBox;
    }

    public JTextField getDireccionTxt() {
        return direccionTxt;
    }

    public JLabel getEncontradosLbl() {
        return encontradosLbl;
    }

    public JTextField getIdTxt() {
        return idTxt;
    }

    public JTextField getMailTxt() {
        return mailTxt;
    }

    public JTextField getNombreTxt() {
        return nombreTxt;
    }

    public JTextField getTelTxt() {
        return telTxt;
    }

    public void ApreteBtnNuevoModificar(){
        celularTxt.setEnabled(true);
        ciudadBox.setEnabled(true);
        //cuitTxt.setEnabled(true); Solo se habilita si es responsable responsable insc.
        direccionTxt.setEnabled(true);
        mailTxt.setEnabled(true);
        nombreTxt.setEnabled(true);
        telTxt.setEnabled(true);
        BtnNuevo.setText("Guardar");
        BtnEliminar.setText("Cancelar");
        BtnEliminar.setEnabled(true);
        BtnModificar.setEnabled(false);
    }
    
    public void EstadoInicial(){
        celularTxt.setEnabled(false);
        ciudadBox.setEnabled(false);
         direccionTxt.setEnabled(false);
        mailTxt.setEnabled(false);
        nombreTxt.setEnabled(false);
        telTxt.setEnabled(false);
        BtnNuevo.setText("Nuevo");
        BtnEliminar.setText("Eliminar");
        BtnEliminar.setEnabled(false);
        BtnModificar.setEnabled(false);
    }
    
    public void EstadoProveedorSeleccionado(){
        celularTxt.setEnabled(false);
        ciudadBox.setEnabled(false);
        direccionTxt.setEnabled(false);
        mailTxt.setEnabled(false);
        nombreTxt.setEnabled(false);
        telTxt.setEnabled(false);
        BtnNuevo.setText("Nuevo");
        BtnEliminar.setText("Eliminar");
        BtnEliminar.setEnabled(true);
        BtnNuevo.setEnabled(true);
        BtnModificar.setEnabled(true);
    }
    
    public void EstadoLuegoDeModificar(){
        celularTxt.setEnabled(false);
        ciudadBox.setEnabled(false);
        direccionTxt.setEnabled(false);
        mailTxt.setEnabled(false);
        nombreTxt.setEnabled(false);
        telTxt.setEnabled(false);
        BtnNuevo.setText("Nuevo");
        BtnEliminar.setText("Eliminar");
        BtnEliminar.setEnabled(true);
        BtnModificar.setEnabled(true);
    }
    
    public void LimpiarCampos(){
        idTxt.setText("");
        celularTxt.setText("");
        ciudadBox.setSelectedIndex(0);
        direccionTxt.setText("");
        mailTxt.setText("");
        nombreTxt.setText("");
        telTxt.setText("");
    }
    
    public void reClick(){
        EstadoInicial();
        LimpiarCampos();
        busquedaProveedoresTxt.setText("");
    }
    
    public void setActionListener(ActionListener al){
        BtnNuevo.addActionListener(al);
        BtnModificar.addActionListener(al);
        BtnEliminar.addActionListener(al);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaComprasProveedor = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        comboVerCompras = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableCuotasCompra = new javax.swing.JTable();
        BtnPagarCuota = new javax.swing.JButton();
        BtnEliminarPago = new javax.swing.JButton();
        BtnModificarPago = new javax.swing.JButton();
        BtnVerTodosPagos = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        LblCompoPor = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        BtnModificarCompra = new javax.swing.JButton();
        BtnEliminarCompra = new javax.swing.JButton();
        BtnCrearNuevoPago = new javax.swing.JButton();
        BtnDetalles = new javax.swing.JButton();
        LblSaldo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        busquedaProveedoresTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        buscarBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProveedores = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        encontradosLbl = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        nombreTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ciudadBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        direccionTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        telTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        celularTxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        mailTxt = new javax.swing.JTextField();
        idTxt = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        BtnNuevo = new javax.swing.JButton();
        BtnModificar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestion de proveedores");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Compras realizadas"));

        TablaComprasProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "Monto","Forma pago"}
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.math.BigDecimal.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TablaComprasProveedor);

        jLabel15.setText("Ver");

        comboVerCompras.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODAS", "PAGAS", "IMPAGAS" }));

        jLabel13.setText("Pagos");

        TableCuotasCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha a pagar", "Fecha pago", "Estado", "Monto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(TableCuotasCompra);

        BtnPagarCuota.setText("Pagar cuota");
        BtnPagarCuota.setEnabled(false);

        BtnEliminarPago.setText("Eliminar pago");
        BtnEliminarPago.setEnabled(false);

        BtnModificarPago.setText("Modificar pago");
        BtnModificarPago.setEnabled(false);

        BtnVerTodosPagos.setText("Ver todos");

        jLabel16.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
        jLabel16.setText("Compre por $");

        LblCompoPor.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
        LblCompoPor.setForeground(new java.awt.Color(8, 129, 39));
        LblCompoPor.setText("0.00");

        jLabel18.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
        jLabel18.setText("Saldo $");

        BtnModificarCompra.setText("Modificar compra");
        BtnModificarCompra.setEnabled(false);

        BtnEliminarCompra.setText("Eliminar compra");
        BtnEliminarCompra.setEnabled(false);

        BtnCrearNuevoPago.setText("Crear nueva cuota");
        BtnCrearNuevoPago.setEnabled(false);

        BtnDetalles.setText("Detalles");
        BtnDetalles.setEnabled(false);

        LblSaldo.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
        LblSaldo.setForeground(new java.awt.Color(196, 37, 27));
        LblSaldo.setText("0.00");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboVerCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LblSaldo))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LblCompoPor))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(BtnDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnModificarCompra)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnEliminarCompra)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(BtnPagarCuota)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnCrearNuevoPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnEliminarPago)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnModificarPago)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnVerTodosPagos)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(comboVerCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnModificarCompra)
                    .addComponent(BtnEliminarCompra)
                    .addComponent(BtnDetalles))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnPagarCuota)
                    .addComponent(BtnEliminarPago)
                    .addComponent(BtnModificarPago)
                    .addComponent(BtnVerTodosPagos)
                    .addComponent(BtnCrearNuevoPago))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(LblCompoPor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(LblSaldo))
                .addGap(57, 57, 57))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("")), "Proveedores"));

        jLabel1.setText("Busqueda");

        jLabel2.setText("Buscar por");

        buscarBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NOMBRE", "CIUDAD", "ZONA", "TIPO", "RAZON" }));

        TablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Ciudad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaProveedores.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TablaProveedores);

        jLabel14.setText("Encontrados: ");

        encontradosLbl.setText("xx");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(busquedaProveedoresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(encontradosLbl)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(buscarBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(busquedaProveedoresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(encontradosLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del proveedor"));

        jLabel3.setText("Nombre");

        nombreTxt.setEnabled(false);

        jLabel5.setText("Ciudad");

        ciudadBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NO ESPECIFICA", "RIO CUARTO", "VILLA MERCEDES" }));
        ciudadBox.setEnabled(false);

        jLabel6.setText("Direccion");

        direccionTxt.setEnabled(false);

        jLabel8.setText("Tel");

        telTxt.setEnabled(false);

        jLabel9.setText("Celular");

        celularTxt.setEnabled(false);

        jLabel10.setText("E-Mail");

        mailTxt.setEnabled(false);

        idTxt.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ciudadBox, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(direccionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celularTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ciudadBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(direccionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(telTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(celularTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BtnNuevo.setText("Nuevo");

        BtnModificar.setText("Modificar");
        BtnModificar.setEnabled(false);

        BtnEliminar.setText("Eliminar");
        BtnEliminar.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(BtnModificar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(BtnNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCrearNuevoPago;
    private javax.swing.JButton BtnDetalles;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnEliminarCompra;
    private javax.swing.JButton BtnEliminarPago;
    private javax.swing.JButton BtnModificar;
    private javax.swing.JButton BtnModificarCompra;
    private javax.swing.JButton BtnModificarPago;
    private javax.swing.JButton BtnNuevo;
    private javax.swing.JButton BtnPagarCuota;
    private javax.swing.JButton BtnVerTodosPagos;
    private javax.swing.JLabel LblCompoPor;
    private javax.swing.JLabel LblSaldo;
    private javax.swing.JTable TablaComprasProveedor;
    private javax.swing.JTable TablaProveedores;
    private javax.swing.JTable TableCuotasCompra;
    private javax.swing.JComboBox buscarBox;
    private javax.swing.JTextField busquedaProveedoresTxt;
    private javax.swing.JTextField celularTxt;
    private javax.swing.JComboBox ciudadBox;
    private javax.swing.JComboBox comboVerCompras;
    private javax.swing.JTextField direccionTxt;
    private javax.swing.JLabel encontradosLbl;
    private javax.swing.JTextField idTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField mailTxt;
    private javax.swing.JTextField nombreTxt;
    private javax.swing.JTextField telTxt;
    // End of variables declaration//GEN-END:variables
}
