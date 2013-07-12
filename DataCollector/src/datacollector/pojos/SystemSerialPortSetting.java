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
public class SystemSerialPortSetting implements Serializable{

    private int id;
    private Date dateCreated;
    private Date lastModified;
    private String com;
    private int baudRateId;
    private int parityBitId;
    private int dataBitId;
    private int stopBitId;
    private int bufferSizeId;

    public SystemSerialPortSetting()
    {
        id = 0;
        dateCreated = new Date();
        lastModified = new Date();
        com = null;
        baudRateId = 0;
        parityBitId = 0;
        dataBitId = 0;
        stopBitId = 0;
        bufferSizeId = 0;
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
     * @return the com
     */
    public String getCom() {
        return com;
    }

    /**
     * @param com the com to set
     */
    public void setCom(String com) {
        this.com = com;
    }

    /**
     * @return the baudRateId
     */
    public int getBaudRateId() {
        return baudRateId;
    }

    /**
     * @param baudRateId the baudRateId to set
     */
    public void setBaudRateId(int baudRateId) {
        this.baudRateId = baudRateId;
    }

    /**
     * @return the parityBitId
     */
    public int getParityBitId() {
        return parityBitId;
    }

    /**
     * @param parityBitId the parityBitId to set
     */
    public void setParityBitId(int parityBitId) {
        this.parityBitId = parityBitId;
    }

    /**
     * @return the dataBitId
     */
    public int getDataBitId() {
        return dataBitId;
    }

    /**
     * @param dataBitId the dataBitId to set
     */
    public void setDataBitId(int dataBitId) {
        this.dataBitId = dataBitId;
    }

    /**
     * @return the stopBitId
     */
    public int getStopBitId() {
        return stopBitId;
    }

    /**
     * @param stopBitId the stopBitId to set
     */
    public void setStopBitId(int stopBitId) {
        this.stopBitId = stopBitId;
    }

    /**
     * @return the bufferSizeId
     */
    public int getBufferSizeId() {
        return bufferSizeId;
    }

    /**
     * @param bufferSizeId the bufferSizeId to set
     */
    public void setBufferSizeId(int bufferSizeId) {
        this.bufferSizeId = bufferSizeId;
    }


}
