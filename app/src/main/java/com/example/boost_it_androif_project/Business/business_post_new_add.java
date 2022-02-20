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

public class business_post_new_add extends Fragment {

    private BusinessPostNewAddViewModel mViewModel;

    public static business_post_new_add newInstance() {
        return new business_post_new_add();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.business_post_new_add, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BusinessPostNewAddViewModel.class);
        // TODO: Use the ViewModel
    }

}