package com.dtt.simulations.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "telecom_sim_issuance")
@Data
public class TelecomSimIssuance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name",nullable = false)
    private String fullName;

    @Column(name = "id_doc_number",nullable = false, unique = true)
    private String idDocNumber;

    @Column(name = "mobile_number",nullable = false, unique = true)
    private String mobileNumber;

    // Store JSON as string (in jsonb or json column)
    @Column(name = "json_body")
    private String additionalData;

    @Column(name = "sim_number")
    private int simNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdDocNumber() {
        return idDocNumber;
    }

    public void setIdDocNumber(String idDocNumber) {
        this.idDocNumber = idDocNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public int getSimNumber() {
        return simNumber;
    }

    public void setSimNumber(int simNumber) {
        this.simNumber = simNumber;
    }

    @Override
    public String toString() {
        return "TelecomSimIssuance{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", idDocNumber='" + idDocNumber + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", additionalData='" + additionalData + '\'' +
                ", simNumber=" + simNumber +
                '}';
    }
}

