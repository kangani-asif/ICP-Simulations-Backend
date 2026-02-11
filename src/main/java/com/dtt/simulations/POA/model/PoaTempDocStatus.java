package com.dtt.simulations.POA.model;


import jakarta.persistence.*;

@Entity
@Table(name = "temp_doc_status_data")
public class PoaTempDocStatus {


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

    @Override
    public String toString() {
        return "TempDocStatus{" +
                "id=" + id +
                ", docId='" + docId + '\'' +
                ", status='" + status + '\'' +
                ", poaId=" + poaId +
                '}';
    }
}
