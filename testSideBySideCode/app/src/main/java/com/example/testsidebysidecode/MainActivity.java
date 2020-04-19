package com.example.testsidebysidecode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Math.max;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.buton);
        button2 = findViewById(R.id.button2);
//
        LinearLayout linearLayout = findViewById(R.id.mainView);
//        String name = textView.getParent().getClass().getName();
//
//        textView.setText(name);
//        View view = LayoutInflater.from(this).inflate(R.layout.activity_main, linearLayout, false);
////        ((ViewGroup)view).removeView(view);
////        ((ViewGroup)view).removeView(view.findViewById(R.id.button2));
////        ((ViewGroup)view).removeView(view.findViewById(R.id.buton));
//
//        linearLayout.addView(view);
//
//        String name = view.getParent().getClass().getName();
//        textView.setText(name);
//
//        Button buttonDown = view.findViewById(R.id.button2);
//        buttonDown.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.setText("Clicked");
//            }
//        });

        View view = LayoutInflater.from(this).inflate(R.layout.activity_main, linearLayout, true);
        linearLayout.addView(view);




//        new CountDownTimer(20000,1000){
//
//            @Override
//            public void onTick(long millisUntilFinished) {
//                maxCuts(1000,1,1,1);
//                textView.setText("Algo finished");
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                maxCuts(1000,1,1,1);
//                textView.setText("Algo finished");
                Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "button 2 clicked", Toast.LENGTH_SHORT).show();
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("progressing..");
                progressDialog.show();
            }
        });

    }


    int maxCuts(int length, int a, final int b, final int c){


        if(length == 0)return 0;
        if(length < 0)return -1;


        int result = max(max(maxCuts(length-a,a,b,c), maxCuts(length-b,a,b,c)), maxCuts(length-c,a,b,c));
        if(result == -1)return -1;
        return 1+result;
    }

}
