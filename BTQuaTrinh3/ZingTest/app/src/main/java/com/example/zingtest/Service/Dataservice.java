package com.example.zingtest.Service;

import com.example.zingtest.Model.Album;
import com.example.zingtest.Model.BaiHat;
import com.example.zingtest.Model.ChuDe;
import com.example.zingtest.Model.TheLoai;
import com.example.zingtest.Model.TheLoaiTrongNgay;
import com.example.zingtest.Model.Playlist;
import com.example.zingtest.Model.Quangcao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlayListCurrentDay();

    @GET("theloaivachudeforcurrentday.php")
    Call<TheLoaiTrongNgay> GetChuDeVaTheLoai();

    @GET("albumforcurrentday.php")
    Call<List<Album>> GetAlbum();

    @GET("baihat.php")
    Call<List<BaiHat>> GetBaiHat();

    //khi su dung phuong thuc post phai goi truoc FromUrlEncoded thi moi su dung duoc
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    //gui du lieu len thong qua phuong thuc @field, trung tu khoa trong Post ben server thi moi nhan duoc du lieu
    Call<List<BaiHat>> GetDanhSachBaiHatTheoQuangCao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoPlayList(@Field("idplaylist") String idplaylist);

    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>> GetDanhSachPlayList();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);

    @GET("danhsachcacchude.php")
    Call<List<ChuDe>> GetDanhSachChuDe();

    @FormUrlEncoded
    @POST("danhsachtheloaitheochude.php")
    Call<List<TheLoai>> GetDanhSachTheLoaiTheoChuDe(@Field("idchude") String idchude);

    @GET("danhsachcacalbum.php")
    Call<List<Album>> GetDanhSachAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("capnhatluothich.php")
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<BaiHat>> SearchBaiHat(@Field("tukhoa") String tukhoa);
}
