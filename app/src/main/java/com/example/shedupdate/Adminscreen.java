package com.example.shedupdate;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Adminscreen extends AppCompatActivity {

    CheckBox show;
    CardView settingsadminscreen,webviewa,adminscreendailyposition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminscreen);

        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.staui));
        final ArrayList<String> loconumbers= (ArrayList<String>) getIntent().getSerializableExtra("loconumbers");
        final ArrayList<String> loconotes1= (ArrayList<String>) getIntent().getSerializableExtra("loconotes");
        final String username=getIntent().getStringExtra("username");
        final String password=getIntent().getStringExtra("password");
        settingsadminscreen=findViewById(R.id.settingsadminscreen);
        webviewa=findViewById(R.id.webviewa);
        adminscreendailyposition=findViewById(R.id.adminscreendailyposition);
        adminscreendailyposition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Adminscreen.this,MainActivity.class);
                intent.putExtra("loconumbers",loconumbers);
                intent.putExtra("determine","1");
                intent.putExtra("username",username);
                intent.putExtra("password",password);
                intent.putExtra("loconotes",loconotes1);
                startActivity(intent);
            }
        });
        settingsadminscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager=getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();

                Settings settings=new Settings();
                transaction.add(R.id.scrollviewadminscreen,settings,"Itshalwa");
                transaction.addToBackStack("admintosettings");
                transaction.commit();
            }
        });

        webviewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Adminscreen.this,Webviewa.class);
                startActivity(intent);
            }
        });
//        show=findViewById(R.id.show);
//        show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                String text1="1";
//
//                if (isChecked){
//                    text1="1";
//                    File file=null;
//                    FileOutputStream fileOutputStream= null;
//                    try {
//                        file=getFilesDir();
//                        fileOutputStream = openFileOutput("admin.txt",MODE_PRIVATE);
//                        fileOutputStream.write(text1.getBytes());
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    finally {
//                        try {
//                            fileOutputStream.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    Toast.makeText(Adminscreen.this,"Successfully saved at "+file,Toast.LENGTH_LONG).show();
//                }else {
//                    text1="0";
//                    File file=null;
//                    FileOutputStream fileOutputStream= null;
//                    try {
//                        file=getFilesDir();
//                        fileOutputStream = openFileOutput("admin.txt",MODE_PRIVATE);
//                        fileOutputStream.write(text1.getBytes());
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    finally {
//                        try {
//                            fileOutputStream.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    Toast.makeText(Adminscreen.this,"Successfully saved at "+file,Toast.LENGTH_LONG).show();
//
//                }
//            }
//        });



    }
}
