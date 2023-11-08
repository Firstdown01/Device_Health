package com.example.mobllics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Microphone extends AppCompatActivity {
    private EditText result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microphone);
        Button startbutton = findViewById(R.id.startbutton);
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMicrophoneTest();
            }
        });

    }

    private void startMicrophoneTest() {
        result=findViewById(R.id.result);
        // Use AudioRecord to capture audio from the device's microphone
        int bufferSize = AudioRecord.getMinBufferSize(
                44100,  // Sample rate
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT
        );

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
//            Toast.makeText(this, "1", Toast.LENGTH_LONG).show();
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
//            return;
        }
        AudioRecord audioRecord = new AudioRecord(
                MediaRecorder.AudioSource.MIC,
                44100,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufferSize
        );
//        Toast.makeText(this, Integer.toString(audioRecord.getState()), Toast.LENGTH_LONG).show();
        if (audioRecord.getState() == AudioRecord.STATE_INITIALIZED) {
            // Microphone is working
            // Display this information to the user
            result.setText("Microphone is working");
            audioRecord.release();
        } else {
            // Microphone is not working
            // Display this information to the user
            result.setText("Microphone is not working");
        }
    }

}