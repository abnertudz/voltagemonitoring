/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.device.data;

/**
 *
 * @author Abner
 */
public class DataSamplerDevice {

    private int deviceAddress;
    private String deviceBlock;
    private double minVoltage;
    private double maxVoltage;
    private double voltage;

    public DataSamplerDevice()
    {
        deviceAddress = 0;
        deviceBlock = null;
        minVoltage = 0;
        maxVoltage = 0;
    }

    public int getDeviceAddress()
    {
        return deviceAddress;
    }

    public void setDeviceAddress(int data)
    {
        deviceAddress = data;
    }

    public String getDeviceBlock()
    {
        return deviceBlock;
    }

    public void setDeviceBlock(String data)
    {
        deviceBlock = data;
    }

    public double getDeviceMinVoltage()
    {
        return minVoltage;
    }

    public void setDeviceMinVoltage(double data)
    {
        minVoltage = data;
    }

    public double getDeviceMaxVoltage()
    {
        return maxVoltage;
    }

    public void setDeviceMaxVoltage(double data)
    {
        maxVoltage = data;
    }

    public double getDeviceVoltage()
    {
        return voltage;
    }

    public void setDeviceVoltage(double data)
    {
        voltage = data;
    }

}
