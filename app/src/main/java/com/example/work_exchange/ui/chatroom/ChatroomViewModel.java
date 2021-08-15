package com.example.work_exchange.ui.chatroom;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChatroomViewModel extends ViewModel {

    private MutableLiveData<String> mtext;

    public ChatroomViewModel(){
        mtext=new MutableLiveData<>();
        mtext.setValue("這是聊天室");
    }

    public LiveData<String> gettext(){
        return mtext;
    }
}
