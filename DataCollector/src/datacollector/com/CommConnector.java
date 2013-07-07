/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.com;
import datacollector.com.data.ComConnectData;
import datacollector.constants.StringConstants;
import datacollector.globals.GlobalVariables;
import datacollector.listeners.serialport.SerialPortListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.comm.*;



/**
 *
 * @author Abner
 */
public class CommConnector{

    // Variable that holds the instance of this object
   // private static final CommConnector comm = new CommConnector();

    private String driverName;
    private CommDriver commDriver;
    private OutputStream out;
    private InputStream in;
    private CommPortIdentifier portIdentifier;
    private SerialPort serialPort;
    private ComConnectData comData;

    public static final int COMM_CONNECT_SUCCESS = 1;
    public static final int COMM_CONNECT_FAILED = 2;
    public static final int COMM_CONNECT_INUSE = 3;
    public static final int COMM_CONNECT_PORT_NOT_FOUND = 4;
    public static final int COMM_CONNECT_PORT_NOT_SERIAL = 5;
    public static final int COMM_CONNECT_PORT_NOT_SUPPORTED = 6;

    private final int MAX_COMM = 100;

    /**
     *  Constructor
     */
    public CommConnector()
    {
        initializeClassVariables();
        initializeDriver();
    }

    /**
     * Initialize class variables
     */
    public void initializeClassVariables()
    {
        driverName = new String();
        commDriver = null;
        out = null;
        portIdentifier = null;
        serialPort = null;
        in = null;
        comData = new ComConnectData();
    }
    /**
     *  Initialize javax.comm driver settings
     */
    public void initializeDriver()
    {
        Properties config = new Properties();
        try
        {            
            String dir = System.getProperty("user.dir") + StringConstants.propertiesDir;
            FileInputStream in = new FileInputStream(dir);
            config.load(in);
            this.driverName = config.getProperty(StringConstants.comDriverKey);
            in.close();
            
        }
        catch (Exception e)
        {
            Logger.getLogger(CommConnector.class.getName()).log(Level.SEVERE, null, e);
        }

        try
        {
            commDriver = (CommDriver)Class.forName(this.driverName).newInstance();
            commDriver.initialize();
        }
        catch (Exception e)
        {
            Logger.getLogger(CommConnector.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }

    /**
     *  Gets comm port lists
     */
     public String[] getCommPortLists()
     {
         String[] ports = new String[this.MAX_COMM];
         int index = 0;
         CommPortIdentifier portIdentifier = null;
         Enumeration portList = CommPortIdentifier.getPortIdentifiers();
         while(portList.hasMoreElements())
         {
             portIdentifier = (CommPortIdentifier)portList.nextElement();
             if(portIdentifier.getPortType() == CommPortIdentifier.PORT_SERIAL);
             {
                 ports[index] = (String)portIdentifier.getName();
                 index++;
             }
         }

         return ports;
     }

     /**
      *  Connect to serial port
      *  @return int Connection status result
      */

     public int connect(String comm, int baudrate, int dataBits, int stopBits, int parity, int bufferSize)
     {
         int result = CommConnector.COMM_CONNECT_SUCCESS;
         try
         {
             portIdentifier = CommPortIdentifier.getPortIdentifier(comm);             
         }
         catch(NoSuchPortException  e)
         {
             result = CommConnector.COMM_CONNECT_FAILED;
             Logger.getLogger(CommConnector.class.getName()).log(Level.SEVERE, null, e);
         }

         if(portIdentifier.getPortType() == CommPortIdentifier.PORT_SERIAL)
         {
             try
             {
                  serialPort = (SerialPort)portIdentifier.open(StringConstants.connIdentifier, 1000);
                  serialPort.setSerialPortParams(baudrate, dataBits, stopBits, parity);
                  out = serialPort.getOutputStream();
                  in = serialPort.getInputStream();

                  serialPort.addEventListener(new SerialPortListener());

                  serialPort.notifyOnDataAvailable(true);
                  serialPort.notifyOnBreakInterrupt(true);
                  serialPort.notifyOnCarrierDetect(true);
                  serialPort.notifyOnCTS(true);
                  serialPort.notifyOnDataAvailable(true);
                  serialPort.notifyOnDSR(true);
                  serialPort.notifyOnFramingError(true);
                  serialPort.notifyOnOutputEmpty(true);
                  serialPort.notifyOnOverrunError(true);
                  serialPort.notifyOnParityError(true);
                  serialPort.notifyOnRingIndicator(true);

                  comData.setupData(comm, baudrate, parity, dataBits, stopBits, bufferSize);
             }
             catch (PortInUseException e)
             {
                  result = CommConnector.COMM_CONNECT_INUSE;
                  Logger.getLogger(CommConnector.class.getName()).log(Level.SEVERE, null, e);
             }
             catch (UnsupportedCommOperationException e)
             {
                  result = CommConnector.COMM_CONNECT_PORT_NOT_SUPPORTED;
                  Logger.getLogger(CommConnector.class.getName()).log(Level.SEVERE, null, e);
             }
             catch (TooManyListenersException e)
             {
                  result = CommConnector.COMM_CONNECT_INUSE;
                  Logger.getLogger(CommConnector.class.getName()).log(Level.SEVERE, null, e);
             }
             catch (IOException e)
             {
                 result = CommConnector.COMM_CONNECT_FAILED;
                 Logger.getLogger(CommConnector.class.getName()).log(Level.SEVERE, null, e);
             }
         }
         else
         {
             result = CommConnector.COMM_CONNECT_PORT_NOT_SERIAL;
             Logger.getLogger(CommConnector.class.getName()).log(Level.SEVERE, null, new Exception());
         }

         return result;
     }

     /**
      * Writes data to Serial Port
      */
     public boolean write(byte[] data)
     {
         boolean result = true;
         try
         {
             out.write(data);
         }
         catch(IOException e)
         {
             result = false;
             Logger.getLogger(CommConnector.class.getName()).log(Level.SEVERE, null, e);             
         }         
         return result;
     }

     /**
      *  Reads data in Serial Port
      */
      public int read(byte[] buffer)
      {
          int length = 0;
          try
          {
             if(GlobalVariables.DATA_AVAILABLE)
             {
                 while(in.available() > 0)
                 {
                     length = in.read(buffer);
                 }
                 GlobalVariables.DATA_AVAILABLE = false;
             }             
          }
          catch(IOException e)
          {             
             Logger.getLogger(CommConnector.class.getName()).log(Level.SEVERE, null, e);
          }
          return length;
      }

     /**
      *
      */
     public boolean close()
     {
         boolean result = true;

         try
         {
             out.close();
             serialPort.close();
         }
         catch(IOException e)
         {
              result = false;
              Logger.getLogger(CommConnector.class.getName()).log(Level.SEVERE, null, e);
         }
         
         return result;
     }

     public CommDriver getCommDriver()
     {
         return commDriver;
     }

     public OutputStream getOutputStream()
     {
         return out;
     }

     public InputStream getInputStream()
     {
         return in;
     }

     public SerialPort getSerialPort()
     {
         return serialPort;
     }      

    /**
     *  Get the instance of this object, SingleTon
     */
   // public static CommConnector getInstance()
   // {
      //  return comm;
  //  }

}
