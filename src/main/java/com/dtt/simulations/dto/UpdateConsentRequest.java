package com.dtt.simulations.dto;


import com.dtt.simulations.enums.ConsentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
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
public class UpdateConsentRequest {

    private ConsentStatus status;

    @Size(max = 1000, message = "Revocation reason cannot exceed 1000 characters")
    private String revocationReason;

    @Size(max = 20, message = "Maximum 20 data categories allowed")
    private List<String> dataCategories;

    @Future(message = "End date must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validityEnd;

    @Size(min = 10, max = 2000, message = "Purpose must be between 10 and 2000 characters")
    private String purpose;
}

