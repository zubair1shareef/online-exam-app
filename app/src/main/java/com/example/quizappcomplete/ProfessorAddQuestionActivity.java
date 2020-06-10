package com.example.quizappcomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quizappcomplete.Model.Question;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfessorAddQuestionActivity extends AppCompatActivity {
    EditText questiono,newquestion,opt1,opt2,opt3,opt4,ans;
    Button create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_add_question);
        questiono=(EditText) findViewById(R.id.editText7);
        newquestion=(EditText) findViewById(R.id.editText);
        opt1=(EditText) findViewById(R.id.editText2);
        opt2=(EditText) findViewById(R.id.editText3);
        opt3=(EditText) findViewById(R.id.editText4);
        opt4=(EditText) findViewById(R.id.editText5);
        ans=(EditText) findViewById(R.id.editText6);

        create=(Button) findViewById(R.id.button);
    }
    public void create(View v)
    {
        String questionno=questiono.getText().toString().trim();
        String question= newquestion.getText().toString();
        String option1=opt1.getText().toString();
        String option2=opt2.getText().toString();
        String option3=opt3.getText().toString();
        String option4=opt4.getText().toString();
        String answer=ans.getText().toString();
        Question update=new Question (questionno,question,option1,option2,option3,option4,answer);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("Question");
        myRef.child(questionno).setValue(update);

    }
}
