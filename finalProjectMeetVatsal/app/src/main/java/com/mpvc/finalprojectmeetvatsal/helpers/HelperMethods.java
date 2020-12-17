package com.mpvc.finalprojectmeetvatsal.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.mpvc.finalprojectmeetvatsal.R;
import com.mpvc.finalprojectmeetvatsal.db.entity.Product;

import java.text.NumberFormat;
import java.util.ArrayList;

public class HelperMethods {
    public static  ArrayList<Product> getAllStaticProducts(){
        ArrayList<Product>  productList= new ArrayList<>();
        //adding some items to our list
        productList.add(
                new Product(
                        "Apple MacBook Pro Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 inch, Silver, 1.35 kg",
                        4.7,
                        1800,
                        "macbook_pro"));

        productList.add(
                new Product(
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 inch, Gray, 1.659 kg",
                        4.3,
                        1200,
                        "dell_inspiron"));

        productList.add(
                new Product(
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 inch, Silver, 1.35 kg",
                        4.5,
                        1300,
                        "microsoft_surface"));

        productList.add(
                new Product(
                        "Apple AirPods Pro In-Ear Noise Cancelling Truly Wireless Headphones - White",
                        "Model Number: MWP22AM/A, Web Code: 14307944",
                        4.7,
                        320,
                        "airpods_pro"));
        productList.add(
                new Product(
                        "Google Pixel 4a 128GB - Just Black - Unlocked",
                        "Model Number: GA02099-US, Web Code: 14878808",
                        4.6,
                        479,
                        "googlepixel4a"));

        productList.add(
                new Product(
                        "Samsung Galaxy Note20 Ultra 5G 128GB - Mystic Bronze",
                        "Model Number: SMN986WZNA, Web Code: SMN986WZNA",
                        4.7,
                        1557.99,
                        "samsung_galaxy_note20"));

        productList.add(
                new Product(
                        "Apple Watch Series 5 (GPS) 44mm Space Grey Aluminium Case with Black Sport Band",
                        "Open Box (10/10 condition)",
                        5.0,
                        418.70,
                        "iwatch_series_5"));

        return productList;
    }
    public static void showToastMessage(Context ctx, String message, int duration){
        Toast.makeText(ctx, message, duration).show();
    }

    public static int getImageID(String imageName) {
        try {
            return R.drawable.class.getField(imageName).getInt(null);
        } catch (NoSuchFieldException | IllegalAccessException  exception) {
            exception.printStackTrace();
        }
        return -1;
    }

    public static  String formatPrice(double price){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(price);
    }
//
//    public static void setDataInSharedPreference(Context ctx, SharedPreferences sharedPreferences){
//        SharedPreferences sharedPref = ctx.getSharedPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putInt(getString(R.string.saved_high_score_key), newHighScore);
//        editor.apply();
//    }
}
