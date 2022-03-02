package com.example.boost_it_androif_project.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class post {

    public static final String collectionName = "posts";


    @PrimaryKey
    @NonNull
    private String key;

    private Business_Account account;
    private String title;
    private String description;
    private String price;
    private String times;
    private String image;


    public post(){};

    public post(String title, String description, String price, String times, String image, Business_Account acc) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.times = times;
        this.image = image;
        this.account = acc;

        this.key = title+description+price+times+acc.getCompanyName();
    }




    public String generateKey(Business_Account acc){
        return title+description+price+times+acc.getCompanyName();
    }

    @NonNull
    public String getKey() { return key; }

    public void setKey(@NonNull String key) { this.key = key; }

    public Business_Account getAccount() { return account; }

    public void setAccount(Business_Account account) { this.account = account; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Map<String, Object> toJson(post post) {

        Map<String, Object> postJson = new HashMap<>();

        postJson.put("BusinessAccount", post.account);
        postJson.put("title", post.title);
        postJson.put("description", post.description);
        postJson.put("price", post.price);
        postJson.put("times", post.times);
        postJson.put("image", post.image);
        postJson.put("key", post.key);

        return postJson;
    }

    public static post postfromJson(Map<String, Object> json){

        if (json == null)
            return null;

        Business_Account BusinessAccount = (Business_Account) json.get("BusinessAccount");
        String title = (String) json.get("title");
        String description = (String) json.get("description");
        String price = (String) json.get("price");
        String times = (String) json.get("times");
        String image = (String) json.get("image");
        String key = (String) json.get("key");

        post post = new post(title,description,price,times,image,BusinessAccount);
        post.key = key;

        return post;
    }
}
