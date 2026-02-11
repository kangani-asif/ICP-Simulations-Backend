package com.dtt.simulations.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.Serializable;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckSubscriberFaceResponseDto implements Serializable {

    private String suID;

    private String mobileNo;
    private String emailId;

    private String onboardingMethod;
    private String templateId;
    private String consentId;
    private String acknowledgement;
    private String subscriberType;
    private String onboardingApprovalStatus;
    private String certStatus;
    private String onboardingPaymentStatus;
    private String levelOfAssurance;

    private subscriberData subscriberData;

    private CertificateDetailDto certficateDetailDto;


    private List<SubscriberDocuments> subscriberDocuments;

    private List<JsonNode> additionalFields;


    public String getSuID() {
        return suID;
    }

    public void setSuID(String suID) {
        this.suID = suID;
    }

    public String getOnboardingMethod() {
        return onboardingMethod;
    }

    public void setOnboardingMethod(String onboardingMethod) {
        this.onboardingMethod = onboardingMethod;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getConsentId() {
        return consentId;
    }

    public void setConsentId(String consentId) {
        this.consentId = consentId;
    }

    public String getAcknowledgement() {
        return acknowledgement;
    }

    public void setAcknowledgement(String acknowledgement) {
        this.acknowledgement = acknowledgement;
    }

    public String getSubscriberType() {
        return subscriberType;
    }

    public void setSubscriberType(String subscriberType) {
        this.subscriberType = subscriberType;
    }

    public String getOnboardingApprovalStatus() {
        return onboardingApprovalStatus;
    }

    public void setOnboardingApprovalStatus(String onboardingApprovalStatus) {
        this.onboardingApprovalStatus = onboardingApprovalStatus;
    }

    public String getCertStatus() {
        return certStatus;
    }

    public void setCertStatus(String certStatus) {
        this.certStatus = certStatus;
    }

    public String getOnboardingPaymentStatus() {
        return onboardingPaymentStatus;
    }

    public void setOnboardingPaymentStatus(String onboardingPaymentStatus) {
        this.onboardingPaymentStatus = onboardingPaymentStatus;
    }

    public String getLevelOfAssurance() {
        return levelOfAssurance;
    }

    public void setLevelOfAssurance(String levelOfAssurance) {
        this.levelOfAssurance = levelOfAssurance;
    }

    public List<SubscriberDocuments> getSubscriberDocuments() {
        return subscriberDocuments;
    }

    public void setSubscriberDocuments(List<SubscriberDocuments> subscriberDocuments) {
        this.subscriberDocuments = subscriberDocuments;
    }

    public com.dtt.simulations.dto.subscriberData getSubscriberData() {
        return subscriberData;
    }

    public void setSubscriberData(com.dtt.simulations.dto.subscriberData subscriberData) {
        this.subscriberData = subscriberData;
    }

    public List<JsonNode> getAdditionalFields() {
        return additionalFields;
    }

    public void setAdditionalFields(List<JsonNode> additionalFields) {
        this.additionalFields = additionalFields;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public CertificateDetailDto getCertficateDetailDto() {
        return certficateDetailDto;
    }

    public void setCertficateDetailDto(CertificateDetailDto certficateDetailDto) {
        this.certficateDetailDto = certficateDetailDto;
    }

    @Override
    public String toString() {
        return "CheckSubscriberFaceResponseDto{" +
                "suID='" + suID + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", emailId='" + emailId + '\'' +
                ", onboardingMethod='" + onboardingMethod + '\'' +
                ", templateId='" + templateId + '\'' +
                ", consentId='" + consentId + '\'' +
                ", acknowledgement='" + acknowledgement + '\'' +
                ", subscriberType='" + subscriberType + '\'' +
                ", onboardingApprovalStatus='" + onboardingApprovalStatus + '\'' +
                ", certStatus='" + certStatus + '\'' +
                ", onboardingPaymentStatus='" + onboardingPaymentStatus + '\'' +
                ", levelOfAssurance='" + levelOfAssurance + '\'' +
                ", subscriberData=" + subscriberData +
                ", certficateDetailDto=" + certficateDetailDto +
                ", subscriberDocuments=" + subscriberDocuments +
                ", additionalFields=" + additionalFields +
                '}';
    }
}

