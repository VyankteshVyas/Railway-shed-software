package com.example.shedupdate;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {



    boolean connected = false;

    RecyclerView recyclerView;
    ProgressBar macplanprogressBar;
    TextView macplanerror;
    public BlankFragment() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView=getActivity().findViewById(R.id.macplanrecyclerview);
        macplanerror=getActivity().findViewById(R.id.macplanerror);
        macplanprogressBar=getActivity().findViewById(R.id.macplanprogressBar);
        getMaterialPlanning();

    }

    private void getMaterialPlanning(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(API.Base3_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api=retrofit.create(API.class);

        Call<List<MachineandPlantResponse>> call=api.getmatplannesponses();
        call.enqueue(new Callback<List<MachineandPlantResponse>>() {
            @Override
            public void onResponse(Call<List<MachineandPlantResponse>> call, Response<List<MachineandPlantResponse>> response) {
                List<MachineandPlantResponse> failureAnalysisResponses=response.body();
                List<String> material=new ArrayList<>();
                for(MachineandPlantResponse machineandPlantResponse :failureAnalysisResponses){
                    material.add(machineandPlantResponse.getMaterial());
                }

                MachineandPlantRecyclerviewAdapter machineandPlantRecyclerviewAdapter=new MachineandPlantRecyclerviewAdapter(getActivity(),failureAnalysisResponses);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(machineandPlantRecyclerviewAdapter);

                macplanerror.setVisibility(View.INVISIBLE);
                macplanprogressBar.setVisibility(View.INVISIBLE);


//                Log.d("getdeti","ran"+failureAnalysisResponses.get(0).getMaterial());
//                    Toast.makeText(getActivity(),failureAnalysisResponses.toString(),Toast.LENGTH_LONG).show();
                Log.d("ocuuredsuccesisksii","ramu kaka"+material);
                Log.d("ocuure",response.toString());
                Log.d("dfhuduue",response.body().toString());
            }

            @Override
            public void onFailure(Call<List<MachineandPlantResponse>> call, Throwable t) {

                Log.d("failure",t.getMessage());
                macplanerror.setVisibility(View.VISIBLE);
                macplanprogressBar.setVisibility(View.INVISIBLE);

            }
        });

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
