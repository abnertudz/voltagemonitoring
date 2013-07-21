/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.com.threads;

import vms.globals.GlobalVariables;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abner
 */
public class IntervalCounterThread extends Thread{

    private int interval = 10;

    @Override
    public void run() {

        while(true)
        {
            if(GlobalVariables.START_INTERVAL_COUNTER && !interrupted())
            {
                int currentTimeInSec = getCurrentTimeInSeconds();
                if(0 == (currentTimeInSec % interval))
                {
                   if(!GlobalVariables.INTERVAL_TRIGGERED)
                   {
                        GlobalVariables.INTERVAL_TRIGGERED = true;                        
                   }
                }
                else
                {
                   GlobalVariables.INTERVAL_TRIGGERED = false;
                }
            }
            try {
                Thread.sleep(800);
            } catch (InterruptedException ex) {
                Logger.getLogger(IntervalCounterThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public int getCurrentTimeInSeconds()
    {
        Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time[] = (sdf.format(cal.getTime())).split(":");
    	int hour=Integer.parseInt(time[0]);
        int minute=Integer.parseInt(time[1]);
        int second=Integer.parseInt(time[2]);

        return second + (60 * minute) + (3600 * hour);
    }
    
    public void setInterval(int count)
    {
        interval = count;
    }
}
