package com.mpvc.finalprojectmeetvatsal.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Product {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="product_id")
    private int ProductID;

    @ColumnInfo(name="title")
    private String Title;

    @ColumnInfo(name="shortdesc")
    private String ShortDesc;

    @ColumnInfo(name="rating")
    private double Rating;

    @ColumnInfo(name="price")
    private double Price;

    @ColumnInfo(name="productImage")
    private String ProductImage;

    public Product(int ProductID, String Title, String ShortDesc, double Rating, double Price, String ProductImage) {
        this.ProductID = ProductID;
        this.Title = Title;
        this.ShortDesc = ShortDesc;
        this.Rating = Rating;
        this.Price = Price;
        this.ProductImage = ProductImage;
    }

    @Ignore
    public Product(String Title, String ShortDesc, double Rating, double Price, String ProductImage) {
        this.Title = Title;
        this.ShortDesc = ShortDesc;
        this.Rating = Rating;
        this.Price = Price;
        this.ProductImage = ProductImage;
    }



    public int getProductID() {
        return ProductID;
    }

    public String getTitle() {
        return Title;
    }

    public String getShortDesc() {
        return ShortDesc;
    }

    public double getRating() {
        return Rating;
    }

    public double getPrice() {
        return Price;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductID(int productID){
        this.ProductID = productID;
    }
    public void setTitle(String title) {
        this.Title = title;
    }

    public void setProductImage(String productImage) {
        this.ProductImage = productImage;
    }

    public void setPrice(double price) {
        this.Price = price;
    }

    public void setRating(double rating) {
        this.Rating = rating;
    }

    public void setShortDesc(String shortDesc) {
        this.ShortDesc = shortDesc;
    }
}
