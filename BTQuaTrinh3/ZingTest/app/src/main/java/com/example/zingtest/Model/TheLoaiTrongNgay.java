package com.example.zingtest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TheLoaiTrongNgay {
    @SerializedName("TheLoai")
    @Expose
    private List<TheLoai> theloai = null;

    @SerializedName("ChuDe")
    @Expose
    private List<ChuDe> chude = null;

    public List<TheLoai> getTheloai() {
        return theloai;
    }

    public void setTheloai(List<TheLoai> theloai) {
        this.theloai = theloai;
    }

    public List<ChuDe> getChude() {
        return chude;
    }

    public void setChude(List<ChuDe> chude) {
        this.chude = chude;
    }
}