package com.example.kei_data;

import android.graphics.Color;
import android.util.Log;

import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;

public class Household {


    public String familyName;
    public int numOfMembers;
    public String householdID;
    public String user;

    //public Users[] user;
    private String routerID;

    public ArrayList<User> userList = new ArrayList<>();
    public ArrayList<Integer> routerList = new ArrayList<>();


    //Constructor
    //String familyName, int numOfMembers, String householdID, String routerID
    public Household() {
        this.familyName = familyName;
        this.numOfMembers = numOfMembers;
        this.householdID = householdID;
        this.user = user;
        this.routerID = routerID;

        userList.add("Casper");
        userList.add("Klaus");

        routerList.add(2563);
    }


    public void addRouter(int router) {
        //DONE
        System.out.println("addRouter BEFORE" + routerList);
        routerList.add(router);
        System.out.println("addRouter AFTER" + routerList);


    }

    public String getRouterId() {
        //DONE
        return routerID;
    }

    public void addUser(String user) {
        //DONE
        System.out.println("addUser BEFORE: " + userList);
        userList.add(user);
        System.out.println("addUser AFTER: " + userList);


    }

    public void removeUser(String user) {
        //DONE
        System.out.println("removeUser BEFORE: " + userList);
        userList.remove(user);
        System.out.println("removeUser AFTER: " + userList);

    }

    //    public String calculateFamilyWinner(DataUse[] dataUse) {
//        //TODO
//
//        return  userID;
//    }
//
//    public void displayBarchart(DataUse [] dataUse) {
//        //TODO
//
//    }
    public void colorChangeWinner(String userID) {
        //TODO

    }




}


