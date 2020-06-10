package com.example.quizappcomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quizappcomplete.Model.User;
import com.google.gson.Gson;

public class WelcomeActivity extends AppCompatActivity {

    Button btnLogin,  btnSignup;
    public static String MY_PREFS_NAME= "CurrentUser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_welcome);

        btnLogin = findViewById (R.id.btnLogin_Welcome);
        btnSignup = findViewById (R.id.btnSignup_Welcome);

        btnLogin.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (WelcomeActivity.this, MainActivity.class);
                finish ();
                startActivity (intent);
            }
        });

        btnSignup.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (WelcomeActivity.this, Register.class);
                finish ();
                startActivity (intent);
            }
        });


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("user", "");
        User user = gson.fromJson(json, User.class);

        if(user.getRole ().equals ("Professor"))
        {
            Intent intent = new Intent (WelcomeActivity.this, ProfessorDashboardActivity.class);
            finish ();
            startActivity (intent);
        }
        else if(user.getRole ().equals ("Student"))
        {

        }
        else if(user.getRole ().equals ("Admin"))
        {

        }
        else if(user.getRole ().equals ("Institute"))
        {

        }

    }
}
