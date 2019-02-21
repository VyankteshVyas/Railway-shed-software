package com.example.shedupdate;

public class MachineandPlantResponse {

    private int loco_number;

    private String material;

    private String statuswa;

    public MachineandPlantResponse(int loco_number, String material, String statuswa) {
        this.loco_number = loco_number;
        this.material = material;
        this.statuswa = statuswa;
    }


    public int getLoco_number() {
        return loco_number;
    }

    public void setLoco_number(int loco_number) {
        this.loco_number = loco_number;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getStatuswa() {
        return statuswa;
    }

    public void setStatuswa(String statuswa) {
        this.statuswa = statuswa;
    }
}
