/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.core;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JDialog;
import vms.AppView;
import vms.AppViewCore;

/**
 *
 * @author Abner
 */
public class SystemDialogCore extends JDialog implements WindowListener{

    // Sets up dialog location
    protected AppViewCore app = AppView.getAppViewCore();
        
     /** Creates new form SystemDialogCore */
    public SystemDialogCore(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

    /**
     *  Initialize system properties
     */
    public void initSystemProperties()
    {
        // Sets up dialog location
        Dimension viewSize = app.getFrame().getSize();
        Dimension dilaogSize = this.getSize();
        this.setLocation((int)((viewSize.getWidth()/2)-(dilaogSize.getWidth()/2)),(int)((viewSize.getHeight()/2) - (dilaogSize.getHeight()/2)));
    }

    /**
     *  Initialize system listeners
     */
    public void initSystemListeners(){}
    
    public void launch()
    {
        this.setVisible(true);
    }

    public void close()
    {
        this.setVisible(false);
    }

    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void windowClosing(WindowEvent e) {
        close();
    }

    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
