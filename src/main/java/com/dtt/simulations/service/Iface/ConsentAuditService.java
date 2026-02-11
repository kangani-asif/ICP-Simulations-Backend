package com.dtt.simulations.service.Iface;


import com.dtt.simulations.enums.ConsentStatus;
import com.dtt.simulations.model.ConsentRecord;

public interface ConsentAuditService {

    void logConsentCreated(ConsentRecord consent);

    void logConsentUpdated(ConsentRecord consent);

    void logStatusChange(ConsentRecord consent, ConsentStatus oldStatus, ConsentStatus newStatus);

    void logConsentRevoked(ConsentRecord consent, String reason);

    void logConsentDeleted(ConsentRecord consent);

    void logBulkOperation(String operation, int count, String details);
}

