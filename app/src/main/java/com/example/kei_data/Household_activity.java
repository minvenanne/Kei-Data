package com.example.kei_data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Switch;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Household_activity extends AppCompatActivity {

    public ImageButton householdSettingsButton;
    public ImageButton householdCategoriesButton;

    BarGraphSeries<DataPoint> dSeriesHousehold;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household);

        Household testHousehold = (Household) getIntent().getSerializableExtra("household");
        User mainUser = (User) getIntent().getSerializableExtra("name");

        GraphView householdGraph = (GraphView) findViewById(R.id.householdGraph);
        dSeriesHousehold = new BarGraphSeries<DataPoint>();
        initGraphHousehold(householdGraph);
        for (int i = 0; i < testHousehold.userList.size(); i++) {
            // specifying the user
            User user = testHousehold.userList.get(i);


dSeriesHousehold = new BarGraphSeries<>(new DataPoint[] {

                    new DataPoint (user.userID, user.currentCo2)
            }
        }

//        Calendar calendar = Calendar.getInstance();
//
//        Date d1 = calendar.getTime();
//        calendar.add(Calendar.DATE, 1);
//        Date d2 = calendar.getTime();
//        calendar.add(Calendar.DATE, 1);
//        Date d3 = calendar.getTime();
//        calendar.add(Calendar.DATE, 1);
//        Date d4 = calendar.getTime();

        //Initilizing the householdGraph and calls the styling method -> initGraphHousehold




        // setNumHorizontalLabels determines the amount of labes on the y-axis that will be visible
        // setHumanRounding enables the rounding of the numbers on the x-axis
        // setNumVerticalLabels determines the amount of labes on the x-axis that will be visible
        householdGraph.getGridLabelRenderer().setNumHorizontalLabels(4);
        householdGraph.getGridLabelRenderer().setHumanRounding(false);
        householdGraph.getGridLabelRenderer().setNumVerticalLabels(5);






        householdGraph.addSeries(dSeriesHousehold);


        //Connects the radiobutton group with the onCheckedChange method.
        //When a radiobutton is checked, it removes the past series, and adds a new.
        RadioGroup householdRadioGroup = (RadioGroup) findViewById(R.id.householdRadioGroup);
        householdRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                if ( checkedId == R.id.householdRadioButton) {
                    Log.d("Success", "D was pressed");
                    householdGraph.removeAllSeries();
                    householdGraph.addSeries(dSeriesHousehold);
                    dSeriesHousehold.setAnimated(true);
                    calculateWinner();


                } else if (checkedId == R.id.householdRadioButton2) {
                    Log.d("Success", "W was pressed");
                    householdGraph.removeAllSeries();
                    householdGraph.addSeries(weekSeriesHousehold);
                    weekSeriesHousehold.setAnimated(true);

                } else if (checkedId == R.id.householdRadioButton3) {
                    Log.d("Success", "M was pressed");
                    householdGraph.removeAllSeries();
                    householdGraph.addSeries(mSeriesHousehold);
                    mSeriesHousehold.setAnimated(true);


                } else if (checkedId == R.id.householdRadioButton4) {
                    Log.d("Success", "6M was pressed");
                    householdGraph.removeAllSeries();
                    householdGraph.addSeries(sixMSeriesHousehold);
                    sixMSeriesHousehold.setAnimated(true);


                } else if (checkedId == R.id.householdRadioButton5) {
                    Log.d("Success", "Y was pressed");
                    householdGraph.removeAllSeries();
                    householdGraph.addSeries(ySeriesHousehold);
                    ySeriesHousehold.setAnimated(true);


                }


            }
        });


        Switch householdHouseholdSwitch = (Switch) findViewById(R.id.householdHousehold);
//            Lets the user switch between two modes.
        householdHouseholdSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (householdHouseholdSwitch.isChecked()) {
                    Log.d("Success", "Household");


                } else {
                    Log.d("Success", "you");
                    Intent intentHousehold = new Intent(Household_activity.this, MainActivity.class);
                    startActivity(intentHousehold);

                }
            }
        });
        //END// SWITCH BETWEEN YOU AND HOUSEHOLD

        householdSettingsButton = (ImageButton) findViewById(R.id.householdSettings);
        householdSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Household_activity.this, Settings_activity1.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", getIntent().getSerializableExtra("user"));
                startActivity(intent);
            }
        });

        householdCategoriesButton = (ImageButton) findViewById(R.id.householdCategories);
        householdCategoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Household_activity.this, Categories_activity.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", getIntent().getSerializableExtra("user"));
                startActivity(intent);
            }
        });


    }


    //Creates the different datasets for the graph.



    BarGraphSeries<DataPoint> weekSeriesHousehold = new BarGraphSeries<>(new DataPoint[] {
            new DataPoint(0, 2),
            new DataPoint(1, 5),
            new DataPoint(2, 3),
            new DataPoint(3, 2),
            new DataPoint(4, 6)
    });

    BarGraphSeries<DataPoint> mSeriesHousehold = new BarGraphSeries<>(new DataPoint[] {
            new DataPoint(0, 0),
            new DataPoint(1, 5),
            new DataPoint(2, 3),
            new DataPoint(3, 2),
            new DataPoint(4, 6)
    });
    BarGraphSeries<DataPoint> sixMSeriesHousehold = new BarGraphSeries<>(new DataPoint[] {
            new DataPoint(0, 10),
            new DataPoint(1, 5),
            new DataPoint(2, 3),
            new DataPoint(3, 2),
            new DataPoint(4, 6)
    });

    BarGraphSeries<DataPoint> ySeriesHousehold = new BarGraphSeries<>(new DataPoint[] {
            new DataPoint(0, 12),
            new DataPoint(1, 5),
            new DataPoint(2, 3),
            new DataPoint(3, 2),
            new DataPoint(4, 6)
    });

    //Styling of the graph
    public void initGraphHousehold(GraphView householdGraph){
        //Colors of the the graph
        dSeriesHousehold.setColor(Color.rgb(120,150,111));
        weekSeriesHousehold.setColor(Color.rgb(120,150,111));
        mSeriesHousehold.setColor(Color.rgb(120,150,111));
        sixMSeriesHousehold.setColor(Color.rgb(120,150,111));
        ySeriesHousehold.setColor(Color.rgb(120,150,111));

        //Thickness of graph
        //dSeriesHousehold.setSpacing(50);
        weekSeriesHousehold.setSpacing(25);
        mSeriesHousehold.setSpacing(25);
        sixMSeriesHousehold.setSpacing(25);
        ySeriesHousehold.setSpacing(25);

        dSeriesHousehold.setDataWidth(0.5);










    }





    public void calculateWinner() {

        



        dSeriesHousehold.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {

                Log.d("Graph", "Y is " + String.valueOf(data.getY()));
                Log.d("Graph", "Lowest Y is " + dSeriesHousehold.getLowestValueY());
                dSeriesHousehold.setDrawValuesOnTop(true);


                return Color.rgb((int) data.getX()*255/4, (int) Math.abs((data.getY()*10)-255), 50);


            }


    });


}
}