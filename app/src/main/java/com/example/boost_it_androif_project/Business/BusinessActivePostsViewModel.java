package com.example.boost_it_androif_project.Business;

import androidx.lifecycle.ViewModel;

import com.example.boost_it_androif_project.model.post;

import java.util.List;

public class BusinessActivePostsViewModel extends ViewModel {

    public List<post> getPosts() { return activePosts; }

    public void setPosts(List<post> posts) { this.activePosts = posts; }

    private List<post> activePosts = null;
}