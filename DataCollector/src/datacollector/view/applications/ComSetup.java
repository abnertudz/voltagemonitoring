/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.view.applications;

import datacollector.view.dialogs.ComSettingDialog;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 *
 * @author Abner
 */
public class ComSetup extends ComSettingDialog implements WindowListener{
   
    // Variable that holds the instance of this object
    private static final ComSetup dialog = new ComSetup(new JFrame(), true);
   
    public ComSetup(JFrame frame, boolean modal)
    {
        super(frame, modal);        
    }

    /**
     *  Singleton, method that get the instance of this object
     * @return
     */
    public static ComSetup getInstance()
    {
        return dialog;
    }

    public void launch()
    {
        this.setVisible(true);
    }
     /** Gets the comPort attribute object
     *
     * @return comPort JComboBox object
     */
    public JComboBox getComPortCBox()
    {
        return this.comPort;
    }

    /** Gets the comBaudRate attribute object
     *
     * @return comBaudRate JComboBox object
     */
    public JComboBox getComBaudRateCBox()
    {
        return this.comBaudRate;
    }

     /** Gets the comBufferSize attribute object
     *
     * @return comBufferSize JComboBox object
     */
    public JComboBox getComBufferSizeCBox()
    {
        return this.comBufferSize;
    }

     /** Gets the comDataBit attribute object
     *
     * @return comDataBit JComboBox object
     */
    public JComboBox getComDataBitCBox()
    {
        return this.comDataBit;
    }

    /** Gets the comParityBit attribute object
     *
     * @return comParityBit JComboBox object
     */
    public JComboBox getComParityBitCBox()
    {
        return this.comParityBit;
    }

    /** Gets the comOkButton attribute object
     *
     * @return comOkButton JButton object
     */
    public JButton getOkButton()
    {
        return this.comOkButton;
    }

    /** Gets the comCancelButton attribute object
     *
     * @return comCancelButton JButton object
     */
    public JButton getCancelButton()
    {
        return this.comCancelButton;
    }

    /** Gets the comStopBit attribute object
     *
     * @return comStopBit JComboBox object
     */
    public JComboBox getComStopBitCBox()
    {
        return comStopBit;
    }

    public void windowClosing(WindowEvent e) {
        
        dialog.setVisible(false);
    }

    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
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
