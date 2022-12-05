package com.example.solvychi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    private ImageView backbtn1;
    private EditText inputusername ,inputchangemail,inputchangepassword;
    private AppCompatButton update;
    DBHelper DB;
    String changeusernamee,changemaill,changepasswordd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().hide();
        backbtn1 = findViewById(R.id.backBtnn);
        inputusername = findViewById(R.id.inputusername);
        inputchangemail = findViewById(R.id.inputchangemail);
        inputchangepassword = findViewById(R.id.inputchangepassword);
        DB = new DBHelper(this);
        Bundle B = new Bundle();


        update = findViewById(R.id.update);
        Bundle userData = getIntent().getExtras();
        // on below lines we are getting data which
        // we passed in our adapter class.
        changeusernamee = getIntent().getStringExtra("username");
        changemaill = getIntent().getStringExtra("email");
        changepasswordd = getIntent().getStringExtra("password");

        // setting data to edit text
        // of our update activity.
        inputusername.setText(changeusernamee);
        inputchangemail.setText(changemaill);
        inputchangepassword.setText(changepasswordd);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DB.updateProfile(changemaill,inputusername.getText().toString(), inputchangemail.getText().toString(), inputchangepassword.getText().toString());

                // displaying a toast message that our course has been updated.
                Toast.makeText(EditActivity.this, "Course Updated..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(EditActivity.this, QuizActivity.class);
                startActivity(i);

            }
        });
        backbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(EditActivity.this, QuizActivity.class);
                startActivity(in);
                finish();

            }
        });
    }
}