package com.dtt.simulations.model;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="bank")
public class BusinessAccountModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="subscriber_uid")
	private String subscriberUId;
	
	@Column(name="applicant_name")
	private String applicantName;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="applicant_photo")
	private String applicantPhoto;
	
	@Column(name="passport_number")
	private String passportNumber;
	
	@Column(name="nationality")
	private String nationality;
	
	@Column(name="resident_id_number")
	private String visaNumber;
	
	@Lob
	@Column(name="passport_document")
	private String passportDocument;
	
	@Lob
	@Column(name="visitor_document")
	private String visitorDocument;
	
	@Lob
	@Column(name="trade_license_document")
	private String tradeLicenseDocument;
	
	@Lob
	@Column(name="establishment_card")
	private String establishmentCard;
	
	@Lob
	@Column(name="residence_id_document")
	private String residenceIdDocument;
	
	@Lob
	@Column(name="bank_account_opening_form")
	private String bankAccountOpeningForm;
	
	@Column(name="bank_account_opening_json_data")
	private String bankAccountOpeningJsonData;
	
	@Column(name="application_status")
	private String applicationStatus;
	
	@Column(name="bank_account_number")
	private String bankAccountNumber;
	
	@Column(name="bank_account_holder")
	private String bankAccountHolder;
	
	@Column(name="bank_account_status")
	private String bankAccountStatus;

	@Column(name="extended_ekyc")
	private boolean extendedEkyc;
	
	@Column(name="created_on")
	private String createdOn;
	
	@Column(name="updated_on")
	private String updatedOn;

	@Column(name="kyc_json")
	private String kycJson ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubscriberUId() {
		return subscriberUId;
	}

	public void setSubscriberUId(String subscriberUId) {
		this.subscriberUId = subscriberUId;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getApplicantPhoto() {
		return applicantPhoto;
	}

	public void setApplicantPhoto(String applicantPhoto) {
		this.applicantPhoto = applicantPhoto;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getVisaNumber() {
		return visaNumber;
	}

	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
	}

	public String getPassportDocument() {
		return passportDocument;
	}

	public void setPassportDocument(String passportDocument) {
		this.passportDocument = passportDocument;
	}

	public String getVisitorDocument() {
		return visitorDocument;
	}

	public void setVisitorDocument(String visitorDocument) {
		this.visitorDocument = visitorDocument;
	}

	public String getTradeLicenseDocument() {
		return tradeLicenseDocument;
	}

	public void setTradeLicenseDocument(String tradeLicenseDocument) {
		this.tradeLicenseDocument = tradeLicenseDocument;
	}

	public String getEstablishmentCard() {
		return establishmentCard;
	}

	public void setEstablishmentCard(String establishmentCard) {
		this.establishmentCard = establishmentCard;
	}

	public String getResidenceIdDocument() {
		return residenceIdDocument;
	}

	public void setResidenceIdDocument(String residenceIdDocument) {
		this.residenceIdDocument = residenceIdDocument;
	}

	public String getBankAccountOpeningForm() {
		return bankAccountOpeningForm;
	}

	public void setBankAccountOpeningForm(String bankAccountOpeningForm) {
		this.bankAccountOpeningForm = bankAccountOpeningForm;
	}

	public String getBankAccountOpeningJsonData() {
		return bankAccountOpeningJsonData;
	}

	public void setBankAccountOpeningJsonData(String bankAccountOpeningJsonData) {
		this.bankAccountOpeningJsonData = bankAccountOpeningJsonData;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankAccountHolder() {
		return bankAccountHolder;
	}

	public void setBankAccountHolder(String bankAccountHolder) {
		this.bankAccountHolder = bankAccountHolder;
	}

	public String getBankAccountStatus() {
		return bankAccountStatus;
	}

	public void setBankAccountStatus(String bankAccountStatus) {
		this.bankAccountStatus = bankAccountStatus;
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

	public boolean isExtendedEkyc() {
		return extendedEkyc;
	}

	public void setExtendedEkyc(boolean extendedEkyc) {
		this.extendedEkyc = extendedEkyc;
	}

	public String getKycJson() {
		return kycJson;
	}

	public void setKycJson(String kycJson) {
		this.kycJson = kycJson;
	}

	@Override
	public String toString() {
		return "BusinessAccountModel{" +
				"id=" + id +
				", subscriberUId='" + subscriberUId + '\'' +
				", applicantName='" + applicantName + '\'' +
				", companyName='" + companyName + '\'' +
				", applicantPhoto='" + applicantPhoto + '\'' +
				", passportNumber='" + passportNumber + '\'' +
				", nationality='" + nationality + '\'' +
				", visaNumber='" + visaNumber + '\'' +
				", passportDocument='" + passportDocument + '\'' +
				", visitorDocument='" + visitorDocument + '\'' +
				", tradeLicenseDocument='" + tradeLicenseDocument + '\'' +
				", establishmentCard='" + establishmentCard + '\'' +
				", residenceIdDocument='" + residenceIdDocument + '\'' +
				", bankAccountOpeningForm='" + bankAccountOpeningForm + '\'' +
				", bankAccountOpeningJsonData='" + bankAccountOpeningJsonData + '\'' +
				", applicationStatus='" + applicationStatus + '\'' +
				", bankAccountNumber='" + bankAccountNumber + '\'' +
				", bankAccountHolder='" + bankAccountHolder + '\'' +
				", bankAccountStatus='" + bankAccountStatus + '\'' +
				", extendedEkyc=" + extendedEkyc +
				", createdOn=" + createdOn +
				", updatedOn=" + updatedOn +
				", kycJson='" + kycJson + '\'' +
				'}';
	}
}
