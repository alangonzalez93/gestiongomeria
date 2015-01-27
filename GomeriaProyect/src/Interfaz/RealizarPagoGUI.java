/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alan
 */
public class RealizarPagoGUI extends javax.swing.JFrame {

    /**
     * Creates new form RealizarPagoGUI
     */
    private DefaultTableModel cuotasTableDefault;
    
    public RealizarPagoGUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        cuotasTableDefault = (DefaultTableModel) CuotasTable.getModel();
        
    }

    public void setActionListener(ActionListener lis) {
        BtnPagarCuota.addActionListener(lis);
        BtnCancelar.addActionListener(lis);
    }
    public DefaultTableModel getCuotasTableDefault() {
        return cuotasTableDefault;
    }

    
    public JButton getBtnCancelar() {
        return BtnCancelar;
    }

    public JButton getBtnPagarCuota() {
        return BtnPagarCuota;
    }

    public JTable getCuotasTable() {
        return CuotasTable;
    }

    public JLabel getLblFormaDePago() {
        return LblFormaDePago;
    }

    public JLabel getLblNombre() {
        return LblNombre;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnPagarCuota = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        CuotasTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        LblNombre = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LblFormaDePago = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setTitle("Cuotas");
        setAlwaysOnTop(true);
        setResizable(false);

        BtnPagarCuota.setText("Pagar cuota seleccionada");

        BtnCancelar.setText("Cancelar");

        CuotasTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha a pagar", "Fecha pagada", "Estado", "Monto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        CuotasTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(CuotasTable);

        jLabel1.setText("Venta realizada a: ");

        LblNombre.setText("Nombre Apellido");

        jLabel3.setText("Forma de pago: ");

        LblFormaDePago.setText("Forma Pago");

        jLabel5.setText("Cuotas:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblNombre))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblFormaDePago))
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(BtnPagarCuota)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnCancelar)
                .addGap(132, 132, 132))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(LblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(LblFormaDePago))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnPagarCuota)
                    .addComponent(BtnCancelar))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnPagarCuota;
    private javax.swing.JTable CuotasTable;
    private javax.swing.JLabel LblFormaDePago;
    private javax.swing.JLabel LblNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
