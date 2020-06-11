package com.example.quizappcomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quizappcomplete.Model.Question;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfessorAddQuestionActivity extends AppCompatActivity {
    EditText etQuestionNo,etNewQuestion,etOp1,etOp2,etOp3,etop4,etAnswer;
    Button btnCreateQuestion;

    String quizId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_add_question);

        etQuestionNo= findViewById(R.id.editText7);
        etNewQuestion= findViewById(R.id.editText);
        etOp1= findViewById(R.id.editText2);
        etOp2= findViewById(R.id.editText3);
        etOp3= findViewById(R.id.editText4);
        etop4= findViewById(R.id.editText5);
        etAnswer= findViewById(R.id.editText6);
        btnCreateQuestion= findViewById(R.id.button);

        Intent intent = getIntent ();
        quizId = intent.getStringExtra ("quizId");

    }
    public void create(View v)
    {
        String questionno=etQuestionNo.getText().toString().trim();
        String question= etNewQuestion.getText().toString();
        String option1=etOp1.getText().toString();
        String option2=etOp2.getText().toString();
        String option3=etOp3.getText().toString();
        String option4=etop4.getText().toString();
        String answer=etAnswer.getText().toString();
        Question update=new Question (questionno,question,option1,option2,option3,option4,answer);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("Question/"+quizId);
        myRef.child(questionno).setValue(update);

        finish ();

    }
}
