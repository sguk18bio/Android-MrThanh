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
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> listbaihat;

    public SearchBaiHatAdapter(Context context, ArrayList<BaiHat> listbaihat) {
        this.context = context;
        this.listbaihat = listbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_search_bai_hat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = listbaihat.get(position);
        holder.tvtenbaihat.setText(baiHat.getTenBaiHat());
        holder.tvtencasi.setText(baiHat.getCaSi());
        Picasso.get().load(baiHat.getHinhBaiHat()).into(holder.ivhinhbaihat);
    }

    @Override
    public int getItemCount() {
        return listbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivhinhbaihat, ivluotthich;
        TextView tvtenbaihat, tvtencasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivhinhbaihat = itemView.findViewById(R.id.imageviewsearchbaihat);
            ivluotthich = itemView.findViewById(R.id.imageviewluotthichsearchtimkiem);
            tvtenbaihat = itemView.findViewById(R.id.textviewtenbaihatsearchbaihat);
            tvtencasi = itemView.findViewById(R.id.textviewtencasisearchbaihat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", listbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });

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
        }
    }
}
