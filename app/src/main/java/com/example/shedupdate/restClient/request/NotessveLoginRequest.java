package com.example.shedupdate.restClient.request;

import com.google.gson.annotations.Expose;

public class NotessveLoginRequest {

    @Expose
    String username;
    Integer loco_number;
    String notes;
    String datewa;

    public NotessveLoginRequest(String username, Integer loco_number, String notes, String datewa) {
        this.username = username;
        this.loco_number = loco_number;
        this.notes = notes;
        this.datewa=datewa;
    }
}
