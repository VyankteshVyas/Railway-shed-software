package com.example.shedupdate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shedupdate.restClient.RestClient;
import com.example.shedupdate.restClient.request.ActualLoginRequest;
import com.example.shedupdate.restClient.request.NotessveLoginRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotesDialog extends DialogFragment {

    ArrayList<String> loconumbers=new ArrayList<>();
    ArrayList<String> loconotes=new ArrayList<>();
    ArrayList<String> loconotesdatewa=new ArrayList<>();
    ArrayList<String> remaniningloconotes=new ArrayList<>();
    ArrayList<String> remainingloconotesdatewa=new ArrayList<>();
    Button submit;
    String username,password,loconumberclicked;
    EditText noteedit;
    TextView dateofnote;
    RecyclerView notesrecyclerview;
    public NotesDialog newInstance(String message){

        NotesDialog notesDialog=new NotesDialog();
        return notesDialog;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.notesdialog,container,false);
        submit=view.findViewById(R.id.submit);
//        noteedit=view.findViewById(R.id.noteedit);
        notesrecyclerview=view.findViewById(R.id.notesrecyclerview);
        dateofnote=view.findViewById(R.id.dateofnote);
        noteedit=view.findViewById(R.id.notessinglerownote);
        username=getArguments().getString("username");
        password=getArguments().getString("password");
        loconumberclicked=getArguments().getString("loco_number");
        getNotes();
        Log.d("loconumbers","df"+loconumbers);
        Log.d("loconotesde","df"+remaniningloconotes);
        Log.d("loconotesdatewaare","df"+loconotesdatewa);
        Date c = Calendar.getInstance().getTime();
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
        final String formattedDate = df.format(c);


        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");
        String dateToStr = format.format(today);
        dateofnote.setText(dateToStr);
//        noteedit.setText(getArguments().getString("notes"));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<NoteSaveResponse> call= RestClient.get().NoteSaveReturn(new NotessveLoginRequest(getArguments().getString("username"),Integer.parseInt(getArguments().getString("loco_number")),noteedit.getText().toString(),formattedDate));
                call.enqueue(new Callback<NoteSaveResponse>() {
                    @Override
                    public void onResponse(Call<NoteSaveResponse> call, Response<NoteSaveResponse> response) {
                        NoteSaveResponse noteSaveResponse=response.body();
                        Log.d("valueofloconumber",""+noteSaveResponse.getLoco_number());
                        if (noteSaveResponse.getLoco_number()==111){
                            NotesDialog.this.dismiss();
                        }else {                        Toast.makeText(getActivity(),"Unable to save the note at the moment",Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<NoteSaveResponse> call, Throwable t) {
                        Log.d("valueofloconumber",""+t.getMessage());
                        Toast.makeText(getActivity(),"Some error Occured! Unable to save the note at the moment",Toast.LENGTH_LONG).show();
                    }
                });
//                NotesDialog.this.dismiss();
            }
        });
        return view;
    }

    public void getNotes(){
        Call<List<Loginingresponse>> call=RestClient.get().loginreturn(new ActualLoginRequest(username,password));
        call.enqueue(new Callback<List<Loginingresponse>>() {
            @Override
            public void onResponse(Call<List<Loginingresponse>> call, Response<List<Loginingresponse>> response) {
                if (response.isSuccessful()) {

                    List<Loginingresponse> loginResponses = response.body();
                    for (Loginingresponse loginingresponse:loginResponses){
                        loconumbers.add(loginingresponse.getLoco_number().toString());
                        loconotes.add(loginingresponse.getNotes());
                        loconotesdatewa.add(loginingresponse.getDatewa());

                    }

                    for (int i=0;i<=loconotes.size()-1;i++){
                        if (loconumbers.get(i).equals(loconumberclicked)){
                            remainingloconotesdatewa.add(loconotesdatewa.get(i));
                            remaniningloconotes.add(loconotes.get(i));
                        }
                    }
                    Log.d("loconumbersis","df"+loconumbers);
                    Log.d("loconotesis","df"+remaniningloconotes);
                    Log.d("loconotesdatewais","df"+remainingloconotesdatewa);

                    notesrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                    notesrecyclerview.setAdapter(new NotesDialogRecyclerView(remaniningloconotes,remainingloconotesdatewa));
                }
            }

            @Override
            public void onFailure(Call<List<Loginingresponse>> call, Throwable t) {
                Toast.makeText(getActivity(),"Some error Occured",Toast.LENGTH_LONG).show();
            }
        });

    }
}
