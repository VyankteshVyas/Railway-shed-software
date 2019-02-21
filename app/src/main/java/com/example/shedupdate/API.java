package com.example.shedupdate;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    //192.168.43.162:8080
    //String BASE_URL = "http://132.148.83.221:8080/amaan/webapi/aliens/alien/";
    //String Base2_URL="http://132.148.83.221:8080/amaan/webapi/fails/fail/";
    String BASE_URL = "http://132.148.83.221:8080/amaan/webapi/aliens/alien/";
    String Base2_URL="http://132.148.83.221:8080/amaan/webapi/fails/fail/";
    String Base3_URL="http://132.148.83.221:8080/amaan/webapi/materials/";

    @GET("101")
    Call<List<Hero>> getHeroes();

    @GET("101")
    Call<List<FailureAnalysisResponse>> getfairesponses();


    @GET("material")
    Call<List<MachineandPlantResponse>> getmatplannesponses();



}
