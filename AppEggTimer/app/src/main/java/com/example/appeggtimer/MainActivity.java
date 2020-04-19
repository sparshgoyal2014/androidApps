package com.example.appeggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView timertextView;
    Boolean counterIsActive = false;
    Button controllerButton;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        timertextView.setText("0:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("Go!");
        seekBar.setEnabled(true);
        counterIsActive = false;
    }

    public void updateTimer(int secondsLeft){
        int minutes = (int)secondsLeft/60;
        int seconds = secondsLeft - (minutes*60);

        String secondsString = Integer.toString(seconds);
        if(secondsString.equals("0")){
            secondsString = "00";
        }else if(seconds <= 9){
            secondsString = "0" + secondsString;
        }

        String displayString = Integer.toString(minutes)+ ":" + secondsString;

        timertextView.setText(displayString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        timertextView = findViewById(R.id.timerTextView);
        controllerButton = findViewById(R.id.controllerButton);

        seekBar.setMax(600);  // arguement is assumed to be in seconds
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void controlTimer(View view) {

        if (counterIsActive == false) {


            counterIsActive = true;
            seekBar.setEnabled(false);
            controllerButton.setText("Stop!");

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
//                    timertextView.setText("0:00");
                    resetTimer();
                    MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.airhorn);
                    mediaPlayer.start();
                    Log.i("Finished", "timer Done");
                }
            }.start();

            Log.i("Button pressed: ", "pressed");
        }else{
            resetTimer();
        }
    }
}



















