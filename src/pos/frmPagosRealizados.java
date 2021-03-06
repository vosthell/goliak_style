/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmPagosRealizados.java
 *
 * Created on 05-mar-2012, 9:46:37
 */
package pos;

import clases.clsCaja;
import clases.clsPago;
import clases.clsReporte;
import clases.clsUtils;
import index.main;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CKaiser
 */
public class frmPagosRealizados extends javax.swing.JInternalFrame {
    MiModelo dtmData = new MiModelo();
    clsReporte objReporte = new clsReporte(); 
    clsCaja objCaja = new clsCaja();
    clsPago objPago = new clsPago();
    clsUtils objUtils = new clsUtils();
    String idCajaAbierta = "";
    /** Creates new form frmPagosRealizados */
    public frmPagosRealizados() {
        initComponents();
        Double total = 0.00;
        
        dtmData.addColumn("N°");/*.setPreferredWidth(500)*/
        dtmData.addColumn("Referencia");
        dtmData.addColumn("Fecha");
        dtmData.addColumn("Valor");
        dtmData.addColumn("idCabeceraMovi");
        //ALINEAR COLUMNA
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tblData.getColumnModel().getColumn(3).setCellRenderer(tcr);
        
        //OCULTAR
        objUtils.setOcultarColumnasJTable(this.tblData, new int[]{4});
        
        //idCajaAbierta
        ArrayList<clases.clsCaja> dataCaja = objCaja.consultarDataCajaAbierta(main.idUser);
        idCajaAbierta = dataCaja.get(0).getIdCajaOperacion(); 
        
        ArrayList<clsPago> dataPagos = objPago.consultaPagosRealizadas(idCajaAbierta); 
        int maxData = dataPagos.size();
        for(int i=0;i<maxData;i++)
        {
             Object[] nuevaFila = {i+1,                                     
                                    dataPagos.get(i).getReferencia(),
                                    dataPagos.get(i).getFechaPago().substring(0, 16),
                                    dataPagos.get(i).getValor(),
                                    dataPagos.get(i).getIdCabeceraMovi()                                   
             };       
             total = total + dataPagos.get(i).getValor();
             dtmData.addRow(nuevaFila); 
        } 
        txtTotal.setText(""+objUtils.redondear(total));
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
        jLabel1 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmPagosRealizados.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblData.setModel(dtmData);
        tblData.setName("tblData"); // NOI18N
        tblData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblData);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtTotal.setFont(resourceMap.getFont("txtTotal.font")); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText(resourceMap.getString("txtTotal.text")); // NOI18N
        txtTotal.setName("txtTotal"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(440, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(23, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(355, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(78, 78, 78)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(105, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
    int fila = tblData.rowAtPoint(evt.getPoint());
    int columna = tblData.columnAtPoint(evt.getPoint());
    /*int columna = 3;*/
    /*if ((fila > -1) && (columna > -1))*/
    int i = tblData.getSelectedRow();
    int idCabecera = Integer.parseInt(""+tblData.getValueAt(i, 4));
    //System.out.println("x:" + idCabecera + " " + fila + " " + columna);
    objReporte.ejecutarReporteParametroInt(idCabecera, "rptPagosFactura");    
}//GEN-LAST:event_tblDataMouseClicked
    
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
