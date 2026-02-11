package com.dtt.simulations.POA.dto;

public class PoaDto {

    private String principalName;
    private String AgentName;
    private String NotaryName;
    private String status;

    private int id;

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

    public String getAgentName() {
        return AgentName;
    }

    public void setAgentName(String agentName) {
        AgentName = agentName;
    }

    public String getNotaryName() {
        return NotaryName;
    }

    public void setNotaryName(String notaryName) {
        NotaryName = notaryName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PoaDto{" +
                "principalName='" + principalName + '\'' +
                ", AgentName='" + AgentName + '\'' +
                ", NotaryName='" + NotaryName + '\'' +
                ", status='" + status + '\'' +
                ", id=" + id +
                '}';
    }
}
