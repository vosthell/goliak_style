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
public class clsEmpleado {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    public boolean comprobarCedula(String cedula)
    {
        boolean exito = false;
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_personal, cedula"
                    + " FROM ck_personal"
                    + " WHERE cedula = '" + cedula + "'";                  
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
    
    public boolean insertarRegistro(String cedula, String n1, String n2, String a1, String a2, 
                                        String convencional, 
                                        String celular, 
                                        String direccion, 
                                        String ciudad, 
                                        String prov, 
                                        String email, 
                                        String fecha_nacimiento)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_personal"
                    + " (nombre1, nombre2,"
                    + " apellido1, apellido2, tlf_convencional,"
                    + " tlf_celular, cedula, direccion, id_provincia, "
                    + " id_ciudad, email, fecha_nacimiento)"
                    + " VALUES('" + n1 + "', '" + n2 + "', '" + a1 + "', '" + a2 + "', "
                    + " '"+convencional+"', '"+celular+"', '"+cedula+"', '"+direccion+"', '"+prov+"'"
                    + ", '"+ciudad+"', '"+ email +"', '" + fecha_nacimiento + "')";           
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
    
    public ArrayList<clsComboBox>  consultar_personal(){            
         ArrayList<clsComboBox> data = new ArrayList<clsComboBox>();  
         try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_personal, apellido1 || ' ' || apellido2 || ' ' || nombre1 descripcion"
                    + " FROM ck_personal"
                    + " WHERE estado = 'A' "
                    + " ORDER BY apellido1";
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsComboBox oListaTemporal = new clsComboBox(bd.resultado.getString("id_personal"),bd.resultado.getString("descripcion"));
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
}
