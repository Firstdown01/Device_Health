package com.example.mobllics;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Sensors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        TextView gyr=findViewById(R.id.gyroscope);
        TextView gps=findViewById(R.id.gps);
        TextView acc=findViewById(R.id.acc);
        if (gyroscopeSensor != null) {
            gyr.setText("Gyroscope sensor is available");

        } else {
            gyr.setText("Gyroscope sensor is not available");
        }

//        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometerSensor != null) {
            acc.setText("accelerometer sensor is available");
            // The accelerometer sensor is available on this device.
        } else {
            acc.setText("accelerometer sensor is not available");
            // The accelerometer sensor is not available on this device.
        }
//        gpsStatusTextView = findViewById(R.id.gpsStatusTextView);

        // Initialize the LocationManager
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Check if GPS is enabled
        boolean isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (isGpsEnabled) {
            gps.setText("gps sensor is  working");
        } else {
            gps.setText("gps sensor is not working");
            showEnableGpsDialog();

        }
        SensorEventListener accelerometerListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                Log.e("orientation",determineOrientation(x,y,z));

                // Display the accelerometer data to the user or perform tests
                // You can check for specific conditions based on the sensor data.
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // Handle accuracy changes if necessary
            }
            // Method to determine device orientation based on accelerometer data
            private String determineOrientation(float x, float y, float z) {
                // Calculate the angle between the gravity vector and each axis
                double angleX = Math.atan2(x, Math.sqrt(y * y + z * z));
                double angleY = Math.atan2(y, Math.sqrt(x * x + z * z));
                double angleZ = Math.atan2(Math.sqrt(x * x + y * y), z);

                // Define thresholds for orientation detection
                double threshold = Math.toRadians(30); // You can adjust this value based on your needs

                // Check for portrait, landscape, or upside-down orientations
                if (Math.abs(angleX) > threshold) {
                    return "Landscape";
                } else if (Math.abs(angleY) > threshold) {
                    return "Portrait";
                } else if (Math.abs(angleZ) > threshold) {
                    return "Upside-Down";
                } else {
                    return "Orientation not determined";
                }
            }

        };

        sensorManager.registerListener(accelerometerListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);


    }
    private void showEnableGpsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("GPS is not enabled. Do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Redirect the user to the GPS settings screen
                        Intent gpsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(gpsIntent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Handle the case when the user declines to enable GPS
                        Toast.makeText(Sensors.this,"cant test with disabled gps",Toast.LENGTH_LONG).show();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

}