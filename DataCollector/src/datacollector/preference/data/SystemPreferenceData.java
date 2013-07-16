/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.preference.data;

/**
 *
 * @author Abner
 */
public class SystemPreferenceData {

    private String preferenceName;
    private int preferenceGroupId;
    private String preferenceGroupName;
    private String preferenceValue;

    public SystemPreferenceData()
    {
        preferenceName = null;
        preferenceGroupId = 0;
        preferenceGroupName = null;
        preferenceValue = null;
    }

    /**
     * @return the preferenceName
     */
    public String getPreferenceName() {
        return preferenceName;
    }

    /**
     * @param preferenceName the preferenceName to set
     */
    public void setPreferenceName(String preferenceName) {
        this.preferenceName = preferenceName;
    }

    /**
     * @return the preferenceGroupId
     */
    public int getPreferenceGroupId() {
        return preferenceGroupId;
    }

    /**
     * @param preferenceGroupId the preferenceGroupId to set
     */
    public void setPreferenceGroupId(int preferenceGroupId) {
        this.preferenceGroupId = preferenceGroupId;
    }

    /**
     * @return the preferenceValue
     */
    public String getPreferenceValue() {
        return preferenceValue;
    }

    /**
     * @param preferenceValue the preferenceValue to set
     */
    public void setPreferenceValue(String preferenceValue) {
        this.preferenceValue = preferenceValue;
    }

    /**
     * @return the preferenceGroupName
     */
    public String getPreferenceGroupName() {
        return preferenceGroupName;
    }

    /**
     * @param preferenceGroupName the preferenceGroupName to set
     */
    public void setPreferenceGroupName(String preferenceGroupName) {
        this.preferenceGroupName = preferenceGroupName;
    }

}
