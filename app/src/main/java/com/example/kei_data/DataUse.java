package com.example.kei_data;

import java.time.LocalDateTime;
import java.util.Date;

public class DataUse {
    private String dataUsageID;
    private String dataUsageDeviceType;
    private LocalDateTime dataUsageTimeSlot;
    private int dataUsageAmount;
    private String dataUsageType;

    public DataUse(String ID, String DeviceType, LocalDateTime Time, int Amount, String Type){
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

    public LocalDateTime getDataUsageTimeSlot() {
        return dataUsageTimeSlot;
    }

    public void setDataUsageTimeSlot(LocalDateTime dataUsageTimeSlot) {
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