/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.factories;

import datacollector.core.FactoryCore;
import datacollector.dao.implementation.*;

/**
 *
 * @author Abner
 */
public class DaoFactory extends FactoryCore{

    // static instances of dialog objects
    private static final DeviceDaoImplementation device = new DeviceDaoImplementation();


    public DaoFactory()
    {
        super();
    }

    private enum Daos
    {
        Device;
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
            default:
                String msg = className + "Dialog cannot be found in datacollector.view.dialogs.* package";
                DaoFactory.errorLog(new Exception(),msg);
                break;
        }

        return newInstance;
    }

    public static Object gewInstance(String className)
    {
        Daos dao = Daos.valueOf(className);
        Object instance = null;
        switch(dao)
        {
            case Device:
                instance = device;
                break;
            default:
                String msg = className + "Dialog cannot be found in datacollector.view.dialogs.* package";
                DaoFactory.errorLog(new Exception(),msg);
                break;
        }

        return instance;
    }

}
