package com.example.boost_it_androif_project.Business;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.boost_it_androif_project.R;

public class business_registration_page extends Fragment {

    private BusinessRegistrationPageViewModel mViewModel;

    public static business_registration_page newInstance() {
        return new business_registration_page();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.business_registration_page, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BusinessRegistrationPageViewModel.class);
        // TODO: Use the ViewModel
    }

}