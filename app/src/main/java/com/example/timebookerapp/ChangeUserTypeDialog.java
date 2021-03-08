package com.example.timebookerapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class ChangeUserTypeDialog extends AppCompatDialogFragment {

    private EditText changeTypeUsername;
    private ChangeUserTypeDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_change_user_type, null);

        builder.setView(view)
                .setTitle("Promote a user to admin")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = changeTypeUsername.getText().toString();
                        listener.applyChangeUserTypeTexts(username);
                    }
                });

        changeTypeUsername = view.findViewById(R.id.changeTypeUsername);


        return builder.create();
    }

    @Override
    public void onAttach( Context context) {
        super.onAttach(context);

        try {
            listener = (ChangeUserTypeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogueListener");
        }
    }

    public interface ChangeUserTypeDialogListener {
        void applyChangeUserTypeTexts(String project);
    }
}
