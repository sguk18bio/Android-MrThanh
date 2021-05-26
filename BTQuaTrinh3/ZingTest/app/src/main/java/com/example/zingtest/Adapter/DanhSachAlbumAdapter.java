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

import com.example.zingtest.Activity.DanhSachAlbumActivity;
import com.example.zingtest.Activity.DanhSachBaiHatActivity;
import com.example.zingtest.Model.Album;
import com.example.zingtest.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachAlbumAdapter extends RecyclerView.Adapter<DanhSachAlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> listalbum;

    public DanhSachAlbumAdapter(Context context, ArrayList<Album> listalbum) {
        this.context = context;
        this.listalbum = listalbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_danh_sach_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = listalbum.get(position);
        Picasso.get().load(album.getHinhAlbum()).into(holder.imageviewdanhsachalbum);
        holder.textviewtendanhsachalbum.setText(album.getTenAlbum());
        holder.textviewtencasidanhsachalbum.setText(album.getTenCaSiAlbum());
    }

    @Override
    public int getItemCount() {
        return listalbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageviewdanhsachalbum;
        TextView textviewtendanhsachalbum,textviewtencasidanhsachalbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageviewdanhsachalbum = itemView.findViewById(R.id.imageviewdanhsachalbum);
            textviewtencasidanhsachalbum = itemView.findViewById(R.id.textviewtencasidanhsachalbum);
            textviewtendanhsachalbum = itemView.findViewById(R.id.textviewtendanhsachalbum);

            imageviewdanhsachalbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("idalbum",listalbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
