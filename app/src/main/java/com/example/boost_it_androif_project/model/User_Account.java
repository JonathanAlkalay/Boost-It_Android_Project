package com.example.boost_it_androif_project.model;

import java.util.ArrayList;
import java.util.List;

public class User_Account extends Account{

    List<post> savedPosts;

    public User_Account() {
        super();
        this.savedPosts = new ArrayList<post>();
    }

    public User_Account(String firstName, String lastName, String phoneNumber, String email, Boolean loggedIn, List<post> savedPosts) {
        super(firstName, lastName, phoneNumber, email);
        this.savedPosts = savedPosts;
    }

    public List<post> getSavedPosts() {
        return savedPosts;
    }

    public void setSavedPosts(List<post> savedPosts) {
        this.savedPosts = savedPosts;
    }
}
