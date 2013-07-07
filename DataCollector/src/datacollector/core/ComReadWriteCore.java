/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.core;

import datacollector.com.CommConnector;
import datacollector.globals.GlobalVariables;

/**
 *
 * @author Abner
 */
public class ComReadWriteCore extends Thread{

    private CommConnector comPort;
    private int counter;
    private int retryCounter;
    private boolean startRead;
    private static int MAX_FRAME_USB = 7;
    private static int MAX_FRAME_COM = 9;
    private static int MAX_READ_RETRY = 100;


    public ComReadWriteCore(CommConnector com)
    {
        comPort = com;
        counter = 0;
        startRead = false;
        retryCounter = 0;
    }

    public void setComPort(CommConnector comm)
    {
        comPort = comm;
    }

    /**
     * This method should be overriden by subclasses
     */
    @Override
    public void run() {
    }

    public boolean writeUsbData()
    {
        boolean result = true;
        if(GlobalVariables.START_COM_WRITE)
        {            
            result = comPort.write(GlobalVariables.writeDataUsb.getBytes());
            GlobalVariables.START_COM_WRITE = false;
            GlobalVariables.WRITE_FINISH = true;
        }
        
        return result;
    }

    public boolean writeComData()
    {
        boolean result = true;
        if(GlobalVariables.START_COM_WRITE)
        {
            result = comPort.write(GlobalVariables.writeDataCom.getBytes());
            GlobalVariables.START_COM_WRITE = false;
            GlobalVariables.WRITE_FINISH = true;
        }

        return result;
    }
    
    public void readUsbData()
    {
        counter = 0;
        startRead = false;
        retryCounter = 0;
        while(GlobalVariables.START_COM_READ)
        {
            byte readBuffer[] = new byte[MAX_FRAME_USB];
            int length = comPort.read(readBuffer);

            GlobalVariables.START_COM_READ = false;
            GlobalVariables.READ_FINISH = true;

            
           /* if(GlobalVariables.FRAME_START == readData && false == startRead)
            {
                startRead = true;
                counter++;
                GlobalVariables.readDataUsb.setFrameStart(readData);
            }
            else if(GlobalVariables.FRAME_END == readData && true == startRead && counter == MAX_FRAME_USB)
            {
                startRead = false;
                counter = 0;
                GlobalVariables.readDataUsb.setFrameEnd(readData);
                GlobalVariables.READ_FINISH = true;
                GlobalVariables.START_COM_READ = false;
            }

            if(true == startRead)
            {
                switch(counter)
                {
                    case 2:
                        GlobalVariables.readDataUsb.setCommand(readData);
                        counter++;
                        break;
                    case 3:
                        GlobalVariables.readDataUsb.setData1LSB(readData);
                        counter++;
                        break;
                    case 4:
                        GlobalVariables.readDataUsb.setData1MSB(readData);
                        counter++;
                        break;
                    case 5:
                        GlobalVariables.readDataUsb.setData2(readData);
                        counter++;
                        break;
                    case 6:
                        GlobalVariables.readDataUsb.setCs(readData);
                        counter++;
                        if(GlobalVariables.readDataUsb.readError())
                        {
                            GlobalVariables.readErrorAddress.add(GlobalVariables.ADDRESS);
                        }
                        break;
                }
            }
            else
            {                
                if(retryCounter == MAX_READ_RETRY)
                {
                    GlobalVariables.readDataCom.clearData();
                    GlobalVariables.START_COM_READ = false;
                    GlobalVariables.READ_FINISH = true;
                }
            }

            if(counter > MAX_FRAME_USB)
            {
                counter = 0;
                startRead = false;
                GlobalVariables.START_COM_READ = false;
                GlobalVariables.READ_FINISH = true;                
            }*/
        }
    }

