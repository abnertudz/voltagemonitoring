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
public class ComWriterThread extends ComReadWriteCore{

    public ComWriterThread(CommConnector comm)
    {
        super(comm);
    }

    @Override
    public void run() {

        while(true)
        {
            if(GlobalVariables.START_COM_WRITE && !interrupted())
            {
                switch(GlobalVariables.HARDWARE_SETTING)
                {
                    case 1:
                        writeComData();
                        break;
                    case 2:
                        writeUsbData();
                        break;
                }
            }
        }
    }

}
