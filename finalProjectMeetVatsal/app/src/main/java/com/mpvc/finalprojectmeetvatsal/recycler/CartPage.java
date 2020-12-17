package com.mpvc.finalprojectmeetvatsal.recycler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mpvc.finalprojectmeetvatsal.MainActivity;
import com.mpvc.finalprojectmeetvatsal.R;
import com.mpvc.finalprojectmeetvatsal.adapter.CartAdapter;
import com.mpvc.finalprojectmeetvatsal.adapter.ProductAdapter;
import com.mpvc.finalprojectmeetvatsal.db.AppDatabase;
import com.mpvc.finalprojectmeetvatsal.db.dao.CartDao;
import com.mpvc.finalprojectmeetvatsal.db.dao.ProductDao;
import com.mpvc.finalprojectmeetvatsal.db.entity.Cart;
import com.mpvc.finalprojectmeetvatsal.db.entity.Product;
import com.mpvc.finalprojectmeetvatsal.helpers.HelperMethods;
import com.mpvc.finalprojectmeetvatsal.interfaces.ButtonListenerInterface;
import com.mpvc.finalprojectmeetvatsal.recycler.HomePage;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends AppCompatActivity implements ButtonListenerInterface {
    List<Cart> cartList;
    RecyclerView recyclerView;
    CartAdapter adapter;

    private AppDatabase appDatabase;
    private ProductDao productDao;

    private Product product;

    private CartDao cartDao;
    private final String SHARED_PREFERENCE_FILE="mv-commerce";

    private TextView txtTotalCartPrice;

    private Button btnProceedToCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);

        txtTotalCartPrice=findViewById(R.id.cartPage_txtTotalPrice);
        btnProceedToCheckout=findViewById(R.id.cartPage_btnCheckout);

        Context ctx=this;
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE);
        int userId=sharedPreferences.getInt("UserID",0);

        btnProceedToCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.cartPage_btnCheckout:
                        if(cartList.size()>0){
                            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                            builder.setMessage("Proceed Checkout?")
                                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            AppDatabase.databaseWriteExecutor.execute(()->{
                                                cartDao.checkOutCart(userId);
                                            });
                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                            builder.show();
                        }
                        else{
                            HelperMethods.showToastMessage(ctx,"No products in cart", Toast.LENGTH_LONG);
                        }

                        break;
                    default:
                        break;
                }
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.cartPage_listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        appDatabase=AppDatabase.getDatabaseInstance(this);
        productDao = appDatabase.productDao();
        cartDao= appDatabase.cartDao();

        fillCartList(userId);

        //creating recyclerview adapter
        adapter = new CartAdapter(this, cartList,this);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }

    private  void getAllCartsCallBack(List<Cart> carts){
        cartList=carts;
        double cartTotal=0;
        for(Cart c:cartList){
            cartTotal+=c.getProduct().getPrice()*c.getQuantity();
        }
        txtTotalCartPrice.setText(HelperMethods.formatPrice(cartTotal));
        adapter.setCartList(cartList);

    }
    private void fillCartList(int userID){
        cartList= new ArrayList<>();

        cartDao.getCart(userID).observe(this,this::getAllCartsCallBack);
    }


    @Override
    public void onAnyBtnClick(View v, int position) {
        Cart cart=cartList.get(position);
        int qty=cart.getQuantity();
        switch (v.getId()) {
            case R.id.cart_btnDecreaseQty:
                qty-=1;
                cart.setQuantity(qty);
                if(qty>0){
                    AppDatabase.databaseWriteExecutor.execute(()->{
                        cartDao.update(cart);
                    });
                }
                else{
                    qty=0;
                    AppDatabase.databaseWriteExecutor.execute(()->{
                        cartDao.delete(cart);
                    });
                }

                break;
            case R.id.cart_btnIncreaseQty:
                qty+=1;
                cart.setQuantity(qty);
                AppDatabase.databaseWriteExecutor.execute(()->{
                    cartDao.update(cart);
                });
                break;
            case R.id.cart_btnRemoveProduct:
                AppDatabase.databaseWriteExecutor.execute(()->{
                    cartDao.delete(cart);
                });
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_button,menu);
        menu.findItem(R.id.menu_btnHome).setOnMenuItemClickListener(item ->{
            Intent i= new Intent(getApplicationContext(), HomePage.class);
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
            return true;
        });
        return true;
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