package com.example.chorefy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class activity_features_page extends AppCompatActivity {

    private ImageButton homeBtn, settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features_page);

        homeBtn = (ImageButton) findViewById(R.id.imgBtnHome);
        homeBtn.setOnClickListener(v -> openHomeActivity());

        settingsBtn = (ImageButton) findViewById(R.id.imgBtnSettings);
        settingsBtn.setOnClickListener(v -> openSettingsActivity());
    }

    public void openHomeActivity(){
        Intent intent = new Intent(this, activity_home_page.class);
        startActivity(intent);
    }

    public void openSettingsActivity(){
        Intent intent = new Intent(this, activity_settings_page.class);
        startActivity(intent);
    }
}