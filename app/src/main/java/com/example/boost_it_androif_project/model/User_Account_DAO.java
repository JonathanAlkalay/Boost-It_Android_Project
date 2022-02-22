package com.example.boost_it_androif_project.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface User_Account_DAO {

    @Query("select * from User_Account")
    List<User_Account> getAll();

    @Query("select * from User_Account where email = :email")
    User_Account findByEmail(String email);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User_Account account);

    @Delete
    void delete(User_Account acc);
}
