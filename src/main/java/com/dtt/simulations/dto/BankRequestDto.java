package com.dtt.simulations.dto;

public class BankRequestDto {

    private String userId;

    private String userIdType;

    private String profileType;

    private String purpose;

    private String clientId;

    private String scopes;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIdType() {
        return userIdType;
    }

    public void setUserIdType(String userIdType) {
        this.userIdType = userIdType;
    }

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    @Override
    public String toString() {
        return "BankRequestDto{" +
                "UserId='" + userId + '\'' +
                ", UserIdType='" + userIdType + '\'' +
                ", ProfileType='" + profileType + '\'' +
                ", Purpose='" + purpose + '\'' +
                ", ClientId='" + clientId + '\'' +
                ", Scopes='" + scopes + '\'' +
                '}';
    }
}
