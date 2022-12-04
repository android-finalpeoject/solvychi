package com.example.solvychi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private String selectedLevelName;
    private AppCompatButton btnlevel1, btnlevel2, btnlevel3, btnlevel4 ;
    private TextView user, level;
    private String userName, email,pwd,gender, levelUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().hide();
        final LinearLayout level1 = findViewById(R.id.level1);
        final LinearLayout level2 = findViewById(R.id.level2);
        final LinearLayout level3 = findViewById(R.id.layout3);
        final LinearLayout level4 = findViewById(R.id.layout4);
        TextView text = (TextView) findViewById(R.id.error);
        ImageView pdp = (ImageView) findViewById(R.id.imageProfile);
        btnlevel1 =findViewById(R.id.btnlevel1);
        btnlevel2 =findViewById(R.id.btnlevel2);
        btnlevel3 =findViewById(R.id.btnlevel3);
        btnlevel4 =findViewById(R.id.btnlevel4);
        level =findViewById(R.id.level_user);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // ---------------get user data from sign up/login---------

        user = findViewById(R.id.Nom_user);
        Bundle userData = getIntent().getExtras();
        String  ActivityType = userData.getString("type");
        email = userData.getString("email");
        pwd = userData.getString("pwd");

        boolean backFromResult = getIntent().getBooleanExtra("result",false);


        //=============== DB get user===========

//         try{

                 DBHelper db = new DBHelper(this);
                 if( ActivityType.equals("signUp"))
                 {
                     gender = userData.getString("gender");
                     userName = userData.getString("name");
                     user.setText(userName);
                     choosePDP(pdp,gender);
                 }
                 else if(ActivityType.equals("login"))
                 {
                     userName = db.fetchUser(email);
                     gender = db.fetchGender(email);
                     user.setText(userName);
                     choosePDP(pdp,gender);


                 }
                if(backFromResult==false)
                {
                    levelUser = db.fetchLevel(email);
                    level.setText("Level "+levelUser);
                }
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
                         Bundle data = new Bundle();
                         Intent intent = new Intent(QuizActivity.this, questionActivity.class);
                         data.putString("selectedLevel", selectedLevelName);
                         data.putString("email", email);
                         intent.putExtras(data);


                         startActivity(intent);
    //                    finish();

                     }
                 });
                 btnlevel2.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         selectedLevelName = "level2";
                         Bundle data = new Bundle();
                         Intent intent = new Intent(QuizActivity.this, questionActivity.class);
                         data.putString("selectedLevel", selectedLevelName);
                         data.putString("email", email);
                         intent.putExtras(data);


                         startActivity(intent);
    //                    finish();
                     }

                 });
                 btnlevel3.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         selectedLevelName = "level3";
                         Bundle data = new Bundle();
                         Intent intent = new Intent(QuizActivity.this, questionActivity.class);
                         data.putString("selectedLevel", selectedLevelName);
                         data.putString("email", email);
                         intent.putExtras(data);

                         startActivity(intent);
    //                finish();
                     }

                 });
                 btnlevel4.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         selectedLevelName = "level4";
                         Bundle data = new Bundle();
                         Intent intent = new Intent(QuizActivity.this, questionActivity.class);
                         data.putString("selectedLevel", selectedLevelName);
                         data.putString("email", email);
                         intent.putExtras(data);

                         startActivity(intent);
//                         finish();
                     }

                 });
                 //=====================================update the level================

                 if(backFromResult)
                 {
                     levelUser = db.fetchLevel(email);
                     level.setText("Level "+levelUser);

                 }

//         }catch (Exception e)
//         {
//             text.setText(e.getMessage());
//             e.printStackTrace();
//         }


    }
    public void choosePDP(ImageView pdp, String gender)
    {
        //===========set PDP===========

        if( gender.equals("Girl"))
        {
            pdp.setImageResource(R.drawable.pdp2);
        }
        else if(gender.equals("Boy"))
        {
            pdp.setImageResource(R.drawable.pdp3);

        }


    }
}