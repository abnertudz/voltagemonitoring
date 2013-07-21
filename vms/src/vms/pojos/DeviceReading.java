/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.pojos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Abner
 */
public class DeviceReading implements Serializable{

    private int id;
    private Date dateCreated;
    private Date lastModified;
    private int deviceStatusId;
    private int deviceId;
    private double voltage;
    private double current;

    public DeviceReading()
    {
        id = 0;
        dateCreated = new Date();
        lastModified = new Date();
        deviceStatusId = 0;
        deviceId = 0;
        current = 0;
        voltage = 0;

    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the lastModified
     */
    public Date getLastModified() {
        return lastModified;
    }

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * @return the deviceStatusId
     */
    public int getDeviceStatusId() {
        return deviceStatusId;
    }

    /**
     * @param deviceStatusId the deviceStatusId to set
     */
    public void setDeviceStatusId(int deviceStatusId) {
        this.deviceStatusId = deviceStatusId;
    }

    /**
     * @return the deviceId
     */
    public int getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the voltage
     */
    public double getVoltage() {
        return voltage;
    }

    /**
     * @param voltage the voltage to set
     */
    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    /**
     * @return the current
     */
    public double getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(double current) {
        this.current = current;
    }

}
