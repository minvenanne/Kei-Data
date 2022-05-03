package com.example.kei_data;


import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.bluetooth.BluetoothAdapter;
import android.widget.Toast;
import android.content.Context;
import java.util.Set;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.ArrayAdapter;

public class Bluetooth_activity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> mDeviceList = new ArrayList<String>();
    public ImageButton settingsButton;
    public ImageButton homeButton;
    public ImageButton categoriesButton;
    private Button search; //Den første knap
    private Button connect; //De to knapper, der ses på devicet - de skal connectes senere...
    private final static int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        ///Her Evalueres hvorvidt brugerens device understøtter Bluetooth
        BluetoothManager bluetoothManager = getSystemService(BluetoothManager.class);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        if (bluetoothAdapter == null) {
            Context context = getApplicationContext();
            CharSequence text = "Sorry, but this phone does not support bluetooth.!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Congratulations, your device supports bluetooth!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show(); ///Her fortæller vi, hvis androiddevicet har bluetooth eller ej
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            ArrayList<String> bluetoothDevicesList1 = new ArrayList<String>();
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                bluetoothDevicesList1.add(deviceName + deviceHardwareAddress);
                String[] bluetoothDevicesList = bluetoothDevicesList1.toArray(new String[0]);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, bluetoothDevicesList);
                ListView listView = (ListView) findViewById(R.id.bluetoothdevices);
                listView.setAdapter(adapter);


            }


            /// Hvis bluetooth ikke er slået til, spørger vi meget pænt om vi må slå det til.


            settingsButton = (ImageButton) findViewById(R.id.Settings);
            settingsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Bluetooth_activity.this, Settings_activity1.class);
                    startActivity(intent);
                }
            });

            homeButton = (ImageButton) findViewById(R.id.Total);
            homeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Bluetooth_activity.this, MainActivity.class);
                    startActivity(intent);
                }
            });

            categoriesButton = (ImageButton) findViewById(R.id.Categories);
            categoriesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Bluetooth_activity.this, Categories_activity.class);
                    startActivity(intent);
                }
            });


        }
    }
}
