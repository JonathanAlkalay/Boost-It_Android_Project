package com.example.boost_it_androif_project.Business;

import androidx.lifecycle.ViewModel;

import com.example.boost_it_androif_project.model.Business_Account;
import com.example.boost_it_androif_project.model.User_Account;

public class BusinessAccountInfoViewModel extends ViewModel {

    public Business_Account getUser() { return user; }

    public void setUser(Business_Account account) {
        user = account;
    }

    private Business_Account user = null;
}