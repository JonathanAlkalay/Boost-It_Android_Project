package com.example.boost_it_androif_project.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.boost_it_androif_project.MainActivity;
import com.example.boost_it_androif_project.MyApplication;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Model {
    public static final Model instance = new Model();

    Executor threadPool = Executors.newFixedThreadPool(1);
    ModelFireBase fireBase = new ModelFireBase();

    public enum allPostListLoadingState{loading, loaded}
    MutableLiveData<allPostListLoadingState> postsIsLoaded = new MutableLiveData<allPostListLoadingState>();
    public LiveData<allPostListLoadingState> getPostsIsLoaded(){ return postsIsLoaded;}

    private Model(){
        postsIsLoaded.setValue(allPostListLoadingState.loaded);
    }


    public interface saveImageListener{
        void onComplete(String url);
    }
    public void saveImage(Bitmap imageBit, String key, saveImageListener listener){
        fireBase.saveImage(imageBit,key,listener);
    }

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

    public void UpdateBusiness(String busEmail, String updateField, Object updateValue){
        fireBase.updateBusiness(busEmail, updateField, updateValue);
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
            refreshAllPosts();
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
        postsIsLoaded.setValue(allPostListLoadingState.loading);

        long lastUpdateDate = MyApplication.getContext().getSharedPreferences("TAG", Context.MODE_PRIVATE)
                .getLong("PostsLastUpdateDate",0);

        fireBase.getAllPosts(lastUpdateDate, list -> {
            threadPool.execute(()->{
                Long lud = new Long(0);

                for (post pst: list) {
                    AppLocalDB.db.post_dao().insert(pst);
                    if (lud < pst.getUpDateDate())
                        lud = pst.getUpDateDate();
                }
                MyApplication.getContext()
                        .getSharedPreferences("TAG",Context.MODE_PRIVATE)
                        .edit().putLong("PostsLastUpdateDate",lud).commit();

                List<post> pstLst = AppLocalDB.db.post_dao().getAll();
                allPosts.postValue(pstLst);
                postsIsLoaded.postValue(allPostListLoadingState.loaded);
            });
        });
    }
}
