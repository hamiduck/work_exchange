package com.example.work_exchange.ui.chatroom;

public class UserListModel {

    private int userimageView;
    private String username;
    private String chatcontext;
    private String chattime;
    //new code
    private String chatline;



    UserListModel(int userimageView , String username, String chatcontext, String chattime, String chatline)
    {
        this.userimageView=userimageView;
        this.username=username;
        this.chatcontext=chatcontext;
        this.chattime=chattime;
        this.chatline=chatline;
    }

    public int getImageview() {
        return userimageView;
    }

    public String getTextview1() {
        return username;
    }

    public String getDivider()
    {
        return chatline;
    }


    public String getTextview2() {
        return chatcontext;
    }

    public String getTextview3() {
        return chattime;
    }


}