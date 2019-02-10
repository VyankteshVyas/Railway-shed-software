package com.example.shedupdate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("loco_number")
    @Expose
    private int loco_number;

    public RegisterResponse(int loco_number) {
        this.loco_number = loco_number;
    }

    public int getLoco_number() {
        return loco_number;
    }

    public void setLoco_number(int loco_number) {
        this.loco_number = loco_number;
    }
}
