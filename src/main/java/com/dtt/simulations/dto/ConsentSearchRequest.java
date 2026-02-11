package com.dtt.simulations.dto;

import com.dtt.simulations.enums.ConsentStatus;
import com.dtt.simulations.enums.ConsentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class ConsentSearchRequest {

    private String emiratesId;
    private String citizenName;
    private String serviceProvider;
    private ConsentType consentType;
    private List<ConsentStatus> statuses;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validityStartFrom;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validityStartTo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validityEndFrom;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validityEndTo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDateFrom;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDateTo;

    @Builder.Default
    private String sortBy = "createdDate";

    @Builder.Default
    private String sortDirection = "DESC";

    @Min(value = 0, message = "Page number cannot be negative")
    @Builder.Default
    private int page = 0;

    @Min(value = 1, message = "Page size must be at least 1")
    @Max(value = 100, message = "Page size cannot exceed 100")
    @Builder.Default
    private int size = 20;
}
