package com.dtt.simulations.dto;

public class ViewApproveRejectForBankDto {
    private boolean extendedKyc;
    private String kycReport;

    public boolean isExtendedKyc() {
        return extendedKyc;
    }

    public void setExtendedKyc(boolean extendedKyc) {
        this.extendedKyc = extendedKyc;
    }

    public String getKycReport() {
        return kycReport;
    }

    public void setKycReport(String kycReport) {
        this.kycReport = kycReport;
    }

    @Override
    public String toString() {
        return "ViewApproveRejectForBankDto{" +
                "extendedKyc=" + extendedKyc +
                ", kycReport='" + kycReport + '\'' +
                '}';
    }
}
