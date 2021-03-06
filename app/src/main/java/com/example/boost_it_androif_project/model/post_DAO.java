package com.example.boost_it_androif_project.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface post_DAO {

    @Query("select * from post")
    List<post> getAll();

    @Query("select * from post where businessEmail = :email")
    List<post> getPostsFromBusiness(String email);

//    @Query("select * from post where key = :id")
//    List<post> findByBusinessEmail(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(post post);

    @Delete
    void delete(post post);

}
