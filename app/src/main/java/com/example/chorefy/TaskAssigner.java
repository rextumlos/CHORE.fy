package com.example.chorefy;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
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
import com.example.chorefy.Model.HomeTaskModel;
import com.example.chorefy.Utils.DataBaseHelper2;

import org.jetbrains.annotations.NotNull;

public class TaskAssigner extends DialogFragment {

    private ImageButton returnBtn, saveBtn;
    private Button setDateBtn, setTimeBtn;
    private EditText dateText, timeText, taskNameET, memberET;
    private TextView dateTV, timeTV, taskNameTV, memberTV;

    public static final String TAG = "TaskAssigner";

    private DataBaseHelper2 myDB;

    private boolean isTaskEmpty, isDateEmpty, isTimeEmpty, isMemberEmpty;

    public static TaskAssigner newInstance(){
        return new TaskAssigner();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

        saveBtn = view.findViewById(R.id.imgBtnSave);

        //Real Time Text Tracking
        taskNameET = ((EditText) view.findViewById(R.id.edit_text_task_name));
        taskNameTV = ((TextView) view.findViewById(R.id.text_view_task_preview));

        taskNameET.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {   //Convert the Text to String
                String inputText = taskNameET.getText().toString();
                taskNameTV.setText(inputText);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.toString().equals("")){
                    isTaskEmpty = true;
                }
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("") || isDateEmpty || isTimeEmpty || isMemberEmpty){
                    saveBtn.setEnabled(false);
                    saveBtn.setColorFilter(Color.GRAY);
                }else{
                    saveBtn.setEnabled(true);
                    saveBtn.setColorFilter(Color.WHITE);
                }

                if(!s.toString().equals("")){
                    isTaskEmpty = false;
                }
            }
        });

        dateTV = ((TextView) view.findViewById(R.id.text_view_date_preview));

        dateText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {   //Convert the Text to String
                String inputText = dateText.getText().toString();
                dateTV.setText(inputText);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.toString().equals("")){
                    isDateEmpty = true;
                }
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("") || isTaskEmpty || isTimeEmpty || isMemberEmpty){
                    saveBtn.setEnabled(false);
                    saveBtn.setColorFilter(Color.GRAY);
                }else{
                    saveBtn.setEnabled(true);
                    saveBtn.setColorFilter(Color.WHITE);
                }

                if(!s.toString().equals("")){
                    isDateEmpty = false;
                }
            }
        });

        timeTV = ((TextView) view.findViewById(R.id.text_view_time_preview));

        timeText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {   //Convert the Text to String
                String inputText = "    |    " + timeText.getText().toString();
                timeTV.setText(inputText);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.toString().equals("")){
                    isTimeEmpty = true;
                }
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("") || isTaskEmpty || isDateEmpty || isMemberEmpty){
                    saveBtn.setEnabled(false);
                    saveBtn.setColorFilter(Color.GRAY);
                }else{
                    saveBtn.setEnabled(true);
                    saveBtn.setColorFilter(Color.WHITE);
                }

                if(!s.toString().equals("")){
                    isTimeEmpty = false;
                }
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
                if(s.toString().equals("")){
                    isMemberEmpty = true;
                }
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("") || isTaskEmpty || isDateEmpty || isTimeEmpty){
                    saveBtn.setEnabled(false);
                    saveBtn.setColorFilter(Color.GRAY);
                }else{
                    saveBtn.setEnabled(true);
                    saveBtn.setColorFilter(Color.WHITE);
                }

                if(!s.toString().equals("")){
                    isMemberEmpty = false;
                }
            }
        });

        if(taskNameET.getText().toString().equals("")){
            isTaskEmpty = true;
        }

        if(dateText.getText().toString().equals("")){
            isDateEmpty = true;
        }

        if(timeText.getText().toString().equals("")){
            isTimeEmpty = true;
        }

        if(memberET.getText().toString().equals("")){
            isMemberEmpty = true;
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myDB = new DataBaseHelper2(getActivity());

        boolean isUpdate = false;

        Bundle bundle = getArguments();
        if(bundle != null){
            isUpdate = true;
            String task = bundle.getString("task");
            taskNameET.setText(task);

            String date = bundle.getString("date");
            dateText.setText(date);

            String time = bundle.getString("time");
            timeText.setText(time);

            String member = bundle.getString("member");
            memberET.setText(member);

            if(task.length() > 0 && date.length() > 0 && time.length() > 0 && member.length() > 0){
                saveBtn.setEnabled(false);
            }
        }

        //Checks if there are any empty input fields
        if(taskNameET.getText().toString().equals("") || dateText.getText().toString().equals("") || timeText.getText().toString().equals("") || memberET.getText().toString().equals("")){
            saveBtn.setEnabled(false);
            saveBtn.setColorFilter(Color.GRAY);
        }

        boolean finalIsUpdate = isUpdate;
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = taskNameET.getText().toString();
                String date = dateText.getText().toString();
                String time = timeText.getText().toString();
                String member = memberET.getText().toString();

                if(finalIsUpdate){
                    myDB.updateTask2(bundle.getInt("id"), task);
                    myDB.updateDate(bundle.getInt("id"), date);
                    myDB.updateTime(bundle.getInt("id"), time);
                    myDB.updateMember(bundle.getInt("id"), member);
                }else{
                    HomeTaskModel item = new HomeTaskModel();
                    item.setTask(task);
                    item.setStatus(0);
                    item.setDate(date);
                    item.setTime(time);
                    item.setMember(member);
                    myDB.insertTask2(item);
                }
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if(activity instanceof OnDialogCloseListener2){
            ((OnDialogCloseListener2)activity).onDialogClose(dialog);
        }
    }

    //Return to Activity when clicking Back
    public void returnToHomeActivity(){
        dismiss();
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