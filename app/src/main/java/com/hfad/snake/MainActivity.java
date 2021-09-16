package com.hfad.snake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Rectangle;

import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button buttonStart;
    Button buttonUp,buttonDown,buttonRight,buttonLeft;
    ImageView imageSnakeBody;       //snake
    ObjectAnimator animationUp,animationDown,animationRight,animationLeft;
    ImageView imageFood;       //food
    char previousMove = 'n';                                          //it stores the previous move and is used to track the previous move so curresponding animation is deleted
    int snakeLocationX = 0 , snakeLocationY = 0;       //locates snake
    int foodX = 0,foodY=0;  //food Location
    int chance = 0 ; //if x coordinates of snake and food match chance is incremented same if y match if chance becomes 2 reset to 0 ,snakegrow,resetfoodlocation
    int snakeSpeed = 2000;  // speed of snake
    int[] foodPos = new int[2];//stores food position

   public void stop(View view){
       //cancelPreviousMove();
       food();
    }

    public  void start(View view){
       buttonStart.setVisibility(View.INVISIBLE);
        buttonUp.setVisibility(View.VISIBLE);
        buttonDown.setVisibility(View.VISIBLE);
        buttonRight.setVisibility(View.VISIBLE);
        buttonLeft.setVisibility(View.VISIBLE);
        timer();        //timer that checks for snake to eat food
        food();                 //sets food location for the first time


    }
    public void up(View view){                                  //up button
       // Log.i("info", "up: ");

         animationUp = ObjectAnimator.ofFloat(imageSnakeBody, "translationY", -500f);  //moving animation
        animationUp.setDuration(snakeSpeed);
        cancelPreviousMove();
        previousMove = 'u';
        animationUp.start();


    }
    public void down(View view){                                //down button
       // Log.i("info", "down: ");
        animationDown = ObjectAnimator.ofFloat(imageSnakeBody, "translationY", 500f);  //moving animation
        animationDown.setDuration(snakeSpeed);
        cancelPreviousMove();
        previousMove = 'd';
        animationDown.start();
        //imageSnakeBody.animate().translationY(1000).setDuration(4000);
    }
    public void right(View view){                               //right button
       // Log.i("info", "right: ");
         animationRight = ObjectAnimator.ofFloat(imageSnakeBody, "translationX", 1000f);  //moving animation
        animationRight.setDuration(snakeSpeed);
        cancelPreviousMove();
        previousMove = 'r';
        animationRight.start();
        // imageSnakeBody.animate().translationX(1000).setDuration(4000);  //Moving right
    }
    public void left(View view){  //left button

        //Log.i("info", "left: ");
        animationLeft = ObjectAnimator.ofFloat(imageSnakeBody, "translationX", -200f);  //moving animation
        animationLeft.setDuration(snakeSpeed);
        cancelPreviousMove();
        previousMove = 'l';
        animationLeft.start();
        // imageSnakeBody.animate().translationX(-1000).setDuration(4000);  //Moving left


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = findViewById(R.id.buttonStart);
        imageSnakeBody = findViewById(R.id.imageSnakeBody);
        buttonUp = findViewById(R.id.buttonUp);
        buttonDown = findViewById(R.id.buttonDown);
        buttonRight = findViewById(R.id.buttonRight);
        buttonLeft = findViewById(R.id.buttonLeft);
        imageFood = findViewById(R.id.imageFood);
      /*  int[] foodPos = new int[2];  //stores initial position of food at the start of the game
        imageFood.getLocationOnScreen(foodPos);
        foodX = foodPos[0];
        foodY = foodPos[1];*/

    } // on create
    public void cancelPreviousMove(){                               //this cancels the previous animation caused by previous move
        Log.i("p : ",Character.toString(previousMove));
        if(previousMove =='u'){
            animationUp.cancel();
        }else if(previousMove =='d'){
            animationDown.cancel();
        }else if(previousMove =='r'){
            animationRight.cancel();
        }else if(previousMove =='l'){
            animationLeft.cancel();
        }
    }
    public void  locate(){  // locate snake

            int[] location = new int[2];
            imageSnakeBody.getLocationOnScreen(location);
            snakeLocationX = location[0];
            snakeLocationY = location[1];
    }
    public void timer(){                                                //timer checks for snake eating food
        new CountDownTimer(600000,100){

            @Override
            public void onTick(long millisUntilFinished) {
                locate();
                locateFood();
                Log.i("snake",Integer.toString(snakeLocationX)+" + "+Integer.toString(snakeLocationY)); // prints location
                if(foodX <= snakeLocationX+10 && foodX >= snakeLocationX-10){
                    chance++;
                }if(foodY <= snakeLocationY+10 && foodY >= snakeLocationY-10){
                    chance++;
                    Log.i("chance",Integer.toString(chance));
                }
                if(chance == 2){
                    //snake collides with food

                    food();
                    chance = 0;
                }

            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
    public void food(){                 //creating food
        Random random = new Random();
        foodX = random.nextInt(500);
        foodY = random.nextInt(800);

        //foodY=50;
        imageFood.setTranslationX(foodX);
        imageFood.setTranslationY(foodY);
        Log.i("food",Integer.toString(foodX)+" + "+ Integer.toString(foodY)); //foodpos
   }

   public void locateFood(){
       imageFood.getLocationOnScreen(foodPos);
       foodX = foodPos[0];
       foodY = foodPos[1];
       Log.i("pos",Integer.toString(foodX)+" + "+ Integer.toString(foodY)); //foodpos
   }
  public  void  generateBody() { //generates body

  }
}//mainActivity