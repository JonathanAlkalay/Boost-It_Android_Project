package com.example.boost_it_androif_project.User;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class UserRegistrationViewModel extends ViewModel {


    private FirebaseAuth mAuth;

    public UserRegistrationViewModel(){
        mAuth = FirebaseAuth.getInstance();
    }

    public FirebaseAuth getmAuth() { return mAuth; }}