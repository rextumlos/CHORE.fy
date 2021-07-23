package com.example.chorefy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Objects;

import static com.google.android.material.internal.ContextUtils.getActivity;

public class activity_features_page extends AppCompatActivity {

    CardView cardRecipe;
    CardView cardGrocery;
    CardView cardPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features_page);

        ImageButton homeBtn = (ImageButton) findViewById(R.id.imgBtnHome);
        homeBtn.setOnClickListener(v -> openHomeActivity());

        ImageButton settingsBtn = (ImageButton) findViewById(R.id.imgBtnSettings);
        settingsBtn.setOnClickListener(v -> openSettingsActivity());

        cardRecipe = findViewById(R.id.cardRecipe);
        cardGrocery = findViewById(R.id.cardGrocery);
        cardPayment = findViewById(R.id.cardPayment);

        cardRecipe.setOnClickListener(view -> openRecipeActivity());

        cardGrocery.setOnClickListener(view -> openGroceryActivity());

        cardPayment.setOnClickListener(view -> openPaymentActivity());
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


    public void openGroceryActivity(){
        Intent intent = new Intent(this, activity_grocery.class);
        startActivity(intent);
    }

    public void openRecipeActivity(){
        Intent intent = new Intent(this, activity_recipe_page.class);
        startActivity(intent);
    }

    public void openPaymentActivity(){
        Intent intent = new Intent(this, activity_payment_page.class);
        startActivity(intent);
    }





}