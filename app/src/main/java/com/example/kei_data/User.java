package com.example.kei_data;

public class User {

    public String userName;

    public String userID;

    public String nfcID;

    public Integer deviceDisplayID;

    public Boolean dataAdded;

    public Boolean userAdded;

    public Boolean userRemoved;

    ArrayList<Device> deviceList;

    ArrayList<DataUse> dataUseList;

    public User (String n) {
        userName = n;
        setUserID("u0");
        setNfcID("n0");
        setDeviceDisplayID(0);
        setUserAdded(true);
        setDataAdded(true);
        setUserRemoved(false);

        this.deviceList = new ArrayList<Device>();
        this.dataUseList = new ArrayList<DataUse>();
    }

    public void setUserID(String newUserID) {
        userID = newUserID;
        //laves til tilfældig værdi som ikke allerede eksisterer
    }

    public void setNfcID(String newNfcID) {
        nfcID = newNfcID;
        //laves til tilfældig værdi som ikke allerede eksisterer
    }

    public void setDeviceDisplayID(Integer newDeviceDisplayID) {
        deviceDisplayID = newDeviceDisplayID;
        //laves til tilfældig værdi som ikke allerede eksisterer
    }

    public void setUserAdded(Boolean newUserAddedStatus) {
        userAdded = newUserAddedStatus;
        //laves til tilfældig værdi som ikke allerede eksisterer
    }

    public void setDataAdded(Boolean newDataAddedStatus) {
        dataAdded = newDataAddedStatus;
        //laves til tilfældig værdi som ikke allerede eksisterer
    }

    public void setUserRemoved(Boolean newUserRemovedStatus) {
        userRemoved = newUserRemovedStatus;
        //laves til tilfældig værdi som ikke allerede eksisterer
    }

}
