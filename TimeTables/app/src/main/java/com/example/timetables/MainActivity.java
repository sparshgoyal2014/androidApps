package com.example.timetables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar timeTablesSeekBar = findViewById(R.id.timeTables);
        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.textView);

        timeTablesSeekBar.setMax(100);
        timeTablesSeekBar.setProgress(10);
        timeTablesSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                int timesTables;

                if(progress < 1){
                    timesTables = min;
                    timeTablesSeekBar.setProgress(min);

                }else{
                    timesTables = progress;
                    textView.setText(Integer.toString(timesTables));

                }

//                Log.i("SeekBar Value:", Integer.toString(timesTables));
                generateTimesTables(timesTables);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        textView.setText(Integer.toString(10));

        generateTimesTables(10);

    }

    public void generateTimesTables(int timesTables){

        ArrayList<String> timesTablesContent = new ArrayList<>();
        for(int i=1; i<= 100; i++){
            timesTablesContent.add(Integer.toString(i) + ": "+ Integer.toString(i*timesTables));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, timesTablesContent);
        listView.setAdapter(arrayAdapter);
    }
}
