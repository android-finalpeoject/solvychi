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
        final AppCompatButton startnewBtn = findViewById(R.id.btnstartnext);
        final TextView correctAnswer = findViewById(R.id.correctAnswers);
        final TextView incorrectAnswer = findViewById(R.id.incorrectAnswers);
        final ImageView back = findViewById(R.id.backBtn);
        final int getCorrectAnsewrs = getIntent().getIntExtra("correct",0);
        final String email = getIntent().getStringExtra("email");

        final int getIncorrectAnsewrs = getIntent().getIntExtra("incorrect",0);
        correctAnswer.setText("Correct answers :"+String.valueOf(getCorrectAnsewrs));
        incorrectAnswer.setText("Incorrect answers:"+String.valueOf(getIncorrectAnsewrs));
        DBHelper db = new DBHelper(this);
            startnewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {

                             Intent backToHome = new Intent(QuizResults.this ,QuizActivity.class);
                             backToHome.putExtra("result", true);
                             startActivity(backToHome);
//                    finish();
                    }catch (Exception e)
                    {
                      Toast.makeText(QuizResults.this,e.getMessage(),Toast.LENGTH_LONG);
                    }
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
        finish();
    }
}