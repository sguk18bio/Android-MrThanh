package com.jackchen.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends Activity {
    Button btnConvert;
    EditText edtFrom, edtTo;
    ListView lvHistory;
    Spinner spnFrom, spnTo;
    RequestQueue queue;

    private ArrayList<String> histories;
    private ArrayAdapter adapter;
    private ArrayList<String> currencyCodes;
    private ArrayAdapter spinnerAdapter;


    private String baseUrl = "https://%s.fxexchangerate.com/%s.xml";
    private String USER_NAME = "jack123";
    private String currencyCodeUrl = "http://api.geonames.org/countryInfoJSON?username="
                                    + USER_NAME;
    private String result;
    private String firstCurrencySelected;
    private String secondCurrencySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Mapping();

        createListView();
        parseJSON();

//        spnFrom = createSpinner(spnFrom);
//        spnTo   = createSpinner(spnTo);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, edtFrom.getText().toString().isEmpty() + "", Toast.LENGTH_SHORT).show();
                firstCurrencySelected = currencyCodes.get(spnFrom.getSelectedItemPosition())
                                        .toString().split("-")[0].trim().toLowerCase();
                secondCurrencySelected = currencyCodes.get(spnTo.getSelectedItemPosition())
                                        .toString().split("-")[0].trim().toLowerCase();

                if(edtFrom.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "User must enter an amount of money", Toast.LENGTH_SHORT).show();
                }else{
                    new ReadCurrencyRatioRSS().execute(firstCurrencySelected, secondCurrencySelected);
                }

            }
        });
    }

    private void Mapping(){
        btnConvert = findViewById(R.id.btnConvert);
        edtFrom = findViewById(R.id.edtFrom);
        edtTo  = findViewById(R.id.edtTo);
        spnFrom = findViewById(R.id.spnFrom);
        spnTo = findViewById(R.id.spnTo);
        lvHistory = findViewById(R.id.lvHistory);
        queue = Volley.newRequestQueue(this);

        histories = new ArrayList<>();
        currencyCodes = new ArrayList<>();
    }

    private void createListView(){
        adapter = new ArrayAdapter(MainActivity.this
                                    , android.R.layout.simple_expandable_list_item_1
                                    , histories);

        lvHistory.setAdapter(adapter);

        Log.d("DDD", "Create ListView Successfully");
    }

    private Spinner createSpinner(Spinner spinner){
        spinnerAdapter = new ArrayAdapter(MainActivity.this
                                         , android.R.layout.simple_spinner_dropdown_item
                                         ,currencyCodes);

        spinner.setAdapter(spinnerAdapter);

        return spinner;
    }


    private void parseJSON(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET
                                                    , currencyCodeUrl, null
                                                    , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("geonames");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String countryName = jsonObject.getString("countryName");
                        String currencyCode = jsonObject.getString("currencyCode");

                        currencyCodes.add(currencyCode + " - " + countryName);

                        spnTo = createSpinner(spnTo);
                        spnFrom = createSpinner(spnFrom);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(jsonObjectRequest);
    }

    class ReadCurrencyRatioRSS extends AsyncTask<String, Void, Double>{

        @Override
        protected Double doInBackground(String... strings) {
            if(!strings[0].equals(strings[1])){
                try {
                    URL url = new URL(String.format(baseUrl, strings[0], strings[1]));
                    InputStream in = url.openConnection().getInputStream();

                    XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
                    parser.setInput(in, null);

                    String content = "";
                    while(parser.next() != XmlPullParser.END_DOCUMENT){
                        int codeType = parser.getEventType();

                        switch (codeType){
                            case XmlPullParser.START_DOCUMENT:  break;
                            case XmlPullParser.END_DOCUMENT: break;
                            case XmlPullParser.START_TAG:
                                if(parser.getName().equals("description")){
                                    content = parser.nextText() + ",";
                                }
                        }
                    }

                    result = content.split("<br/>")[0];
                    Log.d("result 1:", result);
                    result = result.trim().split("\\s+")[3];
                    Log.d("result 2: ", result);

                    in.close();
                    return Double.parseDouble(result) * Double.parseDouble(edtFrom.getText().toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            }else{
                return Double.parseDouble(edtFrom.getText().toString());
            }


            return null;
        }

        @Override
        protected void onPostExecute(Double aDouble) {
            super.onPostExecute(aDouble);

            histories.add(firstCurrencySelected + " " + edtFrom.getText().toString()
                        + " = " + aDouble + " " + secondCurrencySelected);
            adapter.notifyDataSetChanged();

            edtTo.setText(aDouble + "");
        }
    }

}