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
    private int deviceBlock;
    private double minVoltage;
    private double maxVoltage;
    private double voltage;
    private String deviceStatus;
    private String deviceName;

    public DataSamplerDevice()
    {
        deviceAddress = 0;
        deviceBlock = 0;
        minVoltage = 0;
        maxVoltage = 0;
        deviceStatus = null;
        deviceName = null;
    }

    public int getDeviceAddress()
    {
        return deviceAddress;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String name) {
        deviceName = name;
    }

    public void setDeviceAddress(int data)
    {
        deviceAddress = data;
    }

    public int getDeviceBlock()
    {
        return deviceBlock;
    }

    public void setDeviceBlock(int data)
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

    public void setDeviceStatus(String data)
    {
        deviceStatus = data;
    }

    public String getDeviceStatus()
    {
        return deviceStatus;
    }

}
