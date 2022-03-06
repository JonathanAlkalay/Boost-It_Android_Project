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
import com.example.boost_it_androif_project.model.Business_Account;
import com.example.boost_it_androif_project.model.Model;

public class business_account_info_editScreen extends Fragment {

    private BusinessAccountInfoEditScreenViewModel mViewModel;
    View view;

    public static business_account_info_editScreen newInstance() {
        return new business_account_info_editScreen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.view =  inflater.inflate(R.layout.business_account_info_edit_screen_fragment, container, false);



        TextView name = view.findViewById(R.id.business_account_info_editScreen_name_text);
        TextView lastName = view.findViewById(R.id.business_account_info_editScreen_lastName_text);
        TextView companyName = view.findViewById(R.id.business_account_info_editScreen_companyName_text);
        TextView phoneNum = view.findViewById(R.id.business_account_info_editScreen_phoneNumber_text);
        TextView address = view.findViewById(R.id.business_account_info_editScreen_address_text);
        TextView description = view.findViewById(R.id.business_account_info_editScreen_description_text);

        name.setText(business_account_info_editScreenArgs.fromBundle(getArguments()).getName());
        lastName.setText(business_account_info_editScreenArgs.fromBundle(getArguments()).getLastName());
        companyName.setText(business_account_info_editScreenArgs.fromBundle(getArguments()).getCompanyName());
        phoneNum.setText(business_account_info_editScreenArgs.fromBundle(getArguments()).getPhoneNum());
        address.setText(business_account_info_editScreenArgs.fromBundle(getArguments()).getAddress());

        String aboutMe = business_account_info_editScreenArgs.fromBundle(getArguments()).getAboutMe();
        if (aboutMe == null){
            description.setText("enter business description here");
        }else{
            description.setText(aboutMe);
        }



        Button confirm = view.findViewById(R.id.business_account_info_editScreen_editButton);
        confirm.setOnClickListener(v -> {

            String firstName = name.getText().toString();
            String lstname = lastName.getText().toString();
            String compName = companyName.getText().toString();
            String phnNum = phoneNum.getText().toString();
            String addrs = address.getText().toString();
            String descr = description.getText().toString();


            if ( !firstName.equals("") && !lstname.equals("") &&!phnNum.equals("")&&!compName.equals("")
                    &&!addrs.equals("")&&!descr.equals("") ) {

                Toast toast = Toast.makeText(getActivity(), "Updated Account", Toast.LENGTH_LONG);
                toast.show();

                Business_Account business_account = new Business_Account();

                business_account.setFirstName(firstName);
                business_account.setLastName(lstname);
                business_account.setCompanyName(compName);
                business_account.setPassWord(business_account_info_editScreenArgs.fromBundle(getArguments()).getPassWord());
                business_account.setPhoneNumber(phnNum);
                business_account.setAddress(addrs);
                business_account.setEmail(business_account_info_editScreenArgs.fromBundle(getArguments()).getEmail());
                business_account.setAboutMe(descr);

                Model.instance.addBusiness(business_account, () -> Navigation.findNavController(view).popBackStack());
            }else {
                Toast toast = Toast.makeText(getActivity(), "missing fields", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(BusinessAccountInfoEditScreenViewModel.class);
    }
}