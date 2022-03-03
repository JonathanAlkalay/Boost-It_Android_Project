package com.example.boost_it_androif_project;

import androidx.lifecycle.ViewModel;

import com.example.boost_it_androif_project.model.User_Account;
import com.example.boost_it_androif_project.model.post;

public class PostDetailsViewModel extends ViewModel {

    public post getPost() { return post; }

    public void setPost(post post1) { post = post1; }

    private post post = null;
}

