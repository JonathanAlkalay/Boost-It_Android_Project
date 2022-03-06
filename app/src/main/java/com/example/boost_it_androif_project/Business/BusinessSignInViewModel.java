package com.example.boost_it_androif_project.Business;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class BusinessSignInViewModel extends ViewModel {

    private FirebaseAuth mAuth;

    public BusinessSignInViewModel(){
        mAuth = FirebaseAuth.getInstance();
    }

    public FirebaseAuth getmAuth() { return mAuth; }

}