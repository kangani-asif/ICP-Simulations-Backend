package com.dtt.simulations.POA.dto;

import java.util.List;

public class Receps {
    private  String index; //empty

    private int order;

    private String email; //uaeid email

    private String suid;

    private String name;

    private String alternateSignatories; // empty

    private List<User> alternateSignatoriesList; //empty list

    private boolean allowComments; //false

    private  boolean signatureMandatory; //true

    private boolean eseal; //true

    private boolean hasDelegation; //false

    private String  delegationId; //empty

    private  String referredBy; //empty

    private String referredTo; //empty

    private String signedBy; //empty

    private String orgUID; //empty

    private String orgName; //empty


    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlternateSignatories() {
        return alternateSignatories;
    }

    public void setAlternateSignatories(String alternateSignatories) {
        this.alternateSignatories = alternateSignatories;
    }

    public List<User> getAlternateSignatoriesList() {
        return alternateSignatoriesList;
    }

    public void setAlternateSignatoriesList(List<User> alternateSignatoriesList) {
        this.alternateSignatoriesList = alternateSignatoriesList;
    }

    public boolean isAllowComments() {
        return allowComments;
    }

    public void setAllowComments(boolean allowComments) {
        this.allowComments = allowComments;
    }

    public boolean isSignatureMandatory() {
        return signatureMandatory;
    }

    public void setSignatureMandatory(boolean signatureMandatory) {
        this.signatureMandatory = signatureMandatory;
    }

    public boolean isEseal() {
        return eseal;
    }

    public void setEseal(boolean eseal) {
        this.eseal = eseal;
    }

    public boolean isHasDelegation() {
        return hasDelegation;
    }

    public void setHasDelegation(boolean hasDelegation) {
        this.hasDelegation = hasDelegation;
    }

    public String getDelegationId() {
        return delegationId;
    }

    public void setDelegationId(String delegationId) {
        this.delegationId = delegationId;
    }

    public String getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(String referredBy) {
        this.referredBy = referredBy;
    }

    public String getReferredTo() {
        return referredTo;
    }

    public void setReferredTo(String referredTo) {
        this.referredTo = referredTo;
    }

    public String getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(String signedBy) {
        this.signedBy = signedBy;
    }

    public String getOrgUID() {
        return orgUID;
    }

    public void setOrgUID(String orgUID) {
        this.orgUID = orgUID;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public String toString() {
        return "Receps{" +
                "index='" + index + '\'' +
                ", order=" + order +
                ", email='" + email + '\'' +
                ", suid='" + suid + '\'' +
                ", name='" + name + '\'' +
                ", alternateSignatories='" + alternateSignatories + '\'' +
                ", alternateSignatoriesList=" + alternateSignatoriesList +
                ", allowComments=" + allowComments +
                ", signatureMandatory=" + signatureMandatory +
                ", eseal=" + eseal +
                ", hasDelegation=" + hasDelegation +
                ", delegationId='" + delegationId + '\'' +
                ", referredBy='" + referredBy + '\'' +
                ", referredTo='" + referredTo + '\'' +
                ", signedBy='" + signedBy + '\'' +
                ", orgUID='" + orgUID + '\'' +
                ", orgName='" + orgName + '\'' +
                '}';
    }
}
