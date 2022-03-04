package com.example.boost_it_androif_project.model;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.boost_it_androif_project.MyApplication;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;


@Database(entities = {User_Account.class, Business_Account.class, post.class}, version = 10)
@TypeConverters({Converters.class})
abstract class AppLocalDbRepository extends RoomDatabase{
    public abstract User_Account_DAO user_account_dao();
    public abstract Business_Account_DAO business_account_dao();
    public abstract post_DAO post_dao();
}

public class AppLocalDB {

    static public AppLocalDbRepository db =
        Room.databaseBuilder(MyApplication.getContext(),
                AppLocalDbRepository.class, "dbFileName.db")
            .fallbackToDestructiveMigration().build();
}

