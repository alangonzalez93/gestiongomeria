/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.event.ActionListener;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alan
 */
public class ClientesGUI extends javax.swing.JInternalFrame {

    private DefaultTableModel TablaClientesDefault;
    private DefaultTableModel TablaVentaClientesDefault;
    private DefaultTableModel TablaCuotasVentasClientesDefault;
    /**
     * Creates new form ClientesGUI
     */
    public ClientesGUI() {
        initComponents();
        TablaClientesDefault = (DefaultTableModel) TablaClientes.getModel();
        TablaVentaClientesDefault = (DefaultTableModel) TablaVentasCliente.getModel();
        TablaCuotasVentasClientesDefault = (DefaultTableModel) TableCuotasVentaCliente.getModel();
        
        TablaClientes.getColumnModel().getColumn(0).setPreferredWidth(20);
        TablaClientes.getColumnModel().getColumn(1).setPreferredWidth(200);
        TablaClientes.getColumnModel().getColumn(2).setPreferredWidth(170);
        
        TablaVentasCliente.getColumnModel().getColumn(0).setPreferredWidth(50);
        TablaVentasCliente.getColumnModel().getColumn(1).setPreferredWidth(100);
        TablaVentasCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
        TablaVentasCliente.getColumnModel().getColumn(3).setPreferredWidth(100);
        
        TableCuotasVentaCliente.getColumnModel().getColumn(0).setPreferredWidth(50);
        TableCuotasVentaCliente.getColumnModel().getColumn(1).setPreferredWidth(100);
        TableCuotasVentaCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
        TableCuotasVentaCliente.getColumnModel().getColumn(3).setPreferredWidth(100);
       // TableCuotasVentaCliente.getColumnModel().getColumn(4).setPreferredWidth(100);
        
        TablaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TablaVentasCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableCuotasVentaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
    }

    public DefaultTableModel getTablaClientesDefault() {
        return TablaClientesDefault;
    }

    public JTable getTablaClientes() {
        return TablaClientes;
    }

    public JLabel getLblCompoPor() {
        return LblCompoPor;
    }

    public DefaultTableModel getTablaVentaClientesDefault() {
        return TablaVentaClientesDefault;
    }

    public JLabel getLblSaldo() {
        return LblSaldo;
    }

    public DefaultTableModel getTablaCuotasVentasClientesDefault() {
        return TablaCuotasVentasClientesDefault;
    }

    public JTable getTablaVentasCliente() {
        return TablaVentasCliente;
    }

    public JTable getTableCuotasVentaCliente() {
        return TableCuotasVentaCliente;
    }

    
    public JTextField getApellidoTxt() {
        return apellidoTxt;
    }

    public void setApellidoTxt(JTextField apellidoTxt) {
        this.apellidoTxt = apellidoTxt;
    }

    public JComboBox getBuscarBox() {
        return buscarBox;
    }

    public void setBuscarBox(JComboBox buscarBox) {
        this.buscarBox = buscarBox;
    }

    public JTextField getBusquedaClientesTxt() {
        return busquedaClientesTxt;
    }

    public void setBusquedaClientesTxt(JTextField busquedaClientesTxt) {
        this.busquedaClientesTxt = busquedaClientesTxt;
    }

    public JTextField getCelularTxt() {
        return celularTxt;
    }

    public void setCelularTxt(JTextField celularTxt) {
        this.celularTxt = celularTxt;
    }

    public JButton getBtnCrearNuevoPago() {
        return BtnCrearNuevoPago;
    }

    public JButton getBtnEliminarPago() {
        return BtnEliminarPago;
    }

    public JButton getBtnModificarPago() {
        return BtnModificarPago;
    }

    public JButton getBtnPagarCuota() {
        return BtnPagarCuota;
    }

    public JButton getBtnVerTodosPagos() {
        return BtnVerTodosPagos;
    }

    public JComboBox getCiudadBox() {
        return ciudadBox;
    }

    public void setCiudadBox(JComboBox ciudadBox) {
        this.ciudadBox = ciudadBox;
    }

    public JTextField getCuitTxt() {
        return cuitTxt;
    }

    public void setCuitTxt(JTextField cuitTxt) {
        this.cuitTxt = cuitTxt;
    }

    public JTextField getDireccionTxt() {
        return direccionTxt;
    }

