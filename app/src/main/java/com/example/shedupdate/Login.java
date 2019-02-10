package com.example.shedupdate;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements Listtransfer{

    ArrayList<String> loconumbers=new ArrayList<>();
    ArrayList<String> loconotes=new ArrayList<>();
    EditText username,password,registeringusername,registeringpassword,registermobilenubmer;
    Spinner usertypespinner;
    Button login,register;
    Listtransfer listtransfer;
    LinearLayout lini,lino;
    TextView signuplink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.staui));
        login=findViewById(R.id.login);
        username=findViewById(R.id.loginid);
        password=findViewById(R.id.password);
        signuplink=findViewById(R.id.signuplink);
        lini=findViewById(R.id.lini);
        lino=findViewById(R.id.lino);
        register=findViewById(R.id.register);
        registeringpassword=findViewById(R.id.registeringpassword);
        registeringusername=findViewById(R.id.registeringusername);
        usertypespinner=findViewById(R.id.usertypespinner);
        registermobilenubmer=findViewById(R.id.registermobilenubmer);
        listtransfer=Login.this;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                            if (loginResponses.get(0).getNotes().equals("0")&&loginResponses.get(0).getLoco_number()==0){
//                                Log.d("exae","If got executed");
                                Toast.makeText(Login.this,"Invalid Username and Password",Toast.LENGTH_LONG).show();
                            }else {
                                if (loginResponses.get(0).getNotes().equals("1")&&loginResponses.get(0).getLoco_number().toString()=="0"){
//                                    Log.d("exae","Ifelseif got executed");
                                    Intent intent=new Intent(Login.this,MainActivity.class);
                                    intent.putExtra("determine","stop");
                                    startActivity(intent);
                                }else {
//                                    Log.d("exae","Ifelseelse got executed");

                                    for (Loginingresponse loginingresponse:loginResponses){
                                        loconumbers.add(loginingresponse.getLoco_number().toString());
                                        loconotes.add(loginingresponse.getNotes().toString());




                                    }
//                                    Log.d("bhai0","dfdjdi"+loconumbers+"         "+loconotes);
                                    listtransfer.respond(loconumbers,loconotes);
                                    Intent intent=new Intent(Login.this,MainActivity.class);
                                    intent.putExtra("loconumbers",loconumbers);
                                    intent.putExtra("determine","go");
                                    intent.putExtra("loconotes",loconotes);
                                    startActivity(intent);
//                                    Toast.makeText(Login.this,loconumbers.toString(),Toast.LENGTH_LONG).show();
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
            public void onClick(View v) {
                Log.d("insidelogin","chala");
                Call<RegisterResponse> call= RestClient.get().Registerreturn(new LoginRequest(registeringusername.getText().toString(),registeringpassword.getText().toString(),registermobilenubmer.getText().toString(),"cmwa"));
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

                        Log.d("failure",t.getMessage());
                        Toast.makeText(Login.this,t.getMessage(),Toast.LENGTH_LONG).show();

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

    @Override
    public void respond(List<String> locoNumbers, List<String> locoNotes) {

    }
    public ArrayList<String> getMyData() {
        return loconumbers;
    }
}
