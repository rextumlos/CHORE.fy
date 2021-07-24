package com.example.chorefy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class activity_home_page extends AppCompatActivity {

    private ImageButton featuresBtn, settingsBtn;
    private Button addTaskBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        featuresBtn = findViewById(R.id.imgBtnFeatures);
        featuresBtn.setOnClickListener(v -> openFeaturesActivity());

        settingsBtn = findViewById(R.id.imgBtnSettings);
        settingsBtn.setOnClickListener(v -> openSettingsActivity());

        addTaskBtn = findViewById(R.id.btnAddTask);
        addTaskBtn.setOnClickListener(v -> openTaskAssigner());
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

    public void openTaskAssigner(){
        TaskAssigner fragment = new TaskAssigner();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_from_right,R.anim.enter_from_right,R.anim.exit_from_right);
        ft.addToBackStack(null);
        ft.add(R.id.fragment_task_assigner, fragment, "TASK ASSIGNER").commit();
    }

}