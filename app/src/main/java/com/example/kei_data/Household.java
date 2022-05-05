package com.example.kei_data;

import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.jjoe64.graphview.GraphView;

import java.io.Serializable;
import java.util.ArrayList;

public class Household implements Serializable {

    public String familyName;
    public int numberOfUsers;
    public String householdID;
    public String user;
    public String RouterID;

    //public Users[] user;
    private String routerID;

    public ArrayList<User> userList;
    public ArrayList<Integer> routerList = new ArrayList<>();


    //Constructor
    //String familyName, int numOfMembers, String householdID, String routerID
    public Household() {
        familyName = "Last Name";
        householdID = "1234";
        user = "No Name";
        routerID = "2563";
        setUserList();
        setNumberOfUsers();
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

    public void setCurrentUserName(String name){
        user = name;
    }

    // Add user to userlist for the household, based on a name
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addUser(String name){
        userList.add(new User(name, Household.this));
        setNumberOfUsers();
    }

    // add user to userlist of the household, based on a User
    public void addUser(User user){
        userList.add(user);
        setNumberOfUsers();
    }

    // remove user from household, and the userlist
    public void removeUser(int user) {
        userList.remove(user);
        User_activity.setHeight();
    }

    public ArrayList<User> getUserList(){
        return userList;
    }

    public void setUserList(){
        userList = new ArrayList<>();
    }

    public String[] getArraylistOfUserName(){
        String[] array = new String[userList.size()];
        for (int i = 0; i < userList.size(); i++){
            array[i] = userList.get(i).userName;
            System.out.println(array[i]);
        }
        return array;
    }

    public void setNumberOfUsers() {
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


