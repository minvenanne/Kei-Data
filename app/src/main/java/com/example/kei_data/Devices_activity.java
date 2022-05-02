package com.example.kei_data;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class Devices_activity extends AppCompatActivity{
    public ImageButton settingsButton;
    public ImageButton homeButton;
    public ImageButton categoriesButton;
    public Button addDevice;
    public static ListView simpleListPrivate;
    public static ListView simpleListShared;
    public static ArrayList<String> arrayListShared = new ArrayList<>();
    public static ArrayList<String> typeShared = new ArrayList<>();
    ArrayList<Integer> iconsShared = new ArrayList<>();
    public static CustomAdapterDevicesPrivate customAdapterPrivat;
    public static CustomAdapterDevicesShared customAdapterShared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);

        if (User.deviceList.size()==0){
            addElementsToArrayPrivate();
        }

        if (arrayListShared.size()==0){
            addElementsToArrayShared();
        }

        AddDeviceType_activity.setCheckedButton();

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
                Intent intent = new Intent(Devices_activity.this, AddDeviceType_activity.class);
                startActivity(intent);
            }
        });

        ArrayList<Integer> iconsPrivate = turnTypeIntoIcon(User.deviceList);

        ImageButton delete = (ImageButton) findViewById(R.id.list_view_trashcan);

        simpleListPrivate = (ListView) findViewById(R.id.simpleListViewPrivate);
        customAdapterPrivat = new CustomAdapterDevicesPrivate(getApplicationContext(), User.deviceList, iconsPrivate, delete);
        simpleListPrivate.setMinimumHeight(justifyListViewHeightBasedOnChildrenPrivate(simpleListPrivate,customAdapterPrivat));
        simpleListPrivate.setAdapter(customAdapterPrivat);

        simpleListShared = (ListView) findViewById(R.id.simpleListViewShared);
        customAdapterShared = new CustomAdapterDevicesShared(getApplicationContext(), arrayListShared, iconsShared, delete);
        simpleListShared.setMinimumHeight(justifyListViewHeightBasedOnChildrenShared(simpleListShared, customAdapterShared));
        simpleListShared.setAdapter(customAdapterShared);
    }

    //https://stackoverflow.com/questions/12212890/disable-scrolling-of-a-listview-contained-within-a-scrollview/27818661#27818661
    public static int justifyListViewHeightBasedOnChildrenPrivate (ListView listView, CustomAdapterDevicesPrivate customAdapterDevices) {

        CustomAdapterDevicesPrivate adapter = customAdapterDevices;

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

    public static int justifyListViewHeightBasedOnChildrenShared (ListView listView, CustomAdapterDevicesShared customAdapterDevices) {

        CustomAdapterDevicesShared adapter = customAdapterDevices;

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

    //Creates arraylist of icons
    public ArrayList<Integer> turnTypeIntoIcon(ArrayList<Device> device){
        ArrayList<Integer> icons = new ArrayList<>();

        for (int i = 0; i < device.size(); i++){
            if (device.get(i).deviceType == ("TV")){
                icons.add(i, R.drawable.ic_baseline_tv_24);
            }
            else if (device.get(i).deviceType.equals("Speaker")) {
                icons.add(i, R.drawable.ic_baseline_speaker_24);
            }
            else if (device.get(i).deviceType.equals("Phone")){
                icons.add(i, R.drawable.ic_baseline_smartphone_24);
            }
            else if (device.get(i).deviceType.equals("Computer")){
                icons.add(i, R.drawable.ic_baseline_computer_24);
            }
            else if (device.get(i).deviceType.equals("Other")){
                icons.add(i, R.drawable.ic_baseline_devices_other_24);
            }
        }
        return icons;
    }

    private void addElementsToArrayPrivate(){
        User.addDevice("Computer", "345.982.41", "Per's laptop");
        User.addDevice("Phone", "584.682.91", "Per's Iphone 11");
        User.addDevice("TV", "675.892.34", "Per's bedroom TV");
        User.addDevice("Other", "565.875.32", "Per's Ipad");
        User.addDevice("Speaker", "623.769.99", "Per's wifi speaker bedroom");
    }

    // hardcoded liste af shared devices
    private void addElementsToArrayShared(){
        arrayListShared.add("Livingroom TV");
        typeShared.add("TV");
        iconsShared.add(R.drawable.ic_baseline_tv_24);
        arrayListShared.add("Wifi speaker bathroom");
        typeShared.add("Speaker");
        iconsShared.add(R.drawable.ic_baseline_speaker_24);
        arrayListShared.add("Kitchen TV");
        typeShared.add("TV");
        iconsShared.add(R.drawable.ic_baseline_tv_24);
        arrayListShared.add("Wifi speaker office");
        typeShared.add("Speaker");
        iconsShared.add(R.drawable.ic_baseline_speaker_24);
        arrayListShared.add("Shared Phone");
        typeShared.add("Phone");
        iconsShared.add(R.drawable.ic_baseline_smartphone_24);
    }

}