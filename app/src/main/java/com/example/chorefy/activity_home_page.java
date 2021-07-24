package com.example.chorefy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.chorefy.Adapter.HomeTaskAdapter;
import com.example.chorefy.Model.HomeTaskModel;
import com.example.chorefy.Utils.DataBaseHelper;
import com.example.chorefy.Utils.DataBaseHelper2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class activity_home_page extends AppCompatActivity implements OnDialogCloseListener2 {

    private ImageButton featuresBtn, settingsBtn;
    private Button addTaskBtn;
    private RecyclerView mRecyclerview;
    private DataBaseHelper2 myDB;
    private List<HomeTaskModel> mList;
    private HomeTaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        featuresBtn = findViewById(R.id.imgBtnFeatures);
        featuresBtn.setOnClickListener(v -> openFeaturesActivity());

        settingsBtn = findViewById(R.id.imgBtnSettings);
        settingsBtn.setOnClickListener(v -> openSettingsActivity());

        addTaskBtn = findViewById(R.id.btnAddTask);

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskAssigner.newInstance().show(getSupportFragmentManager(), TaskAssigner.TAG);
            }
        });

        mRecyclerview = findViewById(R.id.recycler_view_home);
        myDB = new DataBaseHelper2(activity_home_page.this);
        mList = new ArrayList<>();
        adapter = new HomeTaskAdapter(myDB, activity_home_page.this);

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapter);

        mList = myDB.getAllTasks2();
        Collections.reverse(mList);
        adapter.setTasks2(mList);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelper_Home(adapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerview);
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

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList = myDB.getAllTasks2();
        Collections.reverse(mList);
        adapter.setTasks2(mList);
        adapter.notifyDataSetChanged();
    }
}