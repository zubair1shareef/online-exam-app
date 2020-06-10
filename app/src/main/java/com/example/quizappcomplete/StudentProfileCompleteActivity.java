package com.example.quizappcomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizappcomplete.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class StudentProfileCompleteActivity extends AppCompatActivity {

    EditText etBranch, etSemester;
    Button btnCompleteProfile;

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;

    public static String MY_PREFS_NAME= "CurrentUser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_student_profile_complete);

        etBranch = findViewById (R.id.etBranch);
        etSemester = findViewById (R.id.etSemester);
        btnCompleteProfile = findViewById (R.id.btnCompleteProfile);

        mAuth = FirebaseAuth.getInstance ();
        mDatabase = FirebaseDatabase.getInstance ();
        mReference = mDatabase.getReference ("Users");

        Intent intent = getIntent ();
        final User user = (User) intent.getSerializableExtra ("user");

        btnCompleteProfile.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog (StudentProfileCompleteActivity.this);
                mDialog.setMessage ("Saving Profile...");
                mDialog.show ();
                user.setBranch (etBranch.getText ().toString ());
                user.setSemester (etSemester.getText ().toString ());

                mReference.child (mAuth.getUid ()).setValue (user);

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                Gson gson = new Gson();
                String json = gson.toJson(user);
                editor.putString("user", json);
                editor.commit();

                mDialog.dismiss ();
                Toast.makeText (StudentProfileCompleteActivity.this, "Data Stored", Toast.LENGTH_SHORT).show ();


            }
        });

    }
}
