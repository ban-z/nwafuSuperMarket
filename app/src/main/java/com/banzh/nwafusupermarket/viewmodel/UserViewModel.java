package com.banzh.nwafusupermarket.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.banzh.nwafusupermarket.data.User;

public class UserViewModel extends ViewModel {

    private MutableLiveData<User> user;

    public MutableLiveData<User> getUser() {
        if (user == null){
            user = new MutableLiveData<>();
        }
        return user;
    }
}
