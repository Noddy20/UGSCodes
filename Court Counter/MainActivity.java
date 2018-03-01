package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    int scoreA = 0;
    int scoreB = 0;
    //Add 1point to player A
    public void addPA(View view){
       if(scoreA<21 && scoreB<21)
       scoreA++;
       displayForPlayerA(scoreA);
        if(scoreA==21) {
            char a = 'A';
            displayWinner(a);
        }
    }
    //decrease 1 point of player A
    public void faultPA(View view){
        if(scoreA>0 && scoreB<21 && scoreA<21)
        scoreA--;
        displayForPlayerA(scoreA);
    }
    //Add 1point to player A
    public void addPB(View view){
        if(scoreB<21 && scoreA<21)
        scoreB++;
        displayForPlayerB(scoreB);
        if(scoreB==21) {
            char b = 'B';
            displayWinner(b);
        }
    }
    //decrease 1 point of player A
    public void faultPB(View view){
        if(scoreB>0 && scoreB<21 && scoreA<21)
        scoreB--;
        displayForPlayerB(scoreB);
    }
    //Reset score of team A and team B
    public void resetScore(View view){
        scoreA = 0;
        scoreB = 0;
        displayForPlayerA(scoreA);
        displayForPlayerB(scoreB);
    }
    /**
     * Displays the given score for Team A.
     */
    public void displayForPlayerA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_a_score);
        scoreView.setText(String.valueOf(score));
    }
    /**
     * Displays the given score for Team B.
     */
    public void displayForPlayerB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_b_score);
        scoreView.setText(String.valueOf(score));
    }
    /**
     * Displays the winner.
     */
    public void displayWinner(char p) {
        TextView scoreView = (TextView) findViewById(R.id.winner);
        scoreView.setText(String.valueOf("Player " + p + " Won.."));
    }
}
