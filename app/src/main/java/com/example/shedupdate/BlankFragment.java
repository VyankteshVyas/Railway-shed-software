package com.example.shedupdate;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


    RecyclerView recyclerView;
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
        recyclerView=getActivity().findViewById(R.id.materialplanningrecyclerview);
        getMaterialPlanning();

    }

    private void getMaterialPlanning(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(API.Base3_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api=retrofit.create(API.class);

        Call<List<MaterialPlanningResponse>> call=api.getmatplannesponses();
        call.enqueue(new Callback<List<MaterialPlanningResponse>>() {
            @Override
            public void onResponse(Call<List<MaterialPlanningResponse>> call, Response<List<MaterialPlanningResponse>> response) {
                List<MaterialPlanningResponse> failureAnalysisResponses=response.body();
                List<String> material=new ArrayList<>();
                for(MaterialPlanningResponse materialPlanningResponse:failureAnalysisResponses){
                    material.add(materialPlanningResponse.getMaterial());
                }

                Log.d("getdeti","ran"+failureAnalysisResponses.get(0).getMaterial());
//                    Toast.makeText(getActivity(),failureAnalysisResponses.toString(),Toast.LENGTH_LONG).show();
                Log.d("ocuuredsuccesisksii","ramu kaka"+material);
                Log.d("ocuure",response.toString());
                Log.d("dfhuduue",response.body().toString());
            }

            @Override
            public void onFailure(Call<List<MaterialPlanningResponse>> call, Throwable t) {

                Log.d("failure",t.getMessage());
            }
        });

    }
}
