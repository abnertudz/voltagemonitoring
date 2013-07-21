/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.dao.interfaces;

import vms.pojos.SystemPreferenceGroup;
import vms.preference.data.SystemPreferenceData;
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
