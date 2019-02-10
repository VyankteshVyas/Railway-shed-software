package com.example.shedupdate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FailureAnalysisRecyclerviewAdapter extends RecyclerView.Adapter<FailureAnalysisRecyclerviewAdapter.FailureAnalysisRecyclerviewhoder> {

    List<FailureAnalysisResponse> failureAnalysisResponseList;
    Context context;

    public FailureAnalysisRecyclerviewAdapter(List<FailureAnalysisResponse> failureAnalysisResponseList, Context context) {
        this.failureAnalysisResponseList = failureAnalysisResponseList;
        this.context = context;
    }

    @NonNull
    @Override
    public FailureAnalysisRecyclerviewhoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.failureanalysissinglecardview,viewGroup,false);
        FailureAnalysisRecyclerviewhoder failureAnalysisRecyclerviewhoder=new FailureAnalysisRecyclerviewhoder(view);
        return failureAnalysisRecyclerviewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull FailureAnalysisRecyclerviewhoder failureAnalysisRecyclerviewhoder, int i) {
        failureAnalysisRecyclerviewhoder.trainno.setText(failureAnalysisResponseList.get(i).getTrain_number());
        failureAnalysisRecyclerviewhoder.directpunctuality.setText(failureAnalysisResponseList.get(i).getD_p());
        failureAnalysisRecyclerviewhoder.detection.setText(failureAnalysisResponseList.get(i).getDetection());
        failureAnalysisRecyclerviewhoder.division.setText(failureAnalysisResponseList.get(i).getDivision());
        failureAnalysisRecyclerviewhoder.emake.setText(failureAnalysisResponseList.get(i).getEmake());
        failureAnalysisRecyclerviewhoder.eqp.setText(failureAnalysisResponseList.get(i).getEqp());
        failureAnalysisRecyclerviewhoder.failuredate.setText(failureAnalysisResponseList.get(i).getF_date());
        failureAnalysisRecyclerviewhoder.headquater.setText(failureAnalysisResponseList.get(i).getH_quarter());
        failureAnalysisRecyclerviewhoder.homingshed.setText(failureAnalysisResponseList.get(i).getHoming_shed());
        failureAnalysisRecyclerviewhoder.indirectpunctuality.setText(failureAnalysisResponseList.get(i).getI_P());
        failureAnalysisRecyclerviewhoder.lastfittment.setText(failureAnalysisResponseList.get(i).getLastft());
        failureAnalysisRecyclerviewhoder.lastfittmentdate.setText(failureAnalysisResponseList.get(i).getLastftd());
        failureAnalysisRecyclerviewhoder.lastoh.setText(failureAnalysisResponseList.get(i).getLastoh());
        failureAnalysisRecyclerviewhoder.locono.setText(failureAnalysisResponseList.get(i).getLoco_number());
        failureAnalysisRecyclerviewhoder.drivername.setText(failureAnalysisResponseList.get(i).getName_driver());
        failureAnalysisRecyclerviewhoder.selectionstation.setText(failureAnalysisResponseList.get(i).getS_station());
        failureAnalysisRecyclerviewhoder.site.setText(failureAnalysisResponseList.get(i).getSite());
        failureAnalysisRecyclerviewhoder.timeofoccurence.setText(failureAnalysisResponseList.get(i).getToc());
    }

    @Override
    public int getItemCount() {
        return failureAnalysisResponseList.size();
    }

    public class FailureAnalysisRecyclerviewhoder extends RecyclerView.ViewHolder{
        TextView trainno,directpunctuality,detection,division,emake,eqp,failuredate,headquater,homingshed,indirectpunctuality,lastfittment,lastfittmentdate,lastoh,locono,drivername,selectionstation,site,timeofoccurence;
        public FailureAnalysisRecyclerviewhoder(@NonNull View itemView) {
            super(itemView);
            trainno=itemView.findViewById(R.id.trainiNs);
            directpunctuality=itemView.findViewById(R.id.dirpunc);
            detection=itemView.findViewById(R.id.detection);
            division=itemView.findViewById(R.id.division);
            emake=itemView.findViewById(R.id.emake);
            eqp=itemView.findViewById(R.id.eqp);
            failuredate=itemView.findViewById(R.id.failurerdate);
            headquater=itemView.findViewById(R.id.headquaters);
            homingshed=itemView.findViewById(R.id.homeshed);
            indirectpunctuality=itemView.findViewById(R.id.indirectpunctuality);
            lastfittment=itemView.findViewById(R.id.lastfittment);
            lastfittmentdate=itemView.findViewById(R.id.lastfittmentdate);
            lastoh=itemView.findViewById(R.id.lastoh);
            locono=itemView.findViewById(R.id.lociNumber);
            drivername=itemView.findViewById(R.id.driviname);
            selectionstation=itemView.findViewById(R.id.SelectionStation);
            site=itemView.findViewById(R.id.Site);
            timeofoccurence=itemView.findViewById(R.id.Timeofocuurence);
        }
    }
}
