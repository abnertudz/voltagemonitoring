/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.pojos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Abner
 */
public class DeviceSetting implements Serializable{

    private int id;
    private Date dateCreated;
    private Date lastModified;
    private double deviceMaxValue;
    private double deviceMinValue;
    private int deviceId;

    public DeviceSetting()
    {
        id = 0;
        dateCreated = new Date();
        lastModified = new Date();
        deviceMaxValue = 0;
        deviceMinValue = 0;
        deviceId = 0;
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
     * @return the deviceMaxValue
     */
    public double getDeviceMaxValue() {
        return deviceMaxValue;
    }

    /**
     * @param deviceMaxValue the deviceMaxValue to set
     */
    public void setDeviceMaxValue(double deviceMaxValue) {
        this.deviceMaxValue = deviceMaxValue;
    }

    /**
     * @return the deviceMinValue
     */
    public double getDeviceMinValue() {
        return deviceMinValue;
    }

    /**
     * @param deviceMinValue the deviceMinValue to set
     */
    public void setDeviceMinValue(double deviceMinValue) {
        this.deviceMinValue = deviceMinValue;
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

   

}
