/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CKaiser
 */
public class clsUtils {
    public String version = "0.02 (2014.09.10.14.18)";
    //MENSAJE CUANDO NO TIENE PERMISOS PARA ABRIR FORMULARIO
    public String msgSinPermisosFormulario = "USTED NO TIENE PERMISOS PARA ABRIR ESTE FORMULARIO.";
    public String msgTitleSinPermisos = "Atención!";
    
    //HOSPEDAJE DE ARCHIVOS
    public String HostSystem = "C:/goliak/";
    //public String HostSystem = obtenerHospedaje();
    
     //HOSPEDAJE DE REPORTES
    public String HostSystemReportes = HostSystem + "reportes/";
    //NOMBRE DEL SISTEMA
    public String nombreSistema = "Goliak - ";
    public String nombreLargoSistema = "Goliak - Sistema Integrado de Información Empresarial";
    //USUARIO
    public String userBD = "postgres";
    //CLAVE
    public String passBD = "majcp071102kaiser";
    //BD
    public String nameBD = "GOLIAK";
    //TITULO DE  LA VENTANA
    public String tituloVentanaMensaje = "Atención!";
    //ERROR TEXT VACIOS
    public String camposVacios = "Ingrese correctamente la información";
    //Datos Guardados con exito
    public String exitoGuardar = "Datos almacenados con éxito";
    //Error al guardar información
    public String errorGuardar = "Error al guardar información";
    //ARCHIVO RESPALDAR
    public String batRespaldar = "respaldarGoliak";
    //VERIFICAR SI ES DOUBLE
    public boolean isDouble(String cadena){
        try {
            Double.parseDouble(cadena);
            return true;
        }
        catch (NumberFormatException nfe){
            return false;
        } 
    }
    
    //VERIFICAR SI ES INTEGER
    public boolean isEntero(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        }
        catch (NumberFormatException nfe){
            return false;
        } 
    }
    
    //REDONDEAR 1 2 DECIMALES UN NUMERO
    public double redondear(double numero)
    {
        /*numero = numero*100;
        numero = (int) numero;
        numero = (numero/100);*/
        
        Locale.setDefault(Locale.ENGLISH);       
        DecimalFormat formateador = new DecimalFormat("####.##");
        
        return Double.parseDouble(formateador.format(numero));            
    } 
    
    //LIMPIAR JTABLE
    public void limpiarJTable(DefaultTableModel dtmData){       
         int contRows = dtmData.getRowCount();
            for (int i = 0; i < contRows; i++){
                dtmData.removeRow(0);
            }   
    }
    
    //OCULTAR COLUMNAS DE UNA TABLA
    public void setOcultarColumnasJTable(JTable tbl, int columna[])
    {
        for(int i=0;i<columna.length;i++)
        {
            tbl.getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getColumnModel().getColumn(columna[i]).setMinWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMinWidth(0);
        }
    }
    
    //ENUMERAR LAS FILAS  DE UNA TABLA CON SU MODELO
    public void enumerarFilas(DefaultTableModel p_dtmData, int columna)
    {
        int maxData = p_dtmData.getRowCount();
        for(int i=0; i<maxData; i++)
        {
            p_dtmData.setValueAt(i+1, i, columna);
        }
    }
    
    //VACIAR DATOS DE UNA TABLA
    public void vaciarTabla(DefaultTableModel p_dtmData)
    {
        int contRows = p_dtmData.getRowCount();
        for (int i = 0; i < contRows; i++)
        {
            p_dtmData.removeRow(0);
        }  
    }
    
    public boolean isEmail(String correo) 
    {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            System.out.println("[" + mat.group() + "]");
            return true;
        }else{
            return false;
        }
    }   
     
    public String obtenerHospedaje()
    {
        String host ="";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            //archivo = new File ("C:\\rpt\\server.txt");
            archivo = new File("C:/server.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            int i=0;
            while((linea=br.readLine())!=null)
            {
                if(i==1)
                    host = linea.toString();  
                i = i+1;
                //System.out.println(server);
            }
            System.out.println(host);
            return host;
        }
        catch(Exception e){
            e.printStackTrace();
            return "";
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try{                    
                if( null != fr ){   
                    fr.close();     
                }                  
            }catch (Exception e2){ 
                e2.printStackTrace();
            }
        }
    }
    
    //Me ayuda a alinear texto de valores a la derecha de la impresion en el POS
    public String rellenar(String data)
    {
        String data2 = "";
        if(data.length()<5)
        {    data2 = "   " + data;}
        if(data.length()==5)
        {     data2 = "  " + data;}
        if(data.length()==6)
        {    data2 = " " + data;}
        if(data.length()>6)
        {    data2 = data;}
        return data2;
    }
}
