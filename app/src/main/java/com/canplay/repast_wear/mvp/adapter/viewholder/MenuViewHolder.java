package com.canplay.repast_wear.mvp.adapter.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.canplay.repast_wear.R;


/**
 * Created by mykar on 17/4/12.
 */
public class MenuViewHolder extends RecyclerView.ViewHolder  {

    public ImageView img;
    public CardView cardView;


    public MenuViewHolder(View itemView) {
        super(itemView);
        img= (ImageView) itemView.findViewById(R.id.img);
        cardView= (CardView) itemView.findViewById(R.id.card1);


    }

}
