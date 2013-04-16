/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CKaiser
 */
public class clsPermisos {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private String form_descripcion;
    
    public String getFormDescripcion() {
        return form_descripcion;
    }
    
    public void setFormDescripcion(String form_descripcion) {
        this.form_descripcion = form_descripcion;
    }
    
    public boolean insertarRegistro(String idUsuario, String idModulo, String idFormulario)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_permisos(id_usuario, id_modulo, id_formulario)"
                    + " VALUES ("+ idUsuario+", "+idModulo+", "+idFormulario+")";           
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
    
    public boolean eliminarPermisos(String idUsuario)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_permisos"
                    + " SET estado = 'I',"
                    + " fecha_modificacion = now()"
                    + " WHERE id_usuario = " + idUsuario;      
           
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
     
     public ArrayList<clsPermisos>  consultarPermisosUsuario(String idUsuario){            
        ArrayList<clsPermisos> data = new ArrayList<clsPermisos>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_permisos, id_usuario, id_modulo, form_descripcion "
                    + " FROM ck_permisos AS a, ck_formulario AS b"
                    + " WHERE id_usuario="+idUsuario
                    + " AND a.id_formulario = b.id_formulario"
                    + " AND a.estado ='A'";
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){
                clsPermisos oListaTemporal = new clsPermisos();
                oListaTemporal.setFormDescripcion(bd.resultado.getString("form_descripcion"));                
                data.add(oListaTemporal);
            }
            //return data;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            data = null;
        } 
        bd.desconectarBaseDeDatos();
        return data;
    }
     
    public boolean comprobarPermisoFormulario(String idUsuario, String nameFormulario)
    {
        boolean exito;
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT *"
                    + " FROM ck_permisos AS a, ck_formulario AS b"
                    + " WHERE a.id_usuario = " + idUsuario
                    + " AND a.id_formulario = b.id_formulario"
                    + " AND b.form_descripcion = '" + nameFormulario + "'"
                    + " AND a.estado = 'A'";                  
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
           System.out.print(ex);
           exito = false;
        }     
        bd.desconectarBaseDeDatos();
        return exito;
    }
}
