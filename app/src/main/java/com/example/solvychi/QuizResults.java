package com.example.solvychi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuizResults extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);
        getSupportActionBar().hide();
         AppCompatButton startnewBtn = findViewById(R.id.btnstartnext);
         TextView correctAnswer = findViewById(R.id.correctAnswers);
         TextView updated = (TextView) findViewById(R.id.up);

         TextView incorrectAnswer = findViewById(R.id.incorrectAnswers);
         ImageView back = findViewById(R.id.backBtn);
         int getCorrectAnsewrs = getIntent().getIntExtra("correct",0);
         String email = getIntent().getStringExtra("email");

         int getIncorrectAnsewrs = getIntent().getIntExtra("incorrect",0);
        correctAnswer.setText("Correct answers :"+String.valueOf(getCorrectAnsewrs));
        incorrectAnswer.setText("Incorrect answers:"+String.valueOf(getIncorrectAnsewrs));
//        updated.setText("updated:"+getIntent().getBooleanExtra("updated",false));


            startnewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                             Intent backToHome = new Intent(QuizResults.this ,QuizActivity.class);
//                             backToHome.putExtra("result", true);

                                startActivity(backToHome);


                   finish();

                }
            });





        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              onBackPressed();
            }
        });

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(QuizResults.this ,QuizActivity.class));

    }
}