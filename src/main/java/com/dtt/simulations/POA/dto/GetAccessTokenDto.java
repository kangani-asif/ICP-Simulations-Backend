package com.dtt.simulations.POA.dto;

public class GetAccessTokenDto {
    private  String email;

    private String suid;

    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GetAccessTokenDto{" +
                "email='" + email + '\'' +
                ", suid='" + suid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
