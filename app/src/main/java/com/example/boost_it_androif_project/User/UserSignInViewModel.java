package com.example.boost_it_androif_project.User;

import android.widget.TextView;

import androidx.lifecycle.ViewModel;
import com.example.boost_it_androif_project.R;
import com.google.firebase.auth.FirebaseAuth;

public class UserSignInViewModel extends ViewModel {


    private FirebaseAuth mAuth;

    public UserSignInViewModel(){
        mAuth = FirebaseAuth.getInstance();
    }

    public FirebaseAuth getmAuth() { return mAuth; }

}