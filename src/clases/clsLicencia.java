/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author Kaiser
 */
public class clsLicencia {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    private String idLicencia;
    private String codUno;
    private String codDos;
    private String fecha;
    private String hora;
    private String tiempo;   
    
    public String getIdLicencia() {
        return idLicencia;
    }
    
    public void setIdLicencia(String idLicencia) {
        this.idLicencia =  idLicencia;
    }
    
    public String getCodUno() {
        return codUno;
    }
    
    public void setCodUno(String codUno) {
        this.codUno =  codUno;
    }
    
    public String getCodDos() {
        return codDos;
    }
    
    public void setCodDos(String codDos) {
        this.codDos =  codDos;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha =  fecha;
    }
    
    public String getHora() {
        return hora;
    }
    
    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public String getTiempo() {
        return tiempo;
    }
    
    public void setTiempo(String tiempo) {
        this.tiempo =  tiempo;
    }
    
    public int consultaCantidad()
    {       
         int cantidad = 0;
         try{
            bd.conectarBaseDeDatos();
            sql = "SELECT count(*) cantidad "
                    + "  FROM ck_licencia";
            //System.out.println(sql);
            bd.resultado = bd.sentencia.executeQuery(sql);
             
            if(bd.resultado.next())
            {               
                cantidad = bd.resultado.getInt("cantidad");               
            }
            else
            { 
                cantidad = 0;
            }            
        }
        catch(Exception ex)
        {
            System.out.print(ex);
            cantidad = 0;
        }           
        bd.desconectarBaseDeDatos();
        return cantidad;
    } 
    
    public ArrayList<clsLicencia>  consultarDataLicencia(String codigo1, String codigo2){            
         ArrayList<clsLicencia> data = new ArrayList<clsLicencia>();  
         try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_licencia, codigo_licencia, codigo_licencia_2, fecha_registro::date fecha, fecha_registro::time hora, estado, substr(codigo_licencia_2, 1,1) tiempo"
                    + " FROM ck_licencia"
                    + " WHERE codigo_licencia = '" + codigo1+"'"
                    + " AND substr(codigo_licencia_2, 2, LENGTH(codigo_licencia_2)+1) = '" + codigo2 + "'"; 
            bd.resultado = bd.sentencia.executeQuery(sql);
            System.out.println(sql);
            while(bd.resultado.next()){
                clsLicencia oListaTemporal = new clsLicencia();
                oListaTemporal.setIdLicencia(bd.resultado.getString("id_licencia"));
                oListaTemporal.setCodUno(bd.resultado.getString("codigo_licencia"));
                oListaTemporal.setCodDos(bd.resultado.getString("codigo_licencia_2"));
                oListaTemporal.setFecha(bd.resultado.getString("fecha"));
                oListaTemporal.setHora(bd.resultado.getString("hora"));
                oListaTemporal.setTiempo(bd.resultado.getString("tiempo"));
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
    
    public boolean consultarValidez(String codigo1, String codigo2)
    {
        boolean exito;
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT * "
                    + " FROM ck_licencia"
                    + " WHERE codigo_licencia = '" + codigo1+"'"
                    + " AND substr(codigo_licencia_2, 2, LENGTH(codigo_licencia_2)+1) = '" + codigo2 + "'";                 
            System.out.println(sql);
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
           System.out.print("ERROR EN SQL:"+ex);
           exito = false;
        }   
        bd.desconectarBaseDeDatos();
        return exito;
    }
    
    public boolean consultarVencimiento(String codigo1, String codigo2, String fechaVencimiento)
    {
        boolean exito;
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT * "
                    + " FROM ck_licencia"
                    + " WHERE codigo_licencia = '" + codigo1+"'"
                    + " AND substr(codigo_licencia_2, 2, LENGTH(codigo_licencia_2)+1) = '" + codigo2 + "'"
                    + " AND NOW()::date <= '"+ fechaVencimiento +"'";                 
            System.out.println(sql);
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
           System.out.print("ERROR EN SQL:"+ex);
           exito = false;
        }   
        bd.desconectarBaseDeDatos();
        return exito;
    }
}
