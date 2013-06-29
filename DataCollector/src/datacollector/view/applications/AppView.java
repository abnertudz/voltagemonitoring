/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.view.applications;
import datacollector.core.ViewCore;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abner
 */
public class AppView extends ViewCore{

    // Variables that holds the instance of this object
    private static final AppView application = new AppView();
    
    public AppView()
    {
        super();
    }

    /** This method will be used to get the Instance of this object
     *  There should only be one instance.
     */
    public static AppView getInstance()
    {
       return application;
    }
    
     /**
     *  This method is used to launch the application
     */
    public void launch()
    {
        try
        {
            UIManager ui = new UIManager();
            ui.setLookAndFeel(ui.getSystemLookAndFeelClassName());
        }
        catch(Exception e)
        {
           //@todo this should be implemented in a logger;
            System.out.println("Exception: " );
        }

        this.setVisible(true);
    }

    public JButton getConnectActionButton()
    {
        return connectAction;
    }

    public JTable getDataTable()
    {
        return dataTable;
    }

    public void populateDataTable(ArrayList<Object[][]> data)
    {
        DefaultTableModel tableModel = (DefaultTableModel)dataTable.getModel();
        Iterator iterate = data.iterator();
        int countX=0;

        while(iterate.hasNext())
        {
            Object[][] obj = (Object[][]) iterate.next();          
            Object[] newRow = {false,obj[countX][0],obj[countX][1],obj[countX][2],obj[countX][3],"0","0","No",""};            
            tableModel.addRow(newRow); 
            countX++;
        }
    }
}
