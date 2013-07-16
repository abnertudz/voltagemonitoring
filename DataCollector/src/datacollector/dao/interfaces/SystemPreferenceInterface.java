/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.dao.interfaces;

import datacollector.pojos.SystemPreferenceGroup;
import datacollector.preference.data.SystemPreferenceData;
import java.util.ArrayList;

/**
 *
 * @author Abner
 */
public interface SystemPreferenceInterface {

    public int saveUserPreference(String name, Integer groupdId, String value);

    public int saveUserPreference(SystemPreferenceData preference);

    public ArrayList<Integer> saveUserPreferences(ArrayList<SystemPreferenceData> preference);

    public boolean updateUserPreferenceValue(SystemPreferenceData preferenceData);

    public boolean updateUserPreferenceValues(ArrayList<SystemPreferenceData> preferenceData);

    public boolean deleteUserPreferenceValue(SystemPreferenceData preferenceData);

    public boolean deleteUserPreferenceValues(ArrayList<SystemPreferenceData> preferenceData);

    public SystemPreferenceGroup getSystemPreferenceGroup(String name);

}
