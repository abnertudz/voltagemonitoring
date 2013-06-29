/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.com.threads;

import datacollector.core.ComReadWriteCore;
import datacollector.com.CommConnector;
import datacollector.globals.GlobalVariables;

/**
 *
 * @author Abner
 */
public class ComReaderThread extends ComReadWriteCore{

    public ComReaderThread(CommConnector comm)
    {
        super(comm);
    }
    
    @Override
    public void run() {

        int readData = 0;
        switch(GlobalVariables.HARDWARE_SETTING)
        {
            case 1:
                this.readComData(readData);
                break;
            case 2:
                this.readUsbData(readData);
                break;
        }        
    }

}
