package com.dtt.simulations.dto;

public class BankApproveRejectDto {
    private String passport;
    private String kycJson;
    private String extendedKyc;
    private String status;

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getKycJson() {
        return kycJson;
    }

    public void setKycJson(String kycJson) {
        this.kycJson = kycJson;
    }

    public String getExtendedKyc() {
        return extendedKyc;
    }

    public void setExtendedKyc(String extendedKyc) {
        this.extendedKyc = extendedKyc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BankApproveRejectDto{" +
                "passport='" + passport + '\'' +
                ", kycJson='" + kycJson + '\'' +
                ", extendedKyc='" + extendedKyc + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
