package com.example.kei_data;

import android.graphics.Color;
import android.util.Log;

import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;

public class Household {

    public String familyName;
    public static int numberOfUsers;
    public String householdID;
    public static String user;
    public String RouterID;

    //public Users[] user;
    private String routerID;

    public static ArrayList<User> userList = new ArrayList<>();
    public ArrayList<Integer> routerList = new ArrayList<>();


    //Constructor
    //String familyName, int numOfMembers, String householdID, String routerID
    public Household() {
        familyName = "Last Name";
        setNumberOfUsers();
        householdID = "1234";
        user = "No Name";
        routerID = "2563";
        userList = new ArrayList<>();
    }


    /*public void addRouter(int router) {
        //DONE
        System.out.println("addRouter BEFORE" + routerList);
        routerList.add(router);
        System.out.println("addRouter AFTER" + routerList);
    }

    public String getRouterId() {
        //DONE
        return routerID;
    }*/

    public static void setUserName(String name){
        user = name;
    }

    public static void addUser(String name){
        //DONE
        System.out.println("addUser BEFORE: " + userList);
        userList.add(new User(name));
        System.out.println("addUser AFTER: " + userList);
    }

    public static void removeUser(int user) {
        //DONE
        System.out.println("removeUser BEFORE: " + userList);
        userList.remove(user);
        User_activity.customAdapterUsers.notifyDataSetChanged();
        System.out.println("removeUser AFTER: " + userList);
    }

    public static void setNumberOfUsers() {
        numberOfUsers = userList.size(); //fejl når vi sletter en bruger - skal forbedres.
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


