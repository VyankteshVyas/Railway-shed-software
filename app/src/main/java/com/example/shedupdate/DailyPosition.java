package com.example.shedupdate;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.shedupdate.restClient.RestClient;
import com.example.shedupdate.restClient.request.LoginRequest;
import com.example.shedupdate.restClient.response.LoginResponse;

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


    ArrayList<String> loconumbers;
    ArrayList<String> loconotes;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    String currentDateString;
    private static final String TAG = "MainActivity";
    String daet=new String();
    TextView textView;
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
            loconotes=getArguments().getStringArrayList("loconotes");
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


        getHeroes();
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
                Call<List<LoginResponse>> call = RestClient.get().loginRequest(new LoginRequest(daet));
                call.enqueue(new Callback<List<LoginResponse>>() {
                    @Override
                    public void onResponse(Call<List<LoginResponse>> call, Response<List<LoginResponse>> response) {
                        if (response.isSuccessful()) {

                            List<LoginResponse> loginResponses=response.body();
//                            Toast.makeText(getActivity(),loginResponses.toString(),Toast.LENGTH_LONG).show();
                            Log.d("ocuuredsucces",loginResponses.toString());

                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(new Recyadapter(loginResponses,getActivity(),loconumbers,loconotes));


                        } else {
                            Toast.makeText(getActivity(), "Error Occured Please try again1", Toast.LENGTH_SHORT).show();
                            Log.d("failurem",response.toString());
                        }

                    }

                    @Override
                    public void onFailure(Call<List<LoginResponse>> call, Throwable t) {

                    }
                });
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
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(new RecyclerviewAdapter(heroList,getActivity(),loconumbers,loconotes));



            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
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


}
