package com.mpvc.finalprojectmeetvatsal.viewholders;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mpvc.finalprojectmeetvatsal.R;
import com.mpvc.finalprojectmeetvatsal.interfaces.ButtonListenerInterface;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
    public ImageView imageView;

    public Button btnAddToCart;

    ButtonListenerInterface addToCartButtonListner;

    public ProductViewHolder(View itemView, ButtonListenerInterface addToCartButtonListner) {
        super(itemView);
        this.addToCartButtonListner=addToCartButtonListner;

        textViewTitle = itemView.findViewById(R.id.textViewTitle);
        textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
        textViewRating = itemView.findViewById(R.id.textViewRating);
        textViewPrice = itemView.findViewById(R.id.textViewPrice);
        imageView = itemView.findViewById(R.id.imageView);

        btnAddToCart=itemView.findViewById(R.id.cardlayout_btnAddToCart);
        btnAddToCart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        addToCartButtonListner.onAnyBtnClick(v,getAdapterPosition());
    }
}
