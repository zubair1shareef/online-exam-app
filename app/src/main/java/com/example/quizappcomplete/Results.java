package com.example.quizappcomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.quizappcomplete.Model.Result;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Results extends AppCompatActivity {
    TextView wrong,total,correct,vemail,vname;
    private FirebaseAuth mAuth;


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

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            boolean emailVerified = user.isEmailVerified();


            String uid = user.getUid();
            Result res=new Result(Stotal,Scorrect,Swrong,name,email);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Results");
            myRef.child(uid).setValue(res);

        }

    }


}
