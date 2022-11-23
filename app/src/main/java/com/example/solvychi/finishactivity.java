package com.example.solvychi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

public class finishactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishactivity);
        getSupportActionBar().hide();
        //Change the color of hero
        TextView textView = findViewById(R.id.lovetolearn);
        AppCompatButton btnsign = findViewById(R.id.signin);


        String text = "Love to Learn Problem solving from SOLVYCHI!";

        SpannableString spannableString = new SpannableString(text);

        // we can only use backgroundcolor
        // span with a spannableStringBuilder.


        // It is used to set foreground color.
        ForegroundColorSpan white = new ForegroundColorSpan(Color.parseColor("#F45253"));


        // It is used to set the span to the string
        spannableString.setSpan(white,
                35, 44, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        textView.setText(spannableString);
        //end changing the color of here !
        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(finishactivity.this, QuizActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }









    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(finishactivity.this , NextActivity.class));
        finish();
    }
}

