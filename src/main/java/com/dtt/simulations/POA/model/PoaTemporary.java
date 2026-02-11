package com.dtt.simulations.POA.model;

import jakarta.persistence.*;


@Entity
@Table(name = "temporary_poa")
public class PoaTemporary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "doc_id")
    private String docId;

    @Column(name = "status")
    private String status;

    @Column(name = "poa_id")
    private int poaId;

    @Column(name = "agent")
    private boolean agent;

    @Column(name = "notary")
    private boolean notary;


    @Column(name = "principle_id_doc_number")
    private String principleIdDocNumber;

    @Column(name = "principle_email")
    private String principleEmail;

    @Column(name = "principle_name")
    private String principleName;

    @Column(name = "principle_suid")
    private String principleSuid;

    @Column(name = "agent_id_doc_number")
    private String agentIdDocNumber;

    @Column(name = "agent_email")
    private String agentEmail;

    @Column(name = "agent_name")
    private String agentName;

    @Column(name = "agent_suid")
    private String agentSuid;

    @Column(name = "notary_name")
    private String notaryName;

    @Column(name = "notary_id_doc_number")
    private String notaryIdDocNumber;

    @Column(name = "notary_email")
    private String notaryEmail;

    @Column(name = "notary_suid")
    private String notarySuid;

    @Column(name = "additional_fields")
    private String json;

    @Column(name = "effective_date")
    private String effectiveDate;

    @Column(name = "poa_request_form")
    private String poaRequestForm;

    @Column(name = "scope")
    private String scope;

    @Column(name = "created_on")
    private String createdOn;

    @Column(name = "updated_on")
    private String updatedOn;


    public boolean isAgent() {
        return agent;
    }

    public void setAgent(boolean agent) {
        this.agent = agent;
    }

    public boolean isNotary() {
        return notary;
    }

    public void setNotary(boolean notary) {
        this.notary = notary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPoaId() {
        return poaId;
    }

    public void setPoaId(int poaId) {
        this.poaId = poaId;
    }

    public String getPrincipleIdDocNumber() {
        return principleIdDocNumber;
    }

    public void setPrincipleIdDocNumber(String principleIdDocNumber) {
        this.principleIdDocNumber = principleIdDocNumber;
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

    public String getAgentIdDocNumber() {
        return agentIdDocNumber;
    }

    public void setAgentIdDocNumber(String agentIdDocNumber) {
        this.agentIdDocNumber = agentIdDocNumber;
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

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getPoaRequestForm() {
        return poaRequestForm;
    }

    public void setPoaRequestForm(String poaRequestForm) {
        this.poaRequestForm = poaRequestForm;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
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


    public String getPrincipleSuid() {
        return principleSuid;
    }

    public void setPrincipleSuid(String principleSuid) {
        this.principleSuid = principleSuid;
    }

    public String getAgentSuid() {
        return agentSuid;
    }

    public void setAgentSuid(String agentSuid) {
        this.agentSuid = agentSuid;
    }

    public String getNotarySuid() {
        return notarySuid;
    }

    public void setNotarySuid(String notarySuid) {
        this.notarySuid = notarySuid;
    }

    @Override
    public String toString() {
        return "TemporaryPoa{" +
                "id=" + id +
                ", docId='" + docId + '\'' +
                ", status='" + status + '\'' +
                ", poaId=" + poaId +
                ", agent=" + agent +
                ", notary=" + notary +
                ", principleIdDocNumber='" + principleIdDocNumber + '\'' +
                ", principleEmail='" + principleEmail + '\'' +
                ", principleName='" + principleName + '\'' +
                ", principleSuid='" + principleSuid + '\'' +
                ", agentIdDocNumber='" + agentIdDocNumber + '\'' +
                ", agentEmail='" + agentEmail + '\'' +
                ", agentName='" + agentName + '\'' +
                ", agentSuid='" + agentSuid + '\'' +
                ", notaryName='" + notaryName + '\'' +
                ", notaryIdDocNumber='" + notaryIdDocNumber + '\'' +
                ", notaryEmail='" + notaryEmail + '\'' +
                ", notarySuid='" + notarySuid + '\'' +
                ", json='" + json + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", poaRequestForm='" + poaRequestForm + '\'' +
                ", scope='" + scope + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", updatedOn='" + updatedOn + '\'' +
                '}';
    }
}
