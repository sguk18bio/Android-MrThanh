package com.example.zingtest.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.zingtest.Adapter.BannerAdapter;
import com.example.zingtest.Model.Quangcao;
import com.example.zingtest.R;
import com.example.zingtest.Service.APIService;
import com.example.zingtest.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {
    View view;
    ViewPager viewpager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int current_item;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        getData();
        AnhXa();
        return view;
    }

    private void AnhXa() {
        viewpager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.circleindicator);
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Quangcao>> callback = dataservice.GetDataBanner();
        callback.enqueue(new Callback<List<Quangcao>>() {
            @Override
            public void onResponse(Call<List<Quangcao>> call, Response<List<Quangcao>> response) {
                ArrayList<Quangcao> banners = (ArrayList<Quangcao>) response.body();
                bannerAdapter = new BannerAdapter(getActivity(),banners);
                viewpager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewpager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        current_item = viewpager.getCurrentItem();
                        current_item++;
                        if (current_item >= viewpager.getAdapter().getCount()){
                            current_item = 0;
                        }
                        viewpager.setCurrentItem(current_item,true);
                        handler.postDelayed(runnable,4500);
                    }
                };
                handler.postDelayed(runnable,4500);
            }

            @Override
            public void onFailure(Call<List<Quangcao>> call, Throwable t) {

            }
        });

    }
}
