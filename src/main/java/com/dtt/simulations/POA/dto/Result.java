package com.dtt.simulations.POA.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {
    @JsonProperty("tempid") // Match the key in the JSON
    private String tempId;

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }
}
