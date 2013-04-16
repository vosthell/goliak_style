/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import clases.clsConexion;
import index.main;
import java.sql.SQLException;

/**
 *
 * @author Kaiser
 */
public class clsUsuario {    
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private int id_usuario;
    private String nombre;
    
    public int getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }  
    
    public boolean consultarExistencia(String password)
    {
        boolean exito;
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT * "
                    + " FROM ck_usuario"
                    + " WHERE pass = md5('" + password + "')";                   
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
            int x=0;
            if(bd.resultado.next())
            {   
                do 
                { 
                    x++;
                }
                while(bd.resultado.next()); 
                if(x==0){
                    exito = false;
                }
                else
                {
                    exito = true;
                }           
            }
            else
            { 
                exito = false;
            }               
        }         
        catch(Exception ex)
        {
           System.out.print("ERROR EN SQL:"+ex);
           exito = false;
        }   
        bd.desconectarBaseDeDatos();
        return exito;
    }
    
    public ArrayList<clsUsuario> consultaDataUsuario(String password)
    {       
         ArrayList<clsUsuario> data = new ArrayList<clsUsuario>();  
         try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_usuario, a.name "
                    + " FROM ck_usuario AS a, ck_permisos AS b, ck_modulo AS c"
                    + " WHERE pass = md5('" + password + "')"
                    + " AND a.id_usuario=b.id_usuario"
                    + " AND b.id_modulo=c.id_modulo"
                    + " AND c.modulo_descripcion='POS'";
            //System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
             
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsUsuario oListaTemporal = new clsUsuario();
                    oListaTemporal.setIdUsuario(bd.resultado.getInt("id_usuario"));
                    oListaTemporal.setNombre(bd.resultado.getString("name"));
                    data.add(oListaTemporal);
                }
                while(bd.resultado.next()); 
                //return data;
            }
            else
            { 
                //data = null;
            }            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            data = null;
        }           
        bd.desconectarBaseDeDatos();
        return data;
    } 
    
    public ArrayList<clsComboBox>  consultarUsuarios(){            
        ArrayList<clsComboBox> data = new ArrayList<clsComboBox>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_usuario, name"
                    + " FROM ck_usuario"
                    + " WHERE estado = 'A' "
                    + " ORDER BY name";
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsComboBox oListaTemporal = new clsComboBox(bd.resultado.getString("id_usuario"),bd.resultado.getString("name"));
                    data.add(oListaTemporal);
                }
                while(bd.resultado.next()); 
                //return data;
            }
            else
            { 
                data = null;
            }            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            data = null;
        }  
        bd.desconectarBaseDeDatos();
        return data;
    }
    
    public boolean cambiarClave(String pass)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_usuario "
                    + " SET pass = md5('" +pass+ "') "
                    + " WHERE id_usuario = " + main.idUser;      
           
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
    
    public boolean insertarRegistro(String clave, String nombreUsuario)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_usuario"
                    + " (name, pass, nivel)"
                    + " VALUES('" + nombreUsuario + "', md5('" + clave + "'), '1')";           
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
