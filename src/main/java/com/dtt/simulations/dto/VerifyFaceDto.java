package com.dtt.simulations.dto;

public class VerifyFaceDto {

    private String documentNumber;
    private String capturedImage;


    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getCapturedImage() {
        return capturedImage;
    }

    public void setCapturedImage(String capturedImage) {
        this.capturedImage = capturedImage;
    }

    @Override
    public String toString() {
        return "VerifyFaceDto{" +
                "documentNumber='" + documentNumber + '\'' +
                ", capturedImage='" + capturedImage + '\'' +
                '}';
    }
}
