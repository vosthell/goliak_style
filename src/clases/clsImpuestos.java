/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Kaiser
 */
public class clsImpuestos {
    clsConexion bd = new clsConexion(); 
    String sql;
    
   public Double obtenerPorcentajeIVA()
   {          
        Double porcentaje=0.00; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT porcentaje"
                    + " FROM ck_impuestos"
                    + " WHERE id_impuestos = 1";
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                porcentaje = bd.resultado.getDouble("porcentaje");              
            }
            //return porcentaje;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            porcentaje = null;
        }  
        bd.desconectarBaseDeDatos();
        return porcentaje;
    }
   
   public boolean insertarProductoImpuesto(String idImpuesto)
   {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_item_impuesto"
                    + " (id_items, id_impuestos)"
                    + " VALUES((select last_value from sc_items), " + idImpuesto + ")";           
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
   
   public boolean updateProductoImpuesto(int idProducto, String idImpuesto)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_item_impuesto"
                    + " (id_items, id_impuestos)"
                    + " VALUES(" + idProducto + ", " + idImpuesto + ")";           
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
   
   public boolean comprobarImpuesto(int id_items, String idImpuesto)
   {
        boolean exito;
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_item_impuesto "    
                    + " FROM ck_item_impuesto"
                    + " WHERE id_items = " + id_items
                    + " AND id_impuestos = " + idImpuesto
                    + " AND estado='A'";               
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
   
    public boolean eliminarImpuestosActuales(int id_producto)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_item_impuesto"
                    + " SET estado = 'I'"
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
