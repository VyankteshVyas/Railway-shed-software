package com.example.shedupdate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class NotesDialogRecyclerView extends RecyclerView.Adapter<NotesDialogRecyclerView.RecyclerviewHolder> {

    List<String> notes,date;
    public NotesDialogRecyclerView(List<String> notes,List<String> date){
        this.notes=notes;
        this.date=date;
    }

    @NonNull
    @Override
    public RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.notessinglerow,viewGroup,false);
        RecyclerviewHolder recyclerviewHolder=new RecyclerviewHolder(view);
        return recyclerviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewHolder recyclerviewHolder, int i) {
        recyclerviewHolder.Notesdialog.setText(notes.get(i));
        recyclerviewHolder.Notesdialog.setEnabled(false);
        recyclerviewHolder.dateofnote.setText(date.get(i));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class RecyclerviewHolder extends RecyclerView.ViewHolder{
        EditText Notesdialog;
        TextView dateofnote;
        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);
            Notesdialog=itemView.findViewById(R.id.notessinglerownote);
            dateofnote=itemView.findViewById(R.id.dateofnote);
        }
    }
}
