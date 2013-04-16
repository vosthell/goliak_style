/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Kaiser
 */
public class clsReporte {
    clsConexion objConexion = new clsConexion();
    clsUtils objUtils = new clsUtils();    
    String server = objConexion.obtenerServer();
    Connection conn = null;  
    //DIRECCION DEL REPORTE
    String direccion = objUtils.HostSystemReportes;
    
    public clsReporte() {
        try
            {
                Class.forName("org.postgresql.Driver"); //se carga el driver
                conn= DriverManager.getConnection("jdbc:postgresql://" + server + "/"+objUtils.nameBD, objUtils.userBD, objUtils.passBD);
                //JOptionPane.showMessageDialog(null,"Conexiónestablecida");                
            }
            catch(Exception ex)
            {
                //ex.printStackTrace();
            }
    }
    
    public void ejecutarReporte(String nameReporte){ 
        try{ 
            String archivo = direccion + nameReporte + ".jasper"; 
            System.out.println("Cargando desde: " + archivo);        
            if(archivo == null){ 
               // System.out.println("No se encuentra el archivo.");
                JOptionPane.showMessageDialog(null, "No se encuentra el archivo.");                    
                
                System.exit(2);
            } 
            JasperReport masterReport= null;
            try {
                masterReport= (JasperReport) JRLoader.loadObject(archivo);
            } catch (JRException e) {
                System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                System.exit(3);
            }
            //este es el parámetro, se pueden agregar más parámetros
            //basta con poner mas parametro.put
            HashMap parametro= new HashMap();
            Locale locale = new Locale("en", "EC");
            parametro.put(JRParameter.REPORT_LOCALE, locale);
            //parametro.put("id_anticipo", idAnticipo);
            //Reporte diseñado y compilado con iReport
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, conn);
            //Se lanza el Viewerde Jasper, no termina aplicación al salir
            JasperViewer jviewer= new JasperViewer(jasperPrint,false);
            //stinventario.frmPrincipal.jDesktopPane1.add(jviewer);           
            jviewer.setTitle(objUtils.nombreSistema + "Reporte");
            jviewer.setExtendedState(jviewer.MAXIMIZED_BOTH);
            jviewer.setVisible(true);      
        }catch(Exception j){
            System.out.println("Mensaje de Error:"+j.getMessage());
        }
    }
    
     public void ejecutarReporteParametroInt(int param1, String nameReporte){ 
        try{ 
            String archivo = direccion + nameReporte + ".jasper"; 
            System.out.println("Cargando desde: " + archivo);        
            if(archivo == null){ 
               // System.out.println("No se encuentra el archivo.");
                JOptionPane.showMessageDialog(null, "No se encuentra el archivo.");                    
                
                System.exit(2);
            } 
            JasperReport masterReport= null;
            try {
                masterReport= (JasperReport) JRLoader.loadObject(archivo);
            } catch (JRException e) {
                System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                System.exit(3);
            }
            //este es el parámetro, se pueden agregar más parámetros
            //basta con poner mas parametro.put
            Map parametro= new HashMap();
            Locale locale = new Locale("en", "EC");
            parametro.put(JRParameter.REPORT_LOCALE, locale);
            parametro.put("parametro", param1);
            //Reporte diseñado y compilado con iReport
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport,parametro,conn);
            //Se lanza el Viewerde Jasper, no termina aplicación al salir
            JasperViewer jviewer= new JasperViewer(jasperPrint,false);
            jviewer.setTitle(objUtils.nombreSistema + "Reporte");
            jviewer.setExtendedState(jviewer.MAXIMIZED_BOTH);
            jviewer.setVisible(true);
        }catch(Exception j){
            System.out.println("Mensaje de Error:"+j.getMessage());
        }
    }
     
     public void ejecutarReporte2ParametrosInt(int param1, int param2, String nameReporte){ 
        try{ 
            String archivo = direccion + nameReporte + ".jasper"; 
            System.out.println("Cargando desde: " + archivo);        
            if(archivo == null){ 
               // System.out.println("No se encuentra el archivo.");
                JOptionPane.showMessageDialog(null, "No se encuentra el archivo.");                    
                
                System.exit(2);
            } 
            JasperReport masterReport= null;
            try {
                masterReport= (JasperReport) JRLoader.loadObject(archivo);
            } catch (JRException e) {
                System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                System.exit(3);
            }
            //este es el parámetro, se pueden agregar más parámetros
            //basta con poner mas parametro.put
            Map parametro= new HashMap();
            Locale locale = new Locale("en", "EC");
            parametro.put(JRParameter.REPORT_LOCALE, locale);
            parametro.put("parametro", param1);
            parametro.put("parametro2", param2);
            //Reporte diseñado y compilado con iReport
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport,parametro,conn);
            //Se lanza el Viewerde Jasper, no termina aplicación al salir
            JasperViewer jviewer= new JasperViewer(jasperPrint,false);
            jviewer.setTitle(objUtils.nombreSistema + "Reporte");
            jviewer.setExtendedState(jviewer.MAXIMIZED_BOTH);
            jviewer.setVisible(true);
        }catch(Exception j){
            System.out.println("Mensaje de Error:"+j.getMessage());
        }
    }
}
