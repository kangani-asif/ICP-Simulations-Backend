package com.dtt.simulations.service.Iface;


import com.dtt.simulations.dto.CreateConsentRequest;
import com.dtt.simulations.dto.UpdateConsentRequest;
import com.dtt.simulations.enums.ConsentStatus;
import com.dtt.simulations.enums.ConsentType;
import com.dtt.simulations.model.ConsentRecord;

public interface ConsentValidationService {

    void validateCreateRequest(CreateConsentRequest request);

    void validateUpdateRequest(ConsentRecord existing, UpdateConsentRequest request);

    void validateStatusTransition(ConsentStatus from,ConsentStatus to);

    void validateConsentTypeSpecificRules(ConsentType type, CreateConsentRequest request);
}
