/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kaiser
 */
public class clsCliente {
    clsConexion bd = new clsConexion(); 
    String sql;
     
    private int codigo;
    private String name_completo;
    private String cedula;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String tlf_convencional;
    private String tlf_celular;
    private Integer provincia;
    private String direccion;
    private Integer id_termino;
    private String creditomax;
    private Integer idCiudad;
    private String id_recinto;
    private String fecha_nacimiento;
    private String estado;
    private String email;
    
    public int getCodigo() {
        return codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getNameCompleto() {
        return name_completo;
    }
    
    public void setNameCompleto(String name_completo) {
        this.name_completo = name_completo;
    }
    
    public String getCedula() {
        return cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public String getNombre1() {
        return nombre1;
    }
    
    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }
    
    public String getNombre2() {
        return nombre2;
    }
    
    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }
    
    public String getApellido1() {
        return apellido1;
    }
    
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }
    
    public String getApellido2() {
        return apellido2;
    }
    
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
    
    public String getTlfConvencional() {
        return tlf_convencional;
    }
    
    public void setTlfConvencional(String tlf_convencional) {
        this.tlf_convencional = tlf_convencional;
    }
    
     public String getTlfCelular() {
        return tlf_celular;
    }
    
    public void setTlfCelular(String tlf_celular) {
        this.tlf_celular = tlf_celular;
    }
    
    public int getProvincia() {
        return provincia;
    }
    
    public void setProvincia(int provincia) {
        this.provincia = provincia;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public int getIdTermino() {
        return id_termino;
    }
    
    public void setIdTermino(int id_termino) {
        this.id_termino = id_termino;
    }
    
    public String getCreditoMax() {
        return creditomax;
    }
    
    public void setCreditoMax(String creditomax) {
        this.creditomax = creditomax;
    }
    
    public int getIdCiudad() {
        return idCiudad;
    }
    
    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }
    
    public String getIdRecinto() {
        return id_recinto;
    }
    
    public void setIdRecinto(String id_recinto) {
        this.id_recinto = id_recinto;
    }     
    
    public String getFechaNacimiento() {
        return fecha_nacimiento;
    }
    
    public void setFechaNacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }    
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public ArrayList<clsComboBox>  consultarClientes(){            
        ArrayList<clsComboBox> data = new ArrayList<clsComboBox>();   
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT cedula, name_completo"
                  + " FROM ck_cliente";
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            if(bd.resultado.next())
            {   
                do 
                { 
                    clsComboBox oListaTemporal = new clsComboBox(bd.resultado.getString("cedula"),bd.resultado.getString("name_completo"));
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
     
    public ArrayList<clsCliente>  consultarDataCliente(String cedula){            
        ArrayList<clsCliente> data = new ArrayList<clsCliente>();   
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT codigo, cedula, name_completo, email"
                    + " FROM ck_cliente"
                    + " WHERE cedula = '" + cedula + "'"
                    + " AND estado = 'A'";
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            while(bd.resultado.next()){
                clsCliente oListaTemporal = new clsCliente();
                oListaTemporal.setCedula(bd.resultado.getString("cedula"));
                oListaTemporal.setCodigo(bd.resultado.getInt("codigo"));
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
                oListaTemporal.setEmail(bd.resultado.getString("email"));
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
     
    public List<String> consultarCedulas(){            
         List<String> data = new ArrayList<String>();  
         try{
            bd.conectarBaseDeDatos();
            sql = "SELECT cedula, name_completo"
                    + " FROM ck_cliente"
                    + " WHERE estado = 'A'";
            bd.resultado = bd.sentencia.executeQuery(sql);            
            String line="";
            if(bd.resultado.next())
            {   
                do 
                { 
                    line = bd.resultado.getString("cedula");                   
                    data.add(line);
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
    
    public boolean insertarRegistro(String cedula, String n1, String n2, 
            String a1, String a2, String convencional, String celular, 
            String direccion, String ciudad, String credito, String prov, 
            String termino, String email, String fechaNac, String nombre_completo)
    {       
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();          
            sql = "INSERT INTO ck_cliente"
                    + " (nombre1, nombre2,"
                    + " apellido1, apellido2, name_completo, tlf_convencional,"
                    + " tlf_celular, cedula, direccion, provincia, "
                    + " id_termino, creditoMax, id_ciudad, fecha_registro, email, fecha_nacimiento)"
                    + " VALUES('" + n1 + "', '" + n2 + "', '" + a1 + "', '" + a2 + "', '" + nombre_completo + "', "
                    + " '"+convencional+"', '"+celular+"', '"+cedula+"', '"+direccion+"', '"+prov+"', " +termino
                    + ", "+credito+", '"+ciudad+"', now(), '"+ email +"', '" + fechaNac + "')";           
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
    
    public boolean comprobarCedula(String cedula)
    {
        boolean exito = false;
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT codigo, cedula, name_completo"
                    + " FROM ck_cliente"
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
    
    public ArrayList<clsCliente> consultaDataCliente(String cedula)
    {
        ArrayList <clsCliente> data = new ArrayList<clsCliente>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT codigo, nombre1, nombre2, apellido1, apellido2, tlf_convencional,"
                    + " tlf_celular, cedula, provincia, direccion, creditomax,"
                    + " id_ciudad, name_completo, id_recinto, estado, id_termino, fecha_nacimiento"
                    + " FROM ck_cliente"
                    + " WHERE cedula = '" + cedula + "'"
                    + " AND estado = 'A'";
            bd.resultado = bd.sentencia.executeQuery(sql);           
            
            while(bd.resultado.next()){
                clsCliente oListaTemporal = new clsCliente();
                oListaTemporal.setCodigo(bd.resultado.getInt("codigo")); 
                oListaTemporal.setNombre1(bd.resultado.getString("nombre1")); 
                oListaTemporal.setNombre2(bd.resultado.getString("nombre2")); 
                oListaTemporal.setApellido1(bd.resultado.getString("apellido1")); 
                oListaTemporal.setApellido2(bd.resultado.getString("apellido2")); 
                oListaTemporal.setTlfConvencional(bd.resultado.getString("tlf_convencional")); 
                oListaTemporal.setTlfCelular(bd.resultado.getString("tlf_celular")); 
                oListaTemporal.setProvincia(bd.resultado.getInt("provincia")); 
                oListaTemporal.setDireccion(bd.resultado.getString("direccion")); 
                oListaTemporal.setIdTermino(bd.resultado.getInt("id_termino"));  
                oListaTemporal.setCreditoMax(bd.resultado.getString("creditomax")); 
                oListaTemporal.setIdCiudad(bd.resultado.getInt("id_ciudad")); 
                oListaTemporal.setIdRecinto(bd.resultado.getString("id_recinto")); 
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo")); 
                oListaTemporal.setFechaNacimiento(bd.resultado.getString("fecha_nacimiento")); 
                oListaTemporal.setEstado(bd.resultado.getString("estado")); 
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
    
    public ArrayList<clsCliente> consultaDataClienteTodosAI(String cedula)
    {
        ArrayList <clsCliente> data = new ArrayList<clsCliente>();  
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT codigo, nombre1, nombre2, apellido1, apellido2, tlf_convencional,"
                    + " tlf_celular, cedula, provincia, direccion, creditomax,"
                    + " id_ciudad, name_completo, id_recinto, estado, id_termino, fecha_nacimiento, email"
                    + " FROM ck_cliente"
                    + " WHERE cedula = '" + cedula + "'";
                    
            bd.resultado = bd.sentencia.executeQuery(sql);           
            
            while(bd.resultado.next()){
                clsCliente oListaTemporal = new clsCliente();
                oListaTemporal.setCodigo(bd.resultado.getInt("codigo")); 
                oListaTemporal.setNombre1(bd.resultado.getString("nombre1")); 
                oListaTemporal.setNombre2(bd.resultado.getString("nombre2")); 
                oListaTemporal.setApellido1(bd.resultado.getString("apellido1")); 
                oListaTemporal.setApellido2(bd.resultado.getString("apellido2")); 
                oListaTemporal.setTlfConvencional(bd.resultado.getString("tlf_convencional")); 
                oListaTemporal.setTlfCelular(bd.resultado.getString("tlf_celular")); 
                oListaTemporal.setProvincia(bd.resultado.getInt("provincia")); 
                oListaTemporal.setDireccion(bd.resultado.getString("direccion")); 
                oListaTemporal.setIdTermino(bd.resultado.getInt("id_termino"));  
                oListaTemporal.setCreditoMax(bd.resultado.getString("creditomax")); 
                oListaTemporal.setIdCiudad(bd.resultado.getInt("id_ciudad")); 
                oListaTemporal.setIdRecinto(bd.resultado.getString("id_recinto")); 
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo")); 
                oListaTemporal.setFechaNacimiento(bd.resultado.getString("fecha_nacimiento")); 
                oListaTemporal.setEstado(bd.resultado.getString("estado")); 
                oListaTemporal.setEmail(bd.resultado.getString("email")); 
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
    
    public boolean modificarRegistro(int p_codigo, String p_cedula, 
            String p_nombre1, String p_nombre2, String p_apellido1, 
            String p_apellido2, String p_convencional, String p_celular, 
            String p_direccion, String p_provincia, String p_ciudad, 
            String p_terminos, String p_credito, String p_fecha_nac, 
            String estado, String email, String p_nombre_completo)
    {
        boolean exito = false;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cliente"
                    + " SET cedula = '" + p_cedula + "',"                    
                    + " nombre1 = '" + p_nombre1 + "',"
                    + " nombre2 = '" + p_nombre2 + "',"
                    + " apellido1 = '" + p_apellido1 + "', "
                    + " apellido2 = '" + p_apellido2 + "', "
                    + " tlf_convencional = '" + p_convencional + "', "
                    + " tlf_celular = '" + p_celular + "', "
                    + " provincia = " + p_provincia + ", "
                    + " direccion = '" + p_direccion + "', "
                    + " id_termino = " + p_terminos + ", "
                    + " creditomax = " + p_credito + ", "
                    + " id_ciudad = " + p_ciudad + ", "
                    + " name_completo = '" + p_nombre_completo + "',"
                    + " fecha_nacimiento = '"+ p_fecha_nac+"',"
                    + " estado = '" + estado + "',"
                    + " email = '" + email + "'"
                    + " WHERE codigo = " + p_codigo;      
           
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
    
    /*ELIMINAR CLIENTE*/
    public boolean eliminarCliente(int p_codigo)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cliente"
                    + " SET estado = 'I'" 
                    + " WHERE codigo = " + p_codigo;      
           
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
    
    /*ACTUALIZAR EMAIL*/
    public boolean actualizar_email(int p_codigo, String p_email)
    {
        boolean exito;
        try
        {           
            bd.conectarBaseDeDatos();
            sql = "UPDATE ck_cliente"
                    + " SET email = '" + p_email + "'" 
                    + " WHERE codigo = " + p_codigo;      
           
            System.out.println("SQL enviado - actualizar_email:" + sql);
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
    
    public ArrayList<clsCliente>  consultarDataClientePorNombre(String nombre){            
        ArrayList<clsCliente> data = new ArrayList<clsCliente>();   
        try{
            bd.conectarBaseDeDatos();
            sql = "SELECT codigo, cedula, name_completo"
                    + " FROM ck_cliente"
                    + " WHERE upper(name_completo) like '%" + nombre + "%'"
                    + " AND estado = 'A'";
            bd.resultado = bd.sentencia.executeQuery(sql);
            
            while(bd.resultado.next()){
                clsCliente oListaTemporal = new clsCliente();
                oListaTemporal.setCedula(bd.resultado.getString("cedula"));
                oListaTemporal.setCodigo(bd.resultado.getInt("codigo"));
                oListaTemporal.setNameCompleto(bd.resultado.getString("name_completo"));
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
