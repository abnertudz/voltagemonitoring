/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.listeners.actiontoolbar;

import datacollector.view.dialogs.ComAutoDetectDialog;
import datacollector.view.dialogs.ComDisconnectDialog;
import datacollector.view.applications.AppView;
import datacollector.view.applications.ComSetup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Abner
 */
public class ComToolbarActionListner implements ActionListener{

    // variables that holds the instance of this object
    private static final ComToolbarActionListner toolbarListener = new ComToolbarActionListner();

    AppView app;

    public ComToolbarActionListner()
    {
        app = new AppView();
    }

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
        }
    }
    

    public static ComToolbarActionListner getInstance()
    {
        return toolbarListener;
    }

}
