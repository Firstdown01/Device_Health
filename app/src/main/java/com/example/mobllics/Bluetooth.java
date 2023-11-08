package com.example.mobllics;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.widget.EditText;

public class Bluetooth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            EditText result=findViewById(R.id.result);
            result.setText("your device doesnt support bluetooth");
        } else {
            if (!bluetoothAdapter.isEnabled()) {
                EditText result=findViewById(R.id.result);
                result.setText("enable your bluetooth. Go to your settings and enable the bluetooth. come back !");
                // Bluetooth is not enabled; provide instructions to the user on how to enable it
            } else {
                EditText result=findViewById(R.id.result);
                result.setText("working!");
                // Bluetooth is enabled and functioning
                // You can display this information to the user
            }
        }

    }
}