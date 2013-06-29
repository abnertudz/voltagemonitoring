/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.listeners.menubar;

import datacollector.view.dialogs.ComAutoDetectDialog;
import datacollector.view.dialogs.ComDisconnectDialog;
import datacollector.view.dialogs.DeviceSettingDialog;
import datacollector.view.applications.ComSetup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Abner
 */
public class MenubarActionListener implements ActionListener{

     // variables that holds the instance of this object
    private static final MenubarActionListener menubarListener = new MenubarActionListener();
    
    private enum MenubarActionCommands
    {
        Connect, Disconnect, AutoDetect, Import, Export, Print, Start, Stop, DeviceSetting;
    }

    public void actionPerformed(ActionEvent e) {
        
       MenubarActionCommands command = MenubarActionCommands.valueOf(e.getActionCommand());
       
        switch(command)
        {
            case Connect:
                 ComSetup comDialog = ComSetup.getInstance();
                 comDialog.launch();
                break;
            case Disconnect:
                ComDisconnectDialog dialog = ComDisconnectDialog.getInstance();
                dialog.launch();
                break;
            case AutoDetect:
                ComAutoDetectDialog autoDialog = ComAutoDetectDialog.getInstance();
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
                DeviceSettingDialog deviceDialog = DeviceSettingDialog.getInstance();
                deviceDialog.launch();
                break;
        }
    }

    public static MenubarActionListener getInstance()
    {
        return menubarListener;
    }

}
