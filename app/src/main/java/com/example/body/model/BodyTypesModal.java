package com.example.body.model;

public class BodyTypesModal {
    int id;
    String bodycolor, bodyheight,bodyweight;
    boolean isGoodOrBad;

    public BodyTypesModal(String bodycolor, String bodyheight,String bodyweight, int isGoodOrBad, int id) {
        this.id = id + 1;
        this.bodycolor = bodycolor;
        this.bodyheight = bodyheight;
        this.bodyweight=bodyweight;
        this.isGoodOrBad = isGoodOrBad == 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getbodycolor() {
        return bodycolor;
    }

    public void setbodycolor(String bodycolor) {
        this.bodycolor = bodycolor;
    }

    public String getBodyheight() {
        return bodyheight;
    }

    public void setBodyheight(String bodyheight) {
        this.bodyheight = bodyheight;
    }
    public String getBodyweight() {
        return bodyweight;
    }

    public void setBodyweight(String bodyweight) {
        this.bodyweight = bodyweight;
    }

    public boolean getDisability() {
        return isGoodOrBad;
    }

    public void setIsGoodOrBad(boolean isGoodOrBad) {
        this.isGoodOrBad = isGoodOrBad;
    }
}
