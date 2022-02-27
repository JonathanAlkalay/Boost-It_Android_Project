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
import android.widget.Toast;

import com.example.boost_it_androif_project.MyApplication;
import com.example.boost_it_androif_project.R;
import com.example.boost_it_androif_project.model.Model;
import com.example.boost_it_androif_project.model.User_Account;

public class user_sign_in extends Fragment {

    private UserSignInViewModel mViewModel;
    User_Account userCheck = null;

    public static user_sign_in newInstance() {
        return new user_sign_in();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.user_sign_in, container, false);

        Button createAccountBttn = view.findViewById(R.id.user_sign_in_noAccount_bttn);
        Button signInEnterButtn = view.findViewById(R.id.user_sign_in_enter_bttn);

        TextView email = (TextView) view.findViewById(R.id.user_sign_in_UserName_textEdit);
        TextView passWord = (TextView) view.findViewById(R.id.user_sign_in_Password_textEdit);




        signInEnterButtn.setOnClickListener(v -> {

            String mail = email.getText().toString();
            String pssWord = passWord.getText().toString();

            Model.instance.getUserByEmail(mail, user_account -> {
                userCheck = user_account;

                if (userCheck == null) {
                    Toast toast = Toast.makeText(getActivity(), "User Does Not Exist", Toast.LENGTH_LONG);
                    toast.show();
                }else {
                    if (userCheck.getPassWord().equals(pssWord)){
                        email.setText("");
                        passWord.setText("");
                        Navigation.findNavController(v).navigate(user_sign_inDirections.actionUserSignIn2ToUserHomePage(mail));

                    }else {
                        Toast toast = Toast.makeText(getActivity(), "Incorrect Password", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });
        });

        createAccountBttn.setOnClickListener(v -> {

            Navigation.findNavController(v).navigate(user_sign_inDirections.actionUserSignIn2ToUserRegistration());
        });

        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(UserSignInViewModel.class);
    }
}