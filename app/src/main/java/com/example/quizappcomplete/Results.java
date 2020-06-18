package com.example.quizappcomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.quizappcomplete.Model.QuizInfo;
import com.example.quizappcomplete.Model.Result;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Results extends AppCompatActivity {
    TextView wrong,total,correct,vemail,vname;
    private FirebaseAuth mAuth;
    QuizInfo mQuizInfo;
    TextView Date,Time,Marksperquestion,Totalmaerks,Timealloted,Createdby,Semister,Branch;
    String quizId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);



        total=(TextView) findViewById(R.id.tvAttempted_StudentResult);
        correct=(TextView) findViewById(R.id.tvCorrect_StudentResult);
        wrong=(TextView) findViewById(R.id.tvWrong_StudentResult);

        Date=(TextView) findViewById(R.id.tvDate_StudentResult);
        Time=(TextView) findViewById(R.id.tvTime_StudentResult);
        Marksperquestion=(TextView) findViewById(R.id.tvMarksPerQues_StudentResult);
        Totalmaerks=(TextView) findViewById(R.id.tvTotalMarks_StudentResult);
        Timealloted=(TextView) findViewById(R.id.tvTimeAllowed_StudentResult);
        Createdby=(TextView) findViewById(R.id.tvCreatedBy_StudentResult);
        Semister=(TextView) findViewById(R.id.tvSemester_StudentResult);
        Branch=(TextView) findViewById(R.id.tvBranch_StudentResult);

        Intent i = getIntent();
        Result result = (Result) i.getSerializableExtra ("result");
        String Stotal = result.getTotalQues ();
        String Scorrect = result.getCorrect ();
        String Swrong = result.getWrong ();
        quizId = i.getStringExtra ("quizId");
        mQuizInfo = (QuizInfo) i.getSerializableExtra ("quizInfo");
        total.setText(Stotal);
        correct.setText(Scorrect);
        wrong.setText(Swrong);

        Date.setText(mQuizInfo.getDate());
        Time.setText(mQuizInfo.getTime());
        Timealloted.setText(mQuizInfo.getDuration());
        Totalmaerks.setText(mQuizInfo.getTotalMarks());
        Timealloted.setText(mQuizInfo.getDuration ());
        Createdby.setText(mQuizInfo.getCreatedBy());

        // Subject.setText(mQuizInfo.se);
        Semister.setText(mQuizInfo.getSemester());
        Branch.setText(mQuizInfo.getBranch());
        Marksperquestion.setText(mQuizInfo.getMarksperquestion());

        getSupportActionBar ().setTitle (mQuizInfo.getTitle ());
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);



    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
