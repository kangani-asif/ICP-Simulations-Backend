package com.dtt.simulations.service.Impl;

import com.dtt.simulations.consentResponses.ConsentValidationException;
import com.dtt.simulations.dto.CreateConsentRequest;
import com.dtt.simulations.dto.UpdateConsentRequest;
import com.dtt.simulations.enums.ConsentStatus;
import com.dtt.simulations.enums.ConsentType;
import com.dtt.simulations.model.ConsentRecord;
import com.dtt.simulations.service.Iface.ConsentValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.dtt.simulations.enums.ConsentMethod.EXPLICIT_OPT_IN;
import static com.dtt.simulations.enums.ConsentStatus.REVOKED;

@Service
@Slf4j
public class ConsentValidationServiceImpl implements ConsentValidationService {

    @Override
    public void validateCreateRequest(CreateConsentRequest request) {
        log.debug("Validating create consent request for Emirates ID: {}", request.getEmiratesId());

        // Validate date range
        if (request.getValidityEnd().isBefore(request.getValidityStart())) {
            throw new ConsentValidationException("End date cannot be before start date");
        }

        // Validate maximum consent duration (5 years)
        if (request.getValidityEnd().isAfter(request.getValidityStart().plusYears(5))) {
            throw new ConsentValidationException("Consent validity cannot exceed 5 years");
        }

        // Validate data categories
        if (request.getDataCategories() == null || request.getDataCategories().isEmpty()) {
            throw new ConsentValidationException("At least one data category must be specified");
        }

        // Validate consent type specific rules
        validateConsentTypeSpecificRules(request.getConsentType(), request);
    }

    @Override
    public void validateUpdateRequest(ConsentRecord existing, UpdateConsentRequest request) {
        log.debug("Validating update consent request for consent ID: {}", existing.getId());

        // Cannot modify revoked consent (except admin operations)
        if (existing.getStatus() == REVOKED &&
                request.getStatus() != null && request.getStatus() != REVOKED) {
            throw new ConsentValidationException("Cannot modify revoked consent");
        }

        // Cannot set end date in the past
        if (request.getValidityEnd() != null && request.getValidityEnd().isBefore(LocalDate.now())) {
            throw new ConsentValidationException("Cannot set end date in the past");
        }

        // Validate status transitions
        if (request.getStatus() != null) {
            validateStatusTransition(existing.getStatus(), request.getStatus());
        }

        // Validate revocation reason for revoked status
        if (request.getStatus() == REVOKED &&
                (request.getRevocationReason() == null || request.getRevocationReason().trim().isEmpty())) {
            throw new ConsentValidationException("Revocation reason is required when revoking consent");
        }
    }

    @Override
    public void validateStatusTransition(ConsentStatus from, ConsentStatus to) {
        if (to == null) return;

        switch (from) {
            case PENDING:
                if (to != ConsentStatus.ACTIVE && to != ConsentStatus.REVOKED && to != ConsentStatus.SUSPENDED) {
                    throw new ConsentValidationException("Pending consent can only be activated, revoked, or suspended");
                }
                break;

            case ACTIVE:
                if (to == ConsentStatus.PENDING) {
                    throw new ConsentValidationException("Active consent cannot be set back to pending");
                }
                break;

            case EXPIRED:
                if (to != ConsentStatus.REVOKED) {
                    throw new ConsentValidationException("Expired consent can only be revoked");
                }
                break;

            case REVOKED:
                throw new ConsentValidationException("Revoked consent status cannot be changed");

            case SUSPENDED:
                if (to == ConsentStatus.PENDING) {
                    throw new ConsentValidationException("Suspended consent cannot be set to pending");
                }
                break;
        }
    }

    @Override
    public void validateConsentTypeSpecificRules(ConsentType type, CreateConsentRequest request) {
        switch (type) {
            case HEALTH_RECORDS:
                // Health records require specific data categories
                boolean hasHealthCategory = request.getDataCategories().stream()
                        .anyMatch(category -> category.toLowerCase().contains("medical") ||
                                category.toLowerCase().contains("health"));
                if (!hasHealthCategory) {
                    throw new ConsentValidationException("Health records consent must include medical or health data categories");
                }
                break;

            case FINANCIAL_DATA:
                // Financial data requires stronger authentication
                if (request.getConsentMethod() == EXPLICIT_OPT_IN) {
                    throw new ConsentValidationException("Financial data requires stronger authentication method than explicit opt-in");
                }
                break;

            case BIOMETRIC_DATA:
                // Biometric data has strict duration limits (max 1 year)
                if (request.getValidityEnd().isAfter(request.getValidityStart().plusYears(1))) {
                    throw new ConsentValidationException("Biometric data consent cannot exceed 1 year");
                }
                break;

            case LOCATION_TRACKING:
                // Location tracking should have reasonable duration (max 2 years)
                if (request.getValidityEnd().isAfter(request.getValidityStart().plusYears(2))) {
                    throw new ConsentValidationException("Location tracking consent should not exceed 2 years");
                }
                break;

            default:
                // No specific validation for other types
                break;
        }
    }
}
