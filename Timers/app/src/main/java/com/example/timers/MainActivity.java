package com.example.timers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000, 1000){
            public void onTick(long milliSecondsUntilDone){
                //countdown is counting down(every second)
                Log.i("Seconds Left:", String.valueOf(milliSecondsUntilDone/1000));
            }

            @Override
            public void onFinish() {
                //counter is finished(after 10 seconds)
                Log.i("Done!", "Countdown timer Finished.");
            }
        }.start();

        /*final Handler handler = new Handler(); //it allows chunks of code to be run in a delayed way.
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //Insert Code to be run every second.

                Log.i("Runnable has Run: ", " a second must have past.");
                handler.postDelayed(this, 1000);
            }
        };

        handler.post(runnable);

       */
    }
}
