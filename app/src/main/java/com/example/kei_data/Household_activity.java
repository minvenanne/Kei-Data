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
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LabelFormatter;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

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


                } else {
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

        //StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(householdGraph);
        //householdGraph.setLabelFor(R.id.householdGraph);
        //householdGraph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);


        for (int i = 0; i < testHousehold.userList.size(); i++) {
            // specifying the user
            User user = testHousehold.userList.get(i);
            dSeriesHousehold.appendData(new DataPoint(i, user.currentCo2), false, testHousehold.userList.size());
            System.out.println(i + " med værdien " + user.currentCo2 + " the name is " + user.userName);
        }

        householdGraph.addSeries(dSeriesHousehold);
        initGraphHousehold(householdGraph);
        calculateWinner();
        householdGraph.getGridLabelRenderer().setNumHorizontalLabels(testHousehold.userList.size());
        householdGraph.getGridLabelRenderer().setHumanRounding(true);
        householdGraph.getGridLabelRenderer().setNumVerticalLabels(6);
        householdGraph.getViewport().setMinY(dSeriesHousehold.getLowestValueY() - 250);
        householdGraph.getViewport().setMaxY(dSeriesHousehold.getHighestValueY() + 250);
        householdGraph.getViewport().setYAxisBoundsManual(true);
        dSeriesHousehold.setDrawValuesOnTop(true);
        dSeriesHousehold.setValuesOnTopColor(Color.BLACK);

        householdGraph.getGridLabelRenderer().setLabelFormatter(new LabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                String[] names = testHousehold.getArraylistOfUserName();
                String name;
                System.out.print(value);
                System.out.println(isValueX);
                if (value < testHousehold.userList.size()){
                    name = names[(int) value];
                    System.out.println(value + " tilhører " + name);
                    return name;
                }
                else {
                    return String.valueOf((int)value);
                }
            }

            @Override
            public void setViewport(Viewport viewport) {

            }
        });
        householdGraph.getGridLabelRenderer().setHorizontalLabelsAngle(115);


        TextView CO2Number = findViewById(R.id.householdNumber);
        CO2Number.setText(Math.round(testHousehold.userList.get(0).currentCo2) + " g CO2 / " + Math.round(testHousehold.userList.get(0).currentKM) + " km in a car");

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
                    householdGraph.removeAllSeries();
                    householdGraph.addSeries(dSeriesHousehold);
                    calculateWinner();
                    initGraphHousehold(householdGraph);
                    householdGraph.getGridLabelRenderer().setNumHorizontalLabels(testHousehold.userList.size());
                    householdGraph.getGridLabelRenderer().setHumanRounding(true);
                    householdGraph.getGridLabelRenderer().setNumVerticalLabels(6);
                    householdGraph.getViewport().setMinY(dSeriesHousehold.getLowestValueY() - 250);
                    householdGraph.getViewport().setMaxY(dSeriesHousehold.getHighestValueY() + 250);
                    householdGraph.getViewport().setYAxisBoundsManual(true);
                    dSeriesHousehold.setDrawValuesOnTop(true);
                    dSeriesHousehold.setValuesOnTopColor(Color.BLACK);

                    householdGraph.getGridLabelRenderer().setLabelFormatter(new LabelFormatter() {
                        @Override
                        public String formatLabel(double value, boolean isValueX) {
                            String[] names = testHousehold.getArraylistOfUserName();
                            String name;
                            System.out.print(value);
                            System.out.println(isValueX);
                            if (value < testHousehold.userList.size()){
                                name = names[(int) value];
                                System.out.println(value + " tilhører " + name);
                                return name;
                            }
                            else {
                                return String.valueOf((int)value);
                            }
                        }

                        @Override
                        public void setViewport(Viewport viewport) {

                        }
                    });
                    householdGraph.getGridLabelRenderer().setHorizontalLabelsAngle(115);
                    householdGraph.getGridLabelRenderer().setHorizontalLabelsVisible(true);

                } else if (checkedId == R.id.householdRadioButton2) {
                    householdGraph.removeAllSeries();
                    householdGraph.addSeries(weekSeriesHousehold);
                    initGraphHousehold(householdGraph);
                    weekSeriesHousehold.setAnimated(true);
                    householdGraph.getViewport().setMinY(weekSeriesHousehold.getLowestValueY() - 250);
                    householdGraph.getViewport().setMaxY(weekSeriesHousehold.getHighestValueY() + 250);
                    householdGraph.getViewport().setYAxisBoundsManual(true);
                    householdGraph.getGridLabelRenderer().setHorizontalLabelsVisible(false);

                } else if (checkedId == R.id.householdRadioButton3) {
                    householdGraph.removeAllSeries();
                    householdGraph.addSeries(mSeriesHousehold);
                    initGraphHousehold(householdGraph);
                    mSeriesHousehold.setAnimated(true);
                    householdGraph.getViewport().setMinY(mSeriesHousehold.getLowestValueY() - 250);
                    householdGraph.getViewport().setMaxY(mSeriesHousehold.getHighestValueY() + 250);
                    householdGraph.getViewport().setYAxisBoundsManual(true);
                    householdGraph.getGridLabelRenderer().setHorizontalLabelsVisible(false);


                } else if (checkedId == R.id.householdRadioButton4) {
                    householdGraph.removeAllSeries();
                    householdGraph.addSeries(sixMSeriesHousehold);
                    sixMSeriesHousehold.setAnimated(true);

                } else if (checkedId == R.id.householdRadioButton5) {
                    householdGraph.removeAllSeries();
                    householdGraph.addSeries(ySeriesHousehold);
                    ySeriesHousehold.setAnimated(true);
                }


            }
        });

    }


    //Creates the different datasets for the graph.


    // der rydes op i de her grafer
    BarGraphSeries<DataPoint> weekSeriesHousehold = new BarGraphSeries<>(new DataPoint[]{
            new DataPoint(0, 2100),
            new DataPoint(1, 3200),
            new DataPoint(2, 2300),
            new DataPoint(3, 2450),
            new DataPoint(4, 3120),
            new DataPoint(5, 2800),
            new DataPoint(6, 2300)
    });

    BarGraphSeries<DataPoint> mSeriesHousehold = new BarGraphSeries<>(new DataPoint[]{
            new DataPoint(0, 2100),
            new DataPoint(1, 3200),
            new DataPoint(2, 2300),
            new DataPoint(3, 2450),
            new DataPoint(4, 3120),
            new DataPoint(5, 2800),
            new DataPoint(6, 2140),
            new DataPoint(7, 3240),
            new DataPoint(8, 2120),
            new DataPoint(9, 2950),
            new DataPoint(10, 3240),
            new DataPoint(11, 3400),
            new DataPoint(12, 2100),
            new DataPoint(13, 3200),
            new DataPoint(14, 2300),
            new DataPoint(15, 2450),
            new DataPoint(16, 3120),
            new DataPoint(17, 2800),
            new DataPoint(18, 2140),
            new DataPoint(19, 3240),
            new DataPoint(20, 2120),
            new DataPoint(21, 2950),
            new DataPoint(22, 3240),
            new DataPoint(23, 3400),
            new DataPoint(24, 2140),
            new DataPoint(25, 3240),
            new DataPoint(26, 2120),
            new DataPoint(27, 2950),
            new DataPoint(28, 3240),
            new DataPoint(29, 3400),
            new DataPoint(30, 2100),
            new DataPoint(31, 3200)
    });
    BarGraphSeries<DataPoint> sixMSeriesHousehold = new BarGraphSeries<>(new DataPoint[]{
            new DataPoint(0, 10),
            new DataPoint(1, 5),
            new DataPoint(2, 3),
            new DataPoint(3, 2),
            new DataPoint(4, 6)
    });

    BarGraphSeries<DataPoint> ySeriesHousehold = new BarGraphSeries<>(new DataPoint[]{
            new DataPoint(0, 12),
            new DataPoint(1, 5),
            new DataPoint(2, 3),
            new DataPoint(3, 2),
            new DataPoint(4, 6)
    });

    //Styling of the graph
    public void initGraphHousehold(GraphView householdGraph) {
        //Colors of the the graph
        dSeriesHousehold.setColor(Color.rgb(120, 150, 111));
        weekSeriesHousehold.setColor(Color.rgb(120, 150, 111));
        mSeriesHousehold.setColor(Color.rgb(120, 150, 111));
        sixMSeriesHousehold.setColor(Color.rgb(120, 150, 111));
        ySeriesHousehold.setColor(Color.rgb(120, 150, 111));

        //Thickness of graph
        dSeriesHousehold.setSpacing(25);
        weekSeriesHousehold.setSpacing(25);
        mSeriesHousehold.setSpacing(25);
        sixMSeriesHousehold.setSpacing(25);
        ySeriesHousehold.setSpacing(25);

        dSeriesHousehold.setDataWidth(1);


    }


    public void calculateWinner() {


        dSeriesHousehold.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public int get(DataPoint data) {
                if (data.getY() == dSeriesHousehold.getLowestValueY()){
                    return Color.rgb(103,205,87);
                }
                else if (data.getY() == dSeriesHousehold.getHighestValueY()){
                    return Color.rgb(240,21,36);
                }
                else{
                    return Color.rgb(255,235,69);
                }

                //dSeriesHousehold.setDrawValuesOnTop(true);

                //return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 50);

            }


        });

    }
}