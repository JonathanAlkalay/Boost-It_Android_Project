package com.example.boost_it_androif_project;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.boost_it_androif_project.model.Business_Account;
import com.example.boost_it_androif_project.model.Model;
import com.google.firebase.auth.FirebaseUser;

public class welcome_screen extends Fragment {

    private WelcomeScreenViewModel mViewModel;
    private Button userBttn, businessBttn;
    private TextView selectOne;

    public static welcome_screen newInstance() {
        return new welcome_screen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.welcome_screen, container, false);

        userBttn = view.findViewById(R.id.welcome_screen_userAccount_bttn);
        businessBttn = view.findViewById(R.id.welcome_screen_BusinessAccount_bttn);
        selectOne = view.findViewById(R.id.welcome_screen_selectOne_textView);

        FirebaseUser currentUser = mViewModel.getmAuth().getCurrentUser();
        if(currentUser != null){

            Model.instance.getBusinessByEmail(currentUser.getEmail(), business_account -> {

                if (business_account == null ) {
                    Navigation.findNavController(view).navigate(welcome_screenDirections.actionWelcomeScreenToUserHomePage(currentUser.getEmail()));
                }else {

                    Model.instance.getUserByEmail(currentUser.getEmail(),acc->{
                        if (acc == null)
                        Navigation.findNavController(view).navigate(welcome_screenDirections.actionWelcomeScreenToBusinessHomePage(currentUser.getEmail()));
                    });
                }
            });

        }else {
            userBttn.setVisibility(View.VISIBLE);
            businessBttn.setVisibility(View.VISIBLE);
            selectOne.setVisibility(View.VISIBLE);
        }

        userBttn.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(welcome_screenDirections.actionWelcomeScreenToUserSignIn2()));

        businessBttn.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(welcome_screenDirections.actionWelcomeScreenToBusinessSignIn()));

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(WelcomeScreenViewModel.class);
    }
}