package com.example.boost_it_androif_project.Business;

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

public class business_sign_in extends Fragment {

    private BusinessSignInViewModel mViewModel;

    public static business_sign_in newInstance() {
        return new business_sign_in();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.business_sign_in, container, false);

        Button createAccountBttn = view.findViewById(R.id.business_sign_in_noAccount_bttn);
        createAccountBttn.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(business_sign_inDirections.actionBusinessSignInToBusinessRegistrationPage()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BusinessSignInViewModel.class);
        // TODO: Use the ViewModel
    }

}