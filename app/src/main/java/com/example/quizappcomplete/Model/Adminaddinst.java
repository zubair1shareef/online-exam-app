package com.example.quizappcomplete.Model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quizappcomplete.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Adminaddinst extends AppCompatActivity {
    EditText inemial,incode,inname;
    Button create;
    String name,code,email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminaddinst);
        inemial=(EditText) findViewById(R.id.inemail);
        incode=(EditText) findViewById(R.id.incode);
        inname=(EditText) findViewById(R.id.inname);


    }
    public void newinstitute(View v)
    {
        name=inname.getText().toString();
        email=inemial.getText().toString().trim();
        code=incode.getText().toString().trim();
        instituteadd addinstu=new instituteadd(name,email,code);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Institute");

        myRef.child(code).setValue(addinstu);


    }
}
