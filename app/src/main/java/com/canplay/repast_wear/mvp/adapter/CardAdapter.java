package com.canplay.repast_wear.mvp.adapter;

/**
 * Created by Meiko on 2018/1/7.
 */

import android.support.v7.widget.CardView;

public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 4;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}