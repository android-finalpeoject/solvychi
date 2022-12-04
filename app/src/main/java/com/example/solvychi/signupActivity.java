package com.example.solvychi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.common.api.ApiException;
//import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private EditText name;
    private EditText email;
    private EditText pwd;
    private TextView login;
    private Button signUp;
    private String level="0";
    private String genderType;
    private Spinner gender;
    public final Boolean signed = false;
    private final String type = "signUp";
    List<String> genders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        //get user data
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);

        //get buttons
        signUp = findViewById(R.id.signup);

        // =========spinner =====================
        genders.add(0,"Select your gender");
        genders.add("Girl");
        genders.add("Boy");
        gender = findViewById(R.id.gender);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(signupActivity.this,
               android.R.layout.simple_list_item_1,genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter((adapter));
        gender.setOnItemSelectedListener(this);
        //login Btn
        login = findViewById(R.id.login);

        //==================================== DB LINKING ========================================//
        DBHelper DB = new DBHelper(this);


        // ================================set event listener on the sign up btn

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //------NAME AND EMAIL VALIDATION

                String  mail= email.getText().toString();
                String user = name.getText().toString();
                String pass = pwd.getText().toString();

                //==========check the name:
                // Regex to check valid username.
                String regex = "^[A-Za-z]\\w{2,29}$";

                // Compile the ReGex
                Pattern p = Pattern.compile(regex);

                // to find matching between given username
                // and regular expression.
                Matcher m = p.matcher(user);

                try {
                    // ------------user name------------------
                    if(user.isEmpty() || !m.matches())
                    {
                        name.setBackgroundResource(R.drawable.error_inputs);
                        Toast toast = Toast.makeText(getApplicationContext(), "please enter your name!", Toast.LENGTH_SHORT);
                        toast.show();
                        name.getText().clear();
                        return;
                    }
                    // ------------user email------------------

                    if(mail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(mail).matches())
                    {
                        Toast.makeText(signupActivity.this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
                        email.getText().clear();

                        return;
                    }
                    // ------------user pwd------------------
                    if (pass.isEmpty())
                    {
                        pwd.setBackgroundResource(R.drawable.error_inputs);
                        Toast.makeText(getApplicationContext(), "please enter a password!", Toast.LENGTH_SHORT).show();
                        pwd.getText().clear();

                        return;
                    }
                    // ------------user gender------------------
                    if (genderType != null && genderType.equals("Select your gender"))
                    {
                        Toast.makeText(getApplicationContext(), "please select your gender!", Toast.LENGTH_SHORT).show();
//                        gender.setSelection(0);
                        return;
                    }
                    // =============check if the user exists already by checking his email:
                    Boolean checkuser = DB.checkemail(mail);
                    // affect inputs values to the data bundle
                    if(checkuser)
                    {
                        Toast.makeText(getApplicationContext(), "user already exists!", Toast.LENGTH_SHORT).show();
                        resetInputs();
                        return;
                    }
                    // check if the user got inserted in db
                    Boolean insert = DB.insertData(user, mail, pass,level,genderType);
                    if(insert == false)
                    {
                        Toast.makeText(getApplicationContext(), "try later!", Toast.LENGTH_SHORT).show();
                        resetInputs();
                        return;
                    }
                    Bundle data = new Bundle();
                    data.putString("name", user);
                    data.putString("email", mail);
                    data.putString("pwd", pass);
                    data.putString("gender",genderType);
                    data.putString("type", type);
                    data.putString("level", level);

                    Toast toast = Toast.makeText(getApplicationContext(), "Welcome "+user+" 🤩", Toast.LENGTH_LONG);
                    toast.show();
                    resetInputs();
                    Intent home = new Intent(getApplicationContext(),QuizActivity.class);
                    home.putExtras(data);
                    startActivity(home);
                    finish();
                }catch(Exception e)
                {
                    System.out.println(e.getCause());
                }




            }
        });

 // set event listener on the login btn
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginAct = new Intent(getApplicationContext(),loginActivity.class);

                startActivity(loginAct);
//                finish();
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        if (adapterView.getItemAtPosition(position)!= null && adapterView.getItemAtPosition(position).equals("Select your gender")) {
            genderType = adapterView.getItemAtPosition(0).toString();

        } else {
            genderType = adapterView.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void resetInputs()
    {
        name.getText().clear();
        email.getText().clear();
        pwd.getText().clear();
        gender.setSelection(0);
    }
}