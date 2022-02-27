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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boost_it_androif_project.R;
import com.example.boost_it_androif_project.model.Model;
import com.example.boost_it_androif_project.model.User_Account;
import com.example.boost_it_androif_project.model.post;

import java.util.ArrayList;

public class user_registration extends Fragment {

    private UserRegistrationViewModel mViewModel;
    User_Account userCheck = null;


    public static user_registration newInstance() {
        return new user_registration();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.user_registration_page, container, false);
        Button confirmRegistration = view.findViewById(R.id.user_registration_page_confirm_Bttn);


        confirmRegistration.setOnClickListener(v -> {

            TextView eml = (TextView) view.findViewById(R.id.user_registration_page_Email_Edit_Text);
            TextView name = (TextView) view.findViewById(R.id.user_registration_page_First_Name_Edit_Text);
            TextView lastName = (TextView) view.findViewById(R.id.user_registration_page_Last_Name_Edit_Text);
            TextView pickPssword = (TextView) view.findViewById(R.id.user_registration_page_Pick_Password_Edit_Text);
            TextView confirmPsswrd = (TextView) view.findViewById(R.id.user_registration_page_Confirm_Password_Edit_Text);
            TextView phoneNumber = (TextView) view.findViewById(R.id.user_registration_page_Phone_Number_Edit_Text);

            Model.instance.getUserByEmail(eml.getText().toString(), user -> {
                userCheck = user;

                if (userCheck != null) {
                    Toast toast = Toast.makeText(getActivity(), "User Already Exists", Toast.LENGTH_LONG);
                    toast.show();
                }else {

                    String email = eml.getText().toString();

                    String firstName = name.getText().toString();
                    String lstname = lastName.getText().toString();
                    String passWord = pickPssword.getText().toString();
                    String phnNum = phoneNumber.getText().toString();

                    if (passWord.equals(confirmPsswrd.getText().toString())) {
                        User_Account user_account = new User_Account(email, firstName, lstname, passWord, phnNum, new ArrayList<>());

                        Toast toast = Toast.makeText(getActivity(), "Added Account", Toast.LENGTH_LONG);
                        toast.show();

                        Model.instance.addUser(user_account, () -> Navigation.findNavController(v).navigateUp());
                    }else {
                        Toast toast = Toast.makeText(getActivity(), "PassWords don't match", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });
        });
        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(UserRegistrationViewModel.class);
    }

}