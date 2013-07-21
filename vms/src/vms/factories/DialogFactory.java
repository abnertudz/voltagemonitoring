/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.factories;

import vms.core.FactoryCore;
import vms.view.dialogs.*;
import vms.AppView;

/**
 *
 * @author Abner
 */
public class DialogFactory extends FactoryCore{

    // static instances of dialog objects
    private static final ComAutoDetectDialog autoDetect = new ComAutoDetectDialog(AppView.getAppViewCore().getFrame(),true);
    private static final ComDisconnectDialog disconnect = new ComDisconnectDialog(AppView.getAppViewCore().getFrame(),true);
    private static final ComSettingDialog setting = new ComSettingDialog(AppView.getAppViewCore().getFrame(),true);
    private static final DeviceSettingDialog deviceSetting = new DeviceSettingDialog(AppView.getAppViewCore().getFrame(),true);
    private static final ErrorDialog error = new ErrorDialog(AppView.getAppViewCore().getFrame(),true);
    private static final WarningDialog warning = new WarningDialog(AppView.getAppViewCore().getFrame(),true);
    

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
                newInstance = new ComAutoDetectDialog(AppView.getAppViewCore().getFrame(),true);
                break;
            case ComDisconnect:
                newInstance = new ComDisconnectDialog(AppView.getAppViewCore().getFrame(),true);
                break;
            case ComSetting:
                newInstance = new ComSettingDialog(AppView.getAppViewCore().getFrame(),true);
                break;
            case DeviceSetting:
                newInstance = new DeviceSettingDialog(AppView.getAppViewCore().getFrame(),true);
                break;
            case Error:
                newInstance = new ErrorDialog(AppView.getAppViewCore().getFrame(),true);
                break;
            case Warning:
                newInstance = new WarningDialog(AppView.getAppViewCore().getFrame(),true);
                break;
            default:
                String msg = className + "Dialog cannot be found in vms.view.dialogs.* package";
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
                String msg = className + "Dialog cannot be found in vms.view.dialogs.* package";
                DialogFactory.errorLog(new Exception(),msg);
                break;
        }

        return instance;
    }

}
