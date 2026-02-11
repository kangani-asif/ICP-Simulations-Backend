package com.dtt.simulations.POA.dto;

import java.util.Map;

public class Model {

    private String TempId; //empty

    private  String action;

    private DocDetails docDetails; //

    private String docData;  // empty

    private String fileName; //document name

    private Map<String,Coordinates> signCords;

    private Map<String,Coordinates> qrCords;

    private Map<String,EsealCordinates> esealCords;

    private String watermarkText;

    private String actoken;

    private boolean disableOrder;

    private boolean multisign;

    private boolean qrCodeRequired;

    private boolean isMobile;

    private String docSerialNo; // empty

    private  String entityName; //empty

    private boolean allowToAssignSomeone; //false

    private String signatureEnvironment; //empty


    public String getTempId() {
        return TempId;
    }

    public void setTempId(String tempId) {
        TempId = tempId;
    }

    public DocDetails getDocDetails() {
        return docDetails;
    }

    public void setDocDetails(DocDetails docDetails) {
        this.docDetails = docDetails;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDocData() {
        return docData;
    }

    public void setDocData(String docData) {
        this.docData = docData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getWatermarkText() {
        return watermarkText;
    }

    public void setWatermarkText(String watermarkText) {
        this.watermarkText = watermarkText;
    }

    public String getActoken() {
        return actoken;
    }

    public void setActoken(String actoken) {
        this.actoken = actoken;
    }

    public boolean isDisableOrder() {
        return disableOrder;
    }

    public void setDisableOrder(boolean disableOrder) {
        this.disableOrder = disableOrder;
    }

    public boolean isMultisign() {
        return multisign;
    }

    public void setMultisign(boolean multisign) {
        this.multisign = multisign;
    }

    public boolean isQrCodeRequired() {
        return qrCodeRequired;
    }

    public void setQrCodeRequired(boolean qrCodeRequired) {
        this.qrCodeRequired = qrCodeRequired;
    }

    public boolean isMobile() {
        return isMobile;
    }

    public void setMobile(boolean mobile) {
        isMobile = mobile;
    }

    public String getDocSerialNo() {
        return docSerialNo;
    }

    public void setDocSerialNo(String docSerialNo) {
        this.docSerialNo = docSerialNo;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public boolean isAllowToAssignSomeone() {
        return allowToAssignSomeone;
    }

    public void setAllowToAssignSomeone(boolean allowToAssignSomeone) {
        this.allowToAssignSomeone = allowToAssignSomeone;
    }

    public String getSignatureEnvironment() {
        return signatureEnvironment;
    }

    public void setSignatureEnvironment(String signatureEnvironment) {
        this.signatureEnvironment = signatureEnvironment;
    }



//    public void setSigncords(Map<String, Coordinates> signcords) {
//        this.signcords = signcords;
//    }

    public Map<String, Coordinates> getQrCords() {
        return qrCords;
    }

    public void setQrCords(Map<String, Coordinates> qrCords) {
        this.qrCords = qrCords;
    }

//    public Map<String, EsealCordinates> getEsealcords() {
//        return esealcords;
//    }
//
//    public void setEsealcords(Map<String, EsealCordinates> esealcords) {
//        this.esealcords = esealcords;
//    }


    public Map<String, Coordinates> getSignCords() {
        return signCords;
    }

    public void setSignCords(Map<String, Coordinates> signCords) {
        this.signCords = signCords;
    }

    public Map<String, EsealCordinates> getEsealCords() {
        return esealCords;
    }

    public void setEsealCords(Map<String, EsealCordinates> esealCords) {
        this.esealCords = esealCords;
    }
}
