package com.derick.domain;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "User")
@Table(name = "User")
public class User implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotNull
    private String Firstname;

    @Column(nullable = false)
    @NotNull
    private String Lastname;

    @Column(nullable = false)
    @NotNull
    private String Email;

    @Column(nullable = false)
    @NotNull
    private String Phone;

    @Column
    private Date DateRegistered;

    @Column
    private boolean RegistrationConfirmed;

    @Column
    private boolean Locked;

    @Column
    private boolean OtpConfirmed;

    @Column
    private Date OtpDate;

    @Column(nullable = false)
    @NotNull
    private String Password;

    @Column
    private String Otp;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private Set<UploadPrescription> uploadPrescriptions;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private Set<StkPushRequest> stkPushRequests;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private Set<PrescriptionQuotation> quotations;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private Set<CustomerOrder> customerOrders;

    public Set<StkPushRequest> getStkPushRequests() {
        return stkPushRequests;
    }

    public void setStkPushRequests(Set<StkPushRequest> stkPushRequests) {
        this.stkPushRequests = stkPushRequests;
    }

    public Set<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(Set<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public User() {
        //uploadPrescriptions = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<UploadPrescription> getUploadPrescriptions() {
        return uploadPrescriptions;
    }

    public void setUploadPrescriptions(Set<UploadPrescription> uploadPrescriptions) {
        this.uploadPrescriptions = uploadPrescriptions;
    }

    public Set<PrescriptionQuotation> getQuotations() {
        return quotations;
    }

    public void setQuotations(Set<PrescriptionQuotation> quotations) {
        this.quotations = quotations;
    }

    public Date getOtpDate() {
        return OtpDate;
    }

    public void setOtpDate(Date otpDate) {
        OtpDate = otpDate;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
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

    public Date getDateRegistered() {
        return DateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        DateRegistered = dateRegistered;
    }

    public boolean isRegistrationConfirmed() {
        return RegistrationConfirmed;
    }

    public void setRegistrationConfirmed(boolean registrationConfirmed) {
        RegistrationConfirmed = registrationConfirmed;
    }

    public boolean isLocked() {
        return Locked;
    }

    public void setLocked(boolean locked) {
        Locked = locked;
    }

    public boolean isOtpConfirmed() {
        return OtpConfirmed;
    }

    public void setOtpConfirmed(boolean otpConfirmed) {
        OtpConfirmed = otpConfirmed;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getOtp() {
        return Otp;
    }

    public void setOtp(String otp) {
        Otp = otp;
    }





}
