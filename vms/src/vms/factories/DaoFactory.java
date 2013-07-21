/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.factories;

import vms.core.FactoryCore;
import vms.dao.implementation.*;

/**
 *
 * @author Abner
 */
public class DaoFactory extends FactoryCore{

    // static instances of dialog objects
    private static final DeviceDaoImplementation device = new DeviceDaoImplementation();
    private static final SystemDefaultSettingDaoImplementation setting = new SystemDefaultSettingDaoImplementation();
    private static final SystemPreferenceDaoImplementation preference = new SystemPreferenceDaoImplementation();
    private static final SystemSerialPortSettingDaoImplementation serial = new SystemSerialPortSettingDaoImplementation();


    public DaoFactory()
    {
        super();
    }

    private enum Daos
    {
        Device, SystemDefaultSetting, SystemPreference, SystemSerialPortSetting;
    }

    public static Object newInstance(String className)
    {
        Daos dao = Daos.valueOf(className);
        Object newInstance = null;
        switch(dao)
        {
            case Device:
                newInstance = new DeviceDaoImplementation();
                break;
            case SystemDefaultSetting:
                newInstance = new SystemDefaultSettingDaoImplementation();
                break;
            case SystemPreference:
                newInstance = new SystemPreferenceDaoImplementation();
                break;
            case SystemSerialPortSetting:
                newInstance = new SystemSerialPortSettingDaoImplementation();
                break;
            default:
                String msg = className + "Dialog cannot be found in vms.view.dialogs.* package";
                DaoFactory.errorLog(new Exception(),msg);
                break;
        }

        return newInstance;
    }

    public static Object getInstance(String className)
    {
        Daos dao = Daos.valueOf(className);
        Object instance = null;
        switch(dao)
        {
            case Device:
                instance = device;
                break;
            case SystemDefaultSetting:
                instance = setting;
                break;
            case SystemPreference:
                instance = preference;
                break;
            case SystemSerialPortSetting:
                instance = serial;
                break;
            default:
                String msg = className + "Dialog cannot be found in vms.view.dialogs.* package";
                DaoFactory.errorLog(new Exception(),msg);
                break;
        }

        return instance;
    }

}
