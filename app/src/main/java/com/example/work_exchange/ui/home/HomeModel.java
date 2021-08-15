package com.example.work_exchange.ui.home;

public class HomeModel {

    String id;
     String Title;
     String Type;
     String People;
     String uri1;


    public HomeModel(){

    }
    public HomeModel(String id,String Title,String Type,String People,String uri1){

        this.id=id;
        this.Title=Title;
        this.Type=Type;
        this.People=People;
        this.uri1=uri1;
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
