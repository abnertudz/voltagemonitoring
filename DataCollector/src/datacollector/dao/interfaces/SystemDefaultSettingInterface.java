/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.dao.interfaces;

import datacollector.pojos.SystemDefaultSettingValue;

/**
 *
 * @author Abner
 */
public interface SystemDefaultSettingInterface {

    public SystemDefaultSettingValue getSystemDefaultSettingValue(String name);

    public SystemDefaultSettingValue getSystemDefaultSettingValue(String name, Integer group);

}
