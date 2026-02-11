package com.dtt.simulations.model;


import jakarta.persistence.*;
import java.util.Date;

@Table(name = "subscriber_fcm_token")
@Entity
public class SubscriberFCMToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscriber_fcm_token_id")
    private int id;

    @Column(name = "subscriber_uid")
    private String suid;

    @Column(name = "fcm_token")
    private String fcmToken;

    @Column(name = "created_date")
    private Date createdDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "SubscriberFCMToken{" +
                "id=" + id +
                ", suid='" + suid + '\'' +
                ", fcmToken='" + fcmToken + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
