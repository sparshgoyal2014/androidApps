package com.example.higherorlower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int randomNumber;

    public void makeToast(String string){
        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
    }


    public void guess(View view){
        EditText editText = findViewById(R.id.editText);


        int guessInt = Integer.parseInt(editText.getText().toString());


        if(guessInt > randomNumber){
//            Toast.makeText(MainActivity.this, "Lower!", Toast.LENGTH_SHORT).show();
            makeToast("Lower!");
        }else if(guessInt < randomNumber){
//            Toast.makeText(MainActivity.this, "Higher!", Toast.LENGTH_SHORT).show();
            makeToast("Higher!");
//            makeToast("New toast in Else if");
        }else{
//            Toast.makeText(MainActivity.this, "Hurrah! You Got the Number :)", Toast.LENGTH_SHORT).show();
            makeToast("Hurrah! You Got the Number :) Try it again.");

            randomNumber = new Random().nextInt(20) + 1;

        }

//        Toast.makeText(MainActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
//        makeToast("new toasr");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random random = new Random();
        randomNumber = random.nextInt(20)+1;
    }
}
