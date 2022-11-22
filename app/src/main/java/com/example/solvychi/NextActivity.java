package com.example.solvychi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class NextActivity extends AppCompatActivity {
    private TextView texte;
    private TextView texte2;
    private ImageView image;
    private RelativeLayout layout;
    private ImageView nextBtn;
    private int currentPosition = 0;
    private List<QuestionsList> questionsLists ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        getSupportActionBar().hide();
        texte = findViewById(R.id.firsttext);
        texte2 = findViewById(R.id.secondtext);
        final ImageView backBtn = findViewById(R.id.next);
        image = findViewById(R.id.firstimage);
        nextBtn =findViewById(R.id.back);
        layout = findViewById(R.id.viewnext);
        questionsLists = QuestionsBank.getQuestions();
        image.setImageResource(questionsLists.get(0).getImage());
        texte.setText(questionsLists.get(0).getTexte());
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeNextQuestion();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentPosition--;
                if((currentPosition)== 0){
                    Intent intent = new Intent(NextActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(currentPosition<questionsLists.size()){

                    image.setImageResource(questionsLists.get(currentPosition).getImage());
                    texte.setText(questionsLists.get(currentPosition).getTexte());
                    texte2.setText(questionsLists.get(currentPosition).getTexte2());
                    layout.setBackgroundResource(questionsLists.get(currentPosition).getBackground());
                }

            }
        });


    }
    private void changeNextQuestion(){
        currentPosition++;
        if((currentPosition+1)== questionsLists.size()){
        }
        if(currentPosition<questionsLists.size()){
            image.setImageResource(questionsLists.get(currentPosition).getImage());
            texte.setText(questionsLists.get(currentPosition).getTexte());
            texte2.setText(questionsLists.get(currentPosition).getTexte2());
            layout.setBackgroundResource(questionsLists.get(currentPosition).getBackground());
        }
        else{
            Intent intent = new Intent(NextActivity.this,finishactivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(NextActivity.this , MainActivity.class));
        finish();
    }

}