package com.dtt.simulations.service.Iface;

import com.dtt.simulations.dto.SimIssuanceDto;
import com.dtt.simulations.responseentity.ApiResponse;

public interface TelecomSimIssuanceIface {

    ApiResponse saveSimData(SimIssuanceDto dto);
}
