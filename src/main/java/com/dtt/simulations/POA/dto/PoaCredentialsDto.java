package com.dtt.simulations.POA.dto;

public class PoaCredentialsDto {

    private String agentSuid;

    private String agentName;

    private String agentIdDocNumber;

    private String poaCredential;

    private String agentEmail;

    private String createdOn;

    private String updatedOn;

    private String status;

    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAgentSuid() {
        return agentSuid;
    }

    public void setAgentSuid(String agentSuid) {
        this.agentSuid = agentSuid;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentIdDocNumber() {
        return agentIdDocNumber;
    }

    public void setAgentIdDocNumber(String agentIdDocNumber) {
        this.agentIdDocNumber = agentIdDocNumber;
    }

    public String getPoaCredential() {
        return poaCredential;
    }

    public void setPoaCredential(String poaCredential) {
        this.poaCredential = poaCredential;
    }

    public String getAgentEmail() {
        return agentEmail;
    }

    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "PoaCredentialsDto{" +
                "agentSuid='" + agentSuid + '\'' +
                ", agentName='" + agentName + '\'' +
                ", agentIdDocNumber='" + agentIdDocNumber + '\'' +
                ", poaCredential='" + poaCredential + '\'' +
                ", agentEmail='" + agentEmail + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", updatedOn='" + updatedOn + '\'' +
                ", status='" + status + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
