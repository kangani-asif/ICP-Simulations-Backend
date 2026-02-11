package com.dtt.simulations.dto;

import com.dtt.simulations.enums.ConsentMethod;
import com.dtt.simulations.enums.ConsentStatus;
import com.dtt.simulations.enums.ConsentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsentRecordResponse {

    private Long id;
    private String citizenName;
    private String emiratesId;
    private String serviceProvider;
    private ConsentType consentType;
    private String purpose;
    private List<String> dataCategories;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validityStart;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validityEnd;

    private ConsentStatus status;
    private ConsentMethod consentMethod;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedDate;

    private String revocationReason;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime revokedDate;

    private String digitalSignature;
    private String ipAddress;
    private String userAgent;

    // Computed fields
    private boolean isActive;
    private boolean isExpired;
    private long daysUntilExpiry;
}
