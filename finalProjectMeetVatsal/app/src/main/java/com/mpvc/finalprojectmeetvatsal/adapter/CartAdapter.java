package com.mpvc.finalprojectmeetvatsal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.mpvc.finalprojectmeetvatsal.R;
import com.mpvc.finalprojectmeetvatsal.db.entity.Cart;
import com.mpvc.finalprojectmeetvatsal.helpers.HelperMethods;
import com.mpvc.finalprojectmeetvatsal.interfaces.ButtonListenerInterface;
import com.mpvc.finalprojectmeetvatsal.viewholders.CartViewHolder;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    //this context we will use to inflate the layout
    private Context mCtx;
    private ButtonListenerInterface addToCartButtonListener;

    //we are storing all the products in a list
    private List<Cart> cartList;

    //getting the context and product list with constructor
    public CartAdapter(Context mCtx, List<Cart> cartList, ButtonListenerInterface addToCartButtonListener) {
        this.mCtx = mCtx;
        this.cartList = cartList;
        this.addToCartButtonListener = addToCartButtonListener;
    }

    public void setCartList(List<Cart> cartList){
        this.cartList=cartList;
        notifyDataSetChanged();
    }

    public void addProductToList(Cart cart){
        this.cartList.add(cart);
        notifyDataSetChanged();
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_cart_product_layout, null);
        return new CartViewHolder(view, addToCartButtonListener);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        //getting the product of the specified position
        Cart cart = cartList.get(position);

        //binding the data with the viewholder views
        holder.txtViewTitle.setText(cart.getProduct().getTitle());
        holder.txtViewShortDesc.setText(cart.getProduct().getShortDesc());
        //holder.txtViewRating.setText(String.valueOf(cart.getProduct().getRating()));
        holder.txtViewPrice.setText(HelperMethods.formatPrice(cart.getProduct().getPrice()));
        holder.txtQty.setText(String.valueOf(cart.getQuantity()));
        holder.txtSubtotalPrice.setText(HelperMethods.formatPrice(getProductTotalPrice(cart)));
        holder.cartImageView.setImageDrawable(mCtx.getDrawable(HelperMethods.getImageID(cart.getProduct().getProductImage())));

    }


    private double getProductTotalPrice(Cart cart){
        return cart.getQuantity()*cart.getProduct().getPrice();
    }


    @Override
    public int getItemCount() {
        return cartList.size();
    }


}
