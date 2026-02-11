package com.dtt.simulations.POA.service.Iface;


import com.dtt.simulations.POA.dto.PowerOfAttorneyDto;
import com.dtt.simulations.POA.dto.SavePoaDto;
import com.dtt.simulations.POA.responseentity.ApiResponsePOA;

import java.io.IOException;

public interface PowerOfAttorneyIface {
    ApiResponsePOA savePoaInTemp(SavePoaDto powerOfAttorney);
    ApiResponsePOA getStatus(int id);
    ApiResponsePOA getNotaryAndScopeInformation();
    ApiResponsePOA transferPoa(String agentEmail);
    ApiResponsePOA getAllPoaCredentialRequests(String principalDocumentNumber);
    ApiResponsePOA getAllPoaCredentials(String suid);
    public String modifypoa(PowerOfAttorneyDto powerOfAttorneyDto)throws IOException, java.io.IOException;

    ApiResponsePOA savePoa(SavePoaDto powerOfAttorney);

}
