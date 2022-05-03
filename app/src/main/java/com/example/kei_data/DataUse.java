package com.example.kei_data;

import java.util.Date;

public class DataUse {
    private String dataUsageID;
    private String dataUsageDeviceType;
    private String dataUsageTimeSlot;
    private int dataUsageAmount;
    private String dataUsageType;

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

    public int getDataUsageAmount() {
        return dataUsageAmount;
    }

    public void setDataUsageAmount(int dataUsageAmount) {
        this.dataUsageAmount = dataUsageAmount;
    }

    public String getDataUsageType() {
        return dataUsageType;
    }

    public void setDataUsageType(String dataUsageType) {
        this.dataUsageType = dataUsageType;
    }
}
