/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Kaiser
 */
public class clsComboBox {
    private String descripcion;
    private String codigo;
    
    public clsComboBox()
    {}

    public clsComboBox(String codigo, String descripcion)
      {
        this.codigo=codigo;
        this.descripcion=descripcion;
      }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    @Override
   public String toString()
    {
     return descripcion;
    }
}
