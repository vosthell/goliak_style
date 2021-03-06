/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmPagoAdd.java
 *
 * Created on 08-nov-2011, 10:25:57
 */
package pago;

import clases.clsAuditoria;
import clases.clsCaja;
import clases.clsCliente;
import clases.clsCtasCobrar;
import clases.clsPago;
import clases.clsReporte;
import clases.clsUtils;
import com.jidesoft.hints.ListDataIntelliHints;
import com.jidesoft.swing.SelectAllUtils;
import index.main;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pos.frmFactHistoShow;
import pos.frmListClientes;

/**
 *
 * @author CKaiser
 */
public class frmPagoAdd extends javax.swing.JDialog {
    clsCliente objCliente = new clsCliente();
    clsCtasCobrar objCtasCobrar = new clsCtasCobrar();
    clsPago objPago = new clsPago();
    clsAuditoria objAuditoria = new clsAuditoria();
    clsUtils objUtils = new clsUtils();
    clsReporte objReporte = new clsReporte();
    clsCaja objCaja = new clsCaja();
    
    public static String codigoCliente;
    String idCajaAbierta = "";
    Double saldoActual;
    /** Creates new form frmPagoAdd */
    MiModelo dtmData = new MiModelo();
    
    public frmPagoAdd(javax.swing.JFrame parent, boolean modal) {
         super(parent, modal);
         initComponents();
        //OBTENER IDCAJAOPERACION ACTUAL, OSEA QUE  NO ESTA CERRADA
        idCajaAbierta = objCaja.obtenerCajaAbierta(main.idUser);
        //OBTENER CAJERO
        String cajero = objCaja.obtenerCajero(main.idUser);
        lblCajero.setText(cajero);
        
        dtmData.addColumn("Nº");        
        dtmData.addColumn("Factura Referencia Monica");
        dtmData.addColumn("Descripcion");
        dtmData.addColumn("Valor");
        dtmData.addColumn("Saldo");
        dtmData.addColumn("idCtaCobrar");
        dtmData.addColumn("idCabecera");
        
        List<String> dataCedula = objCliente.consultarCedulas(); 
        SelectAllUtils.install(txtCedula);
        ListDataIntelliHints intellihints = new ListDataIntelliHints(txtCedula, dataCedula);
        intellihints.setCaseSensitive(false);
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActual = new Date();
        lblFechaActual.setText(""+df.format(fechaActual));        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtCedula = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblFechaActual = new javax.swing.JLabel();
        btnBuscarCliente = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtIdCuentaCobrar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnVerPagos = new javax.swing.JButton();
        btnVerFactura = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtValor = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtReferencia = new javax.swing.JTextArea();
        btnGuardarPago = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        lblCajero = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmPagoAdd.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtCedula.setText(resourceMap.getString("txtCedula.text")); // NOI18N
        txtCedula.setName("txtCedula"); // NOI18N
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        txtNombreCliente.setEditable(false);
        txtNombreCliente.setText(resourceMap.getString("txtNombreCliente.text")); // NOI18N
        txtNombreCliente.setName("txtNombreCliente"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        lblFechaActual.setText(resourceMap.getString("lblFechaActual.text")); // NOI18N
        lblFechaActual.setName("lblFechaActual"); // NOI18N

        btnBuscarCliente.setText(resourceMap.getString("btnBuscarCliente.text")); // NOI18N
        btnBuscarCliente.setName("btnBuscarCliente"); // NOI18N
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCedula)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarCliente))
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(lblFechaActual, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(lblFechaActual)
                    .addComponent(jLabel3)
                    .addComponent(btnBuscarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblData.setModel(dtmData);
        tblData.setName("tblData"); // NOI18N
        tblData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblData);

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txtIdCuentaCobrar.setEditable(false);
        txtIdCuentaCobrar.setText(resourceMap.getString("txtIdCuentaCobrar.text")); // NOI18N
        txtIdCuentaCobrar.setName("txtIdCuentaCobrar"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        txtDescripcion.setColumns(20);
        txtDescripcion.setEditable(false);
        txtDescripcion.setRows(5);
        txtDescripcion.setName("txtDescripcion"); // NOI18N
        jScrollPane2.setViewportView(txtDescripcion);

        btnVerPagos.setIcon(resourceMap.getIcon("btnVerPagos.icon")); // NOI18N
        btnVerPagos.setText(resourceMap.getString("btnVerPagos.text")); // NOI18N
        btnVerPagos.setToolTipText(resourceMap.getString("btnVerPagos.toolTipText")); // NOI18N
        btnVerPagos.setActionCommand(resourceMap.getString("btnVerPagos.actionCommand")); // NOI18N
        btnVerPagos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnVerPagos.setName("btnVerPagos"); // NOI18N
        btnVerPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerPagosActionPerformed(evt);
            }
        });

        btnVerFactura.setIcon(resourceMap.getIcon("btnVerFactura.icon")); // NOI18N
        btnVerFactura.setText(resourceMap.getString("btnVerFactura.text")); // NOI18N
        btnVerFactura.setToolTipText(resourceMap.getString("btnVerFactura.toolTipText")); // NOI18N
        btnVerFactura.setActionCommand(resourceMap.getString("btnVerFactura.actionCommand")); // NOI18N
        btnVerFactura.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnVerFactura.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnVerFactura.setName("btnVerFactura"); // NOI18N
        btnVerFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerFacturaActionPerformed(evt);
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
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(23, 23, 23)
                        .addComponent(txtIdCuentaCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnVerPagos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVerFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIdCuentaCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7))
                            .addComponent(btnVerFactura))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVerPagos))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel3.border.title"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        txtValor.setBackground(resourceMap.getColor("txtValor.background")); // NOI18N
        txtValor.setEditable(false);
        txtValor.setFont(resourceMap.getFont("txtValor.font")); // NOI18N
        txtValor.setText(resourceMap.getString("txtValor.text")); // NOI18N
        txtValor.setName("txtValor"); // NOI18N
        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorKeyTyped(evt);
            }
        });

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        txtReferencia.setColumns(20);
        txtReferencia.setEditable(false);
        txtReferencia.setRows(5);
        txtReferencia.setName("txtReferencia"); // NOI18N
        txtReferencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtReferenciaKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(txtReferencia);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        btnGuardarPago.setIcon(resourceMap.getIcon("btnGuardarPago.icon")); // NOI18N
        btnGuardarPago.setText(resourceMap.getString("btnGuardarPago.text")); // NOI18N
        btnGuardarPago.setName("btnGuardarPago"); // NOI18N
        btnGuardarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPagoActionPerformed(evt);
            }
        });

        jLabel10.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        lblCajero.setFont(resourceMap.getFont("lblCajero.font")); // NOI18N
        lblCajero.setText(resourceMap.getString("lblCajero.text")); // NOI18N
        lblCajero.setName("lblCajero"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCajero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 533, Short.MAX_VALUE)
                        .addComponent(btnGuardarPago))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(lblCajero))
                    .addComponent(btnGuardarPago))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public class MiModelo extends DefaultTableModel
    {
            @Override
       public boolean isCellEditable (int row, int column)
       {
           // Aquí devolvemos true o false según queramos que una celda
           // identificada por fila,columna (row,column), sea o no editable
          /* if (column == 3)
              return true;*/
           
           return false;
       }
    } 
