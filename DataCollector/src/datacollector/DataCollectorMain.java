/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector;
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
               AppView sysView = new AppView();
               sysView.launch();
            }
        });
        
    }

}
