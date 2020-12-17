package com.mpvc.finalprojectmeetvatsal.viewholders;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mpvc.finalprojectmeetvatsal.R;
import com.mpvc.finalprojectmeetvatsal.interfaces.ButtonListenerInterface;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtViewTitle, txtViewShortDesc, txtViewPrice,txtQty,txtSubtotalPrice;
    public ImageView cartImageView;

    public Button btnIncreaseQty,btnDecreaseQty;
    public ImageButton btnDeleteProductFromCart;

    ButtonListenerInterface addToCartButtonListener;

    public CartViewHolder(View itemView, ButtonListenerInterface addToCartButtonListener) {
        super(itemView);
        this.addToCartButtonListener = addToCartButtonListener;

        txtViewTitle = itemView.findViewById(R.id.cart_txtViewTitle);
        txtViewShortDesc = itemView.findViewById(R.id.cart_txtViewShortDesc);
        //txtViewRating = itemView.findViewById(R.id.cart_txtViewRating);
        txtViewPrice = itemView.findViewById(R.id.cart_txtViewPrice);

        txtQty = itemView.findViewById(R.id.cart_txtQtyLabel);
        txtSubtotalPrice = itemView.findViewById(R.id.cart_txtPrice);



        cartImageView = itemView.findViewById(R.id.cart_imageView);

        btnIncreaseQty=itemView.findViewById(R.id.cart_btnIncreaseQty);
        btnIncreaseQty.setOnClickListener(this);

        btnDecreaseQty=itemView.findViewById(R.id.cart_btnDecreaseQty);
        btnDecreaseQty.setOnClickListener(this);

        btnDeleteProductFromCart=itemView.findViewById(R.id.cart_btnRemoveProduct);
        btnDeleteProductFromCart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        addToCartButtonListener.onAnyBtnClick(v,getAdapterPosition());
    }
}
