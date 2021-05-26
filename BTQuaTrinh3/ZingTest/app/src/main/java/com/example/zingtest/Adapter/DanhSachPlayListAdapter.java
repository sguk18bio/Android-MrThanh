package com.example.zingtest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zingtest.Activity.DanhSachBaiHatActivity;
import com.example.zingtest.Model.Playlist;
import com.example.zingtest.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachPlayListAdapter extends RecyclerView.Adapter<DanhSachPlayListAdapter.ViewHolder> {
    Context context;
    ArrayList<Playlist> listplaylist;

    public DanhSachPlayListAdapter(Context context, ArrayList<Playlist> listplaylist) {
        this.context = context;
        this.listplaylist = listplaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_danh_sach_play_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = listplaylist.get(position);
        Picasso.get().load(playlist.getHinhNen()).into(holder.ivdanhsachplaylist);
        holder.tvdanhsachtenplaylist.setText(playlist.getTen());
    }

    @Override
    public int getItemCount() {
        return listplaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivdanhsachplaylist;
        TextView tvdanhsachtenplaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivdanhsachplaylist = itemView.findViewById(R.id.imageviewdanhsachplaylist);
            tvdanhsachtenplaylist = itemView.findViewById(R.id.textviewtendanhsachplaylist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,DanhSachBaiHatActivity.class);
                    intent.putExtra("itemplaylist",listplaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
