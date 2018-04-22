package com.ex2.asus.currencyconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    public static final String CURRENCY = "com.ex2.asus.currencyconverter.CURRENCY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void chooseCurr(View view) {
        Intent intent = new Intent(this, ConverterActivity.class);
        RadioGroup radioGroup =  (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton checked = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        String currency = checked.getText().toString();

        intent.putExtra(CURRENCY,currency);
        startActivity(intent);
    }
}
