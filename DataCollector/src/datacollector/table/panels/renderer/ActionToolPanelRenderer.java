/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.table.panels.renderer;

import datacollector.table.panels.ActionToolPanel;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Abner
 */
public class ActionToolPanelRenderer extends ActionToolPanel implements TableCellRenderer{

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
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
