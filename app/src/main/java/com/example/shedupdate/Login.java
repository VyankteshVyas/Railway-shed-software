package com.example.shedupdate;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shedupdate.restClient.RestClient;
import com.example.shedupdate.restClient.request.ActualLoginRequest;
import com.example.shedupdate.restClient.request.LoginRequest;
import com.example.shedupdate.restClient.response.LoginResponse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements Listtransfer{


    boolean connected = false;
    ArrayList<String> loconumbers=new ArrayList<>();
    ArrayList<String> loconotes=new ArrayList<>();
    EditText username,password,registeringusername,registeringpassword,registermobilenubmer,usertypespinner;

    Button login,register;
    Listtransfer listtransfer;
    LinearLayout lini,lino;
    TextView signuplink,registerinpro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.staui));
        login=findViewById(R.id.login);
        username=findViewById(R.id.loginid);
        password=findViewById(R.id.password);
        registerinpro=findViewById(R.id.registerinpro);
        signuplink=findViewById(R.id.signuplink);
        lini=findViewById(R.id.lini);
        lino=findViewById(R.id.lino);
        register=findViewById(R.id.register);
        registeringpassword=findViewById(R.id.registeringpassword);
        registeringusername=findViewById(R.id.registeringusername);
        usertypespinner=findViewById(R.id.usertypespinner);
        registermobilenubmer=findViewById(R.id.registermobilenubmer);
        listtransfer=Login.this;
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        String formattedDate = df.format(c);

        Toast.makeText(Login.this,""+df+"   "+formattedDate,Toast.LENGTH_LONG).show();
        Log.d("hloo",""+df+"   "+formattedDate);

