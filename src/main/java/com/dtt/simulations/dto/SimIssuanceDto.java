package com.dtt.simulations.dto;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class SimIssuanceDto {

    @NotBlank(message = "Full Name is required")
    private String fullName;

    @NotBlank(message = "ID Document Number is required")
    private String idDocNumber;

    @NotBlank(message = "Mobile Number is required")
    private String mobileNumber;

    private String documentType;

    // To hold dynamic JSON payload
    private JsonNode additionalData;

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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public JsonNode getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(JsonNode additionalData) {
        this.additionalData = additionalData;
    }


    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @Override
    public String toString() {
        return "SimIssuanceDto{" +
                "fullName='" + fullName + '\'' +
                ", idDocNumber='" + idDocNumber + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", documentType='" + documentType + '\'' +
                ", additionalData=" + additionalData +
                '}';
    }
}
