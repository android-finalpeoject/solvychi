package com.example.solvychi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.common.api.ApiException;
//import com.google.android.gms.tasks.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signupActivity extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText pwd;
    private TextView login;
    private Button signUp;
    private ImageView girl;
    public final Boolean signed = false;
    // google regestration
//    GoogleSignInOptions gso;
//    GoogleSignInClient gsc;

    private ImageButton google;
    private ImageButton fb;
    //    String []signUpData;
// implement the TextWatcher callback listener
//    private TextWatcher textWatcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            girl.setImageResource(R.drawable.close);
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            girl.setImageResource(R.drawable.close);
//
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        //get user data
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        girl = findViewById(R.id.girl);

        //get buttons
        signUp = findViewById(R.id.signup);

        fb = findViewById(R.id.fb);
        //login Btn
        login = findViewById(R.id.login);
        //==================================== DB LINKING ========================================//
        DBHelper DB = new DBHelper(this);
        //========================change the girl image to closing eyes

//        //==================reset the image================================
//        pwd.addTextChangedListener(textWatcher);
        // ================================set event listener on the sign up btn
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(),QuizActivity.class);
                //------NAME AND EMAIL VALIDATION
                String  mail= email.getText().toString();
                String user = name.getText().toString();
                String pass = pwd.getText().toString();
                //==========check the name:
                // Regex to check valid username.
                String regex = "^[A-Za-z]\\w{5,29}$";
                // Compile the ReGex
                Pattern p = Pattern.compile(regex);
                // to find matching between given username
                // and regular expression.
                Matcher m = p.matcher(user);
                if(user.isEmpty() && !m.matches())
                {
                    name.setBackgroundResource(R.drawable.error_inputs);
                    Toast toast = Toast.makeText(getApplicationContext(), "please enter your name!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    // ==============check the email ==========================
                    if (!mail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                        //==================check the password================
                        if(pass.isEmpty())
                        {
                            pwd.setBackgroundResource(R.drawable.error_inputs);
                            Toast toast = Toast.makeText(getApplicationContext(), "please enter a password!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else {
                            // =============check if the user exists already by checking his email:
                            Boolean checkuser = DB.checkemail(mail);
                            // affect inputs values to the data bundle
                            if(checkuser == false)
                            {
                                // check if the user got inserted in db
                                Boolean insert = DB.insertData(user, mail, pass);
                                if(insert== true)
                                {
                                    Bundle data = new Bundle();
                                    data.putString("name", user);
                                    data.putString("email", mail);
                                    data.putString("pwd", pass);
                                    Toast toast = Toast.makeText(getApplicationContext(), "Welcome "+user+" 🤩", Toast.LENGTH_LONG);
                                    toast.show();
                                    home.putExtras(data);
                                    startActivity(home);
                                }
                                else
                                {
                                    Toast toast = Toast.makeText(getApplicationContext(), "try later!", Toast.LENGTH_SHORT);
                                    toast.show();
                                }

                            }
                            //=============== user already exists ==============================
                            else
                            {
                                Toast toast = Toast.makeText(getApplicationContext(), "user already exists!", Toast.LENGTH_SHORT);
                                toast.show();
                            }

                        }
                    }
                    else {
                        Toast.makeText(signupActivity.this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        //=======================google button
//        google = findViewById(R.id.google);
//        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
//        gsc = GoogleSignIn.getClient(this,gso);
////        // set event listener on the google btn
//        google.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               signIn();
//            }
//        });
//        // set event listener on the fb btn
//        this.fb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent home = new Intent(getApplicationContext(),temp.class);
////                home.putExtra("data",signUpData);
////                startActivity(home);
//            }
//        });
//        // set event listener on the login btn
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginAct = new Intent(getApplicationContext(),loginActivity.class);

                startActivity(loginAct);
            }
        });


    }
//    void signIn()
//    {
//        Intent signInIntent = gsc.getSignInIntent();
//        startActivityForResult(signInIntent,1000);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 1000)
//        {
//             Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                task.getResult(ApiException.class);
//                navigateToSecondActivity();
//            } catch (ApiException e) {
//                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
//
//            }
//        }
//    }
//    void navigateToSecondActivity()
//    {
//        finish();
//        Intent intent = new Intent(getApplicationContext(),temp.class);
//        startActivity(intent);
//    }
//

}