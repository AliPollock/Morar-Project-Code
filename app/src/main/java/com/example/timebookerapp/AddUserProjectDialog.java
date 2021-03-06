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

public class AddUserProjectDialog extends AppCompatDialogFragment {

    private EditText addUsername;
    private EditText addProject;
    private AddUserProjectDialogListener listener;

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_user_project, null);

        builder.setView(view)
               .setTitle("Assign project to user")
               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               })
               .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                        String username = addUsername.getText().toString();
                        String project = addProject.getText().toString();
                        listener.applyTexts(username, project);
                   }
               });

        addUsername = view.findViewById(R.id.addUsername);
        addProject = view.findViewById(R.id.addProject);


        return builder.create();
    }

    @Override
    public void onAttach( Context context) {
        super.onAttach(context);

        try {
            listener = (AddUserProjectDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogueListener");
        }
    }

    public interface AddUserProjectDialogListener {
        void applyTexts(String username, String project);
    }
}
