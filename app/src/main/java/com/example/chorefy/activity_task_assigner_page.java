package com.example.chorefy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class activity_task_assigner_page extends AppCompatActivity {

    private Button cancelBtn, saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_assigner_page);

        cancelBtn = (Button) findViewById(R.id.btnCancel);
        cancelBtn.setOnClickListener(v -> openHomeActivity());
    }

    public void openHomeActivity(){
        Intent intent = new Intent(this, activity_home_page.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}