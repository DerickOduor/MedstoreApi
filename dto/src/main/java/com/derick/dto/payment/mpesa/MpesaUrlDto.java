package com.derick.dto.payment.mpesa;

public class MpesaUrlDto {
    private int id;
    private String Name;
    private String Live;
    private String Sandbox;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLive() {
        return Live;
    }

    public void setLive(String live) {
        Live = live;
    }

    public String getSandbox() {
        return Sandbox;
    }

    public void setSandbox(String sandbox) {
        Sandbox = sandbox;
    }
}
