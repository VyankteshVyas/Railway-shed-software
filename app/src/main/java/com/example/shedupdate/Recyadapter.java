package com.example.shedupdate;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shedupdate.restClient.response.LoginResponse;

import java.util.ArrayList;
import java.util.List;

public class Recyadapter extends RecyclerView.Adapter<Recyadapter.RecyclerViewholder> {


    List<LoginResponse> heroList;
    Context context;
    ArrayList<String> locoNumbersi,locoNotesi;

    public Recyadapter(List<LoginResponse> heroList, Context context,ArrayList<String> locoNumbersi, ArrayList<String> locoNotesi) {
        this.heroList = heroList;
        this.context = context;
        this.locoNumbersi = locoNumbersi;
        Log.d("hids",locoNumbersi.toString());

        this.locoNotesi = locoNotesi;
    }
    @NonNull
    @Override
    public RecyclerViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.singlecardview,viewGroup,false);
        RecyclerViewholder recyclerviewHolder=new RecyclerViewholder(view);
        Log.d("hids1",locoNumbersi.toString());
        return recyclerviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewholder recyclerViewholder, final int i) {

        Log.d("hids2",locoNumbersi.toString());
        recyclerViewholder.wd.setText(heroList.get(i).getWd());
        recyclerViewholder.ws.setText(heroList.get(i).getWs());
        recyclerViewholder.ff.setText(heroList.get(i).getFf());
//        recyclerViewholder.arrvtime.setText(heroList.get(i).getArrival_time());
        recyclerViewholder.arrvdate.setText(heroList.get(i).getArrival_date()+"|"+heroList.get(i).getArrival_time());
        recyclerViewholder.loc.setText(heroList.get(i).getLoc());
        recyclerViewholder.pdc.setText(heroList.get(i).getPdc());
        recyclerViewholder.schedules.setText(heroList.get(i).getSchedules());
        recyclerViewholder.dw.setText(heroList.get(i).getDw());
        recyclerViewholder.trainno.setText(String.valueOf(heroList.get(i).getTrain_number()));
        recyclerViewholder.locono.setText(String.valueOf(heroList.get(i).getLoco_number()));
        recyclerViewholder.locono.setOnClickListener(new View.OnClickListener() {
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


        if (heroList.get(i).getOut_date()==null){
            recyclerViewholder.cardView.setCardBackgroundColor(Color.parseColor("#032F90"));
            recyclerViewholder.wd.setTextColor(Color.parseColor("#FFFFFF"));
            recyclerViewholder.ws.setTextColor(Color.parseColor("#FFFFFF"));
            recyclerViewholder.ff.setTextColor(Color.parseColor("#FFFFFF"));
//            recyclerViewholder.arrvtime.setTextColor(Color.parseColor("#FFFFFF"));
            recyclerViewholder.arrvdate.setText(heroList.get(i).getArrival_date()+"|"+heroList.get(i).getArrival_time());
            recyclerViewholder.loc.setTextColor(Color.parseColor("#FFFFFF"));
            recyclerViewholder.pdc.setTextColor(Color.parseColor("#FFFFFF"));
            recyclerViewholder.dw.setTextColor(Color.parseColor("#FFFFFF"));
            recyclerViewholder.trainno.setTextColor(Color.parseColor("#FFFFFF"));
            recyclerViewholder.locono.setTextColor(Color.parseColor("#FFFFFF"));
            recyclerViewholder.a1.setTextColor(Color.parseColor("#FFFFFF"));
            recyclerViewholder.schedules.setText(heroList.get(i).getSchedules());
            recyclerViewholder.schedules.setTextColor(Color.parseColor("#FFFFFF"));
            recyclerViewholder.a2.setTextColor(Color.parseColor("#FFFFFF"));
            recyclerViewholder.arrvdate.setTextColor(Color.parseColor("#FFFFFF"));
            recyclerViewholder.a3.setTextColor(Color.parseColor("#FFFFFF"));
            recyclerViewholder.a4.setTextColor(Color.parseColor("#FFFFFF"));
            recyclerViewholder.a5.setTextColor(Color.parseColor("#FFFFFF"));



            recyclerViewholder.a6.setTextColor(Color.parseColor("#FFFFFF"));
            recyclerViewholder.a7.setTextColor(Color.parseColor("#FFFFFF"));
//            recyclerViewholder.a8.setTextColor(Color.parseColor("#FFFFFF"));
            recyclerViewholder.v1.setBackgroundColor(Color.parseColor("#FFFFFF"));
            Log.d("itoccured",""+i);
        }else {
            recyclerViewholder.cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            recyclerViewholder.wd.setTextColor(Color.parseColor("#000000"));
            recyclerViewholder.ws.setTextColor(Color.parseColor("#000000"));
            recyclerViewholder.ff.setTextColor(Color.parseColor("#000000"));
//            recyclerViewholder.arrvtime.setTextColor(Color.parseColor("#000000"));
            recyclerViewholder.schedules.setText(heroList.get(i).getSchedules());

            recyclerViewholder.schedules.setTextColor(Color.parseColor("#000000"));
            recyclerViewholder.arrvdate.setTextColor(Color.parseColor("#000000"));
            recyclerViewholder.loc.setTextColor(Color.parseColor("#000000"));
            recyclerViewholder.pdc.setTextColor(Color.parseColor("#000000"));
            recyclerViewholder.dw.setTextColor(Color.parseColor("#000000"));
            recyclerViewholder.trainno.setTextColor(Color.parseColor("#000000"));
            recyclerViewholder.locono.setTextColor(Color.parseColor("#000000"));
            recyclerViewholder.a1.setTextColor(Color.parseColor("#000000"));
            recyclerViewholder.a2.setTextColor(Color.parseColor("#000000"));
            recyclerViewholder.a3.setTextColor(Color.parseColor("#000000"));
            recyclerViewholder.a4.setTextColor(Color.parseColor("#000000"));
            recyclerViewholder.a5.setTextColor(Color.parseColor("#000000"));
            recyclerViewholder.a6.setTextColor(Color.parseColor("#000000"));
            recyclerViewholder.a7.setTextColor(Color.parseColor("#000000"));
//            recyclerViewholder.a8.setTextColor(Color.parseColor("#000000"));
            recyclerViewholder.v1.setBackgroundColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public int getItemCount() {
        Log.d("hids4",locoNumbersi.toString());
        return heroList.size();
    }

    public class RecyclerViewholder extends RecyclerView.ViewHolder{
        TextView arrvdate,arrvtime,dw,loc,ff,wd,pdc,ws,locono,trainno,a1,a2,a3,a4,a5,a6,a7,a8,a9,schedules;
        CardView cardView;
        View v1;



        public RecyclerViewholder(@NonNull View itemView) {
            super(itemView);
             arrvdate=itemView.findViewById(R.id.arrvdate);
            dw=itemView.findViewById(R.id.dw);
            locono=itemView.findViewById(R.id.locoNumber);
            trainno=itemView.findViewById(R.id.trainNo);
            loc=itemView.findViewById(R.id.loc);
            ff=itemView.findViewById(R.id.ff);
            wd=itemView.findViewById(R.id.wd);
            pdc=itemView.findViewById(R.id.pdc);
            ws=itemView.findViewById(R.id.ws);
            ws=itemView.findViewById(R.id.ws);
            a1=itemView.findViewById(R.id.a1);
            a2=itemView.findViewById(R.id.a2);
            a3=itemView.findViewById(R.id.a3);
            a4=itemView.findViewById(R.id.a4);
            a5=itemView.findViewById(R.id.a5);
            schedules=itemView.findViewById(R.id.schedules);
            a6=itemView.findViewById(R.id.a6);
            a7=itemView.findViewById(R.id.a7);
            cardView=itemView.findViewById(R.id.card_view);
//            a8=itemView.findViewById(R.id.a8);
            v1=itemView.findViewById(R.id.v1);
        }
    }
}