//        WifiManager wimanager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        String macAddress = wimanager.getConnectionInfo().getMacAddress();
//        if (macAddress!=null){
//            Toast.makeText(Login.this,"Mac address is "+macAddress,Toast.LENGTH_LONG).show();
//        } else Toast.makeText(Login.this,"Mac address is null",Toast.LENGTH_LONG).show();


        getAuthenticationStatus();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkinternetconnection();

                if (connected==true){
                    Call<List<Loginingresponse>> call=RestClient.get().loginreturn(new ActualLoginRequest(username.getText().toString(),password.getText().toString()));
                    call.enqueue(new Callback<List<Loginingresponse>>() {
                        @Override
                        public void onResponse(Call<List<Loginingresponse>> call, Response<List<Loginingresponse>> response) {
                            if (response.isSuccessful()) {

                                List<Loginingresponse> loginResponses=response.body();

//                            Log.d("bhai","rad"+loginResponses.size()+"dij"+loginResponses.get(0).getNotes()+"hellois"+loginResponses.get(0).getLoco_number());
//                            if(loginResponses.size()>=1){
//                                if (loginResponses.size()==1){
//                                    if (loginResponses.get(0).getNotes().toString()=="1"){
//                                        Intent intent=new Intent(Login.this,MainActivity.class);
//                                        startActivity(intent);
//                                    }else {Toast.makeText(Login.this,loginResponses.get(0).getNotes().toString(),Toast.LENGTH_LONG).show();}
//                                }else {
//                                   List<String> rry=loginResponses.
//                                }
//                            }
                                if (loginResponses.get(0).getLoco_number()==0&&loginResponses.get(0).getValidity()==0&&loginResponses.get(0).getNotes().equals("0")){
//                                Log.d("exae","If got executed");
                                    Toast.makeText(Login.this,"Invalid Username and Password",Toast.LENGTH_LONG).show();
                                }else {
                                    if (loginResponses.get(0).getLoco_number()==0&&loginResponses.get(0).getValidity()==0&&loginResponses.get(0).getNotes().equals("1")&&loginResponses.get(0).getDatewa().equals("0")){
                                        registerinpro.setVisibility(View.VISIBLE);
                                    }else {
                                        if (loginResponses.get(0).getNotes().equals("1")&&loginResponses.get(0).getValidity()==1){
//                                    Log.d("exae","Ifelseif got executed");
                                            Intent intent=new Intent(Login.this,Adminscreen.class);
                                            intent.putExtra("username",username.getText().toString());
                                            intent.putExtra("password",password.getText().toString());
//                                        intent.putExtra("notescontent","0");
                                            startActivity(intent);
                                        }else {
//                                    Log.d("exae","Ifelseelse got executed");


//                                    Log.d("bhai0","dfdjdi"+loconumbers+"         "+loconotes);
                                            listtransfer.respond(loconumbers,loconotes);
                                            Intent intent=new Intent(Login.this,Adminscreen.class);
//                                        intent.putExtra("loconumbers",loconumbers);
//                                        intent.putExtra("notescontent","1");
                                            intent.putExtra("username",username.getText().toString());
                                            intent.putExtra("password",password.getText().toString());
//                                        intent.putExtra("loconotes",loconotes);
                                            startActivity(intent);
//                                    Toast.makeText(Login.this,loconumbers.toString(),Toast.LENGTH_LONG).show();
                                        }
                                    }

                                }






                            } else {
                                Toast.makeText(Login.this, "Error Occured Please try again1", Toast.LENGTH_SHORT).show();
                                Log.d("failurem",response.toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Loginingresponse>> call, Throwable t) {

                            Toast.makeText(Login.this,t.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
//                Log.d("insidelogin","chala");
//                Call<String> call= RestClient.get().Loginreturn(new ActualLoginRequest(username.getText().toString(),password.getText().toString()));
//                Log.d("beforecallback","chala");
//
//                call.enqueue(new Callback<String>() {
//                    @Override
//                    public void onResponse(Call<String> call, Response<String> response) {
//                        if (response.isSuccessful()) {
////
//                            String a=response.body();
//
////                            Log.d("getdeti",failureAnalysisResponses.get(0).getDetection());
////                    Toast.makeText(getActivity(),failureAnalysisResponses.toString(),Toast.LENGTH_LONG).show();
//                            Log.d("ocuuredsuccesis",a);
//                            Log.d("ocuure",response.toString());
//                            Log.d("dfhuduue",response.body().toString());
//
//
//
//
//                        } else {
//                            Toast.makeText(Login.this, "Error Occured Please try again1", Toast.LENGTH_SHORT).show();
//                            Log.d("failurem",response.toString());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<String> call, Throwable t) {
//                        Log.d("failure",t.getMessage());
//                    }
//                });
                }else {
                    Toast.makeText(Login.this,"Please check your internet connection",Toast.LENGTH_LONG).show();
                }
            }
        });
        signuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lino.setVisibility(View.INVISIBLE);
                lini.setVisibility(View.VISIBLE);

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d("insidelogin","chala");
                Call<RegisterResponse> call= RestClient.get().Registerreturn(new LoginRequest(registeringusername.getText().toString(),registeringpassword.getText().toString(),registermobilenubmer.getText().toString(),usertypespinner.getText().toString()));
                Log.d("beforecallback","chala");
                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if (response.isSuccessful()){
                            RegisterResponse a=response.body();

                            Log.d("dfhuduue","djf"+a);
                            Log.d("dfhuduue","difj"+a.getLoco_number());
                            if (a.getLoco_number()==111){
                                Toast.makeText(Login.this,"User Registration Successful",Toast.LENGTH_LONG).show();
                                lino.setVisibility(View.VISIBLE);
                                registerinpro.setVisibility(View.VISIBLE);
                                lini.setVisibility(View.INVISIBLE);
                            }
                            if (a.getLoco_number()==222){
                                Toast.makeText(Login.this,"User with this name already exists",Toast.LENGTH_LONG).show();

                            }
                        }else {
                            Toast.makeText(Login.this, "Error Occured Please try again1", Toast.LENGTH_SHORT).show();

                            Log.d("failurem",response.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {

                        Log.d("failureiy",t.getMessage());
                        Snackbar.make(v,"Oops an error occured",Snackbar.LENGTH_LONG).setAction("Action",null).show();


                    }
                });
//                call.enqueue(new Callback<List<RegisterResponse>>() {
//                    @Override
//                    public void onResponse(Call<List<RegisterResponse>> call, Response<List<RegisterResponse>> response) {
//                        if (response.isSuccessful()) {
////
//                            String a=response.body().toString();
//                            List<RegisterResponse> as=response.body();
//
//                            Log.d("dfhuduue","djf"+as.get(0));
//                            Log.d("dfhuduue","djf"+as.get(0));
//
////                            Log.d("getdeti",failureAnalysisResponses.get(0).getDetection());
////                    Toast.makeText(getActivity(),failureAnalysisResponses.toString(),Toast.LENGTH_LONG).show();
//                            Log.d("ocuuredsuccesis",a);
//                            Log.d("ocuure",response.toString());
//                            Log.d("dfhuduue",response.body().toString());
//
//
//
//
//                        } else {
//                            Toast.makeText(Login.this, "Error Occured Please try again1", Toast.LENGTH_SHORT).show();
//                            Log.d("failurem",response.toString());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<RegisterResponse>> call, Throwable t) {
//                        Log.d("failure",t.getMessage());
//
//                    }
//                });



            }
        });
    }

    private void getAuthenticationStatus() {
        try {
            FileInputStream fileInputStream=openFileInput("admin.txt");
            int read=-1;
            StringBuffer stringBuffer=new StringBuffer();
            while ((read=fileInputStream.read())!=-1){
                stringBuffer.append((char) read);
            }
            Log.d("valueofauthentication",""+stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void respond(List<String> locoNumbers, List<String> locoNotes) {

    }
    public ArrayList<String> getMyData() {
        return loconumbers;
    }

    public void checkinternetconnection(){

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;
    }
}
