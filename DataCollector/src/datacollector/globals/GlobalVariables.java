/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.globals;

import datacollector.com.CommConnector;
import datacollector.com.data.ComCollectorData;
import datacollector.com.data.UsbCollectorData;
import datacollector.wizard.data.SetupWizardData;
import java.util.ArrayList;

/**
 *
 * @author Abner
 */
public class GlobalVariables {

    // Flags for Serial port reading
    public static boolean START_COM_WRITE = false;
    public static boolean START_COM_READ = false;

    // static instruction commands
    public static final int FRAME_START = 85;
    public static final int FRAME_END = 170;    
    public static final int READ_DEVICE_ADDRESS_PC = 1;
    public static final int WRITE_DEVICE_ADDRESS_PC = 2;
    public static final int READ_VOLTAGE_PC = 3;

    //
    public static int ADDRESS = 0;
    
    //
    public static boolean READ_FINISH = false;

    //
    public static ArrayList<Integer> readErrorAddress = new ArrayList<Integer>();

    //
    public static ComCollectorData writeDataCom = new ComCollectorData();
    public static ComCollectorData readDataCom = new ComCollectorData();

    public static UsbCollectorData writeDataUsb = new UsbCollectorData();
    public static UsbCollectorData readDataUsb = new UsbCollectorData();

    //
    public static int HARDWARE_SETTING = 1;

    //
    public static ArrayList<CommConnector> comConnections = new ArrayList<CommConnector>();

    //
    public static SetupWizardData configData = new SetupWizardData();


}
