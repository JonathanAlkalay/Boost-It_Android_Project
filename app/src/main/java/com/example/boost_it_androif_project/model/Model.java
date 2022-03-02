package com.example.boost_it_androif_project.model;

import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.boost_it_androif_project.MainActivity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Model {
    public static final Model instance = new Model();

    Executor threadPool = Executors.newFixedThreadPool(1);
    ModelFireBase fireBase = new ModelFireBase();

    private Model(){}


    public interface AddUserListener{
        void onComplete();
    }
    public void addUser(User_Account user, AddUserListener listener){
        fireBase.addUser(user, () -> {
            listener.onComplete();
        });
    }

    public interface GetUserByEmailListener{
        void onComplete(User_Account user_account);
    }
    public User_Account getUserByEmail(String email, GetUserByEmailListener listener) {
        fireBase.getUserByEmail(email,listener);
        return null;
    }

    public interface AddBusinessListener{
        void onComplete();
    }
    public void addBusiness(Business_Account business_account, AddBusinessListener listener){
        fireBase.addBusiness(business_account, ()->{
            listener.onComplete();
        });
    }

    public interface getBusinessByEmailListener{
        void onComplete(Business_Account business_account);
    }
    public Business_Account getBusinessByEmail(String email, getBusinessByEmailListener listener){
        fireBase.getBusinessByEmail(email, listener);
        return null;
    }

    public interface AddPostListener{
        void onComplete();
    }
    public void addPost(post post, AddPostListener listener){
        fireBase.addPost(post, ()->{
            listener.onComplete();
        });
    }

    public interface getPostByIdListener{
        void onComplete(post post);
    }
    public post getPostById(String id, getPostByIdListener listener){
        fireBase.getPostById(id, listener);
        return null;
    }

    MutableLiveData<List<post>> allPosts = new MutableLiveData<List<post>>();
    public LiveData<List<post>> getAllPosts(){
        if (allPosts.getValue() == null)
            refreshAllPosts();

        return allPosts;
    }

    public void refreshAllPosts() {

//        fireBase.getAllPosts(){
//
//        }
    }
}
