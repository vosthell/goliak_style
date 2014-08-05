/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.*;
import java.sql.*;

/**
 *
 * @author Kaiser
 */
public class clsConexion {
    clsUtils objUtils = new clsUtils();
    String url, url2;
    final String user;
    final String pass;
    private Connection conexion;
    public Statement sentencia;
    public ResultSet resultado;
    
    public clsConexion(){
        String server = obtenerServer();
        url = "jdbc:postgresql://" + server + "/" + objUtils.nameBD;
        user = objUtils.userBD;
        pass = objUtils.passBD;
    }
    
    public String obtenerServer(){
        String server ="";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        //System.out.println(objUtils.HostSystem + "server.txt");
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            //archivo = new File ("C:\\rpt\\server.txt");
            archivo = new File(objUtils.HostSystem + "server.txt");
            
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null)
            {
                server = linea.toString();  
                //System.out.println(server);
            }
            return server;
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
    
    public void conectarBaseDeDatos() {
        try {
            final String CONTROLADOR = "org.postgresql.Driver";
            Class.forName( CONTROLADOR );
            //System.getProperty( "user.dir" )+"/CarpetaBD/NombredelaBasedeDatos.mdb";
            conexion = DriverManager.getConnection(url, user, pass);
            sentencia = conexion.createStatement();
            /*JOptionPane.showMessageDialog(null, "si conecta");*/
        }
        catch (ClassNotFoundException ex1) {
            //ex1.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null,"Error Carga Driver." + ex1.getMessage());
            System.exit(1);
        }
        catch (SQLException ex) {
            //ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null,"Error Creacion Statement." + ex.getMessage());
            System.exit(1);
        }
    }  
  
    
    //AQUI VOYA  OBTENER EL SERVDUIOR QUEMADO EN UN ARCHIVO DE TEXTO
    public void conectarBaseDeDatos2() {
        try {
            final String CONTROLADOR = "org.postgresql.Driver";
            Class.forName( CONTROLADOR );
            //System.getProperty( "user.dir" )+"/CarpetaBD/NombredelaBasedeDatos.mdb";
            conexion = DriverManager.getConnection(url2, user, pass);
            sentencia = conexion.createStatement();
            /*JOptionPane.showMessageDialog(null, "si conecta");*/
        }
        catch (ClassNotFoundException ex1) {
            //ex1.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null,"Error Carga Driver." + ex1.getMessage());
            System.exit(1);
        }
        catch (SQLException ex) {
            //ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null,"Error Creacion Statement." + ex.getMessage()
                    + " / " + url2 + " / " + user +" / " + pass);
            System.exit(1);
        }
    }  
  
   
    public void conectarBDdinamic(String ip) {
        
        if(ip==null)
        {
            ip="192.168.8.231";
        }
        url = "jdbc:postgresql://" + ip + "/ventasalm";
        //javax.swing.JOptionPane.showMessageDialog(null, url);
        String estado;
        try {

            final String CONTROLADOR = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName( CONTROLADOR );
            //System.getProperty( "user.dir" )+"/CarpetaBD/NombredelaBasedeDatos.mdb";
            conexion = DriverManager.getConnection(url, user, pass);
            sentencia = conexion.createStatement();
            //estado="Conectado Correctamente";
        }
         catch (ClassNotFoundException ex1) {
            ex1.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null,"Error Carga Driver");
            estado="Servidor no esta disponible";
            //System.exit(1);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null,"Error Creacion Statement");
            estado="Servidor no esta disponible";
            //System.exit(1);
        }
        //return estado;
    }

   /** Cierra la conexion a la base de datos */

   public void desconectarBaseDeDatos() 
   {
       try {
            if (conexion != null ) {
                if(sentencia != null) {
                    sentencia.close();
                }
                conexion.close();
            }
        }
        catch (SQLException ex) {
            //ex.printStackTrace();
        }
   } 
}
