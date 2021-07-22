package com.example.chorefy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class activity_settings_page extends AppCompatActivity {

    private ImageButton featuresBtn, homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        featuresBtn = (ImageButton) findViewById(R.id.imgBtnFeatures);
        featuresBtn.setOnClickListener(v -> openFeaturesActivity());

        homeBtn = (ImageButton) findViewById(R.id.imgBtnHome);
        homeBtn.setOnClickListener(v -> openHomeActivity());
    }

    public void openFeaturesActivity(){
        Intent intent = new Intent(this, activity_features_page.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void openHomeActivity(){
        Intent intent = new Intent(this, activity_home_page.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}