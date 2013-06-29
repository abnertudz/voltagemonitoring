/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.core;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abner
 */
public abstract class FactoryCore {

    protected static String className;
    protected static String classNamePostFix;
    protected static String rootDir = System.getProperty("user.dir") + File.pathSeparator + "src" + File.pathSeparator + "datacollector";
    protected static String module;
    protected static String type;
    protected static String absolutePath;
    protected static String realClassName;
    protected static String packageName = "datacollector";
    protected static String packagePath;

    public FactoryCore()
    {
        className = null;
        module = null;
        type = null;        
        absolutePath = null;
        realClassName = null;
        classNamePostFix = null;
        packagePath = null;
        
    }

    protected static void errorLog(Exception e)
    {
         String msg = className + " class doesn't exist in " + rootDir + module;
         Logger.getLogger(FactoryCore.class.getName()).log(Level.SEVERE, msg, e);
    }

    protected static String getAbsolutePath()
    {
        if(type.isEmpty())
        {
            absolutePath = rootDir + File.pathSeparator + module + File.pathSeparator + FactoryCore.getRealClassName();
        }
        else
        {
            absolutePath = rootDir + File.pathSeparator + module + File.pathSeparator + type + File.pathSeparator + FactoryCore.getRealClassName();
        }
        
        return absolutePath;
    }

    protected static String getRealClassName()
    {
        realClassName = className + classNamePostFix;
        return realClassName;
    }

    protected static String getPackagePath()
    {
        if(type.isEmpty())
        {
            packagePath = packageName + "." + module + "." + FactoryCore.getRealClassName();
        }
        else
        {
            packagePath = packageName + "." + module + "." + type + "." + FactoryCore.getRealClassName();
        }

        return packagePath;
    }
}
