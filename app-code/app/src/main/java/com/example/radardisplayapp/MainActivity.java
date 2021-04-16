package com.example.radardisplayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    float tracker = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

    }

    public void resizeBox(View view)
    {
        // Do something in response to button

        tracker += 10;
        if (tracker > 100){
            tracker = 0;
        }

        DisplayPaperLevel box = findViewById(R.id.box);
        box.setLevel(tracker);
    }

}
