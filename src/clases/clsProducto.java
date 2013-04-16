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
public class clsProducto {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private String id_items;
    private String cod_item;
    private String des_item;
    private Double cant_stock;
    private Double costo;
    private String control_perecible;
    private String fecha_caducidad;
    private String control_existencia;
    private String control_minimo;
    private String cantidad_minima;
    private Double descuento; 
    private Integer id_grupo_producto;
    private String estado;
    
    public String getIdItems() {
        return id_items;
    }
    
    public void setIdItems(String id_items) {
        this.id_items = id_items;
    }
    
    public String getCodItem() {
        return cod_item;
    }
    
    public void setCodItem(String cod_item) {
        this.cod_item = cod_item;
    }
    
    public String getDesItem() {
        return des_item;
    }
    
    public void setDesItem(String des_item) {
        this.des_item = des_item;
    }
    
    public Double getCantStock() {
        return cant_stock;
    }
    
    public void setCantStock(Double cant_stock) {
        this.cant_stock = cant_stock;
    }
    
    public Double getCosto() {
        return costo;
    }
    
    public void setCosto(Double costo) {
        this.costo = costo;
    }
    
    public String getControlPerecible() {
        return control_perecible;
    }
    
    public void setControlPerecible(String control_perecible) {
        this.control_perecible = control_perecible;
    }
    
    public String getFechaCaducidad() {
        return fecha_caducidad;
    }
    
    public void setFechaCaducidad(String fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }
    
    public String getControlExistencia() {
        return control_existencia;
    }
    
    public void setControlExistencia(String control_existencia) {
        this.control_existencia = control_existencia;
    }
    
    public String getCantidadMinima() {
        return cantidad_minima;
    }
    
    public void setCantidadMinima(String cantidad_minima) {
        this.cantidad_minima = cantidad_minima;
    }
    
    public String getControlMinimo() {
        return control_minimo;
    }
    
    public void setControlMinimo(String control_minimo) {
        this.control_minimo = control_minimo;
    }
    
