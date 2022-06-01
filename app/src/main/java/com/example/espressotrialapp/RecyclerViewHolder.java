package com.example.espressotrialapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_player;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_player = itemView.findViewById(R.id.tv_player);
    }
}
