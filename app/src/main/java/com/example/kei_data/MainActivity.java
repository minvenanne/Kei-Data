package com.example.kei_data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.widget.Button;

import android.content.Intent;

import android.view.View;
import android.widget.ImageButton;

import android.widget.ImageView;
import android.widget.RadioButton;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.CompoundButton;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class MainActivity extends AppCompatActivity {
    public ImageButton settingsButton;
    public ImageButton categoriesButton;
    SimpleDateFormat sdf = new SimpleDateFormat("K mm:ss");
    public Household testHousehold;
    public User mainUser;
    public GraphView graph;


    LineGraphSeries<DataPoint> dSeries;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         /*int minutes = LocalDateTime.now().getMinute();
        System.out.println("minutes is now" + minutes);
        // her tester vi kode
        User user = new User();
        user.currentDataUseStandpoint = (float) 0;
        user.currentCo2 = (float) 0;
        user.updateCurrentDataUseStandpointAndCo2();
        System.out.println(user.userName);
        //*/ // her tester vi kode

        setContentView(R.layout.activity_main);

        if (getIntent().getSerializableExtra("user") != null){
            mainUser = (User) getIntent().getSerializableExtra("user");
            testHousehold = (Household) getIntent().getSerializableExtra("household");
        }
        else{
            testHousehold = new Household();
            mainUser = new User("Roy Hudson", testHousehold);
            testHousehold.addUser(mainUser);
            testHousehold.familyName = "Hudson";
            System.out.println("Userlist = " + testHousehold.getUserList());
            System.out.println("number of users = " + testHousehold.numberOfUsers);
            System.out.println("Userlist size = " + testHousehold.userList.size());
        }

        if (mainUser.deviceList.size()==0){
            mainUser.addDevice("Computer", "345.982.41", "Per's laptop", mainUser);
            mainUser.addDevice("Phone", "584.682.91", "Per's Iphone 11", mainUser);
            mainUser.addDevice("TV", "675.892.34", "Per's bedroom TV", mainUser);
            mainUser.addDevice("Other", "565.875.32", "Per's Ipad", mainUser);
            mainUser.addDevice("Speaker", "623.769.99", "Per's wifi speaker bedroom", mainUser);
        }

        if (Devices_activity.arrayListShared.size()==0){
            Devices_activity.addElementsToArrayShared();
        }

        if (testHousehold.userList.size()==1){
            testHousehold.addUser("Mother Julie");
            testHousehold.addUser("Father Dennis");
            testHousehold.addUser("Little sister Laura");
            testHousehold.addUser("Big brother Jacob");
            testHousehold.addUser("Baby sister Ida");
        }

        //TEST//

        /*testHousehold.addUser("Camilla");
        testHousehold.addRouter(1234);
        testHousehold.removeUser("Klaus");*/


        //END TEST//

        graph = (GraphView) findViewById(R.id.graph);

        dSeries = new LineGraphSeries<DataPoint>();
        initGraph(graph);
        graph.getGridLabelRenderer().setNumHorizontalLabels(6);
        graph.getGridLabelRenderer().setHumanRounding(false);
        graph.getGridLabelRenderer().setNumVerticalLabels(4);

        System.out.println(mainUser.currentCo2);

        //få fikset at vi kan vise alle 24 timer
        for (int r = 0; r < 24*60; r++) { //4320
            int minutes = mainUser.currentDate.getMinute();

            //herover indsættes current time
            //tjekker om minuttallet er 0 eller 30
            if (minutes == 0 || minutes == 30) {
                //opdater current time her i steder for i user
                mainUser.updateCurrentDataUseStandpointAndCo2();

                float currentHour = mainUser.currentDate.getHour();

                float currentMinute = mainUser.currentDate.getMinute();
                float half = 0.5F;

                if (currentMinute == 30) {
                    currentHour = currentHour + half;
                }
                dSeries.appendData(new DataPoint(currentHour, mainUser.currentCo2), true, 1440);

                System.out.println(" my name is " + mainUser.userName);
                System.out.println(" the clock is " + mainUser.currentDate);
                System.out.println(" and your Co2 use is now " + mainUser.currentCo2);
                mainUser.currentDate = (mainUser.currentDate).plusMinutes(1);
            }
            else {
                // creating a variable to hold the updated time
                mainUser.currentDate = (mainUser.currentDate).plusMinutes(1);
            }
            if (mainUser.currentDate.getHour() == 0 && mainUser.currentDate.getMinute() == 0) {
                mainUser.currentDataUseStandpoint = (float) 0;
                System.out.println("new day");
                mainUser.setCurrentDate();
            }
        }
        graph.addSeries(dSeries);

        if (getIntent().getSerializableExtra("user") == null){
            for (int r = 0; r < 24*60; r++) { //4320
                int minutes = mainUser.currentDate.getMinute();

                //herover indsættes current time
                //tjekker om minuttallet er 0 eller 30
                if (minutes == 0 || minutes == 30) {
                    //opdater current time her i steder for i user
                    for (int i = 1; i < testHousehold.userList.size(); i++) {
                        // specifying the user
                        User user = testHousehold.userList.get(i);
                        // hvis user id er 0 (altså vores main user)
                        //ellers er det en "household user" og så får de bare tildelt random data
                        user.updateCurrentDataUseStandpointAndCo2NotMainUser();

                        System.out.println(" my name is " + user.userName);
                        System.out.println(" the clock is " + mainUser.currentDate);
                        System.out.println(" and your Co2 use is now " + user.currentCo2);
                    }
                    mainUser.currentDate = (mainUser.currentDate).plusMinutes(1);
                }
                else {
                    // creating a variable to hold the updated time
                    mainUser.currentDate = (mainUser.currentDate).plusMinutes(1);
                }
                if (mainUser.currentDate.getHour() == 0 && mainUser.currentDate.getMinute() == 0) {
                    mainUser.currentDataUseStandpoint = (float) 0;
                    System.out.println("new day");
                }
            }
        }



        /*
        //Creates a timestamp from the Date object
        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return sdf.format(new Date((long) value));
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }

        });
        // setNumHorizontalLabels determines the amount of labes on the y-axis that will be visible
        // setHumanRounding enables the rounding of the numbers on the x-axis
        // setNumVerticalLabels determines the amount of labes on the x-axis that will be visible
        graph.getGridLabelRenderer().setNumHorizontalLabels(4);
        graph.getGridLabelRenderer().setHumanRounding(false);
        graph.getGridLabelRenderer().setNumVerticalLabels(10);
*/


        //Connects the radiobutton group with the onCheckedChange method.
        //When a radiobutton is checked, it removes the past series, and adds a new.
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                if (checkedId == R.id.radioButton) {
                    Log.d("Success", "D was pressed");
                    graph.removeAllSeries();
                    graph.addSeries(dSeries);
                    dSeries.setAnimated(true);


                } else if (checkedId == R.id.radioButton2) {
                    Log.d("Success", "W was pressed");
                    graph.removeAllSeries();
                    graph.addSeries(weekSeries);
                    weekSeries.setAnimated(true);

                } else if (checkedId == R.id.radioButton3) {
                    Log.d("Success", "M was pressed");
                    graph.removeAllSeries();
                    graph.addSeries(mSeries);
                    mSeries.setAnimated(true);


                } else if (checkedId == R.id.radioButton4) {
                    Log.d("Success", "6M was pressed");
                    graph.removeAllSeries();
                    graph.addSeries(sixMSeries);
                    sixMSeries.setAnimated(true);


                } else if (checkedId == R.id.radioButton5) {
                    Log.d("Success", "Y was pressed");
                    graph.removeAllSeries();
                    graph.addSeries(ySeries);
                    ySeries.setAnimated(true);


                }


            }
        });


        Switch householdSwitch = (Switch) findViewById(R.id.HouseholdSwitch);
