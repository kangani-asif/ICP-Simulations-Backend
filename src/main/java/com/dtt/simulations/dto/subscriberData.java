package com.dtt.simulations.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)

public class subscriberData implements Serializable {

    private String dateOfBirth;
    private String dateOfExpiry;
    private String nationality;
    private String gender;
    private String primaryIdentifier;
    private String secondaryIdentifier;
    private String documentType;
    private String documentCode;
    private String optionalData1;
    private String optionalData2;
    private String documentNumber;
    private String issuingState;
    private String photoVerificationPerc;
    private String subscriberSelfie;
    private String subscriberUniqueId;
    private String geoLocation;
    private String remarks;
    private String onboardingStepType;
    private String niraResponse;

    public String getOnboardingStepType() {
        return onboardingStepType;
    }

    public void setOnboardingStepType(String onboardingStepType) {
        this.onboardingStepType = onboardingStepType;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPrimaryIdentifier() {
        return primaryIdentifier;
    }

    public void setPrimaryIdentifier(String primaryIdentifier) {
        this.primaryIdentifier = primaryIdentifier;
    }

    public String getSecondaryIdentifier() {
        return secondaryIdentifier;
    }

    public void setSecondaryIdentifier(String secondaryIdentifier) {
        this.secondaryIdentifier = secondaryIdentifier;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public String getOptionalData1() {
        return optionalData1;
    }

    public void setOptionalData1(String optionalData1) {
        this.optionalData1 = optionalData1;
    }

    public String getOptionalData2() {
        return optionalData2;
    }

    public void setOptionalData2(String optionalData2) {
        this.optionalData2 = optionalData2;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getIssuingState() {
        return issuingState;
    }

    public void setIssuingState(String issuingState) {
        this.issuingState = issuingState;
    }

    public String getPhotoVerificationPerc() {
        return photoVerificationPerc;
    }

    public void setPhotoVerificationPerc(String photoVerificationPerc) {
        this.photoVerificationPerc = photoVerificationPerc;
    }

    public String getSubscriberSelfie() {
        return subscriberSelfie;
    }

    public void setSubscriberSelfie(String subscriberSelfie) {
        this.subscriberSelfie = subscriberSelfie;
    }

    public String getSubscriberUniqueId() {
        return subscriberUniqueId;
    }

    public void setSubscriberUniqueId(String subscriberUniqueId) {
        this.subscriberUniqueId = subscriberUniqueId;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getNiraResponse() {
        return niraResponse;
    }

    public void setNiraResponse(String niraResponse) {
        this.niraResponse = niraResponse;
    }

    @Override
    public String toString() {
        return "subscriberData{" +
                "dateOfBirth='" + dateOfBirth + '\'' +
                ", dateOfExpiry='" + dateOfExpiry + '\'' +
                ", nationality='" + nationality + '\'' +
                ", gender='" + gender + '\'' +
                ", primaryIdentifier='" + primaryIdentifier + '\'' +
                ", secondaryIdentifier='" + secondaryIdentifier + '\'' +
                ", documentType='" + documentType + '\'' +
                ", documentCode='" + documentCode + '\'' +
                ", optionalData1='" + optionalData1 + '\'' +
                ", optionalData2='" + optionalData2 + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", issuingState='" + issuingState + '\'' +
                ", photoVerificationPerc='" + photoVerificationPerc + '\'' +
                ", subscriberSelfie='" + subscriberSelfie + '\'' +
                ", subscriberUniqueId='" + subscriberUniqueId + '\'' +
                ", geoLocation='" + geoLocation + '\'' +
                ", remarks='" + remarks + '\'' +
                ", onboardingStepType='" + onboardingStepType + '\'' +
                ", niraResponse='" + niraResponse + '\'' +
                '}';
    }
}

