package com.dtt.simulations.POA.model;


import jakarta.persistence.*;

@Entity
@Table(name = "poa_credentials_new")
public class PoaCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "agent_suid")
    private String agentSuid;

    @Column(name = "agent_name")
    private String agentName;

    @Column(name = "agent_id_doc_number")
    private String agentIdDocNumber;

    @Column(name = "poa_credential")
    private String poaCredential;

    @Column(name = "agent_email")
    private String agentEmail;

    @Column(name = "created_on")
    private String createdOn;

    @Column(name = "updated_on")
    private String updatedOn;

    @Column(name = "status")
    private String status;


    @Column(name = "agent_photo")
    private String agentPhoto;

    public String getAgentPhoto() {
        return agentPhoto;
    }

    public void setAgentPhoto(String agentPhoto) {
        this.agentPhoto = agentPhoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "PoaCredentials{" +
                "id=" + id +
                ", agentSuid='" + agentSuid + '\'' +
                ", agentName='" + agentName + '\'' +
                ", agentIdDocNumber='" + agentIdDocNumber + '\'' +
                ", poaCredential='" + poaCredential + '\'' +
                ", agentEmail='" + agentEmail + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", updatedOn='" + updatedOn + '\'' +
                ", status='" + status + '\'' +
                ", agentPhoto='" + agentPhoto + '\'' +
                '}';
    }
}
