package com.dtt.simulations.dto;

public class BusinessAccRequestDTO {

    private String gender;
    private String fullName;
    private String idDocNumber;
    private String dateOfBirth;
    private String photo;
    private String jsonData;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    @Override
    public String toString() {
        return "HospitalInsuranceRequestDTO{" +
                "gender='" + gender + '\'' +
                ", fullName='" + fullName + '\'' +
                ", idDocNumber='" + idDocNumber + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", photo='" + photo + '\'' +
                ", jsonData='" + jsonData + '\'' +
                '}';
    }
}
