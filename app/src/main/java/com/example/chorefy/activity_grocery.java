package com.example.chorefy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageButton;

import com.example.chorefy.Adapter.GroceryAdapter;
import com.example.chorefy.Model.GroceryModel;
import com.example.chorefy.Utils.DataBaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class activity_grocery extends AppCompatActivity implements OnDialogCloseListener{

    private DataBaseHelper myDB;
    private List<GroceryModel> mList;
    private GroceryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);

        ImageButton backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> openHomeActivity());

//      Information Button
        ImageButton infoBtn = findViewById(R.id.infoBtn);
        infoBtn.setOnClickListener(view -> openDialog());

        RecyclerView mRecyclerview = findViewById(R.id.recyclerview);

        FloatingActionButton fab = findViewById(R.id.fab);

        myDB = new DataBaseHelper(activity_grocery.this);
        mList = new ArrayList<>();
        adapter = new GroceryAdapter(myDB , activity_grocery.this);

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapter);

        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);

        fab.setOnClickListener(view -> addnewItem_grocery.newInstance().show(getSupportFragmentManager() , addnewItem_grocery.TAG));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelper_Grocery(adapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerview);
    }

    public void openHomeActivity(){
        Intent intent = new Intent(this, activity_nav_main.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);
        adapter.notifyDataSetChanged();
    }

    public void openDialog(){
        InfoDialogGrocery infoDialogGrocery = new InfoDialogGrocery();
        infoDialogGrocery.show(getSupportFragmentManager(), "Info Dialog");
    }
}