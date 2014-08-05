/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package index;

import clases.clsLicencia;
import clases.clsParametros;
import clases.clsSerial;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author Kaiser
 */
public class main {
    public static String idUser;
    public static String nameUser;
    public static String idEmpresa = "1";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {       
        try{
            /*UIManager.setLookAndFeel(new com.lipstikLF.LipstikLookAndFeel());*/
            /*UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());*/
           javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        } 
        clsLicencia objLicencia = new clsLicencia();
        clsSerial objSerial = new clsSerial();
        clsParametros objParametros = new clsParametros();
        //verificar si hay licencia
        int licencia = objLicencia.consultaCantidad();
        if(licencia == 0)
        {
            frmMessLicencia frame = new  frmMessLicencia();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
        else
        {
            String serialDisco = objSerial.getSerialNumber("C");
            String serialDiscoCod = objSerial.codif(serialDisco);
            String mainBoard = objSerial.getMotherboardSN();
            String mainBoardCod = objSerial.codif(mainBoard);
            //verificar que pertenece a  esta maquina
            boolean exito = objLicencia.consultarValidez(serialDiscoCod, mainBoardCod);
            if(exito)
            {
                //VERIFICAR SI ESTA CADUCADA
                ArrayList<clsLicencia> dataLicencia = objLicencia.consultarDataLicencia(serialDiscoCod, mainBoardCod); 
                String tipoFechaCod = dataLicencia.get(0).getTiempo();
                String fechaRegistro = dataLicencia.get(0).getFecha();
                String tipoFecha = objSerial.decodif(tipoFechaCod);
                System.out.println(tipoFecha);

                Date date1= new Date();
                //date1 = DateFormat.parse(fechaRegistro);                
                Calendar fechaCarta2 = Calendar.getInstance();
                //fechaCarta2.setTime(date1); 
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
                String strFecha = fechaRegistro;        
                try
                {    
                    fechaCarta2.setTime(formatoDelTexto.parse(strFecha));
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                if(tipoFecha.equals("A"))
                    fechaCarta2.add(Calendar.DAY_OF_MONTH, 7);  
                if(tipoFecha.equals("B"))
                    fechaCarta2.add(Calendar.DAY_OF_MONTH, 15);       
                if(tipoFecha.equals("C"))
                    fechaCarta2.add(Calendar.MONTH, 1);  
                if(tipoFecha.equals("D"))
                    fechaCarta2.add(Calendar.MONTH, 6);   
                if(tipoFecha.equals("E"))
                    fechaCarta2.add(Calendar.YEAR, 1);   
                if(tipoFecha.equals("F"))
                    fechaCarta2.add(Calendar.YEAR, 3);   
                if(tipoFecha.equals("G"))
                    fechaCarta2.add(Calendar.YEAR, 5);

                //SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
                Date fecSegCarta = fechaCarta2.getTime();
                date1 = fecSegCarta;
                DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
                //DateFormat df2 = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy", new Locale("es", "EC"));
                String fechaCancelacion = df2.format(fecSegCarta);
                //this.lblTiempoCaducidad.setText(fechaCancelacion);
                boolean exitoVenc = objLicencia.consultarVencimiento(serialDiscoCod, mainBoardCod, fechaCancelacion);
                if(exitoVenc)
                {
                    frmIngreso frame = new  frmIngreso();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }               
                else
                {
                    JOptionPane.showMessageDialog(null, objParametros.consultaValor2("licencia_caducada"), "Mensaje", JOptionPane.WARNING_MESSAGE);
                }               
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Esta licencia es incorrecta", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }
            
        }
    }
}
