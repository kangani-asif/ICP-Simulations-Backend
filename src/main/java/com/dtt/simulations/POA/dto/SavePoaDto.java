package com.dtt.simulations.POA.dto;

public class SavePoaDto {

        private String principleEmail;
        private String principleName;
        private String principleIdDocNumber;
        private String agentEmail;
        private String agentName;
        private String agentIdDocNumber;
        private String notaryName;
        private String notaryIdDocNumber;
        private String notaryEmail;
        private String effectiveFrom;
        private String scope;

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

    public String getPrincipleIdDocNumber() {
        return principleIdDocNumber;
    }

    public void setPrincipleIdDocNumber(String principleIdDocNumber) {
        this.principleIdDocNumber = principleIdDocNumber;
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

    public String getAgentIdDocNumber() {
        return agentIdDocNumber;
    }

    public void setAgentIdDocNumber(String agentIdDocNumber) {
        this.agentIdDocNumber = agentIdDocNumber;
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

    public String getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(String effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }


    @Override
    public String toString() {
        return "SavePoaDto{" +
                "principleEmail='" + principleEmail + '\'' +
                ", principleName='" + principleName + '\'' +
                ", principleIdDocNumber='" + principleIdDocNumber + '\'' +
                ", agentEmail='" + agentEmail + '\'' +
                ", agentName='" + agentName + '\'' +
                ", agentIdDocNumber='" + agentIdDocNumber + '\'' +
                ", notaryName='" + notaryName + '\'' +
                ", notaryIdDocNumber='" + notaryIdDocNumber + '\'' +
                ", notaryEmail='" + notaryEmail + '\'' +
                ", effectiveFrom='" + effectiveFrom + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }
}
