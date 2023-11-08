package com.example.mobllics;

import androidx.camera.core.Camera;
import androidx.camera.lifecycle.ProcessCameraProvider;
import com.google.common.util.concurrent.ListenableFuture;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
//import androidx.camera.view.PreviewView;
//import androidx.camera.core.ImageCapture;
//import androidx.camera.lifecycle.ProcessCameraProvider;
//import androidx.camera.core.CameraSelector;
//import androidx.lifecycle.LifecycleOwner;
//import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleRegistry;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class Cam extends AppCompatActivity {
    private PreviewView PreviewView;
//    private PreviewView backCameraPreviewView;
    private LifecycleRegistry mLifecycleRegistry;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Intent intent = getIntent();
        String pos = intent.getStringExtra("pos");
        PreviewView = findViewById(R.id.Preview);
//        frontCameraPreviewView = findViewById(R.id.frontCameraPreview);
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        if(pos.equals("front")){
            cameraProviderFuture.addListener(() -> {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                    bindPreview(cameraProvider, CameraSelector.LENS_FACING_BACK, PreviewView);

                } catch (Exception e) {
                    // Handle exceptions
                }
            }, ContextCompat.getMainExecutor(this));
        }else{
            cameraProviderFuture.addListener(() -> {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                    bindPreview(cameraProvider, CameraSelector.LENS_FACING_FRONT, PreviewView);

                } catch (Exception e) {
                    // Handle exceptions
                }
            }, ContextCompat.getMainExecutor(this));
        }



    }
    void bindPreview(@NonNull ProcessCameraProvider cameraProvider, int lensFacing, PreviewView previewView) {
        Preview preview = new Preview.Builder().build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(lensFacing)
                .build();

        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        Camera camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview);
//        cameraProvider.unbind(preview);
//        return;
    }
//         final Lazy<LifecycleRegistry> mLifecycleRegistry = (Lazy<LifecycleRegistry>) LazyKt.lazy(() -> new LifecycleRegistry(this));
//
//        previewView = findViewById(R.id.previewView);
////        mLifecycleRegistry = new LifecycleRegistry(this);
//
//        startCamera();
//    }
//
//    private void startCamera() {
//        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);
//
//        cameraProviderFuture.addListener(() -> {
//            try {
//                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
//
//                // Set up the Preview use case
//                Preview preview = new Preview.Builder().build();
//                preview.setSurfaceProvider(previewView.getSurfaceProvider());
//
//                // Select the camera
//                CameraSelector cameraSelector = new CameraSelector.Builder()
//                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
//                        .build();
//
//                // Unbind any use cases that may be bound to a different lifecycle
//                cameraProvider.unbindAll();
//
//                // Bind the camera to the lifecycle with the Preview use case
//                cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, preview);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }, ContextCompat.getMainExecutor(this));
//    }
//
//    @NonNull
//    @Override
//    public Lifecycle getLifecycle() {
//        return mLifecycleRegistry;
//    }
}
/* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        previewView=findViewById(R.id.previewView);
        cameraProviderFuture = ProcessCameraProvider.getInstance(Cam.this);

        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
                bindPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                // No errors need to be handled for this Future.
                // This should never be reached.
            }
        }, ContextCompat.getMainExecutor(Cam.this));
    }
    void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder()
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector, preview);
    }*/