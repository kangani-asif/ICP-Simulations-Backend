package com.dtt.simulations.repo;

import com.dtt.simulations.enums.ConsentStatus;
import com.dtt.simulations.enums.ConsentType;
import com.dtt.simulations.model.ConsentRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConsentRecordRepository extends JpaRepository<ConsentRecord, Long>,
        JpaSpecificationExecutor<ConsentRecord> {

    // Basic finder methods
    List<ConsentRecord> findByEmiratesIdOrderByCreatedDateDesc(String emiratesId);

    List<ConsentRecord> findByServiceProviderOrderByCreatedDateDesc(String serviceProvider);

    Optional<ConsentRecord> findByEmiratesIdAndServiceProviderAndStatus(
            String emiratesId, String serviceProvider, ConsentStatus status);

    // Status-based queries
//    @Query("SELECT c FROM ConsentRecord c WHERE c.status = 'ACTIVE' AND c.validityStart <= :currentDate AND c.validityEnd >= :currentDate")
//    List<ConsentRecord> findActiveConsents(@Param("currentDate") LocalDate currentDate);

    @Query("SELECT c FROM ConsentRecord c WHERE c.status = 'ACTIVE'")
    List<ConsentRecord> findActiveConsents();

    @Query("SELECT c FROM ConsentRecord c")
    List<ConsentRecord> findAllConsents();


    @Query("SELECT c FROM ConsentRecord c WHERE c.validityEnd < :currentDate AND c.status != 'EXPIRED'")
    List<ConsentRecord> findExpiredConsents(@Param("currentDate") LocalDate currentDate);

    @Query("SELECT c FROM ConsentRecord c WHERE c.status = 'ACTIVE' AND c.validityEnd BETWEEN :currentDate AND :expiryDate")
    List<ConsentRecord> findConsentsExpiringSoon(@Param("currentDate") LocalDate currentDate,
                                                 @Param("expiryDate") LocalDate expiryDate);

    // Count queries
    long countByStatus(ConsentStatus status);

    long countByConsentType(ConsentType consentType);

    long countByEmiratesId(String emiratesId);

    long countByServiceProvider(String serviceProvider);

    // Recent consents
    @Query("SELECT c FROM ConsentRecord c WHERE c.createdDate >= :fromDate ORDER BY c.createdDate DESC")
    List<ConsentRecord> findRecentConsents(@Param("fromDate") LocalDateTime fromDate);

    // Advanced search query
    @Query("SELECT c FROM ConsentRecord c WHERE " +
            "(:emiratesId IS NULL OR c.emiratesId = :emiratesId) AND " +
            "(:serviceProvider IS NULL OR LOWER(c.serviceProvider) LIKE LOWER(CONCAT('%', :serviceProvider, '%'))) AND " +
            "(:consentType IS NULL OR c.consentType = :consentType) AND " +
            "(:status IS NULL OR c.status = :status) AND " +
            "(:fromDate IS NULL OR c.createdDate >= :fromDate) AND " +
            "(:toDate IS NULL OR c.createdDate <= :toDate)")
    Page<ConsentRecord> findByMultipleCriteria(
            @Param("emiratesId") String emiratesId,
            @Param("serviceProvider") String serviceProvider,
            @Param("consentType") ConsentType consentType,
            @Param("status") ConsentStatus status,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate,
            Pageable pageable);

    // Statistics queries
    @Query("SELECT c.consentType, COUNT(c) FROM ConsentRecord c GROUP BY c.consentType")
    List<Object[]> getConsentTypeStatistics();

    @Query("SELECT c.status, COUNT(c) FROM ConsentRecord c GROUP BY c.status")
    List<Object[]> getConsentStatusStatistics();

    @Query("SELECT c.serviceProvider, COUNT(c) FROM ConsentRecord c GROUP BY c.serviceProvider ORDER BY COUNT(c) DESC")
    List<Object[]> getTopServiceProviders();

    @Query("SELECT FUNCTION('DATE_FORMAT', c.createdDate, '%Y-%m'), COUNT(c) FROM ConsentRecord c " +
            "WHERE c.createdDate >= :fromDate GROUP BY FUNCTION('DATE_FORMAT', c.createdDate, '%Y-%m') " +
            "ORDER BY FUNCTION('DATE_FORMAT', c.createdDate, '%Y-%m')")
    List<Object[]> getMonthlyConsentTrends(@Param("fromDate") LocalDateTime fromDate);

    // Bulk operations support
    @Query("SELECT c FROM ConsentRecord c WHERE c.id IN :ids")
    List<ConsentRecord> findByIdIn(@Param("ids") List<Long> ids);



    @Query("SELECT c FROM ConsentRecord c WHERE c.validityEnd < :currentDate AND c.status != 'EXPIRED' AND c.status != 'REVOKED'")
    List<ConsentRecord> findConsentsToBeExpired(@Param("currentDate") LocalDate currentDate);


}

