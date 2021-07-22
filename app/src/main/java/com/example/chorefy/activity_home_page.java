package com.example.chorefy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class activity_home_page extends AppCompatActivity {

    private ImageButton featuresBtn, settingsBtn;
    private Button taskBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        featuresBtn = (ImageButton) findViewById(R.id.imgBtnFeatures);
        featuresBtn.setOnClickListener(v -> openFeaturesActivity());

        settingsBtn = (ImageButton) findViewById(R.id.imgBtnSettings);
        settingsBtn.setOnClickListener(v -> openSettingsActivity());

        taskBtn = (Button) findViewById(R.id.btnAddTask);
        taskBtn.setOnClickListener(v -> openTaskAssignerActivity());
    }

    public void openFeaturesActivity(){
        Intent intent = new Intent(this, activity_features_page.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void openSettingsActivity(){
        Intent intent = new Intent(this, activity_settings_page.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void openTaskAssignerActivity(){
        Intent intent = new Intent(this, activity_task_assigner_page.class);
        startActivity(intent);
    }
}