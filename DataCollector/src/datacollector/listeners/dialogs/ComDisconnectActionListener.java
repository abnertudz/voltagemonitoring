/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.listeners.dialogs;
import datacollector.com.CommConnector;
import datacollector.view.dialogs.ComDisconnectDialog;
import datacollector.view.applications.ComSetup;
import datacollector.constants.StringConstants;
import datacollector.globals.GlobalVariables;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;

/**
 *
 * @author Abner
 */
public class ComDisconnectActionListener implements ActionListener{

    // Variable that holds the instance of this object
    private static final ComDisconnectActionListener app = new ComDisconnectActionListener();

    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        ComDisconnectDialog dialog = ComDisconnectDialog.getInstance();
        
        if(actionCommand.equals(StringConstants.OK_BUTTON))
        {
            Iterator iterate = GlobalVariables.comConnections.iterator();
            while(iterate.hasNext())
            {
                CommConnector com = (CommConnector) iterate.next();
                if(true == com.close())
                {
                    iterate.remove();
                    System.out.println("Succesful closing port.");
                    dialog.setVisible(false);
                }
                else
                {
                    System.out.println("Error closing port.");
                }
            }
        }
        else if(actionCommand.equals(StringConstants.CANCEL_BUTTON))
        {
            dialog.setVisible(false);
        }
    }

    public static ComDisconnectActionListener getInstance()
    {
        return app;
    }

}
