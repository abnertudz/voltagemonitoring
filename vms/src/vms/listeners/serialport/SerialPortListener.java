/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.listeners.serialport;

import vms.globals.GlobalVariables;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;

/**
 *
 * @author Abner
 */
public class SerialPortListener implements SerialPortEventListener{

     public void serialEvent(SerialPortEvent event)
     {
         switch(event.getEventType())
         {
            case SerialPortEvent.BI:
                System.out.println("SerialPortEvent.BI occurred");
            case SerialPortEvent.OE:
                System.out.println("SerialPortEvent.OE occurred");
            case SerialPortEvent.FE:
                System.out.println("SerialPortEvent.FE occurred");
            case SerialPortEvent.PE:
                System.out.println("SerialPortEvent.PE occurred");
            case SerialPortEvent.CD:
                System.out.println("SerialPortEvent.CD occurred");
            case SerialPortEvent.CTS:
                System.out.println("SerialPortEvent.CTS occurred");
            case SerialPortEvent.DSR:
                System.out.println("SerialPortEvent.DSR occurred");
            case SerialPortEvent.RI:
                System.out.println("SerialPortEvent.RI occurred");
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                System.out.println("SerialPortEvent.OUTPUT_BUFFER_EMPTY occurred");
                break;
            case SerialPortEvent.DATA_AVAILABLE:
                System.out.println("SerialPortEvent.DATA_AVAILABLE occurred");
                GlobalVariables.DATA_AVAILABLE = true;
                break;
        }
     }

}
