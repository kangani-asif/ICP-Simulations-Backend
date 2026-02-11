package com.dtt.simulations.service.Impl;

import com.dtt.simulations.enums.ConsentStatus;
import com.dtt.simulations.model.ConsentRecord;
import com.dtt.simulations.service.Iface.ConsentAuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsentAuditServiceImpl implements ConsentAuditService {

    @Override
    public void logConsentCreated(ConsentRecord consent) {
        log.info("AUDIT: Consent created - ID: {}, Emirates ID: {}, Service Provider: {}, Type: {}, Method: {}",
                consent.getId(),
                consent.getEmiratesId(),
                consent.getServiceProvider(),
                consent.getConsentType(),
                consent.getConsentMethod());
    }

    @Override
    public void logConsentUpdated(ConsentRecord consent) {
        log.info("AUDIT: Consent updated - ID: {}, Emirates ID: {}, Service Provider: {}",
                consent.getId(),
                consent.getEmiratesId(),
                consent.getServiceProvider());
    }

    @Override
    public void logStatusChange(ConsentRecord consent, ConsentStatus oldStatus, ConsentStatus newStatus) {
        log.info("AUDIT: Status changed - Consent ID: {}, Emirates ID: {}, From: {}, To: {}, Timestamp: {}",
                consent.getId(),
                consent.getEmiratesId(),
                oldStatus,
                newStatus,
                java.time.LocalDateTime.now());
    }

    @Override
    public void logConsentRevoked(ConsentRecord consent, String reason) {
        log.info("AUDIT: Consent revoked - ID: {}, Emirates ID: {}, Service Provider: {}, Reason: {}, Timestamp: {}",
                consent.getId(),
                consent.getEmiratesId(),
                consent.getServiceProvider(),
                reason,
                java.time.LocalDateTime.now());
    }

    @Override
    public void logConsentDeleted(ConsentRecord consent) {
        log.warn("AUDIT: Consent deleted - ID: {}, Emirates ID: {}, Service Provider: {}, Status: {}, Timestamp: {}",
                consent.getId(),
                consent.getEmiratesId(),
                consent.getServiceProvider(),
                consent.getStatus(),
                java.time.LocalDateTime.now());
    }

    @Override
    public void logBulkOperation(String operation, int count, String details) {
        log.info("AUDIT: Bulk operation - Operation: {}, Count: {}, Details: {}, Timestamp: {}",
                operation,
                count,
                details,
                java.time.LocalDateTime.now());
    }
}

