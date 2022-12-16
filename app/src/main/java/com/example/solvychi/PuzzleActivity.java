package com.example.solvychi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PuzzleActivity extends AppCompatActivity {

    private ImageView pieces[], piece1,piece2,piece3;
    private ImageView puzzle, backBtn;
    private TextView answers;
    private AppCompatButton nextBtn;
    private List<puzzle> puzzleList ;
    private boolean chosen;
    private int currentQuestionPosition ,correct,wrong,result=0,userAnswer;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);
        getSupportActionBar().hide();
 //==============get our activity elements===============
        backBtn = findViewById(R.id.backBtn);
        answers = findViewById(R.id.answers);
        puzzle = findViewById(R.id.quiz_image);
        nextBtn = findViewById(R.id.nextBtn);
        final TextView selectedLevelName = findViewById(R.id.levelname);

        //====================get puzzle pieces ================
        piece1 = findViewById(R.id.choice1);
        piece2 = findViewById(R.id.choice2);
        piece3 = findViewById(R.id.choice3);
        pieces = new ImageView[]{piece1, piece2, piece3};
//=============get bundle data============================
        Bundle data = getIntent().getExtras();
        email = data.getString("email");
        final String getSelectedLevelName= data.getString("selectedLevel");
        selectedLevelName.setText(getSelectedLevelName);
        puzzleList = puzzleBank.getPuzzle();
        answers.setText((currentQuestionPosition+1)+"/"+puzzleList.size());
//        puzzle.setImageResource(puzzleList.get(0).getQuizImage());


     //============================Add puzzle pieces========================
        addView(pieces,puzzle);
        //=====================NEXT BTN EVENT==============================
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(chosen==false){
                    Toast.makeText(PuzzleActivity.this , "Please choose an answer ",Toast.LENGTH_SHORT).show();

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
                Intent backToHome = new Intent(getApplicationContext() ,QuizActivity.class);
                Bundle B = new Bundle();
                B.putString("email", getIntent().getStringExtra("email"));
                backToHome.putExtras(B);
                startActivity(backToHome);
            }
        });
    }

    //============addView fct=================
    @SuppressLint("UseCompatLoadingForDrawables")
    private void addView(ImageView [] pieces, final ImageView puzzle)
    {
        //==========================set puzzle image==================================
        puzzle.setBackgroundResource(puzzleList.get(currentQuestionPosition).getQuizImage());
       //=========================set puzzle pieces=================================
        for(int i=0;i<pieces.length;i++)
        {
            int piece = puzzleList.get(currentQuestionPosition).getPieces()[i];
            pieces[i].setBackgroundResource(piece);


        pieces[i].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                chosen = true;

                        doValidate(piece,puzzle);
            }
        });
        }

    }

    // ================doValidate fct=============
    private void doValidate(int userAnswer, final ImageView puzzle){

        int right =  puzzleList.get(currentQuestionPosition).getCorrectAnswer();


        if(userAnswer == right)
        {
            Toast.makeText(PuzzleActivity.this, "Correct 🤩" ,Toast.LENGTH_SHORT).show();
            puzzleList.get(currentQuestionPosition).setUserAns(userAnswer);

        }
        else
        {
            Toast.makeText(PuzzleActivity.this, "wrong 😔" ,Toast.LENGTH_SHORT).show();
        }
        //==========================set completed puzzle image==================================
        puzzle.setBackgroundResource(puzzleList.get(currentQuestionPosition).getCompleted());
    }
    //=============================questions methods=========================
    private void changeNextQuestion(){

        currentQuestionPosition++;
        if((currentQuestionPosition+1)== puzzleList.size()){
            nextBtn.setText("Submit Quiz");
        }
        if(currentQuestionPosition<puzzleList.size()){


            answers.setText((currentQuestionPosition+1)+"/"+puzzleList.size());
//            puzzle.setImageResource(puzzleList.get(currentQuestionPosition).getQuizImage());

                addView(pieces, puzzle);


        }
        else{
            DBHelper db = new DBHelper(this);
            correct = getCorrectAnswers();
            wrong = getIncorrectAnswers();
            int change;
            boolean updated=false;

            String newLevel="" ,prev="";
            result = puzzleList.size() - wrong;
            if (result >= 3) {
                prev= db.fetchLevel(email);

                change =Integer.parseInt(prev);
                if(change<4)
                {
                    change=change+1;
                    newLevel = String.valueOf(change);
                    try{
                        updated = db.alterLevel(email,newLevel);
                    }catch(Exception e){
                        Toast.makeText(PuzzleActivity.this,e.getMessage(),Toast.LENGTH_LONG);
                    }
                }
            }
            Intent intent = new Intent(PuzzleActivity.this, QuizResults.class);

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
        for (int i=0 ; i<puzzleList.size();i++){
            final int getUserAnswer = puzzleList.get(i).getUserAns();
            final int getAnswer = puzzleList.get(i).getCorrectAnswer();
            if(getUserAnswer==getAnswer){
                correctAnswers++;
            }
        }
        return correctAnswers;
    };

    private int getIncorrectAnswers(){
        int correctAnswers = 0;
        for (int i=0 ; i<puzzleList.size();i++){
            final int getUserAnswer = puzzleList.get(i).getUserAns();
            final int getAnswer = puzzleList.get(i).getCorrectAnswer();
            if(getUserAnswer!=getAnswer){
                correctAnswers++;
            }
        }
        return correctAnswers;
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(PuzzleActivity.this , QuizActivity.class));
        finish();
    }


}