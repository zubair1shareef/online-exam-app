package com.example.quizappcomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizappcomplete.Model.QuizInfo;

public class StudentQuizSelectedActivity extends AppCompatActivity {

    QuizInfo mQuizInfo;
    String mQuizId;
    TextView Date,Time,Marksperquestion,Totalmaerks,Timealloted,Createdby,Semister,Branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_student_quiz_selected);
        Date=(TextView) findViewById(R.id.tvDate_Student);
        Time=(TextView) findViewById(R.id.tvTime_Student);
        Marksperquestion=(TextView) findViewById(R.id.tvMarksPerQues_Student);
        Totalmaerks=(TextView) findViewById(R.id.tvTotalMarks_Student);
        Timealloted=(TextView) findViewById(R.id.tvTimeAllowed_Student);
        Createdby=(TextView) findViewById(R.id.tvCreatedBy_Student);
        Semister=(TextView) findViewById(R.id.tvSemester_Student);
        Branch=(TextView) findViewById(R.id.tvBranch_Student);

        Intent intent = getIntent ();
        mQuizInfo = (QuizInfo) intent.getSerializableExtra ("quizInfo");
        mQuizId = intent.getStringExtra ("quizId");

        getSupportActionBar ().setTitle (mQuizInfo.getTitle ());
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        update();

        Toast.makeText(getApplicationContext(),mQuizInfo.getTime(),Toast.LENGTH_SHORT).show();
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
    public void startquiz(View v)
    {
        Intent indent=new Intent(this,Quiz.class);
        indent.putExtra("quizid",String.valueOf(mQuizId));
        startActivity(indent);

    }

}
