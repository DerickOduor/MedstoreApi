package com.derick.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "SystemParameter")
@Table(name = "SystemParameter")
public class SystemParameter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false)
    private String Value;

    @Column
    private boolean Status;

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

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
