/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import index.main;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CKaiser
 */
public class clsAuditoria {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private String usuario;
    private String formulario;
    private String fecha;
    private String descripcion;
    
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getFormulario() {
        return formulario;
    }
    
    public void setFormulario(String formulario) {
        this.formulario = formulario;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean insertarAuditoria(String formulario, String descripcion, String modulo)
    {       
        boolean exito=false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_auditoria"
                    + " (id_usuario, formulario, fecha, descripcion, id_modulo)"
                    + " VALUES("+ main.idUser +", '"+ formulario+"', now(), '"+descripcion+"', "
                    + " '"+modulo+"')";           
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
    
     public ArrayList<clsAuditoria>  consultarDataAuditoria(String setUsuario, String idUsuario, String setFecha, String fecha){            
        ArrayList<clsAuditoria> data = new ArrayList<clsAuditoria>();   
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_auditoria, a.id_usuario, b.name usuario, "
                    + "formulario, fecha, descripcion, id_modulo "
                    + "FROM ck_auditoria AS a JOIN ck_usuario AS b ON a.id_usuario = b.id_usuario ";
            if(setUsuario.equals("S")&&setFecha.equals("N"))
            {
                sql = sql + "WHERE a.id_usuario = " + idUsuario;
            }
            if(setFecha.equals("S")&&setUsuario.equals("N"))
            {    
                sql = sql + "WHERE fecha::date = '" + fecha + "'";            
            }
            if(setFecha.equals("S")&&setUsuario.equals("S"))
            {     sql = sql + "WHERE a.id_usuario = " + idUsuario
                         + " AND fecha::date = '" + fecha + "'";
            }
            bd.resultado = bd.sentencia.executeQuery(sql);
            System.out.println(sql);
            while(bd.resultado.next()){
                clsAuditoria oListaTemporal = new clsAuditoria();
                
                oListaTemporal.setUsuario(bd.resultado.getString("usuario"));  
                oListaTemporal.setFormulario(bd.resultado.getString("formulario"));                
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));  
                oListaTemporal.setDescripcion(bd.resultado.getString("descripcion"));       
                
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
}
