package com.derick.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "MpesaUrl")
@Table(name = "MpesaUrl")
public class MpesaUrl implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false)
    private String Live;

    @Column(nullable = false)
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
