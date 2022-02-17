package com.example.boost_it_androif_project.model;

import java.util.ArrayList;
import java.util.List;

public class Business_Account extends Account{

    private String aboutMe;
    private String address;
    private List<post> activePosts;
    private List<post> historyPosts;

    public Business_Account() {
        super();
        this.aboutMe = null;
        this.address = null;
        this.activePosts = new ArrayList<post>();
        this.historyPosts = new ArrayList<post>();
    }

    public Business_Account(String firstName, String lastName, String phoneNumber, String email, Boolean loggedIn, String aboutMe,
                            String address, List<post> activePosts, List<post> historyPosts) {
        super(firstName, lastName, phoneNumber, email, loggedIn);
        this.aboutMe = aboutMe;
        this.address = address;
        this.activePosts = activePosts;
        this.historyPosts = historyPosts;
    }

    public List<post> getActivePosts() {
        return activePosts;
    }

    public void setActivePosts(List<post> activePosts) {
        this.activePosts = activePosts;
    }

    public List<post> getHistoryPosts() {
        return historyPosts;
    }

    public void setHistoryPosts(List<post> historyPosts) {
        this.historyPosts = historyPosts;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
