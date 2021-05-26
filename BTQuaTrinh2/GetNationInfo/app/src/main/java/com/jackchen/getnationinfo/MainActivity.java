package com.jackchen.getnationinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvNation;
    ArrayList<Nation> nationArrayList;
    NationAdapter adapter;
    URL url;
    StringBuffer response;
    String responseText;
    private ProgressDialog progressDialog;

    //đường dẫn URl
    private static final String USER_NAME = "jack123";
    private static final String PATH = "http://api.geonames.org/countryInfoJSON?username="
            + USER_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Before Mapping", "Pre Mapping");
        // ánh xạ view
        Mapping();

        Log.d("After Mapping", "Before Call Service");
        //Gọi service
        new GetService().execute();
    }

    private void Mapping(){
        lvNation = findViewById(R.id.nationList);
        nationArrayList = new ArrayList<>();
    }
    
    private class GetService extends AsyncTask
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Fetching country data");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            return getWebServiceResponseData();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            // Tắt progressDialog
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
//            // Chèn data của json vào listview
            adapter = new NationAdapter(MainActivity.this,R.layout.custom_listview,nationArrayList);
            lvNation.setAdapter(adapter);

            // click vào 1 row trong listview để chuyển sang trang info
            lvNation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                    Intent intent = new Intent(MainActivity.this, InfoActivity.class);

                    intent.putExtra("Name", nationArrayList.get(i).getName());
                    intent.putExtra("Area", nationArrayList.get(i).getArea());
                    intent.putExtra("Population", nationArrayList.get(i).getPopulation());
                    intent.putExtra("Image", nationArrayList.get(i).getImageUrl());

                    startActivity(intent);
                }
            });
        }
    }

    protected Void getWebServiceResponseData(){
        try {
            url = new URL(PATH);
            Log.d("Tag", "Server Data: " + PATH);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.d("AAA", "After create httpURl");
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            Log.d("Tag", "Response Code: " + responseCode);
            if(responseCode == HttpURLConnection.HTTP_OK){
                //Reading Response from InputStream
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String output;
                response = new StringBuffer();

                while((output = in.readLine()) != null){
                    response.append(output);
                }
                in.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("Test resposnse:", response.toString());
        responseText = response.toString();
        Log.d("Tag", "data: " + responseText);
        try{
            JSONObject jsonObject = new JSONObject(responseText);
            JSONArray jsonArray = jsonObject.getJSONArray("geonames");

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonCountryObject = jsonArray.getJSONObject(i);

                //get json data to add to
                String countryCode = jsonCountryObject.getString("countryCode");
                String name = jsonCountryObject.getString("countryName");
                int population = jsonCountryObject.getInt("population");
                float area = (float) jsonCountryObject.getDouble("areaInSqKm");

                //get image from geonames.org
                String flagUrl = "https://img.geonames.org/flags/x/" +
                        countryCode.toLowerCase() + ".gif";

                Nation nationObj = new Nation(name, population,flagUrl,area);
                nationArrayList.add(nationObj);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}