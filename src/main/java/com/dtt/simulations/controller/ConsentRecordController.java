package com.dtt.simulations.controller;

import com.dtt.simulations.dto.*;
import com.dtt.simulations.enums.ConsentStatus;
import com.dtt.simulations.enums.ConsentType;
import com.dtt.simulations.service.Iface.ConsentRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/consents")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Consent Management", description = "APIs for managing citizen consent records")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ConsentRecordController {

    private final ConsentRecordService consentService;

    @PostMapping
    @Operation(summary = "Create a new consent record")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Consent created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "409", description = "Consent already exists")
    })
    public ResponseEntity<ConsentRecordResponse> createConsent(
            @Valid @RequestBody CreateConsentRequest request,
            HttpServletRequest httpRequest) {

        ConsentRecordResponse consent = consentService.createConsent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(consent);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get consent record by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consent found"),
            @ApiResponse(responseCode = "404", description = "Consent not found")
    })
    public ResponseEntity<ConsentRecordResponse> getConsentById(
            @Parameter(description = "Consent ID") @PathVariable Long id) {

        ConsentRecordResponse consent = consentService.getConsentById(id);
        return ResponseEntity.ok(consent);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update consent record")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consent updated successfully"),
            @ApiResponse(responseCode = "404", description = "Consent not found"),
            @ApiResponse(responseCode = "400", description = "Invalid update data")
    })
    public ResponseEntity<ConsentRecordResponse> updateConsent(
            @Parameter(description = "Consent ID") @PathVariable Long id,
            @Valid @RequestBody UpdateConsentRequest request) {

        ConsentRecordResponse consent = consentService.updateConsent(id, request);
        return ResponseEntity.ok(consent);
    }

    @PutMapping("/{id}/revoke")
    @Operation(summary = "Revoke consent record")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consent revoked successfully"),
            @ApiResponse(responseCode = "404", description = "Consent not found"),
            @ApiResponse(responseCode = "400", description = "Consent already revoked")
    })
    public ResponseEntity<ConsentRecordResponse> revokeConsent(
            @Parameter(description = "Consent ID") @PathVariable Long id,
            @Parameter(description = "Revocation reason") @RequestParam(required = false) String reason) {

        ConsentRecordResponse consent = consentService.revokeConsent(id, reason);
        return ResponseEntity.ok(consent);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete consent record")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Consent deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Consent not found"),
            @ApiResponse(responseCode = "400", description = "Cannot delete active consent")
    })
    public ResponseEntity<Void> deleteConsent(
            @Parameter(description = "Consent ID") @PathVariable Long id) {

        consentService.deleteConsent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/citizen/{emiratesId}")
    @Operation(summary = "Get all consents for a citizen")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consents retrieved successfully")
    })
    public ResponseEntity<List<ConsentRecordResponse>> getConsentsByEmiratesId(
            @Parameter(description = "Emirates ID") @PathVariable String emiratesId) {

        List<ConsentRecordResponse> consents = consentService.getConsentsByEmiratesId(emiratesId);
        return ResponseEntity.ok(consents);
    }

    @GetMapping("/search")
    @Operation(summary = "Search consents with filters")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Search completed successfully")
    })
    public ResponseEntity<Page<ConsentRecordResponse>> searchConsents(
            @Parameter(description = "Emirates ID") @RequestParam(required = false) String emiratesId,
            @Parameter(description = "Citizen name") @RequestParam(required = false) String citizenName,
            @Parameter(description = "Service provider") @RequestParam(required = false) String serviceProvider,
            @Parameter(description = "Consent type") @RequestParam(required = false) ConsentType consentType,
            @Parameter(description = "Status") @RequestParam(required = false) List<ConsentStatus> statuses,
            @Parameter(description = "Validity start from") @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate validityStartFrom,
            @Parameter(description = "Validity start to") @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate validityStartTo,
            @Parameter(description = "Validity end from") @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate validityEndFrom,
            @Parameter(description = "Validity end to") @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate validityEndTo,
            @Parameter(description = "Created date from") @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdDateFrom,
            @Parameter(description = "Created date to") @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdDateTo,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "createdDate") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "DESC") String sortDirection,
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size) {

        ConsentSearchRequest searchRequest = ConsentSearchRequest.builder()
                .emiratesId(emiratesId)
                .citizenName(citizenName)
                .serviceProvider(serviceProvider)
                .consentType(consentType)
                .statuses(statuses)
                .validityStartFrom(validityStartFrom)
                .validityStartTo(validityStartTo)
                .validityEndFrom(validityEndFrom)
                .validityEndTo(validityEndTo)
                .createdDateFrom(createdDateFrom)
                .createdDateTo(createdDateTo)
                .sortBy(sortBy)
                .sortDirection(sortDirection)
                .page(page)
                .size(size)
                .build();

        Page<ConsentRecordResponse> results = consentService.searchConsents(searchRequest);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/active")
    @Operation(summary = "Get all active consents")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Active consents retrieved successfully")
    })
    public ResponseEntity<List<ConsentRecordResponse>> getActiveConsents() {
        List<ConsentRecordResponse> consents = consentService.getActiveConsents();
        return ResponseEntity.ok(consents);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all consents")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "consents retrieved successfully")
    })
    public ResponseEntity<List<ConsentRecordResponse>> getAllConsents() {
        List<ConsentRecordResponse> consents = consentService.getAllConsents();
        return ResponseEntity.ok(consents);
    }

    @GetMapping("/expiring")
    @Operation(summary = "Get consents expiring soon")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Expiring consents retrieved successfully")
    })
    public ResponseEntity<List<ConsentRecordResponse>> getConsentsExpiringSoon(
            @Parameter(description = "Days until expiry") @RequestParam(defaultValue = "30") int days) {

        List<ConsentRecordResponse> consents = consentService.getConsentsExpiringSoon(days);
        return ResponseEntity.ok(consents);
    }

    @GetMapping("/statistics")
    @Operation(summary = "Get consent statistics")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Statistics retrieved successfully")
    })
    public ResponseEntity<ConsentStatisticsDto> getConsentStatistics() {
        ConsentStatisticsDto statistics = consentService.getConsentStatistics();
        return ResponseEntity.ok(statistics);
    }

    @PostMapping("/batch/revoke")
    @Operation(summary = "Revoke multiple consents")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consents revoked successfully")
    })
    public ResponseEntity<List<ConsentRecordResponse>> batchRevokeConsents(
            @RequestBody List<Long> consentIds,
            @Parameter(description = "Revocation reason") @RequestParam(required = false) String reason) {

        List<ConsentRecordResponse> revokedConsents = consentService.batchRevokeConsents(consentIds, reason);
        return ResponseEntity.ok(revokedConsents);
    }
}
