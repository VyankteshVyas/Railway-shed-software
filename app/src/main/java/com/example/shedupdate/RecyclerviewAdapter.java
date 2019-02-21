package com.example.shedupdate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shedupdate.restClient.RestClient;
import com.example.shedupdate.restClient.request.ActualLoginRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.RecyclerviewHolder> {
    List<Hero> heroList;
    Context context;
    String username,password,datetype;

    ArrayList<String> locoNumbersi,locoNotesi;
    ArrayList<String> loconumbersing=new ArrayList<>();
    ArrayList<String> loconotesing=new ArrayList<>();

    public RecyclerviewAdapter(List<Hero> heroList, Context context, ArrayList<String> locoNumbersi, ArrayList<String> locoNotesi,String username,String password,String datetype) {
        this.heroList = heroList;
        this.context = context;
        this.datetype=datetype;
        this.username=username;
        this.locoNumbersi = locoNumbersi;
        this.password=password;
//        Log.d("hids",locoNumbersi.toString());

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
//                getnotes();
                Log.d("ramramsiyaram",""+locoNumbersi+""+locoNotesi);


                    FragmentManager manager=((AppCompatActivity)context).getSupportFragmentManager();
//                    FragmentTransaction transaction=manager.beginTransaction();
                    NotesDialog notesDialog=new NotesDialog();
                    Bundle args=new Bundle();

                    args.putString("loco_number",heroList.get(i).getLoco_number());
                    args.putString("username",username);
                    args.putString("password",password);
                    notesDialog.setArguments(args);
                   notesDialog.show(manager,"difidh");
                    Log.d("hids",String.valueOf(heroList.get(i).getLoco_number()));
//                    Toast.makeText(context,locoNotesi.get(j),Toast.LENGTH_LONG).show();

            }
        });
