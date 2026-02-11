package com.dtt.simulations.service.Impl;

import com.dtt.simulations.dto.ConsentRecordResponse;
import com.dtt.simulations.dto.CreateConsentRequest;
import com.dtt.simulations.model.ConsentRecord;
import com.dtt.simulations.service.Iface.ConsentMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class ConsentMapperImpl implements ConsentMapper {

    @Override
    public ConsentRecord toEntity(CreateConsentRequest request) {
        return ConsentRecord.builder()
                .citizenName(request.getCitizenName())
                .emiratesId(request.getEmiratesId())
                .serviceProvider(request.getServiceProvider())
                .consentType(request.getConsentType())
                .purpose(request.getPurpose())
                .dataCategories(request.getDataCategories())
                .validityStart(request.getValidityStart())
                .validityEnd(request.getValidityEnd())
                .consentMethod(request.getConsentMethod())
                .digitalSignature(request.getDigitalSignature())
                .ipAddress(request.getIpAddress())
                .userAgent(request.getUserAgent())
                .build();
    }

    @Override
    public ConsentRecordResponse toResponse(ConsentRecord entity) {
        return ConsentRecordResponse.builder()
                .id(entity.getId())
                .citizenName(entity.getCitizenName())
                .emiratesId(entity.getEmiratesId())
                .serviceProvider(entity.getServiceProvider())
                .consentType(entity.getConsentType())
                .purpose(entity.getPurpose())
                .dataCategories(entity.getDataCategories())
                .validityStart(entity.getValidityStart())
                .validityEnd(entity.getValidityEnd())
                .status(entity.getStatus())
                .consentMethod(entity.getConsentMethod())
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .revocationReason(entity.getRevocationReason())
                .revokedDate(entity.getRevokedDate())
                .digitalSignature(entity.getDigitalSignature())
                .ipAddress(entity.getIpAddress())
                .userAgent(entity.getUserAgent())
                // Computed fields
                .isActive(entity.isActive())
                .isExpired(entity.isExpired())
                .daysUntilExpiry(entity.getDaysUntilExpiry())
                .build();
    }
}

