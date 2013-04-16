package clases;
import java.awt.Color; 
import java.awt.Component; 
import javax.swing.JTable; 
import javax.swing.table.DefaultTableCellRenderer; 
public class colorFilaTable extends DefaultTableCellRenderer { 
    @Override 
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column ) 
    { 
        setEnabled(table == null || table.isEnabled());        
        
        if(String.valueOf(table.getValueAt(row,5)).equals("Terminado")) 
            setBackground(Color.red); 
        else setBackground(null); 
        
        super.getTableCellRendererComponent(table, value, selected, focused, row, column); 
        return this; 
    } 
}  

