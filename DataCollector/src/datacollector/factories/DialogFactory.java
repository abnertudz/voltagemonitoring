/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.factories;

import datacollector.core.FactoryCore;
import datacollector.view.applications.AppView;
import datacollector.view.dialogs.*;
import java.io.File;

/**
 *
 * @author Abner
 */
public class DialogFactory extends FactoryCore{

    // static instances of dialog objects
    private static final ComAutoDetectDialog autoDetect = new ComAutoDetectDialog(AppView.getInstance(),true);
    private static final ComDisconnectDialog disconnect = new ComDisconnectDialog(AppView.getInstance(),true);
    private static final ComSettingDialog setting = new ComSettingDialog(AppView.getInstance(),true);
    private static final DeviceSettingDialog deviceSetting = new DeviceSettingDialog(AppView.getInstance(),true);
    private static final ErrorDialog error = new ErrorDialog(AppView.getInstance(),true);
    private static final WarningDialog warning = new WarningDialog(AppView.getInstance(),true);
    

    public DialogFactory()
    {
        super();
    }

    private enum ClassDialogs
    {
        ComAutoDetect,ComDisconnect,ComSetting,DeviceSetting,Error,Warning;
    }
    
    public static Object newInstance(String className)
    {
        ClassDialogs dialogs = ClassDialogs.valueOf(className);
        Object newInstance = null;
        switch(dialogs)
        {
            case ComAutoDetect:
                newInstance = new ComAutoDetectDialog(AppView.getInstance(),true);
                break;
            case ComDisconnect:
                newInstance = new ComDisconnectDialog(AppView.getInstance(),true);
                break;
            case ComSetting:
                newInstance = new ComSettingDialog(AppView.getInstance(),true);
                break;
            case DeviceSetting:
                newInstance = new DeviceSettingDialog(AppView.getInstance(),true);
                break;
            case Error:
                newInstance = new ErrorDialog(AppView.getInstance(),true);
                break;
            case Warning:
                newInstance = new WarningDialog(AppView.getInstance(),true);
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
        ClassDialogs dialogs = ClassDialogs.valueOf(className);
        Object instance = null;
        switch(dialogs)
        {
            case ComAutoDetect:
                instance = autoDetect;
                break;
            case ComDisconnect:
                instance = disconnect;
                break;
            case ComSetting:
                instance = setting;
                break;
            case DeviceSetting:
                instance = deviceSetting;
                break;
            case Error:
                instance = error;
                break;
            case Warning:
                instance = warning;
                break;
            default:
                String msg = className + "Dialog cannot be found in datacollector.view.dialogs.* package";
                DialogFactory.errorLog(new Exception(),msg);
                break;
        }

        return instance;
    }

}
