package com.derick.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Country")
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String isoCode;

}
