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
public class DeviceLog implements Serializable{

    private int id;
    private Date dateCreated;
    private Date lastModified;
    private Date started;
    private Date stopped;
    private int deviceId;

    public DeviceLog()
    {
        id = 0;
        dateCreated = new Date();
        lastModified = new Date();
        started = new Date();
        stopped = new Date();
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
     * @return the started
     */
    public Date getStarted() {
        return started;
    }

    /**
     * @param started the started to set
     */
    public void setStarted(Date started) {
        this.started = started;
    }

    /**
     * @return the stopped
     */
    public Date getStopped() {
        return stopped;
    }

    /**
     * @param stopped the stopped to set
     */
    public void setStopped(Date stopped) {
        this.stopped = stopped;
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
