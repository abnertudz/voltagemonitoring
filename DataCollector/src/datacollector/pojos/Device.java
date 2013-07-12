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
public class Device implements Serializable{

    private int id;
    private String deviceName;
    private Date dateCreated;
    private Date lastModified;
    private int  deviceAddress;
    private int deviceBlock;
        
    public Device()
    {
        id = 0;
        deviceName = null;
        dateCreated = new Date();
        lastModified = new Date();
        deviceAddress = 0;
        deviceBlock = 0;

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
     * @return the deviceName
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * @param deviceName the deviceName to set
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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
     * @return the deviceAddress
     */
    public int getDeviceAddress() {
        return deviceAddress;
    }

    /**
     * @param deviceAddress the deviceAddress to set
     */
    public void setDeviceAddress(int deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    /**
     * @return the deviceBlock
     */
    public int getDeviceBlock() {
        return deviceBlock;
    }

    /**
     * @param deviceBlock the deviceBlock to set
     */
    public void setDeviceBlock(int deviceBlock) {
        this.deviceBlock = deviceBlock;
    }

}
