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
public class SystemPreferenceValue implements Serializable{

    private int id;
    private Date dateCreated;
    private Date lastModified;
    private String value;
    private int preferenceId;

    public SystemPreferenceValue()
    {
        id = 0;
        dateCreated = new Date();
        lastModified = new Date();
        value = null;
        preferenceId = 0;
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
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the preferenceId
     */
    public int getPreferenceId() {
        return preferenceId;
    }

    /**
     * @param preferenceId the preferenceId to set
     */
    public void setPreferenceId(int preferenceId) {
        this.preferenceId = preferenceId;
    }

}
