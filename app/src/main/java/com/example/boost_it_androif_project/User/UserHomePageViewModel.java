package com.example.boost_it_androif_project.User;

import androidx.lifecycle.ViewModel;

import com.example.boost_it_androif_project.model.Model;
import com.example.boost_it_androif_project.model.ModelFireBase;
import com.example.boost_it_androif_project.model.User_Account;
import com.google.firebase.auth.FirebaseAuth;

public class UserHomePageViewModel extends ViewModel {

    private FirebaseAuth mAuth;

    public UserHomePageViewModel(){
        mAuth = FirebaseAuth.getInstance();
    }

    public FirebaseAuth getmAuth() { return mAuth; }
}