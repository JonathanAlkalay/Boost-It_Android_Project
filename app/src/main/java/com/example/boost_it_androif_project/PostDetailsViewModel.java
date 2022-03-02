package com.example.boost_it_androif_project;

import androidx.lifecycle.ViewModel;

import com.example.boost_it_androif_project.model.User_Account;
import com.example.boost_it_androif_project.model.post;

public class PostDetailsViewModel extends ViewModel {

    public post getPost() { return post; }

    public void setPost(post post) { post = post; }

    private post post = null;
}

