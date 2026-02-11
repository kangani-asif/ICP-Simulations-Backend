package com.dtt.simulations.model;

import com.dtt.simulations.enums.ConsentMethod;
import com.dtt.simulations.enums.ConsentStatus;
import com.dtt.simulations.enums.ConsentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "consent_records", indexes = {
        @Index(name = "idx_emirates_id", columnList = "emirates_id"),
        @Index(name = "idx_service_provider", columnList = "service_provider"),
        @Index(name = "idx_status", columnList = "status"),
        @Index(name = "idx_consent_type", columnList = "consent_type"),
        @Index(name = "idx_validity_end", columnList = "validity_end")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Citizen name is required")
    @Column(name = "citizen_name", nullable = false, length = 200)
    private String citizenName;

    @NotBlank(message = "Emirates ID is required")
    @Column(name = "emirates_id", nullable = false, length = 25)
    private String emiratesId;

    @NotBlank(message = "Service provider is required")
    @Column(name = "service_provider", nullable = false, length = 200)
    private String serviceProvider;

    @NotNull(message = "Consent type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "consent_type", nullable = false, length = 40)
    private ConsentType consentType;

    @NotBlank(message = "Purpose is required")
    @Column(name = "purpose", nullable = false, columnDefinition = "TEXT")
    private String purpose;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "consent_data_categories",
            joinColumns = @JoinColumn(name = "consent_id"))
    @Column(name = "category", nullable = false, length = 150)
    private List<String> dataCategories;

    @NotNull(message = "Validity start date is required")
    @Column(name = "validity_start", nullable = false)
    private LocalDate validityStart;

    @NotNull(message = "Validity end date is required")
    @Column(name = "validity_end", nullable = false)
    private LocalDate validityEnd;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    @Builder.Default
    private ConsentStatus status = ConsentStatus.PENDING;

    @NotNull(message = "Consent method is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "consent_method", nullable = false, length = 40)
    private ConsentMethod consentMethod;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "revocation_reason", length = 1000)
    private String revocationReason;

    @Column(name = "revoked_date")
    private LocalDateTime revokedDate;

    @Column(name = "digital_signature", columnDefinition = "LONGTEXT")
    private String digitalSignature;

    @Column(name = "ip_address", length = 64)
    private String ipAddress;

    @Column(name = "user_agent", length = 512)
    private String userAgent;

    // Business logic methods
    public boolean isActive() {
        return status == ConsentStatus.ACTIVE &&
                LocalDate.now().isBefore(validityEnd.plusDays(1)) &&
                !LocalDate.now().isBefore(validityStart);
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(validityEnd);
    }

    public long getDaysUntilExpiry() {
        return java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), validityEnd);
    }
}

