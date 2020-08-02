package com.derick.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "County")
@Table(name = "County")
public class County implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false)
    private String Code;

    @OneToMany(mappedBy = "county", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private Set<Pharmacy> pharmacies;

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

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public Set<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(Set<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }
}
