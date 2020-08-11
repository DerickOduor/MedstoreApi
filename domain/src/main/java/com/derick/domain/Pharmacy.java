package com.derick.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Pharmacy")
@Table(name = "Pharmacy")
public class Pharmacy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false)
    private String Email;

    @Column(nullable = false)
    private String Phone;

    @Column(nullable = false)
    private String Location;

    @Column(nullable = false)
    private boolean Locked;

    @Column
    private String MobileToken;

    public String getMobileToken() {
        return MobileToken;
    }

    public void setMobileToken(String mobileToken) {
        MobileToken = mobileToken;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "pharmacy_user",
            joinColumns = @JoinColumn(name = "pharmacy_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private Set<UploadPrescription> uploadPrescriptions;

    public Set<UploadPrescription> getUploadPrescriptions() {
        return uploadPrescriptions;
    }

    public void setUploadPrescriptions(Set<UploadPrescription> uploadPrescriptions) {
        this.uploadPrescriptions = uploadPrescriptions;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Column
    private String Latitude;

    @Column
    private String Longitude;

    @Lob
    private byte[] Image;

    @Column(nullable = false)
    private boolean Approved;

    @Column(nullable = false)
    private boolean Deleted;

    @Column(nullable = false)
    private boolean Parent;

    @Column(nullable = false)
    private int ParentId;

    @Column(nullable = false)
    private Date DateRegistered;

    @Column(nullable = false)
    private Date DateEdited;

    @Column
    private Date DateDeleted;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "county_id", referencedColumnName = "id", nullable = false)
    private County county;

    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private Set<Medicine> medicines;

    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private Set<OrderSlip> orderSlips;

    public Set<OrderSlip> getOrderSlips() {
        return orderSlips;
    }

    public void setOrderSlips(Set<OrderSlip> orderSlips) {
        this.orderSlips = orderSlips;
    }

    public Set<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<Medicine> medicines) {
        this.medicines = medicines;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public boolean isLocked() {
        return Locked;
    }

    public void setLocked(boolean locked) {
        Locked = locked;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public boolean isApproved() {
        return Approved;
    }

    public void setApproved(boolean approved) {
        Approved = approved;
    }

    public boolean isDeleted() {
        return Deleted;
    }

    public void setDeleted(boolean deleted) {
        Deleted = deleted;
    }

    public boolean isParent() {
        return Parent;
    }

    public void setParent(boolean parent) {
        Parent = parent;
    }

    public int getParentId() {
        return ParentId;
    }

    public void setParentId(int parentId) {
        ParentId = parentId;
    }

    public Date getDateRegistered() {
        return DateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        DateRegistered = dateRegistered;
    }

    public Date getDateEdited() {
        return DateEdited;
    }

    public void setDateEdited(Date dateEdited) {
        DateEdited = dateEdited;
    }

    public Date getDateDeleted() {
        return DateDeleted;
    }

    public void setDateDeleted(Date dateDeleted) {
        DateDeleted = dateDeleted;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }
}
