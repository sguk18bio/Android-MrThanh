package com.jackchen.getnationinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class InfoActivity extends AppCompatActivity {
    TextView txtName, txtArea, txtPop;
    Button btnBack;

    ImageView flagImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Mapping();

        castIntent();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Mapping(){
        txtName = findViewById(R.id.info_name);
        txtArea = findViewById(R.id.info_area);
        txtPop = findViewById(R.id.info_population);
        btnBack = findViewById(R.id.btnBack);
        flagImage = findViewById(R.id.info_image);
    }

    private void castIntent(){
//        Bitmap img = null;

        Intent intent = getIntent();
        txtPop.setText("Population: " + intent.getIntExtra("Population", 0) + "");
        txtArea.setText("Area in Square Km: " + intent.getFloatExtra("Area", 0.0f) + " km^2");
        txtName.setText(intent.getStringExtra("Name"));

        new ImageFromUrlHandler().execute(intent.getStringExtra("Image"));
//        Gán ảnh cho ImageView theo URL
//        try {
//            InputStream in = new URL(intent.getStringExtra("Image")).openStream();
//            img = BitmapFactory.decodeStream(in);
//            in.close();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Bitmap bit ;
//        try {
//            Bitmap bit = new ImageFromUrlHandler().execute(intent.getStringExtra("Image")).get();
//            Log.d("CCC",bit + "");
//            flagImage.setImageBitmap(bit);
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new ImageFromUrlHandler().execute(intent.getStringExtra("Image"));


    }

    private class ImageFromUrlHandler extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {

            return GetImageFromUrl(urls[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            flagImage.setImageBitmap(bitmap);
        }
    }

    protected Bitmap GetImageFromUrl(String url) {
        Bitmap image = null;

        try {
            Log.d("image path", url);
            URL path = new URL(url);
            InputStream in = path.openConnection().getInputStream();
            
            // get the Image bounds
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.outWidth = 200;
            options.outHeight = 200;

//            options.inJustDecodeBounds = true;
//
//            image = BitmapFactory.decodeStream(in,null,options);
//
//            //get actual width x height of the image and calculate the scale factor
//            options.inSampleSize = getScaleFactor(options.outWidth,options.outHeight,
//                    view.getWidth(),view.getHeight());

//            options.inJustDecodeBounds = false;


            image = BitmapFactory.decodeStream(in,null,options);

            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}