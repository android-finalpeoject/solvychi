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
    private EditText inputusername ,inputchangepassword;
    private AppCompatButton update;
    DBHelper DB;
    String changeusernamee,changemaill,changepasswordd;
    private final String type = "edited";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().hide();
        backbtn1 = findViewById(R.id.backBtnn);
        inputusername = findViewById(R.id.inputusername);
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
        inputchangepassword.setText(changepasswordd);
        try {


            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DB.updateProfile(changemaill, inputusername.getText().toString(), inputchangepassword.getText().toString());

                    // displaying a toast message that our course has been updated.
                    Toast.makeText(EditActivity.this, "Profile Updated ✅", Toast.LENGTH_SHORT).show();

                    // launching our main activity.

                    Bundle B = new Bundle();

                    B.putString("email", changemaill);

                    B.putString("type", type);
                    B.putString("pwd",inputchangepassword.getText().toString());

                    Intent io = new Intent(EditActivity.this, QuizActivity.class);
                        io.putExtras(B);
                    startActivity(io);

                }


            });
        }catch(Exception e){

            Toast.makeText(EditActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        backbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(EditActivity.this, QuizActivity.class);
                startActivity(in);


            }
        });
    }
}