//            Lets the user switch between two modes.
        householdSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (householdSwitch.isChecked()) {
                    Log.d("Success", "Household");
                    Intent intent = new Intent(MainActivity.this, Household_activity.class);
                    intent.putExtra("household", testHousehold);
                    intent.putExtra("user", mainUser);
                    startActivity(intent);

                } else {
                    Log.d("Success", "you");
                }
            }
        });
        //END// SWITCH BETWEEN YOU AND HOUSEHOLD

        settingsButton = (ImageButton) findViewById(R.id.Settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Settings_activity1.class);
                intent.putExtra("household", testHousehold);
                intent.putExtra("user", mainUser);
                startActivity(intent);
            }
        });

        categoriesButton = (ImageButton) findViewById(R.id.Categories);
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Categories_activity.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", getIntent().getSerializableExtra("user"));
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void readMockData(Device device) {
        InputStream is = App.getRes().openRawResource(R.raw.data_usage_yes);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)
        );

        String line = "";
        try {

            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(";");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                DataUse data = new DataUse(tokens[0], tokens[1], LocalDateTime.parse(tokens[2], formatter), Float.parseFloat(tokens[3]), tokens[4]);
                //System.out.println(data.getDataUsageAmount());
                /*data.setDataUsageID();
                data.setDataUsageDeviceType(tokens[1]);
                data.setDataUsageTimeSlot(tokens[2]);
                data.setDataUsageAmount(Integer.parseInt(tokens[3]));
                data.setDataUsageType(tokens[4]);*/
                if (device.getDeviceType().equals(data.getDataUsageDeviceType())){
                    device.addDataUse(data, device);
                }
                else {
                    //System.out.println("IKKE TILFØJET");
                    //System.out.println("ID: " + data.getDataUsageID());
                    //System.out.println("Device Type:" + data.getDataUsageDeviceType());
                }
                //System.out.println(device.dataUseList);
            }

        } catch (IOException e) {
            Log.wtf("Main Activity", "Error Reading Data File on line" + line, e);
            e.printStackTrace();
        }
    }

    //Creates the different datasets for the graph.
    /*LineGraphSeries<DataPoint> dSeries = new LineGraphSeries<>(new DataPoint[] {
            new DataPoint (new Date().getTime(), 42.9),
            new DataPoint (new Date().getTime(), 68.5),
            new DataPoint (new Date().getTime(), 147),
            new DataPoint (new Date().getTime(), 50),
    });
*/
    BarGraphSeries<DataPoint> weekSeries = new BarGraphSeries<>(new DataPoint[] {

            new DataPoint(0, 2000),
            new DataPoint(1, 900),
            new DataPoint(2, 5000),
            new DataPoint(3, 3400),
            new DataPoint(4, 1200),
            new DataPoint(5, 4000),
            new DataPoint(6, 4500)
    });

    //pr uge i måneden
    BarGraphSeries<DataPoint> mSeries = new BarGraphSeries<>(new DataPoint[] {
            new DataPoint(0, 9000),
            new DataPoint(1, 10000),
            new DataPoint(2, 11000),
            new DataPoint(3, 8000)
    });
    //hver måned i 1000 x Y
    BarGraphSeries<DataPoint> sixMSeries = new BarGraphSeries<>(new DataPoint[] {
            new DataPoint(0, 40),
            new DataPoint(1, 50),
            new DataPoint(2, 35),
            new DataPoint(3, 41),
            new DataPoint(4, 33),
            new DataPoint(4, 56)
    });

    //kvartal per 1000 x Y
    BarGraphSeries<DataPoint> ySeries = new BarGraphSeries<>(new DataPoint[] {
            new DataPoint(0, 200),
            new DataPoint(1, 198),
            new DataPoint(2, 150),
            new DataPoint(3, 230),

    });

    //Styling of the graph
    public void initGraph(GraphView graph){
        //Colors of the the graph
        dSeries.setColor(Color.rgb(120,150,111));
        weekSeries.setColor(Color.rgb(120,150,111));
        mSeries.setColor(Color.rgb(120,150,111));
        sixMSeries.setColor(Color.rgb(120,150,111));
        ySeries.setColor(Color.rgb(120,150,111));

        //Thickness of graph
        dSeries.setThickness(10);
        weekSeries.setSpacing(25);
        mSeries.setSpacing(25);
        sixMSeries.setSpacing(25);
        ySeries.setSpacing(25);

    }

    public User getMainUser(){
        return mainUser;
    }
}