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
public class clsPago {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private String id_cabecera_movi;
    private String referencia;
    private String fecha_pago;
    private Double valor;
    
    public String getIdCabeceraMovi() {
        return id_cabecera_movi;
    }
    
    public void setIdCabeceraMovi(String idCabeceraMovi) {
        this.id_cabecera_movi = idCabeceraMovi;
    }
    
    public String getReferencia() {
        return referencia;
    }
    
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    
    public String getFechaPago() {
        return fecha_pago;
    }
    
    public void setFechaPago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }
    
    public Double getValor() {
        return valor;
    }
    
    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    public boolean insertarRegistroHistorico(String idCtaCobrar, String idUser, String factRef, String fechaPago, String valor)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_pagos"
                    + " (id_cta_cobrar, id_usuario, referencia, fecha_pago, valor, id_caja_operacion)"
                    + " VALUES("+idCtaCobrar+", "+idUser+", '"+factRef+"', '"+fechaPago+"', "+valor+", 0)";           
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
    
    public boolean insertarRegistro(String idCtaCobrar, String idUser, String factRef, String valor, String idCajaOperacion)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_pagos"
                    + " (id_cta_cobrar, id_usuario, referencia, fecha_pago, valor, id_caja_operacion)"
                    + " VALUES("+idCtaCobrar+", "+idUser+", '"+factRef+"', now(), "+valor+", "+idCajaOperacion+")";           
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
    
     public ArrayList<clsPago>  consultaPagosRealizadas(String idCajaAbierta){            
        ArrayList<clsPago> data = new ArrayList<clsPago>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT b.id_cabecera_movi id_cabecera_movi, a.referencia referencia, fecha_pago, a.valor valor "           
                    + " FROM ck_pagos AS a join ck_cta_cobrar AS b on a.id_cta_cobrar = b.id_cta_cobrar "                                           
                    + " WHERE id_caja_operacion = "+idCajaAbierta
                    + " AND a.estado='A'";
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsPago oListaTemporal = new clsPago();
                oListaTemporal.setIdCabeceraMovi(bd.resultado.getString("id_cabecera_movi"));   
                oListaTemporal.setReferencia(bd.resultado.getString("referencia"));                
                oListaTemporal.setFechaPago(bd.resultado.getString("fecha_pago"));
                oListaTemporal.setValor(bd.resultado.getDouble("valor"));                      
                
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