    public Double getDescuento() {
        return descuento;
    }
    
    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }
    
    public int getIdGrupoProducto() {
        return id_grupo_producto;
    }
    
    public void setIdGrupoProducto(int id_grupo_producto) {
        this.id_grupo_producto = id_grupo_producto;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public List<String> consultarCodigos(){            
        List<String> data = new ArrayList<String>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT trim(cod_item) cod_item"
                  + " FROM ck_items"
                    + " WHERE estado = 'A'";
            bd.resultado = bd.sentencia.executeQuery(sql);
             
            String line = "";
            if(bd.resultado.next())
            {   
                do 
                { 
                    line = bd.resultado.getString("cod_item");                   
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
    
    public boolean insertarProducto(String codigo, String descripcion, String costo, String cantidad, char controlMinimo, String cantidadMinima, String controlExistencia, char controlPerecible, String fechaCaducidad, String descuento, String idGrupo)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_items"
                    + "(cod_item, des_item, costo, cant_stock, fecha_caducidad,"
                    + " control_minimo, cantidad_minima, "
                    + " control_existencia, control_perecible, descuento, id_grupo_producto)"
                    + " VALUES('" + codigo + "', '" + descripcion + "', " + costo 
                    + " , " + cantidad + ", '" + fechaCaducidad + "',"
                    + " '" + controlMinimo + "', "+ cantidadMinima+","
                    + " '"+ controlExistencia+"', '"+controlPerecible+"', "+descuento+", "+idGrupo+")";           
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
            sql = "SELECT cod_item, des_item "    
                    + " FROM ck_items"
                    + " WHERE cod_item = '" + codigo + "'";               
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
    
    public ArrayList<clsProducto>  consultarDataProductoPorNombre(String nombre){            
         ArrayList<clsProducto> data = new ArrayList<clsProducto>();  
         try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_items, cod_item, des_item, cant_stock, descuento, control_existencia"
                    + " FROM ck_items"
                    + " WHERE upper(des_item) like '%" + nombre + "%'"
                    + " AND estado='A'";
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            while(bd.resultado.next()){
                clsProducto oListaTemporal = new clsProducto();
                oListaTemporal.setIdItems(bd.resultado.getString("id_items"));
                oListaTemporal.setCodItem(bd.resultado.getString("cod_item"));
                oListaTemporal.setDesItem(bd.resultado.getString("des_item"));
                oListaTemporal.setCantStock(bd.resultado.getDouble("cant_stock"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setControlExistencia(bd.resultado.getString("control_existencia"));
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
    
    public ArrayList<clsProducto>  consultarDataProductoPorCodigo(String codigo){            
        ArrayList<clsProducto> data = new ArrayList<clsProducto>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_items, cod_item, des_item, cant_stock, costo, "
                    + " control_perecible, fecha_caducidad, control_existencia, "
                    + " control_minimo, cantidad_minima, descuento, id_grupo_producto"
                    + " FROM ck_items"
                    + " WHERE cod_item = '" + codigo + "'"
                    + " AND estado='A'";
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsProducto oListaTemporal = new clsProducto();
                oListaTemporal.setIdItems(bd.resultado.getString("id_items"));
                oListaTemporal.setCodItem(bd.resultado.getString("cod_item"));
                oListaTemporal.setDesItem(bd.resultado.getString("des_item"));
                oListaTemporal.setCantStock(bd.resultado.getDouble("cant_stock"));
                oListaTemporal.setCosto(bd.resultado.getDouble("costo"));
                oListaTemporal.setControlPerecible(bd.resultado.getString("control_perecible"));
                oListaTemporal.setFechaCaducidad(bd.resultado.getString("fecha_caducidad"));
                oListaTemporal.setControlExistencia(bd.resultado.getString("control_existencia"));
                oListaTemporal.setControlMinimo(bd.resultado.getString("control_minimo"));
                oListaTemporal.setCantidadMinima(bd.resultado.getString("cantidad_minima"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setIdGrupoProducto(bd.resultado.getInt("id_grupo_producto"));
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
    
    public ArrayList<clsProducto>  consultarDataProductoPorCodigoTodosAI(String codigo){            
        ArrayList<clsProducto> data = new ArrayList<clsProducto>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_items, cod_item, des_item, cant_stock, costo, "
                    + " control_perecible, fecha_caducidad, control_existencia, "
                    + " control_minimo, cantidad_minima, descuento, id_grupo_producto, estado"
                    + " FROM ck_items"
                    + " WHERE cod_item = '" + codigo + "'";                    
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsProducto oListaTemporal = new clsProducto();
                oListaTemporal.setIdItems(bd.resultado.getString("id_items"));
                oListaTemporal.setCodItem(bd.resultado.getString("cod_item"));
                oListaTemporal.setDesItem(bd.resultado.getString("des_item"));
                oListaTemporal.setCantStock(bd.resultado.getDouble("cant_stock"));
                oListaTemporal.setCosto(bd.resultado.getDouble("costo"));
                oListaTemporal.setControlPerecible(bd.resultado.getString("control_perecible"));
                oListaTemporal.setFechaCaducidad(bd.resultado.getString("fecha_caducidad"));
                oListaTemporal.setControlExistencia(bd.resultado.getString("control_existencia"));
                oListaTemporal.setControlMinimo(bd.resultado.getString("control_minimo"));
                oListaTemporal.setCantidadMinima(bd.resultado.getString("cantidad_minima"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setIdGrupoProducto(bd.resultado.getInt("id_grupo_producto"));
                oListaTemporal.setEstado(bd.resultado.getString("estado"));
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
    
    public boolean modificarDataProducto(int p_id, String codigo, String descripcion, String stock, String costo, char controlMinimo, String cantidadMinima, String controlExistencia, char controlPerecible, String fechaCaducidad, String descuento, String idGrupo, String estado)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_items"
                    + " SET cod_item = '" + codigo + "',"
                    + " des_item = '" + descripcion + "',"
                    + " costo = " + costo + ","
                    + " cant_stock = " + stock + ","
                    + " control_minimo = '"+controlMinimo+"',"
                    + " cantidad_minima = "+cantidadMinima+","
                    + " control_existencia='"+controlExistencia+"',"
                    + " control_perecible='"+controlPerecible+"',"
                    + " fecha_caducidad='"+fechaCaducidad+"',"
                    + " descuento = " + descuento +","
                    + " id_grupo_producto = " + idGrupo +","
                    + " estado = '" + estado + "'"
                    + " WHERE id_items = " + p_id;      
           
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
    
    public boolean insertarKardex(int idItem, String descripcion, String unidades)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_kardex"
                    + " (fecha, descripcion, unidades, id_items)"
                    + " VALUES(now(), '"+descripcion+"', "+unidades+", "+idItem+")";           
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
    
    public Integer obtenerUltimoProducto()
    {          
        int ultimo = 0; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT max(id_items) AS maximo"
                    + " FROM ck_items";
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                ultimo = bd.resultado.getInt("maximo");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            ultimo = 0;
        } 
        bd.desconectarBaseDeDatos();
        return ultimo;
    }
    
    public String obtenerCampoCombo(int idProducto)
    {          
        String dato = ""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT prcombo"
                    + " FROM productos"
                    + " WHERE id_productos = " + idProducto;
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                dato = bd.resultado.getString("prcombo");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            dato = null;
        } 
        bd.desconectarBaseDeDatos();
        return dato;
    }
    /*public boolean insertarVerificado(int idItem)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_tmp_verificado"
                    + " (id_items)"
                    + " VALUES("+idItem+")";           
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
    } */
    
    public boolean disminuirStock(int p_id, String cantidad)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_items"
                    + " SET cant_stock = cant_stock - " + cantidad                                     
                    + " WHERE id_items = " + p_id;            
            // System.out.println("SQL enviado:" + sql);
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
    
    public boolean aumentarStock(int p_id, double cantidad)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_items"
                    + " SET cant_stock = cant_stock + " + cantidad                                     
                    + " WHERE id_items = " + p_id;            
            // System.out.println("SQL enviado:" + sql);
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
    
    /*public boolean comprobarVerificado(int id_items)
    {
        boolean exito;
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_items "    
                    + " FROM ck_tmp_verificado"
                    + " WHERE id_items = " + id_items;               
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
    }*/
    
    public boolean eliminarProducto(int p_codigo)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_items"
                    + " SET estado = 'I'" 
                    + " WHERE id_items = " + p_codigo;      
           
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

