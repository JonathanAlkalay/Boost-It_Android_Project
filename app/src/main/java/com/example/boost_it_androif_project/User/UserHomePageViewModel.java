package com.example.boost_it_androif_project.User;

import androidx.lifecycle.ViewModel;

import com.example.boost_it_androif_project.model.User_Account;

public class UserHomePageViewModel extends ViewModel {

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public User_Account getUser() { return user; }

    public void setUser(User_Account user) { this.user = user; }

    private String email;
    private User_Account user;

    public UserHomePageViewModel(){}

}