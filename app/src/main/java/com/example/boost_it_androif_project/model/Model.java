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
    Handler mainThread = HandlerCompat.createAsync(Looper.getMainLooper());
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

//    public List<User_Account> getAllUsers(){
//        return AppLocalDB.db.user_account_dao().getAll();
//    }
//
//
//    public User_Account getUserByEmail(String email){
//          return AppLocalDB.db.user_account_dao().findByEmail(email);
//    }
//
//    public void addUser(User_Account user){
//        AppLocalDB.db.user_account_dao().insert(user);
//    }
//
//    public void deleteUser(User_Account user){
//        AppLocalDB.db.user_account_dao().delete(user);
//    }
//
//    public List<Business_Account> getAllBusinesses(){
//        return AppLocalDB.db.business_account_dao().getAll();
//    }
//
//    public Business_Account getBusinessByEmail(String email){
//        return AppLocalDB.db.business_account_dao().findByEmail(email);
//    }
//
//    public void addBusiness(Business_Account business){
//        AppLocalDB.db.business_account_dao().insert(business);
//    }
//
//    public void deleteBusiness(Business_Account business){
//        AppLocalDB.db.business_account_dao().delete(business);
//    }
//
//    public List<post> getAllPosts(){
//        return AppLocalDB.db.post_dao().getAll();
//    }
//
//    public post getPostByBusiness(Business_Account business_account){
//        return AppLocalDB.db.post_dao().findByBusiness(business_account);
//    }
//
//    public void addPost(post post){
//        AppLocalDB.db.post_dao().insert(post);
//    }
//
//    public void deletePost(post post){
//        AppLocalDB.db.post_dao().delete(post);
//    }
}
