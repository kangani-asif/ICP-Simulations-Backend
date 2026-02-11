package com.dtt.simulations.model;


import jakarta.persistence.*;

@Entity
@Table(name = "health_care_registration")
public class HospitalInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "insured_name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "policy_number")
    private String policy_number;

    @Column(name = "policy_name")
    private String policy_name;

    @Column(name = "photo")
    private String photo;

    @Column(name = "policy_start_date")
    private String policy_start_date;

    @Column(name = "policy_end_date")
    private String policy_end_date;

    @Column(name = "policy_status")
    private String policy_status;

    @Column(name = "json_data")
    private String json_data;

    @Column(name = "created_on")
    private String createdOn;

    @Column(name = "updated_on")
    private String updatedOn;

    @Column(name = "policy_document")
    private String policyDocument;

    public String getPolicyDocument() {
        return policyDocument;
    }

    public void setPolicyDocument(String policyDocument) {
        this.policyDocument = policyDocument;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPolicy_number() {
        return policy_number;
    }

    public void setPolicy_number(String policy_number) {
        this.policy_number = policy_number;
    }

    public String getPolicy_name() {
        return policy_name;
    }

    public void setPolicy_name(String policy_name) {
        this.policy_name = policy_name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPolicy_start_date() {
        return policy_start_date;
    }

    public void setPolicy_start_date(String policy_start_date) {
        this.policy_start_date = policy_start_date;
    }

    public String getPolicy_end_date() {
        return policy_end_date;
    }

    public void setPolicy_end_date(String policy_end_date) {
        this.policy_end_date = policy_end_date;
    }

    public String getPolicy_status() {
        return policy_status;
    }

    public void setPolicy_status(String policy_status) {
        this.policy_status = policy_status;
    }

    public String getJson_data() {
        return json_data;
    }

    public void setJson_data(String json_data) {
        this.json_data = json_data;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "HospitalInsurance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", policy_number='" + policy_number + '\'' +
                ", policy_name='" + policy_name + '\'' +
                ", photo='" + photo + '\'' +
                ", policy_start_date='" + policy_start_date + '\'' +
                ", policy_end_date='" + policy_end_date + '\'' +
                ", policy_status='" + policy_status + '\'' +
                ", json_data='" + json_data + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", updatedOn='" + updatedOn + '\'' +
                ", policyDocument='" + policyDocument + '\'' +
                '}';
    }
}
