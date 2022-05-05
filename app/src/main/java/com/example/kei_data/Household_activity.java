package com.example.kei_data;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
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
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
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


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household);

        Household testHousehold = (Household) getIntent().getSerializableExtra("household");
        User mainUser = (User) getIntent().getSerializableExtra("name");

        Switch householdHouseholdSwitch = (Switch) findViewById(R.id.householdHousehold);
//            Lets the user switch between two modes.
        householdHouseholdSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (householdHouseholdSwitch.isChecked()) {
                    Log.d("Success", "Household");


                } else {
                    Log.d("Success", "you");
                    Intent intent = new Intent(Household_activity.this, MainActivity.class);
                    intent.putExtra("household", getIntent().getSerializableExtra("household"));
                    intent.putExtra("user", getIntent().getSerializableExtra("user"));
                    startActivity(intent);

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

        GraphView householdGraph = (GraphView) findViewById(R.id.householdGraph);

        dSeriesHousehold = new BarGraphSeries<DataPoint>();
        initGraphHousehold(householdGraph);
        householdGraph.getGridLabelRenderer().setNumHorizontalLabels(testHousehold.userList.size());
        householdGraph.getGridLabelRenderer().setHumanRounding(false);
        householdGraph.getGridLabelRenderer().setNumVerticalLabels(5);
        householdGraph.getViewport().setMinY(1750);
        householdGraph.getViewport().setMaxY(3000);
        //householdGraph.getViewport().setMaxX(5.4);
       // householdGraph.getViewport().setMinX(-0.3);
       // householdGraph.getViewport().setXAxisBoundsManual(true);
        householdGraph.getViewport().setYAxisBoundsManual(true);

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(householdGraph);
        staticLabelsFormatter.setHorizontalLabels(testHousehold.getArraylistOfUserName());
        householdGraph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        householdGraph.getGridLabelRenderer().setHorizontalLabelsAngle(140);

        for (int i = 0; i < testHousehold.userList.size(); i++) {
            // specifying the user
            User user = testHousehold.userList.get(i);
            dSeriesHousehold.appendData(new DataPoint(i, user.currentCo2), true, testHousehold.userList.size());
        }
        householdGraph.addSeries(dSeriesHousehold);


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


        //Connects the radiobutton group with the onCheckedChange method.
        //When a radiobutton is checked, it removes the past series, and adds a new.
        RadioGroup householdRadioGroup = (RadioGroup) findViewById(R.id.householdRadioGroup);
        householdRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                if (checkedId == R.id.householdRadioButton) {
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

    }


    //Creates the different datasets for the graph.


// der rydes op i de her grafer
    BarGraphSeries<DataPoint> weekSeriesHousehold = new BarGraphSeries<>(new DataPoint[] {
            new DataPoint(0, 13000),
            new DataPoint(1, 11000),
            new DataPoint(2, 10000),
            new DataPoint(3, 10000),
            new DataPoint(4, 11000),
            new DataPoint(5, 12000)
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
        dSeriesHousehold.setSpacing(-30);
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