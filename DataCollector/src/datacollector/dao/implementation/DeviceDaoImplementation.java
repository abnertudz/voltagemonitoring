/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.dao.implementation;

import datacollector.core.DaoCore;
import datacollector.dao.interfaces.DeviceInterface;
import datacollector.device.data.DataSamplerDevice;
import datacollector.pojos.Device;
import datacollector.pojos.DeviceSetting;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Abner
 */
public class DeviceDaoImplementation extends DaoCore implements DeviceInterface{
    
    public DeviceDaoImplementation()
    {
        super();
    }

    /**
     * Helper method that retrieves single device from database
     * @param int deviceAddress
     * @return DataSamplerDevice
     */
    public DataSamplerDevice getDevice(int deviceAddress) {
        DataSamplerDevice device = new DataSamplerDevice();

        return device;
    }

    /**
     * Helper method that retrieves multiple devices from database
     * @param int[] deviceAddress
     * @return ArrayList<DataSamplerDevice>
     */
    public ArrayList<DataSamplerDevice> getDevice(int[] deviceAddress) {
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

     /**
     * Helper method that adds a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
    public int addDevice(int deviceAddress, int blockId, String deviceName, double maxValue, double minValue)
    {
        int deviceId = 0;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            Device device = new Device();
            device.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            device.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            device.setDeviceAddress(deviceAddress);
            device.setDeviceName(deviceName);
            device.setDeviceBlock(blockId);
            deviceId = (Integer) session.save(device);            
            
            DeviceSetting setting = new DeviceSetting();
            setting.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            setting.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            setting.setDeviceId(deviceId);
            setting.setDeviceMaxValue(maxValue);
            setting.setDeviceMinValue(minValue);
            session.save(setting);

            trans.commit();
        }
       catch (HibernateException e) {
         if (trans!=null)
             trans.rollback();
         e.printStackTrace();
       }
        finally
        {
             session.close();
        }

        return deviceId;
    }

    /**
     * Helper method that adds a single device record in the database
     * @param DataSamplerDevice device
     * @return boolean
     */
    public int addDevice(DataSamplerDevice deviceData)
    {
        int deviceId = 0;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            Device device = new Device();
            device.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            device.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            device.setDeviceAddress(deviceData.getDeviceAddress());
            device.setDeviceName(deviceData.getDeviceName());
            device.setDeviceBlock(deviceData.getDeviceBlock());
            deviceId = (Integer) session.save(device);

            DeviceSetting setting = new DeviceSetting();
            setting.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            setting.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            setting.setDeviceId(deviceId);
            setting.setDeviceMaxValue(deviceData.getDeviceMaxVoltage());
            setting.setDeviceMinValue(deviceData.getDeviceMinVoltage());
            session.save(setting);

            trans.commit();
        }
       catch (HibernateException e) {
         if (trans!=null)
             trans.rollback();
         e.printStackTrace();
       }
        finally
        {
             session.close();
        }

        return deviceId;
    }

    /**
     * Helper method that adds a multiple device record in the database
     * @param ArrayList<DataSamplerDevice> deviceData
     * @return ArrayList<Integer
     */
    public ArrayList<Integer> addDevice(ArrayList<DataSamplerDevice> deviceData) {

        ArrayList<Integer> deviceIds = new ArrayList<Integer>();
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            Iterator iterator = deviceData.iterator();
            while(iterator.hasNext())
            {
                DataSamplerDevice data = (DataSamplerDevice) iterator.next();
                Device device = new Device();
                device.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                device.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                device.setDeviceAddress(data.getDeviceAddress());
                device.setDeviceName(data.getDeviceName());
                device.setDeviceBlock(data.getDeviceBlock());
                int deviceId = (Integer) session.save(device);
                deviceIds.add(deviceId);

                DeviceSetting setting = new DeviceSetting();
                setting.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                setting.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                setting.setDeviceId(deviceId);
                setting.setDeviceMaxValue(data.getDeviceMaxVoltage());
                setting.setDeviceMinValue(data.getDeviceMinVoltage());
                session.save(setting);
            }

            trans.commit();
        }
        catch (HibernateException e) {
          if (trans!=null)
             trans.rollback();
          e.printStackTrace();
        }
        finally
        {
             session.close();
        }

        return deviceIds;
    }


}
