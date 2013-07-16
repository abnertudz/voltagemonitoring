/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.table.panels.editor;

import java.awt.Component;
import java.util.EventObject;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Abner
 */
public class ActionToolPanelEditor implements TableCellEditor{


    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
       
        return null;
    }

    public Object getCellEditorValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isCellEditable(EventObject anEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean shouldSelectCell(EventObject anEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean stopCellEditing() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void cancelCellEditing() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addCellEditorListener(CellEditorListener l) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeCellEditorListener(CellEditorListener l) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
