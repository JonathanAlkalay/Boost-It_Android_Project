package com.example.boost_it_androif_project.Business;

import androidx.lifecycle.ViewModel;

import com.example.boost_it_androif_project.model.Business_Account;

public class BusinessPostNewAddViewModel extends ViewModel {

    private Business_Account account = null;

    public Business_Account getAccount() { return account; }

    public void setAccount(Business_Account account) { this.account = account; }
}