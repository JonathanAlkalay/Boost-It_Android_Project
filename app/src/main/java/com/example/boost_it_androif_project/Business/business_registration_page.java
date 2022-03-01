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
import android.widget.TextView;
import android.widget.Toast;

import com.example.boost_it_androif_project.R;
import com.example.boost_it_androif_project.User.UserRegistrationViewModel;
import com.example.boost_it_androif_project.model.Business_Account;
import com.example.boost_it_androif_project.model.Model;
import com.example.boost_it_androif_project.model.User_Account;

import java.util.ArrayList;

public class business_registration_page extends Fragment {

    private BusinessRegistrationPageViewModel mViewModel;
    Business_Account businessCheck = null;

    public static business_registration_page newInstance() {
        return new business_registration_page();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.business_registration_page, container, false);
        Button confirmRegistrationBttn = view.findViewById(R.id.business_registration_page_confirm_bttn);

        confirmRegistrationBttn.setOnClickListener(v -> {

            TextView eml = (TextView) view.findViewById(R.id.business_registration_page_email_textEdit);
            TextView name = (TextView) view.findViewById(R.id.business_registration_page_AccountFirstName_textEdit);
            TextView lastName = (TextView) view.findViewById(R.id.business_registration_page_AccountLastName_textEdit);
            TextView companyName = (TextView) view.findViewById(R.id.business_registration_page_companyName_textEdit);
            TextView address = (TextView) view.findViewById(R.id.business_registration_page_address_textEdit);
            TextView pickPssword = (TextView) view.findViewById(R.id.business_registration_page_PickPassword_textEdit);
            TextView confirmPsswrd = (TextView) view.findViewById(R.id.business_registration_page_confirmPassword_textEdit);
            TextView phoneNumber = (TextView) view.findViewById(R.id.business_registration_page_phoneNumber_textEdit);

            Model.instance.getBusinessByEmail(eml.getText().toString(), bsnessAccnt -> {
                businessCheck = bsnessAccnt;

                if (businessCheck != null) {
                    Toast toast = Toast.makeText(getActivity(), "User Already Exists", Toast.LENGTH_LONG);
                    toast.show();
                }else {

                    String email = eml.getText().toString();

                    String firstName = name.getText().toString();
                    String lstname = lastName.getText().toString();
                    String compName = companyName.getText().toString();
                    String addrs = address.getText().toString();
                    String passWord = pickPssword.getText().toString();
                    String confPass = confirmPsswrd.getText().toString();
                    String phnNum = phoneNumber.getText().toString();

                    if (email!=null&&firstName!=null&&lstname!=null&&compName!=null&&addrs!=null&&passWord!=null&&
                            confPass!=null&&phnNum!=null&& passWord.equals(confPass)) {

                        Business_Account business_account1 = new Business_Account(compName,null, addrs, email, firstName, lstname, passWord,phnNum);

                        Toast toast = Toast.makeText(getActivity(), "Added Account", Toast.LENGTH_LONG);
                        toast.show();

                        Model.instance.addBusiness(business_account1, () -> Navigation.findNavController(v).navigateUp());
                    }else {
                        Toast toast = Toast.makeText(getActivity(), "PassWords don't match or missing fields", Toast.LENGTH_LONG);
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
        mViewModel = new ViewModelProvider(this).get(BusinessRegistrationPageViewModel.class);
    }
}