package com.example.viewdetail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.BaseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String[] foodList = {"Fried Chicken", "Fried Potatoes", "Hamburger", "Pasta", "Ice Cream"};
    int[] images = {R.drawable.fried_chicken, R.drawable.potatoes, R.drawable.hamburger, R.drawable.pasta, R.drawable.ice_cream};
    String[] descriptions = {
            "Best sale in 2021 - The chicken is so yummy. Just with 2$ you will have a delicious meal, provide energy for new day",
            "Crunchy fried potatoes with cheese, chilly would make you please",
            "A hamburger with fresh meat, delicious sauce make you feel nice",
            "Pasta cooked by Italian chef, bring you to a new world with pasta and cheese",
            "Small ice cream in hot days will cool your life, relax after busy days"
    };
    LayoutInflater inflater;

    public CustomAdapter(Context applicationContext) {
        this.context = context;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return foodList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_listview, null);
        TextView food = (TextView) view.findViewById(R.id.textView);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        food.setText(foodList[pos]);
        icon.setImageResource(images[pos]);
        return view;
    }
}
