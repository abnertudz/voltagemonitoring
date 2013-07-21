/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.factories;

import vms.com.threads.*;
import vms.core.FactoryCore;
import vms.globals.GlobalVariables;

/**
 *
 * @author Abner
 */
public class ThreadFactory extends FactoryCore{

     // static instances of dialog objects
    private static final Thread reader = new ComReaderThread(GlobalVariables.comConnection);
    private static final Thread writer = new ComWriterThread(GlobalVariables.comConnection);
    private static final Thread monitor = new MonitorDevicesThread();
    private static final Thread counter = new IntervalCounterThread();


    public ThreadFactory()
    {
        super();
    }

    private enum ComThreads
    {
        ComReader,ComWriter,MonitorDevices,IntervalCounter;
    }

    public static Thread newInstance(String className)
    {
        ComThreads threads = ComThreads.valueOf(className);
        Thread newInstance = null;
        switch(threads)
        {
            case ComReader:
                newInstance = new ComReaderThread(GlobalVariables.comConnection);
                break;
            case ComWriter:
                newInstance = new ComWriterThread(GlobalVariables.comConnection);
                break;
            case MonitorDevices:
                newInstance = new MonitorDevicesThread();
                break;
            case IntervalCounter:
                newInstance = new IntervalCounterThread();
                break;
            default:
                String msg = className + "Dialog cannot be found in vms.view.dialogs.* package";
                ThreadFactory.errorLog(new Exception(),msg);
                break;
        }

        return newInstance;
    }

    public static Thread getInstance(String className)
    {
        ComThreads threads = ComThreads.valueOf(className);
        Thread instance = null;
        switch(threads)
        {
            case ComReader:
                instance = reader;
                break;
            case ComWriter:
                instance = writer;
                break;
            case MonitorDevices:
                instance = monitor;
                break;
            case IntervalCounter:
                instance = counter;
                break;
            default:
                String msg = className + "Dialog cannot be found in vms.view.dialogs.* package";
                ThreadFactory.errorLog(new Exception(),msg);
                break;
        }

        return instance;
    }

}
