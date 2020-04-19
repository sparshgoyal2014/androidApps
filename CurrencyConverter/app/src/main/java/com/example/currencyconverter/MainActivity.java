package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convert(View view){
        EditText editText = findViewById(R.id.editText2);
        double currencyAmount = Double.parseDouble(editText.getText().toString());

        double convertedCurrency = currencyAmount * 70.0;
        String newCurrency = Double.toString(convertedCurrency);

        Toast.makeText(MainActivity.this, newCurrency, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
