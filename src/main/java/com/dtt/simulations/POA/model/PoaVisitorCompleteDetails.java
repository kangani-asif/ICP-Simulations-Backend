package com.dtt.simulations.POA.model;



import jakarta.persistence.*;

import java.util.Date;

@Table(name = "visitor_complete_details_nodocs")
@Entity
public class PoaVisitorCompleteDetails {

    @Id
    @Column(name = "subscriber_uid")
    private String subscriberUid;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "subscriber_type")
    private String subscriberType;

    @Column(name = "date_of_birth")
    private Date dob;

    @Column(name = "id_doc_type")
    private String idDocType;

    @Column(name = "id_doc_number")
    private String idDocNumber;

    @Column(name = "created_date")
    private Date createdOn;

    @Column(name = "certificate_status")
    private String certificateStatus;

    @Column(name = "device_status")
    private String deviceStatus;

    @Column(name = "subscriber_status")
    private String subscriberStatus;

    @Column(name = "device_registration_time")
    private Date deviceRegistrationTime;

    @Column(name="cerificate_expiry_date")
    private Date certificateExpiryDate;

    @Column(name = "certificate_issue_date")
    private Date certificateIssueDate;

    @Column(name = "sign_pin_set_date")
    private Date signPinSetDate;

    @Column(name = "auth_pin_set_date")
    private Date authPinSetDate;


    @Column(name = "selfie_uri")
    private String selfieUri;

    @Column(name = "on_boarding_time")
    private String onBoardingTime;


    @Column(name = "on_boarding_method")
    private String onBoardingMethod;


    @Column(name = "level_of_assurance")
    private String levelOfAssurance;


    @Column(name = "mobile_number")
    private String mobileNumber;


    @Column(name = "geo_location")
    private String geoLocation;


    @Column(name = "gender")
    private String gender;


    @Column(name = "email_id")
    private String eMail;


    @Column(name = "revocation_date")
    private String revocationDate;


    @Column(name = "revocation_reason")
    private String revocationReason;

    @Column(name = "selfie_thumbnail_uri")
    private String photo;

    @Column(name = "certificate_serial_number")
    private String certificateSerialNumber;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "video_type")
    private String videoType;


    @Transient
    @Column(name = "id_doc_image")
    private String idDocImage;

    @Column(name ="country_name")
    private String countryName;

    @Transient
    @Column(name = "passport_expiry_date")
    private String passportExpiryDate;

    public String getSubscriberUid() {
        return subscriberUid;
    }

    public void setSubscriberUid(String subscriberUid) {
        this.subscriberUid = subscriberUid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSubscriberType() {
        return subscriberType;
    }

    public void setSubscriberType(String subscriberType) {
        this.subscriberType = subscriberType;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getIdDocType() {
        return idDocType;
    }

    public void setIdDocType(String idDocType) {
        this.idDocType = idDocType;
    }

    public String getIdDocNumber() {
        return idDocNumber;
    }

    public void setIdDocNumber(String idDocNumber) {
        this.idDocNumber = idDocNumber;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCertificateStatus() {
        return certificateStatus;
    }

    public void setCertificateStatus(String certificateStatus) {
        this.certificateStatus = certificateStatus;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getSubscriberStatus() {
        return subscriberStatus;
    }

    public void setSubscriberStatus(String subscriberStatus) {
        this.subscriberStatus = subscriberStatus;
    }

    public Date getDeviceRegistrationTime() {
        return deviceRegistrationTime;
    }

    public void setDeviceRegistrationTime(Date deviceRegistrationTime) {
        this.deviceRegistrationTime = deviceRegistrationTime;
    }

    public Date getCertificateExpiryDate() {
        return certificateExpiryDate;
    }

    public void setCertificateExpiryDate(Date certificateExpiryDate) {
        this.certificateExpiryDate = certificateExpiryDate;
    }

    public Date getCertificateIssueDate() {
        return certificateIssueDate;
    }

    public void setCertificateIssueDate(Date certificateIssueDate) {
        this.certificateIssueDate = certificateIssueDate;
    }

    public Date getSignPinSetDate() {
        return signPinSetDate;
    }

    public void setSignPinSetDate(Date signPinSetDate) {
        this.signPinSetDate = signPinSetDate;
    }

    public Date getAuthPinSetDate() {
        return authPinSetDate;
    }

    public void setAuthPinSetDate(Date authPinSetDate) {
        this.authPinSetDate = authPinSetDate;
    }

    public String getSelfieUri() {
        return selfieUri;
    }

    public void setSelfieUri(String selfieUri) {
        this.selfieUri = selfieUri;
    }

    public String getOnBoardingTime() {
        return onBoardingTime;
    }

    public void setOnBoardingTime(String onBoardingTime) {
        this.onBoardingTime = onBoardingTime;
    }

    public String getOnBoardingMethod() {
        return onBoardingMethod;
    }

    public void setOnBoardingMethod(String onBoardingMethod) {
        this.onBoardingMethod = onBoardingMethod;
    }

    public String getLevelOfAssurance() {
        return levelOfAssurance;
    }

    public void setLevelOfAssurance(String levelOfAssurance) {
        this.levelOfAssurance = levelOfAssurance;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getRevocationDate() {
        return revocationDate;
    }

    public void setRevocationDate(String revocationDate) {
        this.revocationDate = revocationDate;
    }

    public String getRevocationReason() {
        return revocationReason;
    }

    public void setRevocationReason(String revocationReason) {
        this.revocationReason = revocationReason;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCertificateSerialNumber() {
        return certificateSerialNumber;
    }

    public void setCertificateSerialNumber(String certificateSerialNumber) {
        this.certificateSerialNumber = certificateSerialNumber;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getIdDocImage() {
        return idDocImage;
    }

    public void setIdDocImage(String idDocImage) {
        this.idDocImage = idDocImage;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPassportExpiryDate() {
        return passportExpiryDate;
    }

    public void setPassportExpiryDate(String passportExpiryDate) {
        this.passportExpiryDate = passportExpiryDate;
    }

    @Override
    public String toString() {
        return "VisitorCompleteDetails{" +
                "subscriberUid='" + subscriberUid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", subscriberType='" + subscriberType + '\'' +
                ", dob=" + dob +
                ", idDocType='" + idDocType + '\'' +
                ", idDocNumber='" + idDocNumber + '\'' +
                ", createdOn=" + createdOn +
                ", certificateStatus='" + certificateStatus + '\'' +
                ", deviceStatus='" + deviceStatus + '\'' +
                ", subscriberStatus='" + subscriberStatus + '\'' +
                ", deviceRegistrationTime=" + deviceRegistrationTime +
                ", certificateExpiryDate=" + certificateExpiryDate +
                ", certificateIssueDate=" + certificateIssueDate +
                ", signPinSetDate=" + signPinSetDate +
                ", authPinSetDate=" + authPinSetDate +
                ", selfieUri='" + selfieUri + '\'' +
                ", onBoardingTime='" + onBoardingTime + '\'' +
                ", onBoardingMethod='" + onBoardingMethod + '\'' +
                ", levelOfAssurance='" + levelOfAssurance + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", geoLocation='" + geoLocation + '\'' +
                ", gender='" + gender + '\'' +
                ", eMail='" + eMail + '\'' +
                ", revocationDate='" + revocationDate + '\'' +
                ", revocationReason='" + revocationReason + '\'' +
                ", photo='" + photo + '\'' +
                ", certificateSerialNumber='" + certificateSerialNumber + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoType='" + videoType + '\'' +
                ", idDocImage='" + idDocImage + '\'' +
                ", countryName='" + countryName + '\'' +
                ", passportExpiryDate='" + passportExpiryDate + '\'' +
                '}';
    }
}

