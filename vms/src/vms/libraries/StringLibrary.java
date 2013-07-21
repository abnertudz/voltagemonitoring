/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.libraries;

/**
 *
 * @author Abner
 */
public class StringLibrary {

    public StringLibrary()
    {
    }

    /**
     *  Converts first letter of the string to upper case
     * @return String
     */
    public String ucfirst(String str)
    {
        String toReturn = "";

        if(str.isEmpty())
        {
            return toReturn;
        }

        for(int i=0; i<str.length();i++)
        {
            if(0 == i)
            {
                toReturn += str.charAt(i);
                toReturn = toReturn.toUpperCase();
            }
            else
            {
                toReturn += str.charAt(i);
            }
            
        }
        
        return toReturn;
    }

}
