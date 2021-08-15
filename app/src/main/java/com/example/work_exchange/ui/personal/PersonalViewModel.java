package com.example.work_exchange.ui.personal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PersonalViewModel extends ViewModel {

    private MutableLiveData<String> mtext;

    public  PersonalViewModel(){
        mtext=new MutableLiveData<>();
        mtext.setValue("這是個人資料");
    }

    public LiveData<String> gettext(){
        return mtext;
    }
}
