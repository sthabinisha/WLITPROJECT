package com.example.user.wlitlogin.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.user.wlitlogin.Model.Items;
import com.example.user.wlitlogin.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    ArrayList<Items> entityArrayList;


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;

    }
    public CustomAdapter(ArrayList<Items> entityArrayList) {
        this.entityArrayList = entityArrayList;

    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
            Items item =entityArrayList.get(position);

       holder.user.setText(item.getUsername());

       holder.img.setImageResource((item.getImage()));
    }

    @Override
    public int getItemCount() {
        return entityArrayList.size();

    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public TextView user;
        public ImageView img;
        public CustomViewHolder(final View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.card);
            img=(ImageView)itemView.findViewById(R.id.image);
            user=(TextView)itemView.findViewById(R.id.username);

        }


    }


}
