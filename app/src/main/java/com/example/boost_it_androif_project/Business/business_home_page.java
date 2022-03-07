package com.example.boost_it_androif_project.Business;

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

import com.example.boost_it_androif_project.R;

public class business_home_page extends Fragment {

    private BusinessHomePageViewModel mViewModel;

    public static business_home_page newInstance() {
        return new business_home_page();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.business_home_page, container, false);

        Button info = view.findViewById(R.id.business_home_page_Business_Info_button2);
        info.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(business_home_pageDirections.actionBusinessHomePageToBusinessAccountInfo
                    (business_home_pageArgs.fromBundle(getArguments()).getBusinessAccountEmail(),true));
        });


        Button logOut = view.findViewById(R.id.business_home_page_Log_Out_button);
        logOut.setOnClickListener(v->{
            mViewModel.getmAuth().signOut();
            Navigation.findNavController(v).popBackStack();
        });

        Button activePosts = view.findViewById(R.id.business_home_page_Active_Ads_button);
        activePosts.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(business_home_pageDirections.actionBusinessHomePageToBusinessActivePosts
                    (business_home_pageArgs.fromBundle(getArguments()).getBusinessAccountEmail()));
        });

        Button post = view.findViewById(R.id.business_home_page_Post_Share_button);
        post.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(business_home_pageDirections.actionBusinessHomePageToBusinessPostNewAdd
                    (business_home_pageArgs.fromBundle(getArguments()).getBusinessAccountEmail()));
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(BusinessHomePageViewModel.class);
    }
}