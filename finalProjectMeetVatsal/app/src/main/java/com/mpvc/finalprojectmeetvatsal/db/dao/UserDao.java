package com.mpvc.finalprojectmeetvatsal.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mpvc.finalprojectmeetvatsal.db.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getUser();


    @Query("SELECT * FROM User WHERE userName=:userName")
    LiveData<User> getUserByName(String userName);

    @Query("SELECT * FROM User WHERE user_id=:userId")
    LiveData<User> getUserById(int userId);

    @Query("SELECT * FROM User WHERE userName=:userName and password=:password")
    LiveData<User> getUserByNameAndPassword(String userName, String password);

    @Insert
    void insert(User... user);
}
