package com.example.kei_data;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;

import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Devices_activity extends AppCompatActivity{
    public ImageButton settingsButton;
    public ImageButton homeButton;
    public ImageButton categoriesButton;
    public Button addDevice;
    ListView simpleListPrivate;
    ListView simpleListShared;


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

        addDevice = (Button) findViewById(R.id.add_device);
        addDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Devices_activity.this, AddDevice_activity.class);
                startActivity(intent);
            }
        });

        ArrayList<String> arrayListPrivate = new ArrayList<>();
        ArrayList<String> typePrivate = new ArrayList<>();

        arrayListPrivate.add("Per's laptop");
        typePrivate.add("Computer");
        arrayListPrivate.add("Per's Iphone 11");
        typePrivate.add("Phone");
        arrayListPrivate.add("Per's bedroom TV");
        typePrivate.add("TV");
        arrayListPrivate.add("Per's Ipad");
        typePrivate.add("Other");
        arrayListPrivate.add("Wifi speaker bedroom");
        typePrivate.add("Speaker");

        ArrayList<Integer> iconsPrivate = turnTypeIntoIcon(typePrivate);

        ArrayList<String> arrayListShared = new ArrayList<>();
        ArrayList<String> typeShared = new ArrayList<>();

        arrayListShared.add("Livingroom TV");
        typeShared.add("TV");
        arrayListShared.add("Wifi speaker bathroom");
        typeShared.add("Speaker");
        arrayListShared.add("Kitchen TV");
        typeShared.add("TV");
        arrayListShared.add("Wifi speaker office");
        typeShared.add("Speaker");
        arrayListShared.add("Shared Phone");
        typeShared.add("Phone");

        ArrayList<Integer> iconsShared = turnTypeIntoIcon(typeShared);
        ImageButton delete = (ImageButton) findViewById(R.id.list_view_trashcan);

        simpleListPrivate = (ListView) findViewById(R.id.simpleListViewPrivate);
        CustomAdapter customAdapterPrivat = new CustomAdapter(getApplicationContext(), arrayListPrivate, iconsPrivate, delete);
        simpleListPrivate.setMinimumHeight(justifyListViewHeightBasedOnChildren (simpleListPrivate,customAdapterPrivat));
        simpleListPrivate.setAdapter(customAdapterPrivat);

        simpleListShared = (ListView) findViewById(R.id.simpleListViewShared);
        CustomAdapter customAdapterShared = new CustomAdapter(getApplicationContext(), arrayListShared, iconsShared, delete);
        simpleListShared.setMinimumHeight(justifyListViewHeightBasedOnChildren (simpleListShared,customAdapterShared));
        simpleListShared.setAdapter(customAdapterShared);
    }

    //https://stackoverflow.com/questions/12212890/disable-scrolling-of-a-listview-contained-within-a-scrollview/27818661#27818661

    private int justifyListViewHeightBasedOnChildren (ListView listView, CustomAdapter customAdapter) {

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

    public ArrayList<Integer> turnTypeIntoIcon(ArrayList type){
        ArrayList<String> arraylist = type;
        ArrayList<Integer> icons = new ArrayList<>();

        for (int i = 0; i < arraylist.size(); i++){
            if (arraylist.get(i) == "TV"){
                icons.add(i, R.drawable.ic_baseline_tv_24);
            }
            else if (arraylist.get(i) == "Speaker") {
                icons.add(i, R.drawable.ic_baseline_speaker_24);
            }
            else if (arraylist.get(i) == "Phone"){
                icons.add(i, R.drawable.ic_baseline_smartphone_24);
            }
            else if (arraylist.get(i) == "Computer"){
                icons.add(i, R.drawable.ic_baseline_computer_24);
            }
            else if (arraylist.get(i) == "Other"){
                icons.add(i, R.drawable.ic_baseline_devices_other_24);
            }
        }
        return icons;
    }

}