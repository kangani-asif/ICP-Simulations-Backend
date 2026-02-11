package com.dtt.simulations.service.Impl;

import com.dtt.simulations.dto.WalletTranscationDto;
import com.dtt.simulations.responseentity.ApiResponse;
import com.dtt.simulations.responseentity.AppUtil;
import com.dtt.simulations.service.Iface.WalletLogIface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WalletLogImpl implements WalletLogIface {

    RestTemplate restTemplate = new RestTemplate();

    @Value("${wallet.transaction.log}")
    public String walletTranscationLog;


    @Override
    public ApiResponse savewalletLog(WalletTranscationDto walletTranscationDto) {
        try {
            if (walletTranscationDto == null) {
                return AppUtil.createApiResponse(false, "dto cannot be null", null);
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> reqEntity1 = new HttpEntity<>(walletTranscationDto, headers);
            ResponseEntity<ApiResponse> res1 = restTemplate.exchange(walletTranscationLog, HttpMethod.POST, reqEntity1, ApiResponse.class);
            if (!res1.getBody().isSuccess()) {
                return AppUtil.createApiResponse(false, "Something went wrong. Please try after sometime", res1.getBody().getMessage());
            } else {
                return AppUtil.createApiResponse(true, "Log saved successfully", res1.getBody().getMessage());
            }


        } catch (Exception e) {
            e.printStackTrace();
            return AppUtil.createApiResponse(false, "something went wrong", null);

        }
    }
}