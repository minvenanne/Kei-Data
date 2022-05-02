package com.example.kei_data;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddDeviceIP_activity extends AppCompatActivity {

    public ListView devices_available;
    public Button Add_device;
    public Button back;
    public static String ip;
    public static int clickedButton;
    public ImageButton settingsButton;
    public ImageButton homeButton;
    public ImageButton categoriesButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddeviceip);

        settingsButton = (ImageButton) findViewById(R.id.Settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceIP_activity.this, Settings_activity1.class);
                startActivity(intent);
            }
        });

        homeButton = (ImageButton) findViewById(R.id.Total);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceIP_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        categoriesButton = (ImageButton) findViewById(R.id.Categories);
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceIP_activity.this, Categories_activity.class);
                startActivity(intent);
            }
        });

        ImageButton delete = (ImageButton) findViewById(R.id.list_view_trashcan);
        devices_available = (ListView) findViewById(R.id.device_address);
        ArrayAdapter<ArrayList> AdapterDevices = new ArrayAdapter(getApplicationContext(), R.layout.activity_listview_ip, R.id.adresse, findIPArrayList());
        devices_available.setMinimumHeight(justifyListViewHeightBasedOnChildren(devices_available, AdapterDevices));
        devices_available.setAdapter(AdapterDevices);


        //set ip equal to the selected one.
        devices_available.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                clickedButton = position;
                Add_device.setEnabled(true);
                ip = findIPArrayList().get(clickedButton);
                setBackground(position, view);
            }
        });

        Add_device = (Button) findViewById(R.id.Next2);
        Add_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceIP_activity.this, AddDeviceName_activity.class);
                startActivity(intent);
            }
        });

        back = (Button) findViewById(R.id.Back2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceIP_activity.this, AddDeviceType_activity.class);
                startActivity(intent);
            }
        });

    }

    //https://stackoverflow.com/questions/12212890/disable-scrolling-of-a-listview-contained-within-a-scrollview/27818661#27818661

    private int justifyListViewHeightBasedOnChildren(ListView listView, ArrayAdapter adapter) {

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
    public static String getDeviceIp(){
        return ip;
    }

    public static void setBackground(int position, View view){
        if(position!=clickedButton)
        {
            view.setBackgroundResource(R.drawable.round_corners);
        }
        else
        {
            view.setBackgroundResource(R.drawable.radiobutton1_selected);
        }
    }

    public ArrayList<String> findIPArrayList(){
        String type = AddDeviceType_activity.getDeviceType();
        ArrayList<String> arrayList = new ArrayList<>();
        if (type.equals("Computer")){
            arrayList.add("726.371.91.24");
            arrayList.add("637.392.34.22");
            arrayList.add("273.384.21.78");
            arrayList.add("273.293.23.38");
            arrayList.add("293.234.21.95");
            return arrayList;
        }
        else if(type.equals("Phone")){
            arrayList.add("637.823.92.09");
            arrayList.add("283.384.13.94");
            arrayList.add("918.294.14.98");
            arrayList.add("283.942.24.63");
            arrayList.add("283.298.32.74");
            return arrayList;
        }

        else if(type.equals("Speaker")){
            arrayList.add("723.182.12.61");
            arrayList.add("928.342.24.23");
            arrayList.add("823.273.24.21");
            arrayList.add("923.73.14.91");
            arrayList.add("283.942.24.42");
            arrayList.add("283.298.32.96");
            return arrayList;
        }

        else if(type.equals("TV")){
            arrayList.add("323.912.22.61");
            arrayList.add("728.341.94.43");
            arrayList.add("103.523.54.51");
            arrayList.add("243.142.24.42");
            arrayList.add("133.218.22.96");
            return arrayList;
        }

        else if(type.equals("Other")){
            arrayList.add("323.112.22.71");
            arrayList.add("303.533.54.51");
            arrayList.add("253.12.24.42");
            arrayList.add("133.28.22.926");
            return arrayList;
        }
        else{
            return null;
        }
    }
}