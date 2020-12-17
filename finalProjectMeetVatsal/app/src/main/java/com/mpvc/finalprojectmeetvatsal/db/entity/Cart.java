package com.mpvc.finalprojectmeetvatsal.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity
public class Cart {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="cart_id")
    private int CartID;

    @ForeignKey(entity = Product.class,
            parentColumns = "product_id",
            childColumns = "productID")
    @ColumnInfo(name="productID")
    private int ProductID;

    @ForeignKey(entity = User.class,
            parentColumns = "user_id",
            childColumns = "userID")
    @ColumnInfo(name="userID")
    private int UserID;

    @ColumnInfo(name="isCheckedOut")
    private boolean IsCheckedOut;

    // @Embedded private User user;
    @Embedded private Product product;

    @ColumnInfo(name="quantity")
    private int Quantity;

    public Cart(int ProductID, int UserID, boolean IsCheckedOut, int Quantity,/*User user,*/Product product) {
        this.ProductID = ProductID;
        this.UserID = UserID;
        this.setCheckedOut(IsCheckedOut);
        this.Quantity=Quantity;
       //  this.user=user;
        this.product=product;
    }

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int CartID) {
        this.CartID = CartID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public boolean getIsCheckedOut() {
        return isCheckedOut();
    }

    public void setIsCheckedOut(boolean checkedOut) {
        setCheckedOut(checkedOut);
    }

    public void setQuantity(int quantity){
        Quantity=quantity;
    }

    public int getQuantity(){
        return Quantity;
    }

    public boolean isCheckedOut() {
        return IsCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        IsCheckedOut = checkedOut;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
