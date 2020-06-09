package com.example.quizappcomplete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizappcomplete.Model.Institute;
import com.example.quizappcomplete.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    final public  String TAG  = "Register Activity";
    EditText etFullName, etEmail, etPassword, etInstituteCode;
    RadioButton rbStudent, rbProfessor;
    Button btnSignup;
    TextView tvLogin;

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;

    String mName, mEmail, mPassword, mInstituteCode;
    ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullName = findViewById (R.id.etSignupName);
        etEmail = findViewById (R.id.etSignupEmail);
        etPassword = findViewById (R.id.etSignupPassword);
        etInstituteCode = findViewById (R.id.etSignupInstitute);
        rbStudent = findViewById (R.id.rbStudent);
        rbProfessor = findViewById (R.id.rbProfessor);
        btnSignup = findViewById (R.id.btnSignup);
        tvLogin = findViewById (R.id.tvLogin);

        mAuth = FirebaseAuth.getInstance ();
        mDatabase = FirebaseDatabase.getInstance ();

        mDialog = new ProgressDialog (this);
        mDialog.setMessage ("Signing  Up...");

        btnSignup.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Log.d (TAG, "onClick: Button CLicked");
                getRegistrationData();
                Log.d (TAG, "onClick: Data Fetched ");

                if(validateData ())
                {
                    mDialog.show ();
                    mReference = mDatabase.getReference ("Institute/");
                    mReference.addListenerForSingleValueEvent (new ValueEventListener () {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot data : dataSnapshot.getChildren ()){
                                Institute institute = data.getValue (Institute.class);
                                if(institute.getInstituteCode ().equals (mInstituteCode))
                                {
                                    Log.d (TAG, "onDataChange: Match Found");
                                    if (rbStudent.isChecked ())
                                        registerNewStudent ();
                                    else if (rbProfessor.isChecked ())
                                        registerNewProfessor ();
                                    break;
                                }
                                else
                                {
                                    etInstituteCode.setError ("Invalid Institute Code");
                                    etInstituteCode.requestFocus ();
                                    mDialog.dismiss ();
                                    break;
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }
        });
    }

    public void getRegistrationData()
    {
        mName = etFullName.getText ().toString ().trim ();
        mEmail = etEmail.getText ().toString ().trim ();
        mInstituteCode = etInstituteCode.getText ().toString ().trim();
        mPassword = etPassword.getText ().toString ().trim ();
    }

    public void registerNewStudent()
    {
            mReference = mDatabase.getReference ("Users");
            mAuth.createUserWithEmailAndPassword(mEmail, mPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult> () {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mDialog.dismiss ();
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("SignUp", "createUserWithEmail:success");
                                final FirebaseUser user = mAuth.getCurrentUser();

                                if(user!=null)
                                {
                                    UserProfileChangeRequest profile = new  UserProfileChangeRequest.Builder()
                                            .setDisplayName (mName)
                                            .build ();

                                    user.updateProfile (profile)
                                            .addOnCompleteListener (new OnCompleteListener<Void> () {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful ()){
                                                        Log.d ("SignUp","Display name updated");
                                                        User u = new User(mAuth.getCurrentUser ().getDisplayName (), user.getEmail (),mInstituteCode,null,null,"Student");
                                                        Intent i = new Intent (Register.this, StudentProfileCompleteActivity.class);
                                                        i.putExtra ("user", u);
                                                        mDialog.dismiss ();
                                                        finish ();
                                                        startActivity (i);
                                                    }
                                                    else
                                                        Log.d ("SignUp","Display name not updated");
                                                }
                                            });
                                }

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("SignUp", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Register.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                mDialog.dismiss ();
                                if(task.getException () instanceof FirebaseAuthUserCollisionException)
                                {
                                    etEmail.setError ("Account Already Exsits");
                                    etEmail.requestFocus ();
                                }
                            }

                        }
                    });
    }

    public void registerNewProfessor()
    {
        mReference = mDatabase.getReference ("Users");
        mAuth.createUserWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mDialog.dismiss ();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SignUp", "createUserWithEmail:success");
                            final FirebaseUser user = mAuth.getCurrentUser();

                            if(user!=null)
                            {
                                UserProfileChangeRequest profile = new  UserProfileChangeRequest.Builder()
                                        .setDisplayName (mName)
                                        .build ();

                                user.updateProfile (profile)
                                        .addOnCompleteListener (new OnCompleteListener<Void> () {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful ()){
                                                    Log.d ("SignUp","Display name updated");
                                                    User u = new User(mAuth.getCurrentUser ().getDisplayName (), user.getEmail (),mInstituteCode,null,null,"Professor");
                                                    mReference.child (mAuth.getUid ()).setValue (u);
                                                    mDialog.dismiss ();
                                                    Toast.makeText (Register.this, "Data Stored", Toast.LENGTH_SHORT).show ();
                                                    //Intent i = new Intent (Register.this, StudentProfileCompleteActivity.class);
                                                    //Update above intent with the Professor Dashboard.
                                                    //finish ();
                                                    //startActivity (i);
                                                }
                                                else
                                                    Log.d ("SignUp","Display name not updated");
                                            }
                                        });
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SignUp", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            mDialog.dismiss ();
                            if(task.getException () instanceof FirebaseAuthUserCollisionException)
                            {
                                etEmail.setError ("Account Already Exsits");
                                etEmail.requestFocus ();
                            }
                        }

                    }
                });
    }

    public boolean validateData()
    {
        if(mName.isEmpty ())
        {
            etFullName.setError ("Name is Required");
            etFullName.requestFocus ();
            return false;
        }
        if(mEmail.isEmpty ())
        {
            etEmail.setError ("Email is Required");
            etEmail.requestFocus ();
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher (mEmail).matches ())
        {
            etEmail.setError ("Invalid Email Address");
            etEmail.requestFocus ();
            return false;
        }

        if(mPassword.isEmpty ())
        {
            etPassword.setError ("Password is Required");
            etPassword.requestFocus ();
            return false;
        }

        if(mPassword.length () <8)
        {
            etPassword.setError ("Password length should be more than 8");
            etPassword.requestFocus ();
            return false;
        }
        return true;
    }
}
