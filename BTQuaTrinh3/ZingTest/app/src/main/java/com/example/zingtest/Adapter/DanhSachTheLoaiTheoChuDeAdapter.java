package com.example.zingtest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zingtest.Activity.DanhSachBaiHatActivity;
import com.example.zingtest.Model.TheLoai;
import com.example.zingtest.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachTheLoaiTheoChuDeAdapter extends RecyclerView.Adapter<DanhSachTheLoaiTheoChuDeAdapter.ViewHolder> {
    Context context;
    ArrayList<TheLoai> listtheloai;

    public DanhSachTheLoaiTheoChuDeAdapter(Context context, ArrayList<TheLoai> listtheloai) {
        this.context = context;
        this.listtheloai = listtheloai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_danh_sach_the_loai_theo_chu_de, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = listtheloai.get(position);
        Picasso.get().load(theLoai.getHinhTheLoai()).into(holder.imageviewdanhsachtheloaitheochude);
        holder.textviewtentheloaitheochude.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return listtheloai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageviewdanhsachtheloaitheochude;
        TextView textviewtentheloaitheochude;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageviewdanhsachtheloaitheochude = itemView.findViewById(R.id.imageviewdanhsachtheloaitheochude);
            textviewtentheloaitheochude = itemView.findViewById(R.id.textviewtentheloaitheochude);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("idtheloai",listtheloai.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
