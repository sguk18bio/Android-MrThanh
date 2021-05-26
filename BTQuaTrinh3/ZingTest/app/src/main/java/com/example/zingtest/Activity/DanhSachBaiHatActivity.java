package com.example.zingtest.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.zingtest.Adapter.DanhSachBaiHatAdapter;
import com.example.zingtest.Model.Album;
import com.example.zingtest.Model.BaiHat;
import com.example.zingtest.Model.Playlist;
import com.example.zingtest.Model.Quangcao;
import com.example.zingtest.Model.TheLoai;
import com.example.zingtest.R;
import com.example.zingtest.Service.APIService;
import com.example.zingtest.Service.Dataservice;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class DanhSachBaiHatActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton floatingActionButton;
    NestedScrollView nestedScrollView;
    RecyclerView recyclerView;
    Toolbar toolbar;
    ImageView imageviewdanhsachbaihat;

    Quangcao quangcao;
    ArrayList<BaiHat> listbaihat;
    DanhSachBaiHatAdapter danhSachBaiHatAdapter;

    Playlist playlist;
    TheLoai theLoai;
    Album album;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        IntentData();
        AnhXa();
        Init();

        if (quangcao != null && !quangcao.getTenBaiHat().equals("")) {
            setDataInViewQuangCao(quangcao.getTenBaiHat(), quangcao.getHinhBaiHat());
            getDataQuangCao(quangcao.getId());
        }

        if (playlist != null && !playlist.getTen().equals("")) {
            setDataInViewPlayList(playlist.getTen(), playlist.getHinhNen());
            getDataPlayList(playlist.getIdPlayList());
        }
        if (theLoai != null && !theLoai.getTenTheLoai().equals("")) {
            setDataInViewTheLoai(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai());
            getDataTheLoai(theLoai.getIdTheLoai());
        }
        if (album != null && !album.getTenAlbum().equals("")) {
            setDataInViewAlbum(album.getTenAlbum(), album.getHinhAlbum());
            getDataAlbum(album.getIdAlbum());
        }
    }

    private void getDataAlbum(String idalbum) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachBaiHatTheoAlbum(idalbum);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                listbaihat = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, listbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerView.setAdapter(danhSachBaiHatAdapter);
                eventClicked();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setDataInViewAlbum(String tenAlbum, String hinhAlbum) {
        collapsingToolbarLayout.setTitle(tenAlbum);
        try {
            //hinh la 1 duong dan nen phai tra ve url va convert bang bitmap
            URL url = new URL(hinhAlbum);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            //sdk > 16 moi su dung duoc setbackground trong code
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Picasso.get().load(hinhAlbum).into(imageviewdanhsachbaihat);
    }

    private void getDataTheLoai(String idTheLoai) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachBaiHatTheoTheLoai(idTheLoai);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                listbaihat = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, listbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerView.setAdapter(danhSachBaiHatAdapter);
                eventClicked();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setDataInViewTheLoai(String tenTheLoai, String hinhTheLoai) {
        collapsingToolbarLayout.setTitle(tenTheLoai);
        try {
            //hinh la 1 duong dan nen phai tra ve url va convert bang bitmap
            URL url = new URL(hinhTheLoai);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            //sdk > 16 moi su dung duoc setbackground trong code
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Picasso.get().load(hinhTheLoai).into(imageviewdanhsachbaihat);
    }

    private void getDataPlayList(String idPlayList) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachBaiHatTheoPlayList(idPlayList);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                listbaihat = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, listbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerView.setAdapter(danhSachBaiHatAdapter);
                eventClicked();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setDataInViewPlayList(String ten, String hinhNen) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            //hinh la 1 duong dan nen phai tra ve url va convert bang bitmap
            URL url = new URL(hinhNen);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            //sdk > 16 moi su dung duoc setbackground trong code
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Picasso.get().load(hinhNen).into(imageviewdanhsachbaihat);
    }

    private void getDataQuangCao(String idquangcao) {
        //dung de khoi tao retrofit
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachBaiHatTheoQuangCao(idquangcao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                listbaihat = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, listbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerView.setAdapter(danhSachBaiHatAdapter);
                eventClicked();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setDataInViewQuangCao(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            //hinh la 1 duong dan nen phai tra ve url va convert bang bitmap
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            //sdk > 16 moi su dung duoc setbackground trong code
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Picasso.get().load(hinh).into(imageviewdanhsachbaihat);

    }

    private void Init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);


    }

    private void AnhXa() {
        coordinatorLayout = findViewById(R.id.coordinatorbaihat);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolebarlayout);
        floatingActionButton = findViewById(R.id.floatingactionbuttonbaihat);
        nestedScrollView = findViewById(R.id.nestedscrollviewbaihat);
        recyclerView = findViewById(R.id.recyclerviewbaihat);
        toolbar = findViewById(R.id.toolbardanhsachbaihat);
        imageviewdanhsachbaihat = findViewById(R.id.imageviewdanhsachbaihat);
        floatingActionButton.setEnabled(false);
    }

    private void IntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
            }
            if (intent.hasExtra("itemplaylist")) {
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
            }
            if (intent.hasExtra("idtheloai")) {
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if (intent.hasExtra("idalbum")) {
                album = (Album) intent.getSerializableExtra("idalbum");
            }
        }
    }

    private void eventClicked() {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachBaiHatActivity.this, PlayNhacActivity.class);
                intent.putExtra("danhsachbaihat", listbaihat);
                startActivity(intent);
            }
        });
    }
}