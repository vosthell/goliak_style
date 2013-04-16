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
public class clsFacturero {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private int idFacturero;
    private int inicio;
    private int fin;
    private int actual;
    private String estado;
    
    public int getIdFacturero() {
        return idFacturero;
    }
    
    public void setIdFacturero(int idFacturero) {
        this.idFacturero = idFacturero;
    }  
    
    public int getInicio() {
        return inicio;
    }
    
    public void setInicio(int inicio) {
        this.inicio = inicio;
    }
    
    public int getFin() {
        return fin;
    }
    
    public void setFin(int fin) {
        this.fin = fin;
    }
    
    public int getActual() {
        return actual;
    }
    
    public void setActual(int actual) {
        this.actual = actual;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public ArrayList<clsFacturero>  consultaDataFacturero(String mostrarActivos, String mostrarTerminados ){            
        ArrayList<clsFacturero> data = new ArrayList<clsFacturero>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_facturero, secuencia_inicio,"
                    + " secuencia_fin, secuencia_actual, estado "           
                    + " FROM ck_facturero"
                    + " WHERE id_facturero is not null";
            if(mostrarActivos.equals("S")&&mostrarTerminados.equals("S"))
                sql = sql + "";
            else if(mostrarActivos.equals("S")&&mostrarTerminados.equals("N"))
                sql = sql + " AND (estado = 'A')";
            else if(mostrarActivos.equals("N")&&mostrarTerminados.equals("S"))
                sql = sql + " AND (estado = 'T')";
            else if(mostrarActivos.equals("N")&&mostrarTerminados.equals("N"))
                sql = sql + "AND (estado = 'A') AND (estado = 'T')";
            
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            while(bd.resultado.next()){
                clsFacturero oListaTemporal = new clsFacturero();
                oListaTemporal.setIdFacturero(bd.resultado.getInt("id_facturero"));                           
                oListaTemporal.setInicio(bd.resultado.getInt("secuencia_inicio"));
                oListaTemporal.setFin(bd.resultado.getInt("secuencia_fin"));
                oListaTemporal.setActual(bd.resultado.getInt("secuencia_actual"));
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
    
    public boolean insertarRegistro(String secuenciaInicio, String secuenciaFin)
    {       
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_facturero(secuencia_inicio, secuencia_fin, secuencia_actual)"                   
                    + " VALUES(" + secuenciaInicio + ", " + secuenciaFin + ", " + secuenciaInicio + ")";           
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
    
     public ArrayList<clsComboBox>  consultarFactureros(){            
        ArrayList<clsComboBox> data = new ArrayList<clsComboBox>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_facturero"
                    + " FROM ck_facturero"
                    + " WHERE estado = 'A' "
                    + " ORDER BY id_facturero";
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsComboBox oListaTemporal = new clsComboBox(bd.resultado.getString("id_facturero"),bd.resultado.getString("id_facturero"));
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
     
   public String seleccionarFacturaActual(int idFacturero)
   {          
        String facturaActual = ""; 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT secuencia_actual"
                    + " FROM ck_facturero"
                    + " WHERE id_facturero = " + idFacturero;
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                facturaActual = bd.resultado.getString("secuencia_actual");              
            }
            //return porcentaje;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            facturaActual = null;
        }  
        bd.desconectarBaseDeDatos();
        return facturaActual;
    }
   
   public String consultarEstado(int idFacturero)
   {          
        String vEstado = "";  
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT estado"
                    + " FROM ck_facturero"
                    + " WHERE id_facturero = " + idFacturero;
            System.out.println(sql);        
            bd.resultado = bd.sentencia.executeQuery(sql);             
            while(bd.resultado.next()){               
                vEstado = bd.resultado.getString("estado");              
            }
            //return porcentaje;            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            vEstado = null;
        }  
        bd.desconectarBaseDeDatos();
        return vEstado;
    }
   
    public boolean actualizarFacturero(int idFacturero)
    {
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_facturero"
                    + " SET secuencia_actual = secuencia_actual + 1"                    
                    + " WHERE id_facturero = " + idFacturero;      
           
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
    
     public boolean modificarEstado(int idFacturero)
    {
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_facturero"
                    + " SET estado = 'T'"                    
                    + " WHERE secuencia_fin < secuencia_actual"
                    + " AND id_facturero = " + idFacturero;      
           
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
