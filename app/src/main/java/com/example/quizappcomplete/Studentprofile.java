package com.example.quizappcomplete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizappcomplete.Model.Sprofile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Studentprofile extends AppCompatActivity {
    TextView name,email,role,sem,branch,institute;
    DatabaseReference databaseReference;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentprofile);
        name=(TextView) findViewById(R.id.name);
       email =(TextView) findViewById(R.id.email);
        role=(TextView) findViewById(R.id.role);
        sem=(TextView) findViewById(R.id.sem);
        branch=(TextView) findViewById(R.id.branch);
        institute=(TextView) findViewById(R.id.institute);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(String.valueOf(uid));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Sprofile profile=dataSnapshot.getValue(Sprofile.class);
                name.setText(profile.getName());
                email.setText(profile.getEmail());
                role.setText(profile.getRole());
                sem.setText(profile.getSemester());
                branch.setText(profile.getBranch());
                institute.setText(profile.getInstituteCode());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void edit(View v)
    {
        Toast.makeText(Studentprofile.this, "not enable",Toast.LENGTH_SHORT).show();
    }

}
