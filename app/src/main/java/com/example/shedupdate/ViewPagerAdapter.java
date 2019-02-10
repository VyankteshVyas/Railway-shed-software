package com.example.shedupdate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    String hello;
    ArrayList<String> locoNumbersis,locoNotesis;
    ArrayList<String> ram,shyam;


    public ViewPagerAdapter(FragmentManager fm, ArrayList<String> locoNumbersi, ArrayList<String> locoNotesi) {
        super(fm);
        locoNumbersis = locoNumbersi;
        locoNotesis = locoNotesi;
        hells(locoNumbersi,locoNotesis);
        Log.d("bhaig031",locoNumbersis+"     "+locoNotesis);
    }

    public void hells(ArrayList<String> r,ArrayList<String> s){
        ram=r;
        shyam=s;
    }



    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            DailyPosition dailyPosition = new DailyPosition();
            i = i + 1;
            Log.d("bhaig032",locoNumbersis+"     "+locoNotesis);
            Bundle bundle = new Bundle();
            bundle.putString("message", "Fragment " + i);
            bundle.putStringArrayList("loconumber",locoNumbersis);
            bundle.putStringArrayList("loconotes",locoNotesis);
            bundle.putString("edttext", "From Activity");
// set Fragmentclass Arguments
            dailyPosition.setArguments(bundle);
            dailyPosition.setArguments(bundle);

            return dailyPosition;
        } else {
            if (i == 1) {
                FailureAnalysis failureAnalysis = new FailureAnalysis();
                i = i + 1;
                Bundle bundle = new Bundle();
                bundle.putString("message", "Fragment " + i);
                failureAnalysis.setArguments(bundle);
                return failureAnalysis;
            } else if (i == 2) {
                BlankFragment materialPlanning = new BlankFragment();
                i = i + 1;
                Bundle bundle = new Bundle();
                bundle.putString("message", "Fragment " + i);
                materialPlanning.setArguments(bundle);
                return materialPlanning;
            }else {MachineAndPlant machineAndPlant=new MachineAndPlant();
            i=i+1;
            Bundle bundle=new Bundle();
            bundle.putString("message","Fragment "+i);
            machineAndPlant.setArguments(bundle);
            return machineAndPlant;
            }
        }

    }

    @Override
    public int getCount() {
        return 4;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0){
            return "Daily Pos";
        }else {if (position==1){
            return "Fail Ana";
        }else if (position==2){
            return "Mat Pla";
        }else return "M&P";}

    }


}
