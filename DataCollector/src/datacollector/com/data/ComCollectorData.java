/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datacollector.com.data;

import datacollector.core.ComDataCore;

/**
 *
 * @author Abner
 */
public class ComCollectorData extends ComDataCore{

    private int addressLSB;
    private int addressMSB;

    public ComCollectorData()
    {
        super();
        addressLSB = 0;
        addressMSB = 0;
    }

    public int getAddress()
    {
        int address = addressMSB << 8;
        return (addressMSB & addressLSB);
    }
    
    public int getAddressLSB()
    {
        return addressLSB;
    }

    public void setAddressLSB(int data)
    {
        addressLSB = data;
    }

     public int getAddressMSB()
    {
        return addressMSB;
    }

    public void setAddressMSB(int data)
    {
        addressMSB = data;
    }

}
