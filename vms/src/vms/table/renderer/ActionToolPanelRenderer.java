/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.table.renderer;

import vms.table.panels.ActionToolPanel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Abner
 */
public class ActionToolPanelRenderer extends ActionToolPanel implements TableCellRenderer{

    public ActionToolPanelRenderer()
    {
        /*this.setText("");
        this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/actiontool.png")));
        this.setHorizontalTextPosition(SwingConstants.RIGHT);
        this.setIconTextGap(40);

        JMenuItem item1 = new JMenuItem();
        item1.setBackground(Color.WHITE);
        
        this.setUI(item1.getUI());
        this.setBackground(Color.WHITE);

        //JMenuItem item1 = new JMenuItem();
        item1.setBackground(Color.WHITE);
        item1.setText("Stop");
        item1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stop_icon.png")));
       // this.setUI(new javax.swing.JButton().getUI());*/
        
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
       // Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
         if((row % 2) == 0){
         //   cellComponent.setBackground(Color.YELLOW);
        }
        else {
           // cellComponent.setBackground(Color.GRAY);
        }

       // this.setHorizontalAlignment(JLabel.CENTER);

        if ( isSelected )
        {
            setForeground( table.getSelectionForeground() );
            setBackground( table.getSelectionBackground() );
        }
        else
        {
            setForeground( table.getForeground() );
            setBackground( UIManager.getColor( "Button.background" ) );
        }
        
        return this;
    }

}
