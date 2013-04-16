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
public class clsPrecio {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private String costo;
    private String utilidad;
    private String precio;
    
    public String getCosto() {
        return costo;
    }
    
    public void setCosto(String costo) {
        this.costo = costo;
    }
    
    public String getUtilidad() {
        return utilidad;
    }
    
    public void setUtilidad(String utilidad) {
        this.utilidad = utilidad;
    }
    
    public String getPrecio() {
        return precio;
    }
    
    public void setPrecio(String precio) {
        this.precio = precio;
    }
    
    public boolean insertarRegistroPrecio(String costo, String utilidad, String precio, String prioridad)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_precio"
                    + " (id_items, precio, costo, utilidad, prioridad)"
                    + " VALUES((select last_value from sc_items), " + precio + ", "+costo+","+utilidad+", "+prioridad+")";           
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
    
    public ArrayList<clsPrecio>  consultarDataPrecios(int idItem)
    {            
        ArrayList <clsPrecio> data = new ArrayList<clsPrecio>();
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_precio, prioridad, costo, utilidad, precio"
                    + " FROM ck_precio"
                    + " WHERE id_items = " + idItem
                    + " AND estado ='A'"
                    + " ORDER BY prioridad";
            bd.resultado = bd.sentencia.executeQuery(sql);
            while(bd.resultado.next()){
                clsPrecio oListaTemporal = new clsPrecio();
                oListaTemporal.setCosto(bd.resultado.getString("costo")); 
                oListaTemporal.setUtilidad(bd.resultado.getString("utilidad")); 
                oListaTemporal.setPrecio(bd.resultado.getString("precio"));                
                data.add(oListaTemporal);
            }
            bd.resultado.close();
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
    
    public ArrayList<clsComboBox>  consultarPrecios(String idItem)
    {            
        ArrayList<clsComboBox> data = new ArrayList<clsComboBox>();   
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_precio, precio, prioridad"
                    + " FROM ck_precio"
                    + " WHERE id_items = " + idItem
                    + " AND estado ='A'"
                    + " ORDER BY prioridad";
            bd.resultado = bd.sentencia.executeQuery(sql);
            System.out.println(sql);
            
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsComboBox oListaTemporal = new clsComboBox(bd.resultado.getString("prioridad"),""+bd.resultado.getDouble("precio"));
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
    
    public boolean insertarNuevoRegistroPrecio(int idProducto, String costo, String utilidad, String precio, String prioridad)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_precio"
                    + " (id_items, precio, costo, utilidad, prioridad)"
                    + " VALUES("+idProducto+", " + precio + ", "+costo+", "+utilidad+", "+prioridad+")";           
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
    
    public boolean eliminarPreciosActuales(int id_producto)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_precio"
                    + " SET estado = 'I', "
                    + " fecha_mod = now()" 
                    + " WHERE id_items = " + id_producto
                    + " AND estado = 'A'";      
           
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
}
