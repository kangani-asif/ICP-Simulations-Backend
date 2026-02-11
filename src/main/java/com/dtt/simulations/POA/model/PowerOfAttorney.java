package com.dtt.simulations.POA.model;


import jakarta.persistence.*;

@Entity
@Table(name = "power_of_attorney")
public class PowerOfAttorney {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @Column(name = "principle_email")
        private String principleEmail;

        @Column(name = "principle_name")
        private String principleName;

        @Column(name = "agent_email")
        private String agentEmail;

        @Column(name = "agent_name")
        private String agentName;

        @Column(name = "additional_fields")
        private String json;



        @Column(name = "effective_from")
        private String effectiveFrom;

        @Column(name = "status")
        private String status;

        @Column(name = "poa_request_form")
        private String poaRequestForm;

        @Column(name = "principle_id_doc_number")
        private String principleIdDocNumber;

        @Column(name = "principle_suid")
        private String principleSuid;

        @Column(name = "agent_id_doc_number")
        private String agentIdDocNumber;

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

        @Column(name = "scope")
        private String scope;

        @Column(name = "poa_doc_signed")
        private String poaDocSigned;

        @Column(name = "created_on")
        private String createdOn;

        @Column(name = "updated_on")
        private String updatedOn;

        @Column(name="principle_photo")
        private String principalPhoto;

        public String getPrincipalPhoto() {
            return principalPhoto;
        }

        public void setPrincipalPhoto(String principalPhoto) {
            this.principalPhoto = principalPhoto;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getJson() {
            return json;
        }

        public void setJson(String json) {
            this.json = json;
        }

    public String getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(String effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
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
        return "PowerOfAttorneyNew{" +
                "id=" + id +
                ", principleEmail='" + principleEmail + '\'' +
                ", principleName='" + principleName + '\'' +
                ", agentEmail='" + agentEmail + '\'' +
                ", agentName='" + agentName + '\'' +
                ", json='" + json + '\'' +
                ", effectiveFrom='" + effectiveFrom + '\'' +
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
                ", principalPhoto='" + principalPhoto + '\'' +
                '}';
    }
}


