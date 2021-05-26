package com.example.zingtest.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zingtest.Adapter.BaiHatAdapter;
import com.example.zingtest.Model.BaiHat;
import com.example.zingtest.R;
import com.example.zingtest.Service.APIService;
import com.example.zingtest.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Bai_Hat extends Fragment {
    View view;
    RecyclerView recyclerviewbaihat;
    TextView tvtitlebaihat;
    BaiHatAdapter baiHatAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bai_hat, container, false);
        GetData();
        AnhXa();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetBaiHat();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> listbaihat = (ArrayList<BaiHat>) response.body();
                baiHatAdapter = new BaiHatAdapter(getActivity(), listbaihat);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerviewbaihat.setLayoutManager(linearLayoutManager);
                recyclerviewbaihat.setAdapter(baiHatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }


    private void AnhXa() {
        recyclerviewbaihat = view.findViewById(R.id.recyclerviewbaihat);
        tvtitlebaihat = view.findViewById(R.id.textviewbaihat);
    }
}
