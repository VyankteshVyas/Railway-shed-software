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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shedupdate.restClient.RestClient;
import com.example.shedupdate.restClient.request.LoginRequest;
import com.example.shedupdate.restClient.response.LoginResponse;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class FailureAnalysis extends Fragment {

    boolean connected = false;


    private DatePickerDialog.OnDateSetListener mDateSetListener;
    String currentDateString;
    String datei1=new String();
    String datei2=new String();
    RecyclerView recyclerView;
    TextView date1,date2,failureanalysiserror;
    ProgressBar failureanalysisprogressBar;
    public FailureAnalysis() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_failure_analysis, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView=getActivity().findViewById(R.id.failureanalysisrecycliverview);
        date1=getActivity().findViewById(R.id.date1);
        date2=getActivity().findViewById(R.id.date2);
        failureanalysisprogressBar=getActivity().findViewById(R.id.failureanalysisprogressBar);
        failureanalysiserror=getActivity().findViewById(R.id.failureanalysiserror);
//        submit=getActivity().findViewById(R.id.subimiit);
        getfairesponses();
    }

    @Override
    public void onResume() {
        super.onResume();
//        Call<List<FailureAnalysisResponse>> call= RestClient.get().FailuAns(new LoginRequest("12-01-02","15-12-27"));
//        call.enqueue(new Callback<List<FailureAnalysisResponse>>() {
//            @Override
//            public void onResponse(Call<List<FailureAnalysisResponse>> call, Response<List<FailureAnalysisResponse>> response) {
//                if (response.isSuccessful()) {
//
//                    List<FailureAnalysisResponse> failureAnalysisResponses=response.body();
//                    Toast.makeText(getActivity(),failureAnalysisResponses.toString(),Toast.LENGTH_LONG).show();
//                    Log.d("ocuuredsucces",failureAnalysisResponses.toString());
////                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
////                    recyclerView.setAdapter(new Recyadapter(loginResponses,getActivity()));
//
//
//                } else {
//                    Toast.makeText(getActivity(), "Error Occured Please try again1", Toast.LENGTH_SHORT).show();
//                    Log.d("failurem",response.toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<FailureAnalysisResponse>> call, Throwable t) {
//
//            }
//        });
        date1.setOnClickListener(new View.OnClickListener() {
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
                        datei1=date;
                        date1.setText(datei1);
                    }
                };
                newdafor();
            }
        });

        date2.setOnClickListener(new View.OnClickListener() {
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
                        datei2=date;
                        date2.setText(date);

                    }
                };
                newdafor();
            }
        });

        date2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(date1.getText().toString())){
                    Toast.makeText(getActivity(),"Please select date 1",Toast.LENGTH_SHORT).show();
                }else {
                    Call<List<FailureAnalysisResponse>> call=RestClient.get().FailuAns(new LoginRequest(datei1,datei2));
                    call.enqueue(new Callback<List<FailureAnalysisResponse>>() {
                        @Override
                        public void onResponse(Call<List<FailureAnalysisResponse>> call, Response<List<FailureAnalysisResponse>> response) {
                            if (response.isSuccessful()) {
//
                                List<FailureAnalysisResponse> failureAnalysisResponses=response.body();

//                            Log.d("getdeti",failureAnalysisResponses.get(0).getDetection());
//                    Toast.makeText(getActivity(),failureAnalysisResponses.toString(),Toast.LENGTH_LONG).show();
                                Log.d("ocuuredsuccesis",failureAnalysisResponses.toString());
                                Log.d("ocuure",response.toString());
                                Log.d("dfhuduue",response.body().toString());

                                failureanalysisprogressBar.setVisibility(View.INVISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                                failureanalysiserror.setVisibility(View.INVISIBLE);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                recyclerView.setAdapter(new FailureAnalysisRecyclerviewAdapter(failureAnalysisResponses,getActivity()));


                            } else {
                                failureanalysisprogressBar.setVisibility(View.INVISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                                failureanalysiserror.setVisibility(View.VISIBLE);
                                failureanalysiserror.setText("Some Error Occured");
                                Toast.makeText(getActivity(), "Error Occured Please try again1", Toast.LENGTH_SHORT).show();
                                Log.d("failurem",response.toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<FailureAnalysisResponse>> call, Throwable t) {
                            failureanalysisprogressBar.setVisibility(View.INVISIBLE);
                            recyclerView.setVisibility(View.VISIBLE);
                            failureanalysiserror.setVisibility(View.VISIBLE);
                            failureanalysiserror.setText("Unable to fetch data at the moment");
                            Log.d("failure",t.getMessage());

                        }
                    });
                }
            }
        });

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Call<List<LoginResponse>> call = RestClient.get().loginRequest(new LoginRequest(daet));
//                Call<List<FailureAnalysisResponse>> call=RestClient.get().FailuAns(new LoginRequest(datei1,datei2));
//                call.enqueue(new Callback<List<FailureAnalysisResponse>>() {
//                    @Override
//                    public void onResponse(Call<List<FailureAnalysisResponse>> call, Response<List<FailureAnalysisResponse>> response) {
//                        if (response.isSuccessful()) {
////
//                            List<FailureAnalysisResponse> failureAnalysisResponses=response.body();
//
////                            Log.d("getdeti",failureAnalysisResponses.get(0).getDetection());
////                    Toast.makeText(getActivity(),failureAnalysisResponses.toString(),Toast.LENGTH_LONG).show();
//                            Log.d("ocuuredsuccesis",failureAnalysisResponses.toString());
//                            Log.d("ocuure",response.toString());
//                            Log.d("dfhuduue",response.body().toString());
//
//                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//                            recyclerView.setAdapter(new FailureAnalysisRecyclerviewAdapter(failureAnalysisResponses,getActivity()));
//
//
//                        } else {
//                            Toast.makeText(getActivity(), "Error Occured Please try again1", Toast.LENGTH_SHORT).show();
//                            Log.d("failurem",response.toString());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<FailureAnalysisResponse>> call, Throwable t) {
//                        Log.d("failure",t.getMessage());
//
//                    }
//                });
//            }
//        });

        getfairesponses();
    }

    private void getfairesponses() {

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(API.Base2_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api=retrofit.create(API.class);
//        Callback<List<FailureAnalysisResponse>> callback=new Callback<List<FailureAnalysisResponse>>() {
//            @Override
//            public void onResponse(Call<List<FailureAnalysisResponse>> call, Response<List<FailureAnalysisResponse>> response) {
//
//                if (response.isSuccessful()) {
////
//                    List<FailureAnalysisResponse> failureAnalysisResponses=response.body();
//                    Toast.makeText(getActivity(),failureAnalysisResponses.toString(),Toast.LENGTH_LONG).show();
//                    Log.d("ocuuredsuccesis",failureAnalysisResponses.toString());
//                    Log.d("ocuure",response.toString());
//                    Log.d("dfhuduue",response.body().toString());
//
////                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
////                    recyclerView.setAdapter(new Recyadapter(loginResponses,getActivity()));
//
//
//                } else {
//                    Toast.makeText(getActivity(), "Error Occured Please try again1", Toast.LENGTH_SHORT).show();
//                    Log.d("failurem",response.toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<FailureAnalysisResponse>> call, Throwable t) {
//
//            }
//        };
        Call<List<FailureAnalysisResponse>> call=api.getfairesponses();
        call.enqueue(new Callback<List<FailureAnalysisResponse>>() {
            @Override
            public void onResponse(Call<List<FailureAnalysisResponse>> call, Response<List<FailureAnalysisResponse>> response) {
                if (response.isSuccessful()) {
//
                    List<FailureAnalysisResponse> failureAnalysisResponses=response.body();

//                    Log.d("getdeti",failureAnalysisResponses.get(0).getDetection());
//                    Toast.makeText(getActivity(),failureAnalysisResponses.toString(),Toast.LENGTH_LONG).show();
                    Log.d("ocuuredsuccesis",failureAnalysisResponses.toString());
                    Log.d("ocuure",response.toString());
                    Log.d("dfhuduue",response.body().toString());


                    failureanalysisprogressBar.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    if(failureAnalysisResponses.isEmpty()){
                        failureanalysiserror.setVisibility(View.VISIBLE);
                        failureanalysiserror.setText("No data available");
                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(new FailureAnalysisRecyclerviewAdapter(failureAnalysisResponses,getActivity()));


                } else {
                    failureanalysisprogressBar.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    failureanalysiserror.setVisibility(View.VISIBLE);
                    failureanalysiserror.setText("Some Error Occured");
                    Toast.makeText(getActivity(), "Error Occured Please try again1", Toast.LENGTH_SHORT).show();
                    Log.d("failurem",response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<FailureAnalysisResponse>> call, Throwable t) {

                failureanalysisprogressBar.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                failureanalysiserror.setVisibility(View.VISIBLE);
                failureanalysiserror.setText("Unable to fetch data at the moment");
                Log.d("failure",t.getMessage());
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
