package com.example.kei_data;


import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class User {

    public String userName;

    public Integer userID;

    public Date dateAdded;

    public Date currentDate;

    public Float currentDataUseStandpoint;

    public Float currentCo2;

    ArrayList<Device> deviceList;

    public User () {
        userName = "n";
        setUserID();
        setDateAdded();
        setCurrentDate();
        currentDataUseStandpoint = (float) 0;
        currentCo2 = (float) 0;

        // creates list of class device
        this.deviceList = new ArrayList<Device>();
    }

    public void setUserID() {
        //length of arraylist users + 1
        userID = userList.size() + 1;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setDateAdded() {
        dateAdded = new Date();
    }

    public void setCurrentDate() {
        currentDate = new Date();
    }

    public void addDevice(Device device){
        deviceList.add(device);
    }

    public Device removeDevice(String currentDevice) {
        for(int i = 0; i < deviceList.size(); i++){
            Device device = deviceList.get(i); // the current element
            // check if the currentDevice has the name that we are looking for
            if(device.deviceName.equals(currentDevice)){
                deviceList.remove(i); // remove the element
                return device; // return the soda that we found
            }
        }
        // if we arrive here, no item was found
        return null;
    }

    public void updateCurrentDataUseStandpointAndCo2() {
        // creating a variable to hold the value of the new standpoint
        float newStandPoint;
        newStandPoint = 0;
        // creating a variable to hold the date at this exact moment
        Date newDate = new Date();
        // checking if the day has shifted
        // if yes it will update the current date and set the current standpoint to 0 before continuing
        //this depends on the fact that we update 23:59 instead of 00:00 - else data is logged on the wrong day
        if (currentDate.compareTo(newDate) != 0) {
            currentDate = newDate;
            currentDataUseStandpoint = (float) 0;
        }

        for(int i = 0; i < deviceList.size(); i++) {

            Device device = deviceList.get(i); // the current element

            for(int f = 0; f < device.dataUseList.size(); f++) {

                DataUse dataUse = dataUseList.get(i);
                newStandPoint = newStandPoint + dataUse.dataUsageAmount;
            }
        }

        currentDataUseStandpoint = newStandPoint + currentDataUseStandpoint;
        System.out.println("current data is now:" + currentDataUseStandpoint);

        calculateCurrentCo2(currentDataUseStandpoint);
        System.out.println("current co2 is now:" + currentCo2);
    }

    public void calculateCurrentCo2(float currentDataUseStandpoint) {

        //co2 pr MB
        float co2MB = (float) 0.054;
        float newCo2;
        //ganger co2 or MB på mængden af MB
        newCo2 = currentDataUseStandpoint*co2MB;
        //sætter current co2 til at være lig med data use i co2 format
        currentCo2 = newCo2;
    }
}