//        if (heroList.get(i).getOut_date()==null){
//            recyclerviewHolder.cardView.setCardBackgroundColor(Color.parseColor("#032F90"));
//            recyclerviewHolder.wd.setTextColor(Color.parseColor("#FFFFFF"));
//            recyclerviewHolder.ws.setTextColor(Color.parseColor("#FFFFFF"));
//            recyclerviewHolder.ff.setTextColor(Color.parseColor("#FFFFFF"));
////            recyclerViewholder.arrvtime.setTextColor(Color.parseColor("#FFFFFF"));
//            recyclerviewHolder.arrvdate.setText(heroList.get(i).getArrival_date()+"|"+heroList.get(i).getArrival_time());
//            recyclerviewHolder.loc.setTextColor(Color.parseColor("#FFFFFF"));
//            recyclerviewHolder.pdc.setTextColor(Color.parseColor("#FFFFFF"));
//            recyclerviewHolder.dw.setTextColor(Color.parseColor("#FFFFFF"));
//            recyclerviewHolder.trainno.setTextColor(Color.parseColor("#FFFFFF"));
//            recyclerviewHolder.locono.setTextColor(Color.parseColor("#FFFFFF"));
//            recyclerviewHolder.a1.setTextColor(Color.parseColor("#FFFFFF"));
//            recyclerviewHolder.schedules.setText(heroList.get(i).getSchedules());
//            recyclerviewHolder.schedules.setTextColor(Color.parseColor("#FFFFFF"));
//            recyclerviewHolder.a2.setTextColor(Color.parseColor("#FFFFFF"));
//            recyclerviewHolder.arrvdate.setTextColor(Color.parseColor("#FFFFFF"));
//            recyclerviewHolder.a3.setTextColor(Color.parseColor("#FFFFFF"));
//            recyclerviewHolder.a4.setTextColor(Color.parseColor("#FFFFFF"));
//            recyclerviewHolder.a5.setTextColor(Color.parseColor("#FFFFFF"));
//
//
//
//            recyclerviewHolder.a6.setTextColor(Color.parseColor("#FFFFFF"));
//            recyclerviewHolder.a7.setTextColor(Color.parseColor("#FFFFFF"));
////            recyclerviewHolder.a8.setTextColor(Color.parseColor("#FFFFFF"));
//            recyclerviewHolder.v1.setBackgroundColor(Color.parseColor("#FFFFFF"));
//            Log.d("itoccured",""+i);
//        }else {
//            recyclerviewHolder.cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
//            recyclerviewHolder.wd.setTextColor(Color.parseColor("#000000"));
//            recyclerviewHolder.ws.setTextColor(Color.parseColor("#000000"));
//            recyclerviewHolder.ff.setTextColor(Color.parseColor("#000000"));
////            recyclerViewholder.arrvtime.setTextColor(Color.parseColor("#000000"));
//            recyclerviewHolder.schedules.setText(heroList.get(i).getSchedules());
//
//            recyclerviewHolder.schedules.setTextColor(Color.parseColor("#000000"));
//            recyclerviewHolder.arrvdate.setTextColor(Color.parseColor("#000000"));
//            recyclerviewHolder.loc.setTextColor(Color.parseColor("#000000"));
//            recyclerviewHolder.pdc.setTextColor(Color.parseColor("#000000"));
//            recyclerviewHolder.dw.setTextColor(Color.parseColor("#000000"));
//            recyclerviewHolder.trainno.setTextColor(Color.parseColor("#000000"));
//            recyclerviewHolder.locono.setTextColor(Color.parseColor("#000000"));
//            recyclerviewHolder.a1.setTextColor(Color.parseColor("#000000"));
//            recyclerviewHolder.a2.setTextColor(Color.parseColor("#000000"));
//            recyclerviewHolder.a3.setTextColor(Color.parseColor("#000000"));
//            recyclerviewHolder.a4.setTextColor(Color.parseColor("#000000"));
//            recyclerviewHolder.a5.setTextColor(Color.parseColor("#000000"));
//            recyclerviewHolder.a6.setTextColor(Color.parseColor("#000000"));
//            recyclerviewHolder.a7.setTextColor(Color.parseColor("#000000"));
////            recyclerViewholder.a8.setTextColor(Color.parseColor("#000000"));
//            recyclerviewHolder.v1.setBackgroundColor(Color.parseColor("#000000"));
//        }
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    public class RecyclerviewHolder extends RecyclerView.ViewHolder{
        TextView arrvdate,arrvtime,dw,loc,ff,wd,pdc,ws,locono,trainno,a1,a2,a3,a4,a5,a6,a7,schedules;
        CardView cardView;
        View v1;
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
            cardView=itemView.findViewById(R.id.card_view);
           a1=itemView.findViewById(R.id.a1);
            a2=itemView.findViewById(R.id.a2);
            a3=itemView.findViewById(R.id.a3);
            a4=itemView.findViewById(R.id.a4);
            a5=itemView.findViewById(R.id.a5);
            schedules=itemView.findViewById(R.id.schedules);
            a6=itemView.findViewById(R.id.a6);
            a7=itemView.findViewById(R.id.a7);
            v1=itemView.findViewById(R.id.v1);

        }
    }

    public void getnotes(){
        loconumbersing.clear();
        loconotesing.clear();
        Call<List<Loginingresponse>> call= RestClient.get().loginreturn(new ActualLoginRequest(username,password));
        call.enqueue(new Callback<List<Loginingresponse>>() {
            @Override
            public void onResponse(Call<List<Loginingresponse>> call, Response<List<Loginingresponse>> response) {
                if (response.isSuccessful()) {

                    List<Loginingresponse> loginResponses=response.body();

//                            Log.d("bhai","rad"+loginResponses.size()+"dij"+loginResponses.get(0).getNotes()+"hellois"+loginResponses.get(0).getLoco_number());
//                            if(loginResponses.size()>=1){
//                                if (loginResponses.size()==1){
//                                    if (loginResponses.get(0).getNotes().toString()=="1"){
//                                        Intent intent=new Intent(Login.this,MainActivity.class);
//                                        startActivity(intent);
//                                    }else {Toast.makeText(Login.this,loginResponses.get(0).getNotes().toString(),Toast.LENGTH_LONG).show();}
//                                }else {
//                                   List<String> rry=loginResponses.
//                                }
//                            }
                    if (loginResponses.get(0).getNotes().equals("0")&&loginResponses.get(0).getLoco_number()==0){
//                                Log.d("exae","If got executed");
                        Toast.makeText(context,"Invalid Username and Password",Toast.LENGTH_LONG).show();
                    }else {
                        if (loginResponses.get(0).getNotes().equals("1")&&loginResponses.get(0).getLoco_number().toString()=="0"){
//                                    Log.d("exae","Ifelseif got executed");
//                            Intent intent=new Intent(Login.this,MainActivity.class);
//                            intent.putExtra("determine","stop");
//                            startActivity(intent);
                        }else {
//                                    Log.d("exae","Ifelseelse got executed");

                            for (Loginingresponse loginingresponse:loginResponses){
                                loconumbersing.add(loginingresponse.getLoco_number().toString());
                                loconotesing.add(loginingresponse.getNotes().toString());




                            }
                                    Log.d("excuseme","dfdjdi"+loconumbersing+"         "+loconotesing);
//                            listtransfer.respond(loconumbers,loconotes);
//                            Intent intent=new Intent(Login.this,MainActivity.class);
//                            intent.putExtra("loconumbers",loconumbers);
//                            intent.putExtra("username",username.getText().toString());
//                            intent.putExtra("password",password.getText().toString());
//                            intent.putExtra("loconotes",loconotes);
                            locoNumbersi=loconumbersing;
                            locoNotesi=loconotesing;

//                                    Toast.makeText(Login.this,loconumbers.toString(),Toast.LENGTH_LONG).show();
                        }
                    }






                } else {
                    Toast.makeText(context, "Error Occured Please try again1", Toast.LENGTH_SHORT).show();
                    Log.d("failurem",response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Loginingresponse>> call, Throwable t) {

                Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
//                Log.d("insidelogin","chala");
//                Call<String> call= RestClient.get().Loginreturn(new ActualLoginRequest(username.getText().toString(),password.getText().toString()));
//                Log.d("beforecallback","chala");
//
//                call.enqueue(new Callback<String>() {
//                    @Override
//                    public void onResponse(Call<String> call, Response<String> response) {
//                        if (response.isSuccessful()) {
////
//                            String a=response.body();
//
////                            Log.d("getdeti",failureAnalysisResponses.get(0).getDetection());
////                    Toast.makeText(getActivity(),failureAnalysisResponses.toString(),Toast.LENGTH_LONG).show();
//                            Log.d("ocuuredsuccesis",a);
//                            Log.d("ocuure",response.toString());
//                            Log.d("dfhuduue",response.body().toString());
//
//
//
//
//                        } else {
//                            Toast.makeText(Login.this, "Error Occured Please try again1", Toast.LENGTH_SHORT).show();
//                            Log.d("failurem",response.toString());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<String> call, Throwable t) {
//                        Log.d("failure",t.getMessage());
//                    }
//                });
    }
}
