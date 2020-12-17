package com.mpvc.finalprojectmeetvatsal.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mpvc.finalprojectmeetvatsal.db.entity.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM Product")
    LiveData<List<Product>> getProduct();

    @Insert
    void insert(Product... product);
}

