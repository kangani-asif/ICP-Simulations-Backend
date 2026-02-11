package com.dtt.simulations.POA.dto;

public class User {

    private String email;

    private String suid;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", suid='" + suid + '\'' +
                '}';
    }
}
