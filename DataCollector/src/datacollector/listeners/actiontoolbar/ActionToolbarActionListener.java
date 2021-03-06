/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.listeners.actiontoolbar;

import datacollector.factories.DialogFactory;
import datacollector.factories.ThreadFactory;
import datacollector.globals.GlobalVariables;
import datacollector.view.dialogs.ComAutoDetectDialog;
import datacollector.view.dialogs.ComDisconnectDialog;
import datacollector.view.dialogs.ComSettingDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Abner
 */
public class ActionToolbarActionListener implements ActionListener{

    public enum ToolbarActionCommands
    {
        Connect, Disconnect, AutoDetect, Import, Export, Print, Start, Stop;
    }
     
    public void actionPerformed(ActionEvent e)
    {
        ToolbarActionCommands command = ToolbarActionCommands.valueOf(e.getActionCommand());
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
                GlobalVariables.START_INTERVAL_COUNTER = true;
                Thread interval = ThreadFactory.getInstance("IntervalCounter");
                if(!interval.isAlive())
                {
                    interval.start();
                }
                
                GlobalVariables.START_MONITORING = true;
                Thread monitorStart = ThreadFactory.getInstance("MonitorDevices");
                if(!monitorStart.isAlive())
                {
                    monitorStart.start();
                }

                
                break;
            case Stop:
                Thread monitorStop = ThreadFactory.getInstance("MonitorDevices");
                GlobalVariables.START_MONITORING = false;
                monitorStop.interrupt();
                break;
        }
    }

}
