package com.example.boost_it_androif_project.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;

import java.lang.reflect.Field;
import java.security.PrivateKey;
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

    private String businessEmail;
    private String title;
    private String description;
    private String price;
    private String times;
    private String image;
    private Boolean deleted = false;
    private Long upDateDate = new Long(0);


    public post(){};

    @Ignore
    public post(String title, String description, String price, String times, String image, String businessEmail) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.times = times;
        this.image = image;
        this.businessEmail = businessEmail;
        this.key = title+description+price+times+businessEmail;
        this.deleted = false;
    }


    public String generateKey(String businessEmail){ return title+description+price+times+businessEmail; }

    @NonNull
    public String getKey() { return key; }

    public void setKey(@NonNull String key) { this.key = key; }

    public Boolean getDeleted() { return deleted; }

    public void setDeleted(Boolean deleted) { this.deleted = deleted; }

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

    public Long getUpDateDate() { return upDateDate; }

    public void setUpDateDate(Long upDateDate) { this.upDateDate = upDateDate; }

    public String getBusinessEmail() { return businessEmail; }

    public void setBusinessEmail(String businessEmail) { this.businessEmail = businessEmail; }

    public static Map<String, Object> toJson(post post) {

        Map<String, Object> postJson = new HashMap<>();

        postJson.put("BusinessEmail", post.businessEmail);
        postJson.put("title", post.title);
        postJson.put("description", post.description);
        postJson.put("price", post.price);
        postJson.put("times", post.times);
        postJson.put("image", post.image);
        postJson.put("key", post.key);
        postJson.put("updateDate", FieldValue.serverTimestamp());
        postJson.put("deleted",post.deleted);

        return postJson;
    }

    public static post postfromJson(Map<String, Object> json){

        if (json == null)
            return null;

        String businessEmail = (String)json.get("BusinessEmail");
        String title = (String) json.get("title");
        String description = (String) json.get("description");
        String price = (String) json.get("price");
        String times = (String) json.get("times");
        String image = (String) json.get("image");
        String key = (String) json.get("key");
        Boolean deleted = (Boolean)json.get("deleted");
        Timestamp ts = (Timestamp)json.get("updateDate");
        Long updateDate = ts.getSeconds();

        post post = new post(title,description,price,times,image,businessEmail);

        post.upDateDate = updateDate;
        post.key = key;
        post.deleted = deleted;

        return post;
    }
}
