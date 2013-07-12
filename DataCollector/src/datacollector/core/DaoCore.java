/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.core;

import datacollector.factories.LibraryFactory;
import datacollector.libraries.DateTimeLibrary;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author Abner
 */
public class DaoCore {

    protected SessionFactory factory;
    protected ServiceRegistry serviceRegistry;
    protected DateTimeLibrary dateLib;

    public DaoCore()
    {
        dateLib = (DateTimeLibrary) LibraryFactory.getInstance("DateTime");
        try 
        {
             Configuration conf = new Configuration();
             conf.configure();
             serviceRegistry = (ServiceRegistry) new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
             factory = conf.buildSessionFactory(serviceRegistry);
        }catch (Throwable ex)
        {
            System.err.println("Failed to create sessionFactory object." + ex);
            //throw new ExceptionInInitializerError(ex);
        }
    }

}
