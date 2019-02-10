package com.example.shedupdate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class NotesDialog extends DialogFragment {

    Button submit;
    EditText noteedit;
    public NotesDialog newInstance(String message){

        NotesDialog notesDialog=new NotesDialog();
        return notesDialog;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.notesdialog,container,false);
        submit=view.findViewById(R.id.submit);
        noteedit=view.findViewById(R.id.noteedit);
        getArguments().getString("notes");
        noteedit.setText(getArguments().getString("notes"));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotesDialog.this.dismiss();
            }
        });
        return view;
    }
}
