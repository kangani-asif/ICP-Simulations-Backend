package com.dtt.simulations.POA.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class W3cResult {

    @JsonProperty("vpToken")
    private String vpToken;




    public String getVpToken() {
        return vpToken;
    }

    public void setVpToken(String vpToken) {
        this.vpToken = vpToken;
    }
}
