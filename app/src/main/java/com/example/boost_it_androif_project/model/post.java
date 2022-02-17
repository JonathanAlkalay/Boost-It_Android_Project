package com.example.boost_it_androif_project.model;

public class post {

    private String title;
    private String description;
    private String price;
    private String times;
    private String image;


    public post(){};

    public post(String title, String description, String price, String times, String image) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.times = times;
        this.image = image;
    }

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
}
