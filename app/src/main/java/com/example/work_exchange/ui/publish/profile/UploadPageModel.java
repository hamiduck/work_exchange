package com.example.work_exchange.ui.publish.profile;

public class UploadPageModel {

    String id,email,Title,HomestayAddress,People,Months,Period,Remark,Area,Type,Time,Bonus,Serving,uri1;

    public UploadPageModel(String id, String email, String title,String homestayAddress, String people, String months, String period, String remark, String area, String type, String time, String bonus, String serving, String uri1) {
        this.id = id;
        this.email = email;
        this. HomestayAddress = homestayAddress;
        this. Title=title;
        this. People = people;
        this. Months = months;
        this. Period = period;
        this. Remark = remark;
        this.  Area = area;
        this.  Type = type;
        this.  Time = time;
        this.  Bonus = bonus;
        this.  Serving = serving;
        this.uri1 = uri1;
    }

    public UploadPageModel(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getHomestayAddress() {
        return HomestayAddress;
    }

    public void setHomestayAddress(String homestayAddress) {
        HomestayAddress = homestayAddress;
    }

    public String getPeople() {
        return People;
    }

    public void setPeople(String people) {
        People = people;
    }

    public String getMonths() {
        return Months;
    }

    public void setMonths(String months) {
        Months = months;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getBonus() {
        return Bonus;
    }

    public void setBonus(String bonus) {
        Bonus = bonus;
    }

    public String getServing() {
        return Serving;
    }

    public void setServing(String serving) {
        Serving = serving;
    }

    public String getUri1() {
        return uri1;
    }

    public void setUri1(String uri1) {
        this.uri1 = uri1;
    }
}
