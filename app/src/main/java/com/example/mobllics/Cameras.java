package com.example.mobllics;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.Manifest;

import java.util.Arrays;
import java.util.Map;

public class Cameras extends AppCompatActivity {
//    private static final int pic_id = 123;
    // Define the button and imageview type variable
//    Button camera_open_id;
//    ImageView click_image_id;
//    private static final String[] REQUIRED_PERMISSIONS = {
//            Manifest.permission.CAMERA,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameras);
        Button front=findViewById(R.id.front);
        Button back=findViewById(R.id.back);
        Intent intent=new Intent(this,Cam.class);
        intent.putExtra("pos","front");
//        Button sensors = findViewById(R.id.sensors);
        front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cameras.this, Cam.class);
                intent.putExtra("pos","back");
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cameras.this, Cam.class);
                intent.putExtra("pos","front");
                startActivity(intent);
            }
        });

//        if (isCameraAvailable(this)) {
//            Toast.makeText(this, "not available", Toast.LENGTH_LONG).show();
//        }else{
//            Toast.makeText(this,"available0",Toast.LENGTH_LONG).show();
////            camera_open_id = findViewById(R.id.camera_button);
////            click_image_id = findViewById(R.id.click_image);
//
//            // Camera_open button is for open the camera and add the setOnClickListener in this button
//            camera_open_id.setOnClickListener(v -> {
//                // Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
//                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                // Start the activity with camera_intent, and request pic id
//                startActivityForResult(camera_intent, pic_id);
//            });
//
//        }


        }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // Match the request 'pic id with requestCode
//        if (requestCode == pic_id) {
//            // BitMap is data structure of image file which store the image in memory
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            // Set the image in imageview for display
//            click_image_id.setImageBitmap(photo);
//        }
//    }
//    public static boolean isCameraAvailable(Context context) {
//        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
//    }
//    // Define an instance of ActivityResultLauncher
//    private ActivityResultLauncher<String[]> activityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.RequestMultiplePermissions(),
//            permissions -> {
//                // Handle Permission granted/rejected
//                boolean permissionGranted = true;
//                for (Map.Entry<String, Boolean> entry : permissions.entrySet()) {
//                    if (Arrays.asList(REQUIRED_PERMISSIONS).contains(entry.getKey()) && !entry.getValue()) {
//                        permissionGranted = false;
//                        break;
//                    }
//                }
//                if (!permissionGranted) {
//                    Toast.makeText(this, "Permission request denied", Toast.LENGTH_SHORT).show();
//                } else {
//                    startCamera();
//                }
//            }
//    );
//
//
//    private void startCamera() {
//    }
    }
