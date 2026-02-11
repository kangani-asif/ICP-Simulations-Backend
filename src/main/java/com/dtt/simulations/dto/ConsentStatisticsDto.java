package com.dtt.simulations.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsentStatisticsDto {

    private long totalConsents;
    private long activeConsents;
    private long revokedConsents;
    private long expiredConsents;
    private long pendingConsents;
    private long suspendedConsents;

    private Map<String, Long> consentTypeStatistics;
    private Map<String, Long> consentStatusStatistics;
    private Map<String, Long> topServiceProviders;
    private Map<String, Long> monthlyConsentTrends;
}

