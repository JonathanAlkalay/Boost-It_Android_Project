package com.example.boost_it_androif_project.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class User_Account{

    public static final String collectionName = "usersAccounts";

    @PrimaryKey
    @NonNull
    private String email;

    private String firstName;
    private String lastName;
    private String passWord;
    private String phoneNumber;
    private Boolean loggedIn;
    private List<post> savedPosts;

    public User_Account(String email, String firstName, String lastName,String passWord, String phoneNumber,
                        List<post> savedPosts) {

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passWord = passWord;
        this.phoneNumber = phoneNumber;
        this.savedPosts = savedPosts;
        this.loggedIn = false;
        this.savedPosts = new ArrayList<>();
    }

    public User_Account() {
        this.email = null;
        this.firstName = null;
        this.lastName = null;
        this.passWord = null;
        this.phoneNumber = null;
        this.loggedIn = false;
        this.savedPosts = new ArrayList<>();
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

    public String getPassWord() { return passWord; }

    public void setPassWord(String passWord) { this.passWord = passWord; }

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

    public List<post> getSavedPosts() {
        return savedPosts;
    }

    public void setSavedPosts(List<post> savedPosts) {
        this.savedPosts = savedPosts;
    }

    public Map<String, Object> toJson(User_Account user) {

        Map<String, Object> userJson = new HashMap<>();

        userJson.put("email", user.email);
        userJson.put("firstName", user.firstName);
        userJson.put("lastName", user.lastName);
        userJson.put("passWord", user.passWord);
        userJson.put("phoneNumber", user.phoneNumber);
        userJson.put("loggedIn", user.loggedIn);
        userJson.put("savedPosts", user.savedPosts);

        return userJson;
    }

    public static User_Account UserfromJson(Map<String, Object> json){

        if(json == null)
            return null;


        String email = (String) json.get("email");
        String firstName = (String) json.get("firstName");
        String lastName = (String) json.get("lastName");
        String passWord = (String) json.get("passWord");
        String phoneNumber = (String) json.get("phoneNumber");
        Boolean loggedIn = (Boolean) json.get("loggedIn");
        List<post> savedPosts = (List<post>) json.get("savedPosts");

        User_Account user = new User_Account(email,firstName,lastName,passWord, phoneNumber,savedPosts);
        user.loggedIn = loggedIn;

        return user;
    }
}
