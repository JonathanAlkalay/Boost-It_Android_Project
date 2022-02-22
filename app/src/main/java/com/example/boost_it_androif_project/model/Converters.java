package com.example.boost_it_androif_project.model;

import androidx.room.TypeConverter;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {
    @TypeConverter
    public static List<post> fromString(String value) {
        Type listType = new TypeToken<List<post>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String FromArrayList(List<post> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static List<User_Account> FromString(String value) {
        Type listType = new TypeToken<List<User_Account>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(List<User_Account> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static Business_Account Fromstring(String value) {

        return new Gson().fromJson(value,Business_Account.class);
    }

    @TypeConverter
    public static String fromArraylist(Business_Account acc) {
        return new Gson().toJson(acc);
    }
}
