package com.example.chorefy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.chorefy.Adapter.HomeTaskAdapter;
import com.example.chorefy.Model.HomeTaskModel;
import com.example.chorefy.Utils.DataBaseHelper2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment implements OnDialogCloseListener2{

    private Button addTaskBtn;
    private RecyclerView mRecyclerview;
    private DataBaseHelper2 myDB;
    private List<HomeTaskModel> mList;
    private HomeTaskAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        addTaskBtn = view.findViewById(R.id.btnAddTask);
        addTaskBtn.setOnClickListener(view1 -> {
            TaskAssigner taskAssigner = new TaskAssigner();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(android.R.id.content, taskAssigner);
            fragmentTransaction.commit();
        });

        ImageButton imgBtn = view.findViewById(R.id.infoBtn);
        imgBtn.setOnClickListener(view12 -> openDialog());

        mRecyclerview = view.findViewById(R.id.recycler_view_home);
        myDB = new DataBaseHelper2((FragmentActivity) getContext());
        mList = new ArrayList<>();
        adapter = new HomeTaskAdapter(myDB, HomeFragment.this);

        mRecyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(layoutManager);
        mRecyclerview.setAdapter(adapter);

        mList = myDB.getAllTasks2();
        Collections.reverse(mList);
        adapter.setTasks2(mList);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelper_Home(adapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerview);


        return view;
    }

    public void openDialog() {
        InfoDialogHome infoDialogHome = new InfoDialogHome();
        infoDialogHome.show(getFragmentManager(), "Info Dialog");
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList = myDB.getAllTasks2();
        Collections.reverse(mList);
        adapter.setTasks2(mList);
        adapter.notifyDataSetChanged();
    }
}