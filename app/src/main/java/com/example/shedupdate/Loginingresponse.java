package com.example.shedupdate;

import com.google.gson.annotations.Expose;

public class Loginingresponse {

    @Expose
    private Integer loco_number;
    @Expose
    private String notes;
    @Expose
    private Integer validity;
    @Expose
    private String datewa;

    public Loginingresponse(Integer loco_number, String notes,Integer validity,String datewa) {
        this.loco_number = loco_number;
        this.notes = notes;
        this.validity=validity;
        this.datewa=datewa;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public String getDatewa() {
        return datewa;
    }

    public void setDatewa(String datewa) {
        this.datewa = datewa;
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

