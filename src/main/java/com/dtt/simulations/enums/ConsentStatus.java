package com.dtt.simulations.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum ConsentStatus {
    PENDING("PENDING", "Pending Approval"),
    ACTIVE("ACTIVE", "Active & Valid"),
    REVOKED("REVOKED", "Revoked by User"),
    EXPIRED("EXPIRED", "Expired"),
    SUSPENDED("SUSPENDED", "Temporarily Suspended");

    private final String code;
    @Getter
    private final String description;

    ConsentStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

}

