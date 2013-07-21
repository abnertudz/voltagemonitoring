/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.factories;

import vms.core.FactoryCore;
import vms.pojos.Device;

/**
 *
 * @author Abner
 */
public class PojoFactory extends FactoryCore{

    // static instances of dialog objects
    private static final Device device = new Device();


    public PojoFactory()
    {
        super();
    }

    private enum Pojos
    {
        Device;
    }

    public static Object newInstance(String className)
    {
        Pojos pojo = Pojos.valueOf(className);
        Object newInstance = null;
        switch(pojo)
        {
            case Device:
                newInstance = new Device();
                break;
            default:
                String msg = className + "Dialog cannot be found in vms.view.dialogs.* package";
                PojoFactory.errorLog(new Exception(),msg);
                break;
        }

        return newInstance;
    }

    public static Object gewInstance(String className)
    {
        Pojos pojo = Pojos.valueOf(className);
        Object instance = null;
        switch(pojo)
        {
            case Device:
                instance = device;
                break;
            default:
                String msg = className + "Dialog cannot be found in vms.view.dialogs.* package";
                PojoFactory.errorLog(new Exception(),msg);
                break;
        }

        return instance;
    }


}
