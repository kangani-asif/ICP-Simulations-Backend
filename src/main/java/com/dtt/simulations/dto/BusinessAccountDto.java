package com.dtt.simulations.dto;

import java.io.Serializable;


public class BusinessAccountDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String subscriberUId;

	private String applicantName;

	private String companyName;
	
	private String applicantPhoto;
	
	private String passportNumber;
	
	private String nationality;
	
	private String visaNumber;

	private String createdDate;
	
//	private byte[] passportDocument;
//	
//	private byte[] visaDocument;
//	
//	private byte[] tradeLicenseDocument;
//	
//	private byte[] establishmentCard;
//	
//	private byte[] residenceIdDocument;
//	
//	private byte[] bankAccountOpeningForm;
	
	private Object bankAccountOpeningJsonData;
	
	private String applicationStatus;
	
	private String bankAccountNumber;
	
	private String bankAccountHolder;
	
	private String bankAccountStatus;

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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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

	public Object getBankAccountOpeningJsonData() {
		return bankAccountOpeningJsonData;
	}

	public void setBankAccountOpeningJsonData(Object bankAccountOpeningJsonData) {
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

	@Override
	public String toString() {
		return "BusinessAccountDto{" +
				"id=" + id +
				", subscriberUId='" + subscriberUId + '\'' +
				", applicantName='" + applicantName + '\'' +
				", companyName='" + companyName + '\'' +
				", applicantPhoto='" + applicantPhoto + '\'' +
				", passportNumber='" + passportNumber + '\'' +
				", nationality='" + nationality + '\'' +
				", visaNumber='" + visaNumber + '\'' +
				", createdDate=" + createdDate +
				", bankAccountOpeningJsonData=" + bankAccountOpeningJsonData +
				", applicationStatus='" + applicationStatus + '\'' +
				", bankAccountNumber='" + bankAccountNumber + '\'' +
				", bankAccountHolder='" + bankAccountHolder + '\'' +
				", bankAccountStatus='" + bankAccountStatus + '\'' +
				'}';
	}


}
