/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.core;

import datacollector.com.CommConnector;
import datacollector.view.applications.AppView;
import datacollector.globals.GlobalVariables;

/**
 *
 * @author Abner
 */
public class ComReadWriteCore implements Runnable{

    private CommConnector comPort;
    private AppView app;
    private int counter;
    private boolean startRead;
    private static int MAX_FRAME_USB = 7;
    private static int MAX_FRAME_COM = 9;


    public ComReadWriteCore(CommConnector comm)
    {
        app = AppView.getInstance();
        comPort = comm;
        counter = 0;
        startRead = false;
    }
    
    public void run() {

      /*  int readData = 0;
        switch(GlobalVariables.HARDWARE_SETTING)
        {
            case 1:
                readComData(readData);
                break;
            case 2:
                readUsbData(readData);
                break;
        }  */
    }

    public boolean writeUsbData(int data)
    {
        boolean result = true;
        if(GlobalVariables.START_COM_WRITE)
        {
            byte[] sendData = null;
            
            sendData[0] = (byte)GlobalVariables.writeDataUsb.getFramestart();
            sendData[1] = (byte)GlobalVariables.writeDataUsb.getCommand();
            sendData[2] = (byte)GlobalVariables.writeDataUsb.getData1LSB();
            sendData[3] = (byte)GlobalVariables.writeDataUsb.getData1MSB();
            sendData[4] = (byte)GlobalVariables.writeDataUsb.getData2();
            sendData[5] = (byte)GlobalVariables.writeDataUsb.getCs();
            sendData[6] = (byte)GlobalVariables.writeDataUsb.getFrameEnd();

            result = comPort.write(sendData);
            GlobalVariables.START_COM_WRITE = false;
        }
        
        return result;
    }

    public boolean writeComData(int data)
    {
        boolean result = true;
        if(GlobalVariables.START_COM_WRITE)
        {
            byte[] sendData = null;

            sendData[0] = (byte)GlobalVariables.writeDataCom.getFramestart();
            sendData[1] = (byte)GlobalVariables.writeDataCom.getCommand();
            sendData[2] = (byte)GlobalVariables.writeDataCom.getAddressLSB();
            sendData[3] = (byte)GlobalVariables.writeDataCom.getAddressMSB();
            sendData[4] = (byte)GlobalVariables.writeDataCom.getData1LSB();
            sendData[5] = (byte)GlobalVariables.writeDataCom.getData1MSB();
            sendData[6] = (byte)GlobalVariables.writeDataCom.getData2();
            sendData[7] = (byte)GlobalVariables.writeDataCom.getCs();
            sendData[8] = (byte)GlobalVariables.writeDataCom.getFrameEnd();

            result = comPort.write(sendData);
            GlobalVariables.START_COM_WRITE = false;
        }

        return result;
    }
    
    public void readUsbData(int readData)
    {
        if(GlobalVariables.START_COM_READ)
        {
            readData = comPort.read();
            if(GlobalVariables.FRAME_START == readData && false == startRead)
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
                GlobalVariables.ADDRESS = 0;
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

            if(counter > MAX_FRAME_USB)
            {
                counter = 0;
                startRead = false;
                GlobalVariables.START_COM_READ = false;
                GlobalVariables.READ_FINISH = true;
                GlobalVariables.ADDRESS = 0;
            }
        }
    }

    public void readComData(int readData)
    {
        if(GlobalVariables.START_COM_READ)
        {
            readData = comPort.read();
            if(GlobalVariables.FRAME_START == readData && false == startRead)
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
                GlobalVariables.ADDRESS = 0;
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

            if(counter > MAX_FRAME_COM)
            {
                counter = 0;
                startRead = false;
                GlobalVariables.START_COM_READ = false;
                GlobalVariables.READ_FINISH = true;
                GlobalVariables.ADDRESS = 0;
            }
        }
    }

}
