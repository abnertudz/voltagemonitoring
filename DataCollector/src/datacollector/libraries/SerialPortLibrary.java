/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.libraries;

import datacollector.device.data.DataSamplerDevice;
import datacollector.factories.ThreadFactory;
import datacollector.globals.GlobalVariables;

/**
 *
 * @author Abner
 */
public class SerialPortLibrary {

    private Thread writer;
    private Thread reader;

    private final int LSB_BITMASK = 15;

    public SerialPortLibrary()
    {
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

    

}
