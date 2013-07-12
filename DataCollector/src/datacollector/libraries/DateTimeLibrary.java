/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.libraries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abner
 */
public class DateTimeLibrary {

    public Date getCurrentDate(String format)
    {
        Date date = null;
        DateFormat dateFormat;
        if(format.isEmpty())
        {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        else
        {
             dateFormat = new SimpleDateFormat(format);
        }
        try {            
            date = dateFormat.parse(dateFormat.format(Calendar.getInstance().getTime()));
            System.out.println(date);
        } catch (ParseException ex) {
            Logger.getLogger(DateTimeLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }               
        return date;
    }

}
