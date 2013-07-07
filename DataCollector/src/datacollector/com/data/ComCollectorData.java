/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.com.data;

import datacollector.core.ComDataCore;

/**
 *
 * @author Abner
 */
public class ComCollectorData extends ComDataCore{

    private int addressLSB;
    private int addressMSB;

    public ComCollectorData()
    {
        super();
        addressLSB = 0;
        addressMSB = 0;
    }

    @Override
    public int getDeviceAddress()
    {
        int address = addressMSB << 8;
        //System.out.println("Address : " + (address | addressLSB));
        return (address | addressLSB);
    }

    @Override
    public double getDeviceVoltage()
    {        
        double voltage= ((((data1MSB << 8) | data1LSB)*ADC1_VOLTAGE_UNIT))/1000;
        return voltage;
    }
     
    public int getAddressLSB()
    {
        return addressLSB;
    }

    public void setAddressLSB(int data)
    {
        addressLSB = data;
    }

     public int getAddressMSB()
    {
        return addressMSB;
    }

    public void setAddressMSB(int data)
    {
        addressMSB = data;
    }

    @Override
    public byte[] getBytes()
    {
        byte[] sendData = new byte[MAX_FRAME_COM];
            
        sendData[0] = (byte)frameStart;
        sendData[1] = (byte)command;
        sendData[2] = (byte)addressLSB;
        sendData[3] = (byte)addressMSB;
        sendData[4] = (byte)data1LSB;
        sendData[5] = (byte)data1MSB;
        sendData[6] = (byte)data2;
        sendData[7] = (byte)cs;
        sendData[8] = (byte)frameEnd;

        return sendData;
    }
}
