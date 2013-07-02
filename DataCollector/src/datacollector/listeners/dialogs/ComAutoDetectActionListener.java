/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.listeners.dialogs;
import datacollector.com.CommConnector;
import datacollector.view.dialogs.ComAutoDetectDialog;
import datacollector.constants.StringConstants;
import datacollector.factories.DialogFactory;
import datacollector.globals.GlobalVariables;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author Abner
 */
public class ComAutoDetectActionListener implements ActionListener{

    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        ComAutoDetectDialog dialog = (ComAutoDetectDialog) DialogFactory.getInstance("ComAutoDetect");
        CommConnector comm = new CommConnector();
        String comPort = this.getSelectedSerialPort();
        
        if(actionCommand.equals(StringConstants.OK_BUTTON))
        {
            System.out.println(comPort);
            int result = comm.connect(comPort,StringConstants.defaultBaudRate,StringConstants.defaultDataBit,StringConstants.defaultStopBit,StringConstants.defaultParityBit,StringConstants.defaultBufferSize);

            switch(result)
            {
                case 1:
                    dialog.setVisible(false);
                    GlobalVariables.comConnections.add(comm);
                    System.out.println("Success");
                    break;
                case 2:
                    System.out.println("Failed");
                    break;
                case 3:
                    System.out.println("In Use");
                    break;
                case 4:
                    System.out.println("Port not found");
                    break;
                case 5:
                    System.out.println("Port not serial");
                    break;
                case 6:
                    System.out.println("Port not supported");
                    break;
            }
        }
        else if(actionCommand.equals(StringConstants.CANCEL_BUTTON))
        {
            dialog.setVisible(false);
        }
    }

    private String getSelectedSerialPort()
    {
        String comPort = null;
        ComAutoDetectDialog dialog = (ComAutoDetectDialog) DialogFactory.getInstance("ComAutoDetect");
        if(dialog.getCb1().isSelected())
        {
            comPort = dialog.getCb1().getText();
        }

        return comPort;
    }
}
