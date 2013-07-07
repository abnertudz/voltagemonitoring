/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.core;

/**
 *
 * @author Abner
 */
public class ComDataCore {

    protected int frameStart;
    protected int command;
    protected int data1LSB;
    protected int data1MSB;
    protected int data2;
    protected int cs;
    protected int frameEnd;
    protected static int MAX_FRAME_USB = 7;
    protected static int MAX_FRAME_COM = 9;

    protected double ADC1_VOLTAGE_UNIT = 100;

    public ComDataCore()
    {
         initializeVariables();
    }
    
    public void initializeVariables()
    {
         frameStart = 0;
         command = 0;
         data1LSB = 0;
         data1MSB = 0;
         data2 = 0;
         cs = 0;
         frameEnd = 0;
    }

    public boolean readError()
    {
        int total = (frameStart + command + data1LSB + data1MSB + data2)%256;
        return (cs == total) ? true : false;
    }
    
    public void clearData()
    {
        initializeVariables();
    }
    public int getData1()
    {
        int data = data1MSB << 8;
        data = data & data1LSB;
        return data;
    }
    
    public void setFrameStart(int data)
    {
        frameStart = data;
    }

    public int getFramestart()
    {
        return frameStart;
    }

    public void setCommand(int data)
    {
        command = data;
    }

    public int getCommand()
    {
        return command;
    }

    public void setData1LSB(int data)
    {
        data1LSB = data;
    }

    public int getData1LSB()
    {
        return data1LSB;
    }

    public void setData1MSB(int data)
    {
        data1MSB = data;
    }

    public int getData1MSB()
    {
        return data1MSB;
    }

    public void setData2(int data)
    {
        data2 = data;
    }

    public int getData2()
    {
        return data2;
    }

    public void setCs(int data)
    {
        cs = data;
    }

    public int getCs()
    {
        return cs;
    }

    public void setFrameEnd(int data)
    {
        frameEnd = data;
    }

    public int getFrameEnd()
    {
        return frameEnd;
    }

    public double getDeviceVoltage()
    {
        double voltage= ((data1MSB << 8) | data1LSB);
        return voltage;
    }

    public int getDeviceAddress()
    {
         int address = data1MSB << 8;
         return (address | data1LSB);
    }

    public byte[] getBytes()
    {
        byte[] sendData = new byte[MAX_FRAME_USB];

        sendData[0] = (byte)frameStart;
        sendData[1] = (byte)command;
        sendData[2] = (byte)data1LSB;
        sendData[3] = (byte)data1MSB;
        sendData[4] = (byte)data2;
        sendData[5] = (byte)cs;
        sendData[6] = (byte)frameEnd;
            
        return sendData;
    }

}
