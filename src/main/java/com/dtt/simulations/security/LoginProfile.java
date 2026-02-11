package com.dtt.simulations.security;

public class LoginProfile {
    public String Email ;
    public String OrgnizationId;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getOrgnizationId() {
        return OrgnizationId;
    }

    public void setOrgnizationId(String orgnizationId) {
        OrgnizationId = orgnizationId;
    }

    @Override
    public String toString() {
        return "LoginProfile{" +
                "Email='" + Email + '\'' +
                ", OrgnizationId='" + OrgnizationId + '\'' +
                '}';
    }
}


