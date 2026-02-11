package com.dtt.simulations.enums;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum ConsentMethod {
    DIGITAL_SIGNATURE("DIGITAL_SIGNATURE", "Digital Signature"),
    BIOMETRIC("BIOMETRIC", "Biometric Authentication"),
    EXPLICIT_OPT_IN("EXPLICIT_OPT_IN", "Explicit Opt-in"),
    EMAIL_CONFIRMATION("EMAIL_CONFIRMATION", "Email Confirmation"),
    SMS_VERIFICATION("SMS_VERIFICATION", "SMS Verification"),
    IN_PERSON("IN_PERSON", "In-Person Verification");

    private final String code;
    @Getter
    private final String description;

    ConsentMethod(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

}
