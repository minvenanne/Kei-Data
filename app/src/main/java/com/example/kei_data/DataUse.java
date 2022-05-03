package com.example.kei_data;

import java.time.LocalDateTime;

public class DataUse {
    public String dataUsageID;
    public String dataUsageDeviceType;
    public LocalDateTime dataUsageTimeSlot;
    public float dataUsageAmount;
    public String dataUsageType;

    public DataUse(String ID, String DeviceType, LocalDateTime Time, float Amount, String Type){
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

    public float getDataUsageAmount() {
        return dataUsageAmount;
    }

    public void setDataUsageAmount(float dataUsageAmount) {
        this.dataUsageAmount = dataUsageAmount;
    }

    public String getDataUsageType() {
        return dataUsageType;
    }

    public void setDataUsageType(String dataUsageType) {
        this.dataUsageType = dataUsageType;
    }
}
