/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kaiser
 */
public class clsGrupo {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private int idGrupo;
    private String GrupoDescripcion;
    
    public int getIdGrupo() {
        return idGrupo;
    }
    
    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }
    
    public String getGrupoDescripcion() {
        return GrupoDescripcion;
    }
    
    public void setGrupoDescripcion(String GrupoDescripcion) {
        this.GrupoDescripcion = GrupoDescripcion;
    }
    
    public ArrayList<clsComboBox>  consultarGrupos(){            
        ArrayList<clsComboBox> data = new ArrayList<clsComboBox>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_grupo_producto, grupo_descripcion"
                    + " FROM ck_grupo_producto"
                    + " WHERE grupo_estado = 'A' "
                    + " ORDER BY grupo_descripcion";
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsComboBox oListaTemporal = new clsComboBox(bd.resultado.getString("id_grupo_producto"),bd.resultado.getString("grupo_descripcion"));
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
    
    public List<String> consultarNombreGrupos(){            
        List<String> data = new ArrayList<String>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT grupo_descripcion"
                  + " FROM ck_grupo_producto"
                    + " WHERE grupo_estado = 'A'";
            bd.resultado = bd.sentencia.executeQuery(sql);
             
            String line = "";
            if(bd.resultado.next())
            {   
                do 
                { 
                    line = bd.resultado.getString("grupo_descripcion");                   
                    data.add(line);
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
    
    public boolean insertarRegistro(String descripcion)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_grupo_producto"
                    + " (grupo_descripcion)"
                    + " VALUES('" + descripcion + "')";           
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
    
    public ArrayList<clsGrupo> consultaDataGrupo(String descripcion)
    {
        ArrayList <clsGrupo> data = new ArrayList<clsGrupo>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_grupo_producto, grupo_descripcion "
                    + " FROM ck_grupo_producto"
                    + " WHERE grupo_descripcion = '" + descripcion + "'";
            bd.resultado = bd.sentencia.executeQuery(sql);           
            
            while(bd.resultado.next()){
                clsGrupo oListaTemporal = new clsGrupo();
                oListaTemporal.setIdGrupo(bd.resultado.getInt("id_grupo_producto")); 
                oListaTemporal.setGrupoDescripcion(bd.resultado.getString("grupo_descripcion"));                 
                data.add(oListaTemporal);
            }
            bd.resultado.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        bd.desconectarBaseDeDatos();
        return data;        
    }
    
    public boolean modificarRegistro(int p_codigo, String p_descripcion)
    {
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_grupo_producto"
                    + " SET grupo_descripcion = '" + p_descripcion + "'"                 
                    + " WHERE id_grupo_producto = " + p_codigo;      
           
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
