package com.example.chorefy;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class InfoDialogGrocery extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Instructions:")
                .setMessage("Press button below to ADD note.\nSwipe left to EDIT note.\nSwipe right to DELETE note.")
                .setPositiveButton("ok", (dialogInterface, i) -> {

                });
        return builder.create();
    }
}
