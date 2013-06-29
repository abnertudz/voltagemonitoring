/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.listeners.dialogs;

import datacollector.view.dialogs.DeviceSettingDialog;
import datacollector.globals.GlobalVariables;
import datacollector.view.applications.AppView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abner
 */
public class DeviceSettingActionListener implements ActionListener{

    private static final DeviceSettingActionListener app = new DeviceSettingActionListener();

    private Object[] addRow = {"","",""};
    
    private enum DeviceSettingCommands
    {
        okButton, cancelButton, addRowButton, deleteRowButton, generateButton;
    }
    public void actionPerformed(ActionEvent e) {

        DeviceSettingCommands command = DeviceSettingCommands.valueOf(e.getActionCommand());
        DeviceSettingDialog dialog = DeviceSettingDialog.getInstance();
        AppView appView = AppView.getInstance();

        switch(command)
        {
            case okButton:
                ArrayList<Object[][]> dataTable = getTableData(dialog.getDeviceSettingTable());
                GlobalVariables.configData.setDeviceSettings(dataTable);
                GlobalVariables.configData.setDeviceSleepTime(dialog.getSleepTime().getText());
                GlobalVariables.configData.setReadInterval(dialog.getReadInterval().getText());
                appView.populateDataTable(dataTable);
                dialog.close();
                break;
            case cancelButton:
                dialog.close();
                break;
            case addRowButton:
                DefaultTableModel modelAdd = (DefaultTableModel)dialog.getDeviceSettingTable().getModel();
                modelAdd.addRow(addRow);
                break;
            case deleteRowButton:
                JTable deviceSettingTable = dialog.getDeviceSettingTable();
                DefaultTableModel modelDelete = (DefaultTableModel)deviceSettingTable.getModel();
                int[] selectedRows = deviceSettingTable.getSelectedRows();
                if(selectedRows.length > 0)
                {
                    int deletedCount = 0;
                    for(int row = 0; row < selectedRows.length; row++)
                    {
                        modelDelete.removeRow(selectedRows[row]-deletedCount);
                        deviceSettingTable.clearSelection();
                        deletedCount++;
                    }
                }
                else
                {
                     modelDelete.removeRow(deviceSettingTable.getRowCount()-1);
                     deviceSettingTable.clearSelection();
                }
                break;
            case generateButton:
                int fromAddress = (Integer)dialog.getFromDeviceAddress().getValue();
                int toAddress = (Integer)dialog.getToDeviceAddress().getValue();
                double minVoltage = (Double)dialog.getMinimumVoltage().getValue();
                double maxVoltage = (Double)dialog.getMaximumVoltage().getValue();
                String block = dialog.getBlock().getText();
                DefaultTableModel modelGenerate = (DefaultTableModel)dialog.getDeviceSettingTable().getModel();
                
                for(int row=fromAddress; row <= toAddress; row++)
                {
                    Object[] newRow = {row,block,minVoltage,maxVoltage};
                    modelGenerate.addRow(newRow);
                }
                
                break;
        }
        
    }

    private ArrayList<Object[][]> getTableData(JTable table)
    {
        ArrayList<Object[][]> tableData = new ArrayList<Object[][]>();
        DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
        int row = tableModel.getRowCount();
        int col = tableModel.getColumnCount();

        for(int x=0; x < row; x++)
        {
            Object[][] data = new Object[row][col];
            for(int y=0; y < col; y++)
            {
                data[x][y] = tableModel.getValueAt(x, y);
            }
            tableData.add(data);
        }
        
        return tableData;
    }
    
    public static DeviceSettingActionListener getInstance()
    {
        return app;
    }

}
