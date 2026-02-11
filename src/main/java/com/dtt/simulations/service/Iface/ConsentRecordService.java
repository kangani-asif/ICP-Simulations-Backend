package com.dtt.simulations.service.Iface;


import com.dtt.simulations.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ConsentRecordService {

    ConsentRecordResponse createConsent(CreateConsentRequest request);

    ConsentRecordResponse getConsentById(Long id);

    ConsentRecordResponse updateConsent(Long id, UpdateConsentRequest request);

    ConsentRecordResponse revokeConsent(Long id, String reason);

    void deleteConsent(Long id);

    List<ConsentRecordResponse> getConsentsByEmiratesId(String emiratesId);

    Page<ConsentRecordResponse> searchConsents(ConsentSearchRequest request);

    List<ConsentRecordResponse> getActiveConsents();

    List<ConsentRecordResponse> getConsentsExpiringSoon(int days);

    ConsentStatisticsDto getConsentStatistics();

    List<ConsentRecordResponse> batchRevokeConsents(List<Long> consentIds, String reason);

    void markExpiredConsents();

    List<ConsentRecordResponse> getAllConsents();
}

