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
public class clsEgreso {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private String concepto;
    private Double cantidadEgreso;
    private String fecha;
    
    public String getConcepto() {
        return concepto;
    }
    
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
    
    public Double getCantidadEgreso() {
        return cantidadEgreso;
    }
    
    public void setCantidadEgreso(Double cantidadEgreso) {
        this.cantidadEgreso = cantidadEgreso;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public boolean insertarValorCuota(String idCajaOperacion, String concepto, String cantidadEgreso)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_egreso(id_caja_operacion, concepto, cantidad_egreso, fecha)"                   
                    + " VALUES(" + idCajaOperacion + ", '" + concepto + "', " + cantidadEgreso + ", now())";           
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
    
    public Double obtenerValorEgresos(String idCajaAbierta)
    {          
        Double valorFacturado = 0.00; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT sum(cantidad_egreso) AS efectivo"
                    + " FROM ck_egreso"
                    + " WHERE id_caja_operacion = "+idCajaAbierta
                    + " AND estado = 'A'";
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                valorFacturado = bd.resultado.getDouble("efectivo");              
            }
            //return valorFacturado;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            valorFacturado = null;
        } 
        bd.desconectarBaseDeDatos();
        return valorFacturado;        
    }
    
    public ArrayList<clsEgreso>  consultaEgresosRealizadas(String idCajaAbierta){            
        ArrayList<clsEgreso> data = new ArrayList<clsEgreso>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_egreso, id_caja_operacion, concepto, cantidad_egreso, fecha, estado "           
                    + " FROM ck_egreso "                                           
                    + " WHERE id_caja_operacion = "+idCajaAbierta
                    + " AND estado='A'";
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsEgreso oListaTemporal = new clsEgreso();
                oListaTemporal.setConcepto(bd.resultado.getString("concepto"));   
                oListaTemporal.setCantidadEgreso(bd.resultado.getDouble("cantidad_egreso"));                
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                               
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
