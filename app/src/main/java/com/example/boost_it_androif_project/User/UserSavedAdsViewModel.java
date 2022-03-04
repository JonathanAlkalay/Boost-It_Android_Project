package com.example.boost_it_androif_project.User;

import androidx.lifecycle.ViewModel;

import com.example.boost_it_androif_project.model.Model;
import com.example.boost_it_androif_project.model.User_Account;
import com.example.boost_it_androif_project.model.post;

import java.util.List;

public class UserSavedAdsViewModel extends ViewModel {

    public List<post> getSavedPosts() { return savedPosts; }

    public void setSavedPosts(List<post> savedPosts) { this.savedPosts = savedPosts; }

    private List<post> savedPosts = null;

}