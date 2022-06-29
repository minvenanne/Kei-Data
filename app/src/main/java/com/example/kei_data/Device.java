package com.example.kei_data;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//setting up the variables
public class Device implements Serializable {

    public String deviceType;

    public int deviceID;

    public String deviceIP;

    public Date dateAdded;

    public String deviceName;


    // public Boolean statusPrivate;

    public ArrayList<DataUse> dataUseList = new ArrayList<>();

    //constructor creating a n0 user

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Device (String type, String ip, String name, User user) {
        setDeviceName(name);
        setDeviceID(user);
        setDateAdded();
        setDeviceIP(ip);
        setDeviceType(type);
        setDataUseArray(this);
    }

    //set device name to content of parenthesis
    public void setDeviceName(String name) {
        deviceName = name;
    }

    // set the date added to date of creation
    public void setDateAdded() {
        dateAdded = new Date();
    }

    public void setDeviceID(User mainUser) {
        //length of arraylist device
        mainUser.setNumberOfDevices();

        //device ID is set to number of devices in the list
        deviceID = mainUser.numberOfDevices;
    }

    //set device type to fx tv or computer
    public void setDeviceType(String type) {
        deviceType = type;
    }

    public String getDeviceType(){
        return this.deviceType;
    }


    // should help users locate IP
    public void setDeviceIP(String ip) {
        deviceIP = ip;
    }

    public static void addDataUse(DataUse data, Device device) {
        device.dataUseList.add(data);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDataUseArray(Device device){
            MainActivity.readMockData(device);
        }
}
