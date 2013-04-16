/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

//import clases.clsConexion;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Kaiser
 */
public class clsCaja {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private double valor_apertura;
    private String id_caja_operacion;
    
    public double getValorApertura() {
        return valor_apertura;
    }

    public void setValorApertura(double valor_apertura) {
        this.valor_apertura = valor_apertura;
    }
    
    public String getIdCajaOperacion()
    {
        return id_caja_operacion;
    }
    
    public void setIdCajaOperacion(String id_caja_operacion) {
        this.id_caja_operacion = id_caja_operacion;
    }
    
    public boolean consultarCajaAbierta(String idUsuario)
    {
        boolean exito = false;
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_caja_operacion, id_usuario, apertura, fecha_apertura, cierre,"  
                    + " fecha_cierre, valor_apertura"
                    + " FROM ck_caja_operacion"
                    + " WHERE id_usuario = " + idUsuario 
                    + " AND cierre='N'"
                    + " AND apertura='S'";
            //System.out.println(sql);
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
    
    public boolean registrarAperturaCaja(String idUsuario, String valor, String cajero, String facturacionManual, String idFacturero)
    {
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "INSERT INTO ck_caja_operacion(id_usuario, apertura, fecha_apertura, valor_apertura, id_cajero, "
                    + "facturacion_manual, id_facturero)"
                    + " VALUES (" + idUsuario + ", 'S', now(), " + valor + ", " + cajero + ", "
                    + "'" + facturacionManual + "', "+idFacturero+")";            
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
    
     public ArrayList<clsCaja> consultarDataCajaAbierta(String idUser)
     {       
        ArrayList<clsCaja> data = new ArrayList<clsCaja>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_caja_operacion, id_usuario, "
                    + " apertura, fecha_apertura, cierre, "
                    + " fecha_cierre, valor_apertura"
                    + " FROM ck_caja_operacion"
                    + " WHERE id_usuario = " + idUser
                    + " AND cierre='N'"
                    + " AND apertura='S'";
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
               
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsCaja oListaTemporal = new clsCaja();
                    oListaTemporal.setValorApertura(bd.resultado.getDouble("valor_apertura"));
                    oListaTemporal.setIdCajaOperacion(bd.resultado.getString("id_caja_operacion"));
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
     
    public boolean registrarCierreCaja(String idUsuario, String facturado, String contado, String diferencia, Double pagos)
    {
        boolean exito;
        try
        {
            String sql;
            bd.conectarBaseDeDatos();            
             sql = "UPDATE ck_caja_operacion"
                     + " SET cierre = 'S', "
                     + " valor_contado = " + contado + ","
                     + " valor_facturado = " + facturado + ","
                     + " valor_pagos = "+ pagos +","
                     + " diferencia = " + diferencia + ","
                     + " fecha_cierre = now()"
                     + " WHERE id_usuario = " + idUsuario
                     + " AND cierre = 'N'"
                     + " AND apertura = 'S'";
            
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
    
    public ArrayList<clsComboBox>  consultarCajero(){            
        ArrayList<clsComboBox> data = new ArrayList<clsComboBox>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_cajero, nombre"
                    + " FROM ck_cajero"
                    + " WHERE estado = 'I' "
                    + " ORDER BY nombre";
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsComboBox oListaTemporal = new clsComboBox(bd.resultado.getString("id_cajero"),bd.resultado.getString("nombre"));
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
    
    public String obtenerCajero(String idUser)
    {          
        String nombreCajero = ""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT b.nombre, a.id_cajero"
                    + " FROM ck_caja_operacion AS a, ck_cajero AS b"
                    + " WHERE a.id_usuario = '" + idUser + "'"
                    + " AND a.id_cajero = b.id_cajero"
                    + " AND apertura='S'";
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                nombreCajero = bd.resultado.getString("nombre");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            nombreCajero = null;
        }     
        bd.desconectarBaseDeDatos();
        return nombreCajero;
    }
    
    public String obtenerIdCajero(String idUser)
    {          
        String nombreCajero=""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_cajero"
                    + " FROM ck_caja_operacion AS a, ck_cajero AS b"
                    + " WHERE a.id_usuario = '" + idUser + "'"
                    + " AND a.id_cajero = b.id_cajero"
                    + " AND a.apertura='S'";
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                nombreCajero = bd.resultado.getString("id_cajero");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            nombreCajero = null;
        }     
        bd.desconectarBaseDeDatos();
        return nombreCajero;
    }
    
    public String obtenerCajaAbierta(String idUser)
    {          
        String nombreCajero=""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_caja_operacion"
                    + " FROM ck_caja_operacion"
                    + " WHERE id_usuario = '" + idUser + "'"
                    + " AND cierre = 'N'"
                    + " AND apertura = 'S'";
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                nombreCajero = bd.resultado.getString("id_caja_operacion");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            nombreCajero = null;
        } 
        bd.desconectarBaseDeDatos();
        return nombreCajero;
    }
    
    public Double obtenerValorFacturado(String idCajaAbierta)
    {          
        Double valorFacturado= 0.00; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT sum(efectivo) AS efectivo"
                    + " FROM ck_cabecera_movi"
                    + " WHERE id_caja_operacion = "+idCajaAbierta
                    + " AND estado='A'";
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
    
    public boolean modificarCaja(String idCajero, String estado)
    {
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cajero"
                    + " SET estado = '" + estado + "'"              
                    + " WHERE id_cajero = " + idCajero;      
           
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
    
    public Double obtenerValorPagos(String idCajaAbierta)
    {          
        Double valorFacturado = 0.00; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT sum(valor) AS efectivo"
                    + " FROM ck_pagos"
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
    
    public String comprobarFacturacionManual(String idCajaAbierta)
    {          
        String dato = ""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT facturacion_manual"
                    + " FROM ck_caja_operacion"
                    + " WHERE id_caja_operacion = " + idCajaAbierta;                    
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
               dato = bd.resultado.getString("facturacion_manual");              
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
    
    /*public String comprobarPrimeraFacturacionManual(String idCajaAbierta)
    {          
        String dato = ""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT primera_vez_manual"
                    + " FROM ck_caja_operacion"
                    + " WHERE id_caja_operacion = " + idCajaAbierta;                    
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
               dato = bd.resultado.getString("primera_vez_manual");              
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
    }*/
    
    /*public String obtenerPrimerValorFacturacionManual(String idCajaAbierta)
    {          
        String dato = ""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT primer_valor_manual"
                    + " FROM ck_caja_operacion"
                    + " WHERE id_caja_operacion = " + idCajaAbierta;                    
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
               dato = bd.resultado.getString("primer_valor_manual");              
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
    }*/
    
    /*public boolean modificarPrimeraVezFactManual(String idCajaAbierta)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_caja_operacion"
                    + " SET primera_vez_manual = 'N'"                                     
                    + " WHERE id_caja_operacion = " + idCajaAbierta;            
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
    }  */ 
    public int obtenerIdFacturero(String idCajaAbierta)
   {          
        int idFacturero = 0;
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_facturero"
                    + " FROM ck_caja_operacion"
                    + " WHERE id_caja_operacion = " + idCajaAbierta;
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                idFacturero = bd.resultado.getInt("id_facturero");              
            }
            //return porcentaje;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            idFacturero = 0;
        }  
        bd.desconectarBaseDeDatos();
        return idFacturero;
    }
}