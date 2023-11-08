package com.example.mobllics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.scottyab.rootbeer.RootBeer;

public class Root extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        RootBeer rootBeer = new RootBeer(this);

        if (rootBeer.isRooted()) {
            // The device is rooted
            // You can display this information to the user
            TextView result=findViewById(R.id.result);
            result.setText("Your device is rooted");
        } else {
            // The device is not rooted
            // You can display this information to the user
            TextView result=findViewById(R.id.result);
            result.setText("Your device is not rooted");
        }

    }
}