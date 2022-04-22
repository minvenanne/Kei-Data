package com.example.kei_data;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


public class Devices_activity extends AppCompatActivity{
    public ImageButton settingsButton;
    public ImageButton homeButton;
    public ImageButton categoriesButton;
    ListView privateDevices;
    ListView sharedDevices;
    ListView simpleListPrivate;
    ListView simpleListShared;
    String countryList[] = {"India", "China", "australia", "Portugal"};
    int flags[] = {R.drawable.ic_user, R.drawable.ic_baseline_work_24, R.drawable.ic_baseline_sentiment_very_satisfied_24, R.drawable.ic_settings};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);

        settingsButton = (ImageButton) findViewById(R.id.Settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Devices_activity.this, Settings_activity1.class);
                startActivity(intent);
            }
        });

        homeButton = (ImageButton) findViewById(R.id.Total);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Devices_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        categoriesButton = (ImageButton) findViewById(R.id.Categories);
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Devices_activity.this, Categories_activity.class);
                startActivity(intent);
            }
        });

        privateDevices = (ListView) findViewById(R.id.simpleListViewPrivate);
        ArrayList<String> arrayListPrivate = new ArrayList<>();

        arrayListPrivate.add("Per's laptop");
        arrayListPrivate.add("Per's Iphone 11");
        arrayListPrivate.add("Per's bedroom TV");
        arrayListPrivate.add("Per's Ipad");

        ArrayAdapter arrayAdapterPrivate = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayListPrivate);

        privateDevices.setAdapter(arrayAdapterPrivate);

        sharedDevices = (ListView) findViewById(R.id.simpleListViewShared);
        ArrayList<String> arrayListShared = new ArrayList<>();


        arrayListShared.add("Livingroom TV");
        arrayListShared.add("Wifi speaker bathroom");
        arrayListShared.add("Kitchen TV");
        arrayListShared.add("Wifi speaker office");


        ArrayAdapter arrayAdapterShared = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayListShared);

        sharedDevices.setAdapter(arrayAdapterShared);

        simpleListPrivate = (ListView) findViewById(R.id.simpleListViewPrivate);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), arrayListPrivate, flags);
        simpleListPrivate.setAdapter(customAdapter);
    }
}