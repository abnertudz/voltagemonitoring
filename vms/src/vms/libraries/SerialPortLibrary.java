/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.libraries;

import vms.constants.StringConstants;
import vms.dao.implementation.DeviceDaoImplementation;
import vms.device.data.DataSamplerDevice;
import vms.factories.DaoFactory;
import vms.factories.ThreadFactory;
import vms.globals.GlobalVariables;
import vms.pojos.Device;
import vms.pojos.DeviceSetting;
import vms.pojos.DeviceStatus;

/**
 *
 * @author Abner
 */
public class SerialPortLibrary {

    private Thread writer;
    private Thread reader;
    private DeviceDaoImplementation deviceDao;

    private final int LSB_BITMASK = 15;

    public SerialPortLibrary()
    {
        deviceDao = (DeviceDaoImplementation) DaoFactory.getInstance("Device");
        writer = ThreadFactory.getInstance("ComWriter");
        if(!writer.isAlive())
        {
            writer.start();
        }
        reader = ThreadFactory.getInstance("ComReader");
        if(!reader.isAlive())
        {
            reader.start();
        }

    }

    public DataSamplerDevice readComDevice(int address, int sleepTime)
    {
        DataSamplerDevice device = new DataSamplerDevice();
        
        int cs = (GlobalVariables.FRAME_START + GlobalVariables.READ_DEVICE_ADDRESS_COLLECTOR + (address & LSB_BITMASK) + (address >> 4)
                + (sleepTime & LSB_BITMASK) + (sleepTime >> 4 ) + 0)%256;

        GlobalVariables.writeDataCom.setFrameStart(GlobalVariables.FRAME_START);
        GlobalVariables.writeDataCom.setCommand(GlobalVariables.READ_DEVICE_ADDRESS_COLLECTOR);
        GlobalVariables.writeDataCom.setAddressLSB(address & LSB_BITMASK );
        GlobalVariables.writeDataCom.setAddressMSB(address >> 4 );
        GlobalVariables.writeDataCom.setData1LSB(sleepTime & LSB_BITMASK );
        GlobalVariables.writeDataCom.setData1MSB(sleepTime >> 4 );
        GlobalVariables.writeDataCom.setData2(0);
        GlobalVariables.writeDataCom.setCs(cs);        
        GlobalVariables.writeDataCom.setFrameEnd(GlobalVariables.FRAME_END);

        GlobalVariables.START_COM_WRITE = true;
        while(!GlobalVariables.WRITE_FINISH)
        {
            // do nothing
        }
        GlobalVariables.WRITE_FINISH = false;
        GlobalVariables.readDataCom.clearData();


        GlobalVariables.START_COM_READ = true;        
        while(!GlobalVariables.READ_FINISH){
            // Do nothing
        }        
        
        device.setDeviceVoltage(GlobalVariables.readDataCom.getDeviceVoltage());
        device.setDeviceAddress(GlobalVariables.readDataCom.getDeviceAddress());

        GlobalVariables.READ_FINISH = false;
        GlobalVariables.readDataCom.clearData();
        
        return device;
    }

    public DataSamplerDevice readComDevice(int address)
    {
        DataSamplerDevice device = new DataSamplerDevice();

        int cs = (GlobalVariables.FRAME_START + GlobalVariables.READ_DEVICE_ADDRESS_COLLECTOR + (address & LSB_BITMASK) + (address >> 4)+ 0)%256;

        GlobalVariables.writeDataCom.setFrameStart(GlobalVariables.FRAME_START);
        GlobalVariables.writeDataCom.setCommand(GlobalVariables.READ_DEVICE_ADDRESS_COLLECTOR);
        GlobalVariables.writeDataCom.setAddressLSB(address & LSB_BITMASK );
        GlobalVariables.writeDataCom.setAddressMSB(address >> 4 );
        GlobalVariables.writeDataCom.setData1LSB(0);
        GlobalVariables.writeDataCom.setData1MSB(0);
        GlobalVariables.writeDataCom.setData2(0);
        GlobalVariables.writeDataCom.setCs(cs);
        GlobalVariables.writeDataCom.setFrameEnd(GlobalVariables.FRAME_END);

        GlobalVariables.START_COM_WRITE = true;
        while(!GlobalVariables.WRITE_FINISH){
            // do nothing
        }
        GlobalVariables.WRITE_FINISH = false;
        GlobalVariables.readDataCom.clearData();


        GlobalVariables.ADDRESS = address;
        GlobalVariables.START_COM_READ = true;
        while(!GlobalVariables.READ_FINISH){
            // Do nothing
        }

         GlobalVariables.ADDRESS = 0;
         
        device.setDeviceVoltage(GlobalVariables.readDataCom.getDeviceVoltage());
        device.setDeviceAddress(GlobalVariables.readDataCom.getDeviceAddress());

        DeviceSetting deviceSetting = deviceDao.getDeviceSetting(address);
                
        if(GlobalVariables.readDataCom.getDeviceAlarmBit())
        {
            //@todo: this variable should be placed in a property file
            // Querying from db server will make this part slow
            DeviceStatus status = deviceDao.getDeviceStatus(StringConstants.LOW_BATTERY);
            device.setDeviceName(status.getValue());
            device.setDeviceStatus(status.getId());
        }
        else if(!GlobalVariables.DEVICE_RESPONDING)
        {
            //@todo: this variable should be placed in a property file
            // Querying from db server will make this part slow
            DeviceStatus status = deviceDao.getDeviceStatus(StringConstants.NOT_RESPONDING);
            device.setDeviceName(status.getValue());
            device.setDeviceStatus(status.getId());
        }
        else if(deviceSetting.getDeviceMaxValue() < GlobalVariables.readDataCom.getDeviceVoltage())
        {
            //@todo: this variable should be placed in a property file
            // Querying from db server will make this part slow
            DeviceStatus status = deviceDao.getDeviceStatus(StringConstants.MAX_VOLTAGE);
            device.setDeviceName(status.getValue());
            device.setDeviceStatus(status.getId());
        }
        else if(deviceSetting.getDeviceMinValue() > GlobalVariables.readDataCom.getDeviceVoltage())
        {
            //@todo: this variable should be placed in a property file
            // Querying from db server will make this part slow
            DeviceStatus status = deviceDao.getDeviceStatus(StringConstants.MIN_VOLTAGE);
            device.setDeviceName(status.getValue());
            device.setDeviceStatus(status.getId());
        }
        else
        {
             //@todo: this variable should be placed in a property file
            // Querying from db server will make this part slow
            DeviceStatus status = deviceDao.getDeviceStatus(StringConstants.NORMAL);
            device.setDeviceName(status.getValue());
            device.setDeviceStatus(status.getId());
        }
        
        GlobalVariables.READ_FINISH = false;
        GlobalVariables.readDataCom.clearData();

        return device;
    }

    

}
