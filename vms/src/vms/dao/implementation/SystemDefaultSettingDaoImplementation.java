/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.dao.implementation;

import vms.core.DaoCore;
import vms.dao.interfaces.SystemDefaultSettingInterface;
import vms.pojos.SystemDefaultSetting;
import vms.pojos.SystemDefaultSettingValue;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Abner
 */
public class SystemDefaultSettingDaoImplementation extends DaoCore implements SystemDefaultSettingInterface{

    public SystemDefaultSettingValue getSystemDefaultSettingValue(String name) {

        SystemDefaultSettingValue returnValue = new SystemDefaultSettingValue();
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            List<SystemDefaultSetting> defaults = (List<SystemDefaultSetting>) session.createCriteria(SystemDefaultSetting.class).add(Restrictions.eq("name", name));

            for(SystemDefaultSetting sysDefault : defaults)
            {
                List<SystemDefaultSettingValue> values = (List<SystemDefaultSettingValue>) session.createCriteria(SystemDefaultSettingValue.class).add(Restrictions.eq("defaultSettingId", new Integer(sysDefault.getId())));

                for(SystemDefaultSettingValue value: values)
                {
                    returnValue = value;
                    break;
                }
            }

            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();

        }
        finally{
             session.close();
        }

        return returnValue;
    }

    public SystemDefaultSettingValue getSystemDefaultSettingValue(String name, Integer group) {

        SystemDefaultSettingValue returnValue = new SystemDefaultSettingValue();
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            List<SystemDefaultSetting> defaults = (List<SystemDefaultSetting>) session.createCriteria(SystemDefaultSetting.class).add(Restrictions.and(Restrictions.eq("name", name), Restrictions.eq("group", group)));

            for(SystemDefaultSetting sysDefault : defaults)
            {
                List<SystemDefaultSettingValue> values = (List<SystemDefaultSettingValue>) session.createCriteria(SystemDefaultSettingValue.class).add(Restrictions.eq("defaultSettingId", new Integer(sysDefault.getId())));

                for(SystemDefaultSettingValue value: values)
                {
                    returnValue = value;
                    break;
                }
            }

            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();

        }
        finally{
             session.close();
        }

        return returnValue;
    }

}
