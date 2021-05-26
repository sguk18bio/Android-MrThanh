package com.example.zingtest.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.zingtest.Activity.DanhSachBaiHatActivity;
import com.example.zingtest.Activity.DanhSachChuDeActivity;
import com.example.zingtest.Activity.DanhSachTheLoaiTheoChuDeActivity;
import com.example.zingtest.Model.ChuDe;
import com.example.zingtest.Model.TheLoaiTrongNgay;
import com.example.zingtest.Model.TheLoai;
import com.example.zingtest.R;
import com.example.zingtest.Service.APIService;
import com.example.zingtest.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView tvxemthem, tvchudevatheloai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai,container,false);
        GetData();
        AnhXa();

        tvxemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachChuDeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void AnhXa() {
        horizontalScrollView = view.findViewById(R.id.horizontalscrollview);
        tvxemthem = view.findViewById(R.id.textviewxemthem);
        tvchudevatheloai = view.findViewById(R.id.textviewtitlechudevatheloai);
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<TheLoaiTrongNgay> callback = dataservice.GetChuDeVaTheLoai();
        callback.enqueue(new Callback<TheLoaiTrongNgay>() {
            @Override
            public void onResponse(Call<TheLoaiTrongNgay> call, Response<TheLoaiTrongNgay> response) {
                TheLoaiTrongNgay theLoaitrongngay = response.body();

                final ArrayList<ChuDe> listchude = new ArrayList<>();
                listchude.addAll(theLoaitrongngay.getChude());

                final ArrayList<TheLoai> listtheloai = new ArrayList<>();
                listtheloai.addAll(theLoaitrongngay.getTheloai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580, 250);
                layout.setMargins(10, 20, 10, 30);
                for (int i = 0; i < listchude.size(); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (listchude.get(i).getHinhChuDe() != null) {
                        Picasso.get().load(listchude.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), DanhSachTheLoaiTheoChuDeActivity.class);
                            intent.putExtra("idchude",listchude.get(finalI));
                            startActivity(intent);
                        }
                    });

                }
                for (int j = 0; j < listtheloai.size(); j++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (listtheloai.get(j).getHinhTheLoai() != null) {
                        Picasso.get().load(listtheloai.get(j).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), DanhSachBaiHatActivity.class);
                            intent.putExtra("idtheloai",listtheloai.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<TheLoaiTrongNgay> call, Throwable t) {

            }
        });
    }
}