    public void setDireccionTxt(JTextField direccionTxt) {
        this.direccionTxt = direccionTxt;
    }

    public JLabel getEncontradosLbl() {
        return encontradosLbl;
    }

    public void setEncontradosLbl(JLabel encontradosLbl) {
        this.encontradosLbl = encontradosLbl;
    }
    

    public JTextField getMailTxt() {
        return mailTxt;
    }

    public void setMailTxt(JTextField mailTxt) {
        this.mailTxt = mailTxt;
    }

    public JTextField getNombreTxt() {
        return nombreTxt;
    }

    public void setNombreTxt(JTextField nombreTxt) {
        this.nombreTxt = nombreTxt;
    }

    public JComboBox getRazonBox() {
        return razonBox;
    }

    public void setRazonBox(JComboBox razonBox) {
        this.razonBox = razonBox;
    }

    public JTextField getTelTxt() {
        return telTxt;
    }

    public void setTelTxt(JTextField telTxt) {
        this.telTxt = telTxt;
    }

    public JComboBox getTipoClienteBox() {
        return tipoClienteBox;
    }

    public void setTipoClienteBox(JComboBox tipoClienteBox) {
        this.tipoClienteBox = tipoClienteBox;
    }

    public JComboBox getZonaBox() {
        return zonaBox;
    }

    public void setZonaBox(JComboBox zonaBox) {
        this.zonaBox = zonaBox;
    }

    public JButton getBtnEliminar() {
        return BtnEliminar;
    }

    public void setBtnEliminar(JButton BtnEliminar) {
        this.BtnEliminar = BtnEliminar;
    }

    public JButton getBtnModificar() {
        return BtnModificar;
    }

    public void setBtnModificar(JButton BtnModificar) {
        this.BtnModificar = BtnModificar;
    }

    public JButton getBtnNuevo() {
        return BtnNuevo;
    }

    public void setBtnNuevo(JButton BtnNuevo) {
        this.BtnNuevo = BtnNuevo;
    }

    public JTextField getIdTxt() {
        return idTxt;
    }

    public void setIdTxt(JTextField idTxt) {
        this.idTxt = idTxt;
    }

    public JLabel getCuitLbl() {
        return CuitLbl;
    }
    

    public void ApreteBtnNuevoModificar(){
        apellidoTxt.setEnabled(true);
        celularTxt.setEnabled(true);
        ciudadBox.setEnabled(true);
        //cuitTxt.setEnabled(true); Solo se habilita si es responsable responsable insc.
        direccionTxt.setEnabled(true);
        mailTxt.setEnabled(true);
        nombreTxt.setEnabled(true);
        razonBox.setEnabled(true);
        telTxt.setEnabled(true);
        tipoClienteBox.setEnabled(true);
        zonaBox.setEnabled(true);
        BtnNuevo.setText("Guardar");
        BtnEliminar.setText("Cancelar");
        BtnEliminar.setEnabled(true);
        BtnModificar.setEnabled(false);
    }
    
    public void EstadoInicial(){
        apellidoTxt.setEnabled(false);
        celularTxt.setEnabled(false);
        ciudadBox.setEnabled(false);
        cuitTxt.setEnabled(false); 
        direccionTxt.setEnabled(false);
        mailTxt.setEnabled(false);
        nombreTxt.setEnabled(false);
        razonBox.setEnabled(false);
        telTxt.setEnabled(false);
        tipoClienteBox.setEnabled(false);
        zonaBox.setEnabled(false);
        BtnNuevo.setText("Nuevo");
        BtnEliminar.setText("Eliminar");
        BtnEliminar.setEnabled(false);
        BtnModificar.setEnabled(false);
    }
    
    public void reClick(){
        EstadoInicial();
        LimpiarCampos();
        busquedaClientesTxt.setText("");
    }
    
    public void EstadoClienteSeleccionado(){
        apellidoTxt.setEnabled(false);
        celularTxt.setEnabled(false);
        ciudadBox.setEnabled(false);
        cuitTxt.setEnabled(false); 
        direccionTxt.setEnabled(false);
        mailTxt.setEnabled(false);
        nombreTxt.setEnabled(false);
        razonBox.setEnabled(false);
        telTxt.setEnabled(false);
        tipoClienteBox.setEnabled(false);
        zonaBox.setEnabled(false);
        BtnNuevo.setText("Nuevo");
        BtnEliminar.setText("Eliminar");
        BtnEliminar.setEnabled(true);
        BtnNuevo.setEnabled(true);
        BtnModificar.setEnabled(true);
    }
    
