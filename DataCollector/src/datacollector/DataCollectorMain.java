/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector;
import datacollector.dao.implementation.DeviceDaoImplementation;
import datacollector.factories.DaoFactory;
import datacollector.view.applications.*;

/**
 *
 * @author Abner
 */
public class DataCollectorMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // run
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

              //DeviceDaoImplementation device = (DeviceDaoImplementation) DaoFactory.getInstance("Device");
             // device.addDevice(1, 1, "Example",new Double(1.1),new Double(1.2));
               
               AppView sysView = new AppView();
               sysView.launch();
            }
        });
        
    }

}
