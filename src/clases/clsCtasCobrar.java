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
public class clsCtasCobrar {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private String id_cta_cobrar;
    private String fact_referencia;
    private String descripcion;
    private String valor;
    private String valor_actual;
    private String id_cabecera_movi;
    
    public String getIdCtaCobrar() {
        return id_cta_cobrar;
    }
    
    public void setIdCtaCobrar(String id_cta_cobrar) {
        this.id_cta_cobrar = id_cta_cobrar;
    }
    
    public String getFactReferencia() {
        return fact_referencia;
    }
    
    public void setFactReferencia(String fact_referencia) {
        this.fact_referencia = fact_referencia;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getValor() {
        return valor;
    }
    
    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public String getValorActual() {
        return valor_actual;
    }
    
    public void setValorActual(String valor_actual) {
        this.valor_actual = valor_actual;
    }
    
    public String getIdCabeceraMovi() {
        return id_cabecera_movi;
    }
    
    public void setIdCabeceraMovi(String id_cabecera_movi) {
        this.id_cabecera_movi = id_cabecera_movi;
    }
    
    public ArrayList<clsCtasCobrar> consultaCtasCobrarHistorico(String idCliente)
    {
        ArrayList <clsCtasCobrar> data=new ArrayList<clsCtasCobrar>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "select ck_cta_cobrar.id_cta_cobrar, fact_referencia, ck_cta_cobrar.descripcion, "
                    + " ck_cta_cobrar.valor valor, ck_cta_cobrar.valor_actual valor_actual, "
                    + " ck_cta_cobrar.id_cabecera_movi, ck_cabecera_movi.codigo"
                    + " FROM ck_cta_cobrar "
                    + " INNER JOIN ck_cabecera_movi"
                    + " ON ck_cta_cobrar.id_cabecera_movi = ck_cabecera_movi.id_cabecera_movi "
                    + " WHERE ck_cabecera_movi.codigo = " + idCliente
                    + " AND id_caja_operacion=0"
                    + " AND ck_cabecera_movi.estado = 'A'"
                    + " ORDER BY valor_actual DESC";
                    //+ " --and valor_actual>0"
                    
            bd.resultado = bd.sentencia.executeQuery(sql);           
            
            while(bd.resultado.next()){
                clsCtasCobrar oListaTemporal = new clsCtasCobrar();
                oListaTemporal.setIdCtaCobrar(bd.resultado.getString("id_cta_cobrar")); 
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setDescripcion(bd.resultado.getString("descripcion"));
                oListaTemporal.setValor(bd.resultado.getString("valor"));
                oListaTemporal.setValorActual(bd.resultado.getString("valor_actual"));
                oListaTemporal.setIdCabeceraMovi(bd.resultado.getString("id_cabecera_movi"));
                
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
    
    public ArrayList<clsCtasCobrar> consultaCtasCobrar(String idCliente)
    {
        ArrayList <clsCtasCobrar> data=new ArrayList<clsCtasCobrar>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "select ck_cta_cobrar.id_cta_cobrar, fact_referencia, ck_cta_cobrar.descripcion, "
                    + " ck_cta_cobrar.valor valor, ck_cta_cobrar.valor_actual valor_actual, "
                    + " ck_cta_cobrar.id_cabecera_movi, ck_cabecera_movi.codigo"
                    + " FROM ck_cta_cobrar "
                    + " INNER JOIN ck_cabecera_movi "
                    + " ON ck_cta_cobrar.id_cabecera_movi = ck_cabecera_movi.id_cabecera_movi "
                    + " WHERE ck_cabecera_movi.codigo = " + idCliente
                    + " --AND id_caja_operacion<>0"
                    + " ORDER BY valor_actual DESC";
                    //+ " --and valor_actual>0"
                    
            bd.resultado = bd.sentencia.executeQuery(sql);           
            
            while(bd.resultado.next()){
                clsCtasCobrar oListaTemporal = new clsCtasCobrar();
                oListaTemporal.setIdCtaCobrar(bd.resultado.getString("id_cta_cobrar")); 
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setDescripcion(bd.resultado.getString("descripcion"));
                oListaTemporal.setValor(bd.resultado.getString("valor"));
                oListaTemporal.setValorActual(bd.resultado.getString("valor_actual"));
                oListaTemporal.setIdCabeceraMovi(bd.resultado.getString("id_cabecera_movi"));
                
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
    
    public boolean actualizarSaldoHisto(String idCtaCobrar, double valor, String fechaPago)
    {
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cta_cobrar "
                    + " SET valor_actual = valor_actual-" + valor
                    + " , fecha_modificacion = '"+fechaPago+"'"
                    + " WHERE id_cta_cobrar= " + idCtaCobrar;
                   
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
    
     public boolean actualizarSaldo(String idCtaCobrar, double valor)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cta_cobrar "
                    + " SET valor_actual = valor_actual-" + valor
                    + " , fecha_modificacion = now()"
                    + " WHERE id_cta_cobrar= " + idCtaCobrar;
                   
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
    
    public boolean registrarCancelacionHistorico(String idCtaCobrar, String fecha)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cta_cobrar "
                    + " SET fecha_cancelacion = '" + fecha + "',"
                    + " estado = 'C'"
                    + " WHERE id_cta_cobrar= " + idCtaCobrar;
                   
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
    
    public boolean registrarCancelacion(String idCtaCobrar)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cta_cobrar "
                    + " SET fecha_cancelacion = now(),"
                    + " estado = 'C'"
                    + " WHERE id_cta_cobrar= " + idCtaCobrar;
                   
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
    
    public String consultarSaldoCta(String idCtaCobrar)
    {          
        String valorFacturado = ""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT valor "
                    + " FROM ck_cta_cobrar " 
                    + " WHERE id_cta_cobrar = " + idCtaCobrar;
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                valorFacturado = bd.resultado.getString("valor");              
            }
            //return valorFacturado;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            valorFacturado = null;
        }   
        return valorFacturado;
    }
}