    public void EstadoLuegoDeModificar(){
        apellidoTxt.setEnabled(false);
        celularTxt.setEnabled(false);
        ciudadBox.setEnabled(false);
        cuitTxt.setEnabled(false); 
        direccionTxt.setEnabled(false);
        mailTxt.setEnabled(false);
        nombreTxt.setEnabled(false);
        razonBox.setEnabled(false);
        telTxt.setEnabled(false);
        tipoClienteBox.setEnabled(false);
        zonaBox.setEnabled(false);
        BtnNuevo.setText("Nuevo");
        BtnEliminar.setText("Eliminar");
        BtnEliminar.setEnabled(true);
        BtnModificar.setEnabled(true);
    }
    
    public void LimpiarCampos(){
        idTxt.setText("");
        apellidoTxt.setText("");
        celularTxt.setText("");
        ciudadBox.setSelectedIndex(0);
        cuitTxt.setText(""); 
        direccionTxt.setText("");
        mailTxt.setText("");
        nombreTxt.setText("");
        razonBox.setSelectedIndex(0);
        cuitTxt.setEnabled(false);
        telTxt.setText("");
        tipoClienteBox.setSelectedIndex(0);
        zonaBox.setSelectedIndex(0);
    }

    public JButton getBtnEliminarVenta() {
        return BtnEliminarVenta;
    }

    public JButton getBtnModificarVenta() {
        return BtnModificarVenta;
    }

    public JButton getBtnDetalles() {
        return BtnDetalles;
    }
    
