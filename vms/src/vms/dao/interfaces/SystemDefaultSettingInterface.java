/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.dao.interfaces;

import vms.pojos.SystemDefaultSettingValue;

/**
 *
 * @author Abner
 */
public interface SystemDefaultSettingInterface {

    public SystemDefaultSettingValue getSystemDefaultSettingValue(String name);

    public SystemDefaultSettingValue getSystemDefaultSettingValue(String name, Integer group);

}
