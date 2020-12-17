package com.mpvc.finalprojectmeetvatsal.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.mpvc.finalprojectmeetvatsal.db.entity.Cart;

import java.util.List;

@Dao
public interface CartDao {

    @Query("SELECT * FROM cart where isCheckedOut=0 and userID=:userID")
    LiveData<List<Cart>> getCart(int userID);

    @Query("SELECT * FROM cart")
    List<Cart> getCarts();

    @Query("SELECT * FROM cart where productID=:productID and isCheckedOut=0 and userID=:userID LIMIT 1")
    LiveData<Cart> getProductFromCartByProductID(int productID,int userID);

    @Insert
    void insert(Cart... cart);

    @Update
    void update(Cart... cart);

    @Delete
    void delete(Cart... cart);

    @Query("UPDATE Cart set isCheckedOut=1 where userID=:userID and isCheckedOut=0")
    void checkOutCart(int userID);
}
