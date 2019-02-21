package com.example.shedupdate;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
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

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    RecyclerView recyclerView;
    Toolbar toolo;
    String daet=new String();
    Button Submit;
    String currentDateString;
    private static final String TAG = "MainActivity";

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.staui));

        ArrayList<String> loconumbers= (ArrayList<String>) getIntent().getSerializableExtra("loconumbers");
        ArrayList<String> loconotes1= (ArrayList<String>) getIntent().getSerializableExtra("loconotes");
        String username=getIntent().getStringExtra("username");
        String password=getIntent().getStringExtra("password");
        viewPager=findViewById(R.id.pager);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),loconumbers,loconotes1,username,password);
        viewPager.setAdapter(viewPagerAdapter);
        toolbar=findViewById(R.id.toolb);
        tabLayout=findViewById(R.id.tabs);
        setSupportActionBar(toolbar);

        tabLayout.setupWithViewPager(viewPager);





//        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),loconumbers,loconotes1);

        Log.d("bhai01","dfdj02di"+loconumbers+"         "+loconotes1);
//        DailyPosition dailyPosition=new DailyPosition();
//        dailyPosition.hello(loconumbers,loconotes1);



//        getHeroes();
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                mDateSetListener = new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                        month = month + 1;
//                        Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
//
//                        String das=Integer.toString(day);
//                        if (das.length()==1){
//                            das="0"+das;
//                        }
//                        String mas=Integer.toString(month);
//                        if (mas.length()==1){
//                            mas="0"+mas;
//                        }
//                        String date = year + "-" + mas + "-" + das;
//                        daet=date;
//                        textView.setText(date);
//
//                    }
//                };
//                newdafor();
//            }
//        });
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
//                            Toast.makeText(MainActivity.this,loginResponses.toString(),Toast.LENGTH_LONG).show();
//                            Log.d("ocuuredsucces",loginResponses.toString());
//                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                            recyclerView.setAdapter(new Recyadapter(loginResponses,MainActivity.this));
//
//
//                        } else {
//                            Toast.makeText(MainActivity.this, "Error Occured Please try again1", Toast.LENGTH_SHORT).show();
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
    private void getHeroes(){
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
                Log.d("responsebody",s);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                recyclerView.setAdapter(new RecyclerviewAdapter(heroList,MainActivity.this));

            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
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
    public void newdafor(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                MainActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Logout_action_button:
                logout();

                return true;
            case R.id.action_setting_button:
                moveToAccountSetup();


            default:return false;
        }
    }

    private void logout() {
        Intent intent=new Intent(MainActivity.this,Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }

    private void moveToAccountSetup() {
        Intent intent=new Intent(MainActivity.this,Adminscreen.class);
        startActivity(intent);
        finish();
    }
}
