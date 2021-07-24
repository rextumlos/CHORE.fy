package com.example.chorefy;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import android.text.TextWatcher;
import android.text.Editable;

public class TaskAssigner extends Fragment {

    private ImageButton returnBtn;
    private Button setDateBtn, setTimeBtn;
    private EditText dateText, timeText, taskNameET, memberET;
    private TextView dateTV, timeTV, taskNameTV, memberTV;

/*    private static final String ARG_PARAM1 = "param1";
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
    }*/

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

        //Real Time Text Tracking
        taskNameET = ((EditText) view.findViewById(R.id.edit_text_task_name));
        taskNameTV = ((TextView) view.findViewById(R.id.text_view_task_preview));

        taskNameET.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {   //Convert the Text to String
                String inputText = taskNameET.getText().toString();
                taskNameTV.setText(inputText);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }
        });

        dateTV = ((TextView) view.findViewById(R.id.text_view_date_preview));

        dateText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {   //Convert the Text to String
                String inputText = dateText.getText().toString();
                dateTV.setText(inputText);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }
        });

        timeTV = ((TextView) view.findViewById(R.id.text_view_time_preview));

        timeText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {   //Convert the Text to String
                String inputText = "    |    " + timeText.getText().toString();
                timeTV.setText(inputText);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }
        });

        memberET = (EditText) view.findViewById(R.id.edit_text_member);
        memberTV = (TextView) view.findViewById(R.id.text_view_member_preview);

        memberET.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {   //Convert the Text to String
                String inputText = memberET.getText().toString();
                memberTV.setText(inputText);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }
        });

        return view;
    }

    //Return to Activity when clicking Back
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