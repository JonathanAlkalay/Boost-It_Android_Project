package com.example.boost_it_androif_project.Business;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.boost_it_androif_project.R;
import com.example.boost_it_androif_project.User.user_available_ads;
import com.example.boost_it_androif_project.User.user_available_adsDirections;
import com.example.boost_it_androif_project.model.Model;
import com.example.boost_it_androif_project.model.post;
import com.squareup.picasso.Picasso;

public class business_active_posts extends Fragment {

    private BusinessActivePostsViewModel mViewModel;
    View view;
    MyAdapter adapter;
    SwipeRefreshLayout swipeRefresh;


    //TODO only show ads that were posted by current business

    public static business_active_posts newInstance() {
        return new business_active_posts();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.view =  inflater.inflate(R.layout.business_active_posts, container, false);

        swipeRefresh = view.findViewById(R.id.businessActivePosts_swipeToRefresh);
        swipeRefresh.setOnRefreshListener(() -> Model.instance.refreshAllPosts());


        RecyclerView list = view.findViewById(R.id.business_activePosts_recyclerView);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyAdapter();
        list.setAdapter(adapter);

        adapter.setOnItemClickListener((v, position) -> {
            String postId = mViewModel.getData().getValue().get(position).getKey();
            Navigation.findNavController(v).navigate(business_active_postsDirections.
                    actionBusinessActivePostsToPostDetails(postId,true));
        });

        mViewModel.getData().observe(getViewLifecycleOwner(), list1 -> refresh());
        swipeRefresh.setRefreshing(Model.instance.getPostsIsLoaded().getValue() == Model.allPostListLoadingState.loading);
        Model.instance.getPostsIsLoaded().observe(getViewLifecycleOwner(), postLoadingState -> {
            if (postLoadingState == Model.allPostListLoadingState.loading){
                swipeRefresh.setRefreshing(true);
            }else{
                swipeRefresh.setRefreshing(false);
            }
        });

        return this.view;
    }
    private void refresh() {
        adapter.notifyDataSetChanged();
        swipeRefresh.setRefreshing(false);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView hours;
        TextView price;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            name = itemView.findViewById(R.id.ads_list_row_title);
            hours = itemView.findViewById(R.id.ads_list_row_hours);
            price = itemView.findViewById(R.id.ads_list_row_discountPrice);
            image = itemView.findViewById(R.id.ads_list_row_image);

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                listener.onItemClick(v,pos);
            });
        }

        void bind(post post){
            name.setText(post.getTitle());
            hours.setText(post.getTimes());
            price.setText(post.getPrice());

            image.setImageResource(R.drawable.ic_launcher_foreground);
            Picasso.get()
                    .load(post.getImage())
                    .into(image);
        }
    }

    interface OnItemClickListener{
        void onItemClick(View v,int position);
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        OnItemClickListener listener;
        public void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.ads_list_row,parent,false);
            MyViewHolder holder = new MyViewHolder(view,listener);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            post post = mViewModel.getData().getValue().get(position);
            holder.bind(post);
        }

        @Override
        public int getItemCount() {
            if(mViewModel.getData().getValue() == null){
                return 0;
            }
            return mViewModel.getData().getValue().size();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(BusinessActivePostsViewModel.class);
    }
}