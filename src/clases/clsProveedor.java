/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import clases.clsConexion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kaiser
 */
public class clsProveedor {
    clsConexion bd = new clsConexion();
    String sql;
    //private String id_codigo;
    private int id_proveedor;
    private String ruc;
    private String nombre;
    private String direccion;
    private String telefono;
    private String estado;
    
    public int getIdProveedor() {
        return id_proveedor;
    }
    
    public void setIdProveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }
    
    public String getRuc() {
        return ruc;
    }
    
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
     public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public boolean insertarRegistro(String ruc, String nombre, String direccion, String telefono)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_proveedor"
                    + "(ruc, nombre, direccion, telefono, estado)"
                    + " VALUES('" + ruc +"', '" + nombre + "', '" + direccion + "'"
                    + " , '" + telefono + "' , 'A')";           
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
    
    public List<String> consultarCodigos(){            
        List<String> data = new ArrayList<String>();   
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT ruc, nombre"
                  + " FROM ck_proveedor";
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            String line="";
            if(bd.resultado.next())
            {   
                do 
                {                     
                    line = bd.resultado.getString("ruc");                   
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
    
    public ArrayList<clsProveedor> consultaDataProveedor(String codigo)
    {
        ArrayList <clsProveedor> data=new ArrayList<clsProveedor>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_proveedor, ruc, nombre, direccion, telefono, estado "    
                    + " FROM ck_proveedor"
                    + " WHERE ruc = '" + codigo + "'"
                    + " AND estado = 'A'";
            bd.resultado = bd.sentencia.executeQuery(sql);           
            System.out.println();
            while(bd.resultado.next()){
                clsProveedor oListaTemporal = new clsProveedor();
                oListaTemporal.setIdProveedor(bd.resultado.getInt("id_proveedor")); 
                oListaTemporal.setRuc(bd.resultado.getString("ruc")); 
                oListaTemporal.setNombre(bd.resultado.getString("nombre")); 
                oListaTemporal.setDireccion(bd.resultado.getString("direccion")); 
                oListaTemporal.setTelefono(bd.resultado.getString("telefono"));   
                oListaTemporal.setEstado(bd.resultado.getString("estado"));  
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
    
    public ArrayList<clsProveedor> consultaDataProveedorTodosAI(String codigo)
    {
        ArrayList <clsProveedor> data=new ArrayList<clsProveedor>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_proveedor, ruc, nombre, direccion, telefono, estado "    
                    + " FROM ck_proveedor"
                    + " WHERE ruc = '" + codigo + "'";                    
            bd.resultado = bd.sentencia.executeQuery(sql);           
            System.out.println();
            while(bd.resultado.next()){
                clsProveedor oListaTemporal = new clsProveedor();
                oListaTemporal.setIdProveedor(bd.resultado.getInt("id_proveedor")); 
                oListaTemporal.setRuc(bd.resultado.getString("ruc")); 
                oListaTemporal.setNombre(bd.resultado.getString("nombre")); 
                oListaTemporal.setDireccion(bd.resultado.getString("direccion")); 
                oListaTemporal.setTelefono(bd.resultado.getString("telefono"));   
                oListaTemporal.setEstado(bd.resultado.getString("estado"));  
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
    
    public boolean modificarRegistro(int p_codigo, String codigo, String nombre, String direccion, String telefono, String estado)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_proveedor"
                    + " SET ruc = '" + codigo + "',"                    
                    + " nombre = '" + nombre + "',"
                    + " direccion = '" + direccion + "',"
                    + " telefono = '" + telefono + "',"
                    + " estado = '" + estado + "' "                    
                    + " WHERE id_proveedor = " + p_codigo;      
           
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
    
    public boolean comprobarCodigo(String codigo)
    {
        boolean exito;
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_proveedor, ruc, nombre, direccion, telefono, estado "    
                    + " FROM ck_proveedor"
                    + " WHERE ruc = '" + codigo + "'";               
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
    
    public boolean eliminarProveedor(int p_codigo)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_proveedor"
                    + " SET estado = 'I'" 
                    + " WHERE id_proveedor = " + p_codigo;      
           
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
    
    public ArrayList<clsProveedor>  consultarDataProveedorPorNombre(String nombre){            
        ArrayList<clsProveedor> data = new ArrayList<clsProveedor>();   
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_proveedor, ruc, nombre"
                    + " FROM ck_proveedor"
                    + " WHERE upper(nombre) like '%" + nombre + "%'"
                    + " AND estado = 'A'";
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            while(bd.resultado.next()){
                clsProveedor oListaTemporal = new clsProveedor();
                oListaTemporal.setIdProveedor(bd.resultado.getInt("id_proveedor")); 
                oListaTemporal.setRuc(bd.resultado.getString("ruc")); 
                oListaTemporal.setNombre(bd.resultado.getString("nombre")); 
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
