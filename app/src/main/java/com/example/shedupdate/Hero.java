package com.example.shedupdate;

public class Hero {
    private String loco_number;
    private String arrival_date;
    private String arrival_time;
    private String dw;
    private String ff;
    private String loc;
    private String wd;
    private String pdc;
    private String ws;
    private String schedules;
    private int train_number;

    public Hero(String loco_number, String arrival_date, String arrival_time, String dw, String ff, String loc, String wd, String ws, int train_number,String pdc,String schedules) {
        this.loco_number = loco_number;
        this.arrival_date = arrival_date;
        this.arrival_time = arrival_time;
        this.dw = dw;
        this.ff = ff;
        this.pdc=pdc;
        this.loc = loc;
        this.wd = wd;
        this.schedules=schedules;
        this.ws = ws;
        this.train_number = train_number;
    }

    public String getPdc() {
        return pdc;
    }

    public String getSchedules() {
        return schedules;
    }

    public void setSchedules(String schedules) {
        this.schedules = schedules;
    }

    public void setPdc(String pdc) {
        this.pdc = pdc;
    }

    public String getLoco_number() {
        return loco_number;
    }

    public void setLoco_number(String loco_number) {
        this.loco_number = loco_number;
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

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getFf() {
        return ff;
    }

    public void setFf(String ff) {
        this.ff = ff;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }

    public int getTrain_number() {
        return train_number;
    }

    public void setTrain_number(int train_number) {
        this.train_number = train_number;
    }
}
