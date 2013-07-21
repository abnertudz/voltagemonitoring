/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.com.threads;

import vms.com.CommConnector;
import vms.core.ComReadWriteCore;
import vms.globals.GlobalVariables;

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

        while(true)
        {
            if(GlobalVariables.START_COM_READ && !interrupted())
            {
                switch(GlobalVariables.HARDWARE_SETTING)
                {
                    case 1:
                        readComData();
                        break;
                    case 2:
                        readUsbData();
                        break;
                }
            }
        }
    }

}
