package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {  // extends AppCompatActivity is used for backward Compatibility

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickFunction(View view){

        Toast.makeText(MainActivity.this, "Hi There", Toast.LENGTH_SHORT).show();
        EditText editText = findViewById(R.id.editText);
        Toast.makeText(MainActivity.this, editText.getText().toString() + "!", Toast.LENGTH_SHORT).show();


        Log.i("Info.", editText.getText().toString());
    }
}
