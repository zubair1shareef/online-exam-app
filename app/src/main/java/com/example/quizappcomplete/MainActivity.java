package com.example.quizappcomplete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
   EditText etEmail,etPassword;
   Button btnLogin;
   TextView tvSignup;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEmail= findViewById(R.id.etEmail);
        etPassword= findViewById(R.id.etPassword);
        btnLogin= findViewById(R.id.btnLogin);
        tvSignup= findViewById(R.id.tvSignup);

    }
    public void login(View v)
    {
        String email=etEmail.getText().toString().trim();
        String password=etPassword.getText().toString().trim();
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Authentication sucess.",
                                    Toast.LENGTH_SHORT).show();
                            Intent indent=new Intent(MainActivity.this,Quiz.class);
                            startActivity(indent);
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }
    public void register(View v)
    {
        Intent indent=new Intent(this,Register.class);
        startActivity(indent);

    }
}
