package com.mpvc.finalprojectmeetvatsal.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mpvc.finalprojectmeetvatsal.db.dao.CartDao;
import com.mpvc.finalprojectmeetvatsal.db.dao.UserDao;
import com.mpvc.finalprojectmeetvatsal.db.dao.ProductDao;
import com.mpvc.finalprojectmeetvatsal.db.entity.Cart;
import com.mpvc.finalprojectmeetvatsal.db.entity.User;
import com.mpvc.finalprojectmeetvatsal.db.entity.Product;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Product.class, Cart.class},version = 17)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract ProductDao productDao();
    public abstract CartDao cartDao();

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //Enforce that is gonna read and write from the main thread.
    //AppDatabase is going to live inside this instance. <- Singleton design pattern
    private static volatile AppDatabase instance;

    //Singleton Design Pattern.
    public static AppDatabase getDatabaseInstance(final Context context){
        if (instance == null){  //Lazy Loading Design Pattern.
            synchronized (AppDatabase.class){
                if (instance == null){
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "mv-commerce") //Name of Database in SQL Database
                            .fallbackToDestructiveMigration() // Migration is database modified/changed
                            .build();
                }
            }
        }
        return instance;
    }
}
