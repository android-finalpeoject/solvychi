package com.example.solvychi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    private Button loginBtn;
    private TextView sign;
    private EditText email;
    private EditText pwd;
    private ImageButton google;
    private ImageButton fb;
    private CheckBox remember;
    private ImageView girl;
    DBHelper DB;

    private final String type = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        // get name and email
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        DB = new DBHelper(this);
        //get buttons
        loginBtn = findViewById(R.id.loginBtn);
        google = findViewById(R.id.google);
        fb = findViewById(R.id.fb);
        //sign up Btn
        sign = findViewById(R.id.sign);
        // get check box
        remember = findViewById(R.id.remember);
//        pwd.addTextChangedListener(textWatcher);

        // ============================set event listener on the sign up btn=====================
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //verify the email and password:
                String mail = email.getText().toString();
                String pass = pwd.getText().toString();


                if(DB.checkemailpassword(mail,pass))
                {

                   Toast.makeText(getApplicationContext(),"Let's go🤩!",Toast.LENGTH_SHORT).show();


                    Bundle B = new Bundle();

                    B.putString("email", mail);
                    B.putString("pwd", pass);
                    B.putString("type", type);
                    Intent home = new Intent(getApplicationContext(),QuizActivity.class);
                    home.putExtras(B);
                    startActivity(home);


                }
                //==================WRONG============
                // wrong email
                else {
                    if (!DB.checkemail(mail) && !mail.isEmpty()) {
                        email.setBackgroundResource(R.drawable.error_inputs);
                       Toast.makeText(getApplicationContext(), "wrong email!", Toast.LENGTH_SHORT).show();
                    }
                    // wrong pwd
                    //===========EMPTY=================
                    // empty email
                    else {
                        if (mail.isEmpty()) {
                            email.setBackgroundResource(R.drawable.error_inputs);
                           Toast.makeText(getApplicationContext(), "empty email!", Toast.LENGTH_SHORT).show();
                        }
                        // empty pwd
                        else if (pass.isEmpty())
                        {

                            pwd.setBackgroundResource(R.drawable.error_inputs);
                         Toast.makeText(getApplicationContext(), "empty password!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            pwd.setBackgroundResource(R.drawable.error_inputs);
                          Toast.makeText(getApplicationContext(), "wrong password!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        // set event listener on the sign up btn
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(getApplicationContext(),signupActivity.class);
                startActivity(signUp);
            }
        });
        // set event listener on the google btn
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent home = new Intent(getApplicationContext(),temp.class);
//                home.putExtra("data",signUpData);
//                startActivity(home);
            }
        });
        // set event listener on the fb btn
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent home = new Intent(getApplicationContext(),temp.class);
//                home.putExtra("data",signUpData);
//                startActivity(home);
            }
        });
    }
}