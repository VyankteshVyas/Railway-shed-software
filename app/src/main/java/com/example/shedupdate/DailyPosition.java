package com.example.shedupdate;


import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shedupdate.restClient.RestClient;
import com.example.shedupdate.restClient.request.ActualLoginRequest;
import com.example.shedupdate.restClient.request.LoginRequest;
import com.example.shedupdate.restClient.response.LoginResponse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class DailyPosition extends Fragment implements DatePickerDialog.OnDateSetListener {

    boolean connected = false;

    RelativeLayout nointerconnectionview;
    CardView nointernetconnectioncardview;
    ArrayList<String> loconumbers;
    ArrayList<String> loconotes;
    ArrayList<String> loconotesdatewa;
    String username,password;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    String currentDateString;
    private static final String TAG = "MainActivity";
    String daet=new String();
    ProgressBar dailypositionprogressBar;
    TextView textView,dialypositionerror;
    RecyclerView recyclerView;

    public DailyPosition() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle=this.getArguments();
        if (bundle!=null){
            String strtext = getArguments().getString("edttext");
            loconumbers=getArguments().getStringArrayList("loconumber");
            username=getArguments().getString("username");
            loconotes=getArguments().getStringArrayList("loconotes");
            password=getArguments().getString("password");
            Log.d("bhai02"," "+strtext);
//            Log.d("radif","df"+loconumbers.toString());
        }

        return inflater.inflate(R.layout.fragment_daily_position, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView=getActivity().findViewById(R.id.datie);
        recyclerView=getActivity().findViewById(R.id.recyclerview);
        dialypositionerror=getActivity().findViewById(R.id.dialypositionerror);
        dailypositionprogressBar=getActivity().findViewById(R.id.dailypositionprogressBar);
        nointerconnectionview=getActivity().findViewById(R.id.nointerconnectionview);
        nointernetconnectioncardview=getActivity().findViewById(R.id.nointernetconnectioncardview);
//        nointerconnectionview.setVisibility(View.VISIBLE);
        Log.d("dailypo","activitycreated");


        checkinternetconnection();
        if (connected!=false){

//            getHeroes();
            Log.d("dailypo","activitycreated"+connected);
            nointerconnectionview.setVisibility(View.INVISIBLE);
            Log.d("dailypo","Onresume");
            dialypositionerror.setVisibility(View.INVISIBLE);
            dailypositionprogressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getActivity(),"1111111dailypo",Toast.LENGTH_SHORT);
        }else {
            Log.d("dailypo","activitycreated"+connected);
            Log.d("dailypo","Onresume");
            nointerconnectionview.setVisibility(View.VISIBLE);
            dialypositionerror.setVisibility(View.INVISIBLE);
            dailypositionprogressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getActivity(),"1111111dailypo",Toast.LENGTH_SHORT);
        }




        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                        String das=Integer.toString(day);
                        if (das.length()==1){
                            das="0"+das;
                        }
                        String mas=Integer.toString(month);
                        if (mas.length()==1){
                            mas="0"+mas;
                        }
                        String date = year + "-" + mas + "-" + das;
                        daet=date;
                        textView.setText(date);

                    }
                };
                newdafor();
            }
        });

        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                nointerconnectionview.setVisibility(View.VISIBLE);
                checkinternetconnection();
                if (connected!=false){

                    Call<List<LoginResponse>> call = RestClient.get().loginRequest(new LoginRequest(daet));
                    call.enqueue(new Callback<List<LoginResponse>>() {
                        @Override
                        public void onResponse(Call<List<LoginResponse>> call, Response<List<LoginResponse>> response) {

                            if (response.isSuccessful()) {

                                List<LoginResponse> loginResponses=response.body();
//                            Toast.makeText(getActivity(),loginResponses.toString(),Toast.LENGTH_LONG).show();
                                Log.d("ocuuredsucces",loginResponses.toString());
//                            Log.d("ocuuredsuccesis",loconumbers.toString());
//                            Log.d("ocuuredsuccesis",loconotes.toString());


                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                recyclerView.setAdapter(new Recyadapter(loginResponses,getActivity(),loconumbers,loconotes,username,password));


                            } else {
                                Toast.makeText(getActivity(), "Error Occured Please try again1", Toast.LENGTH_SHORT).show();
                                Log.d("failurem",response.toString());
                            }

                        }

                        @Override
                        public void onFailure(Call<List<LoginResponse>> call, Throwable t) {

                            //shri vinod kumar Sr. dme ratlam
                        }
                    });
                    Log.d("dailypo","activitycreated"+connected);
                    nointerconnectionview.setVisibility(View.INVISIBLE);
                    Log.d("dailypo","Onresume");
                    dialypositionerror.setVisibility(View.INVISIBLE);
                    dailypositionprogressBar.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(),"1111111dailypo",Toast.LENGTH_SHORT);
                }else {
                    Log.d("dailypo","activitycreated"+connected);
                    Log.d("dailypo","Onresume");
                    dialypositionerror.setVisibility(View.INVISIBLE);
                    dailypositionprogressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(),"1111111dailypo",Toast.LENGTH_SHORT);
                }

            }
        });
