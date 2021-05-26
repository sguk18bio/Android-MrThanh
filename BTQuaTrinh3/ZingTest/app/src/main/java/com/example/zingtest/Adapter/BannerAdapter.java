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
import androidx.viewpager.widget.PagerAdapter;

//import com.example.zingtest.Activity.DanhSachBaiHatActivity;
import com.example.zingtest.Activity.DanhSachBaiHatActivity;
import com.example.zingtest.Model.Quangcao;
import com.example.zingtest.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<Quangcao> listbanner;

    public BannerAdapter(Context context, ArrayList<Quangcao> listbanner) {
        this.context = context;
        this.listbanner = listbanner;
    }

    @Override
    public int getCount() {
        return listbanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //định hình và gán dữ liệu cho mỗi object,tượng trưng cho mỗi cái page
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_banner, null);
        //Ánh xạ
        ImageView imageviewbackground=view.findViewById(R.id.imageviewbackgroundbanner);
        ImageView imageviewbanner=view.findViewById(R.id.imageviewbanner);
        TextView textviewnamesong=view.findViewById(R.id.textviewnamesong);
        TextView textviewtitlesong=view.findViewById(R.id.textviewtitlesong);

        Picasso.get().load(listbanner.get(position).getHinhAnh()).into(imageviewbackground);
        Picasso.get().load(listbanner.get(position).getHinhBaiHat()).into(imageviewbanner);
        textviewnamesong.setText(listbanner.get(position).getTenBaiHat());
        textviewtitlesong.setText(listbanner.get(position).getNoiDung());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("banner",listbanner.get(position));
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
