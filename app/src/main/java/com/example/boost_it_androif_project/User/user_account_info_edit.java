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

import com.example.boost_it_androif_project.R;
import com.example.boost_it_androif_project.model.Model;
import com.example.boost_it_androif_project.model.User_Account;

import java.util.ArrayList;

public class user_account_info_edit extends Fragment {

    private UserAccountInfoEditViewModel mViewModel;
    View view;
    public static user_account_info_edit newInstance() {
        return new user_account_info_edit();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.view =  inflater.inflate(R.layout.user_account_info_edit_fragment, container, false);

        TextView name = (TextView) view.findViewById(R.id.user_account_info_edit_name);
        TextView lastName = (TextView) view.findViewById(R.id.user_account_info_edit_lastName);
        TextView phoneNumber = (TextView) view.findViewById(R.id.user_account_info_edit_phoneNumber);

        name.setText(user_account_info_editArgs.fromBundle(getArguments()).getUserName());
        lastName.setText(user_account_info_editArgs.fromBundle(getArguments()).getUserLastName());
        phoneNumber.setText(user_account_info_editArgs.fromBundle(getArguments()).getUserPhoneNumber());

        Button bttn = view.findViewById(R.id.user_account_info_edit_confirmBttn);
        bttn.setOnClickListener(v -> {

            String firstName = name.getText().toString();
            String lstname = lastName.getText().toString();
            String phnNum = phoneNumber.getText().toString();


            if ( firstName!=null && lstname!=null &&phnNum!=null ) {

                Toast toast = Toast.makeText(getActivity(), "Updated Account", Toast.LENGTH_LONG);
                toast.show();

                User_Account user_account = new User_Account();

                user_account.setFirstName(firstName);
                user_account.setLastName(lstname);
                user_account.setPassWord(user_account_info_editArgs.fromBundle(getArguments()).getUserPassword());
                user_account.setPhoneNumber(phnNum);
                user_account.setEmail(user_account_info_editArgs.fromBundle(getArguments()).getUserEmail());


                Model.instance.addUser(user_account, () -> Navigation.findNavController(view).popBackStack());
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
        mViewModel = new ViewModelProvider(this).get(UserAccountInfoEditViewModel.class);
    }
}