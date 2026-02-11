package com.dtt.simulations.POA.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DocStatusDto {

    @JsonProperty("status")
    private String status;

    @JsonProperty("edmsId")
    private String edmsId;

    @JsonProperty("recepients")
    private List<Recipients> recepientsList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getEdmsId() {
        return edmsId;
    }

    public List<Recipients> getRecepientsList() {
        return recepientsList;
    }

    public void setRecepientsList(List<Recipients> recepientsList) {
        this.recepientsList = recepientsList;
    }

    public void setEdmsId(String edmsId) {
        this.edmsId = edmsId;
    }
}
