package com.dtt.simulations.service.Iface;


import com.dtt.simulations.dto.ConsentRecordResponse;
import com.dtt.simulations.dto.CreateConsentRequest;
import com.dtt.simulations.model.ConsentRecord;

public interface ConsentMapper {

    ConsentRecord toEntity(CreateConsentRequest request);

    ConsentRecordResponse toResponse(ConsentRecord entity);
}

