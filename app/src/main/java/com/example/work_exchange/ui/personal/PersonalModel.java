package com.example.work_exchange.ui.personal;

public class PersonalModel {

    private String textPersonal;
    private Integer imageHeader,imageEnd;

    public PersonalModel(String textPersonal, Integer imageHeader, Integer imageEnd) {
        this.textPersonal = textPersonal;
        this.imageHeader = imageHeader;
        this.imageEnd = imageEnd;
    }

    public String getTextPersonal() {
        return textPersonal;
    }

    public void setTextPersonal(String textPersonal) {
        this.textPersonal = textPersonal;
    }

    public Integer getImageHeader() {
        return imageHeader;
    }

    public void setImageHeader(Integer imageHeader) {
        this.imageHeader = imageHeader;
    }

    public Integer getImageEnd() {
        return imageEnd;
    }

    public void setImageEnd(Integer imageEnd) {
        this.imageEnd = imageEnd;
    }
}
