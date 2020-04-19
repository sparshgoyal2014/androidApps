package com.example.animations2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void fade(View view){
        ImageView imageView = findViewById(R.id.imageView);


        imageView.animate().scaleX(0.5f).scaleY(0.5f).setDuration(1000);


//        ImageView imageView2 = findViewById(R.id.imageView2);
//        imageView2.animate().alpha(1).setDuration(2000);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
