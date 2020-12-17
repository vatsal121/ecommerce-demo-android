package com.mpvc.finalprojectmeetvatsal.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.mpvc.finalprojectmeetvatsal.db.entity.Cart;
import com.mpvc.finalprojectmeetvatsal.db.entity.Product;
import com.mpvc.finalprojectmeetvatsal.db.entity.User;

public class CartUserAndProducts {

    @Embedded public User user;
    @Relation(
            parentColumn = "user_id",
            entityColumn = "userID"
    )
    @Embedded public Product product;
    @Relation(
            parentColumn = "product_id",
            entityColumn = "productID"
    )

    public Cart cart;
}
