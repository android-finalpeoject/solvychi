package com.example.solvychi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private String selectedLevelName;
    private AppCompatButton btnlevel1, btnlevel2, btnlevel3, btnlevel4 ;
    private TextView user;
    private String userName, email,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().hide();
        final LinearLayout level1 = findViewById(R.id.level1);
        final LinearLayout level2 = findViewById(R.id.level2);
        final LinearLayout level3 = findViewById(R.id.layout3);
        final LinearLayout level4 = findViewById(R.id.layout4);

        btnlevel1 =findViewById(R.id.btnlevel1);
        btnlevel2 =findViewById(R.id.btnlevel2);
        btnlevel3 =findViewById(R.id.btnlevel3);
        btnlevel4 =findViewById(R.id.btnlevel4);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // ---------------get user data from sign up---------

        user = findViewById(R.id.Nom_user);
        Bundle userData = getIntent().getExtras();
        String ActivityType = userData.getString("type");
        if(ActivityType.equals("signUp"))
        {
            userName = userData.getString("name");
            user.setText(userName);
        }
        email = userData.getString("email");
        pwd = userData.getString("name");

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedLevelName = "level1";
                level1.setBackgroundResource(R.drawable.bgborder2);
                level2.setBackgroundResource(R.drawable.frame11);
                level3.setBackgroundResource(R.drawable.frame33);
                level4.setBackgroundResource(R.drawable.frame44);


            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedLevelName = "level2";
                level1.setBackgroundResource(R.drawable.frame22);
                level2.setBackgroundResource(R.drawable.bgborder1);
                level3.setBackgroundResource(R.drawable.frame33);
                level4.setBackgroundResource(R.drawable.frame44);


            }
        });
        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedLevelName = "level3";
                level1.setBackgroundResource(R.drawable.frame22);
                level2.setBackgroundResource(R.drawable.frame11);
                level3.setBackgroundResource(R.drawable.bgborder3);
                level4.setBackgroundResource(R.drawable.frame44);


            }
        });
        level4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedLevelName = "level4";
                level1.setBackgroundResource(R.drawable.frame22);
                level2.setBackgroundResource(R.drawable.frame11);
                level3.setBackgroundResource(R.drawable.frame33);
                level4.setBackgroundResource(R.drawable.bgborder4);


            }
        });
        btnlevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedLevelName = "level1";
                Intent intent = new Intent(QuizActivity.this, questionActivity.class);
                    intent.putExtra("selectedLevel", selectedLevelName);
                    startActivity(intent);
//                    finish();

            }
        });
        btnlevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedLevelName = "level2";
                Intent intent = new Intent(QuizActivity.this, questionActivity.class);
                    intent.putExtra("selectedLevel", selectedLevelName);
                    startActivity(intent);
//                    finish();
                }

        });
        btnlevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedLevelName = "level3";
                Intent intent = new Intent(QuizActivity.this, GameActivity.class);
                intent.putExtra("selectedLevel", selectedLevelName);
                startActivity(intent);
//                finish();
            }

        });
        btnlevel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedLevelName = "level4";
                Intent intent = new Intent(QuizActivity.this, GameActivity.class);
                intent.putExtra("selectedLevel", selectedLevelName);
                startActivity(intent);
//                finish();
            }

        });
    }
}