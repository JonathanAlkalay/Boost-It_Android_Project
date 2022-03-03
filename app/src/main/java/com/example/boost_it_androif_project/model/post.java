package com.example.boost_it_androif_project.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;

import java.lang.reflect.Field;
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


    private Long upDateDate = new Long(0);


    public post(){};

    @Ignore
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

    public Long getUpdateDate() { return this.upDateDate; }

    public Long getUpDateDate() { return upDateDate; }

    public void setUpDateDate(Long upDateDate) { this.upDateDate = upDateDate; }

    public Map<String, Object> toJson(post post) {

        Map<String, Object> postJson = new HashMap<>();

        postJson.put("BusinessAccount", post.account);
        postJson.put("title", post.title);
        postJson.put("description", post.description);
        postJson.put("price", post.price);
        postJson.put("times", post.times);
        postJson.put("image", post.image);
        postJson.put("key", post.key);
        postJson.put("updateDate", FieldValue.serverTimestamp());

        return postJson;
    }

    public static post postfromJson(Map<String, Object> json){

        if (json == null)
            return null;

        Business_Account business_account = BusinessFromHash((HashMap<String,Object>)json.get("BusinessAccount"));
        String title = (String) json.get("title");
        String description = (String) json.get("description");
        String price = (String) json.get("price");
        String times = (String) json.get("times");
        String image = (String) json.get("image");
        String key = (String) json.get("key");
        Timestamp ts = (Timestamp)json.get("updateDate");
        Long updateDate = ts.getSeconds();

        post post = new post(title,description,price,times,image,business_account);

        post.upDateDate = updateDate;
        post.key = key;

        return post;
    }

    private static Business_Account BusinessFromHash(Map<String,Object> map){


        String email = (String) map.get("email");
        String companyName = (String) map.get("companyName");
        String aboutMe = (String) map.get("aboutMe");
        String address = (String) map.get("address");
        String firstName = (String) map.get("firstName");
        String lastName = (String) map.get("lastName");
        String passWord = (String) map.get("passWord");
        String phoneNumber = (String) map.get("phoneNumber");
        Boolean loggedIn = (Boolean) map.get("loggedIn");
        List<post> activePosts = (List<post>) map.get("activePosts");
        List<post> historyPosts = (List<post>) map.get("historyPosts");

        Business_Account business_account = new Business_Account( companyName,aboutMe,address,email,
                firstName,lastName,passWord,phoneNumber);
        business_account.setLoggedIn(loggedIn);
        business_account.setActivePosts(activePosts);
        business_account.setHistoryPosts(historyPosts);

        return business_account;
    }
}
