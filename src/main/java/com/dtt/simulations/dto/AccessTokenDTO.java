package com.dtt.simulations.dto;




public class AccessTokenDTO {
    private String email;
    private String name;
    private String suid;
    private String key;
    private String OrganizationName;
    private String OrganizationId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public String getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(String organizationId) {
        OrganizationId = organizationId;
    }

    @Override
    public String toString() {
        return "AccessTokenDTO{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", suid='" + suid + '\'' +
                ", key='" + key + '\'' +
                ", OrganizationName='" + OrganizationName + '\'' +
                ", OrganizationId='" + OrganizationId + '\'' +
                '}';
    }
}


