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

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private String selectedLevelName;
    private AppCompatButton btnlevel1, btnlevel2, btnlevel3, btnlevel4 ;
    private TextView user, level;
    private String userName, email,pwd,gender, levelUser;
    private ImageView logout;
    private TextView editprofil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().hide();
        final LinearLayout level1 = findViewById(R.id.level1);
        final LinearLayout level2 = findViewById(R.id.level2);
        final LinearLayout level3 = findViewById(R.id.layout3);
        final LinearLayout level4 = findViewById(R.id.layout4);
        logout = findViewById(R.id.logout);
        editprofil=findViewById(R.id.editprofile);
        TextView text = (TextView) findViewById(R.id.error);
        ImageView pdp = (ImageView) findViewById(R.id.imageProfile);
        ImageView stars = (ImageView) findViewById(R.id.level_stars);
        btnlevel1 =findViewById(R.id.btnlevel1);
        btnlevel2 =findViewById(R.id.btnlevel2);
        btnlevel3 =findViewById(R.id.btnlevel3);
        btnlevel4 =findViewById(R.id.btnlevel4);
        level =findViewById(R.id.level_user);
        List<Button> btnList = new ArrayList<>();
        btnList.add(btnlevel1);
        btnList.add(btnlevel2);
        btnList.add(btnlevel3);
        btnList.add(btnlevel4);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // ---------------get user data from sign up/login---------

        user = findViewById(R.id.Nom_user);
        Bundle userData = getIntent().getExtras();
         String  ActivityType = userData.getString("type");
        email = userData.getString("email");
        pwd = userData.getString("pwd");




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
                 levelUser = db.fetchLevel(email);
        // ===============enable all the buttons of remaining levels===============
        int nbLevel= Integer.parseInt(levelUser);
        for (int i =nbLevel+1;i<btnList.size();i++)
        {
            btnList.get(i).setEnabled(false);
            btnList.get(i).setText("Locked 🔒");
            btnList.get(i).setBackgroundResource(R.drawable.button_lock);
        }
        // ===============change the state of all previous levels===============
        int n = nbLevel-1;
        while (n>=0)
        {
            btnList.get(n).setText("Replay");
            n--;
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
        switch (levelUser)
        {

            case "1":
                btnlevel2.setEnabled(true);
                btnlevel2.setText("START");
                btnlevel2.setBackgroundResource(R.drawable.button);

                break;
            case "2":
                btnlevel3.setEnabled(true);
                btnlevel3.setText("START");
                btnlevel3.setBackgroundResource(R.drawable.button);

                break;
            case "3":
                btnlevel4.setEnabled(true);
                btnlevel4.setText("START");
                btnlevel4.setBackgroundResource(R.drawable.button);




        }
        editprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent edit = new Intent(QuizActivity.this, EditActivity.class);
                edit.putExtra("username", userData.getString("name"));
                edit.putExtra("email", userData.getString("email"));
                edit.putExtra("password", userData.getString("pwd"));
                startActivity(edit);

                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent in = new Intent(getApplicationContext(), loginActivity.class);
                startActivity(in);

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
                         Intent intent = new Intent(QuizActivity.this, GameActivity.class);
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
                         Intent intent = new Intent(QuizActivity.this, GameActivity.class);
                         data.putString("selectedLevel", selectedLevelName);
                         data.putString("email", email);
                         intent.putExtras(data);

                         startActivity(intent);
//                         finish();
                     }

                 });
                 //=====================================update the level================

                 level.setText("Level "+levelUser);
                 changeStars(levelUser,stars);

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

    public void changeStars(String star,ImageView stars)
    {
        switch (star)
        {
            case "0":
                stars.setImageResource(R.drawable.level0);
                break;
            case "1":
                stars.setImageResource(R.drawable.level1);
                break;
            case "2":
                stars.setImageResource(R.drawable.level2);
                break;
            case "3":
                stars.setImageResource(R.drawable.level3);
                break;
            case "4":
                stars.setImageResource(R.drawable.level4);

        }
    }


}