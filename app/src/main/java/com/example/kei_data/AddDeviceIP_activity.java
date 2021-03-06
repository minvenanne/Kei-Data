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
    public Button Next2;
    public Button back;
    public static String ip;
    public static int clickedButton = -1;
    public ImageButton settingsButton;
    public ImageButton homeButton;
    public ImageButton categoriesButton;
    public ArrayList<String> arrayList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddeviceip);
        User mainUser = (User) getIntent().getSerializableExtra("user");

        settingsButton = (ImageButton) findViewById(R.id.Settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceIP_activity.this, Settings_activity1.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", getIntent().getSerializableExtra("user"));
                startActivity(intent);
            }
        });

        homeButton = (ImageButton) findViewById(R.id.Total);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceIP_activity.this, MainActivity.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", getIntent().getSerializableExtra("user"));
                startActivity(intent);
            }
        });

        categoriesButton = (ImageButton) findViewById(R.id.Categories);
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceIP_activity.this, Categories_activity.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", getIntent().getSerializableExtra("user"));
                startActivity(intent);
            }
        });

        devices_available = (ListView) findViewById(R.id.device_address);
        if (arrayList == null){
            arrayList = findIPArrayList();
        }
        ArrayAdapter<ArrayList> AdapterDevices = new ArrayAdapter(getApplicationContext(), R.layout.activity_listview_ip, R.id.adresse, arrayList);
        devices_available.setMinimumHeight(justifyListViewHeightBasedOnChildren(devices_available, AdapterDevices));
        devices_available.setAdapter(AdapterDevices);

        //set ip equal to the selected one.
        devices_available.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for (int i = 0; i < devices_available.getChildCount(); i++) {
                    if(position == i ){
                        devices_available.getChildAt(i).setBackgroundResource(R.drawable.radiobutton1_selected);
                    }else{
                        devices_available.getChildAt(i).setBackgroundResource(R.drawable.round_corners);
                    }
                }
                clickedButton = position;
                Next2.setEnabled(true);
                ip = arrayList.get(position);
                AdapterDevices.notifyDataSetChanged();
            }
        });

        Next2 = (Button) findViewById(R.id.Next2);
        Next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceIP_activity.this, AddDeviceName_activity.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", mainUser);
                startActivity(intent);
            }
        });

        back = (Button) findViewById(R.id.Back2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceIP_activity.this, AddDeviceType_activity.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", getIntent().getSerializableExtra("user"));
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

    public ArrayList<String> findIPArrayList() {
        String type = AddDeviceType_activity.getDeviceType();
        if (type.equals("Computer")) {
            return arrayListComputer();
        }
        else if (type.equals("Phone")) {
            return arrayListPhone();
        }
        else if (type.equals("Speaker")) {
            return arrayListSpeaker();
        }
        else if (type.equals("TV")) {
            return arrayListTV();
        }
        else if (type.equals("Other")) {
            return arrayListOther();
        }
        else {
            return null;
        }
    }

    public ArrayList<String> arrayListComputer(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("126.251.91.24");
        arrayList.add("37.32.234.22");
        arrayList.add("243.184.21.178");
        arrayList.add("273.23.23.138");
        arrayList.add("23.234.121.95");
        return arrayList;
    }

    public ArrayList<String> arrayListPhone(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("37.123.92.39");
        arrayList.add("213.184.13.94");
        arrayList.add("18.94.114.198");
        arrayList.add("23.242.24.263");
        arrayList.add("235.28.32.74");
        return arrayList;
    }

    public ArrayList<String> arrayListSpeaker(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("123.182.12.61");
        arrayList.add("28.142.24.23");
        arrayList.add("123.223.24.121");
        arrayList.add("242.73.14.91");
        arrayList.add("213.942.24.42");
        arrayList.add("183.28.232.96");
        return arrayList;
    }

    public ArrayList<String> arrayListTV(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("132.12.122.261");
        arrayList.add("28.141.94.243");
        arrayList.add("103.123.54.51");
        arrayList.add("243.142.24.42");
        arrayList.add("133.218.22.96");
        return arrayList;
    }

    public ArrayList<String> arrayListOther(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("123.112.22.71");
        arrayList.add("103.233.54.51");
        arrayList.add("253.12.224.42");
        arrayList.add("133.28.22.226");
        return arrayList;
    }
}