package com.example.boost_it_androif_project;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeScreenViewModel extends ViewModel {


    private FirebaseAuth mAuth;

    public WelcomeScreenViewModel(){
        mAuth = FirebaseAuth.getInstance();
    }

    public FirebaseAuth getmAuth() { return mAuth; }}