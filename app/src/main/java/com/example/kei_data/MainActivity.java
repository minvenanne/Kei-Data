package com.example.kei_data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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

        //TEST//
        Household testHousehold = new Household();
        testHousehold.setCurrentUserName("Roy Hudson");
        testHousehold.familyName = "Hudson";
        /*testHousehold.addUser("Camilla");
        testHousehold.addRouter(1234);
        testHousehold.removeUser("Klaus");*/


        //END TEST//

        GraphView graph = (GraphView) findViewById(R.id.graph);
        initGraph(graph);

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


        graph.addSeries(dSeries);

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


        Switch householdSwitch = (Switch) findViewById(R.id.household);
//            Lets the user switch between two modes.
        householdSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (householdSwitch.isChecked()) {
                    Log.d("Success", "Household");
                    Intent intent = new Intent(MainActivity.this, Household_activity.class);
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
                startActivity(intent);
            }
        });

        categoriesButton = (ImageButton) findViewById(R.id.Categories);
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Categories_activity.class);
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

                DataUse data = new DataUse(tokens[0], tokens[1], LocalDateTime.parseCase
                        (tokens[2]), Float.parseFloat(tokens[3]), tokens[4]);
                System.out.println(data.getDataUsageAmount());
                /*data.setDataUsageID();
                data.setDataUsageDeviceType(tokens[1]);
                data.setDataUsageTimeSlot(tokens[2]);
                data.setDataUsageAmount(Integer.parseInt(tokens[3]));
                data.setDataUsageType(tokens[4]);*/
                if (device.getDeviceType().equals(data.getDataUsageDeviceType())){
                    device.addDataUse(data);
                }
                else {
                    System.out.println("IKKE TILFØJET");
                    System.out.println("ID: " + data.getDataUsageID());
                    System.out.println("Device Type:" + data.getDataUsageDeviceType());
                }
            }

        } catch (IOException e) {
            Log.wtf("Main Activity", "Error Reading Data File on line" + line, e);
            e.printStackTrace();
        }
    }

    //Creates the different datasets for the graph.

    LineGraphSeries<DataPoint> dSeries = new LineGraphSeries<>(new DataPoint[] {
            new DataPoint (new Date().getTime(), 42.9),
            new DataPoint (new Date().getTime(), 68.5),
            new DataPoint (new Date().getTime(), 147),
            new DataPoint (new Date().getTime(), 50),
    });

    BarGraphSeries<DataPoint> weekSeries = new BarGraphSeries<>(new DataPoint[] {
            new DataPoint(0, -2),
            new DataPoint(1, 5),
            new DataPoint(2, 3),
            new DataPoint(3, 2),
            new DataPoint(4, 6)
    });

    BarGraphSeries<DataPoint> mSeries = new BarGraphSeries<>(new DataPoint[] {
            new DataPoint(0, 0),
            new DataPoint(1, 5),
            new DataPoint(2, 3),
            new DataPoint(3, 2),
            new DataPoint(4, 6)
    });
    BarGraphSeries<DataPoint> sixMSeries = new BarGraphSeries<>(new DataPoint[] {
            new DataPoint(0, -10),
            new DataPoint(1, 5),
            new DataPoint(2, 3),
            new DataPoint(3, 2),
            new DataPoint(4, 6)
    });

    BarGraphSeries<DataPoint> ySeries = new BarGraphSeries<>(new DataPoint[] {
            new DataPoint(0, 12),
            new DataPoint(1, 5),
            new DataPoint(2, 3),
            new DataPoint(3, 2),
            new DataPoint(4, 6)
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









}