/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.listeners.dialogs;
import datacollector.com.CommConnector;
import datacollector.view.applications.ComSetup;
import datacollector.constants.StringConstants;
import datacollector.globals.GlobalVariables;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author Abner
 */
public class ComSetupActionListener implements ActionListener{

    // Variable that holds the instance of this object
    private static final ComSetupActionListener app = new ComSetupActionListener();

    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        ComSetup comSetupDialog = ComSetup.getInstance();
        CommConnector comm = new CommConnector();
        
        if(actionCommand.equals(StringConstants.OK_BUTTON))
        {
            String comPort = comSetupDialog.getComPortCBox().getSelectedItem().toString();
            int baudRate = Integer.parseInt(comSetupDialog.getComBaudRateCBox().getSelectedItem().toString());

            String parityBitsS = comSetupDialog.getComParityBitCBox().getSelectedItem().toString();
            int parityBits = this.getParityBitValue(parityBitsS);

            int dataBits = Integer.parseInt(comSetupDialog.getComDataBitCBox().getSelectedItem().toString());
            
            String stopBitsS = comSetupDialog.getComStopBitCBox().getSelectedItem().toString();
            stopBitsS = stopBitsS.equals("1.5") ? "2" : stopBitsS;
            int stopBits = Integer.parseInt(stopBitsS);
            
            int bufferSize = Integer.parseInt(comSetupDialog.getComBufferSizeCBox().getSelectedItem().toString());            
            int result = comm.connect(comPort,baudRate,dataBits,stopBits, parityBits,bufferSize);

            switch(result)
            {
                case 1:
                    comSetupDialog.setVisible(false);
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
            comSetupDialog.setVisible(false);
        }
    }

    public static ComSetupActionListener getInstance()
    {
        return app;
    }

    private int getParityBitValue(String parity)
    {
        int value = 0;

        if("NONE" == parity)
        {
            value = 0;
        }
        else if("ODD" == parity)
        {
             value = 1;
        }
        else if("EVEN" == parity)
        {
            value = 2;
        }
        else if("MARK" == parity)
        {
            value = 3;
        }        
        else if("SPACE" == parity)
        {
             value = 4;
        }
        return value;
    }

}
