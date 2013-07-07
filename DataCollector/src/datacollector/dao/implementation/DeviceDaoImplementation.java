/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.dao.implementation;

import datacollector.dao.interfaces.DeviceInterface;
import datacollector.device.data.DataSamplerDevice;
import java.util.ArrayList;

/**
 *
 * @author Abner
 */
public class DeviceDaoImplementation implements DeviceInterface{    

    /**
     * Helper method that retrieves single device from database
     * @param int deviceAddress
     * @return DataSamplerDevice
     */
    public DataSamplerDevice readDevice(int deviceAddress) {
        DataSamplerDevice device = new DataSamplerDevice();

        return device;
    }

    /**
     * Helper method that retrieves multiple devices from database
     * @param int[] deviceAddress
     * @return ArrayList<DataSamplerDevice>
     */
    public ArrayList<DataSamplerDevice> readDevice(int[] deviceAddress) {
        ArrayList<DataSamplerDevice> device = new ArrayList<DataSamplerDevice>();

        return device;
    }

    /**
     * Helper method that deletes a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
    public boolean deleteDevice(int deviceAddress) {
        boolean result = true;

        return result;
    }

     /**
     * Helper method that deletes a multiple device record in the database
     * @param int[] deviceAddress
     * @return boolean
     */
    public boolean deleteDevice(int[] deviceAddress) {
        boolean result = true;

        return result;
    }

    /**
     * Helper method that updates a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
    public boolean updateDevice(int deviceAddress) {
        boolean result = true;

        return result;
    }

    /**
     * Helper method that updates a multiple device record in the database
     * @param int[] deviceAddress
     * @return boolean
     */
    public boolean updateDevice(int[] deviceAddress) {
        boolean result = true;

        return result;
    }

}
