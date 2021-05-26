package com.example.zingtest.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zingtest.Activity.PlayNhacActivity;
import com.example.zingtest.Adapter.PlayNhacAdapter;
import com.example.zingtest.R;

public class Fragment_Play_Nhac extends Fragment {
    View view;
    RecyclerView recyclerView;

    PlayNhacAdapter playNhacAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_nhac, container, false);
        recyclerView = view.findViewById(R.id.recyclerviewplaynhac);
        if (PlayNhacActivity.listbaihat.size() > 0) {
            playNhacAdapter = new PlayNhacAdapter(getActivity(), PlayNhacActivity.listbaihat);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(playNhacAdapter);
        }
        return view;
    }
}
