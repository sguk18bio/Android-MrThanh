package com.example.zingtest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zingtest.Activity.PlayNhacActivity;
import com.example.zingtest.Model.BaiHat;
import com.example.zingtest.R;
import com.example.zingtest.Service.APIService;
import com.example.zingtest.Service.Dataservice;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> listbaihat;

    public DanhSachBaiHatAdapter(Context context, ArrayList<BaiHat> listbaihat) {
        this.context = context;
        this.listbaihat = listbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_danh_sach_bai_hat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = listbaihat.get(position);
        holder.tvtencasi.setText(baiHat.getCaSi());
        holder.tvtenbaihat.setText(baiHat.getTenBaiHat());
        holder.tvindex.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return listbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvindex, tvtenbaihat, tvtencasi;
        ImageView ivluotthich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvindex = itemView.findViewById(R.id.textviewindexdanhsachbaihat);
            tvtenbaihat = itemView.findViewById(R.id.textviewtenbaihat);
            tvtencasi = itemView.findViewById(R.id.textviewtencasidanhsachbaihat);
            ivluotthich = itemView.findViewById(R.id.imageviewdanhsachbaihat);

            ivluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ivluotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1", listbaihat.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("success")) {
                                Toast.makeText(context, "Đã Thích", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    ivluotthich.setEnabled(false);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",listbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