    public void readComData()
    {
        counter = 0;
        startRead = false;
        retryCounter = 0;
        while(GlobalVariables.START_COM_READ)
        {
            byte readBuffer[] = new byte[MAX_FRAME_COM];
            int length = comPort.read(readBuffer);
            boolean error = false;

            for(int index=0; index < length; index++)
            {
                int data = (readBuffer[index] & 0xFF);                
                switch(index)
                {
                    case 0:
                        if(GlobalVariables.FRAME_START == data)
                        {
                            GlobalVariables.readDataCom.setFrameStart(data);
                        }
                        else
                        {
                            error = true;
                        }
                        break;                        
                    case 1:
                        if(GlobalVariables.DATA_FROM_DEVICE == data)
                        {
                            GlobalVariables.readDataCom.setCommand(data);
                        }
                        else
                        {
                            error = true;
                        }                        
                        break;
                    case 2:
                       
                        GlobalVariables.readDataCom.setAddressLSB(data);
                        break;
                    case 3:
                        GlobalVariables.readDataCom.setAddressMSB(data);
                        break;
                    case 4:                         
                        GlobalVariables.readDataCom.setData1LSB(data);
                        break;
                    case 5:                         
                        GlobalVariables.readDataCom.setData1MSB(data);
                        break;
                    case 6:
                        GlobalVariables.readDataCom.setData2(data);
                        break;
                    case 7:
                        GlobalVariables.readDataCom.setCs(data);
                        if(GlobalVariables.readDataCom.readError())
                        {
                            GlobalVariables.readErrorAddress.add(GlobalVariables.ADDRESS);
                        }
                        break;
                    case 8:
                        GlobalVariables.readDataCom.setFrameEnd(data);
                        break;
                }
                if(error)
                {
                    GlobalVariables.readDataCom.clearData();
                    System.out.println("READ ERROR.");
                }
            }

            GlobalVariables.START_COM_READ = false;
            GlobalVariables.READ_FINISH = true;
                
            /*if(GlobalVariables.FRAME_START == readData && false == startRead)
            {
                startRead = true;
                counter++;
                GlobalVariables.readDataCom.setFrameStart(readData);
            }
            else if(GlobalVariables.FRAME_END == readData && true == startRead && counter == MAX_FRAME_COM)
            {
                startRead = false;
                counter = 0;
                GlobalVariables.readDataCom.setFrameEnd(readData);
                GlobalVariables.READ_FINISH = true;
                GlobalVariables.START_COM_READ = false;
            }

            if(true == startRead)
            {
                switch(counter)
                {
                    case 2:
                        GlobalVariables.readDataCom.setCommand(readData);
                        counter++;
                        break;
                    case 3:
                        GlobalVariables.readDataCom.setAddressLSB(readData);
                        counter++;
                        break;
                    case 4:
                        GlobalVariables.readDataCom.setAddressMSB(readData);
                        counter++;
                        break;
                    case 5:
                        GlobalVariables.readDataCom.setData1LSB(readData);
                        counter++;
                        break;
                    case 6:
                        GlobalVariables.readDataCom.setData1MSB(readData);
                        counter++;
                        break;
                    case 7:
                        GlobalVariables.readDataCom.setData2(readData);
                        counter++;
                        break;
                    case 8:
                        GlobalVariables.readDataCom.setCs(readData);
                        counter++;
                        if(GlobalVariables.readDataCom.readError())
                        {
                            GlobalVariables.readErrorAddress.add(GlobalVariables.ADDRESS);
                        }
                        break;
                }
            }
            else
            {
                retryCounter++;               
                if(retryCounter == MAX_READ_RETRY)
                {
                    GlobalVariables.readDataCom.clearData();
                    GlobalVariables.START_COM_READ = false;
                    GlobalVariables.READ_FINISH = true;
                }
            }*/

            if(counter > MAX_FRAME_COM)
            {
                counter = 0;
                startRead = false;
                GlobalVariables.START_COM_READ = false;
                GlobalVariables.READ_FINISH = true;                
            }
        }
    }

}
