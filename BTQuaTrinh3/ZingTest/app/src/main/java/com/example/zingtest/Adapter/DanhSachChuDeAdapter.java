package com.example.zingtest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zingtest.Activity.DanhSachTheLoaiTheoChuDeActivity;
import com.example.zingtest.Model.ChuDe;
import com.example.zingtest.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachChuDeAdapter extends RecyclerView.Adapter<DanhSachChuDeAdapter.ViewHolder> {
    Context context;
    ArrayList<ChuDe> listchude;

    public DanhSachChuDeAdapter(Context context, ArrayList<ChuDe> listchude) {
        this.context = context;
        this.listchude = listchude;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_danh_sach_chu_de, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chude = listchude.get(position);
        Picasso.get().load(chude.getHinhChuDe()).into(holder.imageviewdongdanhsachchude);
    }

    @Override
    public int getItemCount() {
        return listchude.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageviewdongdanhsachchude;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageviewdongdanhsachchude = itemView.findViewById(R.id.imageviewdongdanhsachchude);

            imageviewdongdanhsachchude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,DanhSachTheLoaiTheoChuDeActivity.class);
                    intent.putExtra("idchude",listchude.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }

}
