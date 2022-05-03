package com.example.kei_data;

import java.util.Date;

public class DataUse {
    private String dataUsageID;
    private String dataUsageDeviceType;
    private String dataUsageTimeSlot;
    private String dataUsageAmount;
    private String dataUsageType;

    public DataUse(String ID, String DeviceType, String Time, String Amount, String Type){
        setDataUsageID(ID);
        setDataUsageDeviceType(DeviceType);
        setDataUsageTimeSlot(Time);
        setDataUsageAmount(Amount);
        setDataUsageType(Type);
    }

    public String getDataUsageID() {
        return dataUsageID;
    }

    public void setDataUsageID(String dataUsageID) {
        this.dataUsageID = dataUsageID;
    }

    public String getDataUsageDeviceType() {
        return dataUsageDeviceType;
    }

    public void setDataUsageDeviceType(String dataUsageDeviceType) {
        this.dataUsageDeviceType = dataUsageDeviceType;
    }

    public String getDataUsageTimeSlot() {
        return dataUsageTimeSlot;
    }

    public void setDataUsageTimeSlot(String dataUsageTimeSlot) {
        this.dataUsageTimeSlot = dataUsageTimeSlot;
    }

    public String getDataUsageAmount() {
        return dataUsageAmount;
    }

    public void setDataUsageAmount(String dataUsageAmount) {
        this.dataUsageAmount = dataUsageAmount;
    }

    public String getDataUsageType() {
        return dataUsageType;
    }

    public void setDataUsageType(String dataUsageType) {
        this.dataUsageType = dataUsageType;
    }
}
