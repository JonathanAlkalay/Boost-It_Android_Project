package com.example.boost_it_androif_project;

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

public class welcome_screen extends Fragment {

    private WelcomeScreenViewModel mViewModel;
    private Button userBttn, businessBttn;

    public static welcome_screen newInstance() {
        return new welcome_screen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.welcome_screen, container, false);
        userBttn = view.findViewById(R.id.welcome_screen_userAccount_bttn);
        businessBttn = view.findViewById(R.id.welcome_screen_BusinessAccount_bttn);

        userBttn.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(welcome_screenDirections.actionWelcomeScreenToUserSignIn2()));

        businessBttn.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(welcome_screenDirections.actionWelcomeScreenToBusinessSignIn()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WelcomeScreenViewModel.class);
        // TODO: Use the ViewModel
    }
}