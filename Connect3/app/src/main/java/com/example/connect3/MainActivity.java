package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    // 0: greenCounter and 1: redCounter

    boolean gameIsActive = true;

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    // 2: means unplayed

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};


    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        System.out.println(counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2  && gameIsActive){

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000);

            if(activePlayer == 0){
                counter.setImageResource(R.drawable.greencounter);
                activePlayer = 1;
            }else{
                counter.setImageResource(R.drawable.redcounter);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000).rotation(3600).setDuration(300);

            for(int[] winningPosition: winningPositions){
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2){

                    // Someone Has Won!

                    gameIsActive = false;
                    System.out.println(gameState[winningPosition[0]]);

                    String winner = "Red";

                    if(gameState[winningPosition[0]] == 0){
                        winner = "Green";
                    }




                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has Won!");

                    LinearLayout layout = findViewById(R.id.playAgain);

                    layout.setVisibility(View.VISIBLE);

                }else{
                    boolean gameIsOver = true;
                    for(int counterState: gameState){
                        if(counterState == 2) gameIsOver = false;
                    }

                    if(gameIsOver){
                        TextView winnerMessage = findViewById(R.id.winnerMessage);
                        winnerMessage.setText("Its a draw!");

                        LinearLayout layout = findViewById(R.id.playAgain);

                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }

    }


    public void playAgain(View view){
        gameIsActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgain);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        // 0: greenCounter and 1: redCounter


        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }

        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);  // empty image
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}


























