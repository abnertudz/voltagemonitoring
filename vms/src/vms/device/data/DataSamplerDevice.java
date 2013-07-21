/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.device.data;

import vms.dao.implementation.DeviceDaoImplementation;
import vms.factories.DaoFactory;
import vms.pojos.DeviceStatus;

/**
 *
 * @author Abner
 */
public class DataSamplerDevice {

    private int deviceAddress;
    private int deviceBlockId;
    private String deviceBlockName;
    private double minVoltage;
    private double maxVoltage;
    private double voltage;
    private double current;
    private int deviceStatus;
    private String deviceStatusValue;
    private String deviceName;

    public DataSamplerDevice()
    {
        deviceAddress = 0;
        deviceBlockId = 0;
        minVoltage = 0;
        maxVoltage = 0;
        current = 0;
        deviceStatus = 0;
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
        return deviceBlockId;
    }

    public void setDeviceBlock(int data)
    {
        deviceBlockId = data;
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

    public void setDeviceStatus(int data)
    {
        deviceStatus = data;
    }

    public int getDeviceStatus()
    {
        return deviceStatus;
    }

    /**
     * @return the current
     */
    public double getDeviceCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setDeviceCurrent(double current) {
        this.current = current;
    }

    public String getDeviceStatusValue(int statusId)
    {
        DeviceDaoImplementation deviceDao = (DeviceDaoImplementation) DaoFactory.getInstance("Device");
        DeviceStatus status = deviceDao.getDeviceStatusById(statusId);        

        return status.getValue();
    }

    /**
     * @param deviceStatusValue the deviceStatusValue to set
     */
    public void setDeviceStatusValue(String deviceStatusValue) {
        this.deviceStatusValue = deviceStatusValue;
    }

    /**
     * @return the deviceStatusValue
     */
    public String getDeviceStatusValue() {
        return deviceStatusValue;
    }

    /**
     * @return the deviceBlockName
     */
    public String getDeviceBlockName() {
        return deviceBlockName;
    }

    /**
     * @param deviceBlockName the deviceBlockName to set
     */
    public void setDeviceBlockName(String deviceBlockName) {
        this.deviceBlockName = deviceBlockName;
    }

}
