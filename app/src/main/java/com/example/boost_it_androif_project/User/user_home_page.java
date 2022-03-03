package com.example.boost_it_androif_project.User;

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

import com.example.boost_it_androif_project.R;
import com.example.boost_it_androif_project.model.User_Account;

public class user_home_page extends Fragment {

    private UserHomePageViewModel mViewModel;

    public static user_home_page newInstance() {
        return new user_home_page();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.user_home_page, container, false);

        Button personal = view.findViewById(R.id.user_home_page_Personal_zone_button);
        personal.setOnClickListener(v -> {

            Navigation.findNavController(v).navigate(user_home_pageDirections.actionUserHomePageToUserAccountInfo(
                    user_home_pageArgs.fromBundle(getArguments()).getUserAccountEmail()));
        });

        Button logOut = view.findViewById(R.id.user_home_page_Log_Out_button);
        logOut.setOnClickListener(v->{
            Navigation.findNavController(v).popBackStack();
        });

        Button allAds = view.findViewById(R.id.user_home_page_browseActiveAds_button);
        allAds.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(user_home_pageDirections.actionUserHomePageToUserAvailableAds());
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(UserHomePageViewModel.class);

    }
}