package com.example.kei_data;

import java.util.Date;

public class Device {

    private String deviceType;
    //private int deviceId;
    private String deviceIp;
    private Date dateAdded;
    private String deviceName;
    private boolean deviceAdded;
    private boolean deviceRemoved;

    public Device(String type, String ip, String name){
        deviceType = type;
        deviceIp = ip;
        dateAdded = new Date();
        deviceName = name;
        this.deviceAdded = true;
        this.deviceRemoved = false;
    }

}
