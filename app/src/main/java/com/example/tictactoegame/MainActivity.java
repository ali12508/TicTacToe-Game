package com.example.tictactoegame;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public PlayerHandler playerHandler;
    public BoardHandler boardHandler;

public  int c=1;

    public void playerTap(View view) throws InterruptedException {
        Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
        vb.vibrate(100);
        TextView textView= (TextView) view;

//checkIfTappedSlotIsEmpty
    if(isEmptySlot(textView.getCurrentTextColor(),textView.getDrawingCacheBackgroundColor()))
    {
       // Log.i("textView = ",""+textView.getText());
        //Snackbar.make(view,  textView.getTag().toString(),Snackbar.LENGTH_SHORT).show();
        boardHandler.setTextView(playerHandler.getPlayer(),Integer.parseInt(textView.getTag().toString()));
        textView.setTextColor(Color.GREEN);
        textView.setText(""+playerHandler.getPlayer());
        char winner=boardHandler.checkWinner();
        if(winner!='E')
        {//Declare Winner


showAlertDailogue("Congradulations","\t"+winner+" is the Winner\nClick Ok to Play Again!");

        }else{//Check if board is full
            if(boardHandler.boardIsFull())
            {//Declare Game is Draw

showAlertDailogue("OoopSsss...!","\t"+"Game is DRAW\n\nClick Ok to Play Again!");


            }else{//go for next Move
                playerHandler.changePlayer(playerHandler.getPlayer());
                TextView playerTurn= findViewById(R.id.textView9);
                playerTurn.setText("Now Player "+playerHandler.getPlayer()+"\'s  Turn");
            }
        }
    }
    else{

        Snackbar.make(view, "Already Done",Snackbar.LENGTH_SHORT).show();

    }

}

public void doClick(View view)
{

}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        initializeBoard();
        if (savedInstanceState != null) {
           // playerHandler.setPlayer(savedInstanceState.getChar("player"));
           // boardHandler.setTextView(savedInstanceState.getCharArray("tv"));

            Log.i("O_C","Player"+savedInstanceState.getChar("player"));
        }
        setupBoard(playerHandler.getPlayer(),boardHandler.getTextView());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putCharArray("tv",boardHandler.getTextView());
        outState.putChar("player",playerHandler.getPlayer());
        Log.i("O_C Saving","Player"+outState.getChar("player"));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        //  playerHandler.setPlayer(savedInstanceState.getChar("player"));
        // boardHandler.setTextView(savedInstanceState.getCharArray("tv"));


        if (savedInstanceState != null) {
            playerHandler.setPlayer(savedInstanceState.getChar("player"));
            boardHandler.setTextView(savedInstanceState.getCharArray("tv"));
            setupBoard(playerHandler.getPlayer(),boardHandler.getTextView());
         }
        super.onRestoreInstanceState(savedInstanceState);
    }


    public void initializeBoard()
    {
        playerHandler=new PlayerHandler();
        boardHandler=new BoardHandler();
    }

    public void setupBoard(char p,char[] tv)
    {
        TextView []textViews=new TextView[9];

        TextView playerTurn= findViewById(R.id.textView9);
        playerTurn.setText("Now Player "+playerHandler.getPlayer()+"\'s  Turn");
            textViews[0]=findViewById(R.id.textView0);
            textViews[1]=findViewById(R.id.textView1);
            textViews[2]=findViewById(R.id.textView2);
            textViews[3]=findViewById(R.id.textView3);
            textViews[4]=findViewById(R.id.textView4);
            textViews[5]=findViewById(R.id.textView5);
            textViews[6]=findViewById(R.id.textView6);
            textViews[7]=findViewById(R.id.textView7);
            textViews[8]=findViewById(R.id.textView8);
        for(int i=0;i<9;i++)
        {
            if(tv[i]=='E')
            {
              textViews[i].setTextColor(textViews[i].getDrawingCacheBackgroundColor());
                textViews[i].setText("E");
            }else
            {
                textViews[i].setText(""+tv[i]);
               textViews[i].setTextColor(Color.GREEN);
            }
        }
   }

    public void resetBoard(View view){
   // Log.i("textView = ",""+c++);
        this.initializeBoard();
        setupBoard(playerHandler.getPlayer(),boardHandler.getTextView());
}
public boolean isEmptySlot(int a, int b)
{
    //(condition)? expressionTrue: expressionFalse;
 return a==b?true:false;
}

public void showAlertDailogue(String title, String message) throws InterruptedException {

    AlertDialog alertDialog = new AlertDialog.Builder(this).create();
    alertDialog.setTitle(title);
    alertDialog.setMessage(message);
    alertDialog.setCanceledOnTouchOutside(true);
alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
    @Override
    public void onCancel(DialogInterface dialog) {
        initializeBoard();
        setupBoard(playerHandler.getPlayer(),boardHandler.getTextView());
    }
});{

    };

    alertDialog.setIcon(R.drawable.winnerlogo);

    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getApplicationContext(), "Welcome Again..!\n Player X's Turn", Toast.LENGTH_SHORT).show();
            initializeBoard();
            setupBoard(playerHandler.getPlayer(),boardHandler.getTextView());
        }
    });

    alertDialog.show();
   // initializeBoard();
}

}