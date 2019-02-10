package com.example.shedupdate;

import com.google.gson.annotations.Expose;

public class Loginingresponse {

    @Expose
    private Integer loco_number;
    @Expose
    private String notes;

    public Loginingresponse(Integer loco_number, String notes) {
        this.loco_number = loco_number;
        this.notes = notes;
    }

    public Integer getLoco_number() {
        return loco_number;
    }

    public void setLoco_number(Integer loco_number) {
        this.loco_number = loco_number;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

