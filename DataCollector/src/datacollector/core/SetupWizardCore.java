/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.core;

import datacollector.factories.DialogFactory;
import datacollector.view.dialogs.SetupWizardDialog;
import datacollector.view.dialogs.SetupWizardStep1;
import datacollector.view.dialogs.SetupWizardStep2;
import datacollector.view.dialogs.SetupWizardStep3;
import datacollector.view.dialogs.SetupWizardStep4;
import datacollector.wizard.data.SetupWizardData;
import java.awt.CardLayout;
import java.awt.Dimension;

/**
 *
 * @author Abner
 */
public class SetupWizardCore {

    private static final SetupWizardCore app = new SetupWizardCore();

    private int currentStep;
    private SetupWizardData wizardData;

    public SetupWizardCore()
    {
        initVariables();
    }

    public static SetupWizardCore getInstance()
    {
        return app;
    }
    
    public void initVariables()
    {
        currentStep = 1;
        wizardData = new SetupWizardData();
        
    }

    public void resetWizard()
    {
        initVariables();
    }

    public SetupWizardData getWizardData()
    {
        return wizardData;
    }

    public void setWizardData(SetupWizardData data)
    {
        wizardData = data;
    }

    public boolean next()
    {
        boolean result = true;

        if(true == validateStep())
        {
            currentStep++;
            SetupWizardDialog wizard = (SetupWizardDialog) DialogFactory.getInstance("SetupWizard");
            CardLayout wizardLayout = (CardLayout)wizard.getWizardPanel().getLayout();
            Dimension step2Dimension = wizard.getWizardStep2().getSize();
            wizard.setSize(step2Dimension);
            wizard.getWizardPanel().setSize(step2Dimension);
            wizardLayout.show(wizard.getWizardPanel(), String.valueOf(currentStep));
        }
        else
        {
            result = false;
        }

        return result;
    }

    public boolean previous()
    {
        boolean result = true;

        if(true == validateStep())
        {
            if(currentStep > 1)
            {
                currentStep--;
                //launchWizard(currentStep,false);
            }
        }
        else
        {
            result = false;
        }

        return result;
    }

    private boolean validateStep()
    {
        boolean result = true;

        switch(currentStep)
        {
        }

        return result;
    }

    public void launchWizard()
    {
        SetupWizardDialog wizard = (SetupWizardDialog) DialogFactory.getInstance("SetupWizard");
        wizard.launch();
    }

    public void cancelWizard()
    {        
        switch(currentStep)
        {
            case 1:
                SetupWizardStep1 step1 = SetupWizardStep1.getInstance();
                step1.close();
                break;
            case 2:
                SetupWizardStep2 step2 = SetupWizardStep2.getInstance();
                step2.close();
                break;
            case 3:
                SetupWizardStep3 step3 = SetupWizardStep3.getInstance();
                step3.close();
                break;
            case 4:
                SetupWizardStep4 step4 = SetupWizardStep4.getInstance();
                step4.close();
                break;
        }
    }
}
