/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.dao.implementation;

import vms.core.DaoCore;
import vms.dao.interfaces.DeviceInterface;
import vms.device.data.DataSamplerDevice;
import vms.pojos.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Abner
 */
public class DeviceDaoImplementation extends DaoCore implements DeviceInterface{
    
    public DeviceDaoImplementation()
    {
        super();
    }

    public DeviceSetting getDeviceSetting(int address) {

        DeviceSetting toReturn = null;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            List<Device> devices = (List<Device>) session.createCriteria(Device.class).add(Restrictions.eq("deviceAddress", address));
            for(Device device : devices)
            {
                List<DeviceSetting> deviceSettings = (List<DeviceSetting>) session.createCriteria(DeviceSetting.class).add(Restrictions.eq("deviceId", device.getId()));
                for(DeviceSetting deviceSetting : deviceSettings)
                {
                    toReturn = deviceSetting;
                    break;
                }
                break;
            }

            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();

        }
        finally{
             session.close();
        }

        return toReturn;
    }


     /**
     * Helper method that retrieves single device block from database
     * @param int deviceAddress
     * @return DataSamplerDevice
     */
    public DeviceStatus getDeviceStatus(String name) {

        DeviceStatus toReturn = null;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            List<DeviceStatus> devices = (List<DeviceStatus>) session.createCriteria(DeviceStatus.class).add(Restrictions.eq("value", name));
            for(DeviceStatus device : devices)
            {
                toReturn = device;
                break;
            }

            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();

        }
        finally{
             session.close();
        }

        return toReturn;
    }
    
    /**
     * Helper method that retrieves single device block from database
     * @param int deviceAddress
     * @return DataSamplerDevice
     */
    public DeviceBlock getDeviceBlock(String name) {

        DeviceBlock toReturn = null;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            List<DeviceBlock> devices = (List<DeviceBlock>) session.createCriteria(DeviceBlock.class).add(Restrictions.eq("blockName", name));
            for(DeviceBlock device : devices)
            {
                toReturn = device;
                break;
            }

            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();

        }
        finally{
             session.close();
        }

        return toReturn;
    }
    
    /**
     * Helper method that retrieves single device from database
     * @param int deviceAddress
     * @return DataSamplerDevice
     */
    public Device getDevice(Integer deviceAddress) {

        Device rDevice = null;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            List<Device> devices = (List<Device>) session.createCriteria(Device.class).add(Restrictions.eq("deviceAddress", deviceAddress));            
            for(Device device : devices)
            {
                rDevice = device;
                break;
            }

            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();

        }
        finally{
             session.close();
        }

        return rDevice;
    }    
    

    /**
     * Helper method that retrieves single device from database
     * @param int deviceAddress
     * @return DataSamplerDevice
     */
    public DataSamplerDevice getDataSamplerDevice(Integer deviceAddress) {
        
        DataSamplerDevice rDevice = new DataSamplerDevice();
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            List<Device> devices = (List<Device>) session.createCriteria(Device.class).add(Restrictions.eq("deviceAddress", deviceAddress));
            int deviceId = 0;
            for(Device device : devices)
            {
                rDevice.setDeviceAddress(device.getDeviceAddress());
                rDevice.setDeviceBlock(device.getDeviceBlock());
                rDevice.setDeviceName(device.getDeviceName());
                deviceId = device.getId();
                break;
            }

            List<DeviceSetting> deviceSettings = (List<DeviceSetting>) session.createCriteria(DeviceSetting.class).add(Restrictions.eq("deviceId", new Integer(deviceId)));
            for(DeviceSetting deviceSetting : deviceSettings)
            {
                rDevice.setDeviceMaxVoltage(deviceSetting.getDeviceMaxValue());
                rDevice.setDeviceMinVoltage(deviceSetting.getDeviceMinValue());                
                break;
            }

            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();

        }
        finally{
             session.close();
        }
        
        return rDevice;
    }

    /**
     * Helper method that retrieves multiple devices from database
     * @param int[] deviceAddress
     * @return ArrayList<DataSamplerDevice>
     */
    public ArrayList<DataSamplerDevice> getDataSamplerDevices(Integer[] deviceAddress) {

        ArrayList<DataSamplerDevice> rDevice = new ArrayList<DataSamplerDevice>();
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            Iterator iterator = rDevice.iterator();

            while(iterator.hasNext())
            {
                DataSamplerDevice fDevice = (DataSamplerDevice) iterator.next();
                List<Device> devices = (List<Device>) session.createCriteria(Device.class).add(Restrictions.eq("deviceAddress", deviceAddress));
                int deviceId = 0;
                for(Device device : devices)
                {
                    fDevice.setDeviceAddress(device.getDeviceAddress());
                    fDevice.setDeviceBlock(device.getDeviceBlock());
                    fDevice.setDeviceName(device.getDeviceName());
                    deviceId = device.getId();
                    break;
                }

                List<DeviceSetting> deviceSettings = (List<DeviceSetting>) session.createCriteria(DeviceSetting.class).add(Restrictions.eq("deviceId", new Integer(deviceId)));
                for(DeviceSetting deviceSetting : deviceSettings)
                {
                    fDevice.setDeviceMaxVoltage(deviceSetting.getDeviceMaxValue());
                    fDevice.setDeviceMinVoltage(deviceSetting.getDeviceMinValue());
                    break;
                }
                rDevice.add(fDevice);
            }            
            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();

        }
        finally{
             session.close();
        }

        return rDevice;
    }

    /**
     * Helper method that deletes a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
    public boolean deleteDevice(Integer deviceAddress) {
        
        boolean result = true;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            List<Device> devices = (List<Device>) session.createCriteria(Device.class).add(Restrictions.eq("deviceAddress", deviceAddress));
            for(Device device : devices)
            { 
                session.delete(device);                              
            }
            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();
            result = false;

        }
        finally{
             session.close();
        }

        return result;
    }

     /**
     * Helper method that deletes a multiple device record in the database
     * @param int[] deviceAddress
     * @return boolean
     */
    public boolean deleteDevice(Integer[] deviceAddress) {
        
        boolean result = true;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            List<Device> devices = (List<Device>) session.createCriteria(Device.class).add(Restrictions.in("deviceAddress", deviceAddress));
            for(Device device : devices)
            {
                session.delete(device);
            }
            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();
            result = false;

        }
        finally{
             session.close();
        }

        return result;
    }

    /**
     * Helper method that deletes a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
    public boolean deleteDevice(DataSamplerDevice deviceData) {
        
        boolean result = true;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            List<Device> devices = (List<Device>) session.createCriteria(Device.class).add(Restrictions.eq("deviceAddress", new Integer(deviceData.getDeviceAddress())));
            for(Device device : devices)
            {
                session.delete(device);
            }
            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();
            result = false;

        }
        finally{
             session.close();
        }

        return result;
    }

     /**
     * Helper method that deletes a multiple device record in the database
     * @param int[] deviceAddress
     * @return boolean
     */
    public boolean deleteDevice(ArrayList<DataSamplerDevice> deviceData) {

        boolean result = true;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            Iterator iterator = deviceData.iterator();
            while(iterator.hasNext())
            {
                DataSamplerDevice fDevice = (DataSamplerDevice) iterator.next();
                List<Device> devices = (List<Device>) session.createCriteria(Device.class).add(Restrictions.eq("deviceAddress", new Integer(fDevice.getDeviceAddress())));
                for(Device device : devices)
                {
                    session.delete(device);
                }
            }
            
            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();
            result = false;

        }
        finally{
             session.close();
        }

        return result;
        
    }

    public DeviceStatus getDeviceStatusById(int statusId) {

        DeviceStatus toReturn = null;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
           
            toReturn = (DeviceStatus) session.get(DeviceStatus.class, statusId);
            
            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();

        }
        finally{
             session.close();
        }

        return toReturn;
    }

    /**
     * Helper method that updates a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
    public boolean updateDataSamplerDevice(Integer deviceAddress, Integer blockId, String deviceName, Double maxValue, Double minValue) {
        
        boolean result = true;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            List<Device> devices = (List<Device>) session.createCriteria(Device.class).add(Restrictions.eq("deviceAddress", deviceAddress));
            for(Device device : devices)
            {
                device.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                device.setDeviceAddress(deviceAddress);
                device.setDeviceName(deviceName);
                device.setDeviceBlock(blockId);
                session.update(device);

                List<DeviceSetting> deviceSettings = (List<DeviceSetting>) session.createCriteria(DeviceSetting.class).add(Restrictions.eq("deviceId", new Integer(device.getId())));
                for(DeviceSetting deviceSetting : deviceSettings)
                {
                    deviceSetting.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                    deviceSetting.setDeviceMaxValue(maxValue);
                    deviceSetting.setDeviceMinValue(minValue);
                    session.update(deviceSetting);
                }
            }
            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();
            result = false;

        }
        finally{
             session.close();
        }

        return result;
    }

    /**
     * Helper method that updates a multiple device record in the database
     * @param int[] deviceAddress
     * @return boolean
     */
    public boolean updateDataSamplerDevice(DataSamplerDevice deviceData) {
        
        boolean result = true;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            List<Device> devices = (List<Device>) session.createCriteria(Device.class).add(Restrictions.eq("deviceAddress", new Integer(deviceData.getDeviceAddress())));
            for(Device device : devices)
            {
                device.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                device.setDeviceAddress(deviceData.getDeviceAddress());
                device.setDeviceName(deviceData.getDeviceName());
                device.setDeviceBlock(deviceData.getDeviceBlock());
                session.update(device);

                List<DeviceSetting> deviceSettings = (List<DeviceSetting>) session.createCriteria(DeviceSetting.class).add(Restrictions.eq("deviceId", new Integer(device.getId())));
                for(DeviceSetting deviceSetting : deviceSettings)
                {
                    deviceSetting.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                    deviceSetting.setDeviceMaxValue(deviceData.getDeviceMaxVoltage());
                    deviceSetting.setDeviceMinValue(deviceData.getDeviceMinVoltage());
                    session.update(deviceSetting);
                }
            }
            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();
            result = false;

        }
        finally{
             session.close();
        }

        return result;
    }

     public boolean updateDataSamplerDevice(ArrayList<DataSamplerDevice> deviceData) {

         boolean result = true;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            Iterator iterator = deviceData.iterator();

            while(iterator.hasNext())
            {
                DataSamplerDevice fDevice = (DataSamplerDevice) iterator.next();
                List<Device> devices = (List<Device>) session.createCriteria(Device.class).add(Restrictions.eq("deviceAddress", new Integer(fDevice.getDeviceAddress())));
                for(Device device : devices)
                {
                    device.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                    device.setDeviceAddress(fDevice.getDeviceAddress());
                    device.setDeviceName(fDevice.getDeviceName());
                    device.setDeviceBlock(fDevice.getDeviceBlock());
                    session.update(device);

                    List<DeviceSetting> deviceSettings = (List<DeviceSetting>) session.createCriteria(DeviceSetting.class).add(Restrictions.eq("deviceId", new Integer(device.getId())));
                    for(DeviceSetting deviceSetting : deviceSettings)
                    {
                        deviceSetting.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                        deviceSetting.setDeviceMaxValue(fDevice.getDeviceMaxVoltage());
                        deviceSetting.setDeviceMinValue(fDevice.getDeviceMinVoltage());
                        session.update(deviceSetting);
                    }
                }
            }
            
            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();
            result = false;

        }
        finally{
             session.close();
        }

        return result;
         
     }
     /**
     * Helper method that adds a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
    public int addDataSamplerDevice(Integer deviceAddress, Integer blockId, String deviceName, Double maxValue, Double minValue)
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
    public int addDataSamplerDevice(DataSamplerDevice deviceData)
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
    public ArrayList<Integer> addDataSamplerDevices(ArrayList<DataSamplerDevice> deviceData) {

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

    /**
     * Helper method that adds a single device record in the database
     * @param int deviceAddress
     * @return boolean
     */
    public int addDevice(Integer deviceAddress, Integer blockId, String deviceName, Double maxValue, Double minValue)
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
     * @param int deviceAddress
     * @return boolean
     */
    public int addDeviceSetting(Integer deviceAddress, Double maxValue, Double minValue)
    {
        int deviceSettingId = 0;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            List<Device> devices = (List<Device>) session.createCriteria(Device.class).add(Restrictions.eq("deviceAddress", new Integer(deviceAddress)));
            for(Device device : devices)
            {
                DeviceSetting setting = new DeviceSetting();
                setting.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                setting.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                setting.setDeviceId(device.getId());
                setting.setDeviceMaxValue(maxValue);
                setting.setDeviceMinValue(minValue);
                deviceSettingId = (Integer)session.save(setting);
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

        return deviceSettingId;
    }

    /**
     * Helper method that adds a single device record in the database
     * @param DataSamplerDevice device
     * @return boolean
     */
    public int addDeviceSetting(DataSamplerDevice deviceData)
    {
        int deviceSettingId = 0;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            List<Device> devices = (List<Device>) session.createCriteria(Device.class).add(Restrictions.eq("deviceAddress", new Integer(deviceData.getDeviceAddress())));
            for(Device device : devices)
            {
                DeviceSetting setting = new DeviceSetting();
                setting.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                setting.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                setting.setDeviceId(device.getId());
                setting.setDeviceMaxValue(deviceData.getDeviceMaxVoltage());
                setting.setDeviceMinValue(deviceData.getDeviceMinVoltage());
                deviceSettingId = (Integer)session.save(setting);
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

        return deviceSettingId;
    }

    public ArrayList<Integer> addDeviceSetting(ArrayList<DataSamplerDevice> deviceData)
    {
        ArrayList<Integer> deviceSettingIds = new ArrayList<Integer>();
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            Iterator iterator = deviceData.iterator();
            while(iterator.hasNext())
            {
                DataSamplerDevice fDevice = (DataSamplerDevice) iterator.next();
                List<Device> devices = (List<Device>) session.createCriteria(Device.class).add(Restrictions.eq("deviceAddress", new Integer(fDevice.getDeviceAddress())));
                for(Device device : devices)
                {
                    DeviceSetting setting = new DeviceSetting();
                    setting.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                    setting.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                    setting.setDeviceId(device.getId());
                    setting.setDeviceMaxValue(fDevice.getDeviceMaxVoltage());
                    setting.setDeviceMinValue(fDevice.getDeviceMinVoltage());
                    deviceSettingIds.add((Integer)session.save(setting));
                }
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

        return deviceSettingIds;
    }

    public int addDeviceReading(DataSamplerDevice deviceData)
    {
        int deviceReadingId = 0;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            List<Device> devices = (List<Device>) session.createCriteria(Device.class).add(Restrictions.eq("deviceAddress", new Integer(deviceData.getDeviceAddress())));
            for(Device device : devices)
            {
                DeviceReading reading = new DeviceReading();
                reading.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                reading.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                reading.setDeviceId(device.getId());
                reading.setVoltage(deviceData.getDeviceVoltage());
                reading.setCurrent(deviceData.getDeviceCurrent());
                deviceReadingId = (Integer)session.save(reading);
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

        return deviceReadingId;
    }

    public ArrayList<Integer> addDeviceReading(ArrayList<DataSamplerDevice> deviceData)
    {
        ArrayList<Integer> deviceReadingIds = new ArrayList<Integer>();
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            Iterator iterator = deviceData.iterator();
            while(iterator.hasNext())
            {
                DataSamplerDevice fDevice = (DataSamplerDevice) iterator.next();
                List<Device> devices = (List<Device>) session.createCriteria(Device.class).add(Restrictions.eq("deviceAddress", new Integer(fDevice.getDeviceAddress())));
                for(Device device : devices)
                {
                    DeviceReading reading = new DeviceReading();
                    reading.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                    reading.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                    reading.setDeviceId(device.getId());
                    reading.setVoltage(fDevice.getDeviceVoltage());
                    reading.setCurrent(fDevice.getDeviceCurrent());
                    deviceReadingIds.add((Integer)session.save(reading));
                }

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

        return deviceReadingIds;
    }

    public ArrayList<Integer> addDeviceReading(HashMap<Integer,DataSamplerDevice> deviceData)
    {
        ArrayList<Integer> deviceReadingIds = new ArrayList<Integer>();
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            Iterator iterator = deviceData.values().iterator();
            while(iterator.hasNext())
            {
                DataSamplerDevice fDevice = (DataSamplerDevice) iterator.next();
                List<Device> devices = (List<Device>) session.createCriteria(Device.class).add(Restrictions.eq("deviceAddress", new Integer(fDevice.getDeviceAddress())));
                for(Device device : devices)
                {
                    DeviceReading reading = new DeviceReading();
                    reading.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                    reading.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                    reading.setDeviceId(device.getId());
                    reading.setVoltage(fDevice.getDeviceVoltage());
                    reading.setCurrent(fDevice.getDeviceCurrent());
                    deviceReadingIds.add((Integer)session.save(reading));
                }

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
        
        return deviceReadingIds;
    }

}