    public void setActionListener(ActionListener lis) {
        BtnNuevo.addActionListener(lis);
        BtnModificar.addActionListener(lis);
        BtnEliminar.addActionListener(lis);
        BtnCrearNuevoPago.addActionListener(lis);
        BtnEliminarPago.addActionListener(lis);
        BtnModificarPago.addActionListener(lis);
        BtnPagarCuota.addActionListener(lis);
        BtnVerTodosPagos.addActionListener(lis);
        BtnDetalles.addActionListener(lis);
        BtnEliminarVenta.addActionListener(lis);
        BtnModificarVenta.addActionListener(lis);
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
        jLabel1 = new javax.swing.JLabel();
        busquedaClientesTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        buscarBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaClientes = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        encontradosLbl = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        nombreTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        apellidoTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ciudadBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        direccionTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        zonaBox = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        telTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        celularTxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        mailTxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        razonBox = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        tipoClienteBox = new javax.swing.JComboBox();
        CuitLbl = new javax.swing.JLabel();
        cuitTxt = new javax.swing.JTextField();
        idTxt = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        BtnNuevo = new javax.swing.JButton();
        BtnModificar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaVentasCliente = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableCuotasVentaCliente = new javax.swing.JTable();
        BtnPagarCuota = new javax.swing.JButton();
        BtnEliminarPago = new javax.swing.JButton();
        BtnModificarPago = new javax.swing.JButton();
        BtnVerTodosPagos = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        LblCompoPor = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        BtnModificarVenta = new javax.swing.JButton();
        BtnEliminarVenta = new javax.swing.JButton();
        BtnCrearNuevoPago = new javax.swing.JButton();
        BtnDetalles = new javax.swing.JButton();
        LblSaldo = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestion de clientes");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("")), "Clientes"));

        jLabel1.setText("Busqueda");

        jLabel2.setText("Buscar por");

        buscarBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NOMBRE", "CIUDAD", "ZONA", "TIPO", "RAZON" }));

        TablaClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        TablaClientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TablaClientes);

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
                        .addComponent(busquedaClientesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(busquedaClientesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(encontradosLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del cliente"));

        jLabel3.setText("Nombre");

        nombreTxt.setEnabled(false);

        jLabel4.setText("Apellido");

        apellidoTxt.setEnabled(false);

        jLabel5.setText("Ciudad");

        ciudadBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NO ESPECIFICA", "RIO CUARTO", "VILLA MERCEDES" }));
        ciudadBox.setEnabled(false);

        jLabel6.setText("Direccion");

        direccionTxt.setEnabled(false);

        jLabel7.setText("Zona");

        zonaBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NO ESPECIFICA", "NOROESTE", "NORESTE", "SUROESTE", "SURESTE" }));
        zonaBox.setEnabled(false);

        jLabel8.setText("Tel");

        telTxt.setEnabled(false);

        jLabel9.setText("Celular");

        celularTxt.setEnabled(false);

        jLabel10.setText("E-Mail");

        mailTxt.setEnabled(false);

        jLabel11.setText("Razon");

        razonBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NO ESPECIFICA", "RESP. INSC.", "CONSUMIDOR FINAL" }));
        razonBox.setEnabled(false);

        jLabel12.setText("Tipo de cliente");

        tipoClienteBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NO ESPECIFICA", "MOSTRADOR", "CALLE" }));
        tipoClienteBox.setEnabled(false);

        CuitLbl.setText("CUIT");
        CuitLbl.setEnabled(false);

        cuitTxt.setEnabled(false);

        idTxt.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ciudadBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(zonaBox, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(direccionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(razonBox, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CuitLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cuitTxt))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(telTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(celularTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tipoClienteBox, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(apellidoTxt))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(apellidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ciudadBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(zonaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(direccionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(telTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(celularTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(mailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(razonBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CuitLbl)
                    .addComponent(cuitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(tipoClienteBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Ventas realizadas"));

        TablaVentasCliente.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(TablaVentasCliente);

        jLabel15.setText("Ver");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODAS", "PAGAS", "IMPAGAS" }));

        jLabel13.setText("Pagos");

        TableCuotasVentaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha a pagar", "Fecha pago", "Monto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
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
        jScrollPane3.setViewportView(TableCuotasVentaCliente);

        BtnPagarCuota.setText("Pagar cuota");
        BtnPagarCuota.setEnabled(false);

        BtnEliminarPago.setText("Eliminar pago");
        BtnEliminarPago.setEnabled(false);

        BtnModificarPago.setText("Modificar pago");
        BtnModificarPago.setEnabled(false);

        BtnVerTodosPagos.setText("Ver todos");

        jLabel16.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
        jLabel16.setText("Compro por $");

        LblCompoPor.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
        LblCompoPor.setForeground(new java.awt.Color(8, 129, 39));
        LblCompoPor.setText("0.00");

        jLabel18.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
        jLabel18.setText("Saldo $");

        BtnModificarVenta.setText("Modificar venta");
        BtnModificarVenta.setEnabled(false);

        BtnEliminarVenta.setText("Eliminar venta");
        BtnEliminarVenta.setEnabled(false);

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
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(BtnModificarVenta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnEliminarVenta)))
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
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnModificarVenta)
                    .addComponent(BtnEliminarVenta)
                    .addComponent(BtnDetalles))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JButton BtnEliminarPago;
    private javax.swing.JButton BtnEliminarVenta;
    private javax.swing.JButton BtnModificar;
    private javax.swing.JButton BtnModificarPago;
    private javax.swing.JButton BtnModificarVenta;
    private javax.swing.JButton BtnNuevo;
    private javax.swing.JButton BtnPagarCuota;
    private javax.swing.JButton BtnVerTodosPagos;
    private javax.swing.JLabel CuitLbl;
    private javax.swing.JLabel LblCompoPor;
    private javax.swing.JLabel LblSaldo;
    private javax.swing.JTable TablaClientes;
    private javax.swing.JTable TablaVentasCliente;
    private javax.swing.JTable TableCuotasVentaCliente;
    private javax.swing.JTextField apellidoTxt;
    private javax.swing.JComboBox buscarBox;
    private javax.swing.JTextField busquedaClientesTxt;
    private javax.swing.JTextField celularTxt;
    private javax.swing.JComboBox ciudadBox;
    private javax.swing.JTextField cuitTxt;
    private javax.swing.JTextField direccionTxt;
    private javax.swing.JLabel encontradosLbl;
    private javax.swing.JTextField idTxt;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField mailTxt;
    private javax.swing.JTextField nombreTxt;
    private javax.swing.JComboBox razonBox;
    private javax.swing.JTextField telTxt;
    private javax.swing.JComboBox tipoClienteBox;
    private javax.swing.JComboBox zonaBox;
    // End of variables declaration//GEN-END:variables
}
