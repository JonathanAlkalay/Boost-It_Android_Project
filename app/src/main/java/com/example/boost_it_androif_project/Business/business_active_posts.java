package com.example.boost_it_androif_project.Business;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.boost_it_androif_project.R;
import com.example.boost_it_androif_project.model.post;

public class business_active_posts extends Fragment {

    private BusinessActivePostsViewModel mViewModel;
    View view;
//    MyAdapter adapter;
    SwipeRefreshLayout swipeRefresh;


    public static business_active_posts newInstance() {
        return new business_active_posts();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.view =  inflater.inflate(R.layout.business_active_posts, container, false);

        swipeRefresh = view.findViewById(R.id.businessActivePosts_swipeToRefresh);
//        swipeRefresh.setOnRefreshListener(() -> Model.instance.refreshStudentList());


        RecyclerView list = view.findViewById(R.id.business_activePosts_recyclerView);
        list.setHasFixedSize(true);

//        list.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapter = new MyAdapter();
//        list.setAdapter(adapter);
//
//        adapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//                String id = mViewModel.getPosts().
//            }
//        });


//        if (mViewModel.getPosts() == null){
//            Model.instance.getBusinessByEmail(business_active_postsArgs.fromBundle(getArguments()).getBusinessEmail(), business -> {
//
//                mViewModel.setPosts(business.getActivePosts());
//                setScreen();
//            });
//        }else {
//            setScreen();
//        }

        return this.view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(BusinessActivePostsViewModel.class);
    }


}