/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.com.threads;

import vms.dao.implementation.DeviceDaoImplementation;
import vms.device.data.DataSamplerDevice;
import vms.factories.DaoFactory;
import vms.factories.LibraryFactory;
import vms.globals.GlobalVariables;
import vms.libraries.SerialPortLibrary;
import vms.AppView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import vms.AppViewCore;

/**
 *
 * @author Abner
 */
public class MonitorDevicesThread extends Thread{

    private SerialPortLibrary serialLib;
    private AppViewCore app;
    private DeviceDaoImplementation deviceDao;

    public MonitorDevicesThread()
    {
        serialLib = (SerialPortLibrary)LibraryFactory.getInstance("SerialPort");
        deviceDao = (DeviceDaoImplementation) DaoFactory.getInstance("Device");
        app = AppView.getAppViewCore();
    }


    @Override
    public void run() {
        
        while(true)
        {
            if(GlobalVariables.START_MONITORING && !interrupted() && GlobalVariables.INTERVAL_TRIGGERED)
            {
                ArrayList<DataSamplerDevice> deviceSettings = GlobalVariables.configData.getDeviceSettings();
                Iterator iterate = deviceSettings.iterator();
                HashMap deviceMap = new HashMap();
                
                while(iterate.hasNext())
                {
                    DataSamplerDevice device = (DataSamplerDevice) iterate.next();
                    try
                    {                        
                        DataSamplerDevice readValue = serialLib.readComDevice(device.getDeviceAddress());
                        readValue.setDeviceAddress(device.getDeviceAddress());
                        readValue.setDeviceBlock(device.getDeviceBlock());
                        readValue.setDeviceName(device.getDeviceName());
                        readValue.setDeviceMaxVoltage(device.getDeviceMaxVoltage());
                        readValue.setDeviceMinVoltage(device.getDeviceMinVoltage());
                        deviceMap.put(device.getDeviceAddress(), readValue);

                        System.out.println("Adress = " + readValue.getDeviceAddress() + " Value = " + readValue.getDeviceVoltage());
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();                        
                    }                    
                }

                // Check whether there's an error
                if(!GlobalVariables.readErrorAddress.isEmpty())
                {
                    for(int i = 0; i < GlobalVariables.MAX_READ_ERROR_LIMIT;i++)
                    {
                        if(!GlobalVariables.readErrorAddress.isEmpty())
                        {
                            ArrayList<Integer> errorAddress = GlobalVariables.readErrorAddress;
                            Iterator iterateError = deviceSettings.iterator();

                            GlobalVariables.readErrorAddress.clear();

                            while(iterateError.hasNext())
                            {
                                DataSamplerDevice device = (DataSamplerDevice) iterateError.next();
                                if(errorAddress.contains(device.getDeviceAddress()))
                                {
                                    try
                                    {
                                        DataSamplerDevice readValue = serialLib.readComDevice(device.getDeviceAddress());
                                        readValue.setDeviceAddress(device.getDeviceAddress());
                                        readValue.setDeviceBlock(device.getDeviceBlock());
                                        readValue.setDeviceName(device.getDeviceName());
                                        readValue.setDeviceMaxVoltage(device.getDeviceMaxVoltage());
                                        readValue.setDeviceMinVoltage(device.getDeviceMinVoltage());
                                        deviceMap.put(device.getDeviceAddress(), readValue);

                                        System.out.println("Adress = " + readValue.getDeviceAddress() + " Value = " + readValue.getDeviceVoltage());
                                    }
                                    catch(Exception e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }

                // Save data to  database
                deviceDao.addDeviceReading(deviceMap);

                // update GUI
                app.updateDataTable(deviceMap);

                // update graph
                
            }
        }
        
    }

}
