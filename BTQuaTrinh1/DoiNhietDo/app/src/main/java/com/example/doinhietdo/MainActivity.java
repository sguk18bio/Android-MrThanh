package com.example.doinhietdo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText enterf, enterc;
    Button convert, reload;
    ListView result;
    ArrayList<String> listresult;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterf = (EditText) findViewById(R.id.ftoc);
        enterc = (EditText) findViewById(R.id.ctof);
        convert = (Button) findViewById(R.id.convert);
        reload = (Button) findViewById(R.id.reload);
        result = (ListView) findViewById(R.id.result);
        listresult = new ArrayList<>();

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, listresult);
        result.setAdapter(adapter);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textf = enterf.getText().toString();
                String textc = enterc.getText().toString();

                if (!textf.equals("") && textc.equals("")) {
                    double f = Double.parseDouble(textf);
                    double c = (f - 32.0) * 5.0 / 9.0;
                    String kq;
                    enterc.setText(String.valueOf(c));
                    kq = "F to C :" + f + "->" + c;
                    listresult.add(kq);
                    adapter.notifyDataSetChanged();
                } else if (!textc.equals("") && textf.equals("")) {
                    double c = Double.parseDouble(textc);
                    double f = (c * 9.0 / 5.0) + 32;
                    String kq;
                    enterf.setText(String.valueOf(f));
                    kq = "C to F :" + c + "->" + f;
                    listresult.add(kq);
                    adapter.notifyDataSetChanged();
                }
            }
        });

//        result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                enterf.setText(listresult.get(i));
//            }
//        });

//        result.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                i=position;
//                listresult.remove(position);
//                adapter.notifyDataSetChanged();
//                return false;
//            }
//        });
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterc.setText("");
                enterf.setText("");
            }
        });

    }
}