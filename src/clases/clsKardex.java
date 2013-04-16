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
public class clsKardex {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private int id_kardex;
    private String fecha;
    private String descripcion;
    private double unidades;
    private String estado;
    
    public int getIdKardex() {
        return id_kardex;
    }
    
    public void setIdKardex(int id_kardex) {
        this.id_kardex = id_kardex;
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
    
    public double getUnidades() {
        return unidades;
    }
    
    public void setUnidades(double unidades) {
        this.unidades = unidades;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public ArrayList<clsKardex>  consultarMovimientos(String id_items){            
        ArrayList<clsKardex> data = new ArrayList<clsKardex>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_kardex, fecha, descripcion, unidades, estado, id_items"
                    + " FROM ck_kardex"
                    + " WHERE id_items =" + id_items
                    + " AND estado = 'A' "
                    + " ORDER BY fecha";
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);              
            while(bd.resultado.next()){
                clsKardex oListaTemporal = new clsKardex();
                oListaTemporal.setIdKardex(bd.resultado.getInt("id_kardex"));
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                oListaTemporal.setDescripcion(bd.resultado.getString("descripcion"));
                oListaTemporal.setUnidades(bd.resultado.getDouble("unidades"));
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
    
    public boolean insertarRegistro(String fecha, String descripcion, Double unidades, int idItems)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_kardex"
                    + " (fecha, descripcion, unidades, id_items)"
                    + " VALUES('" + fecha + "', '" + descripcion + "', " + unidades + ", " + idItems + ")";           
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
