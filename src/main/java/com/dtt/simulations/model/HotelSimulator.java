package com.dtt.simulations.model;


import jakarta.persistence.*;


import java.io.Serializable;


@Entity
@Table(name = "hospitality")

public class HotelSimulator implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "age_in_years")
    private String ageInYears;

   @Column(name="age_over_18")
   private boolean ageOver18;

    @Column(name="gender")
    private String gender;

    @Column(name="photo")
    private String photo;

    @Column(name = "room_allocated")
    private String roomAllocated;

    @Column(name = "creation_date")
    private String creationDate;


    @Column(name="json_data")
    private String jsonData;

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
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

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAgeInYears() {
        return ageInYears;
    }

    public void setAgeInYears(String ageInYears) {
        this.ageInYears = ageInYears;
    }

    public boolean isAgeOver18() {
        return ageOver18;
    }

    public void setAgeOver18(boolean ageOver18) {
        this.ageOver18 = ageOver18;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRoomAllocated() {
        return roomAllocated;
    }

    public void setRoomAllocated(String roomAllocated) {
        this.roomAllocated = roomAllocated;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "HotelSimulator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", ageInYears='" + ageInYears + '\'' +
                ", ageOver18=" + ageOver18 +
                ", gender='" + gender + '\'' +
                ", photo='" + photo + '\'' +
                ", roomAllocated='" + roomAllocated + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", jsonData='" + jsonData + '\'' +
                '}';
    }
}






