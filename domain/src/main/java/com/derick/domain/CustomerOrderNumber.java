package com.derick.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "CustomerOrderNumber")
@Table(name = "CustomerOrderNumber")
public class CustomerOrderNumber implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String Number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
