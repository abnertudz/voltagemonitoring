/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.listeners.dialogs;

import datacollector.device.data.DataSamplerDevice;
import datacollector.factories.DialogFactory;
import datacollector.view.dialogs.DeviceSettingDialog;
import datacollector.globals.GlobalVariables;
import datacollector.view.applications.AppView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abner
 */
public class DeviceSettingActionListener implements ActionListener{

    private Object[] addRow = {"","",""};
    
    private enum DeviceSettingCommands
    {
        okButton, cancelButton, addRowButton, deleteRowButton, generateButton;
    }
    public void actionPerformed(ActionEvent e) {

        DeviceSettingCommands command = DeviceSettingCommands.valueOf(e.getActionCommand());
        DeviceSettingDialog dialog = (DeviceSettingDialog) DialogFactory.getInstance("DeviceSetting");
        AppView appView = AppView.getInstance();

        switch(command)
        {
            case okButton:
                ArrayList<DataSamplerDevice> dataTable = getTableData(dialog.getDeviceSettingTable());
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

    private ArrayList<DataSamplerDevice> getTableData(JTable table)
    {
        ArrayList<DataSamplerDevice> deviceData = new ArrayList<DataSamplerDevice>();
        DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
        int row = tableModel.getRowCount();

        for(int x=0; x < row; x++)
        {
            DataSamplerDevice device = new DataSamplerDevice();
            device.setDeviceAddress((Integer)tableModel.getValueAt(x, 0));
            device.setDeviceBlock(Integer.parseInt((String)tableModel.getValueAt(x, 1)));
            device.setDeviceMinVoltage((Double)tableModel.getValueAt(x, 2));
            device.setDeviceMaxVoltage((Double)tableModel.getValueAt(x, 3));
            deviceData.add(device);
        }
        
        return deviceData;
    }

}
