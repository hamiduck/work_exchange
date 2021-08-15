package com.example.work_exchange.ui.home.profile;

public class ProductShowcaseModel {

    String id;
    String Title;
    String Type;
    String People;
    String uri1;
    String email,HomestayAddress, Months,Period,Remark;
    String Area,Time,Bonus,Serving;


    public ProductShowcaseModel(){

    }
    public ProductShowcaseModel(String id, String Title, String Type, String People, String uri1,
                                String email,String HomestayAddress,String Months,String Period,String Remark,
                                String Area,String Time,String Bonus,String Serving){

        this.id=id;
        this.Title=Title;
        this.Type=Type;
        this.People=People;
        this.email=email;
        this.HomestayAddress=HomestayAddress;
        this.Months=Months;
        this.Period=Period;
        this.Remark=Remark;
        this.Area=Area;
        this.Time=Time;
        this.Bonus=Bonus;
        this.Serving=Serving;
        this.uri1=uri1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomestayAddress() {
        return HomestayAddress;
    }

    public void setHomestayAddress(String homestayAddress) {
        HomestayAddress = homestayAddress;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPeople() {
        return People;
    }

    public void setPeople(String people) {
        People = people;
    }

    public String getUri1() {
        return uri1;
    }

    public void setUri1(String uri1) {
        this.uri1 = uri1;
    }
}
