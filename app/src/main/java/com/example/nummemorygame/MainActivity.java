package com.example.nummemorygame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textView1,textView2,textView3;

    TextView textView11, textView12,textView13,
             textView21, textView22,textView23,
             textView31, textView32,textView33,
             textView41, textView42,textView43;

    //array for images.
    Integer[] cardsArray={11,12,13,14,15,16,21,22,23,24,25,26};
    //actual image
    int text11,text12,text13,text21,text22,text23,
            text31,text32,text33,text41,text42,text43;

    //used for comparison of card "value" to see if its identical
    int firstCard, secondCard;
    //used to find which is the actual clicked "card"
    int clickedFirst, clickedSecond;
    //used to remember which image exactly should be flipped back
    TextView card1Iv, card2Iv;

    //to know whether current pick is first or second pick
    int cardNumber=1;
    //solved cards, 12 in total
    Integer[] solvedPairs=new Integer[12];
    int matches = 0;
    int steps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        textView1.setText("0/6 Matches");
        textView2 = findViewById(R.id.textView2);
        textView2.setText("Steps : 0 ");
        textView3 = findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        textView11 = findViewById(R.id.textView11);
        textView11.setOnClickListener(this);
        textView12 = findViewById(R.id.textView12);
        textView12.setOnClickListener(this);
        textView13 = findViewById(R.id.textView13);
        textView13.setOnClickListener(this);
        textView21 = findViewById(R.id.textView21);
        textView21.setOnClickListener(this);
        textView22 = findViewById(R.id.textView22);
        textView22.setOnClickListener(this);
        textView23 = findViewById(R.id.textView23);
        textView23.setOnClickListener(this);
        textView31 = findViewById(R.id.textView31);
        textView31.setOnClickListener(this);
        textView32 = findViewById(R.id.textView32);
        textView32.setOnClickListener(this);
        textView33 = findViewById(R.id.textView33);
        textView33.setOnClickListener(this);
        textView41 = findViewById(R.id.textView41);
        textView41.setOnClickListener(this);
        textView42 = findViewById(R.id.textView42);
        textView42.setOnClickListener(this);
        textView43 = findViewById(R.id.textView43);
        textView43.setOnClickListener(this);

        textView11.setTag("0");
        textView12.setTag("1");
        textView13.setTag("2");
        textView21.setTag("3");
        textView22.setTag("4");
        textView23.setTag("5");
        textView31.setTag("6");
        textView32.setTag("7");
        textView33.setTag("8");
        textView41.setTag("9");
        textView42.setTag("10");
        textView43.setTag("11");

        frontOfCards();
        Collections.shuffle(Arrays.asList(cardsArray));
    }


    @Override
    public void onClick(View v) {
        int chosenCard = Integer.parseInt((String)v.getTag());
        gameLogic((TextView)v,chosenCard);
        steps++;
        String s = "Steps: "+ steps;
        textView2.setText(s);

    }

    public void  frontOfCards(){
        int[] randomNumberArray = getRandomNumberArray();
        text11 = getRandomNumber(randomNumberArray,0);
        text12 = getRandomNumber(randomNumberArray,1);
        text13 = getRandomNumber(randomNumberArray,2);
        text21 = getRandomNumber(randomNumberArray,3);
        text22 = getRandomNumber(randomNumberArray,4);
        text23 = getRandomNumber(randomNumberArray,5);
        text31 = getRandomNumber(randomNumberArray,0);
        text32 = getRandomNumber(randomNumberArray,1);
        text33 = getRandomNumber(randomNumberArray,2);
        text41 = getRandomNumber(randomNumberArray,3);
        text42 = getRandomNumber(randomNumberArray,4);
        text43 = getRandomNumber(randomNumberArray,5);

    }

    private int getRandomNumber(int[] randomArray,int numbering)
    {
        int result;
        result = randomArray[numbering];
        return  result;
    }

    private int[] getRandomNumberArray()
    {
        int[] resultArray = new int[6];
        int count = 0;
        while(count < 6) {
            int num = (int) (Math.random() * (100 - 1)) + 1;
            boolean flag = true;
            for (int j = 0; j < 6; j++) {
                if(num == resultArray[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                resultArray[count] = num;
                count++;
            }
        }
        return resultArray;
    }

    private void gameLogic(TextView iv, int card){
        //set and show correct images on screen
        if(cardsArray[card]==11){
            iv.setBackgroundColor(Color.GRAY);
            iv.setText(Integer.toString(text11));
        }else if(cardsArray[card]==12){
            iv.setBackgroundColor(Color.GRAY);
            iv.setText(Integer.toString(text12));
        }else if(cardsArray[card]==13){
            iv.setBackgroundColor(Color.GRAY);
            iv.setText(Integer.toString(text13));
        }else if(cardsArray[card]==14){
            iv.setBackgroundColor(Color.GRAY);
            iv.setText(Integer.toString(text21));
        }else if(cardsArray[card]==15){
            iv.setBackgroundColor(Color.GRAY);
            iv.setText(Integer.toString(text22));
        }else if(cardsArray[card]==16){
            iv.setBackgroundColor(Color.GRAY);
            iv.setText(Integer.toString(text23));
        }else if(cardsArray[card]==21){
            iv.setBackgroundColor(Color.GRAY);
            iv.setText(Integer.toString(text31));
        }else if(cardsArray[card]==22){
            iv.setBackgroundColor(Color.GRAY);
            iv.setText(Integer.toString(text32));
        }else if(cardsArray[card]==23){
            iv.setBackgroundColor(Color.GRAY);
            iv.setText(Integer.toString(text33));
        }else if(cardsArray[card]==24){
            iv.setBackgroundColor(Color.GRAY);
            iv.setText(Integer.toString(text41));
        }else if(cardsArray[card]==25){
            iv.setBackgroundColor(Color.GRAY);
            iv.setText(Integer.toString(text42));
        }else if(cardsArray[card]==26){
            iv.setBackgroundColor(Color.GRAY);
            iv.setText(Integer.toString(text43));
        }

        //"Remember" choice pair of player
        if(cardNumber==1){
            firstCard=cardsArray[card];
            if(firstCard>20){
                firstCard = firstCard-10;
            }
            cardNumber=2;
            clickedFirst=card;
            iv.setEnabled(false);
            card1Iv=iv;
        }else if(cardNumber==2) {
            secondCard = cardsArray[card];
            if (secondCard > 20) {
                secondCard = secondCard - 10;
            }
            cardNumber = 1;
            clickedSecond = card;
            card2Iv=iv;

            //after player picks a pair:
            //temporarily disable all press actions to resolve current pair
            textView11.setEnabled(false);
            textView12.setEnabled(false);
            textView13.setEnabled(false);
            textView21.setEnabled(false);
            textView22.setEnabled(false);
            textView23.setEnabled(false);
            textView31.setEnabled(false);
            textView32.setEnabled(false);
            textView33.setEnabled(false);
            textView41.setEnabled(false);
            textView42.setEnabled(false);
            textView43.setEnabled(false);

            //show pair images for awhile if wrong "flip back"
            //if correct, leave them there.
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    check();
                }
            }, 1000);
        }
    }

    //add to solved pairs array
    private void solved(int first, int second){
        solvedPairs[matches*2]=first;
        solvedPairs[matches*2+1]=second;
    }
    private void check(){
        if(firstCard == secondCard){

            solved(clickedFirst, clickedSecond);

            //update solved pairs
            matches++;
            textView1.setText(matches+"/6 Matches");
        } else{
            //flip the chosen cards back as the pair is not identical
            card1Iv.setBackgroundColor(Color.WHITE);
            card1Iv.setText("?");
            card2Iv.setBackgroundColor(Color.WHITE);
            card2Iv.setText("?");
        }
        //check if game ended. If not then reactivate pairs not solved
        checkEnd();
        //re-enable all the pairs that haven't been solved
        if(!solvedHas(0)) {
            textView11.setEnabled(true);
        }
        if(!solvedHas(1)) {
            textView12.setEnabled(true);
        }
        if(!solvedHas(2)) {
            textView13.setEnabled(true);
        }
        if(!solvedHas(3)) {
            textView21.setEnabled(true);
        }
        if(!solvedHas(4)) {
            textView22.setEnabled(true);
        }
        if(!solvedHas(5)) {
            textView23.setEnabled(true);
        }
        if(!solvedHas(6)) {
            textView31.setEnabled(true);
        }
        if(!solvedHas(7)) {
            textView32.setEnabled(true);
        }
        if(!solvedHas(8)) {
            textView33.setEnabled(true);
        }
        if(!solvedHas(9)) {
            textView41.setEnabled(true);
        }
        if(!solvedHas(10)) {
            textView42.setEnabled(true);
        }
        if(!solvedHas(11)) {
            textView43.setEnabled(true);
        }
    }

    //check if card is solved
    private boolean solvedHas(int x){
        for(int i=0;i<=11;i++){
            if(solvedPairs[i]!=null) {
                if (solvedPairs[i] == x) {
                    return true;
                }
            }
        }
        return false;
    }

    private void checkEnd(){
        if(matches==6){
            AlertDialog.Builder alertDialogueBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogueBuilder
                    .setMessage("Great Work! " )
                    .setCancelable(false)
                    .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
            AlertDialog alertDialog = alertDialogueBuilder.create();
            alertDialog.show();
        }
    }
}
