package com.example.boost_it_androif_project.Business;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.boost_it_androif_project.model.Model;
import com.example.boost_it_androif_project.model.post;

import java.util.List;

public class BusinessActivePostsViewModel extends ViewModel {

    LiveData<List<post>> data;

    public void setData(String mail){ data = Model.instance.getAllBusinessPosts(mail); }
    public LiveData<List<post>> getData() { return data; }
}