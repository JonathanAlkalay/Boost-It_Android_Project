package com.example.boost_it_androif_project;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.boost_it_androif_project.Business.BusinessAccountInfoViewModel;
import com.example.boost_it_androif_project.User.user_account_infoArgs;
import com.example.boost_it_androif_project.model.Business_Account;
import com.example.boost_it_androif_project.model.Model;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class post_details extends Fragment {

    private PostDetailsViewModel mViewModel;
    View view;

    public static post_details newInstance() {
        return new post_details();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.post_details, container, false);


        if (mViewModel.getPost() == null){
            Model.instance.getPostById(post_detailsArgs.fromBundle(getArguments()).getPostId(), post -> {

                mViewModel.setPost(post);
                setScreen();
            });
        }else {
            setScreen();
        }

        return view;
    }

    private void setScreen() {

        Button confirm = view.findViewById(R.id.post_details_confirmBttn);
        Button businessDETAILS = view.findViewById(R.id.post_details_business_button);
        TextView companyName = view.findViewById(R.id.post_details_businessName);
        TextView title = view.findViewById(R.id.post_details_title);
        TextView hours = view.findViewById(R.id.post_details_hours);
        TextView price = view.findViewById(R.id.post_details_price);
        TextView description = view.findViewById(R.id.post_details_description);
        ImageView image = view.findViewById(R.id.post_details_image);

        if (post_detailsArgs.fromBundle(getArguments()).getIsBusiness()){
            confirm.setVisibility(View.VISIBLE);
            businessDETAILS.setVisibility(View.INVISIBLE);

        }else {
            confirm.setVisibility(View.INVISIBLE);
            businessDETAILS.setVisibility(View.VISIBLE);
        }

        image.setImageResource(R.drawable.ic_launcher_foreground);
        Picasso.get()
                .load(mViewModel.getPost().getImage())
                .into(image);

        Model.instance.getBusinessByEmail(mViewModel.getPost().getBusinessEmail(), business_account
                -> companyName.setText(business_account.getCompanyName()));

        title.setText(mViewModel.getPost().getTitle());
        hours.setText(mViewModel.getPost().getTimes());
        price.setText(mViewModel.getPost().getPrice());
        description.setText(mViewModel.getPost().getDescription());

        confirm.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(post_detailsDirections.actionPostDetailsToPostDetailsEdit(mViewModel.getPost().getKey())));

        businessDETAILS.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(post_detailsDirections.actionPostDetailsToBusinessAccountInfo(mViewModel.getPost().getBusinessEmail(),false)));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(PostDetailsViewModel.class);
    }
}