package com.example.boost_it_androif_project.User;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boost_it_androif_project.R;
import com.example.boost_it_androif_project.model.Model;
import com.example.boost_it_androif_project.model.User_Account;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

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
        Button confirmRegistration = view.findViewById(R.id.user_account_info_edit_confirmBttn);


        confirmRegistration.setOnClickListener(v -> {

            TextView eml = (TextView) view.findViewById(R.id.user_account_info_edit_email);
            TextView name = (TextView) view.findViewById(R.id.user_account_info_edit_name);
            TextView lastName = (TextView) view.findViewById(R.id.user_account_info_edit_lastName);
            TextView pickPssword = (TextView) view.findViewById(R.id.user_account_info_edit_password);
            TextView confirmPsswrd = (TextView) view.findViewById(R.id.user_registration_page_Confirm_Password_Edit_Text);
            TextView phoneNumber = (TextView) view.findViewById(R.id.user_account_info_edit_phoneNumber);

            if (!eml.getText().toString().equals("") && pickPssword.getText().toString().length() >= 6) {
                Model.instance.getUserByEmail(eml.getText().toString(), user -> {
                    userCheck = user;

                    if (userCheck != null) {
                        Toast toast = Toast.makeText(getActivity(), "User Already Exists", Toast.LENGTH_LONG);
                        toast.show();
                    } else {

                        String email = eml.getText().toString();

                        String firstName = name.getText().toString();
                        String lstname = lastName.getText().toString();
                        String passWord = pickPssword.getText().toString();
                        String confPsswrd = confirmPsswrd.getText().toString();
                        String phnNum = phoneNumber.getText().toString();


                        if (!email.equals("") && !firstName.equals("") && !lstname.equals("") && !passWord.equals("") && !confPsswrd.equals("")
                                && !phnNum.equals("") && passWord.equals(confPsswrd)) {

                            User_Account user_account = new User_Account(email, firstName, lstname, passWord, phnNum);

                            Toast toast = Toast.makeText(getActivity(), "Added Account", Toast.LENGTH_LONG);
                            toast.show();

                            mViewModel.getmAuth().createUserWithEmailAndPassword(email, passWord);

                            Model.instance.addUser(user_account, () -> Navigation.findNavController(v).navigateUp());
                        } else {
                            Toast toast = Toast.makeText(getActivity(), "PassWords don't match or missing fields", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                });
            }else {
                Toast toast = Toast.makeText(getActivity(), "missing fields or passWord length is smaller than 6", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(UserRegistrationViewModel.class);
    }

}