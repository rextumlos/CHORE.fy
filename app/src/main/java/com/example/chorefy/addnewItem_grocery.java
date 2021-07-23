package com.example.chorefy;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chorefy.Model.GroceryModel;
import com.example.chorefy.Utils.DataBaseHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class addnewItem_grocery extends BottomSheetDialogFragment {

    public static final String TAG = "addnewItem_grocery";

    //widgets
    private EditText mEditText;
    private Button mSaveButton;

    private DataBaseHelper myDb;

    public static addnewItem_grocery newInstance(){
        return new addnewItem_grocery();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_grocery_item , container , false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditText = view.findViewById(R.id.edittext);
        mSaveButton = view.findViewById(R.id.button_save);

        myDb = new DataBaseHelper(getActivity());

        boolean isUpdate = false;

        Bundle bundle = getArguments();
        if (bundle != null){
            isUpdate = true;
            String task = bundle.getString("task");
            mEditText.setText(task);

            if (task.length() > 0){
                mSaveButton.setEnabled(false);
            }
        }
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")){
                    mSaveButton.setEnabled(false);
                    mSaveButton.setBackgroundColor(Color.GRAY);
                }else{
                    mSaveButton.setEnabled(true);
                    mSaveButton.setBackgroundColor(getResources().getColor(R.color.darker_teal));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        boolean finalIsUpdate = isUpdate;
        mSaveButton.setOnClickListener(view1 -> {
            String text = mEditText.getText().toString();

            if (finalIsUpdate){
                myDb.updateTask(bundle.getInt("id") , text);
            }else{
                GroceryModel item = new GroceryModel();
                item.setTask(text);
                item.setStatus(0);
                myDb.insertTask(item);
            }
            dismiss();
        });


    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if (activity instanceof OnDialogCloseListener){
            ((OnDialogCloseListener) activity).onDialogClose(dialog);
        }
    }
}







