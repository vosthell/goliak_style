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
public class clsDetalle {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private int id;
    private String codigo;
    private String descripcion;
    private Double cantidad;
    private Double precio;
    private Double deta_descuento;
    private Double deta_iva;
    
    public int getIdProducto() {
        return id;
    }
    
    public void setIdProducto(int id) {
        this.id = id;
    }
    
    public String getCodigoProducto() {
        return codigo;
    }
    
    public void setCodigoProducto(String codigo) {
        this.codigo = codigo;
    }
    
    public String getDescripcionProducto() {
        return descripcion;
    }
    
    public void setDescripcionProducto(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Double getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
    
    public Double getPrecio() {
        return precio;
    }
    
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    public Double getDescuento() {
        return deta_descuento;
    }
    
    public void setDescuento(Double deta_descuento) {
        this.deta_descuento = deta_descuento;
    }
    
    public Double getIVA() {
        return deta_iva;
    }
    
    public void setIVA(Double deta_iva) {
        this.deta_iva = deta_iva;
    }
    
    public boolean insertarDetalle(String ultFactura, int idItems, String cantidad, String precio, String descuento, String iva )
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_detalle_movi(id_cabecera_movi, id_items, cantidad, precio, deta_descuento, deta_iva)"                   
                    + " VALUES("+ultFactura+", "+idItems+", "+cantidad+", "+precio+", "+descuento+", "+iva+")";           
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
    
    public boolean insertarDetalleCompras(int ultFactura, int idItems, Double cantidad, String precio, String descuento, String iva )
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_detalle_movi_compras(id_cabecera_movi, id_items, "
                    + "cantidad, precio, deta_descuento, deta_iva)"                   
                    + " VALUES("+ultFactura+", "+idItems+", "
                    + cantidad+", "+precio+", "+descuento+", "+iva+")";           
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
    
    public ArrayList<clsDetalle>  consultarDataDetalle(int idCabecera){            
        ArrayList<clsDetalle> data = new ArrayList<clsDetalle>();   
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_detalle_movi, id_cabecera_movi, cantidad, "
                    + " precio, a.id_items id_items, des_item, cod_item,"
                    + " deta_descuento, deta_iva"
                    + " FROM ck_detalle_movi AS a INNER JOIN ck_items AS b ON a.id_items = b.id_items"
                    + " WHERE id_cabecera_movi= " + idCabecera;
            bd.resultado = bd.sentencia.executeQuery(sql);
            System.out.println(sql);
            while(bd.resultado.next()){
                clsDetalle oListaTemporal = new clsDetalle();
                
                oListaTemporal.setIdProducto(bd.resultado.getInt("id_items"));  
                oListaTemporal.setCodigoProducto(bd.resultado.getString("cod_item"));                
                oListaTemporal.setDescripcionProducto(bd.resultado.getString("des_item"));  
                oListaTemporal.setCantidad(bd.resultado.getDouble("cantidad"));   
                oListaTemporal.setPrecio(bd.resultado.getDouble("precio"));   
                oListaTemporal.setDescuento(bd.resultado.getDouble("deta_descuento"));   
                oListaTemporal.setIVA(bd.resultado.getDouble("deta_iva"));   
                
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
