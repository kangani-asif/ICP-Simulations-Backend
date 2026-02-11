package com.dtt.simulations.model;


import jakarta.persistence.*;

@Entity
@Table(name = "use_of_poa")
public class MoneyTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "principal_name")
    private String principalName;

    @Column(name = "principal_email")
    private String principalEmail;

    @Column(name = "principal_id_doc_number")
    private String principalIdDocNumber;

    @Column(name = "notary_name")
    private String notaryName;

    @Column(name = "notary_email")
    private String notaryEmail;

    @Column(name = "notary_id_doc_number")
    private String notaryIdDocNumber;


    @Column(name = "agent_name")
    private String agentName;

    @Column(name = "agent_email")
    private String agentEmail;

    @Column(name = "agent_id_doc_number")
    private String agentIdDocNumber;

    @Column(name = "poa_signed_doc")
    private String poaSignedDoc;

    @Column(name = "created_on")
    private String createdOn;

    @Column(name = "updated_on")
    private String updatedOn;

    @Column(name = "valid_upto")
    private String validUpto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getPrincipalEmail() {
        return principalEmail;
    }

    public void setPrincipalEmail(String principalEmail) {
        this.principalEmail = principalEmail;
    }

    public String getPrincipalIdDocNumber() {
        return principalIdDocNumber;
    }

    public void setPrincipalIdDocNumber(String principalIdDocNumber) {
        this.principalIdDocNumber = principalIdDocNumber;
    }

    public String getNotaryName() {
        return notaryName;
    }

    public void setNotaryName(String notaryName) {
        this.notaryName = notaryName;
    }

    public String getNotaryEmail() {
        return notaryEmail;
    }

    public void setNotaryEmail(String notaryEmail) {
        this.notaryEmail = notaryEmail;
    }

    public String getNotaryIdDocNumber() {
        return notaryIdDocNumber;
    }

    public void setNotaryIdDocNumber(String notaryIdDocNumber) {
        this.notaryIdDocNumber = notaryIdDocNumber;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentEmail() {
        return agentEmail;
    }

    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }

    public String getAgentIdDocNumber() {
        return agentIdDocNumber;
    }

    public void setAgentIdDocNumber(String agentIdDocNumber) {
        this.agentIdDocNumber = agentIdDocNumber;
    }

    public String getPoaSignedDoc() {
        return poaSignedDoc;
    }

    public void setPoaSignedDoc(String poaSignedDoc) {
        this.poaSignedDoc = poaSignedDoc;
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

    public String getValidUpto() {
        return validUpto;
    }

    public void setValidUpto(String validUpto) {
        this.validUpto = validUpto;
    }

    @Override
    public String toString() {
        return "MoneyTransfer{" +
                "id=" + id +
                ", principalName='" + principalName + '\'' +
                ", principalEmail='" + principalEmail + '\'' +
                ", principalIdDocNumber='" + principalIdDocNumber + '\'' +
                ", notaryName='" + notaryName + '\'' +
                ", notaryEmail='" + notaryEmail + '\'' +
                ", notaryIdDocNumber='" + notaryIdDocNumber + '\'' +
                ", agentName='" + agentName + '\'' +
                ", agentEmail='" + agentEmail + '\'' +
                ", agentIdDocNumber='" + agentIdDocNumber + '\'' +
                ", poaSignedDoc='" + poaSignedDoc + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", updatedOn='" + updatedOn + '\'' +
                ", validUpto='" + validUpto + '\'' +
                '}';
    }
}