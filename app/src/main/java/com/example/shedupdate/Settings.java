package com.example.shedupdate;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;


public class Settings extends Fragment {

    RadioGroup settingsradiogroup;
    RadioButton jav05radiobuttonsettings,normaldateradiobuttonsettings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_settings, container, false);
        view.getRootView().setClickable(true);
        view.getRootView().setFocusable(true);
        settingsradiogroup=view.findViewById(R.id.settingsradiogroup);
        jav05radiobuttonsettings=view.findViewById(R.id.jav05radiobuttonsettings);
        normaldateradiobuttonsettings=view.findViewById(R.id.normaldateradiobuttonsettings);
        getAuthenticationStatus();
        settingsradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String radioid =Integer.toString(settingsradiogroup.getCheckedRadioButtonId());
                String text1="1";
                if (radioid.equals(Integer.toString(getActivity().findViewById(R.id.jav05radiobuttonsettings).getId()))){
                    text1="1";
                    File file=null;
                    FileOutputStream fileOutputStream= null;
                    try {
                        file=getActivity().getFilesDir();
                        fileOutputStream = getActivity().openFileOutput("datetype.txt",MODE_PRIVATE);
                        fileOutputStream.write(text1.getBytes());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    Toast.makeText(getActivity(),"Successfully saved at "+file,Toast.LENGTH_LONG).show();
                }else {
                    text1="0";
                    File file=null;
                    FileOutputStream fileOutputStream= null;
                    try {
                        file=getActivity().getFilesDir();
                        fileOutputStream = getActivity().openFileOutput("datetype.txt",MODE_PRIVATE);
                        fileOutputStream.write(text1.getBytes());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    Toast.makeText(getActivity(),"ijieeiehfeih Successfully saved at "+file,Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void getAuthenticationStatus() {
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
                jav05radiobuttonsettings.setChecked(true);
            }else normaldateradiobuttonsettings.setChecked(true);
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


        }
}
