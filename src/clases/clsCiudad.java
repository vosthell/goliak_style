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
public class clsCiudad {
    clsConexion bd = new clsConexion(); 
    String sql;
    
    public ArrayList<clsComboBox>  consultarCiudad(String idProvincia){            
        ArrayList<clsComboBox> data = new ArrayList<clsComboBox>(); 
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT id_ciudad, ciudad_descripcion"
                    + " FROM ck_ciudad"
                    + " WHERE estado = 'A'"
                    + " AND id_provincia = " + idProvincia
                    + " ORDER BY ciudad_descripcion";
            bd.resultado = bd.sentencia.executeQuery(sql);
              
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsComboBox oListaTemporal = new clsComboBox(bd.resultado.getString("id_ciudad"),bd.resultado.getString("ciudad_descripcion"));
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
