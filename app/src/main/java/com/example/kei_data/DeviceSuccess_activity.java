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


        settingsButton = (ImageButton) findViewById(R.id.Settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeviceSuccess_activity.this, Settings_activity1.class);
                startActivity(intent);
            }
        });

        homeButton = (ImageButton) findViewById(R.id.Total);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeviceSuccess_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        categoriesButton = (ImageButton) findViewById(R.id.Categories);
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeviceSuccess_activity.this, Categories_activity.class);
                startActivity(intent);
            }
        });

        Name = findViewById(R.id.textViewName);
        Type = findViewById(R.id.textViewType);
        IP = findViewById(R.id.textViewIP);

        Name.setText(AddDeviceName_activity.getDeviceName());
        Type.setText(AddDeviceType_activity.getDeviceType());
        IP.setText(AddDeviceIP_activity.getDeviceIp());

        openDevices = (Button) findViewById(R.id.GoToMyDevices);
        openDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeviceSuccess_activity.this, Devices_activity.class);
                startActivity(intent);
            }
        });
    }
}