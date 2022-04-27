package com.example.kei_data;


import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class User {

    public String userName;

    public Integer userID;

    public Date dateAdded;

    public Integer currentDataUseStandpoint;
    public Integer currentCo2;

    //ArrayList<Device> deviceList;

    public User () {
        userName = "n";
        setUserID();
        setDateAdded();
        currentDataUseStandpoint = 0;
        currentCo2 = 0;

        // creates list of class device
        //this.deviceList = new ArrayList<Device>();
    }

    public void setUserID() {
        //length of arraylist users + 1
        //        userID = userList.size() + 1;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setDateAdded() {
        dateAdded = new Date();
    }

 //fikses - er det den rigtige måde?
    public void updateCurrentDataUseStandpointAndCo2() {
        int newStandPoint;
        newStandPoint = 2;
        currentDataUseStandpoint = newStandPoint;
        System.out.println("current data is now:" + currentDataUseStandpoint);

        calculateCurrentCo2(currentDataUseStandpoint);
        System.out.println("current co2 is now:" + currentCo2);
    }

    public void calculateCurrentCo2(int currentDataUseStandpoint) {

        int newCo2;
        newCo2 = 3;

        currentCo2 = newCo2;
    }

    //public void addDevice(Device device){
        //deviceList.add(device);
    //}

    /* public Device removeDevice(String currentDevice) {
        for(int i = 0; i < deviceList.size(); i++){
            Device device = deviceList.get(i); // the current element
            /* check if the currentDevice has the name that
               we are looking for
            if(device.deviceName.equals(currentDevice)){
                deviceList.remove(i); // remove the element
                return device; // return the soda that we found
            }
        }
        // if we arrive here, no item was found
        return null;
    } */


// alt herunder er gammel kode, vi ikke bruger fordi vi ser bort fra det

    //public ArrayList getDataUse() {
        //return dataUseList;
    //}


    //attributes:
    //public Boolean userAdded;

    //public Boolean userRemoved;

    //public String nfcID;

    //public Integer deviceDisplayID;

    //ArrayList<DataUse> dataUseList;

    //this.dataUseList = new ArrayList<DataUse>();

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
