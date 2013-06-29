/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.com.data;

/**
 *
 * @author Abner
 */
public class ComConnectData {

    private String comPort;
    private int baudRate;
    private int parityBits;
    private int dataBits;
    private int stopBits;
    private int bufferSize;

    public ComConnectData()
    {
        initVariables();
    }

    public void initVariables()
    {
        comPort = null;
        baudRate = 0;
        parityBits = 0;
        dataBits = 0;
        stopBits = 0;
        bufferSize = 0;
    }

    public String getComPort()
    {
        return comPort;
    }

    public void setComPort(String data)
    {
       comPort = data;
    }

    public int getBaudRate()
    {
        return baudRate;
    }

    public void setBaudRate(int data)
    {
       baudRate = data;
    }

    public int getParityBits()
    {
        return parityBits;
    }

    public void setParityBits(int data)
    {
       parityBits = data;
    }

    public int getDataBits()
    {
        return dataBits;
    }

    public void setDataBits(int data)
    {
       dataBits = data;
    }

    public int getStopBits()
    {
        return stopBits;
    }

    public void setStopBits(int data)
    {
       stopBits = data;
    }

    public int getBufferSize()
    {
        return bufferSize;
    }

    public void setBufferSize(int data)
    {
       bufferSize = data;
    }

    public void setupData(String comPort, int baudRate, int parityBits, int dataBits, int stopBits, int bufferSize)
    {
        this.setComPort(comPort);
        this.setBaudRate(baudRate);
        this.setParityBits(parityBits);
        this.setDataBits(dataBits);
        this.setStopBits(stopBits);
        this.setBufferSize(bufferSize);
    }

    public void clearData()
    {
       initVariables();
    }
}
