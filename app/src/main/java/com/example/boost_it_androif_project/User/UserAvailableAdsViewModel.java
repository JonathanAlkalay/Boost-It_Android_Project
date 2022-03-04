package com.example.boost_it_androif_project.User;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.boost_it_androif_project.model.Model;
import com.example.boost_it_androif_project.model.post;

import java.util.List;

public class UserAvailableAdsViewModel extends ViewModel {
    LiveData<List<post>> data;

    public UserAvailableAdsViewModel(){ data = Model.instance.getAllPosts();}
    public LiveData<List<post>> getData() { return data; }

}