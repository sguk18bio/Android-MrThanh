package com.jackchen.smsfoodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnSubmit;
    CheckBox chkChicken, chkPotato, chkSandwich, chkNugget, chkHamburger, chkIceCream,
        chkFanta, chkCoca, chkSevUp, chkWater;
    RadioButton radMed, radLarge, radBanhTrang, radSalad;
    RadioGroup rgrpSize, rgrpExtra;
    ArrayList<CheckBox> foods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mapping();


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:0763233977"));
                String s = "Đơn đặt: ";

                for (CheckBox c : foods) {
                    if (c.isChecked()) {
                        s += c.getText() + "\n";
                    }
                }

                if (isNotChecked(foods)){
                    Toast.makeText(MainActivity.this, "You have to choose" +
                            "food before you place order", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isNotRadioChecked(radMed,radLarge)){
                    Toast.makeText(MainActivity.this, "Please choose size",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (rgrpSize.getCheckedRadioButtonId() == R.id.radMed) {
                    s += "Size: " + radMed.getText() + "\n";
                }else{
                    s += "Size: " + radLarge.getText() + "\n";
                }

                if(!isNotRadioChecked(radBanhTrang, radSalad)){
                    if (rgrpExtra.getCheckedRadioButtonId() == R.id.radBanhTrang){
                        s += "Extra: " + radBanhTrang.getText() + "\n";
                    }else{
                        s += "Extra: " + radSalad.getText() + "\n";
                    }
                }

                intent.putExtra("sms_body", s);
                startActivity(intent);
            }
        });
    }

    private boolean isNotChecked(ArrayList<CheckBox> foodList){
        for (CheckBox c : foodList){
            if (c.isChecked())
                return false;
        }
        return true;
    }

    private boolean isNotRadioChecked(RadioButton rad1, RadioButton rad2){
        if (!rad1.isChecked() && !rad2.isChecked())
            return true;
        return false;
    }

    private void Mapping(){
        btnSubmit = findViewById(R.id.btnSubmit);
        chkChicken = findViewById(R.id.chkFriedChicken);
        chkPotato = findViewById(R.id.chkFriedPotato);
        chkSandwich = findViewById(R.id.chkSandwich);
        chkNugget = findViewById(R.id.chkChickenNugget);
        chkHamburger = findViewById(R.id.chkHamburger);
        chkIceCream = findViewById(R.id.chkIceCream);
        chkFanta = findViewById(R.id.chkFanta);
        chkCoca = findViewById(R.id.chkCoca);
        chkSevUp = findViewById(R.id.chkSevenUp);
        chkWater = findViewById(R.id.chkAquafina);
        radBanhTrang = findViewById(R.id.radBanhTrang);
        radSalad = findViewById(R.id.radSalad);
        radLarge = findViewById(R.id.radLarge);
        radMed = findViewById(R.id.radMed);
        rgrpExtra = findViewById(R.id.radgrpExtra);
        rgrpSize = findViewById(R.id.radgrpSize);

        // add to foods
        foods.add(chkChicken);
        foods.add(chkPotato);
        foods.add(chkSandwich);
        foods.add(chkNugget);
        foods.add(chkHamburger);
        foods.add(chkIceCream);
        foods.add(chkFanta);
        foods.add(chkCoca);
        foods.add(chkSevUp);
        foods.add(chkWater);
    }
}