package com.example.work_exchange.ui.personal.profile;

public class ProfileModel {

    private String textProfile,textHint;
    private int imageEnd;


    public ProfileModel(String textProfile, String textHint, int imageEnd) {
        this.textProfile = textProfile;
        this.textHint = textHint;
        this.imageEnd = imageEnd;
    }

    public String getTextProfile() {
        return textProfile;
    }

    public void setTextProfile(String textProfile) {
        this.textProfile = textProfile;
    }

    public String getTextHint() {
        return textHint;
    }

    public void setTextHint(String textHint) {
        this.textHint = textHint;
    }

    public int getImageEnd() {
        return imageEnd;
    }

    public void setImageEnd(int imageEnd) {
        this.imageEnd = imageEnd;
    }
}
