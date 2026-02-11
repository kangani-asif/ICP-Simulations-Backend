package com.dtt.simulations.dto;


import java.io.Serializable;

public class VerifyImageDto implements Serializable{

    private String image1;

    private String image2;

    private Boolean livenesscheck;


    public Boolean getLivenesscheck() {
        return livenesscheck;
    }

    public void setLivenesscheck(Boolean livenesscheck) {
        this.livenesscheck = livenesscheck;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    @Override
    public String toString() {
        return "VerifyImageDto{" +
                "image1='" + image1 + '\'' +
                ", image2='" + image2 + '\'' +
                ", livenesscheck=" + livenesscheck +
                '}';
    }
}

