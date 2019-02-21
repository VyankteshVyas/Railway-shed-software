package com.example.shedupdate;

import com.google.gson.annotations.Expose;

public class NoteSaveResponse {


    @Expose
    private Integer loco_number;

    public NoteSaveResponse(Integer loco_number) {
        this.loco_number = loco_number;
    }

    public Integer getLoco_number() {
        return loco_number;
    }

    public void setLoco_number(Integer loco_number) {
        this.loco_number = loco_number;
    }
}
