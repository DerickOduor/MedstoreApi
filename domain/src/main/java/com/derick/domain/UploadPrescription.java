package com.derick.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity(name = "UploadPrescription")
@Table(name = "UploadPrescription")
public class UploadPrescription implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    @Column(nullable = false)
    @NotNull
    private byte[] file;

    @Column
    private String Narration;

    @Column
    @NotNull
    private Date DateUploaded;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "prescription")
    private PrescriptionQuotation prescriptionQuotation;

    public UploadPrescription() {
    }

    public String getNarration() {
        return Narration;
    }

    public void setNarration(String narration) {
        Narration = narration;
    }

    public Date getDateUploaded() {
        return DateUploaded;
    }

    public void setDateUploaded(Date dateUploaded) {
        DateUploaded = dateUploaded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
