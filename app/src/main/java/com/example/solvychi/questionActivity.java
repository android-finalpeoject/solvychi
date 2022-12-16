package com.example.solvychi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class questionActivity extends AppCompatActivity {
    private TextView questions;
    private ImageView question;
    private AppCompatButton option1, option2, option3, option4 ;
    private AppCompatButton nextBtn;
    private List<QuestionsList> questionsLists ;
    private int currentQuestionPosition = 0,correct,wrong,result=0;
    private ImageView backBtn;
    private String selectedOptionByUser="",email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        getSupportActionBar().hide();

        backBtn = findViewById(R.id.backBtn);
        questions = findViewById(R.id.questions);
        question = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextBtn = findViewById(R.id.nextBtn);
        final TextView selectedLevelName = findViewById(R.id.levelname);

        Bundle data = getIntent().getExtras();
        email = data.getString("email");
        final String getSelectedLevelName = data.getString("selectedLevel");
        selectedLevelName.setText(getSelectedLevelName);
        questionsLists = QuestionsBank.getQuestions(getSelectedLevelName);
        questions.setText((currentQuestionPosition + 1) + "/" + questionsLists.size());
        question.setImageResource(questionsLists.get(0).getQuestion());
        option1.setText(questionsLists.get(0).getOption1());
        option2.setText(questionsLists.get(0).getOption2());
        option3.setText(questionsLists.get(0).getOption3());
        option4.setText(questionsLists.get(0).getOption4());

        //=============== DB get user===========

        try {



            option1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (selectedOptionByUser.isEmpty()) {
                        selectedOptionByUser = option1.getText().toString();
                        option1.setBackgroundResource(R.drawable.round_back_red10);
                        option1.setTextColor(Color.parseColor("#000000"));
                        revealAnswer();
                        questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);


                    }
                }
            });
            option2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (selectedOptionByUser.isEmpty()) {
                        selectedOptionByUser = option2.getText().toString();
                        option2.setBackgroundResource(R.drawable.round_back_red10);
                        option2.setTextColor(Color.parseColor("#000000"));
                        revealAnswer();
                        questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);


                    }
                }
            });
            option3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (selectedOptionByUser.isEmpty()) {
                        selectedOptionByUser = option3.getText().toString();
                        option3.setBackgroundResource(R.drawable.round_back_red10);
                        option3.setTextColor(Color.parseColor("#000000"));
                        revealAnswer();
                        questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);


                    }

                }
            });
            option4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (selectedOptionByUser.isEmpty()) {
                        selectedOptionByUser = option4.getText().toString();
                        option4.setBackgroundResource(R.drawable.round_back_red10);
                        option4.setTextColor(Color.parseColor("#000000"));
                        revealAnswer();
                        questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);


                    }

                }
            });
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (selectedOptionByUser.isEmpty()) {
                        Toast.makeText(questionActivity.this, "Please select an option ", Toast.LENGTH_SHORT).show();

                    } else {
                        changeNextQuestion();

                    }

                }
            });
            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onBackPressed();

                }
            });
            //====================change level value=========================


        }catch (Exception e)
        {
            System.out.println(e.getCause());
        }


    }
    private void changeNextQuestion(){
        currentQuestionPosition++;
        if((currentQuestionPosition+1)== questionsLists.size()){
            nextBtn.setText("Submit Quiz");
        }
        if(currentQuestionPosition<questionsLists.size()){
            selectedOptionByUser="";
            option1.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option1.setTextColor(Color.parseColor("#000000"));
            option2.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option2.setTextColor(Color.parseColor("#000000"));
            option3.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option3.setTextColor(Color.parseColor("#000000"));
            option4.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option4.setTextColor(Color.parseColor("#000000"));
            questions.setText((currentQuestionPosition+1)+"/"+questionsLists.size());
            question.setImageResource(questionsLists.get(currentQuestionPosition).getQuestion());
            option1.setText(questionsLists.get(currentQuestionPosition).getOption1());
            option2.setText(questionsLists.get(currentQuestionPosition).getOption2());
            option3.setText(questionsLists.get(currentQuestionPosition).getOption3());
            option4.setText(questionsLists.get(currentQuestionPosition).getOption4());
        }
     else
        {

                DBHelper db = new DBHelper(this);
                correct = getCorrectAnswers();
                wrong = getIncorrectAnswers();
                int change;
                boolean updated=false;

                String newLevel="" ,prev="";
                result = questionsLists.size() - wrong;
                if (result >= 4) {
                    prev= db.fetchLevel(email);
                    change =Integer.parseInt(prev);
                    change=change+1;
                    newLevel = String.valueOf(change);
                   try{
                       updated = db.alterLevel(email,newLevel);
                   }catch(Exception e){
                              Toast.makeText(questionActivity.this,e.getMessage(),Toast.LENGTH_LONG);
                   }
                }
                Intent intent = new Intent(questionActivity.this, QuizResults.class);

                intent.putExtra("correct", correct);
                intent.putExtra("incorrect", wrong);
                intent.putExtra("email", email);
                intent.putExtra("updated", updated);
                startActivity(intent);
                finish();
        }

    }


    private int getCorrectAnswers(){
        int correctAnswers = 0;
        for (int i=0 ; i<questionsLists.size();i++){
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();
            if(getUserSelectedAnswer !=null && getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;
            }
        }
        return correctAnswers;
    };

    private int getIncorrectAnswers(){
        int correctAnswers = 0;
        for (int i=0 ; i<questionsLists.size();i++){
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();
            if(getUserSelectedAnswer !=null && !getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;
            }
        }
        return correctAnswers;
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent backToHome = new Intent(getApplicationContext() ,QuizActivity.class);
        Bundle B = new Bundle();
        B.putString("email", getIntent().getStringExtra("email"));
        backToHome.putExtras(B);
        startActivity(backToHome);

    }
    private void revealAnswer(){
        final String getAnswer = questionsLists.get(currentQuestionPosition).getAnswer();
        if(option1.getText().toString().equals(getAnswer)){
            option1.setBackgroundResource(R.drawable.round_back_green10);
            option1.setTextColor(Color.BLACK);
        }
        if(option2.getText().toString().equals(getAnswer)){
            option2.setBackgroundResource(R.drawable.round_back_green10);
            option2.setTextColor(Color.BLACK);
        }
        if(option3.getText().toString().equals(getAnswer)){
            option3.setBackgroundResource(R.drawable.round_back_green10);
            option3.setTextColor(Color.BLACK);
        }
        if(option4.getText().toString().equals(getAnswer)){
            option4.setBackgroundResource(R.drawable.round_back_green10);
            option4.setTextColor(Color.BLACK);
        }


    }
}