/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.listeners.dialogs;
import datacollector.constants.StringConstants;
import datacollector.core.SetupWizardCore;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author Abner
 */
public class SetupWizardListener implements ActionListener{

    // Variable that holds the instance of this object
    private static final SetupWizardListener app = new SetupWizardListener();

    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        SetupWizardCore wizard = SetupWizardCore.getInstance();
   
        if(actionCommand.equals(StringConstants.SETUP_WIZARD))
        {            
            wizard.resetWizard();
            wizard.launchWizard();
        }
        else if(actionCommand.equals(StringConstants.NEXT_WIZARD))
        {
            
            wizard.next();
        }
        else if(actionCommand.equals(StringConstants.FINISH_WIZARD))
        {

        }
        else if(actionCommand.equals(StringConstants.CANCEL_WIZARD))
        {
            wizard.cancelWizard();
        }
        else if(actionCommand.equals(StringConstants.PREVIOUS_WIZARD))
        {
            wizard.previous();
        }
        else if(actionCommand.equals(StringConstants.HELP_WIZARD))
        {
            
        }
    }

    public static SetupWizardListener getInstance()
    {
        return app;
    }

}
