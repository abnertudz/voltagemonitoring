/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.com.threads;

import datacollector.device.data.DataSamplerDevice;
import datacollector.factories.LibraryFactory;
import datacollector.globals.GlobalVariables;
import datacollector.libraries.SerialPortLibrary;
import datacollector.view.applications.AppView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Abner
 */
public class MonitorDevicesThread extends Thread{

    private SerialPortLibrary serialLib;
    private AppView app;

    public MonitorDevicesThread()
    {
        serialLib = (SerialPortLibrary)LibraryFactory.getInstance("SerialPort");
        app = AppView.getInstance();
    }


    @Override
    public void run() {
        
        while(true)
        {
            if(GlobalVariables.START_MONITORING && !interrupted() && GlobalVariables.INTERVAL_TRIGGERED)
            {
                ArrayList<DataSamplerDevice> deviceSettings = GlobalVariables.configData.getDeviceSettings();
                Iterator iterate = deviceSettings.iterator();
                int columnCount = 0;
                HashMap deviceMap = new HashMap();
                
                while(iterate.hasNext())
                {
                    DataSamplerDevice device = (DataSamplerDevice) iterate.next();
                    try
                    {                        
                        DataSamplerDevice readValue = serialLib.readComDevice(device.getDeviceAddress());
                        readValue.setDeviceAddress(device.getDeviceAddress());
                        deviceMap.put(readValue.getDeviceAddress(), readValue);
                        System.out.println("Adress = " + readValue.getDeviceAddress() + " Value = " + readValue.getDeviceVoltage());
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();                        
                    }                    
                    columnCount++;
                }

                // Check wether there's an error


                // Save data to  database

                // update GUI
                app.updateDataTable(deviceMap);

                // update graph
            }
        }
        
    }

}
