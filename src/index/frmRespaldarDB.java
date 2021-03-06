/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmRespaldarDB.java
 *
 * Created on 26-mar-2012, 10:42:01
 */
package index;

import clases.clsUtils;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.*;
import javax.swing.JOptionPane;
/**
 *
 * @author CKaiser
 */
public class frmRespaldarDB extends javax.swing.JInternalFrame {
    clsUtils objUtils = new clsUtils();
    /** Creates new form frmRespaldarDB */
    public frmRespaldarDB() {
        initComponents();
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
        txtRuta = new javax.swing.JTextField();
        btnAbrir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stinventario.STInventarioApp.class).getContext().getResourceMap(frmRespaldarDB.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setToolTipText(resourceMap.getString("jPanel1.toolTipText")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtRuta.setEditable(false);
        txtRuta.setName("txtRuta"); // NOI18N

        btnAbrir.setIcon(resourceMap.getIcon("btnAbrir.icon")); // NOI18N
        btnAbrir.setText(resourceMap.getString("btnAbrir.text")); // NOI18N
        btnAbrir.setName("btnAbrir"); // NOI18N
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(btnAbrir)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrir)
                    .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(61, 61, 61))
        );

        btnGuardar.setText(resourceMap.getString("btnGuardar.text")); // NOI18N
        btnGuardar.setEnabled(false);
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        // TODO add your handling code here:
        File nombrearchivo = new File("");
        JFileChooser selector = new JFileChooser();
        selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        selector.setDialogTitle("Escoger carpeta donde guardar");
        selector.setApproveButtonText("Enviar");
        //FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de PDF (.pdf)", "pdf");
        //selector.setFileFilter(filter);
        int returnVal = selector.showOpenDialog(this);
        if (returnVal==JFileChooser.CANCEL_OPTION)
        {    return;}
        if (returnVal==JFileChooser.APPROVE_OPTION)
        {
            nombrearchivo = selector.getSelectedFile();
            String nom = nombrearchivo.getPath();
            txtRuta.setText(nom); 
            if(txtRuta.getText().equals(""))
            {
               btnGuardar.setEnabled(false);
            }
            else
            {
                btnGuardar.setEnabled(true);
            }                  
        }              
}//GEN-LAST:event_btnAbrirActionPerformed

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    
        File archivoBat = new File("c:/" + objUtils.batRespaldar + ".bat");
    
        try
        {
            PrintWriter pw = new PrintWriter (new BufferedWriter(new FileWriter(archivoBat)));                

            pw.println("@echo off ");        
            pw.println("set pgpassword=" + objUtils.passBD);        
            pw.println("set FECHA=%DATE% %TIME%");        
            pw.println("set FECHA=%FECHA:/=%");        
            pw.println("set FECHA=%FECHA::=-%");        
            pw.println("set FECHA=%FECHA:.=-%");        
            //pw.println("\"\\archivos de programa\\postgresql\\8.4\\bin\\\"pg_dump -U postgres -f \"c:\\respaldos\\resp%FECHA%.sql\" " + objUtils.nameBD);        
            pw.println("\"\\archivos de programa\\postgresql\\8.4\\bin\\\"pg_dump -U postgres -f \"" + txtRuta.getText() + "\\resp%FECHA%.sql\" " + objUtils.nameBD);        

            pw.close();
        }
        catch (IOException io)
        {
            //JOptionPane.showMessageDialog(this,"Error al Abrir el ejecutar" + io);
        }
        try
        {

            String auxiliar;
            Process p;
            InputStream is;
            BufferedReader br;                    
            p = Runtime.getRuntime().exec("cmd.exe /c c:/" + objUtils.batRespaldar + ".bat");

            is = p.getInputStream();
            br = new BufferedReader (new InputStreamReader(is));
            while ((auxiliar = br.readLine()) != null)
            {
                System.out.println(auxiliar);
            }                 
        }
        catch (Exception e)
        {
           JOptionPane.showMessageDialog(this ,"Error al Abrir el ejecutar" + e);
        } 
        //archivoBat.delete();   
        JOptionPane.showMessageDialog(this,"Base de Datos respaldada correctamente");    
        
}//GEN-LAST:event_btnGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JTextField txtRuta;
    // End of variables declaration//GEN-END:variables
}
