/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.dao.implementation;

import vms.core.DaoCore;
import vms.dao.interfaces.SystemPreferenceInterface;
import vms.pojos.SystemPreference;
import vms.pojos.SystemPreferenceGroup;
import vms.pojos.SystemPreferenceValue;
import vms.preference.data.SystemPreferenceData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Abner
 */
public class SystemPreferenceDaoImplementation extends DaoCore implements SystemPreferenceInterface{

    public SystemPreferenceDaoImplementation()
    {
        super();
    }

    public int saveUserPreference(String name, Integer groupdId, String value) {
        
        int preferenceId = 0;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            SystemPreference preference = new SystemPreference();
            preference.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            preference.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            preference.setGroupId(groupdId);
            preference.setName(name);
            preferenceId = (Integer) session.save(preference);

            SystemPreferenceValue preferenceValue = new SystemPreferenceValue();
            preferenceValue.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            preferenceValue.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            preferenceValue.setValue(value);
            preferenceValue.setPreferenceId(preferenceId);
            session.save(preferenceValue);

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

        return preferenceId;
    }

    public int saveUserPreference(SystemPreferenceData preferenceData) {
        
        int preferenceId = 0;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            SystemPreference preference = new SystemPreference();
            preference.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            preference.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            preference.setGroupId(preferenceData.getPreferenceGroupId());
            preference.setName(preferenceData.getPreferenceName());
            preferenceId = (Integer) session.save(preference);

            SystemPreferenceValue preferenceValue = new SystemPreferenceValue();
            preferenceValue.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            preferenceValue.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            preferenceValue.setValue(preferenceData.getPreferenceValue());
            preferenceValue.setPreferenceId(preferenceId);
            session.save(preferenceValue);

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

        return preferenceId;
    }

    public ArrayList<Integer> saveUserPreferences(ArrayList<SystemPreferenceData> preferenceData) {
        
        ArrayList<Integer> preferenceIds = new ArrayList<Integer>();
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            Iterator iterator = preferenceData.iterator();

            while(iterator.hasNext())
            {
                SystemPreferenceData  fPreference = (SystemPreferenceData) iterator.next();
                
                SystemPreference preference = new SystemPreference();
                preference.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                preference.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                preference.setGroupId(fPreference.getPreferenceGroupId());
                preference.setName(fPreference.getPreferenceName());
                int preferenceId = (Integer) session.save(preference);
                preferenceIds.add(preferenceId);

                SystemPreferenceValue preferenceValue = new SystemPreferenceValue();
                preferenceValue.setDateCreated(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                preferenceValue.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                preferenceValue.setValue(fPreference.getPreferenceValue());
                preferenceValue.setPreferenceId(preferenceId);
                session.save(preferenceValue);
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

        return preferenceIds;
    }

    public boolean updateUserPreferenceValue(SystemPreferenceData preferenceData) {
        
        boolean result = true;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            List<SystemPreference> preferences = (List<SystemPreference>) session.createCriteria(SystemPreference.class).add(Restrictions.eq("name", preferenceData.getPreferenceName()));

            for(SystemPreference preference : preferences)
            {
                List<SystemPreferenceValue> preferenceValues = (List<SystemPreferenceValue>) session.createCriteria(SystemPreferenceValue.class).add(Restrictions.eq("preferenceId", new Integer(preference.getId())));

                for(SystemPreferenceValue value: preferenceValues)
                {
                    value.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                    value.setValue(preferenceData.getPreferenceValue());
                    session.update(value);
                }
            }

            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();
            result = false;

        }
        finally{
             session.close();
        }

        return result;
    }

    public boolean updateUserPreferenceValues(ArrayList<SystemPreferenceData> preferenceData) {
        
        boolean result = true;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            Iterator iterator = preferenceData.iterator();
            while(iterator.hasNext())
            {
                SystemPreferenceData fPreference = (SystemPreferenceData) iterator.next();

                List<SystemPreference> preferences = (List<SystemPreference>) session.createCriteria(SystemPreference.class).add(Restrictions.eq("name", fPreference.getPreferenceName()));

                for(SystemPreference preference : preferences)
                {
                    List<SystemPreferenceValue> preferenceValues = (List<SystemPreferenceValue>) session.createCriteria(SystemPreferenceValue.class).add(Restrictions.eq("preferenceId", new Integer(preference.getId())));

                    for(SystemPreferenceValue value: preferenceValues)
                    {
                        value.setLastModified(dateLib.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                        value.setValue(fPreference.getPreferenceValue());
                        session.update(value);
                    }
                }
            }

            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();
            result = false;

        }
        finally{
             session.close();
        }

        return result;
    }

    public boolean deleteUserPreferenceValue(SystemPreferenceData preferenceData) {
        
        boolean result = true;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();

            List<SystemPreference> preferences = (List<SystemPreference>) session.createCriteria(SystemPreference.class).add(Restrictions.eq("name", preferenceData.getPreferenceName()));

            for(SystemPreference preference : preferences)
            {
                List<SystemPreferenceValue> preferenceValues = (List<SystemPreferenceValue>) session.createCriteria(SystemPreferenceValue.class).add(Restrictions.eq("preferenceId", new Integer(preference.getId())));

                for(SystemPreferenceValue value: preferenceValues)
                {
                    session.delete(value);
                }
                session.delete(preference);
            }

            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();
            result = false;

        }
        finally{
             session.close();
        }

        return result;
    }

    public boolean deleteUserPreferenceValues(ArrayList<SystemPreferenceData> preferenceData) {
        
        boolean result = true;
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            Iterator iterator = preferenceData.iterator();
            while(iterator.hasNext())
            {
                SystemPreferenceData fPreference = (SystemPreferenceData) iterator.next();

                List<SystemPreference> preferences = (List<SystemPreference>) session.createCriteria(SystemPreference.class).add(Restrictions.eq("name", fPreference.getPreferenceName()));

                for(SystemPreference preference : preferences)
                {
                    List<SystemPreferenceValue> preferenceValues = (List<SystemPreferenceValue>) session.createCriteria(SystemPreferenceValue.class).add(Restrictions.eq("preferenceId", new Integer(preference.getId())));

                    for(SystemPreferenceValue value: preferenceValues)
                    {                        
                        session.delete(value);
                    }
                    session.delete(preference);
                }
            }

            trans.commit();
        }
        catch (HibernateException e) {
            if (trans!=null)
                trans.rollback();
            e.printStackTrace();
            result = false;

        }
        finally{
             session.close();
        }

        return result;
    }

    public SystemPreferenceGroup getSystemPreferenceGroup(String name) {

        SystemPreferenceGroup prefGroup = new SystemPreferenceGroup();
        Session session = factory.openSession();
        Transaction trans = null;

        try
        {
            trans = (Transaction) session.beginTransaction();
            List<SystemPreferenceGroup> groups = (List<SystemPreferenceGroup>) session.createCriteria(SystemPreferenceGroup.class).add(Restrictions.eq("name", name));

            for(SystemPreferenceGroup group : groups)
            {
                prefGroup = group;
                break;
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

        return prefGroup;
    }

}
