package com.example.kei_data;

import java.util.Date;

public class Device {

    public String deviceType;
    //private int deviceId;
    public String deviceIp;
    public Date dateAdded;
    public String deviceName;
    public boolean deviceAdded;
    public boolean deviceRemoved;

    public Device(String type, String ip, String name){
        deviceType = type;
        deviceIp = ip;
        dateAdded = new Date();
        deviceName = name;
        this.deviceAdded = true;
        this.deviceRemoved = false;
    }

}