//        Submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Call<List<LoginResponse>> call = RestClient.get().loginRequest(new LoginRequest(daet));
//                call.enqueue(new Callback<List<LoginResponse>>() {
//                    @Override
//                    public void onResponse(Call<List<LoginResponse>> call, Response<List<LoginResponse>> response) {
//                        if (response.isSuccessful()) {
//
//                            List<LoginResponse> loginResponses=response.body();
////                            Toast.makeText(getActivity(),loginResponses.toString(),Toast.LENGTH_LONG).show();
//                            Log.d("ocuuredsucces",loginResponses.toString());
//                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//                            recyclerView.setAdapter(new Recyadapter(loginResponses,getActivity()));
//
//
//                        } else {
//                            Toast.makeText(getActivity(), "Error Occured Please try again1", Toast.LENGTH_SHORT).show();
//                            Log.d("failurem",response.toString());
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<LoginResponse>> call, Throwable t) {
//
//                    }
//                });
//            }
//        });
    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        currentDateString= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        textView.setText(currentDateString);
    }

    private void getHeroes(){



        loconumbers=getArguments().getStringArrayList("loconumber");
        loconotes=getArguments().getStringArrayList("loconotes");
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api=retrofit.create(API.class);
        Call<List<Hero>> call=api.getHeroes();
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                List<Hero> heroList=response.body();
                String s=response.body().toString();
                Log.d("responsebody",response.toString());
                Log.d("responseibodyi",s);
                String h="1";
                FileInputStream fileInputStream=null;
                try {
                    fileInputStream=getActivity().openFileInput("datetype.txt");
                    int read=-1;
                    StringBuffer stringBuffer=new StringBuffer();
                    while ((read=fileInputStream.read())!=-1){
                        stringBuffer.append((char) read);
                    }
                    Log.d("valueofauthentication",""+stringBuffer.toString());
                    if (stringBuffer.toString().equals("1")){
                        h="1";
                    }else h="0";
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }catch (NullPointerException n){
                        Toast.makeText(getActivity(),"It's null pointero",Toast.LENGTH_LONG).show();
                    }
                }

                if (heroList.isEmpty()){
                    Toast.makeText(getActivity(),"It is empty",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getActivity(),"It is not empty",Toast.LENGTH_LONG).show();

                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(new RecyclerviewAdapter(heroList,getActivity(),loconumbers,loconotes,username,password,h));
                dailypositionprogressBar.setVisibility(View.INVISIBLE);
                dialypositionerror.setVisibility(View.INVISIBLE);



            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
                dialypositionerror.setVisibility(View.VISIBLE);
                dailypositionprogressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
    public void newdafor(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                getActivity(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void checkinternetconnection(){

        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;
    }





}
