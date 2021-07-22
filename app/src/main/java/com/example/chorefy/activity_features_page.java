package com.example.chorefy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class activity_features_page extends AppCompatActivity {

    private ImageButton homeBtn, settingsBtn;
    CardView cardRecipe;
    CardView cardGrocery;
    CardView cardPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features_page);

        homeBtn = (ImageButton) findViewById(R.id.imgBtnHome);
        homeBtn.setOnClickListener(v -> openHomeActivity());

        settingsBtn = (ImageButton) findViewById(R.id.imgBtnSettings);
        settingsBtn.setOnClickListener(v -> openSettingsActivity());

        cardRecipe = findViewById(R.id.cardRecipe);
        cardGrocery = findViewById(R.id.cardGrocery);
        cardPayment = findViewById(R.id.cardPayment);

        cardRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Recipe Clicked");
            }
        });

        cardGrocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Grocery Clicked");
            }
        });

        cardPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Payment Clicked");
            }
        });
    }

    public void openHomeActivity(){
        Intent intent = new Intent(this, activity_home_page.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void openSettingsActivity(){
        Intent intent = new Intent(this, activity_settings_page.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    private void showToast(String message){

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}