private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
     int fila = tblData.rowAtPoint(evt.getPoint());
     //int columna = tblData.columnAtPoint(evt.getPoint());
     
     txtIdCuentaCobrar.setText(""+tblData.getValueAt(fila,5));
     txtDescripcion.setText(""+tblData.getValueAt(fila,2));
     saldoActual = Double.parseDouble(""+tblData.getValueAt(fila,4));
     txtValor.setEditable(true);
     txtReferencia.setEditable(true);
     txtValor.requestFocus();
}//GEN-LAST:event_tblDataMouseClicked

private void btnGuardarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPagoActionPerformed
    String idCtaCobrar = txtIdCuentaCobrar.getText();
    String factRef = txtReferencia.getText();
    
    if(txtNombreCliente.getText().equals("")||idCtaCobrar.equals("")||txtValor.getText().equals("")||factRef.equals(""))
    {
       JOptionPane.showMessageDialog(this, "Ingrese correctamente la información", "Atención!", JOptionPane.ERROR_MESSAGE);
    }
    else
    {
        Double valor = Double.parseDouble(txtValor.getText());
        Double diferencia;
        diferencia = saldoActual - valor;
        if(diferencia<0)
        {
            JOptionPane.showMessageDialog(this, "Valor del pago excede saldo de la deuda", "Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            //INSERTAR PAGO
            objPago.insertarRegistro(idCtaCobrar, main.idUser, factRef, ""+objUtils.redondear(valor), idCajaAbierta);
            //ACTUALIZAR SALDO
            objCtasCobrar.actualizarSaldo(idCtaCobrar, objUtils.redondear(valor));
            //OBTENER SALDO SI SALDO ES  CERO CANCELO DEUDA
            String saldo = objCtasCobrar.consultarSaldoCta(idCtaCobrar);
            if(saldo.equals("0")||saldo.equals("0.00"))
            {
                //REGISTRAR QUE FECHA QUEDO CANCELADO O EN CERO
                objCtasCobrar.registrarCancelacion(idCtaCobrar);
            }
            JOptionPane.showMessageDialog(this, "Pago ingresado con éxito", "Atención!", JOptionPane.INFORMATION_MESSAGE);
            //AUDITORIA
            objAuditoria.insertarAuditoria("frmPagoAdd", "INGRESO DEL PAGO POR LA  CUENTA: "+txtIdCuentaCobrar +"-"+txtDescripcion.getText().toString(), "3");
            //dispose();
            txtValor.setText("");
            txtReferencia.setText("");
            txtIdCuentaCobrar.setText("");
            txtDescripcion.setText("");
            objUtils.limpiarJTable(dtmData);
            llenarTablaDeudas();                 
        }
    }
}//GEN-LAST:event_btnGuardarPagoActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    frmListClientes ventana = new frmListClientes(null, true, "3");
    //new inventariopdf.JDEscoger(this, true);
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
}//GEN-LAST:event_jButton1ActionPerformed

