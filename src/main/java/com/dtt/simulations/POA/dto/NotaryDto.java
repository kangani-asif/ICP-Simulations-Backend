package com.dtt.simulations.POA.dto;

public class NotaryDto {

    private String notaryEmployeeEmail;

    private String notaryUaeIdEmail;

    private String notarySuid;


    public String getNotaryEmployeeEmail() {
        return notaryEmployeeEmail;
    }

    public void setNotaryEmployeeEmail(String notaryEmployeeEmail) {
        this.notaryEmployeeEmail = notaryEmployeeEmail;
    }

    public String getNotaryUaeIdEmail() {
        return notaryUaeIdEmail;
    }

    public void setNotaryUaeIdEmail(String notaryUaeIdEmail) {
        this.notaryUaeIdEmail = notaryUaeIdEmail;
    }

    public String getNotarySuid() {
        return notarySuid;
    }

    public void setNotarySuid(String notarySuid) {
        this.notarySuid = notarySuid;
    }

    @Override
    public String toString() {
        return "NotaryDto{" +
                "notaryEmployeeEmail='" + notaryEmployeeEmail + '\'' +
                ", notaryUaeIdEmail='" + notaryUaeIdEmail + '\'' +
                ", notarySuid='" + notarySuid + '\'' +
                '}';
    }
}
