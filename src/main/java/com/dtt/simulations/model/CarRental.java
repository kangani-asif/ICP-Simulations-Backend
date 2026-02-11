package com.dtt.simulations.model;



import jakarta.persistence.*;

import java.util.Date;


    @Entity
    @Table(name = "vehcile_rental")
    public class CarRental {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
        private int id;


        @Column(name = "applicant_name")
        private String applicantName;

        @Column(name = "passport_number")
        private String passportNumber;

        @Column(name = "passport_document")
        private String passportDocumnet;

        @Column(name = "pid_document")
        private String pidDocumnet;

        @Column(name = "driving_license_number")
        private String drivingLicenseNumber;

        @Column(name = "driving_license_document")
        private String drivingLicenseDocument;

        @Column(name = "international_permit")
        private String internationalPermit;

        @Column(name = "rental_agreement_file")
        private String rentalAgreementFile;



        @Column(name = "status")
        private String status;

        @Column(name = "country")
        private String country;

        @Column(name="no_of_days")
        private String noOfDays;

        @Column(name="pick_up_date")
        private String pickUpDate;

        @Column(name="car_number")
        private String carNumber;

        @Column(name="json_data")
        private String jsonData;



        @Column(name = "created_on")
        private Date createdOn;

        @Column(name = "updated_on")
        private Date updatedOn;

        @Column(name="photo")
        private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRentalAgreementFile() {
            return rentalAgreementFile;
        }

        public void setRentalAgreementFile(String rentalAgreementFile) {
            this.rentalAgreementFile = rentalAgreementFile;
        }

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

        public String getPassportDocumnet() {
            return passportDocumnet;
        }

        public void setPassportDocumnet(String passportDocumnet) {
            this.passportDocumnet = passportDocumnet;
        }

        public String getPidDocumnet() {
            return pidDocumnet;
        }

        public void setPidDocumnet(String pidDocumnet) {
            this.pidDocumnet = pidDocumnet;
        }

        public String getDrivingLicenseNumber() {
            return drivingLicenseNumber;
        }

        public void setDrivingLicenseNumber(String drivingLicenseNumber) {
            this.drivingLicenseNumber = drivingLicenseNumber;
        }

        public String getDrivingLicenseDocument() {
            return drivingLicenseDocument;
        }

        public void setDrivingLicenseDocument(String drivingLicenseDocument) {
            this.drivingLicenseDocument = drivingLicenseDocument;
        }

        public String getInternationalPermit() {
            return internationalPermit;
        }

        public void setInternationalPermit(String internationalPermit) {
            this.internationalPermit = internationalPermit;
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

        public String getPickUpDate() {
            return pickUpDate;
        }

        public void setPickUpDate(String pickUpDate) {
            this.pickUpDate = pickUpDate;
        }

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }

        public String getJsonData() {
            return jsonData;
        }

        public void setJsonData(String jsonData) {
            this.jsonData = jsonData;
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


    @Override
    public String toString() {
        return "CarRental{" +
                "id=" + id +
                ", applicantName='" + applicantName + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", passportDocumnet='" + passportDocumnet + '\'' +
                ", pidDocumnet='" + pidDocumnet + '\'' +
                ", drivingLicenseNumber='" + drivingLicenseNumber + '\'' +
                ", drivingLicenseDocument='" + drivingLicenseDocument + '\'' +
                ", internationalPermit='" + internationalPermit + '\'' +
                ", rentalAgreementFile='" + rentalAgreementFile + '\'' +
                ", status='" + status + '\'' +
                ", country='" + country + '\'' +
                ", noOfDays='" + noOfDays + '\'' +
                ", pickUpDate='" + pickUpDate + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", jsonData='" + jsonData + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", photo='" + photo + '\'' +
                '}';
    }
}
