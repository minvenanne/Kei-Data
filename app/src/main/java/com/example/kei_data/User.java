package com.example.kei_data;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ArrayList;
import java.time.LocalDateTime;


public class User implements Serializable {
    public String userName;

    public Integer userID;

    public LocalDateTime dateAdded;

    public LocalDateTime currentDate;

    public Float currentDataUseStandpoint;

    public Float currentCo2;

    public Integer numberOfDevices;

    public ArrayList<Device> deviceList = new ArrayList<>(); // made static to be able to use it across classes

    //constructor creating a n0 user
    @RequiresApi(api = Build.VERSION_CODES.O)
    public User (String name, Household testHousehold) {
        setUserName(name);
        setUserID(testHousehold);
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

    public void setUserID(Household testHousehold) {
        //size of arraylist User
        testHousehold.setNumberOfUsers();
        //user ID is set to number of devices in the list
        userID = testHousehold.numberOfUsers;
    }

    public ArrayList<Device> getDeviceList(){
        return this.deviceList;
    }

    public void setNumberOfDevices() {
        numberOfDevices = deviceList.size(); //wrong, fejl i antal hvis man sletter device
    }

    public int getUserID() {
        return userID;
    }

    // set the date added to date of creation
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDateAdded() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateAdded = LocalDateTime.parse("2022-04-01 00:00:00",formatter);
    }

    // set the current date to the date of creation
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        currentDate = LocalDateTime.parse("2022-04-01 00:00:00", formatter);
    }

    //Danner tilfældig værdi som ligges oveni current datause, for alle andre brugere end main bruger
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateCurrentDataUseStandpointAndCo2NotMainUser() {

        // creating a variable to hold the updated time
        LocalDateTime newTime = (currentDate).plusMinutes(1);

        //beregn random værdig mellem 0 og 3000
        float newStandPoint= (float)Math.floor(Math.random()*(3000-0+1)+0);

        //Update standpoint med tilfældigt tal
        currentDataUseStandpoint = newStandPoint + currentDataUseStandpoint;

        //Calculating the co2 as a result of the current data use
        calculateCurrentCo2(currentDataUseStandpoint);
        System.out.println("current co2 is now:" + currentCo2);

        Instant instant1 = currentDate.atZone(ZoneId.systemDefault()).toInstant();
        Date date1 = Date.from(instant1);

        Instant instant2 = newTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date2 = Date.from(instant2);

        // checking if the day has shifted
        // if yes it will update the current date and set the current standpoint to 0 before continuing
        //this depends on the fact that we update 23:59 instead of 00:00 - else data is logged on the wrong day
        if (date1.compareTo(date2) != 0) {
            currentDate = newTime;
            currentDataUseStandpoint = (float) 0;
        }
        else {
            currentDate = newTime;
        }

    }
    //add a device to the list of devices
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addDevice(String type, String IP, String name, User user){
        Device device = new Device(type, IP, name, user);
        deviceList.add(device);

        // prints out the content of the added device

        System.out.println(device.deviceType);
        System.out.println(device.deviceIP);
        System.out.println(device.dateAdded);
        System.out.println(device.deviceName);
        System.out.println(device.deviceAdded);
        System.out.println(device.deviceRemoved);

        setNumberOfDevices();
    }

    public void removeDevicePrivate(int position, User user){
        user.deviceList.remove(position);
        Devices_activity.iconsPrivate.remove(position);
        Devices_activity.customAdapterPrivat.notifyDataSetChanged();
        Devices_activity.simpleListPrivate.setMinimumHeight(Devices_activity.justifyListViewHeightBasedOnChildrenPrivate(Devices_activity.simpleListPrivate, Devices_activity.customAdapterPrivat));
    }

    public void removeDeviceShared(int position){
        Devices_activity.arrayListShared.remove(position);
        Devices_activity.iconsShared.remove(position);
        Devices_activity.customAdapterShared.notifyDataSetChanged();
        Devices_activity.simpleListShared.setMinimumHeight(Devices_activity.justifyListViewHeightBasedOnChildrenShared(Devices_activity.simpleListShared, Devices_activity.customAdapterShared));
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateCurrentDataUseStandpointAndCo2() {
        // creating a variable to hold the value of the new standpoint
        float newStandPoint;
        newStandPoint = 0;
        // creating a variable to hold the date at this exact moment

        //adding x minute
        LocalDateTime newTime = currentDate.plusMinutes(1);

        //going through each individual device
        for (int i = 0; i < deviceList.size(); i++) {

            // specifying the device
            Device device = deviceList.get(i); // the current element

            //cykling through each datause on data use list
            for (int f = 0; f < device.dataUseList.size(); f++) {

                //specifying the datause
                DataUse dataUse = device.dataUseList.get(i);

                // tjekker om tidspunktet i datause listen ligger inden for det gældende interval (for det givne tidspunkt og periode), og lægger tallet oven i standpoint, hvis det er.
                if (dataUse.dataUsageTimeSlot.isAfter(newTime.minusMinutes(1)) && dataUse.dataUsageTimeSlot.isBefore(newTime))
                    //tallying up all the datause from each device
                    newStandPoint = newStandPoint + dataUse.dataUsageAmount;

                //setting the data use amount to 0 to start over - tror ikke det er nødvendigt
                //dataUse.dataUsageAmount = (float) 0;
            }
        }

        //adding the new data use to the current data use
        currentDataUseStandpoint = newStandPoint + currentDataUseStandpoint;
        System.out.println("current data is now:" + currentDataUseStandpoint);

        //Calculating the co2 as a result of the current data use
        calculateCurrentCo2(currentDataUseStandpoint);
        System.out.println("current co2 is now:" + currentCo2);

        // checking if the day has shifted
        // if yes it will update the current date and set the current standpoint to 0 before continuing
        //this depends on the fact that we update 23:59 instead of 00:00 - else data is logged on the wrong day
        Instant instant1 = currentDate.atZone(ZoneId.systemDefault()).toInstant();
        Date date1 = Date.from(instant1);

        Instant instant2 = newTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date2 = Date.from(instant2);


        if (date1.compareTo(date2) != 0) {
            currentDate = newTime;
            currentDataUseStandpoint = (float) 0;
        }
        else {
            currentDate = newTime;
        }
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

/*
Kode til main:

    @RequiresApi(api = Build.VERSION_CODES.O)

// får det gældende minut-tal
int minutes = LocalDateTime.now().getMinute();

//tjekker om minuttallet er 0 eller 30
if (minutes == 0 || minutes == 30) {

                //Ruller igennem alle Users
                for(int i = 0; i < Household.userList.size(); i++) {

                    // specifying the user
                    User user = Household.userList.get(i);

                    // hvis user id er 1 (altså vores main user)
                    if (user.userID == 1) {
                        user.updateCurrentDataUseStandpointAndCo2();
                    }

                    //ellers er det en "household user" og så får de bare tildelt random data
                    else {
                        user.updateCurrentDataUseStandpointAndCo2NotMainUser();
                    }
                    System.out.println(" the clock is" + User.currentDate);
                    System.out.println("and your Co2 use is now" + User.currentCo2);                }
            }
        }


*/
