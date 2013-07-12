/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.dao.interfaces;

import datacollector.device.data.DataSamplerDevice;
import java.util.ArrayList;

/**
 *
 * @author Abner
 */
public interface DeviceInterface {

    /**
     * Helper method that retrieves single device from database
     * @param int deviceAddress
     * @return DataSamplerDevice
     */
    public DataSamplerDevice getDevice(int deviceAddress);

    /**
     * Helper method that retrieves multiple devices from database
     * @param int[] deviceAddress
     * @return ArrayList<DataSamplerDevice>
     */
    public ArrayList<DataSamplerDevice> getDevice(int[] deviceAddress);

    /**
     * Helper method that deletes a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
    public boolean deleteDevice(int deviceAddress);
    
    /**
     * Helper method that deletes a multiple device record in the database
     * @param int[] deviceAddress
     * @return boolean
     */
    public boolean deleteDevice(int[] deviceAddress);

    /**
     * Helper method that updates a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
    public boolean updateDevice(int deviceAddress);

    /**
     * Helper method that updates a multiple device record in the database
     * @param int[] deviceAddress
     * @return boolean
     */
    public boolean updateDevice(int[] deviceAddress);

    /**
     * Helper method that adds a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
     public int addDevice(int deviceAddress, int blockId, String deviceName, double maxValue, double minValue);

     /**
     * Helper method that adds a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
     public int addDevice(DataSamplerDevice deviceData);

      /**
     * Helper method that adds a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
     public ArrayList<Integer> addDevice(ArrayList<DataSamplerDevice> deviceData);

    

}
