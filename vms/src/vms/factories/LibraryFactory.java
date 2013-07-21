/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.factories;

import vms.libraries.*;
import vms.core.FactoryCore;

/**
 *
 * @author Abner
 */
public class LibraryFactory extends FactoryCore{

    // static instances of dialog objects
    private static final StringLibrary string = new StringLibrary();
    private static final SerialPortLibrary serial = new SerialPortLibrary();
    private static final DateTimeLibrary date = new DateTimeLibrary();


    public LibraryFactory()
    {
        super();
    }

    private enum Libraries
    {
        String,SerialPort,DateTime;
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
            case DateTime:
                newInstance = new DateTimeLibrary();
                break; 
            default:
                String msg = className + "Dialog cannot be found in vms.view.dialogs.* package";
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
            case DateTime:
                instance = date;
                break; 
            default:
                String msg = className + "Dialog cannot be found in vms.view.dialogs.* package";
                LibraryFactory.errorLog(new Exception(),msg);
                break;
        }

        return instance;
    }

}
