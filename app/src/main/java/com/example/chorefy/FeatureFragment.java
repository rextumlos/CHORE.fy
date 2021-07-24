package com.example.chorefy;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FeatureFragment extends Fragment {

    CardView cardRecipe;
    CardView cardGrocery;
    CardView cardPayment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feature, container, false);

        cardRecipe = view.findViewById(R.id.cardRecipe);
        cardGrocery = view.findViewById(R.id.cardGrocery);
        cardPayment = view.findViewById(R.id.cardPayment);

        cardRecipe.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), activity_recipe_page.class);
            startActivity(intent);
        });

        cardGrocery.setOnClickListener(view12 -> {
            Intent intent = new Intent(getActivity(), activity_grocery.class);
            startActivity(intent);
        });

        cardPayment.setOnClickListener(view13 -> {
            Intent intent = new Intent(getActivity(), activity_payment_page.class);
            startActivity(intent);
        });

        return view;
    }

}