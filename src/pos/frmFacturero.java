/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmFacturero.java
 *
 * Created on 05-abr-2012, 9:48:47
 */
package pos;

import clases.clsAuditoria;
import clases.clsFacturero;
import clases.clsUtils;
import clases.colorFilaTable;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CKaiser
 */
public class frmFacturero extends javax.swing.JInternalFrame {
    MiModelo dtmData = new MiModelo();
    clsFacturero objFacturero = new clsFacturero();
    clsAuditoria objAuditoria = new clsAuditoria();
    clsUtils objUtils = new clsUtils();
    /** Creates new form frmFacturero */
    public frmFacturero() {
        initComponents();
        
        dtmData.addColumn("N°");/*.setPreferredWidth(500)*/
        dtmData.addColumn("Codigo facturero");
        dtmData.addColumn("Secuencia Inicio");
        dtmData.addColumn("Secuencia Fin");
        dtmData.addColumn("Secuencia Actual");
        dtmData.addColumn("Estado");
        
        llenarDatosFactureros("S", "S");
        tblData.setDefaultRenderer (Object.class, new colorFilaTable());/// tabla, es la que tienes en tu formulario 
    }
    
    private void llenarDatosFactureros(String mostrarActivos, String mostrarTerminados)
    {
        objUtils.vaciarTabla(dtmData);
        ArrayList<clsFacturero> dataFacturero = objFacturero.consultaDataFacturero(mostrarActivos, mostrarTerminados); 
        int maxData = dataFacturero.size();
        String descripcionEstado = "";
        for(int i=0;i<maxData;i++)
        {
            if(dataFacturero.get(i).getEstado().equals("A"))
                descripcionEstado = "Activo";
            else if(dataFacturero.get(i).getEstado().equals("T"))
                descripcionEstado = "Terminado";
            
            Object[] nuevaFila = {i+1,                                     
                                    dataFacturero.get(i).getIdFacturero(),                                    
                                    dataFacturero.get(i).getInicio(),
                                    dataFacturero.get(i).getFin(),
                                    dataFacturero.get(i).getActual(),
                                    descripcionEstado
             };       
             //total = total + dataEgresos.get(i).getCantidadEgreso();
             dtmData.addRow(nuevaFila); 
        } 
    }        
    
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
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        chkActivos = new javax.swing.JCheckBox();
        chkTerminados = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtSecuenciaInicio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSecuenciaFin = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmFacturero.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblData.setModel(dtmData);
        tblData.setName("tblData"); // NOI18N
        jScrollPane1.setViewportView(tblData);

        chkActivos.setSelected(true);
        chkActivos.setText(resourceMap.getString("chkActivos.text")); // NOI18N
        chkActivos.setName("chkActivos"); // NOI18N
        chkActivos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkActivosItemStateChanged(evt);
            }
        });

        chkTerminados.setForeground(resourceMap.getColor("chkTerminados.foreground")); // NOI18N
        chkTerminados.setSelected(true);
        chkTerminados.setText(resourceMap.getString("chkTerminados.text")); // NOI18N
        chkTerminados.setName("chkTerminados"); // NOI18N
        chkTerminados.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkTerminadosItemStateChanged(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtSecuenciaInicio.setText(resourceMap.getString("txtSecuenciaInicio.text")); // NOI18N
        txtSecuenciaInicio.setName("txtSecuenciaInicio"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtSecuenciaFin.setText(resourceMap.getString("txtSecuenciaFin.text")); // NOI18N
        txtSecuenciaFin.setName("txtSecuenciaFin"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtSecuenciaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSecuenciaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtSecuenciaFin, txtSecuenciaInicio});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSecuenciaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSecuenciaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(chkActivos)
                        .addGap(18, 18, 18)
                        .addComponent(chkTerminados))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkActivos)
                    .addComponent(chkTerminados))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    boolean exito;
    String sInicio = txtSecuenciaInicio.getText();
    String sFin = txtSecuenciaFin.getText();
    if(sInicio.equals("")||sFin.equals(""))
    {
        JOptionPane.showMessageDialog(this, "Verificar datos", "Atención!", JOptionPane.ERROR_MESSAGE);    
    }
    else if(Integer.parseInt(sInicio)>Integer.parseInt(sFin))
    {
        JOptionPane.showMessageDialog(this, "Orden de secuencia fin incorrecto", "Atención!", JOptionPane.ERROR_MESSAGE);    
    }
    else
    {
        exito = objFacturero.insertarRegistro(txtSecuenciaInicio.getText(), txtSecuenciaFin.getText());
        if (exito)
        {
            JOptionPane.showMessageDialog(this, objUtils.exitoGuardar, objUtils.tituloVentanaMensaje, JOptionPane.INFORMATION_MESSAGE);
            objAuditoria.insertarAuditoria("frmFacturero", "INGRESO DEL FACTURERO: ", "3"); 
            llenarDatosFactureros("S", "S");            
        } 
        
    }
}//GEN-LAST:event_jButton1ActionPerformed

private void chkActivosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkActivosItemStateChanged
    String activos;
    String terminados;
    if(this.chkActivos.isSelected())        
        activos = "S";
    else
        activos = "N";
    
    if(this.chkTerminados.isSelected())        
        terminados = "S";
    else
        terminados = "N";
    llenarDatosFactureros(activos, terminados);   
    
}//GEN-LAST:event_chkActivosItemStateChanged

private void chkTerminadosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkTerminadosItemStateChanged
    String activos;
    String terminados;
    if(this.chkActivos.isSelected())        
        activos = "S";
    else
        activos = "N";
    
    if(this.chkTerminados.isSelected())        
        terminados = "S";
    else
        terminados = "N";
    llenarDatosFactureros(activos, terminados);   
}//GEN-LAST:event_chkTerminadosItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkActivos;
    private javax.swing.JCheckBox chkTerminados;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtSecuenciaFin;
    private javax.swing.JTextField txtSecuenciaInicio;
    // End of variables declaration//GEN-END:variables
}