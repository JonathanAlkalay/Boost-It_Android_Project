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
import com.example.boost_it_androif_project.model.post;

public class business_post_new_add extends Fragment {

    private BusinessPostNewAddViewModel mViewModel;
    View view;

    public static business_post_new_add newInstance() {
        return new business_post_new_add();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.business_post_new_add, container, false);

        if (mViewModel.getAccount() == null) {
            Model.instance.getBusinessByEmail(business_post_new_addArgs.fromBundle(getArguments()).getBusinessEmail(),
                    business_account -> {
                        mViewModel.setAccount(business_account);

                        TextView compName = view.findViewById(R.id.business_post_new_add_businessName);
                        compName.setText(mViewModel.getAccount().getCompanyName());
                        setScreen();
                    });
        } else {
            TextView compName = view.findViewById(R.id.business_post_new_add_businessName);
            compName.setText(mViewModel.getAccount().getCompanyName());
            setScreen();
        }
        return this.view;
    }

    @Override
    public void onAttach (@NonNull Context context){
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(BusinessPostNewAddViewModel.class);
    }

    private void setScreen() {

        Button confirm = view.findViewById(R.id.business_post_new_add_enter_button);
        confirm.setOnClickListener(v -> {

            TextView title = view.findViewById(R.id.business_post_new_add_title);
            TextView hours = view.findViewById(R.id.business_post_new_add_hours);
            TextView price = view.findViewById(R.id.business_post_new_add_price);
            TextView description = view.findViewById(R.id.business_post_new_add_description);


            String ttle = title.getText().toString();
            String hrs = hours.getText().toString();
            String prce = price.getText().toString();
            String dscrptn = description.getText().toString();

            if (ttle != null && hrs != null && prce != null && dscrptn != null) {

                post post = new post();

                post.setBusinessEmail(mViewModel.getAccount().getEmail());
                post.setTitle(ttle);
                post.setPrice(prce);
                post.setTimes(hrs);
                post.setDescription(dscrptn);
                post.setImage("no image");
                post.setKey(post.generateKey(mViewModel.getAccount().getEmail()));

                Toast toast = Toast.makeText(getActivity(), "Added Post", Toast.LENGTH_LONG);
                toast.show();

                //TODO change business account in post to email and add this new post to business active posts and historic
                mViewModel.getAccount().getActivePosts().add(post);
                mViewModel.getAccount().getHistoryPosts().add(post);

                Model.instance.UpdateBusiness(mViewModel.getAccount().getEmail(),"activePosts", mViewModel.getAccount().getActivePosts());
                Model.instance.UpdateBusiness(mViewModel.getAccount().getEmail(),"historyPosts", mViewModel.getAccount().getHistoryPosts());

                Model.instance.addPost(post, () -> Navigation.findNavController(view).navigateUp());
            } else {
                Toast toast = Toast.makeText(getActivity(), "missing fields", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}