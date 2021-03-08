package com.example.timebookerapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

public class RemoveUserProjectDialog extends AppCompatDialogFragment {

    private EditText removeUsername;
    private EditText removeProject;
    private RemoveUserProjectDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_remove_user_project, null);

        builder.setView(view)
                .setTitle("Remove user from project")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = removeUsername.getText().toString();
                        String project = removeProject.getText().toString();
                        listener.applyRemoveTexts(username, project);
                    }
                });

        removeUsername = view.findViewById(R.id.removeUsername);
        removeProject = view.findViewById(R.id.removeProject);


        return builder.create();
    }

    @Override
    public void onAttach( Context context) {
        super.onAttach(context);

        try {
            listener = (RemoveUserProjectDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogueListener");
        }
    }

    public interface RemoveUserProjectDialogListener {
        void applyRemoveTexts(String username, String project);
    }
}
