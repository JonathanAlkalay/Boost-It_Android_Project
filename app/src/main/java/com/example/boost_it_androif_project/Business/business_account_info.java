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

import com.example.boost_it_androif_project.R;
import com.example.boost_it_androif_project.model.Business_Account;
import com.example.boost_it_androif_project.model.Model;

public class business_account_info extends Fragment {

    private BusinessAccountInfoViewModel mViewModel;
    View view;

    public static business_account_info newInstance() {
        return new business_account_info();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.view =  inflater.inflate(R.layout.business_account_info, container, false);


        if (mViewModel.getUser() == null){
            Model.instance.getBusinessByEmail(business_account_infoArgs.fromBundle(getArguments()).getBusinessAccountEmail(), business_account -> {

                mViewModel.setUser(business_account);
                setScreen();
            });
        }else {
            setScreen();
        }


        Button bttn = view.findViewById(R.id.business_account_info_editButton);
        if (!business_account_infoArgs.fromBundle(getArguments()).getIsBusiness()){
            bttn.setVisibility(View.INVISIBLE);
        }
        bttn.setOnClickListener(v -> {
            String email, name, lastNanme,companyName,address, passWord, number, aboutMe;

            email = business_account_infoArgs.fromBundle(getArguments()).getBusinessAccountEmail();
            name = mViewModel.getUser().getFirstName();
            lastNanme = mViewModel.getUser().getLastName();
            passWord = mViewModel.getUser().getPassWord();
            number = mViewModel.getUser().getPhoneNumber();
            companyName = mViewModel.getUser().getCompanyName();
            address = mViewModel.getUser().getAddress();
            aboutMe = mViewModel.getUser().getAboutMe()==null?"empty": mViewModel.getUser().getAboutMe();

            Navigation.findNavController(v).navigate(business_account_infoDirections.actionBusinessAccountInfoToBusinessAccountInfoEditScreen
                    (name,lastNanme,companyName,number,address,email,passWord,aboutMe));

        });

        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(BusinessAccountInfoViewModel.class);
    }


    private void setScreen(){
        Business_Account account = mViewModel.getUser();
        TextView passWord = view.findViewById(R.id.business_account_info_password_text);

        if (business_account_infoArgs.fromBundle(getArguments()).getIsBusiness()){
            Button edit = view.findViewById(R.id.business_account_info_editButton);
            edit.setVisibility(View.VISIBLE);

            passWord.setVisibility(View.VISIBLE);

        }

        TextView name = view.findViewById(R.id.business_account_info_name_text);
        TextView lastName = view.findViewById(R.id.business_account_info_lastName_text);
        TextView companyName = view.findViewById(R.id.business_account_info_companyName_text);
        TextView phoneNum = view.findViewById(R.id.business_account_info_phoneNumber_text);
        TextView address = view.findViewById(R.id.business_account_info_address_text);
        TextView email = view.findViewById(R.id.business_account_info_email_text);
        TextView description = view.findViewById(R.id.business_account_info_description_text);

        name.setText(account.getFirstName());
        lastName.setText(account.getLastName());
        companyName.setText(account.getCompanyName());
        email.setText(account.getCompanyName());
        phoneNum.setText(account.getPhoneNumber());
        address.setText(account.getAddress());
        passWord.setText(account.getPassWord());

        if (account.getAboutMe() != null)
            description.setText(account.getAboutMe());
    }
}