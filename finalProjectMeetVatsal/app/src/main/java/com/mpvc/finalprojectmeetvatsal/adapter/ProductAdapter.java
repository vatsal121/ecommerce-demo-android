package com.mpvc.finalprojectmeetvatsal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.recyclerview.widget.RecyclerView;

import com.mpvc.finalprojectmeetvatsal.R;
import com.mpvc.finalprojectmeetvatsal.db.entity.Product;
import com.mpvc.finalprojectmeetvatsal.helpers.HelperMethods;
import com.mpvc.finalprojectmeetvatsal.interfaces.ButtonListenerInterface;
import com.mpvc.finalprojectmeetvatsal.viewholders.ProductViewHolder;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    //this context we will use to inflate the layout
    private Context mCtx;
    private ButtonListenerInterface addToCartButtonListner;

    //we are storing all the products in a list
    private List<Product> productList;

    //getting the context and product list with constructor
    public ProductAdapter(Context mCtx, List<Product> productList, ButtonListenerInterface addToCartButtonListner) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.addToCartButtonListner=addToCartButtonListner;
    }

    public void setProductList(List<Product> productList){
        this.productList=productList;
        notifyDataSetChanged();
    }

    public void addProductToList(Product product){
        this.productList.add(product);
        notifyDataSetChanged();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_layout_products, null);
        return new ProductViewHolder(view, addToCartButtonListner);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        Product product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getShortDesc());
        holder.textViewRating.setText(String.valueOf(product.getRating()));
        holder.textViewPrice.setText(String.valueOf("$ "+product.getPrice()));

        holder.imageView.setImageDrawable(mCtx.getDrawable(HelperMethods.getImageID(product.getProductImage())));
        Animation animation= AnimationUtils.loadAnimation(mCtx, android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


}
