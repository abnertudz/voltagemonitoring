/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.listeners.menubar;

import datacollector.factories.DialogFactory;
import datacollector.view.dialogs.ComAutoDetectDialog;
import datacollector.view.dialogs.ComDisconnectDialog;
import datacollector.view.dialogs.ComSettingDialog;
import datacollector.view.dialogs.DeviceSettingDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Abner
 */
public class MenubarActionListener implements ActionListener{
    
    private enum MenubarActionCommands
    {
        Connect, Disconnect, AutoDetect, Import, Export, Print, Start, Stop, DeviceSetting;
    }

    public void actionPerformed(ActionEvent e) {
        
       MenubarActionCommands command = MenubarActionCommands.valueOf(e.getActionCommand());
       
        switch(command)
        {
            case Connect:
                 ComSettingDialog comDialog = (ComSettingDialog) DialogFactory.getInstance("ComSetting");
                 comDialog.launch();
                break;
            case Disconnect:
                ComDisconnectDialog dialog = (ComDisconnectDialog) DialogFactory.getInstance("ComDisconnect");
                dialog.launch();
                break;
            case AutoDetect:
                ComAutoDetectDialog autoDialog = (ComAutoDetectDialog) DialogFactory.getInstance("ComAutoDetect");
                autoDialog.launch();
                break;
            case Import:
                break;
            case Export:
                break;
            case Print:
                break;
            case Start:
                break;
            case Stop:
                break;
            case DeviceSetting:
                DeviceSettingDialog deviceDialog = (DeviceSettingDialog) DialogFactory.getInstance("DeviceSetting");
                deviceDialog.launch();
                break;
        }
    }

}
