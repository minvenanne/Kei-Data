package com.example.kei_data;


import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class User {

    public String userName;

    public String userID;

    public Date dateAdded;

    ArrayList<Device> deviceList;

    //ArrayList<DataUse> dataUseList;

    public User () {
        setUserName("n");
        setUserID();
        setDateAdded(); // få styr på denne

        this.deviceList = new ArrayList<Device>();
        //this.dataUseList = new ArrayList<DataUse>();
    }

    public void setUserName(String newUserName) {
        userName = newUserName;
    }

    public void setUserID() {

        //length of arraylist users + 1
        userID = userList.size() + 1;
    }

    public void setDateAdded() {
        dateAdded = new Date();
        //dato aflæses og sættes som værdi her
    }

    public ArrayList<Device> getDeviceList() {
        return deviceList;
    }

    //public ArrayList getDataUse() {
        //return dataUseList;
    //}


    //attributes:
    //public Boolean userAdded;

    //public Boolean userRemoved;

    //public String nfcID;

    //public Integer deviceDisplayID;

    //add to "object":
    //setNfcID("n0");
    //setDeviceDisplayID(0);
    //setUserAdded(true);
    //setUserRemoved(false);

    //Methods:
    //public void setNfcID(String newNfcID) {
        //nfcID = newNfcID;
        //laves til tilfældig værdi som ikke allerede eksisterer
    //}

    //public void setDeviceDisplayID(Integer newDeviceDisplayID) {
        //deviceDisplayID = newDeviceDisplayID;
        //potentielt set tager den her knap indputtet(eventlistener og sætter lig et device (if x eventlistener then x)
    //}

    //public void setUserAdded(Boolean newUserAddedStatus) {
        //userAdded = newUserAddedStatus;
        //laves til tilfældig værdi som ikke allerede eksisterer
    //}

    //public void setUserRemoved(Boolean newUserRemovedStatus) {
        //userRemoved = newUserRemovedStatus;
        //laves til tilfældig værdi som ikke allerede eksisterer
    //}

}
