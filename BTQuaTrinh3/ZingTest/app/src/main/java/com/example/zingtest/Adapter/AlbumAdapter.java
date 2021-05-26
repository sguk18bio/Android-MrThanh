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
import com.example.zingtest.Model.Album;
import com.example.zingtest.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter  extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{
    Context context;
    ArrayList<Album> listalbum;

    public AlbumAdapter(Context context, ArrayList<Album> listalbum) {
        this.context = context;
        this.listalbum = listalbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.dong_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = listalbum.get(position);
        holder.tvtenalbum.setText(album.getTenAlbum());
        holder.tvtencasialbum.setText(album.getTenCaSiAlbum());
        Picasso.get().load(album.getHinhAlbum()).into(holder.imageviewalbum);
    }

    @Override
    public int getItemCount() {
        return listalbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageviewalbum;
        TextView tvtenalbum , tvtencasialbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageviewalbum = itemView.findViewById(R.id.imageviewalbum);
            tvtenalbum =itemView.findViewById(R.id.textviewtenalbum);
            tvtencasialbum = itemView.findViewById(R.id.textviewtencasialbum);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("idalbum",listalbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
