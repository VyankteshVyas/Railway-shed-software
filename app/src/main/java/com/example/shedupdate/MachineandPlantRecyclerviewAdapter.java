package com.example.shedupdate;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MachineandPlantRecyclerviewAdapter extends RecyclerView.Adapter<MachineandPlantRecyclerviewAdapter.MachineandPlantViewHolder> {

    Context context;
    List<MachineandPlantResponse> material;

    public MachineandPlantRecyclerviewAdapter(Context context, List<MachineandPlantResponse> material) {
        this.context = context;
        this.material = material;
    }

    @NonNull
    @Override
    public MachineandPlantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.machineandplantsinglerow,viewGroup,false);
        MachineandPlantRecyclerviewAdapter.MachineandPlantViewHolder recyclerviewHolder=new MachineandPlantRecyclerviewAdapter.MachineandPlantViewHolder(view);
        return recyclerviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MachineandPlantViewHolder machineandPlantViewHolder, int i) {
        machineandPlantViewHolder.no.setText(Integer.toString(i+1));
        machineandPlantViewHolder.condition.setText(material.get(i).getStatuswa());
        machineandPlantViewHolder.labiel.setText(material.get(i).getMaterial());
        machineandPlantViewHolder.condition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeresponse(1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return material.size();
    }

    public class MachineandPlantViewHolder extends RecyclerView.ViewHolder{
        TextView labiel,no,condition;

        public MachineandPlantViewHolder(@NonNull View itemView) {
            super(itemView);

            labiel=itemView.findViewById(R.id.labiel);
            no=itemView.findViewById(R.id.no);
            condition=itemView.findViewById(R.id.condition);
        }
    }

    public void changeresponse(int i){

    }
}
