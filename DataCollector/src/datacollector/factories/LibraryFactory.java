/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.factories;

import datacollector.libraries.*;
import datacollector.core.FactoryCore;
import java.io.File;

/**
 *
 * @author Abner
 */
public class LibraryFactory extends FactoryCore{

    private static final LibraryFactory self = new LibraryFactory();
    
    private static File classFile;
    private static String classPostFix;

    public LibraryFactory()
    {
        super();
        LibraryFactory.module = "libraries";
        classFile = null;
        classPostFix = "Library";
    }

    public static LibraryFactory newInstance(String className)
    {
        classFile = new File(DialogFactory.rootDir + LibraryFactory.module + File.pathSeparator + className + classPostFix);
        if(!classFile.exists())
        {
            LibraryFactory.errorLog(new Exception());
            return null;
        }

        return new LibraryFactory();
    }

    public static LibraryFactory newInstance(String className, String modules)
    {
        return new LibraryFactory();
    }

    public static LibraryFactory getInstance()
    {
        return self;
    }

}
