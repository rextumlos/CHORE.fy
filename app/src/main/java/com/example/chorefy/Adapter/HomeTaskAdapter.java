package com.example.chorefy.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chorefy.Model.HomeTaskModel;
import com.example.chorefy.R;
import com.example.chorefy.TaskAssigner;
import com.example.chorefy.Utils.DataBaseHelper2;
import com.example.chorefy.activity_home_page;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeTaskAdapter extends RecyclerView.Adapter<HomeTaskAdapter.MyViewHolder2> {

    private List<HomeTaskModel> mList;
    private activity_home_page activity;
    private DataBaseHelper2 myDB;

    public HomeTaskAdapter(DataBaseHelper2 myDB, activity_home_page activity){
        this.activity = activity;
        this.myDB = myDB;
    }


    @NonNull
    @NotNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_task_layout, parent, false);
        return new MyViewHolder2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder2 holder, int position) {
        final HomeTaskModel item = mList.get(position);

        holder.mTaskTextView.setText(item.getTask());

        holder.mCheckBox.setChecked(toBoolean2(item.getStatus()));
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myDB.updateStatus2(item.getId(),1);
                }else
                    myDB.updateStatus2(item.getId(),0);
            }
        });

        holder.mDateTextView.setText(item.getDate());
        holder.mTimeTextView.setText(item.getTime());
        holder.mMemberTextView.setText(item.getMember());
    }

    public boolean toBoolean2(int num){
        return num != 0;
    }

    public Context getContext2(){
        return activity;
    }

    public void setTasks2(List<HomeTaskModel> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void deleteTask2(int position){
        HomeTaskModel item = mList.get(position);
        myDB.deleteTask2(item.getId());
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem2(int position){
        HomeTaskModel item = mList.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        bundle.putString("date", item.getDate());
        bundle.putString("time", item.getTime());
        bundle.putString("member", item.getMember());

        TaskAssigner task = new TaskAssigner();
        task.setArguments(bundle);
        task.show(activity.getSupportFragmentManager(), task.getTag());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder{

        CheckBox mCheckBox;
        TextView mTaskTextView, mDateTextView, mTimeTextView, mMemberTextView;

        public MyViewHolder2(@NonNull @NotNull View itemView) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.check_box);
            mTaskTextView = itemView.findViewById(R.id.text_view_task);
            mDateTextView = itemView.findViewById(R.id.text_view_date);
            mTimeTextView = itemView.findViewById(R.id.text_view_time);
            mMemberTextView = itemView.findViewById(R.id.text_view_member);
        }
    }
}
