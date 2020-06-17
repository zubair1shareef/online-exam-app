package com.example.quizappcomplete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.quizappcomplete.Model.QuizInfo;
import com.example.quizappcomplete.Model.Setquestions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Quiz extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    Button button1,button2,button3,button4;
    FloatingActionButton next,back;
    TextView textView,qno,timmer;
    private FirebaseAuth mAuth;
    int wrong=0,correct=0,max=5,maxquestions=1,quesyionno=1;
    DatabaseReference reference;
    String time ,quizid;
    int count;
    QuizInfo mQuizInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        button1=(Button) findViewById(R.id.btnOption1_Student);
        button2=(Button) findViewById(R.id.btnOption2_Student);
        button3=(Button) findViewById(R.id.btnOption3_Student);
        button4=(Button) findViewById(R.id.btnOption4_Student);
        qno=(TextView) findViewById(R.id.qno);
        next=(FloatingActionButton) findViewById(R.id.next);
        back=(FloatingActionButton) findViewById(R.id.back);
        textView=(TextView) findViewById(R.id.tvQuestion_Student);
        timmer=(TextView) findViewById(R.id.tvTimer);
        Intent i = getIntent();
        quizid = i.getStringExtra("quizid");
        mQuizInfo = (QuizInfo) i.getSerializableExtra ("quizInfo");
        int timer=Integer.parseInt(mQuizInfo.getDuration());
        timer=timer*60;
        Log.d (TAG, "onCreate: Timer "+timer);


        reference = FirebaseDatabase.getInstance ().getReference ("Question/"+quizid);
        reference.addListenerForSingleValueEvent (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                max = Integer.parseInt (String.valueOf (dataSnapshot.getChildrenCount ()));

                Log.d (TAG, "onDataChange: No. of question : "+max);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        update();
        reverseTimer(timer,timmer);

    }
    public void update() {
        qno.setText(Integer.toString(maxquestions));

        if(maxquestions==max)
        {
           Resultq();

        }
        else if(maxquestions>0)
        {  //maxquestions++;
            reference= FirebaseDatabase.getInstance().getReference().child("Question").child(quizid).child(String.valueOf(maxquestions));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Setquestions question=dataSnapshot.getValue(Setquestions.class);
                    textView.setText(question.getQuestion());
                    button1.setText(question.getOption1());
                    button2.setText(question.getOption2());
                    button3.setText(question.getOption3());
                    button4.setText(question.getOption4());
                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(button1.getText().toString().equals(question.getAnswer()))
                            {
                                Toast.makeText(getApplicationContext(),"Correct answer",Toast.LENGTH_SHORT).show();
                               // button1.setBackgroundColor(Color.GREEN);
                                correct = correct +1;
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        //button1.setBackgroundColor(Color.parseColor("#E3D3EA"));//colour change
                                        update();
                                    }
                                },1000);
                            }
                            else
                            {
                                wrong++;
                               // button1.setBackgroundColor(Color.RED);//colour cahnge
                                if(button2.getText().toString().equals(question.getAnswer()))
                                {
                                   // button2.setBackgroundColor(Color.GREEN);
                                }
                                else if(button3.getText().toString().equals(question.getAnswer()))
                                {
                                   // button3.setBackgroundColor(Color.GREEN);
                                }
                                else if(button4.getText().toString().equals(question.getAnswer()))
                                {
                                   // button4.setBackgroundColor(Color.GREEN);
                                }
                              //  colorchangeback();
                                update();
                            }


                        }
                    });
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(button2.getText().toString().equals(question.getAnswer()))
                            {
                                Toast.makeText(getApplicationContext(),"Correct answer",Toast.LENGTH_SHORT).show();
                               // button2.setBackgroundColor(Color.GREEN);
                                correct = correct +1;
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                       // button2.setBackgroundColor(Color.parseColor("#E3D3EA"));//colour change
                                        update();
                                    }
                                },1000);
                            }
                            else
                            {
                                wrong++;
                               // button2.setBackgroundColor(Color.RED);//colour cahnge
                                if(button1.getText().toString().equals(question.getAnswer()))
                                {
                                   // button1.setBackgroundColor(Color.GREEN);
                                }
                                else if(button3.getText().toString().equals(question.getAnswer()))
                                {
                                    //button3.setBackgroundColor(Color.GREEN);
                                }
                                else if(button4.getText().toString().equals(question.getAnswer()))
                                {
                                    //button4.setBackgroundColor(Color.GREEN);
                                }
                                //colorchangeback();
                                update();
                            }



                        }
                    });
                    button3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(button3.getText().toString().equals(question.getAnswer()))
                            {
                                Toast.makeText(getApplicationContext(),"Correct answer",Toast.LENGTH_SHORT).show();
                               // button3.setBackgroundColor(Color.GREEN);
                                correct = correct +1;
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                     //   button3.setBackgroundColor(Color.parseColor("#E3D3EA"));//colour change
                                        update();
                                    }
                                },1000);
                            }
                            else
                            {
                                wrong++;
                               // button3.setBackgroundColor(Color.RED);//colour cahnge
                                if(button1.getText().toString().equals(question.getAnswer()))
                                {
                                   // button1.setBackgroundColor(Color.GREEN);
                                }
                                else if(button2.getText().toString().equals(question.getAnswer()))
                                {
                                    //button2.setBackgroundColor(Color.GREEN);
                                }
                                else if(button4.getText().toString().equals(question.getAnswer()))
                                {
                                   // button4.setBackgroundColor(Color.GREEN);
                                }
                               // colorchangeback();
                                update();
                            }


                        }
                    });
                    button4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(button4.getText().toString().equals(question.getAnswer()))
                            {
                                Toast.makeText(getApplicationContext(),"Correct answer",Toast.LENGTH_SHORT).show();
                               // button4.setBackgroundColor(Color.GREEN);
                                correct = correct +1;
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                       // button4.setBackgroundColor(Color.parseColor("#E3D3EA"));//colour change
                                        update();
                                    }
                                },1000);
                            }
                            else
                            {
                                wrong++;
                               // button4.setBackgroundColor(Color.RED);//colour cahnge
                                if(button1.getText().toString().equals(question.getAnswer()))
                                {
                                    //button1.setBackgroundColor(Color.GREEN);
                                }
                                else if(button2.getText().toString().equals(question.getAnswer()))
                                {
                                   // button2.setBackgroundColor(Color.GREEN);
                                }
                                else if(button3.getText().toString().equals(question.getAnswer()))
                                {
                                    //button3.setBackgroundColor(Color.GREEN);
                                }
                                //colorchangeback();
                                update();
                            }

                        }
                    });



                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }



    }

    public void colorchangeback() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                button1.setBackgroundColor(Color.parseColor("#E3D3EA"));
                button2.setBackgroundColor(Color.parseColor("#E3D3EA"));
                button3.setBackgroundColor(Color.parseColor("#E3D3EA"));
                button4.setBackgroundColor(Color.parseColor("#E3D3EA"));

            }
        }, 1000);
        //chnage tho color to default
    }
    public void back(View v)
    {
        maxquestions=maxquestions-1;
        update();
    }
    public  void next(View v)
    {
        maxquestions++;
        update();
    }


    public void submit(View v)
    {
        Resultq();
    }
    public void Resultq()
    {
        Intent myIntent = new Intent(Quiz.this,Results.class);
        myIntent.putExtra("total",String.valueOf(max));
        myIntent.putExtra("correct",String.valueOf(correct));
        myIntent.putExtra("incorrect",String.valueOf(wrong));
        myIntent.putExtra ("quizInfo",mQuizInfo);
        myIntent.putExtra ("quizId",quizid);
        finish ();
        startActivity(myIntent);


    }
    public void savve(View v)
    {
        Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
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
                tv.setText("Completed");
                Intent myIntent = new Intent(Quiz.this,Results.class);
                myIntent.putExtra("total",String.valueOf(maxquestions));
                myIntent.putExtra("correct",String.valueOf(correct));
                myIntent.putExtra("incorrect",String.valueOf(wrong));
                myIntent.putExtra ("quizInfo",mQuizInfo);
                myIntent.putExtra ("quizId",quizid);
                finish ();
                startActivity(myIntent);
            }
        }.start();
    }

}
