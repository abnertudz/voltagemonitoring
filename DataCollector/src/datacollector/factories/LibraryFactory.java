/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.factories;

import datacollector.libraries.*;
import datacollector.core.FactoryCore;

/**
 *
 * @author Abner
 */
public class LibraryFactory extends FactoryCore{

    // static instances of dialog objects
    private static final StringLibrary string = new StringLibrary();
    private static final SerialPortLibrary serial = new SerialPortLibrary();


    public LibraryFactory()
    {
        super();
    }

    private enum Libraries
    {
        String,SerialPort;
    }

    public static Object newInstance(String className)
    {
        Libraries libs = Libraries.valueOf(className);
        Object newInstance = null;
        switch(libs)
        {
            case String:
                newInstance = new StringLibrary();
                break;
            case SerialPort:
                newInstance = new SerialPortLibrary();
                break;            
            default:
                String msg = className + "Dialog cannot be found in datacollector.view.dialogs.* package";
                LibraryFactory.errorLog(new Exception(),msg);
                break;
        }

        return newInstance;
    }

    public static Object getInstance(String className)
    {
        Libraries libs = Libraries.valueOf(className);
        Object instance = null;
        switch(libs)
        {
            case String:
                instance = string;
                break;
            case SerialPort:
                instance = serial;
                break;
            default:
                String msg = className + "Dialog cannot be found in datacollector.view.dialogs.* package";
                LibraryFactory.errorLog(new Exception(),msg);
                break;
        }

        return instance;
    }

}
