/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.factories;

import datacollector.core.FactoryCore;
import datacollector.view.dialogs.*;
import java.io.File;

/**
 *
 * @author Abner
 */
public class DialogFactory extends FactoryCore{

    private static final DialogFactory self = new DialogFactory();
    private static File classFile;

    public DialogFactory()
    {
        super();        
        DialogFactory.module = "view";
        DialogFactory.type = "dialogs";
        DialogFactory.classNamePostFix="Dialog";
        classFile = null;
    }

    public static Object newInstance(String className)
    {
        Object thisClass = null;
        
        classFile = new File(DialogFactory.rootDir + DialogFactory.module + File.pathSeparator + className);
        if(!classFile.exists())
        {
            DialogFactory.errorLog(new Exception());
            return null;
        }
        DialogFactory.className = className;

        try
        {
            thisClass = Class.forName(DialogFactory.getPackagePath());
        }
        catch (ClassNotFoundException ex)
        {
            DialogFactory.errorLog(ex);
        }

        return thisClass;
    }

    public static Object newInstance(String className, String modules)
    {
        Object thisClass = null;

        classFile = new File(DialogFactory.rootDir + DialogFactory.module + File.pathSeparator + className);
        if(!classFile.exists())
        {
            DialogFactory.errorLog(new Exception());
            return null;
        }
        DialogFactory.className = className;

        try
        {
            thisClass = Class.forName(DialogFactory.getPackagePath());
        }
        catch (ClassNotFoundException ex)
        {
            DialogFactory.errorLog(ex);
        }

        return thisClass;
    }

    public static Object getInstance()
    {
        Object thisClass = null;

        classFile = new File(DialogFactory.rootDir + DialogFactory.module + File.pathSeparator + className);
        if(!classFile.exists())
        {
            DialogFactory.errorLog(new Exception());
            return null;
        }
        DialogFactory.className = className;

        try
        {
            thisClass = Class.forName(DialogFactory.getPackagePath());
        }
        catch (ClassNotFoundException ex)
        {
            DialogFactory.errorLog(ex);
        }

        return thisClass;       
    }

}
