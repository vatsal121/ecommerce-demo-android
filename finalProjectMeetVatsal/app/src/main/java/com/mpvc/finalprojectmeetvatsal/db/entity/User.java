package com.mpvc.finalprojectmeetvatsal.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="user_id")
    private int UserID;

    @ColumnInfo(name="userName")
    private String UserName;

    @ColumnInfo(name="password")
    private String Password;

    public User(@NonNull String UserName, @NonNull String Password){
        this.UserName = UserName;
        this.Password = Password;
    }

    public String getUserName() {
        return UserName;
    }
    public String getPassword() {
        return Password;
    }

    public void setUserName(String username){
        this.UserName = username;
    }

    public void setPassword(String password){
        this.Password = password;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }
}
