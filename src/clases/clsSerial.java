/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 *
 * @author CKaiser
 */
public class clsSerial {
    String sql;
    clsConexion bd = new clsConexion(); 
    public static String getSerialNumber(String drive) {
  String result = "";
    try {
      File file = File.createTempFile("realhowto",".vbs");
      file.deleteOnExit();
      FileWriter fw = new java.io.FileWriter(file);

      String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                  +"Set colDrives = objFSO.Drives\n"
                  +"Set objDrive = colDrives.item(\"" + drive + "\")\n"
                  +"Wscript.Echo objDrive.SerialNumber";  // see note
      fw.write(vbs);
      fw.close();
      Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
      BufferedReader input =
        new BufferedReader
          (new InputStreamReader(p.getInputStream()));
      String line;
      while ((line = input.readLine()) != null) {
         result += line;
      }
      input.close();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    return result.trim();
  }
    
    public static String getMotherboardSN() {
  String result = "";
    try {
      File file = File.createTempFile("realhowto",".vbs");
      file.deleteOnExit();
      FileWriter fw = new java.io.FileWriter(file);

      String vbs =
         "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
        + "Set colItems = objWMIService.ExecQuery _ \n"
        + "   (\"Select * from Win32_BaseBoard\") \n"
        + "For Each objItem in colItems \n"
        + "    Wscript.Echo objItem.SerialNumber \n"
        + "    exit for  ' do the first cpu only! \n"
        + "Next \n";

      fw.write(vbs);
      fw.close();
      Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
      BufferedReader input =
        new BufferedReader
          (new InputStreamReader(p.getInputStream()));
      String line;
      while ((line = input.readLine()) != null) {
         result += line;
      }
      input.close();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    return result.trim();
  }

     //Codificación
    public String codif(String cad) {
            String clue="";
            for(int i=0; i<cad.length(); i++) {
                    for(int j=0; j<256; j++) {
                            if(cad.charAt(i)==(char)j) {
                                    clue+=((char)(j+5));
                            }
                    }
            }
            return clue;
    }

    //Decodificación
    public String decodif(String cad) {
            String clue="";
            for(int i=0; i<cad.length(); i++) {
                    for(int j=0; j<256; j++) {
                            if(cad.charAt(i)==(char)j) {
                                    clue+=((char)(j-5));
                            }
                    }
            }
            return clue;
    }
    
    public boolean insertarRegistro(String codigo1, String codigo2)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_licencia(codigo_licencia, fecha_registro, estado, codigo_licencia_2)"                   
                    + " VALUES('" + codigo1+"', NOW(), 'A', '" + codigo2 + "')";           
            //System.out.println("SQL enviado:" + sql);
            bd.sentencia.executeUpdate(sql);
            exito = true; 
        }
        catch(SQLException e) //Captura posible error de SQL
        {
            System.out.println("Error SQL:" + e);
            exito = false;
        } 
        bd.desconectarBaseDeDatos();
        return exito;
    } 
    
    public boolean eliminarRegistro(String codigo1, String codigo2)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "DELETE FROM ck_licencia"                   
                    + " WHERE codigo_licencia = '" + codigo1+"'"
                    + " AND substr(codigo_licencia_2, 2, LENGTH(codigo_licencia)+1) = '" + codigo2.substring(1, codigo2.length()) + "'";           
            System.out.println("SQL enviado:" + sql);
            bd.sentencia.executeUpdate(sql);
            exito = true; 
        }
        catch(SQLException e) //Captura posible error de SQL
        {
            System.out.println("Error SQL:" + e);
            exito = false;
        } 
        bd.desconectarBaseDeDatos();
        return exito;
    } 
}
