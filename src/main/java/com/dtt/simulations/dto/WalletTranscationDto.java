package com.dtt.simulations.dto;

public class WalletTranscationDto {

    private String passportNumber;

    private String type;

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "WalletTranscationDto{" +
                "passportNumber='" + passportNumber + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
