package com.example.kei_data;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.jar.Attributes;

public class DeviceSuccess_activity extends AppCompatActivity {
    Button openDevices;
    TextView Name;
    TextView Type;
    TextView IP;
    public ImageButton settingsButton;
    public ImageButton homeButton;
    public ImageButton categoriesButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devicesuccess);
        User mainUser = (User) getIntent().getSerializableExtra("user");

        settingsButton = (ImageButton) findViewById(R.id.Settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeviceSuccess_activity.this, Settings_activity1.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", getIntent().getSerializableExtra("user"));
                startActivity(intent);
            }
        });

        homeButton = (ImageButton) findViewById(R.id.Total);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeviceSuccess_activity.this, MainActivity.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", getIntent().getSerializableExtra("user"));
                startActivity(intent);
            }
        });

        categoriesButton = (ImageButton) findViewById(R.id.Categories);
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeviceSuccess_activity.this, Categories_activity.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", getIntent().getSerializableExtra("user"));
                startActivity(intent);
            }
        });

        Name = findViewById(R.id.textViewName);
        Type = findViewById(R.id.textViewType);
        IP = findViewById(R.id.textViewIP);
        System.out.println(AddDeviceName_activity.getDeviceName());
        System.out.println(AddDeviceType_activity.getDeviceType());
        System.out.println(AddDeviceIP_activity.getDeviceIp());

        int size = mainUser.getDeviceList().size();
        Name.setText(mainUser.deviceList.get(size).deviceName);
        Type.setText(mainUser.deviceList.get(size).deviceType);
        IP.setText(mainUser.deviceList.get(size).deviceIP);

        openDevices = (Button) findViewById(R.id.GoToMyDevices);
        openDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeviceSuccess_activity.this, Devices_activity.class);
                intent.putExtra("user", mainUser);
                startActivity(intent);
            }
        });
    }
}
