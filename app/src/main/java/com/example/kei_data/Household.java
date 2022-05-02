package com.example.kei_data;

import android.graphics.Color;
import android.util.Log;

import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;

public class Household {


    public String familyName;
    public static int numberOfUsers;
    public String householdID;
    public String user;

    //public Users[] user;
    private String routerID;

    public ArrayList<User> userList;
    public ArrayList<Integer> routerList = new ArrayList<>();


    //Constructor
    //String familyName, int numOfMembers, String householdID, String routerID
    public Household() {
        this.familyName = familyName;
        setNumberOfUsers();
        this.householdID = householdID;
        this.user = user;
        this.routerID = routerID;
        userList = new ArrayList<>();
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

    public void addUser(User user) {
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

    public static void setNumberOfUsers() {
        numberOfUsers = userList.size();
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


