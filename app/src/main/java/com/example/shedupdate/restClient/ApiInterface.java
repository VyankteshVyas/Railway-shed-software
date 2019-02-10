package com.example.shedupdate.restClient;



import com.example.shedupdate.Loginingresponse;
import com.example.shedupdate.RegisterResponse;
import com.example.shedupdate.restClient.request.ActualLoginRequest;
import com.example.shedupdate.restClient.request.LoginRequest;
import com.example.shedupdate.FailureAnalysisResponse;
import com.example.shedupdate.restClient.response.LoginResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by root on 6/1/18.
 */

public interface ApiInterface {

    @POST("registerUser")
    Call<LoginResponse> registerResponse(@Body LoginRequest request);

    @GET("verifyOtp")
    Call<LoginResponse> verifyOTP(@Query("userid") String userid, @Query("otp") String otp);

    @POST("amaan/webapi/aliens/alien/")
    Call<List<LoginResponse>> loginRequest(@Body LoginRequest request);

    @POST("amaan/webapi/fails/fail/")
    Call<List<FailureAnalysisResponse>> FailuAns(@Body LoginRequest request);

    @POST("amaan/webapi/users/userregister")
    Call<RegisterResponse> Registerreturn (@Body LoginRequest request);

    @POST("amaan/webapi/users/user/")
    Call<List<Loginingresponse>> loginreturn (@Body ActualLoginRequest request);





}

