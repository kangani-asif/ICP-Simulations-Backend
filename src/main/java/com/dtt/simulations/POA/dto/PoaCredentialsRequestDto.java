package com.dtt.simulations.POA.dto;

public class PoaCredentialsRequestDto {

    private String principleEmail;

    private String principleName;

    private String agentEmail;

    private String agentName;

//    private String json;

    private String delegationUpto;

    private String status;

    private String poaRequestForm;

    private String principleIdDocNumber;

    private String principleSuid;

    private String agentIdDocNumber;

    private String agentSuid;

    private String notaryName;

    private String notaryIdDocNumber;

    private String notaryEmail;

    private String notarySuid;

    private String scope;

    private String poaDocSigned;

    private String createdOn;

    private String updatedOn;

    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPrincipleEmail() {
        return principleEmail;
    }

    public void setPrincipleEmail(String principleEmail) {
        this.principleEmail = principleEmail;
    }

    public String getPrincipleName() {
        return principleName;
    }

    public void setPrincipleName(String principleName) {
        this.principleName = principleName;
    }

    public String getAgentEmail() {
        return agentEmail;
    }

    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

//    public String getJson() {
//        return json;
//    }
//
//    public void setJson(String json) {
//        this.json = json;
//    }

    public String getDelegationUpto() {
        return delegationUpto;
    }

    public void setDelegationUpto(String delegationUpto) {
        this.delegationUpto = delegationUpto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPoaRequestForm() {
        return poaRequestForm;
    }

    public void setPoaRequestForm(String poaRequestForm) {
        this.poaRequestForm = poaRequestForm;
    }

    public String getPrincipleIdDocNumber() {
        return principleIdDocNumber;
    }

    public void setPrincipleIdDocNumber(String principleIdDocNumber) {
        this.principleIdDocNumber = principleIdDocNumber;
    }

    public String getPrincipleSuid() {
        return principleSuid;
    }

    public void setPrincipleSuid(String principleSuid) {
        this.principleSuid = principleSuid;
    }

    public String getAgentIdDocNumber() {
        return agentIdDocNumber;
    }

    public void setAgentIdDocNumber(String agentIdDocNumber) {
        this.agentIdDocNumber = agentIdDocNumber;
    }

    public String getAgentSuid() {
        return agentSuid;
    }

    public void setAgentSuid(String agentSuid) {
        this.agentSuid = agentSuid;
    }

    public String getNotaryName() {
        return notaryName;
    }

    public void setNotaryName(String notaryName) {
        this.notaryName = notaryName;
    }

    public String getNotaryIdDocNumber() {
        return notaryIdDocNumber;
    }

    public void setNotaryIdDocNumber(String notaryIdDocNumber) {
        this.notaryIdDocNumber = notaryIdDocNumber;
    }

    public String getNotaryEmail() {
        return notaryEmail;
    }

    public void setNotaryEmail(String notaryEmail) {
        this.notaryEmail = notaryEmail;
    }

    public String getNotarySuid() {
        return notarySuid;
    }

    public void setNotarySuid(String notarySuid) {
        this.notarySuid = notarySuid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getPoaDocSigned() {
        return poaDocSigned;
    }

    public void setPoaDocSigned(String poaDocSigned) {
        this.poaDocSigned = poaDocSigned;
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

    @Override
    public String toString() {
        return "PoaCredentialsRequestDto{" +
                "principleEmail='" + principleEmail + '\'' +
                ", principleName='" + principleName + '\'' +
                ", agentEmail='" + agentEmail + '\'' +
                ", agentName='" + agentName + '\'' +
                ", delegationUpto='" + delegationUpto + '\'' +
                ", status='" + status + '\'' +
                ", poaRequestForm='" + poaRequestForm + '\'' +
                ", principleIdDocNumber='" + principleIdDocNumber + '\'' +
                ", principleSuid='" + principleSuid + '\'' +
                ", agentIdDocNumber='" + agentIdDocNumber + '\'' +
                ", agentSuid='" + agentSuid + '\'' +
                ", notaryName='" + notaryName + '\'' +
                ", notaryIdDocNumber='" + notaryIdDocNumber + '\'' +
                ", notaryEmail='" + notaryEmail + '\'' +
                ", notarySuid='" + notarySuid + '\'' +
                ", scope='" + scope + '\'' +
                ", poaDocSigned='" + poaDocSigned + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", updatedOn='" + updatedOn + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
