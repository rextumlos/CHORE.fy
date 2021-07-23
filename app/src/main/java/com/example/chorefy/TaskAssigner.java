package com.example.chorefy;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;


public class TaskAssigner extends Fragment {

    private ImageButton returnBtn;
    private Button setDateBtn, setTimeBtn;
    private EditText dateText, timeText;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public TaskAssigner() {
        // Required empty public constructor
    }

    public static TaskAssigner newInstance(String param1, String param2) {
        TaskAssigner fragment = new TaskAssigner();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_assigner, container, false);

        returnBtn = view.findViewById(R.id.imgBtnReturn);
        returnBtn.setOnClickListener(v -> returnToHomeActivity());

        setDateBtn = view.findViewById(R.id.btnSetDate);
        setDateBtn.setOnClickListener(v -> setDate());

        setTimeBtn = view.findViewById(R.id.btnSetTime);
        setTimeBtn.setOnClickListener(v -> setTime());

        dateText = view.findViewById(R.id.editTxtSetDate);

        timeText = view.findViewById(R.id.editTxtSetTime);

        return view;
    }

    public void returnToHomeActivity(){
        getActivity().onBackPressed();
    }

    public void setDate(){

        Calendar calendar = Calendar.getInstance();

        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int date) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);

                CharSequence charSequence = DateFormat.format("EEE, MMM d, yyyy", calendar1);
                dateText.setText(charSequence);
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();
    }

    public void setTime(){

        Calendar calendar = Calendar.getInstance();

        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);

                CharSequence charSequence = DateFormat.format("hh:mm a", calendar1);
                timeText.setText(charSequence);
            }
        }, HOUR, MINUTE, false);

        timePickerDialog.show();
    }
}