package com.example.boost_it_androif_project.User;

import androidx.lifecycle.ViewModel;

import com.example.boost_it_androif_project.model.User_Account;

public class UserAccountInfoEditViewModel extends ViewModel {

    public User_Account getUser() { return user; }

    public void setUser(User_Account account) {
        user = account;
    }

    private User_Account user = null;}