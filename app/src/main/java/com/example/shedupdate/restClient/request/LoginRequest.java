package com.example.shedupdate.restClient.request;

import com.google.gson.annotations.Expose;

/**
 * Created by root on 6/1/18.
 */

public class LoginRequest {
    @Expose
    String date;
    String date1;
    String date2;
    String username;
    String password;
    String cug;
    String post;

    public LoginRequest(String username, String password, String cug, String post) {
        this.username = username;
        this.password = password;
        this.cug = cug;
        this.post = post;
    }

    public LoginRequest() {
    }

    public LoginRequest(String date1, String date2) {
        this.date1 = date1;
        this.date2 = date2;
    }

    public LoginRequest(String date) {
        this.date = date;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String userid) {
        this.date = userid;
    }


    @Override
    public String toString() {
        return "LoginRequest{" +
                "date='" + date + '\'' +
                '}';
    }
}
