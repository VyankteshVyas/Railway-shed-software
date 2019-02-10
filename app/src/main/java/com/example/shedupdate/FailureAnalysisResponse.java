package com.example.shedupdate;

import com.google.gson.annotations.Expose;

public class FailureAnalysisResponse {


    private String d_p;

    private String detection;

    private String division;

    private String emake;

    private String eqp;

    private String f_date;

    private String h_quarter;

    private String homing_shed;

    private String i_P;

    private String lastft;

    private String lastftd;
    private String lastoh;
    private String loco_number;
    private String name_driver;
    private String s_station;
    private String site;
    private String toc;
    private String train_number;

    public FailureAnalysisResponse(String d_p, String detection, String division, String emake, String eqp, String f_date, String h_quarter, String homing_shed, String i_P, String lastft, String lastftd, String lastoh, String loco_number, String name_driver, String s_station, String site, String toc, String train_number) {
        this.d_p = d_p;
        this.detection = detection;
        this.division = division;
        this.emake = emake;
        this.eqp = eqp;
        this.f_date = f_date;
        this.h_quarter = h_quarter;
        this.homing_shed = homing_shed;
        this.i_P = i_P;
        this.lastft = lastft;
        this.lastftd = lastftd;
        this.lastoh = lastoh;
        this.loco_number = loco_number;
        this.name_driver = name_driver;
        this.s_station = s_station;
        this.site = site;
        this.toc = toc;
        this.train_number = train_number;
    }

    public String getD_p() {
        return d_p;
    }

    public void setD_p(String d_p) {
        this.d_p = d_p;
    }

    public String getDetection() {
        return detection;
    }

    public void setDetection(String detection) {
        this.detection = detection;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getEmake() {
        return emake;
    }

    public void setEmake(String emake) {
        this.emake = emake;
    }

    public String getEqp() {
        return eqp;
    }

    public void setEqp(String eqp) {
        this.eqp = eqp;
    }

    public String getF_date() {
        return f_date;
    }

    public void setF_date(String f_date) {
        this.f_date = f_date;
    }

    public String getH_quarter() {
        return h_quarter;
    }

    public void setH_quarter(String h_quarter) {
        this.h_quarter = h_quarter;
    }

    public String getHoming_shed() {
        return homing_shed;
    }

    public void setHoming_shed(String homing_shed) {
        this.homing_shed = homing_shed;
    }

    public String getI_P() {
        return i_P;
    }

    public void setI_P(String i_P) {
        this.i_P = i_P;
    }

    public String getLastft() {
        return lastft;
    }

    public void setLastft(String lastft) {
        this.lastft = lastft;
    }

    public String getLastftd() {
        return lastftd;
    }

    public void setLastftd(String lastftd) {
        this.lastftd = lastftd;
    }

    public String getLastoh() {
        return lastoh;
    }

    public void setLastoh(String lastoh) {
        this.lastoh = lastoh;
    }

    public String getLoco_number() {
        return loco_number;
    }

    public void setLoco_number(String loco_number) {
        this.loco_number = loco_number;
    }

    public String getName_driver() {
        return name_driver;
    }

    public void setName_driver(String name_driver) {
        this.name_driver = name_driver;
    }

    public String getS_station() {
        return s_station;
    }

    public void setS_station(String s_station) {
        this.s_station = s_station;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getToc() {
        return toc;
    }

    public void setToc(String toc) {
        this.toc = toc;
    }

    public String getTrain_number() {
        return train_number;
    }

    public void setTrain_number(String train_numberl) {
        this.train_number = train_number;
    }

    public FailureAnalysisResponse(){}
}
