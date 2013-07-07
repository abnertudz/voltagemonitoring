/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.view.applications;
import datacollector.core.ViewCore;
import datacollector.device.data.DataSamplerDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abner
 */
public class AppView extends ViewCore{

    // Variables that holds the instance of this object
    private static final AppView application = new AppView();
    
    public AppView()
    {
        super();
    }

    /** This method will be used to get the Instance of this object
     *  There should only be one instance.
     */
    public static AppView getInstance()
    {
       return application;
    }
    
     /**
     *  This method is used to launch the application
     */
    public void launch()
    {
        try
        {
            UIManager ui = new UIManager();
            ui.setLookAndFeel(ui.getSystemLookAndFeelClassName());
        }
        catch(Exception e)
        {
           //@todo this should be implemented in a logger;
            System.out.println("Exception: " );
        }

        this.setVisible(true);
    }

    public JButton getConnectActionButton()
    {
        return connectAction;
    }

    public JTable getDataTable()
    {
        return dataTable;
    }

    public void populateDataTable(ArrayList<DataSamplerDevice> data)
    {
        DefaultTableModel tableModel = (DefaultTableModel)dataTable.getModel();
        Iterator iterate = data.iterator();
        int countX=0;

        while(iterate.hasNext())
        {
            DataSamplerDevice device = (DataSamplerDevice) iterate.next();
            Object[] newRow = {false,device.getDeviceAddress(),device.getDeviceBlock(),device.getDeviceMinVoltage(),device.getDeviceMaxVoltage(),"0","0","No",""};
            tableModel.addRow(newRow); 
            countX++;
        }
    }

    public void updateDataTable(HashMap dataMap)
    {
        DefaultTableModel tableModel = (DefaultTableModel)dataTable.getModel();
        int row = tableModel.getRowCount();
        Object address = 0;

        for(int x=0; x<row; x++)
        {
           address = tableModel.getValueAt(x, 1);
           DataSamplerDevice device = (DataSamplerDevice) dataMap.get((Integer)address);
           System.out.println("Device Voltage: " + device.getDeviceVoltage());
           if(null != device)
           {               
               tableModel.setValueAt(device.getDeviceVoltage(), x, 5);
           }
        }
        
    }
}
