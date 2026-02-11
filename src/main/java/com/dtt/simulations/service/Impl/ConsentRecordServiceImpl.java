package com.dtt.simulations.service.Impl;

import com.dtt.simulations.consentResponses.ConsentNotFoundException;
import com.dtt.simulations.consentResponses.ConsentValidationException;
import com.dtt.simulations.dto.*;
import com.dtt.simulations.enums.ConsentStatus;
import com.dtt.simulations.model.ConsentRecord;
import com.dtt.simulations.repo.ConsentRecordRepository;
import com.dtt.simulations.service.Iface.ConsentAuditService;
import com.dtt.simulations.service.Iface.ConsentMapper;
import com.dtt.simulations.service.Iface.ConsentRecordService;
import com.dtt.simulations.service.Iface.ConsentValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ConsentRecordServiceImpl implements ConsentRecordService {

    private final ConsentRecordRepository repository;
    private final ConsentMapper mapper;
    private final ConsentValidationService validationService;
    private final ConsentAuditService auditService;

    @Override
    public ConsentRecordResponse createConsent(CreateConsentRequest request) {
        log.info("Creating consent for Emirates ID: {}", request.getEmiratesId());

        // Validate request
        validationService.validateCreateRequest(request);

        // Check for existing active consent for same service provider
        repository.findByEmiratesIdAndServiceProviderAndStatus(
                request.getEmiratesId(),
                request.getServiceProvider(),
                ConsentStatus.ACTIVE
        ).ifPresent(existing -> {
            throw new ConsentValidationException(
                    "Active consent already exists for this service provider"
            );
        });

        // Create and save consent
        ConsentRecord consent = mapper.toEntity(request);
        consent.setStatus(ConsentStatus.ACTIVE);
        consent.setCreatedDate(LocalDateTime.now());

        ConsentRecord savedConsent = repository.save(consent);
        auditService.logConsentCreated(savedConsent);

        return mapper.toResponse(savedConsent);
    }

    @Override
    @Transactional(readOnly = true)
    public ConsentRecordResponse getConsentById(Long id) {
        ConsentRecord consent = repository.findById(id)
                .orElseThrow(() -> new ConsentNotFoundException("Consent not found with ID: " + id));

        return mapper.toResponse(consent);
    }

    @Override
    public ConsentRecordResponse updateConsent(Long id, UpdateConsentRequest request) {
        log.info("Updating consent with ID: {}", id);

        ConsentRecord consent = repository.findById(id)
                .orElseThrow(() -> new ConsentNotFoundException("Consent not found with ID: " + id));

        // Validate update request
        validationService.validateUpdateRequest(consent, request);

        // Update fields
        if (request.getStatus() != null) {
            ConsentStatus oldStatus = consent.getStatus();
            consent.setStatus(request.getStatus());

            if (request.getStatus() == ConsentStatus.REVOKED) {
                consent.setRevokedDate(LocalDateTime.now());
                consent.setRevocationReason(request.getRevocationReason());
            }

            auditService.logStatusChange(consent, oldStatus, request.getStatus());
        }

        if (request.getDataCategories() != null) {
            consent.setDataCategories(request.getDataCategories());
        }

        if (request.getValidityEnd() != null) {
            consent.setValidityEnd(request.getValidityEnd());
        }

        if (request.getPurpose() != null) {
            consent.setPurpose(request.getPurpose());
        }

        consent.setUpdatedDate(LocalDateTime.now());
        ConsentRecord updatedConsent = repository.save(consent);

        auditService.logConsentUpdated(updatedConsent);
        return mapper.toResponse(updatedConsent);
    }

    @Override
    public ConsentRecordResponse revokeConsent(Long id, String reason) {
        log.info("Revoking consent with ID: {}", id);

        ConsentRecord consent = repository.findById(id)
                .orElseThrow(() -> new ConsentNotFoundException("Consent not found with ID: " + id));

        if (consent.getStatus() == ConsentStatus.REVOKED) {
            throw new ConsentValidationException("Consent is already revoked");
        }

        ConsentStatus oldStatus = consent.getStatus();
        consent.setStatus(ConsentStatus.REVOKED);
        consent.setRevokedDate(LocalDateTime.now());
        consent.setRevocationReason(reason != null ? reason : "User requested revocation");
        consent.setUpdatedDate(LocalDateTime.now());

        ConsentRecord revokedConsent = repository.save(consent);
        auditService.logConsentRevoked(revokedConsent, reason);

        return mapper.toResponse(revokedConsent);
    }

    @Override
    public void deleteConsent(Long id) {
        ConsentRecord consent = repository.findById(id)
                .orElseThrow(() -> new ConsentNotFoundException("Consent not found with ID: " + id));

        // Only allow deletion of non-active consents
        if (consent.getStatus() == ConsentStatus.ACTIVE) {
            throw new ConsentValidationException("Cannot delete active consent. Please revoke first.");
        }

        repository.delete(consent);
        auditService.logConsentDeleted(consent);
        log.info("Deleted consent with ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConsentRecordResponse> getConsentsByEmiratesId(String emiratesId) {
        List<ConsentRecord> consents = repository.findByEmiratesIdOrderByCreatedDateDesc(emiratesId);
        return consents.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ConsentRecordResponse> searchConsents(ConsentSearchRequest request) {
        Specification<ConsentRecord> specification = buildSearchSpecification(request);

        Sort sort = Sort.by(
                "DESC".equalsIgnoreCase(request.getSortDirection()) ?
                        Sort.Direction.DESC : Sort.Direction.ASC,
                request.getSortBy()
        );

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Page<ConsentRecord> consents = repository.findAll(specification, pageable);

        return consents.map(mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConsentRecordResponse> getActiveConsents() {
        List<ConsentRecord> consents = repository.findActiveConsents();
        return consents.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConsentRecordResponse> getAllConsents() {
        List<ConsentRecord> consents = repository.findAllConsents();
        return consents.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConsentRecordResponse> getConsentsExpiringSoon(int days) {
        LocalDate currentDate = LocalDate.now();
        LocalDate expiryDate = currentDate.plusDays(days);

        List<ConsentRecord> consents = repository.findConsentsExpiringSoon(currentDate, expiryDate);
        return consents.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ConsentStatisticsDto getConsentStatistics() {
        // Get basic counts
        long totalConsents = repository.count();
        long activeConsents = repository.countByStatus(ConsentStatus.ACTIVE);
        long revokedConsents = repository.countByStatus(ConsentStatus.REVOKED);
        long expiredConsents = repository.countByStatus(ConsentStatus.EXPIRED);
        long pendingConsents = repository.countByStatus(ConsentStatus.PENDING);
        long suspendedConsents = repository.countByStatus(ConsentStatus.SUSPENDED);

        // Get type statistics
        List<Object[]> typeStats = repository.getConsentTypeStatistics();
        Map<String, Long> consentTypeStatistics = typeStats.stream()
                .collect(Collectors.toMap(
                        row -> row[0].toString(),
                        row -> ((Number) row[1]).longValue(),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));

        // Get status statistics
        List<Object[]> statusStats = repository.getConsentStatusStatistics();
        Map<String, Long> consentStatusStatistics = statusStats.stream()
                .collect(Collectors.toMap(
                        row -> row[0].toString(),
                        row -> ((Number) row[1]).longValue(),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));

        // Get top service providers
        List<Object[]> providerStats = repository.getTopServiceProviders();
        Map<String, Long> topServiceProviders = providerStats.stream()
                .limit(10) // Top 10 service providers
                .collect(Collectors.toMap(
                        row -> row[0].toString(),
                        row -> ((Number) row[1]).longValue(),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));

        // Get monthly trends (last 12 months)
        LocalDateTime fromDate = LocalDateTime.now().minusMonths(12);
        List<Object[]> trendStats = repository.getMonthlyConsentTrends(fromDate);
        Map<String, Long> monthlyConsentTrends = trendStats.stream()
                .collect(Collectors.toMap(
                        row -> row[0].toString(),
                        row -> ((Number) row[1]).longValue(),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));

        return ConsentStatisticsDto.builder()
                .totalConsents(totalConsents)
                .activeConsents(activeConsents)
                .revokedConsents(revokedConsents)
                .expiredConsents(expiredConsents)
                .pendingConsents(pendingConsents)
                .suspendedConsents(suspendedConsents)
                .consentTypeStatistics(consentTypeStatistics)
                .consentStatusStatistics(consentStatusStatistics)
                .topServiceProviders(topServiceProviders)
                .monthlyConsentTrends(monthlyConsentTrends)
                .build();
    }

    @Override
    public List<ConsentRecordResponse> batchRevokeConsents(List<Long> consentIds, String reason) {
        log.info("Batch revoking {} consents", consentIds.size());

        List<ConsentRecord> consents = repository.findByIdIn(consentIds);

        if (consents.size() != consentIds.size()) {
            throw new ConsentNotFoundException("Some consent IDs were not found");
        }

        List<ConsentRecordResponse> revokedConsents = new ArrayList<>();

        for (ConsentRecord consent : consents) {
            if (consent.getStatus() != ConsentStatus.REVOKED) {
                ConsentStatus oldStatus = consent.getStatus();
                consent.setStatus(ConsentStatus.REVOKED);
                consent.setRevokedDate(LocalDateTime.now());
                consent.setRevocationReason(reason != null ? reason : "Batch revocation");
                consent.setUpdatedDate(LocalDateTime.now());

                ConsentRecord saved = repository.save(consent);
                auditService.logConsentRevoked(saved, reason);
                revokedConsents.add(mapper.toResponse(saved));
            }
        }

        auditService.logBulkOperation("BATCH_REVOKE", revokedConsents.size(),
                "Revoked " + revokedConsents.size() + " consents");

        return revokedConsents;
    }

    @Override
    @Scheduled(cron = "0 0 2 * * *") // Run daily at 2 AM
    public void markExpiredConsents() {
        log.info("Checking for expired consents...");

        List<ConsentRecord> expiredConsents = repository.findExpiredConsents(LocalDate.now());

        for (ConsentRecord consent : expiredConsents) {
            ConsentStatus oldStatus = consent.getStatus();
            consent.setStatus(ConsentStatus.EXPIRED);
            consent.setUpdatedDate(LocalDateTime.now());
            repository.save(consent);

            auditService.logStatusChange(consent, oldStatus, ConsentStatus.EXPIRED);
        }

        if (!expiredConsents.isEmpty()) {
            auditService.logBulkOperation("AUTO_EXPIRE", expiredConsents.size(),
                    "Automatically marked " + expiredConsents.size() + " consents as expired");
        }

        log.info("Marked {} consents as expired", expiredConsents.size());
    }

    private Specification<ConsentRecord> buildSearchSpecification(ConsentSearchRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getEmiratesId() != null && !request.getEmiratesId().trim().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("emiratesId"), request.getEmiratesId().trim()));
            }

            if (request.getCitizenName() != null && !request.getCitizenName().trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("citizenName")),
                        "%" + request.getCitizenName().toLowerCase() + "%"
                ));
            }

            if (request.getServiceProvider() != null && !request.getServiceProvider().trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("serviceProvider")),
                        "%" + request.getServiceProvider().toLowerCase() + "%"
                ));
            }

            if (request.getConsentType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("consentType"), request.getConsentType()));
            }

            if (request.getStatuses() != null && !request.getStatuses().isEmpty()) {
                predicates.add(root.get("status").in(request.getStatuses()));
            }

            if (request.getValidityStartFrom() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("validityStart"), request.getValidityStartFrom()
                ));
            }

            if (request.getValidityStartTo() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("validityStart"), request.getValidityStartTo()
                ));
            }

            if (request.getValidityEndFrom() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("validityEnd"), request.getValidityEndFrom()
                ));
            }

            if (request.getValidityEndTo() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("validityEnd"), request.getValidityEndTo()
                ));
            }

            if (request.getCreatedDateFrom() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("createdDate"), request.getCreatedDateFrom().atStartOfDay()
                ));
            }

            if (request.getCreatedDateTo() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("createdDate"), request.getCreatedDateTo().atTime(23, 59, 59)
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    /**
     * Daily cron job to automatically expire consents based on validity date
     * Runs every day at 2:00 AM
     */
    @Scheduled(cron = "0 0 2 * * *")
    @Transactional
    public void expireConsentsDaily() {
        log.info("Starting daily consent expiry job...");

        LocalDate currentDate = LocalDate.now();

        try {
            // Find all consents that should be expired but aren't marked as expired yet
            List<ConsentRecord> expiredConsents = repository.findConsentsToBeExpired(currentDate);

            if (expiredConsents.isEmpty()) {
                log.info("No consents to expire on {}", currentDate);
                return;
            }

            log.info("Found {} consents to expire on {}", expiredConsents.size(), currentDate);

            // Update each consent to expired status
            for (ConsentRecord consent : expiredConsents) {
                ConsentStatus oldStatus = consent.getStatus();
                consent.setStatus(ConsentStatus.EXPIRED);
                consent.setUpdatedDate(LocalDateTime.now());
                repository.save(consent);

                // Log for audit
                auditService.logStatusChange(consent, oldStatus, ConsentStatus.EXPIRED);
            }

            log.info("Daily consent expiry job completed. Expired {} consents", expiredConsents.size());

            // Log bulk operation
            auditService.logBulkOperation("DAILY_AUTO_EXPIRE", expiredConsents.size(),
                    "Daily job expired " + expiredConsents.size() + " consents on " + currentDate);

        } catch (Exception e) {
            log.error("Error in daily consent expiry job: ", e);
        }
    }

}

