/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.factories;

import datacollector.com.data.*;
import datacollector.core.FactoryCore;

/**
 *
 * @author Abner
 */
public class ComDataFactory extends FactoryCore{

    // static instances of dialog objects
    private static final ComCollectorData collector = new ComCollectorData();
    private static final ComConnectData connect = new ComConnectData();
    private static final UsbCollectorData usb = new UsbCollectorData();
    
    public ComDataFactory()
    {
        super();
    }

    private enum ComData
    {
        ComCollector,ComConnect,UsbCollector;
    }

    public static Object newInstance(String className)
    {
        ComData data = ComData.valueOf(className);
        Object newInstance = null;
        switch(data)
        {
            case ComCollector:
                newInstance = new ComCollectorData();
                break;
            case ComConnect:
                newInstance = new ComConnectData();
                break;
            case UsbCollector:
                newInstance = new UsbCollectorData();
                break;           
            default:
                String msg = className + "Dialog cannot be found in datacollector.view.dialogs.* package";
                DialogFactory.errorLog(new Exception(),msg);
                break;
        }

        return newInstance;
    }

    public static Object getInstance(String className)
    {
        ComData data = ComData.valueOf(className);
        Object newInstance = null;
        switch(data)
        {
            case ComCollector:
                newInstance = collector;
                break;
            case ComConnect:
                newInstance = connect;
                break;
            case UsbCollector:
                newInstance = usb;
                break;
            default:
                String msg = className + "Dialog cannot be found in datacollector.view.dialogs.* package";
                DialogFactory.errorLog(new Exception(),msg);
                break;
        }

        return newInstance;
    }

}
