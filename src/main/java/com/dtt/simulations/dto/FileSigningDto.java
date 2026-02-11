package com.dtt.simulations.dto;

public class FileSigningDto {

    public String suid;
    public String base64;

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    @Override
    public String toString() {
        return "ESealConsentDto{" +
                "suid='" + suid + '\'' +
                ", base64='" + base64 + '\'' +
                '}';
    }
}
