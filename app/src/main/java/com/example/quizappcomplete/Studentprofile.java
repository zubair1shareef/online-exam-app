package com.example.quizappcomplete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizappcomplete.Model.Sprofile;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Studentprofile extends AppCompatActivity {
    TextView name,email,role,sem,branch,institute, tvEmail,tvName;
    DatabaseReference databaseReference;
    String uid;

    private FirebaseAuth mAuth;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private NavigationView mNavigationView;


    private static final String MY_PREFS_NAME = "CurrentUser";
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

        getSupportActionBar ().setTitle ("Profile");
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);

        mAuth = FirebaseAuth.getInstance ();

        mDrawerLayout = findViewById(R.id.activity_studentprofile);
        mActionBarDrawerToggle = new ActionBarDrawerToggle (this, mDrawerLayout,R.string.Open, R.string.Close);

        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

        mNavigationView = findViewById(R.id.navpane_studentprofile);

        tvEmail = mNavigationView.getHeaderView (0).findViewById (R.id.navEmail);
        tvName = mNavigationView.getHeaderView (0).findViewById (R.id.navName);

        tvName.setText (mAuth.getCurrentUser ().getDisplayName ());
        tvEmail.setText (mAuth.getCurrentUser ().getEmail ());

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.nav_student_quiz:{
                        Intent intent = new Intent (Studentprofile.this, StudentDashboardActivity.class);
                        finish ();
                        startActivity (intent);
                    }break;
                    case R.id.nav_student_signout:{
                        mAuth.signOut ();
                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.clear ();
                        editor.commit();
                        Intent intent = new Intent (Studentprofile.this, WelcomeActivity.class);
                        finish ();
                        startActivity (intent);
                    }break;
                    case R.id.nav_student_result:{
                        Intent intent = new Intent (Studentprofile.this, StudentResultListActivity.class);
                        finish ();
                        startActivity (intent);
                    }break;
                    case R.id.nav_student_profile:{
                        Toast.makeText(Studentprofile.this, "Profile",Toast.LENGTH_SHORT).show();
                    }break;
                    default:
                        return true;
                }
                return true;
            }
        });


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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mActionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
