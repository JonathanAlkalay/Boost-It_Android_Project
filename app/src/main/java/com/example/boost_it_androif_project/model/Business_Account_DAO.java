package com.example.boost_it_androif_project.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Business_Account_DAO {

    @Query("select * from Business_Account")
    List<Business_Account> getAll();

    @Query("select * from Business_Account where email = :email")
    Business_Account findByEmail(String email);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Business_Account account);

    @Delete
    void delete(Business_Account acc);
}
