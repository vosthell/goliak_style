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
public class clsCabecera {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private String cedula;
    private String name_completo;
    private String fact_referencia;
    private String comentario;
    private Double efectivo;
    private Double total;
    private Double saldo;
    private String descripcion;
    private String valor;
    private String fecha;
    private Double descuento;
    private Double iva;
    private Double tarifaCero;
    private Double tarifaIVA;
    private int idCabeceraMovi;
    private String estado;
    
    public int getIdCabeceraMovi() {
        return idCabeceraMovi;
    }
    
    public void setIdCabeceraMovi(int idCabeceraMovi) {
        this.idCabeceraMovi = idCabeceraMovi;
    }
    
    public String getCedula() {
        return cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public String getNameCompleto() {
        return name_completo;
    }
    
    public void setNameCompleto(String name_completo) {
        this.name_completo = name_completo;
    }
    
    public String getFactReferencia() {
        return fact_referencia;
    }
    
    public void setFactReferencia(String fact_referencia) {
        this.fact_referencia = fact_referencia;
    }
    
    public String getComentario() {
        return comentario;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public Double getEfectivo() {
        return efectivo;
    }
    
    public void setEfectivo(Double efectivo) {
        this.efectivo = efectivo;
    }
    
    public Double getTotal() {
        return total;
    }
    
    public void setTotal(Double total) {
        this.total = total;
    }
    
    public Double getSaldo() {
        return saldo;
    }
    
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
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
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public Double getDescuento() {
        return descuento;
    }
    
    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }
    
    public Double getIVA() {
        return iva;
    }
    
    public void setIVA(Double iva) {
        this.iva = iva;
    }  
    
    public Double getTarifaCero() {
        return tarifaCero;
    }
    
    public void setTarifaCero(Double tarifaCero) {
        this.tarifaCero = tarifaCero;
    }
    
    public Double getTarifaIVA() {
        return tarifaIVA;
    }
    
    public void setTarifaIVA(Double tarifaIVA) {
        this.tarifaIVA = tarifaIVA;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public boolean insertarRegistro(int idCliente, String idUser, String idCajero, String total, String idEmpresa, String cajaAbierta, String descuento, String iva, String factura, String tarifa_iva, String tarifa_cero)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cabecera_movi(codigo, id_usuario, id_cajero, "
                    + " total, efectivo, fecha, id_empresa, id_caja_operacion, descuento, iva, "
                    + " fact_referencia, base_tarifa_0, base_tarifa_iva, saldo)"                   
                    + " VALUES(" + idCliente + ", "+idUser+", "+idCajero+", "
                    + total+", "+total+", now(), "+idEmpresa+", "+cajaAbierta+", "+descuento+", "+iva+", '" + factura + "', "
                    + tarifa_cero + ", "+tarifa_iva+", 0.00)";           
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
    
    public boolean insertarRegistroCompras(int idProveedor, String idUser, String total, String idEmpresa, String descuento, String iva, String tarifa_iva, String tarifa_cero, String factura_referencia)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cabecera_movi_compras(id_proveedor, id_usuario, "
                    + " total, efectivo, fecha, id_empresa, descuento, iva, "
                    + " base_tarifa_0, base_tarifa_iva, fact_referencia)"                   
                    + " VALUES(" + idProveedor + ", "+idUser+", "
                    + total+", "+total+", now(), "+idEmpresa+", "+descuento+", "+iva+", "
                    + tarifa_cero + ", "+tarifa_iva+", '"+factura_referencia+"')";           
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
      
    public String obtenerUltimaFactura(String idCajero)
    {          
        String ultmFactura=""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT max(id_cabecera_movi) AS maximo"
                    + " FROM ck_cabecera_movi"
                    + " WHERE id_cajero = " + idCajero;
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                ultmFactura = bd.resultado.getString("maximo");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            ultmFactura = null;
        }     
        bd.desconectarBaseDeDatos();
        if (ultmFactura==null)
        {
           ultmFactura="0"; 
        }
        return ultmFactura;
    }
    
    public int obtenerUltimaFacturaReferencia(String idCabecera)
    {          
        int dato = 0; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT fact_referencia"
                    + " FROM ck_cabecera_movi"
                    + " WHERE id_cabecera_movi = " + idCabecera;
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                dato = bd.resultado.getInt("fact_referencia");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            dato = 0;
        }     
        bd.desconectarBaseDeDatos();
        return dato+1;
    }
    
    public int obtenerUltimaFacturaRegistrada()
    {          
        int ultmFacturaUsada=0; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT max(id_cabecera_movi) AS maximo"
                    + " FROM ck_cabecera_movi";                    
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
               ultmFacturaUsada = bd.resultado.getInt("maximo");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            ultmFacturaUsada = 0;
        }     
        bd.desconectarBaseDeDatos();
        return ultmFacturaUsada+1;
    }
    
    public int obtenerUltimaFacturaCompras(String idUser)
    {          
        int ultimaFactura = 0; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT max(id_cabecera_movi) AS maximo"
                    + " FROM ck_cabecera_movi_compras"
                    + " WHERE id_usuario = " + idUser;
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                ultimaFactura = bd.resultado.getInt("maximo");              
            }
            //return nombreCajero;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            ultimaFactura = 0;
        }     
        bd.desconectarBaseDeDatos();
        return ultimaFactura;
    }
    
    public boolean insertarRegistroCreditoMonica(int idCliente, String idUser, String idCajero, String total, String idEmpresa, String cajaAbierta, String factReferencia, String comentario, String saldo, String efectivo, String fecha)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cabecera_movi(codigo, id_usuario, id_cajero, total, "
                    + " fecha, id_empresa, id_caja_operacion, fact_referencia,"
                    + " comentario, saldo, efectivo)"                   
                    + " VALUES(" + idCliente + ", "+idUser+", "+idCajero+", "+total
                    + " , '"+fecha+"', "+idEmpresa+", "+cajaAbierta+", '"+factReferencia+"',"
                    + " '"+comentario+"', "+saldo+", "+efectivo+")";           
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
    
    public boolean insertarRegistroCredito(int idCliente, String idUser, String idCajero, String total, String idEmpresa, String cajaAbierta, String comentario, String saldo, String efectivo, String descuento, String iva, String factura, String tarifaIva, String tarifaCero)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cabecera_movi(codigo, id_usuario, id_cajero, total, "
                    + " fecha, id_empresa, id_caja_operacion, "
                    + " comentario, saldo, efectivo, descuento, iva, fact_referencia, base_tarifa_0, base_tarifa_iva)"                   
                    + " VALUES(" + idCliente + ", "+idUser+", "+idCajero+", "+total
                    + " , now(), "+idEmpresa+", "+cajaAbierta+", "
                    + " '"+comentario+"', "+saldo+", "+efectivo+", "+descuento+", "+iva+", '"+factura+"', "+tarifaCero+", "+tarifaIva+")";           
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
    
    public String obtenerUltimaFacturaCreditoMonica(String idUser)
    {          
        String nombreCajero=""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT max(id_cabecera_movi) AS maximo"
                    + " FROM ck_cabecera_movi"
                    + " WHERE id_usuario = " + idUser;
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                nombreCajero = bd.resultado.getString("maximo");              
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
    
    public boolean insertarCtaCobrarMonica(String ultFactura, String comentario, String saldo, String p_fecha, String p_fecha_cancelacion)
    {       
        boolean exito = false;
        try
        {           
            System.out.println(ultFactura +" "+ comentario +" "+ saldo +" "+ p_fecha + " "+ p_fecha_cancelacion);
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cta_cobrar(id_cabecera_movi, descripcion, valor, "
                    + " fecha_creacion, valor_actual, fecha_modificacion, fecha_cancelacion_sistema)"                   
                    + " VALUES("+ultFactura+", '"+comentario+"', "+saldo+", '"+p_fecha+"', "
                    + saldo+", '"+p_fecha+"', '" + p_fecha_cancelacion + "')";           
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
    
    public boolean insertarCtaCobrar(String ultFactura, String comentario, String saldo, String p_fecha_cancelacion)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cta_cobrar(id_cabecera_movi, descripcion, valor, fecha_creacion, "
                    + " valor_actual, fecha_modificacion, fecha_cancelacion_sistema)"                   
                    + " VALUES("+ultFactura+", '"+comentario+"', "+saldo+", now(), "+saldo+", "
                    + " now(), '" + p_fecha_cancelacion + "')";           
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
    
    public boolean insertarValorCuota(String ultFactura, String idCuota, String valor)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_rel_cabecera_cuota(id_cabecera_movi , id_cuota, valor)"                   
                    + " VALUES("+ultFactura+", "+idCuota+", "+valor+")";           
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
    
    public ArrayList<clsCabecera>  consultarDataCabeceraCredito(int idCabecera){            
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_cabecera_movi id_cabecera_movi, a.codigo, b.name_completo, "
                    + " b.cedula, a.id_usuario, c.name,  "
                    + " a.estado, total, saldo, efectivo,  "
                    + " fecha, fact_referencia, comentario,  "
                    + " id_cajero, id_empresa, id_caja_operacion, "
                    + " d.valor valor, e.descripcion descripcion, "
                    + " base_tarifa_0, base_tarifa_iva, descuento, iva"
                    + " FROM ck_cabecera_movi AS a inner join ck_cliente AS b on a.codigo = b.codigo "
                    + " inner join ck_usuario AS c on a.id_usuario = c.id_usuario "
                    + " inner join ck_rel_cabecera_cuota AS d on a.id_cabecera_movi = d.id_cabecera_movi"
                    + " inner join ck_cuota as e on d.id_cuota = e.id_cuota"
                    + " WHERE a.id_cabecera_movi=" + idCabecera;
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsCabecera oListaTemporal = new clsCabecera();
                
                oListaTemporal.setCedula(bd.resultado.getString("cedula"));                
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setComentario(bd.resultado.getString("comentario"));
                oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));
                oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                oListaTemporal.setDescripcion(bd.resultado.getString("descripcion"));
                oListaTemporal.setValor(bd.resultado.getString("valor"));
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                oListaTemporal.setTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                oListaTemporal.setTarifaIVA(bd.resultado.getDouble("base_tarifa_iva"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setIVA(bd.resultado.getDouble("iva"));
                
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
    
    public ArrayList<clsCabecera>  consultarDataCabecera(int idCabecera){            
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_cabecera_movi id_cabecera_movi, a.codigo, b.name_completo, "
                    + " b.cedula, a.id_usuario, c.name,  "
                    + " a.estado, total, saldo, efectivo,  "
                    + " fecha, fact_referencia, comentario,  "
                    + " id_cajero, id_empresa, id_caja_operacion, descuento, iva, base_tarifa_0, base_tarifa_iva "              
                    + " FROM ck_cabecera_movi AS a inner join ck_cliente AS b on a.codigo = b.codigo "
                    + " inner join ck_usuario AS c on a.id_usuario = c.id_usuario    "               
                    + " WHERE a.id_cabecera_movi=" + idCabecera;
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsCabecera oListaTemporal = new clsCabecera();
                
                oListaTemporal.setCedula(bd.resultado.getString("cedula"));                
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setComentario(bd.resultado.getString("comentario"));
                oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));
                oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setIVA(bd.resultado.getDouble("iva"));
                oListaTemporal.setTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                oListaTemporal.setTarifaIVA(bd.resultado.getDouble("base_tarifa_iva"));            
                
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
    
     public ArrayList<clsCabecera>  consultaFacturasRealizadas(String idCajaAbierta){            
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_cabecera_movi id_cabecera_movi, a.codigo, b.name_completo, "
                    + " b.cedula, a.id_usuario, "
                    + " a.estado, total, saldo, efectivo,  "
                    + " fecha, fact_referencia, comentario,  "
                    + " id_cajero, id_empresa, id_caja_operacion, descuento, iva, base_tarifa_0, base_tarifa_iva "              
                    + " FROM ck_cabecera_movi AS a inner join ck_cliente AS b on a.codigo = b.codigo "                                
                    + " WHERE id_caja_operacion = "+idCajaAbierta
                    + " AND a.estado='A'";
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsCabecera oListaTemporal = new clsCabecera();
                oListaTemporal.setIdCabeceraMovi(bd.resultado.getInt("id_cabecera_movi"));   
                oListaTemporal.setCedula(bd.resultado.getString("cedula"));                
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setComentario(bd.resultado.getString("comentario"));
                oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));
                oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setIVA(bd.resultado.getDouble("iva"));
                oListaTemporal.setTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                oListaTemporal.setTarifaIVA(bd.resultado.getDouble("base_tarifa_iva"));            
                
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
    
    public boolean eliminarCabecera(int idCabecera)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "UPDATE ck_cabecera_movi"
                    + " SET estado = 'I'"
                    + " WHERE id_cabecera_movi = " + idCabecera;           
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
    
    public ArrayList<clsCabecera>  consultarDataCabeceraFactura(String codeFactura, String hoy){            
        ArrayList<clsCabecera> data = new ArrayList<clsCabecera>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT a.id_cabecera_movi id_cabecera_movi, a.codigo, b.name_completo, "
                    + " b.cedula, a.id_usuario, c.name,  "
                    + " a.estado, total, saldo, efectivo,  "
                    + " fecha::date || ' ' || fecha::time fecha, fact_referencia, comentario,  "
                    + " id_cajero, id_empresa, id_caja_operacion, descuento, iva, "
                    + " base_tarifa_0, base_tarifa_iva, a.estado estado"              
                    + " FROM ck_cabecera_movi AS a "
                    + " inner join ck_cliente AS b on a.codigo = b.codigo "
                    + " inner join ck_usuario AS c on a.id_usuario = c.id_usuario    "               
                    + " WHERE a.fact_referencia = '" + codeFactura + "'";
            if(hoy.equals("S"))
            {    
                sql = sql + " AND fecha::date = now()::date";
            }
            else if(hoy.equals("N"))
            {
                
            }
            System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsCabecera oListaTemporal = new clsCabecera();
                oListaTemporal.setIdCabeceraMovi(bd.resultado.getInt("id_cabecera_movi"));   
                oListaTemporal.setCedula(bd.resultado.getString("cedula"));                
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                oListaTemporal.setFactReferencia(bd.resultado.getString("fact_referencia"));
                oListaTemporal.setComentario(bd.resultado.getString("comentario"));
                oListaTemporal.setEfectivo(bd.resultado.getDouble("efectivo"));
                oListaTemporal.setTotal(bd.resultado.getDouble("total"));
                oListaTemporal.setSaldo(bd.resultado.getDouble("saldo"));
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                oListaTemporal.setDescuento(bd.resultado.getDouble("descuento"));
                oListaTemporal.setIVA(bd.resultado.getDouble("iva"));
                oListaTemporal.setTarifaCero(bd.resultado.getDouble("base_tarifa_0"));
                oListaTemporal.setTarifaIVA(bd.resultado.getDouble("base_tarifa_iva"));  
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
}
