package com.example.boost_it_androif_project.User;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.boost_it_androif_project.R;

import java.util.NavigableMap;

public class user_sign_in extends Fragment {

    private UserSignInViewModel mViewModel;

    public static user_sign_in newInstance() {
        return new user_sign_in();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.user_sign_in, container, false);

        Button createAccountBttn = view.findViewById(R.id.user_sign_in_noAccount_bttn);
        createAccountBttn.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(user_sign_inDirections.actionUserSignIn2ToUserRegistration()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UserSignInViewModel.class);
        // TODO: Use the ViewModel
    }

}