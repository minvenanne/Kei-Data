package com.example.kei_data;

import static com.example.kei_data.User.setNumberOfDevices;

import java.util.ArrayList;
import java.util.Date;

//setting up the variables
public class Device {

    public String deviceType;

    public Integer deviceID;

    public String deviceIP;

    public Date dateAdded;

    public String deviceName;

    public Boolean deviceAdded;

    public Boolean deviceRemoved;

    // public Boolean statusPrivate;

    //ArrayList<DataUse> dataUseList;

    //constructor creating a n0 user
    public Device (String type, String ip, String name) {
        setDeviceName(name); //skal kobles op med activity så den tager indput derfra
        setDeviceID();
        setDateAdded();
        setDeviceIP(ip); // mangler alt - kan måske først sættes op når der er lavet en falsk database af IP ala den til datause som anne har lavet.
        setDeviceAdded(true);
        setDeviceRemoved(false);
        setDeviceType(type);
        //setStatusPrivate(true);

        // creates list of class datause
        //this.dataUseList = new ArrayList<DataUse>();
    }

    //set device name to content of parenthesis
    public void setDeviceName(String name) {
        deviceName = name;
    }

    // set the date added to date of creation
    public void setDateAdded() {
        dateAdded = new Date();
    }

    //add a device to the list of devices
    /*public void addDataUse(DataUse dataUse){
        dataUseList.add(dataUse);
    }*/
    public void setDeviceID() {
        //length of arraylist device
        setNumberOfDevices();

        //device ID is set to number of devices in the list
        deviceID = User.numberOfDevices;
    }

    //set status of added to true or false
    public void setDeviceAdded (Boolean addedStatus) {
        deviceAdded = addedStatus;
    }

    //set status of removed to true or false
    public void setDeviceRemoved (Boolean removedStatus) {
        deviceRemoved = removedStatus;
    }

    //set device type to fx tv or computer
    public void setDeviceType(String type) {
        deviceType = type;
    }

    // should help users locate IP
    public void setDeviceIP(String ip) {
        deviceIP = ip;
    }

}
