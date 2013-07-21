/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.listeners.dialogs;
import vms.com.CommConnector;
import vms.view.dialogs.ComDisconnectDialog;
import vms.constants.StringConstants;
import vms.factories.DialogFactory;
import vms.globals.GlobalVariables;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author Abner
 */
public class ComDisconnectActionListener implements ActionListener{

    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        ComDisconnectDialog dialog = (ComDisconnectDialog) DialogFactory.getInstance("ComDisconnect");
        
        if(actionCommand.equals(StringConstants.OK_BUTTON))
        {
           // Iterator iterate = GlobalVariables.comConnections.iterator();
           // while(iterate.hasNext())
           // {
                CommConnector com = GlobalVariables.comConnection;
                if(true == com.close())
                {
                    System.out.println("Succesful closing port.");
                    dialog.setVisible(false);
                }
                else
                {
                    System.out.println("Error closing port.");
                }
           // }
        }
        else if(actionCommand.equals(StringConstants.CANCEL_BUTTON))
        {
            dialog.setVisible(false);
        }
    }

}
