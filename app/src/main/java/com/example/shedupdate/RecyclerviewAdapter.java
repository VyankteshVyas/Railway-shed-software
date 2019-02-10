package com.example.shedupdate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.RecyclerviewHolder> {
    List<Hero> heroList;
    Context context;

    ArrayList<String> locoNumbersi,locoNotesi;

    public RecyclerviewAdapter(List<Hero> heroList, Context context, ArrayList<String> locoNumbersi, ArrayList<String> locoNotesi) {
        this.heroList = heroList;
        this.context = context;
        this.locoNumbersi = locoNumbersi;
        Log.d("hids",locoNumbersi.toString());

        this.locoNotesi = locoNotesi;
    }

    @NonNull
    @Override
    public RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.singlecardview,viewGroup,false);
        RecyclerviewHolder recyclerviewHolder=new RecyclerviewHolder(view);
        return recyclerviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewHolder recyclerviewHolder, final int i) {
        recyclerviewHolder.wd.setText(heroList.get(i).getWd());
        recyclerviewHolder.ws.setText(heroList.get(i).getWs());
        recyclerviewHolder.ff.setText(heroList.get(i).getFf());
//        recyclerviewHolder.arrvtime.setText(heroList.get(i).getArrival_time());
        recyclerviewHolder.arrvdate.setText(heroList.get(i).getArrival_date()+"|"+heroList.get(i).getArrival_time());
        recyclerviewHolder.loc.setText(heroList.get(i).getLoc());
        recyclerviewHolder.pdc.setText(heroList.get(i).getPdc());
        recyclerviewHolder.dw.setText(heroList.get(i).getDw());
        recyclerviewHolder.schedules.setText(heroList.get(i).getSchedules());
        recyclerviewHolder.trainno.setText(String.valueOf(heroList.get(i).getTrain_number()));
        recyclerviewHolder.locono.setText(String.valueOf(heroList.get(i).getLoco_number()));
        recyclerviewHolder.locono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locoNumbersi.contains(String.valueOf(heroList.get(i).getLoco_number()))){
                    int j=locoNumbersi.indexOf(String.valueOf(heroList.get(i).getLoco_number()));

                    FragmentManager manager=((AppCompatActivity)context).getSupportFragmentManager();
//                    FragmentTransaction transaction=manager.beginTransaction();
                    NotesDialog notesDialog=new NotesDialog();
                    Bundle args=new Bundle();
                    args.putString("notes",locoNotesi.get(j));
                    notesDialog.setArguments(args);
                   notesDialog.show(manager,"difidh");
                    Log.d("hids",String.valueOf(heroList.get(i).getLoco_number()));
//                    Toast.makeText(context,locoNotesi.get(j),Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(context,"No",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    public class RecyclerviewHolder extends RecyclerView.ViewHolder{
        TextView arrvdate,arrvtime,dw,loc,ff,wd,pdc,ws,locono,trainno,schedules;
        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);
            arrvdate=itemView.findViewById(R.id.arrvdate);
            dw=itemView.findViewById(R.id.dw);
            schedules=itemView.findViewById(R.id.schedules);
            locono=itemView.findViewById(R.id.locoNumber);
            trainno=itemView.findViewById(R.id.trainNo);
            loc=itemView.findViewById(R.id.loc);
            ff=itemView.findViewById(R.id.ff);
            wd=itemView.findViewById(R.id.wd);
            pdc=itemView.findViewById(R.id.pdc);
            ws=itemView.findViewById(R.id.ws);

        }
    }
}
