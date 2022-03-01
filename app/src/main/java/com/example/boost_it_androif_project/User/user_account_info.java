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
import com.example.boost_it_androif_project.model.Model;
import com.example.boost_it_androif_project.model.User_Account;

public class user_account_info extends Fragment {

    private UserAccountInfoViewModel mViewModel;
    View view;

    public static user_account_info newInstance() {
        return new user_account_info();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.view =  inflater.inflate(R.layout.user_account_info, container, false);

        if (mViewModel.getUser() == null){
            Model.instance.getUserByEmail(user_account_infoArgs.fromBundle(getArguments()).getUserEmail(), user_account -> {

                mViewModel.setUser(user_account);
                setScreen();
            });
        }else {
            setScreen();
        }


        Button bttn = view.findViewById(R.id.user_account_info_edit_bttn);
        bttn.setOnClickListener(v -> {
            String email, name, lastNanme, passWord, number;
            email = user_account_infoArgs.fromBundle(getArguments()).getUserEmail();
            name = mViewModel.getUser().getFirstName();
            lastNanme = mViewModel.getUser().getLastName();
            passWord = mViewModel.getUser().getPassWord();
            number = mViewModel.getUser().getPhoneNumber();

            Navigation.findNavController(v).navigate(user_account_infoDirections.actionUserAccountInfoToUserAccountInfoEdit(
                    email,name,lastNanme,number,passWord));
        });

        return view;
    }




    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(UserAccountInfoViewModel.class);
    }

    private void setScreen(){
        User_Account user_account = mViewModel.getUser();


        TextView name = view.findViewById(R.id.user_account_info_name_textView);
        TextView lastName = view.findViewById(R.id.user_account_info_lastName_textView);
        TextView phoneNum = view.findViewById(R.id.user_account_info_phoneNumber_TextView);
        TextView email = view.findViewById(R.id.user_account_info_email_textView);
        TextView passWord = view.findViewById(R.id.user_account_info_password_textView);

        name.setText(user_account.getFirstName());
        lastName.setText(user_account.getLastName());
        phoneNum.setText(user_account.getPhoneNumber());
        email.setText(user_account.getEmail());
        passWord.setText(user_account.getPassWord());

    }
}