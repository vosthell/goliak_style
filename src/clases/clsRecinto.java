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
public class clsRecinto {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    public ArrayList<clsComboBox>  consultarRecintos(){            
        ArrayList<clsComboBox> data = new ArrayList<clsComboBox>();  
        try{          
            bd.conectarBaseDeDatos();
            sql = "SELECT id_recinto, descripcion"
                    + " FROM ck_recinto"
                    + " WHERE estado = 'A' "
                    + " ORDER BY descripcion";
            bd.resultado = bd.sentencia.executeQuery(sql);
             
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsComboBox oListaTemporal = new clsComboBox(bd.resultado.getString("id_recinto"),bd.resultado.getString("descripcion"));
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
   
    public ArrayList<clsComboBox>  consultarSectores(){            
        ArrayList<clsComboBox> data = new ArrayList<clsComboBox>();   
        try{          
            bd.conectarBaseDeDatos();
            sql = " SELECT id_sector " 
                    + " FROM ck_recinto "
                    + " WHERE estado='A'"
                    + " GROUP BY id_sector"
                    + " ORDER BY id_sector";
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsComboBox oListaTemporal = new clsComboBox(bd.resultado.getString("id_sector"),bd.resultado.getString("id_sector"));
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
