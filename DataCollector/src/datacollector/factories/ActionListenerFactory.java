/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.factories;

import datacollector.core.FactoryCore;
import datacollector.listeners.actiontoolbar.*;
import datacollector.listeners.dialogs.*;
import datacollector.listeners.menubar.MenubarActionListener;

/**
 *
 * @author Abner
 */
public class ActionListenerFactory extends FactoryCore{

    // static instances of dialog objects
    private static final ActionToolbarActionListener actionToolbar = new ActionToolbarActionListener();
    private static final ComAutoDetectActionListener autoDetect = new ComAutoDetectActionListener();
    private static final ComDisconnectActionListener disconnect = new ComDisconnectActionListener();
    private static final ComSetupActionListener setup = new ComSetupActionListener();
    private static final DeviceSettingActionListener deviceSetting = new DeviceSettingActionListener();
    private static final SetupWizardListener wizard = new SetupWizardListener();
    private static final MenubarActionListener menubar = new MenubarActionListener();

    public ActionListenerFactory()
    {
        super();
    }

    private enum ActionListeners
    {
        ActionToolbar,ComAutoDetect,ComDisconnect,ComSetup,DeviceSetting,SetupWizard,Menubar;
    }

    public static Object newInstance(String className)
    {
        ActionListeners listeners = ActionListeners.valueOf(className);
        Object newInstance = null;
        switch(listeners)
        {
            case ActionToolbar:
                newInstance = new ActionToolbarActionListener();
                break;
            case ComAutoDetect:
                newInstance = new ComAutoDetectActionListener();
                break;
            case ComDisconnect:
                newInstance = new ComDisconnectActionListener();
                break;
            case ComSetup:
                newInstance = new ComSetupActionListener();
                break;
            case DeviceSetting:
                newInstance = new DeviceSettingActionListener();
                break;
            case SetupWizard:
                newInstance = new SetupWizardListener();
                break;
            case Menubar:
                newInstance = new MenubarActionListener();
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
        ActionListeners listeners = ActionListeners.valueOf(className);
        Object instance = null;
        switch(listeners)
        {
            case ActionToolbar:
                instance = actionToolbar;
                break;
            case ComAutoDetect:
                instance = autoDetect;
                break;
            case ComDisconnect:
                instance = disconnect;
                break;
            case ComSetup:
                instance = setup;
                break;
            case DeviceSetting:
                instance = deviceSetting;
                break;
            case SetupWizard:
                instance = wizard;
                break;
            case Menubar:
                instance = menubar;
                break;
            default:
                String msg = className + "Dialog cannot be found in datacollector.view.dialogs.* package";
                DialogFactory.errorLog(new Exception(),msg);
                break;
        }

        return instance;
    }

}
