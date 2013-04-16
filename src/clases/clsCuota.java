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
public class clsCuota {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    public ArrayList<clsComboBox>  consultarCuotas(){            
         ArrayList<clsComboBox> data = new ArrayList<clsComboBox>();  
         try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_cuota, descripcion"
                    + " FROM ck_cuota"
                    + " WHERE estado = 'A' "
                    + " ORDER BY descripcion";
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsComboBox oListaTemporal = new clsComboBox(bd.resultado.getString("id_cuota"),bd.resultado.getString("descripcion"));
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
