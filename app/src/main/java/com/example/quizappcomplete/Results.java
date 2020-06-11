package com.example.quizappcomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Results extends AppCompatActivity {
    TextView wrong,total,correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        total=(TextView) findViewById(R.id.totalans);
        correct=(TextView) findViewById(R.id.correctans);
        wrong=(TextView) findViewById(R.id.wrongans);
        Intent i = getIntent();
        String Stotal = i.getStringExtra("total");
        String Scorrect = i.getStringExtra("correct");
        String Swrong = i.getStringExtra("incorrect");
        total.setText(Stotal);
        correct.setText(Scorrect);
        wrong.setText(Swrong);

    }
}
