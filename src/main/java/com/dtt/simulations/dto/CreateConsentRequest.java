package com.dtt.simulations.dto;

import com.dtt.simulations.enums.ConsentMethod;
import com.dtt.simulations.enums.ConsentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateConsentRequest {

    @NotBlank(message = "Citizen name is required")
    private String citizenName;

    @NotBlank(message = "Emirates ID is required")
    private String emiratesId;

    @NotBlank(message = "Service provider is required")
    @Size(min = 2, max = 200, message = "Service provider must be between 2 and 200 characters")
    private String serviceProvider;

    @NotNull(message = "Consent type is required")
    private ConsentType consentType;

    @NotBlank(message = "Purpose is required")
    @Size(min = 2, max = 2000, message = "Purpose must be between 10 and 2000 characters")
    private String purpose;

    @NotEmpty(message = "At least one data category is required")
    @Size(max = 20, message = "Maximum 20 data categories allowed")
    private List<@NotBlank(message = "Data category cannot be blank") String> dataCategories;

    @NotNull(message = "Validity start date is required")
    @FutureOrPresent(message = "Start date cannot be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validityStart;

    @NotNull(message = "Validity end date is required")
    @Future(message = "End date must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validityEnd;

    @NotNull(message = "Consent method is required")
    private ConsentMethod consentMethod;

    private String digitalSignature;

    // These will be populated by the controller
    private String ipAddress;
    private String userAgent;

    @AssertTrue(message = "End date must be after start date")
    public boolean isValidDateRange() {
        if (validityStart == null || validityEnd == null) {
            return true; // Let @NotNull handle null validation
        }
        return validityEnd.isAfter(validityStart);
    }

    @AssertTrue(message = "Consent period cannot exceed 5 years")
    public boolean isValidDuration() {
        if (validityStart == null || validityEnd == null) {
            return true;
        }
        return validityEnd.isBefore(validityStart.plusYears(5).plusDays(1));
    }
}

