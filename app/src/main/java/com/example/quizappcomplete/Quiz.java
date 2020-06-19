package com.example.quizappcomplete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizappcomplete.Model.Question;
import com.example.quizappcomplete.Model.QuizInfo;
import com.example.quizappcomplete.Model.Response;
import com.example.quizappcomplete.Model.Result;
import com.example.quizappcomplete.Model.Setquestions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    Button button1,button2,button3,button4,btnSubmit,btnSave;
    FloatingActionButton next,back;
    TextView tvquestion,qno,timmer;
    private FirebaseAuth mAuth;
    int wrong=0,correct=0,max=0;
    DatabaseReference reference;
    String time ,quizid;
    int count;
    QuizInfo mQuizInfo;

    List<Question> mQuestionList;
    List<Response> mResponseList;
    private ProgressDialog mDialog;

    int currentQuestion=0, currentOptionSelected=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mAuth = FirebaseAuth.getInstance ();
        button1=(Button) findViewById(R.id.btnOption1_Student);
        button2=(Button) findViewById(R.id.btnOption2_Student);
        button3=(Button) findViewById(R.id.btnOption3_Student);
        button4=(Button) findViewById(R.id.btnOption4_Student);
        btnSubmit = findViewById (R.id.btnSubmit_Student);
        btnSave = findViewById (R.id.btnSave_Student);
        qno=(TextView) findViewById(R.id.qno);
        next=(FloatingActionButton) findViewById(R.id.next);
        back=(FloatingActionButton) findViewById(R.id.back);
        tvquestion=(TextView) findViewById(R.id.tvQuestion_Student);
        timmer=(TextView) findViewById(R.id.tvTimer);
        Intent i = getIntent();
        quizid = i.getStringExtra("quizid");
        mQuizInfo = (QuizInfo) i.getSerializableExtra ("quizInfo");
        int timer=Integer.parseInt(mQuizInfo.getDuration());
        timer=timer*60;
        Log.d (TAG, "onCreate: Timer "+timer);
        mQuestionList = new ArrayList<> ();
        mResponseList = new ArrayList<> ();
        mDialog = new ProgressDialog (this);
        mDialog.setMessage ("Loading Questions...");
        reference = FirebaseDatabase.getInstance ().getReference ("Question/"+quizid);
        reference.addListenerForSingleValueEvent (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                max = Integer.parseInt (String.valueOf (dataSnapshot.getChildrenCount ()));
                Log.d (TAG, "onDataChange: No. of question : "+max);
                for(DataSnapshot data : dataSnapshot.getChildren ())
                {
                    Question question = data.getValue (Question.class);
                    Response response = new Response (question.getAnswer (),"0",question.getQuestionno ());
                    mQuestionList.add (question);
                    mResponseList.add (response);

                    updateQuestion();
                }
                mDialog.dismiss ();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reverseTimer(timer,timmer);

        button1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                currentOptionSelected=1;
                setUserSelection ();
            }
        });
        button2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                currentOptionSelected=2;
                setUserSelection ();
            }
        });
        button3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                currentOptionSelected=3;
                setUserSelection ();
            }
        });
        button4.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                currentOptionSelected=4;
                setUserSelection ();
            }
        });
        btnSubmit.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                submitQuiz ();
            }
        });

        btnSave.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                saveAnswer (String.valueOf (currentOptionSelected));
            }
        });

    }
    public void updateQuestion() {

        Question question = mQuestionList.get (currentQuestion);
        qno.setText (question.getQuestionno ());
        tvquestion.setText (question.getQuestion ());
        button1.setText (question.getOption1 ());
        button2.setText (question.getOption2 ());
        button3.setText (question.getOption3 ());
        button4.setText (question.getOption4 ());
        resetButtons ();
        Response response = mResponseList.get (currentQuestion);
        if(!response.getUserAnswer ().equals ("0"))
        {
            switch (response.getUserAnswer ())
            {
                case "1":currentOptionSelected=1;
                    break;
                case "2":currentOptionSelected=2;
                    break;
                case "3":currentOptionSelected=3;
                    break;
                case "4":currentOptionSelected=4;
                    break;
                default:break;
            }
        }
        setUserSelection();
    }

    public void resetButtons() {
        button1.setBackgroundResource (R.drawable.option_normal);
        button2.setBackgroundResource (R.drawable.option_normal);
        button3.setBackgroundResource (R.drawable.option_normal);
        button4.setBackgroundResource (R.drawable.option_normal);
    }

    public  void setUserSelection()
    {
        resetButtons ();
        switch (currentOptionSelected)
        {
            case 1:button1.setBackgroundResource (R.drawable.option_selected);
                break;
            case 2:button2.setBackgroundResource (R.drawable.option_selected);
                break;
            case 3:button3.setBackgroundResource (R.drawable.option_selected);
                break;
            case 4:button4.setBackgroundResource (R.drawable.option_selected);
                break;
            default:break;
        }
    }
    public void back(View v)
    {
        if(currentQuestion>0) {
            currentQuestion = (currentQuestion - 1);
            currentOptionSelected=0;
            updateQuestion ();
        }
        else
            Toast.makeText (this, "This is the First Question", Toast.LENGTH_SHORT).show ();
    }
    public  void next(View v)
    {
        if(currentQuestion<max-1) {
            currentQuestion = (currentQuestion + 1);
            currentOptionSelected=0;
            updateQuestion ();
        }
        else
            Toast.makeText (this, "This is Last Question. Please Submit", Toast.LENGTH_LONG).show ();
    }


    public void submitQuiz()
    {
        int marksperQues = Integer.parseInt (mQuizInfo.getMarksperquestion ());
        for (Response response : mResponseList)
        {
            if(response.isAswerCorrect ())
            {
                correct++;
            }
            else
                wrong++;
        }
        String totalMarks = mQuizInfo.getTotalMarks ();
        String obtainedMarks = String.valueOf (marksperQues * correct);
        Intent myIntent = new Intent(Quiz.this,Results.class);
        Result res=new Result(String.valueOf (max),
                String.valueOf (correct),
                String.valueOf (wrong)
                ,mAuth.getCurrentUser ().getDisplayName (),mAuth.getCurrentUser ().getEmail (),obtainedMarks,totalMarks);
        myIntent.putExtra("result",res);
        myIntent.putExtra ("quizInfo",mQuizInfo);
        myIntent.putExtra ("quizId",quizid);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Results/"+quizid);
        myRef.child(mAuth.getUid ()).setValue(res);

        finish ();
        startActivity(myIntent);
    }

    public void saveAnswer(String option)
    {
        mResponseList.get (currentQuestion).setUserAnswer (option);
        Toast.makeText (this, "Your response is saved", Toast.LENGTH_SHORT).show ();
    }


    public void reverseTimer(int Seconds,final TextView tv){

        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                submitQuiz ();
            }
        }.start();
    }

}
