package com.ex2.asus.currencyconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConverterActivity extends AppCompatActivity {
    public static final Double VND_TO_USD = 0.000044;
    public static final Double VND_TO_EUR = 0.000035805893;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        Intent intent = getIntent();
        String currency = intent.getStringExtra(MainActivity.CURRENCY);
        TextView textView = (TextView) findViewById(R.id.curText);
        TextView curResView = (TextView) findViewById(R.id.curResText);
        TextView curResView2 = (TextView) findViewById(R.id.curResText2);
        TextView unitView = (TextView) findViewById(R.id.unit);
        TextView unitView2 = (TextView) findViewById(R.id.unit2);
        TextView unitView3 = (TextView) findViewById(R.id.unit3);

        textView.setText(String.format("%s:", currency));
        if (currency.equals(getString(R.string.radio_vnd))) {
            //If user choose VND
            unitView.setText(R.string.vnd);
            curResView.setText(R.string.usd_label);
            unitView2.setText(R.string.usd);
            curResView2.setText(R.string.eur_label);
            unitView3.setText(R.string.eur);
        } else {
            curResView.setText(R.string.vnd_label);
            unitView2.setText(R.string.vnd);
            if (currency.equals(getString(R.string.radio_usd))) {
                unitView.setText(R.string.usd);
                curResView2.setText(R.string.eur_label);
                unitView3.setText(R.string.eur);
            } else {
                unitView.setText(R.string.eur);
                curResView2.setText(R.string.usd_label);
                unitView3.setText(R.string.usd);
            }
        }
    }

    public void convertCurr(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        TextView curView = (TextView) findViewById(R.id.curText);

        if (editText.getText().toString().equals("")) {
            Toast.makeText(this, R.string.empty_error, Toast.LENGTH_SHORT).show();
        } else {
            Double amount = Double.parseDouble(editText.getText().toString());
            TextView resText = (TextView) findViewById(R.id.resText);
            TextView resText2 = (TextView) findViewById(R.id.resText2);

            if (curView.getText().toString().equals(getString(R.string.radio_vnd) + ":")) {
                Double convert = amount*VND_TO_USD;
                Double convert2 = amount*VND_TO_EUR;

                resText.setText(String.format("%.4f",convert));
                resText2.setText(String.format("%.4f",convert2));
            } else if (curView.getText().toString().equals(getString(R.string.radio_usd) + ":")) {
                Double convert = amount/VND_TO_USD;
                Double convert2 = amount*(VND_TO_EUR/VND_TO_USD);

                resText.setText(String.format("%.4f",convert));
                resText2.setText(String.format("%.4f",convert2));
            } else {
                Double convert = amount / VND_TO_EUR;
                Double convert2 = amount * (VND_TO_USD / VND_TO_EUR);

                resText.setText(String.format("%.4f", convert));
                resText2.setText(String.format("%.4f", convert2));
            }
        }
    }
}
