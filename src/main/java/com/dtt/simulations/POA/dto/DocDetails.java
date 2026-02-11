package com.dtt.simulations.POA.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class DocDetails {

    private String ownerName; //principal name

    private List<Receps> receps;

    private String tempname; //document name

    private String daysToComplete; // 2 or above

    public int signaturesRequiredCount; // 3

    public String autoReminders; // empty

    public String remindEvery; // empty

    public  String annotations; //empty

    public String noteToAll; //empty

    public String orgn_name; // empty

    public  String watermark; //empty

    public String expiredate;  //DateTime.now() + 365


    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public List<Receps> getReceps() {
        return receps;
    }

    public void setReceps(List<Receps> receps) {
        this.receps = receps;
    }

    public String getTempname() {
        return tempname;
    }

    public void setTempname(String tempname) {
        this.tempname = tempname;
    }

    public String getDaysToComplete() {
        return daysToComplete;
    }

    public void setDaysToComplete(String daysToComplete) {
        this.daysToComplete = daysToComplete;
    }

    public int getSignaturesRequiredCount() {
        return signaturesRequiredCount;
    }

    public void setSignaturesRequiredCount(int signaturesRequiredCount) {
        this.signaturesRequiredCount = signaturesRequiredCount;
    }

    public String getAutoReminders() {
        return autoReminders;
    }

    public void setAutoReminders(String autoReminders) {
        this.autoReminders = autoReminders;
    }

    public String getRemindEvery() {
        return remindEvery;
    }

    public void setRemindEvery(String remindEvery) {
        this.remindEvery = remindEvery;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }

    public String getNoteToAll() {
        return noteToAll;
    }

    public void setNoteToAll(String noteToAll) {
        this.noteToAll = noteToAll;
    }

    public String getOrgn_name() {
        return orgn_name;
    }

    public void setOrgn_name(String orgn_name) {
        this.orgn_name = orgn_name;
    }

    public String getWatermark() {
        return watermark;
    }

    public void setWatermark(String watermark) {
        this.watermark = watermark;
    }

    public String getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }

    @Override
    public String toString() {
        return "DocDetails{" +
                "ownerName='" + ownerName + '\'' +
                ", receps=" + receps +
                ", tempname='" + tempname + '\'' +
                ", daysToComplete='" + daysToComplete + '\'' +
                ", signaturesRequiredCount=" + signaturesRequiredCount +
                ", autoReminders='" + autoReminders + '\'' +
                ", remindEvery='" + remindEvery + '\'' +
                ", annotations='" + annotations + '\'' +
                ", noteToAll='" + noteToAll + '\'' +
                ", orgn_name='" + orgn_name + '\'' +
                ", watermark='" + watermark + '\'' +
                ", expiredate=" + expiredate +
                '}';
    }
}
