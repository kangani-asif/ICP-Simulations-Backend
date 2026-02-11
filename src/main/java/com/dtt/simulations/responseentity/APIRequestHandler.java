package com.dtt.simulations.responseentity;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Configuration

public class APIRequestHandler {

    RestTemplate restTemplate = new RestTemplate();

    public ApiResponse handleApiRequest(String url, HttpMethod method, HttpEntity<Object> requestEntity) {
        try {
            ResponseEntity<ApiResponse> response = restTemplate.exchange(url, method, requestEntity, ApiResponse.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return AppUtil.createApiResponse(response.getBody().isSuccess(), response.getBody().getMessage(), response.getBody().getResult());

            } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
                return AppUtil.createApiResponse(false, "Bad Request", null);
            } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                return AppUtil.createApiResponse(false, "Internal Server Error", null);
            }
            return AppUtil.createApiResponse(false, "Unexpected Error", null);
        } catch (RestClientException ex) {
            return AppUtil.createApiResponse(false, "Error in API request: " + ex.getMessage(), null);
        }
    }
}
