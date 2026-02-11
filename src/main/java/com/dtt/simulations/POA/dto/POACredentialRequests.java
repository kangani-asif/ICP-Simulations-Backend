package com.dtt.simulations.POA.dto;

import java.util.List;

public class POACredentialRequests {
    private String type;
    private SelectedClaims selectedClaims;
    private String scope;
    private String clientId;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SelectedClaims getSelectedClaims() {
        return selectedClaims;
    }

    public void setSelectedClaims(SelectedClaims selectedClaims) {
        this.selectedClaims = selectedClaims;
    }

}