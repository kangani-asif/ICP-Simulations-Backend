package com.dtt.simulations.dto;

public class BookingDetailsDto {


    private String gender;
    private String fullName;
    private String idDocNumber;
    private String dateOfBirth;
    private String photo;
    private String jsonData;

    private String documentType;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @Override
    public String toString() {
        return "BookingDetailsDto{" +
                "gender='" + gender + '\'' +
                ", fullName='" + fullName + '\'' +
                ", idDocNumber='" + idDocNumber + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", photo='" + photo + '\'' +
                ", jsonData='" + jsonData + '\'' +
                ", documentType='" + documentType + '\'' +
                '}';
    }
}
