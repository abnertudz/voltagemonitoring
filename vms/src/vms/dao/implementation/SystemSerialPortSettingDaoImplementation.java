/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.dao.implementation;

import vms.com.data.ComConnectData;
import vms.core.DaoCore;
import vms.dao.interfaces.SystemSerialPortSettingInterface;
import vms.pojos.SerialPortBaudRate;
import vms.pojos.SerialPortBufferSize;
import vms.pojos.SerialPortDataBit;
import vms.pojos.SerialPortParityBit;
import vms.pojos.SerialPortStopBit;
import vms.pojos.SystemSerialPortSetting;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Abner
 */
public class SystemSerialPortSettingDaoImplementation extends DaoCore implements SystemSerialPortSettingInterface {

    public int addSerialPortSetting(String comPort, Integer baudRateId, Integer parityBitId, Integer dataBitId, Integer stopBitId, Integer bufferSizeId) {
        
        int settingId = 0;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            SystemSerialPortSetting setting = new SystemSerialPortSetting();
            setting.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            setting.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            setting.setCom(comPort);
            setting.setBaudRateId(baudRateId);
            setting.setParityBitId(parityBitId);
            setting.setDataBitId(dataBitId);
            setting.setStopBitId(stopBitId);
            setting.setBufferSizeId(bufferSizeId);
            settingId = (Integer) session.save(setting);

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

        return settingId;
    }

    public int addSerialPortSetting(ComConnectData com) {

        int settingId = 0;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            SystemSerialPortSetting setting = new SystemSerialPortSetting();
            setting.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            setting.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            setting.setCom(com.getComPort());
            setting.setBaudRateId(com.getBaudRate());
            setting.setParityBitId(com.getDataBits());
            setting.setDataBitId(com.getDataBits());
            setting.setStopBitId(com.getStopBits());
            setting.setBufferSizeId(com.getBufferSize());
            settingId = (Integer) session.save(setting);

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

        return settingId;
    }

    public ComConnectData getSerialPortSetting(String comPort) {

        ComConnectData comSetting = new ComConnectData();
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            List<SystemSerialPortSetting> settings = (List<SystemSerialPortSetting>) session.createCriteria(SystemSerialPortSetting.class).add(Restrictions.eq("com", comPort));

            for(SystemSerialPortSetting setting : settings)
            {
                comSetting.setComPort(setting.getCom());
                comSetting.setBaudRate(setting.getBaudRateId());
                comSetting.setDataBits(setting.getDataBitId());
                comSetting.setParityBits(setting.getParityBitId());
                comSetting.setBufferSize(setting.getBufferSizeId());
                comSetting.setStopBits(setting.getStopBitId());
                
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

        return comSetting;
    }

    public boolean deleteSerialPortSetting(String comPort) {
        
        boolean result = true;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            List<SystemSerialPortSetting> settings = (List<SystemSerialPortSetting>) session.createCriteria(SystemSerialPortSetting.class).add(Restrictions.eq("com", comPort));

            for(SystemSerialPortSetting setting : settings)
            {
                session.delete(setting);
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

    public boolean updateSerialPortSetting(ComConnectData com) {
        
        boolean result = true;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            List<SystemSerialPortSetting> settings = (List<SystemSerialPortSetting>) session.createCriteria(SystemSerialPortSetting.class).add(Restrictions.eq("com", com.getComPort()));

            for(SystemSerialPortSetting setting : settings)
            {             
                setting.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                setting.setBaudRateId(com.getBaudRate());
                setting.setParityBitId(com.getDataBits());
                setting.setDataBitId(com.getDataBits());
                setting.setStopBitId(com.getStopBits());
                setting.setBufferSizeId(com.getBufferSize());
                session.update(setting);
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

    public boolean updateSerialPortSetting(String comPort, Integer baudRateId, Integer parityBitId, Integer dataBitId, Integer stopBitId, Integer bufferSizeId) {
        
        boolean result = true;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            List<SystemSerialPortSetting> settings = (List<SystemSerialPortSetting>) session.createCriteria(SystemSerialPortSetting.class).add(Restrictions.eq("com", comPort));

            for(SystemSerialPortSetting setting : settings)
            {
                setting.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                setting.setBaudRateId(baudRateId);
                setting.setParityBitId(parityBitId);
                setting.setDataBitId(dataBitId);
                setting.setStopBitId(stopBitId);
                setting.setBufferSizeId(bufferSizeId);
                session.update(setting);
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

    public SerialPortBaudRate getSerialPortBaudRate(Integer value) {
        
        SerialPortBaudRate baudRate = new SerialPortBaudRate();
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            List<SerialPortBaudRate> values = (List<SerialPortBaudRate>) session.createCriteria(SerialPortBaudRate.class).add(Restrictions.eq("value", value));

            for(SerialPortBaudRate data : values)
            {
                baudRate = data;
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

        return baudRate;
    }

    public SerialPortBufferSize getSerialPortBufferSize(Integer value) {

        SerialPortBufferSize bufferSize = new SerialPortBufferSize();
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            List<SerialPortBufferSize> values = (List<SerialPortBufferSize>) session.createCriteria(SerialPortBufferSize.class).add(Restrictions.eq("value", value));

            for(SerialPortBufferSize data : values)
            {
                bufferSize = data;
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

        return bufferSize;
    }

    public SerialPortDataBit getSerialPortDataBit(Integer value) {

        SerialPortDataBit dataBit = new SerialPortDataBit();
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            List<SerialPortDataBit> values = (List<SerialPortDataBit>) session.createCriteria(SerialPortDataBit.class).add(Restrictions.eq("value", value));

            for(SerialPortDataBit data : values)
            {
                dataBit = data;
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

        return dataBit;
    }

    public SerialPortParityBit getSerialPortParityBit(String value) {

        SerialPortParityBit parityBit = new SerialPortParityBit();
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            List<SerialPortParityBit> values = (List<SerialPortParityBit>) session.createCriteria(SerialPortParityBit.class).add(Restrictions.eq("value", value));

            for(SerialPortParityBit data : values)
            {
                parityBit = data;
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

        return parityBit;

    }

    public SerialPortStopBit getSerialPortStopBit(Double value) {

        SerialPortStopBit stopBit = new SerialPortStopBit();
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            List<SerialPortStopBit> values = (List<SerialPortStopBit>) session.createCriteria(SerialPortStopBit.class).add(Restrictions.eq("value", value));

            for(SerialPortStopBit data : values)
            {
                stopBit = data;
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

        return stopBit;
    }

}
