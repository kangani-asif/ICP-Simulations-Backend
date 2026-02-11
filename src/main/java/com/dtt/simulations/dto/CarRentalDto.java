package com.dtt.simulations.dto;

import java.util.Date;

public class CarRentalDto {

    private int id;
    private String applicantName;
   private String passportNumber;
   private String drivingLicenseNumber;
   private String internationalPermit;
   private Date createdOn;
   private Date updatedOn;
   private String status;
   private String country;
   private String noOfDays;
   private String carNumber;
   private String pickupDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getInternationalPermit() {
        return internationalPermit;
    }

    public void setInternationalPermit(String internationalPermit) {
        this.internationalPermit = internationalPermit;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(String noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    @Override
    public String toString() {
        return "CarRentalDto{" +
                "id='" + id + '\'' +
                ", applicantName='" + applicantName + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", drivingLicenseNumber='" + drivingLicenseNumber + '\'' +
                ", internationalPermit='" + internationalPermit + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", status='" + status + '\'' +
                ", country='" + country + '\'' +
                ", noOfDays='" + noOfDays + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", pickupDate='" + pickupDate + '\'' +
                '}';
    }
}
