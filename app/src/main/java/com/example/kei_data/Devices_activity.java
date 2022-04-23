package com.example.kei_data;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;

import android.view.View;
import android.view.ViewGroup;
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

        ArrayList<String> arrayListPrivate = new ArrayList<>();

        arrayListPrivate.add("Per's laptop");
        arrayListPrivate.add("Per's Iphone 11");
        arrayListPrivate.add("Per's bedroom TV");
        arrayListPrivate.add("Per's Ipad");

        ArrayList<String> arrayListShared = new ArrayList<>();

        arrayListShared.add("Livingroom TV");
        arrayListShared.add("Wifi speaker bathroom");
        arrayListShared.add("Kitchen TV");
        arrayListShared.add("Wifi speaker office");


        simpleListPrivate = (ListView) findViewById(R.id.simpleListViewPrivate);
        CustomAdapter customAdapterPrivat = new CustomAdapter(getApplicationContext(), arrayListPrivate, flags);
        simpleListPrivate.setMinimumHeight(justifyListViewHeightBasedOnChildren (simpleListPrivate,customAdapterPrivat));
        simpleListPrivate.setAdapter(customAdapterPrivat);

        simpleListShared = (ListView) findViewById(R.id.simpleListViewShared);
        CustomAdapter customAdapterShared = new CustomAdapter(getApplicationContext(), arrayListShared, flags);
        simpleListShared.setMinimumHeight(justifyListViewHeightBasedOnChildren (simpleListShared,customAdapterShared));
        simpleListShared.setAdapter(customAdapterShared);


    }

    public int justifyListViewHeightBasedOnChildren (ListView listView, CustomAdapter customAdapter) {

        CustomAdapter adapter = customAdapter;

        if (adapter == null) {
            return 0;
        }
        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
        return totalHeight;
    }

}