private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
  ArrayList<clsCliente> dataCliente = objCliente.consultarDataCliente(txtCedula.getText().toString());
    if(dataCliente.isEmpty())
    {
        JOptionPane.showMessageDialog(this, "Cédula no existe!!!", "Atención!", JOptionPane.ERROR_MESSAGE);
    }
    else
    {
        objUtils.limpiarJTable(dtmData);
        this.txtNombreCliente.setText(dataCliente.get(0).getNameCompleto());
        //BUSCAR LAS CUENTAS POR COBRAR
        codigoCliente = ""+dataCliente.get(0).getCodigo();
        llenarTablaDeudas();        
    }
}//GEN-LAST:event_btnBuscarClienteActionPerformed

    void llenarTablaDeudas()
    {
        ArrayList<clsCtasCobrar> dataCtasCobrar = objCtasCobrar.consultaCtasCobrar(codigoCliente); 
        Double valor;
        Double valorActual;
        int maxData = dataCtasCobrar.size();
        if(maxData==0)
        {
            JOptionPane.showMessageDialog(this, "No tiene cuentas por cobrar", "Atención!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            for(int i=0;i<maxData;i++)
            {
                 valor = Double.parseDouble(dataCtasCobrar.get(i).getValor());
                 valorActual = Double.parseDouble(dataCtasCobrar.get(i).getValorActual());

                 Object[] nuevaFila = {i+1, 
                                        dataCtasCobrar.get(i).getFactReferencia(), 
                                        dataCtasCobrar.get(i).getDescripcion(), 
                                        objUtils.redondear(valor), 
                                        objUtils.redondear(valorActual),
                                        dataCtasCobrar.get(i).getIdCtaCobrar(),
                                        dataCtasCobrar.get(i).getIdCabeceraMovi()};
                 dtmData.addRow(nuevaFila); 

            }   
        }
    }

private void btnVerFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerFacturaActionPerformed
    int i = tblData.getSelectedRow();
    String idCabecera = ""+tblData.getValueAt(i,6);
    System.out.println(idCabecera);
    frmFactHistoShow ventana = new frmFactHistoShow(null, true, idCabecera);
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
}//GEN-LAST:event_btnVerFacturaActionPerformed

private void btnVerPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPagosActionPerformed
    int i = tblData.getSelectedRow();
    int idCabecera = Integer.parseInt(""+tblData.getValueAt(i,6));
    objReporte.ejecutarReporteParametroInt(idCabecera, "rptPagosFactura");
    
}//GEN-LAST:event_btnVerPagosActionPerformed

private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
    if(evt.getKeyChar() == KeyEvent.VK_ENTER){
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            manager.focusNextComponent();  
    }
}//GEN-LAST:event_txtCedulaKeyTyped

private void txtValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyTyped
    if(evt.getKeyChar() == KeyEvent.VK_ENTER){
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            manager.focusNextComponent();  
    }
}//GEN-LAST:event_txtValorKeyTyped

private void txtReferenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReferenciaKeyTyped
    if(evt.getKeyChar() == KeyEvent.VK_ENTER){
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            manager.focusNextComponent();  
    }
}//GEN-LAST:event_txtReferenciaKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnGuardarPago;
    private javax.swing.JButton btnVerFactura;
    private javax.swing.JButton btnVerPagos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCajero;
    private javax.swing.JLabel lblFechaActual;
    private javax.swing.JTable tblData;
    public static javax.swing.JTextField txtCedula;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtIdCuentaCobrar;
    public static javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextArea txtReferencia;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
