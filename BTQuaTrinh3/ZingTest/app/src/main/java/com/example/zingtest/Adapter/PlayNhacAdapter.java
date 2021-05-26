package com.example.zingtest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zingtest.Model.BaiHat;
import com.example.zingtest.R;

import java.util.ArrayList;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> listbaihat;

    public PlayNhacAdapter(Context context, ArrayList<BaiHat> listbaihat) {
        this.context = context;
        this.listbaihat = listbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_play_nhac, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baihat = listbaihat.get(position);
        holder.tvindex.setText(position + 1 + "");
        holder.tvtencasi.setText(baihat.getCaSi());
        holder.tvtenbaihat.setText(baihat.getTenBaiHat());
    }

    @Override
    public int getItemCount() {
        return listbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvindex, tvtenbaihat, tvtencasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvindex = itemView.findViewById(R.id.textviewplaynhacindex);
            tvtenbaihat = itemView.findViewById(R.id.textviewplaynhactenbaihat);
            tvtencasi = itemView.findViewById(R.id.textviewplaynhactencasi);
        }
    }
}
