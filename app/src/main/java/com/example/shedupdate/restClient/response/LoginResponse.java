package com.example.shedupdate.restClient.response;

import com.google.gson.annotations.Expose;

public class LoginResponse {

    @Expose
    private int loco_number;
    @Expose
    private String ff;
    @Expose
    private String dw;
    @Expose
    private String pdc;
    @Expose
    private String arrival_date;
    @Expose
    private String arrival_time;
    @Expose
    private String schedules;
    @Expose
    private String loc;
    @Expose
    private String ws;
    @Expose
    private String wd;
    @Expose
    private String out_date;
    @Expose
    private String out_time;
    @Expose
    private int train_number;

    public LoginResponse() {

    }


    public LoginResponse(int loco_number, String ff, String dw, String pdc, String arrival_date, String arrival_time, String schedules, String loc, String ws, String wd, String out_date, String out_time, int train_number) {
        this.loco_number = loco_number;
        this.ff = ff;
        this.dw = dw;
        this.pdc = pdc;
        this.arrival_date = arrival_date;
        this.arrival_time = arrival_time;
        this.schedules = schedules;
        this.loc = loc;
        this.ws = ws;
        this.wd = wd;
        this.out_date = out_date;
        this.out_time = out_time;
        this.train_number = train_number;
    }

    public int getLoco_number() {
        return loco_number;
    }

    public void setLoco_number(int loco_number) {
        this.loco_number = loco_number;
    }

    public String getFf() {
        return ff;
    }

    public void setFf(String ff) {
        this.ff = ff;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getPdc() {
        return pdc;
    }

    public void setPdc(String pdc) {
        this.pdc = pdc;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getSchedules() {
        return schedules;
    }

    public void setSchedules(String schedules) {
        this.schedules = schedules;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getOut_date() {
        return out_date;
    }

    public void setOut_date(String out_date) {
        this.out_date = out_date;
    }

    public String getOut_time() {
        return out_time;
    }

    public void setOut_time(String out_time) {
        this.out_time = out_time;
    }

    public int getTrain_number() {
        return train_number;
    }

    public void setTrain_number(int train_number) {
        this.train_number = train_number;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "loco_number=" + loco_number +
                ", ff='" + ff + '\'' +
                ", dw='" + dw + '\'' +
                ", pdc='" + pdc + '\'' +
                ", arrival_date='" + arrival_date + '\'' +
                ", arrival_time='" + arrival_time + '\'' +
                ", schedules='" + schedules + '\'' +
                ", loc='" + loc + '\'' +
                ", ws='" + ws + '\'' +
                ", wd='" + wd + '\'' +
                ", out_date='" + out_date + '\'' +
                ", out_time='" + out_time + '\'' +
                ", train_number=" + train_number +
                '}';
    }
}
