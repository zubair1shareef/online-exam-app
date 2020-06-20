package com.example.quizappcomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizappcomplete.Model.QuizInfo;

public class ProfessorViewQuizActivity extends AppCompatActivity {

    QuizInfo mQuizInfo;
    String mQuizId;
    TextView Date,Time,Marksperquestion,Totalmaerks,Timealloted,Createdby,Semister,Branch;
    Button btnViewQues, btnViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_professor_view_quiz);


        Date=(TextView) findViewById(R.id.tvDate_Professor);
        Time=(TextView) findViewById(R.id.tvTime_Professor);
        Marksperquestion=(TextView) findViewById(R.id.tvMarksPerQues_Professor);
        Totalmaerks=(TextView) findViewById(R.id.tvTotalMarks_Professor);
        Timealloted=(TextView) findViewById(R.id.tvTimeAllowed_Professor);
        Createdby=(TextView) findViewById(R.id.tvCreatedBy_Professor);
        Semister=(TextView) findViewById(R.id.tvSemester_Professor);
        Branch=(TextView) findViewById(R.id.tvBranch_Professor);
        btnViewQues = findViewById (R.id.btnViewQuestion_Prof);
        btnViewResult = findViewById (R.id.btnViewResult_Prof);
        Intent intent = getIntent ();
        mQuizInfo = (QuizInfo) intent.getSerializableExtra ("quizInfo");
        mQuizId = intent.getStringExtra ("quizId");

        getSupportActionBar ().setTitle (mQuizInfo.getTitle ());
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        update();

        btnViewResult.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ProfessorViewQuizActivity.this, ProfessorViewResultActivity.class);
                intent.putExtra ("quizInfo",mQuizInfo);
                intent.putExtra ("quizId",mQuizId);
                finish ();
                startActivity (intent);
            }
        });

        btnViewQues.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ProfessorViewQuizActivity.this, ProfessorViewQuestionsActivity.class);
                intent.putExtra ("quizInfo",mQuizInfo);
                intent.putExtra ("quizId",mQuizId);
                finish ();
                startActivity (intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    public void update()
    {
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

    }
}
