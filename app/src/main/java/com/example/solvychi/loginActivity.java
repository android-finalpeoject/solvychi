package com.example.solvychi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    private String [] data ={"chaimae@gmail.com","chaimae"};
    // implement the TextWatcher callback listener
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            girl.setImageResource(R.drawable.close);
        }

        @Override
        public void afterTextChanged(Editable s) {
            girl.setImageResource(R.drawable.girl);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        // get name and email
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        //get buttons
        loginBtn = findViewById(R.id.loginBtn);
        google = findViewById(R.id.google);
        fb = findViewById(R.id.fb);
        //sign up Btn
        sign = findViewById(R.id.sign);
        //========================change the girl image to closing eyes
        //        //==================reset the image================================
        pwd.addTextChangedListener(textWatcher);

        // set event listener on the sign up btn
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //verify the email and password:
                String mail = email.getText().toString();
                String pass = pwd.getText().toString();


                if(mail.equals(data[0])  && pass.equals(data[1]) )
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Let's go🤩!",Toast.LENGTH_SHORT);
                    toast.show();
//                    Intent home = new Intent(getApplicationContext(),temp.class);
//                    startActivity(home);
//                    finish();
                }
                //==================WRONG============
                // wrong email
                else {
                    if (!mail.equals(data[0]) && !mail.isEmpty()) {
                        email.setBackgroundResource(R.drawable.error_inputs);
                        Toast toast = Toast.makeText(getApplicationContext(), "wrong email!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    // wrong pwd
                    else if (!pass.equals(data[1]) && !pass.isEmpty()) {
                        pwd.setBackgroundResource(R.drawable.error_inputs);
                        Toast toast = Toast.makeText(getApplicationContext(), "wrong password!", Toast.LENGTH_SHORT);
                        toast.show();
                    }//===========EMPTY=================
                    // empty email
                    else {
                        if (mail.isEmpty()) {
                            email.setBackgroundResource(R.drawable.error_inputs);
                            Toast toast = Toast.makeText(getApplicationContext(), "empty email!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        // empty pwd
                        else if (pass.isEmpty())
                        {

                            pwd.setBackgroundResource(R.drawable.error_inputs);
                            Toast toast = Toast.makeText(getApplicationContext(), "empty password!", Toast.LENGTH_SHORT);
                            toast.show();
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