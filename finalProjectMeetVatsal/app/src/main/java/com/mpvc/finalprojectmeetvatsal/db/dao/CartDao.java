package com.mpvc.finalprojectmeetvatsal.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;


import com.mpvc.finalprojectmeetvatsal.db.entity.Cart;
import com.mpvc.finalprojectmeetvatsal.models.CartUserAndProducts;

import java.util.List;

@Dao
public interface CartDao {

    //@Query("SELECT c.cart_id,p.title,c.productID ,p.shortdesc,p.rating,p.price,p.productImage,c.userID,c.isCheckedOut,c.quantity FROM Cart c LEFT JOIN Product p on p.product_id=c.productID LEFT JOIN User u on u.user_id=c.userID")
    @Query("SELECT * FROM cart where isCheckedOut=0 and userID=:userID")
    LiveData<List<Cart>> getCart(int userID);

    @Query("SELECT * FROM cart")
    List<Cart> getCarts();

    @Query("SELECT * FROM cart where productID=:productID and isCheckedOut=0 and userID=:userID LIMIT 1")
    LiveData<Cart> getProductFromCartByProductID(int productID,int userID);

//    @Transaction
//    @Query("SELECT * FROM Cart")
//    public LiveData<List<CartUserAndProducts>> getCartWithRefrencedTables();

    @Insert
    void insert(Cart... cart);

    @Update
    void update(Cart... cart);

    @Delete
    void delete(Cart... cart);

    @Query("UPDATE Cart set isCheckedOut=1 where userID=:userID and isCheckedOut=0")
    void checkOutCart(int userID);
}
