/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.dao.interfaces;

import datacollector.device.data.DataSamplerDevice;
import datacollector.pojos.*;
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
    public Device getDevice(Integer deviceAddress);
    public DataSamplerDevice getDataSamplerDevice(Integer deviceAddress);

    /**
     * Helper method that retrieves multiple devices from database
     * @param int[] deviceAddress
     * @return ArrayList<DataSamplerDevice>
     */
    public ArrayList<DataSamplerDevice> getDataSamplerDevices(Integer[] deviceAddress);

    /**
     * Helper method that deletes a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
    public boolean deleteDevice(Integer deviceAddress);
    
    /**
     * Helper method that deletes a multiple device record in the database
     * @param int[] deviceAddress
     * @return boolean
     */
    public boolean deleteDevice(Integer[] deviceAddress);

    /**
     * Helper method that deletes a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
    public boolean deleteDevice(DataSamplerDevice deviceData);

    /**
     * Helper method that deletes a multiple device record in the database
     * @param int[] deviceAddress
     * @return boolean
     */
    public boolean deleteDevice(ArrayList<DataSamplerDevice> deviceData);

    /**
     * Helper method that updates a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
    public boolean updateDataSamplerDevice(Integer deviceAddress, Integer blockId, String deviceName, Double maxValue, Double minValue);

     /**
     * Helper method that updates a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
     public boolean updateDataSamplerDevice(DataSamplerDevice deviceData);

     /**
     * Helper method that updates a multiple device record in the database
     * @param int[] deviceAddress
     * @return boolean
     */
     public boolean updateDataSamplerDevice(ArrayList<DataSamplerDevice> deviceData);

    /**
     * Helper method that adds a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
     public int addDataSamplerDevice(Integer deviceAddress, Integer blockId, String deviceName, Double maxValue, Double minValue);
     public int addDevice(Integer deviceAddress, Integer blockId, String deviceName, Double maxValue, Double minValue);

     /**
     * Helper method that adds a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
     public int addDataSamplerDevice(DataSamplerDevice deviceData);
     public int addDevice(DataSamplerDevice deviceData);

      /**
     * Helper method that adds a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
     public ArrayList<Integer> addDataSamplerDevices(ArrayList<DataSamplerDevice> deviceData);


     public int addDeviceSetting(Integer deviceAddress, Double maxValue, Double minValue);
     public int addDeviceSetting(DataSamplerDevice deviceData);


     public int addDeviceReading(DataSamplerDevice deviceData);
     public ArrayList<Integer> addDeviceReading(ArrayList<DataSamplerDevice> deviceData);

    

}
