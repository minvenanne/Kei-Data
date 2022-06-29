package com.example.kei_data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.content.Intent;

import android.view.View;
import android.widget.ImageButton;

import android.util.Log;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.TextView;



import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class MainActivity extends AppCompatActivity {
    public ImageButton settingsButton;
    public ImageButton categoriesButton;
    public Household testHousehold;
    public User mainUser;
    public GraphView graph;
    private int x;
    BarGraphSeries<DataPoint> weekSeries;

    LineGraphSeries<DataPoint> dSeries;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        graph = (GraphView) findViewById(R.id.graph);

        dSeries = new LineGraphSeries<DataPoint>();
        weekSeries = new BarGraphSeries<DataPoint>();
        initGraph(graph);
        graph.getGridLabelRenderer().setNumHorizontalLabels(6);
        graph.getGridLabelRenderer().setHumanRounding(false);
        graph.getGridLabelRenderer().setNumVerticalLabels(4);
        
        if (getIntent().getSerializableExtra("user") != null){
            mainUser = (User) getIntent().getSerializableExtra("user");
            testHousehold = (Household) getIntent().getSerializableExtra("household");
        }
        else{
            testHousehold = new Household();
            mainUser = new User("Lucas", testHousehold);
            testHousehold.addUser(mainUser);
            testHousehold.familyName = "Hudson";
        }

        if (mainUser.deviceList.size()==0){
            mainUser.addDevice("Computer", "345.982.41", "Laptop", mainUser);
            mainUser.addDevice("Phone", "584.682.91", "Iphone 11", mainUser);
            mainUser.addDevice("Other", "565.875.32", "Ipad", mainUser);
            mainUser.addDevice("Speaker", "623.769.99", "wifi speaker bedroom", mainUser);
        }

        if (Devices_activity.arrayListShared.size()==0){
            Devices_activity.addElementsToArrayShared();
        }

        if (testHousehold.userList.size()==1){
            testHousehold.addUser("Mother");
            testHousehold.addUser("Father");
            testHousehold.addUser("Julie");
            testHousehold.addUser("Henry");
            testHousehold.addUser("Karen");
        }

        //dSeries
        //få fikset at vi kan vise alle 24 timer
        mainUser.setCurrentDate();
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
                dSeries.appendData(new DataPoint(currentHour, Math.round(mainUser.currentCo2)), true, 1440);

                mainUser.currentDate = (mainUser.currentDate).plusMinutes(1);
            }
            else {
                // creating a variable to hold the updated time
                mainUser.currentDate = (mainUser.currentDate).plusMinutes(1);
            }
            if (mainUser.currentDate.getHour() == 23 && mainUser.currentDate.getMinute() == 59) {
                mainUser.currentDataUseStandpoint = (float) 0;
                mainUser.setCurrentDate();
                break;
            }
        }
        graph.addSeries(dSeries);
        graph.getGridLabelRenderer().setNumHorizontalLabels(6);
        graph.getGridLabelRenderer().setHumanRounding(true, false);
        graph.getGridLabelRenderer().setNumVerticalLabels(6);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(dSeries.getHighestValueY() + 250);
        graph.getViewport().setMaxX(24);
        graph.getViewport().setMinX(4);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);

        TextView CO2Number = findViewById(R.id.number);
        CO2Number.setText(Math.round(mainUser.currentCo2) + " g CO2 / " + Math.round(mainUser.currentKM) + " km in a car");

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
                        user.updateCurrentDataUseStandpointAndCo2NotMainUser();

                    }
                    mainUser.currentDate = (mainUser.currentDate).plusMinutes(1);
                }
                else {
                    // creating a variable to hold the updated time
                    mainUser.currentDate = (mainUser.currentDate).plusMinutes(1);
                }
                if (mainUser.currentDate.getHour() == 0 && mainUser.currentDate.getMinute() == 0) {
                    mainUser.currentDataUseStandpoint = (float) 0;
                }
            }
        }

        //Connects the radiobutton group with the onCheckedChange method.
        //When a radiobutton is checked, it removes the past series, and adds a new.
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                if (checkedId == R.id.radioButton) {
                    userCo2GraphDay();
                    graph.removeAllSeries();
                    graph.addSeries(dSeries);
                    dSeries.setAnimated(true);
                    weekSeries.resetData(new DataPoint[] {});
                    initGraph(graph);
                    graph.getGridLabelRenderer().setNumHorizontalLabels(6);
                    graph.getGridLabelRenderer().setHumanRounding(true, false);
                    graph.getGridLabelRenderer().setNumVerticalLabels(6);
                    graph.getViewport().setMinY(0);
                    graph.getViewport().setMaxY(dSeries.getHighestValueY() + 250);
                    graph.getViewport().setMaxX(24);
                    graph.getViewport().setMinX(4);
                    graph.getViewport().setXAxisBoundsManual(true);
                    graph.getViewport().setYAxisBoundsManual(true);
                }
                else if (checkedId == R.id.radioButton2) {
                    x = 7;
                    userCO2GraphWeek(x, weekSeries);
                    dSeries.resetData(new DataPoint[]{});
                    graph.removeAllSeries();
                    graph.addSeries(weekSeries);
                    initGraph(graph);
                    graph.getGridLabelRenderer().setNumHorizontalLabels(7);
                    graph.getGridLabelRenderer().setHumanRounding(true);
                    graph.getGridLabelRenderer().setNumVerticalLabels(6);
                    graph.getViewport().setMinY(weekSeries.getLowestValueY()-500);
                    graph.getViewport().setMaxY(weekSeries.getHighestValueY() + 250);
                    graph.getViewport().setMaxX(x + 1);
                    graph.getViewport().setMinX(-1);
                    graph.getViewport().setXAxisBoundsManual(true);
                    graph.getViewport().setYAxisBoundsManual(true);
                    weekSeries.setDrawValuesOnTop(true);
                    weekSeries.setValuesOnTopColor(Color.BLACK);
                }
                else if (checkedId == R.id.radioButton3) {
                    x = 30;
                    userCO2GraphWeek(x, mSeries);
                    weekSeries.resetData(new DataPoint[] {});
                    dSeries.resetData(new DataPoint[]{});
                    graph.removeAllSeries();
                    graph.addSeries(mSeries);
                    initGraph(graph);
                    graph.getGridLabelRenderer().setNumHorizontalLabels(x/2);
                    graph.getGridLabelRenderer().setHumanRounding(false);
                    graph.getGridLabelRenderer().setNumVerticalLabels(8);
                    graph.getViewport().setMinY(mSeries.getLowestValueY()-500);
                    graph.getViewport().setMaxY(mSeries.getHighestValueY() + 250);
                    graph.getViewport().setMaxX(x + 1);
                    graph.getViewport().setMinX(-1);
                    graph.getViewport().setXAxisBoundsManual(true);
                    graph.getViewport().setYAxisBoundsManual(true);
                    graph.getGridLabelRenderer().setHorizontalLabelsAngle(140);
                }
                else if (checkedId == R.id.radioButton4) {
                    graph.removeAllSeries();
                    graph.addSeries(sixMSeries);
                    sixMSeries.setAnimated(true);
                }
                else if (checkedId == R.id.radioButton5) {
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
                    Intent intent = new Intent(MainActivity.this, Household_activity.class);
                    dSeries.resetData(new DataPoint[]{});
                    intent.putExtra("household", testHousehold);
                    intent.putExtra("user", mainUser);
                    startActivity(intent);
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
                if (device.getDeviceType().equals(data.getDataUsageDeviceType())){
                    device.addDataUse(data, device);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
    public void initGraph(GraphView graph) {
        //Colors of the the graph
        dSeries.setColor(Color.rgb(120, 150, 111));
        weekSeries.setColor(Color.rgb(120, 150, 111));
        mSeries.setColor(Color.rgb(120, 150, 111));
        sixMSeries.setColor(Color.rgb(120, 150, 111));
        ySeries.setColor(Color.rgb(120, 150, 111));

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void userCO2GraphWeek(int x, BarGraphSeries<DataPoint> series){
        series.resetData(new DataPoint[]{});
        int currentDay = 0;
        mainUser.setCurrentDate();
        for (int r = 0; r < 24*60*x; r++) { //4320
            int minutes = mainUser.currentDate.getMinute();
            float currentHour = mainUser.currentDate.getHour();

            //herover indsættes current time
            //tjekker om minuttallet er 0 eller 30
            if (minutes == 0 || minutes == 30) {
                //opdater current time her i steder for i user
                mainUser.updateCurrentDataUseStandpointAndCo2();
                currentHour = mainUser.currentDate.getHour();

                float currentMinute = mainUser.currentDate.getMinute();
                float half = 0.5F;

                if (currentMinute == 30) {
                    currentHour = currentHour + half;
                }
                mainUser.currentDate = (mainUser.currentDate).plusMinutes(1);
            }
            else {
                // creating a variable to hold the updated time
                mainUser.currentDate = (mainUser.currentDate).plusMinutes(1);
            }

            if (mainUser.currentDate.getHour() == 23 && mainUser.currentDate.getMinute() == 59) {
                series.appendData(new DataPoint(currentDay, mainUser.currentCo2), true, x);
                mainUser.currentDataUseStandpoint = (float) 0;
                currentDay = currentDay + 1;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void userCo2GraphDay(){
        dSeries.resetData(new DataPoint[]{});
        mainUser.setCurrentDate();
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

                mainUser.currentDate = (mainUser.currentDate).plusMinutes(1);
            }
            else {
                // creating a variable to hold the updated time
                mainUser.currentDate = (mainUser.currentDate).plusMinutes(1);
            }
            if (mainUser.currentDate.getHour() == 23 && mainUser.currentDate.getMinute() == 59) {
                mainUser.currentDataUseStandpoint = (float) 0;
                mainUser.setCurrentDate();
                break;
            }
        }
        graph.addSeries(dSeries);
    }
}