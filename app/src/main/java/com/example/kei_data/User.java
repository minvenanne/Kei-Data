package com.example.kei_data;


import java.util.Date;
import java.util.ArrayList;

public class User {

    public String userName;

    public Integer userID;

    public Date dateAdded;

    public Date currentDate;

    public Float currentDataUseStandpoint;

    public Float currentCo2;

    public static Integer numberOfDevices;

    static ArrayList<Device> deviceList;

    //constructor creating a n0 user
    public User () {
        setUserName("n");
        setUserID();
        setDateAdded();
        setCurrentDate();
        setNumberOfDevices();
        currentDataUseStandpoint = (float) 0;
        currentCo2 = (float) 0;

        // creates list of class device
        deviceList = new ArrayList<>();
    }

    public void setUserName(String name) {
        userName = name;
    }

    public void setUserID() {
        //size of arraylist User
        Household.setNumberOfUsers();
        //user ID is set to number of devices in the list
        userID = Houshold.numberOfUsers;
    }

    public static void setNumberOfDevices() {
        numberOfDevices = deviceList.size();
    }

    public Integer getUserID() {
        return userID;
    }

    // set the date added to date of creation
    public void setDateAdded() {
        dateAdded = new Date();
    }

    // set the current date to the date of creation
    public void setCurrentDate() {
        currentDate = new Date();
    }

    //add a device to the list of devices
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

        //going through each individual device
        for(int i = 0; i < deviceList.size(); i++) {

            // specifying the device
            Device device = deviceList.get(i); // the current element

            //cykling through each datause on data use list
            for(int f = 0; f < device.dataUseList.size(); f++) {

                //specifying the datause
                DataUse dataUse = dataUseList.get(i);

                //tallying up all the datause from each device
                newStandPoint = newStandPoint + dataUse.dataUsageAmount;

                //setting the data use amount to 0 to start over
                dataUse.dataUsageAmount = (float) 0;
            }
        }

        //adding the new data use to the current data use
        currentDataUseStandpoint = newStandPoint + currentDataUseStandpoint;
        System.out.println("current data is now:" + currentDataUseStandpoint);

        //Calculating the co2 as a result of the current data use
        calculateCurrentCo2(currentDataUseStandpoint);
        System.out.println("current co2 is now:" + currentCo2);
    }

    public void calculateCurrentCo2(float currentDataUseStandpoint) {

        //co2 pr MB
        float co2MB = (float) 0.054;
        float newCo2;
        //multiply co2 pr MB with current MB
        newCo2 = currentDataUseStandpoint*co2MB;
        // current co2 set equal to current data use in co2 format
        currentCo2 = newCo2;
    }
}
