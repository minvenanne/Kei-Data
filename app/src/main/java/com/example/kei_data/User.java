package com.example.kei_data;

import java.util.Date;

public class User {

    public String userName;

    public String userID;

    //public String nfcID;

    public Integer deviceDisplayID;

    public Date dateAdded;

    //public Boolean userAdded;

    //public Boolean userRemoved;

    ArrayList<Device> deviceList;

    ArrayList<DataUse> dataUseList;

    public User (String n) {
        userName = n;
        setUserID("u0");
        //setNfcID("n0");
        setDeviceDisplayID(0);
        //setUserAdded(true);
        setDateAdded(); // få styr på denne
        //setUserRemoved(false);

        this.deviceList = new ArrayList<Device>();
        this.dataUseList = new ArrayList<DataUse>();
    }

    public void setUserID(String newUserID) {
        userID = newUserID;
        //laves til tilfældig værdi som ikke allerede eksisterer
    }

    //public void setNfcID(String newNfcID) {
        //nfcID = newNfcID;
        //laves til tilfældig værdi som ikke allerede eksisterer
    //}

    public void setDeviceDisplayID(Integer newDeviceDisplayID) {
        deviceDisplayID = newDeviceDisplayID;
        //potentielt set tager den her knap indputtet(eventlistener og sætter lig et device (if x eventlistener then x)
    }

    public void setUserAdded(Boolean newUserAddedStatus) {
        userAdded = newUserAddedStatus;
        //laves til tilfældig værdi som ikke allerede eksisterer
    }

    public void setDateAdded(Date newDateAdded) {
        dateAdded = newDateAdded;
        //laves til tilfældig værdi som ikke allerede eksisterer
    }

    public void setUserRemoved(Boolean newUserRemovedStatus) {
        userRemoved = newUserRemovedStatus;
        //laves til tilfældig værdi som ikke allerede eksisterer
    }

}
