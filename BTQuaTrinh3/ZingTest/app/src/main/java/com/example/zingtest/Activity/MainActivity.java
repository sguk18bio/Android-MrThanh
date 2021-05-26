package com.example.zingtest.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.zingtest.Adapter.MainViewAdapterPager;
import com.example.zingtest.Fragment.Fragment_Tim_Kiem;
import com.example.zingtest.Fragment.Fragment_Trang_Chu;
import com.example.zingtest.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        Init();
    }
    @SuppressLint("ResourceType")
    private void Init(){
        MainViewAdapterPager mainViewAdapterPager= new MainViewAdapterPager(getSupportFragmentManager());
        mainViewAdapterPager.addFragment(new Fragment_Trang_Chu(),"Trang Chủ");
        mainViewAdapterPager.addFragment(new Fragment_Tim_Kiem(),"Tìm Kiếm");
        viewPager.setAdapter(mainViewAdapterPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
    }
    private  void AnhXa(){
        viewPager=(ViewPager) findViewById(R.id.viewpager);
        tabLayout=(TabLayout) findViewById(R.id.tablayout);
    }
}