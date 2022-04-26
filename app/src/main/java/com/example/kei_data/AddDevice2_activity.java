package com.example.kei_data;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AddDevice2_activity extends AppCompatActivity {

    public ListView devices_available;
    public Button Add_device;
    public Button back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddevice2);

        ArrayList<String> IP_address = new ArrayList<>();

        IP_address.add("726.371.91");
        IP_address.add("637.392.34");
        IP_address.add("273.384.21");
        IP_address.add("273.293.23");
        IP_address.add("293.234.21");

        ArrayList<Integer> Image = new ArrayList<>();
        Image.add(R.drawable.ic_baseline_smartphone_24);
        Image.add(R.drawable.ic_baseline_smartphone_24);
        Image.add(R.drawable.ic_baseline_smartphone_24);
        Image.add(R.drawable.ic_baseline_smartphone_24);
        Image.add(R.drawable.ic_baseline_smartphone_24);

        ImageButton delete = (ImageButton) findViewById(R.id.list_view_trashcan);
        devices_available = (ListView) findViewById(R.id.device_address);
        CustomAdapter2 customAdapterDevices = new CustomAdapter2(getApplicationContext(), IP_address);
        devices_available.setMinimumHeight(justifyListViewHeightBasedOnChildren(devices_available, customAdapterDevices));
        devices_available.setAdapter(customAdapterDevices);


        Add_device = (Button) findViewById(R.id.addDevice);

        RadioGroup type2 = (RadioGroup) findViewById(R.id.radioGroup2);
        type2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Add_device.setEnabled(true);
            }
        });

        Add_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDevice2_activity.this, Devices_activity.class);
                startActivity(intent);
            }
        });

        back = (Button) findViewById(R.id.Back2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDevice2_activity.this, AddDevice_activity.class);
                startActivity(intent);
            }
        });

    }

    //https://stackoverflow.com/questions/12212890/disable-scrolling-of-a-listview-contained-within-a-scrollview/27818661#27818661

    private int justifyListViewHeightBasedOnChildren(ListView listView, CustomAdapter2 customAdapter) {

        CustomAdapter2 adapter = customAdapter;

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