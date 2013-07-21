/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.table.renderer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Abner
 */
public class ViewCoreFirstColumnTableRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if((row % 2) == 0){
            cellComponent.setBackground(Color.YELLOW);
        }
        else {
            cellComponent.setBackground(Color.WHITE);
        }

       
        this.setHorizontalAlignment(JLabel.CENTER);
        return cellComponent;
    }

}
