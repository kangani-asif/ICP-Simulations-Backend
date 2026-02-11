package com.dtt.simulations.dto;

public class HospitalInsuranceDTO {

    private int id;

    private String name;

    private String gender;

    private String phone_number;

    private String policy_number;

    private String policy_name;

    private String policy_start_date;

    private String policy_end_date;

    private String policy_status;

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

    @Override
    public String toString() {
        return "HospitalInsuranceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", policy_number='" + policy_number + '\'' +
                ", policy_name='" + policy_name + '\'' +
                ", policy_start_date='" + policy_start_date + '\'' +
                ", policy_end_date='" + policy_end_date + '\'' +
                ", policy_status='" + policy_status + '\'' +
                '}';
    }
}
