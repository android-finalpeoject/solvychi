package com.example.solvychi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView answers, userAnswer;
    private ImageView question;
    private int presCounter = 0;
    private AppCompatButton nextBtn;
    private List<GameObject> quizList ;
    private int currentQuestionPosition = 0;
    private String userAns="";
    Animation smallbigforth;
    private String [] keys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();
//        //==========animation========================
        smallbigforth = AnimationUtils.loadAnimation(this, R.anim.smallbigforth);
//        //==============get our activity elements===============
        final ImageView backBtn = findViewById(R.id.backBtn);
        answers = findViewById(R.id.answers);
        question = findViewById(R.id.quiz_image);
        userAnswer = findViewById(R.id.userAnswer);
        nextBtn = findViewById(R.id.nextBtn);
        final TextView selectedLevelName = findViewById(R.id.levelname);
//
        final String getSelectedLevelName= getIntent().getStringExtra("selectedLevel");
        selectedLevelName.setText(getSelectedLevelName);
        quizList = GameBank.getQuiz(getSelectedLevelName);
        answers.setText((currentQuestionPosition+1)+"/"+quizList.size());
        question.setImageResource(quizList.get(0).getQuizImage());
        // to affect for each textView its letter:j
        keys = shuffleArray(quizList.get(0).getKeys());

        for(String key : keys)
        {
            addView(((LinearLayout) findViewById(R.id.layoutParent)), key, ((EditText) findViewById(R.id.userAnswer)));
        }
        //=====================NEXT BTN EVENT==============================
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAns = userAnswer.getText().toString();
                if(userAns.isEmpty()){
                    Toast.makeText(GameActivity.this , "Please compose the answer ",Toast.LENGTH_SHORT).show();

                }
                else{
                    changeNextQuestion();
                }

            }
        });
        //=======================BACK BTN EVENT====================
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(GameActivity.this, QuizActivity.class));
                finish();
            }
        });

    }
    //===========================game methods==============================
    //============shuffle fct=================
    private String[] shuffleArray(String[] ar)
    {
        Random rnd = new Random();
        for(int i= ar.length-1;i>0; i--)
        {
            int index = rnd.nextInt(i+1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }
    //============addView fct=================
    @SuppressLint("UseCompatLoadingForDrawables")
    private void addView(LinearLayout viewParent, final String text, final EditText userAnswer)
    {
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        linearLayoutParams.rightMargin = 20;
        final TextView textView = new TextView(this);
        textView.setLayoutParams(linearLayoutParams);
        textView.setBackground(this.getResources().getDrawable(R.drawable.blue_btns));
        textView.setTextColor(this.getResources().getColor(R.color.blue));
        textView.setGravity((Gravity.CENTER));
        textView.setText(text);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTextSize(32);


        textView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                int maxPresCounter = quizList.get(currentQuestionPosition).getMaxCounter();
                if(presCounter < maxPresCounter)
                {
                    if(presCounter == 0)
                        userAnswer.setText("");

                    userAnswer.setText(userAnswer.getText().toString()+text);
                    textView.startAnimation(smallbigforth);
                    textView.animate().alpha(0).setDuration(300);
                    presCounter++;
                    if( presCounter == maxPresCounter)
                        doValidate();

                }
            }
        });
        viewParent.addView(textView);




    }

    // ================doValidate fct=============
    private void doValidate(){
        presCounter = 0;
        userAns = userAnswer.getText().toString();
        LinearLayout linearLayout = findViewById(R.id.layoutParent);
        String right =  quizList.get(currentQuestionPosition).getCorrectAnswer();
        if(userAns.equals(right))
        {
            Toast.makeText(GameActivity.this, "Correct 🤩" ,Toast.LENGTH_SHORT).show();
//            userAnswer.setText("");
            quizList.get(currentQuestionPosition).setUserAns(userAns);
//            userAns="";
//            Intent a = new Intent( MainActivity.this, BossAct.class);
//            startActivity(a);
        }
        else
        {
//            Toast.makeText(MainActivity.this, "Wrong 😓" ,Toast.LENGTH_SHORT).show();
//
////            editText.setText(textAnswer);
            //Creating the Toast object

            Toast toast = new Toast(GameActivity.this);
            toast.setText("✔ the right answer is :"+right);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            userAnswer.setBackgroundResource(R.drawable.wrong_answer);
        }
//
//        keys = shuffleArray(keys);
//        linearLayout.removeAllViews();
//        for(String key :keys )
//        {
//            addView(linearLayout, key, editText);
//        }
    }
    //=============================questions methods=========================
    private void changeNextQuestion(){
        LinearLayout linearLayout = findViewById(R.id.layoutParent);
        userAnswer.setBackgroundResource(R.drawable.dark_blue);
        currentQuestionPosition++;
        if((currentQuestionPosition+1)== quizList.size()){
            nextBtn.setText("Submit Quiz");
        }
        if(currentQuestionPosition<quizList.size()){

            userAnswer.setText("");
            answers.setText((currentQuestionPosition+1)+"/"+quizList.size());
            question.setImageResource(quizList.get(currentQuestionPosition).getQuizImage());
            keys = shuffleArray(quizList.get(currentQuestionPosition).getKeys());
            linearLayout.removeAllViews();
            for(String key : keys)
            {
                addView(((LinearLayout) findViewById(R.id.layoutParent)), key, ((EditText) findViewById(R.id.userAnswer)));
            }

        }
        else{
            Intent intent = new Intent(GameActivity.this,QuizResults.class);
            intent.putExtra("correct",getCorrectAnswers());
            intent.putExtra("incorrect",getIncorrectAnswers());
            startActivity(intent);
            finish();
        }
    }


    private int getCorrectAnswers(){
        int correctAnswers = 0;
        for (int i=0 ; i<quizList.size();i++){
            final String getUserAnswer = quizList.get(i).getUserAns();
            final String getAnswer = quizList.get(i).getCorrectAnswer();
            if(getUserAnswer.equals(getAnswer)){
                correctAnswers++;
            }
        }
        return correctAnswers;
    };

    private int getIncorrectAnswers(){
        int correctAnswers = 0;
        for (int i=0 ; i<quizList.size();i++){
            final String getUserAnswer = quizList.get(i).getUserAns();
            final String getAnswer = quizList.get(i).getCorrectAnswer();
            if(!getUserAnswer.equals(getAnswer)){
                correctAnswers++;
            }
        }
        return correctAnswers;
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(GameActivity.this , QuizActivity.class));
        finish();
    }
}