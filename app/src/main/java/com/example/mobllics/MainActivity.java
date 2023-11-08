package com.example.mobllics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
        
        Button bluetooth = findViewById(R.id.bluetooth);
        
        bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Bluetooth.class);

                startActivity(intent);
            }
        });

        Button camera = findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                requestCameraPermission();


            }
        });

        Button root = findViewById(R.id.rooted);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Root.class);

                startActivity(intent);
            }
        });

        Button microphone = findViewById(R.id.microphone);
        microphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestMicrophonePermission();

            }
        });
        Button sensors = findViewById(R.id.sensors);
        sensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Sensors.class);
                startActivity(intent);
            }
        });
    }
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 1;
    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
//            return false;
        }else {
            // Microphone permission already granted, proceed with microphone-related operations.
            Toast.makeText(this, "Camera permission already granted", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,Cameras.class);
            startActivity(intent);
        }
//        return true;
    }
    private static final int MICROPHONE_PERMISSION_REQUEST_CODE = 2;

    private void requestMicrophonePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, MICROPHONE_PERMISSION_REQUEST_CODE);
//            return false;
        }else {
            // Microphone permission already granted, proceed with microphone-related operations.
            Toast.makeText(this, "Microphone permission already granted", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,Microphone.class);
            startActivity(intent);
        }
//        return true;
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, you can proceed with related operations.
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
                // Start the Camera activity if camera permission is granted.
                Intent cameraIntent = new Intent(this, Cameras.class);
                startActivity(cameraIntent);
            } else if (requestCode == MICROPHONE_PERMISSION_REQUEST_CODE) {
                // Start the Microphone activity if microphone permission is granted.
                Intent microphoneIntent = new Intent(this, Microphone.class);
                startActivity(microphoneIntent);
            }
        } else {
            // Handle permission denial.
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();

            // Request the same permission again.
            if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
                requestCameraPermission();
            } else if (requestCode == MICROPHONE_PERMISSION_REQUEST_CODE) {
                requestMicrophonePermission();
            }
        }
    }
}
//    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 3;
//
//    private void checkForPermissions() {
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.CAMERA)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.CAMERA},
//                    MY_PERMISSIONS_REQUEST_CAMERA);
//        } else {
//            return;
//        }
//    }
//    private void openAlertDialog() {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        alertDialogBuilder.setMessage("This app requires your location to function!");
//        alertDialogBuilder.setPositiveButton("Try again",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        checkForPermissions();
//                    }
//                });
//
//        alertDialogBuilder.setNegativeButton("Settings", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Intent i = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                i.addCategory(Intent.CATEGORY_DEFAULT);
//                i.setData(Uri.parse("package:dk.redweb.intern.findetlokum"));
//                startActivity(i);
//            }
//        });
//
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//    }


