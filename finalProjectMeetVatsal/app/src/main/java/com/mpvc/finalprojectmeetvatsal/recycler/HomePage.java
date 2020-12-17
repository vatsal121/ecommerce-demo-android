package com.mpvc.finalprojectmeetvatsal.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.mpvc.finalprojectmeetvatsal.MainActivity;
import com.mpvc.finalprojectmeetvatsal.R;
import com.mpvc.finalprojectmeetvatsal.adapter.ProductAdapter;
import com.mpvc.finalprojectmeetvatsal.db.AppDatabase;
import com.mpvc.finalprojectmeetvatsal.db.dao.CartDao;
import com.mpvc.finalprojectmeetvatsal.db.dao.ProductDao;
import com.mpvc.finalprojectmeetvatsal.db.entity.Cart;
import com.mpvc.finalprojectmeetvatsal.db.entity.Product;
import com.mpvc.finalprojectmeetvatsal.helpers.HelperMethods;
import com.mpvc.finalprojectmeetvatsal.interfaces.ButtonListenerInterface;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements ButtonListenerInterface {
    List<Product> productList;
    RecyclerView recyclerView;
    ProductAdapter adapter;
    private AppDatabase appDatabase;
    private ProductDao productDao;

    private Product product;

    private CartDao cartDao;
    private final String SHARED_PREFERENCE_FILE="mv-commerce";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        recyclerView = (RecyclerView) findViewById(R.id.homePage_listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        appDatabase=AppDatabase.getDatabaseInstance(this);
        productDao = appDatabase.productDao();
        cartDao= appDatabase.cartDao();

        productList= new ArrayList<>();
        insertProductsIfNotExists();

       //creating recyclerview adapter
       adapter = new ProductAdapter(this, productList,this);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }

    private  void getAllProductsCallBack(List<Product> products){
        if(products!=null && products.size()<=0){
           productList= HelperMethods.getAllStaticProducts();
            AppDatabase.databaseWriteExecutor.execute(()->{
                for (Product item:productList) {
                    productDao.insert(item);
                }
            });
        }else{
            productList=products;
        }

        adapter.setProductList(productList);

    }
    private void insertProductsIfNotExists(){
        productDao.getProduct().observe(this,this::getAllProductsCallBack);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar,menu);
        menu.findItem(R.id.menu_btnCart).setOnMenuItemClickListener(item ->{
            Intent i= new Intent(getApplicationContext(), CartPage.class);
            startActivity(i);
            return false;
        });

        menu.findItem(R.id.menu_btnLogOut).setOnMenuItemClickListener(item ->{
            SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE).edit();
            editor.remove("UserName");
            editor.remove("UserID");
            editor.clear();
            editor.apply();

            Intent i= new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
            return false;
        });
        return true;
    }

    public void getCartProductIfExists(Cart foundCartObject,int userID){
        if(foundCartObject!=null){
            Cart newCart=foundCartObject;
            newCart.setQuantity(newCart.getQuantity()+1);
            AppDatabase.databaseWriteExecutor.execute(()->{
                cartDao.update(newCart);
            });
            HelperMethods.showToastMessage(this,"Successfully updated cart.",Toast.LENGTH_SHORT);
        }
        else{
            Cart cart=new Cart(product.getProductID(),userID,false,1,product);
            AppDatabase.databaseWriteExecutor.execute(()-> {
                        cartDao.insert(cart);
                    });
            HelperMethods.showToastMessage(this,"Successfully added product to cart.",Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onAnyBtnClick(View v, int position) {
        product=productList.get(position);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE);
        int userId=sharedPreferences.getInt("UserID",0);

        LiveData<Cart> cartLiveData =cartDao.getProductFromCartByProductID(product.getProductID(),userId);
        cartLiveData.observe(this, new Observer<Cart>() {
            @Override
            public void onChanged(Cart cart) {
                getCartProductIfExists(cart,userId);
                cartLiveData.removeObservers(HomePage.this);    //Needed to give same Observer see Line 154
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE);
        int userId=sharedPreferences.getInt("UserID",0);
        if(userId==0){
            Intent i= new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}