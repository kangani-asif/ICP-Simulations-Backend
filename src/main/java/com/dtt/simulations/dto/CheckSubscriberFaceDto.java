package com.dtt.simulations.dto;

import java.io.Serializable;

public class CheckSubscriberFaceDto implements Serializable {

    private boolean selfieRequired;
    private String suid;

    public boolean isSelfieRequired() {
        return selfieRequired;
    }

    public void setSelfieRequired(boolean selfieRequired) {
        this.selfieRequired = selfieRequired;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    @Override
    public String toString() {
        return "CheckSubscriberFaceDto{" +
                "selfieRequired=" + selfieRequired +
                ", suid='" + suid + '\'' +
                '}';
    }
}

