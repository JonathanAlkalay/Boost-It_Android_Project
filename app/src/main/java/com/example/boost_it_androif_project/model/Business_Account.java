package com.example.boost_it_androif_project.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Business_Account{

    public static final String collectionName = "businessAccounts";

    @PrimaryKey
    @NonNull
    private String email;

    private String aboutMe;
    private String address;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Boolean loggedIn;
    private List<post> activePosts;
    private List<post> historyPosts;

    public Business_Account(String aboutMe, String address, String email, String firstName, String lastName,
                            String phoneNumber) {
        this.aboutMe = aboutMe;
        this.address = address;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.activePosts = new ArrayList<>();
        this.historyPosts = new ArrayList<>();
        this.loggedIn = false;
    }

    public Business_Account(){
        this.aboutMe = null;
        this.address = null;
        this.email = null;
        this.firstName = null;
        this.lastName = null;
        this.phoneNumber = null;
        this.loggedIn = false;
        this.activePosts = new ArrayList<>();
        this.historyPosts = new ArrayList<>();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
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

    public Map<String, Object> toJson(Business_Account business_account) {

        Map<String, Object> businessJson = new HashMap<>();

        businessJson.put("email", business_account.email);
        businessJson.put("aboutMe", business_account.aboutMe);
        businessJson.put("address", business_account.address);
        businessJson.put("firstName", business_account.firstName);
        businessJson.put("lastName", business_account.lastName);
        businessJson.put("phoneNumber", business_account.phoneNumber);
        businessJson.put("loggedIn", business_account.loggedIn);
        businessJson.put("activePosts", business_account.activePosts);
        businessJson.put("historyPosts", business_account.historyPosts);

        return businessJson;
    }

    public Business_Account BusinessesfromJson(Map<String, Object> json){

        String email = (String) json.get("email");
        String aboutMe = (String) json.get("aboutMe");
        String address = (String) json.get("address");
        String firstName = (String) json.get("firstName");
        String lastName = (String) json.get("lastName");
        String phoneNumber = (String) json.get("phoneNumber");
        Boolean loggedIn = (Boolean) json.get("loggedIn");
        List<post> activePosts = (List<post>) json.get("activePosts");
        List<post> historyPosts = (List<post>) json.get("historyPosts");



        Business_Account business_account = new Business_Account(aboutMe,address,email,firstName,
                lastName,phoneNumber);

        business_account.loggedIn = loggedIn;
        business_account.activePosts = activePosts;
        business_account.historyPosts = historyPosts;

        return business_account;
    }
}
