package com.example.solvychi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private String selectedLevelName;

    private TextView user, level;
    private String userName,pwd,gender, levelUser,emailchanged;
    public String email;
    private ImageView logout;
    private TextView editprofil;
    public View levelView;
    List < AppCompatButton>  Btns = new ArrayList<AppCompatButton>();  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().hide();

        logout = findViewById(R.id.logout);
        editprofil=findViewById(R.id.editprofile);
        TextView text = (TextView) findViewById(R.id.error);
        ImageView pdp = (ImageView) findViewById(R.id.imageProfile);
        ImageView stars = (ImageView) findViewById(R.id.level_stars);

        level =findViewById(R.id.level_user);


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // ---------------get user data from sign up/login---------

        user = findViewById(R.id.Nom_user);
        Bundle userData = getIntent().getExtras();
        String  ActivityType = userData.getString("type");
        email = userData.getString("email");
        pwd = userData.getString("pwd");
        emailchanged = userData.getString("emailchanged");

        //=============================== CREATE LEVELS USING ADAPTER===========================
        ListView levels = (ListView) findViewById(R.id.levels);

        //-------------create our levels objects array-----------------------------
        level level1 = new level("Level 1","Simple questions","frame11","button","level1","bgborder1");
        level level2 = new level("Level 2","Simple questions","frame22","button","level2","bgborder2");
        level level3 = new level("Level 3","Simple questions","frame33","button","level3","bgborder3");
        level level4 = new level("Level 4","Simple questions","frame44","button","level4","bgborder4");
        level [] levelsArray ={level1,level2,level3,level4};
        int itemTemplateFile = R.layout.level_item;

        // get buttons of list view

//           for (int i=0;i<4;i++)
//           {
//               level current = levelsArray[0];
//               View row =(View) adapter.getViewByPosition(i,levels);
//               AppCompatButton levelBtn= (AppCompatButton) row.findViewById(R.id.btnlevel);
//
//               Btns.add(levelBtn);
//
//           }

        //=============== DB get user===========

//         try{

        DBHelper db = new DBHelper(this);
        if( ActivityType.equals("edited"))
        {
            userName = db.fetchUser(email);
            gender = db.fetchGender(email);
            user.setText(userName);
            choosePDP(pdp,gender);
        }
        else if( ActivityType.equals("signUp"))
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
        LevelsAdapter adapter = new LevelsAdapter(QuizActivity.this,itemTemplateFile,levelsArray,email,nbLevel);
        levels.setAdapter(adapter);


        //===================edit profile======================================================
        editprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent edit = new Intent(QuizActivity.this, EditActivity.class);
                edit.putExtra("username", userName);
                edit.putExtra("email", email);
                edit.putExtra("password", pwd);
                startActivity(edit);


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