/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vms.dao.interfaces;

import vms.com.data.ComConnectData;
import vms.pojos.*;

/**
 *
 * @author Abner
 */
public interface SystemSerialPortSettingInterface {

    public int addSerialPortSetting(String comPort, Integer baudRateId, Integer parityBitId, Integer dataBitId, Integer stopBitId, Integer bufferSizeId);

    public int addSerialPortSetting(ComConnectData com);

    public ComConnectData getSerialPortSetting(String comPort);

    public boolean deleteSerialPortSetting(String comPort);

    public boolean updateSerialPortSetting(ComConnectData com);

    public boolean updateSerialPortSetting(String comPort, Integer baudRateId, Integer parityBitId, Integer dataBitId, Integer stopBitId, Integer bufferSizeId);

    public SerialPortBaudRate getSerialPortBaudRate(Integer value);

    public SerialPortBufferSize getSerialPortBufferSize(Integer value);

    public SerialPortDataBit getSerialPortDataBit(Integer value);

    public SerialPortParityBit getSerialPortParityBit(String value);

    public SerialPortStopBit getSerialPortStopBit(Double value);

}
