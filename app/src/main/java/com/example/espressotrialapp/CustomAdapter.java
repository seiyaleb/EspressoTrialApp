package com.example.espressotrialapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<String> list_player;

    public CustomAdapter(List<String> _list_player) {

        list_player = _list_player;
    }

    //ビューホルダーオブジェクトを生成
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row,parent,false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view);
        return holder;
    }

    //各リストにデータを割り当て
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        //データ取得
        String player = list_player.get(position);

        //TextView設定
        holder.tv_player.setText(player);
    }

    @Override
    public int getItemCount() {
        return list_player.size();
    }
}
