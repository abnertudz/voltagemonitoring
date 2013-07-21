/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.wizard.data;

import vms.com.data.ComConnectData;
import vms.device.data.DataSamplerDevice;
import java.util.ArrayList;

/**
 *
 * @author Abner
 */
public class SetupWizardData {

    private String computerName;
    private String macAddress;
    private ArrayList<ComConnectData> comData;
    private ArrayList<DataSamplerDevice> deviceSettings;
    private String readInterval;
    private String deviceSleepTime;

    public SetupWizardData()
    {
        computerName = null;
        macAddress = null;
        comData = new ArrayList<ComConnectData>();
        deviceSettings = new ArrayList<DataSamplerDevice>();
        readInterval = null;
        deviceSleepTime = null;
    }

    public void setComputerName(String data)
    {
        computerName = data;
    }

    public String getComputerName()
    {
        return computerName;
    }

    public void setMacAddress(String data)
    {
        macAddress = data;
    }

    public String getMacAddress()
    {
        return macAddress;
    }

    public void setReadInterval(String data)
    {
        readInterval = data;
    }

    public String getReadInterval()
    {
        return readInterval;
    }

    public void setDeviceSleepTime(String data)
    {
        deviceSleepTime = data;
    }

    public String getDeviceSleepTime()
    {
        return deviceSleepTime;
    }

    public void setComData(ArrayList<ComConnectData> data)
    {
        comData = data;
    }

    public ArrayList<ComConnectData> getComData()
    {
        return comData;
    }

    public void setDeviceSettings(ArrayList<DataSamplerDevice> data)
    {
        deviceSettings = data;
    }

    public ArrayList<DataSamplerDevice> getDeviceSettings()
    {
        return deviceSettings;
    }

}
