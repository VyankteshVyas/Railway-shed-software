package com.example.shedupdate.restClient.request;

import com.google.gson.annotations.Expose;

public class ActualLoginRequest {

    @Expose
    String username;
    String password;


    public ActualLoginRequest(){}

    public ActualLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
