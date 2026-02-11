package com.dtt.simulations.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum ConsentType {
    DATA_SHARING("DATA_SHARING", "General Data Sharing"),
    HEALTH_RECORDS("HEALTH_RECORDS", "Health & Medical Records"),
    FINANCIAL_DATA("FINANCIAL_DATA", "Financial Information"),
    LOCATION_TRACKING("LOCATION_TRACKING", "Location & GPS Data"),
    BIOMETRIC_DATA("BIOMETRIC_DATA", "Biometric Information"),
    COMMUNICATION_PREFERENCES("COMMUNICATION_PREFERENCES", "Communication & Marketing"),
    MARKETING("MARKETING", "Marketing Communications"),
    ANALYTICS("ANALYTICS", "Usage Analytics");

    private final String code;
    @Getter
    private final String description;

    ConsentType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

}
