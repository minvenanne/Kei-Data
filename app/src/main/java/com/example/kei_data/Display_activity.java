package com.example.kei_data;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;

import android.content.Intent;

import android.view.View;
import android.widget.ImageButton;

import java.io.IOException;
import java.util.UUID;


public class Display_activity extends AppCompatActivity{
    public ImageButton smileyButton;
    public ImageButton barChartButton;
    public ImageButton speedometerButton;
    public ImageButton settingsButton;
    public ImageButton homeButton;
    public ImageButton categoriesButton;
    static final UUID mUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public int counter = 0;
    BluetoothAdapter btAdapter;
    BluetoothDevice hc05;
    BluetoothSocket btSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        btAdapter = BluetoothAdapter.getDefaultAdapter();

        hc05 = btAdapter.getRemoteDevice("98:D3:31:F7:73:55");

        btSocket = null;

        System.out.println("Test");

        smileyButton = (ImageButton) findViewById((R.id.smiley));
        smileyButton.setOnClickListener(new View.OnClickListener(){

            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                System.out.println("du har trykket på smiley knappen");
                do {
                    try {
                        btSocket = hc05.createRfcommSocketToServiceRecord(mUUID);
                        btSocket.connect();

                    } catch (IOException e) {
                        //e.printStackTrace();
                    }
                    counter = 1;
                } while (!btSocket.isConnected() && counter < 3);

                try {
                    btSocket.close();
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }
        });

        barChartButton = (ImageButton) findViewById((R.id.barchart));
        barChartButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                System.out.println("du har trykket på barChart knappen");
                do {
                    try {
                        btSocket = hc05.createRfcommSocketToServiceRecord(mUUID);
                        btSocket.connect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    counter = 3;
                } while (!btSocket.isConnected() && counter < 3);

                try {
                    btSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        speedometerButton = (ImageButton) findViewById((R.id.speedometer));
        speedometerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                System.out.println("du har trykket på speedometer knappen");
                do {
                    try {
                        btSocket = hc05.createRfcommSocketToServiceRecord(mUUID);
                        btSocket.connect();
                    } catch (IOException e) {
                        System.out.println("Fail 1");
                        //e.printStackTrace();
                    }
                    counter = 2;
                } while (!btSocket.isConnected() && counter < 3);

                try {
                    btSocket.close();
                } catch (IOException e) {
                    System.out.println("Fail 2");
                    //e.printStackTrace();
                }
            }
        });



        settingsButton = (ImageButton) findViewById(R.id.Settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Display_activity.this, Settings_activity1.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", getIntent().getSerializableExtra("user"));
                startActivity(intent);
            }
        });

        homeButton = (ImageButton) findViewById(R.id.Total);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Display_activity.this, MainActivity.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", getIntent().getSerializableExtra("user"));
                startActivity(intent);
            }
        });

        categoriesButton = (ImageButton) findViewById(R.id.Categories);
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Display_activity.this, Categories_activity.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", getIntent().getSerializableExtra("user"));
                startActivity(intent);
            }
        });
    }
}

