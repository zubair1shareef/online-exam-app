package com.example.quizappcomplete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizappcomplete.Model.Setquestions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Quiz extends AppCompatActivity {
    Button button1,button2,button3,button4;
    TextView textView;
    int wrong=0,correct=0,max=5,maxquestions=0;
    DatabaseReference reference;
    String time ,quizid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
        button3=(Button) findViewById(R.id.button3);
        button4=(Button) findViewById(R.id.button4);
        textView=(TextView) findViewById(R.id.textView);
        Intent i = getIntent();
        quizid = i.getStringExtra("quizid");
        Toast.makeText(getApplicationContext(),quizid,Toast.LENGTH_SHORT).show();

        update();
    }
    public void update() {
        if(maxquestions==max)
        {
            Intent myIntent = new Intent(Quiz.this,Results.class);
           myIntent.putExtra("total",String.valueOf(max));
            myIntent.putExtra("correct",String.valueOf(correct));
            myIntent.putExtra("incorrect",String.valueOf(wrong));
            startActivity(myIntent);

        }
        else
        {  maxquestions++;
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
                                button1.setBackgroundColor(Color.GREEN);
                                correct = correct +1;
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        button1.setBackgroundColor(Color.parseColor("#E3D3EA"));//colour change
                                        update();
                                    }
                                },1000);
                            }
                            else
                            {
                                wrong++;
                                button1.setBackgroundColor(Color.RED);//colour cahnge
                                if(button2.getText().toString().equals(question.getAnswer()))
                                {
                                    button2.setBackgroundColor(Color.GREEN);
                                }
                                else if(button3.getText().toString().equals(question.getAnswer()))
                                {
                                    button3.setBackgroundColor(Color.GREEN);
                                }
                                else if(button4.getText().toString().equals(question.getAnswer()))
                                {
                                    button4.setBackgroundColor(Color.GREEN);
                                }
                                colorchangeback();
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
                                button2.setBackgroundColor(Color.GREEN);
                                correct = correct +1;
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        button2.setBackgroundColor(Color.parseColor("#E3D3EA"));//colour change
                                        update();
                                    }
                                },1000);
                            }
                            else
                            {
                                wrong++;
                                button2.setBackgroundColor(Color.RED);//colour cahnge
                                if(button1.getText().toString().equals(question.getAnswer()))
                                {
                                    button1.setBackgroundColor(Color.GREEN);
                                }
                                else if(button3.getText().toString().equals(question.getAnswer()))
                                {
                                    button3.setBackgroundColor(Color.GREEN);
                                }
                                else if(button4.getText().toString().equals(question.getAnswer()))
                                {
                                    button4.setBackgroundColor(Color.GREEN);
                                }
                                colorchangeback();
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
                                button3.setBackgroundColor(Color.GREEN);
                                correct = correct +1;
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        button3.setBackgroundColor(Color.parseColor("#E3D3EA"));//colour change
                                        update();
                                    }
                                },1000);
                            }
                            else
                            {
                                wrong++;
                                button3.setBackgroundColor(Color.RED);//colour cahnge
                                if(button1.getText().toString().equals(question.getAnswer()))
                                {
                                    button1.setBackgroundColor(Color.GREEN);
                                }
                                else if(button2.getText().toString().equals(question.getAnswer()))
                                {
                                    button2.setBackgroundColor(Color.GREEN);
                                }
                                else if(button4.getText().toString().equals(question.getAnswer()))
                                {
                                    button4.setBackgroundColor(Color.GREEN);
                                }
                                colorchangeback();
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
                                button4.setBackgroundColor(Color.GREEN);
                                correct = correct +1;
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        button4.setBackgroundColor(Color.parseColor("#E3D3EA"));//colour change
                                        update();
                                    }
                                },1000);
                            }
                            else
                            {
                                wrong++;
                                button4.setBackgroundColor(Color.RED);//colour cahnge
                                if(button1.getText().toString().equals(question.getAnswer()))
                                {
                                    button1.setBackgroundColor(Color.GREEN);
                                }
                                else if(button2.getText().toString().equals(question.getAnswer()))
                                {
                                    button2.setBackgroundColor(Color.GREEN);
                                }
                                else if(button3.getText().toString().equals(question.getAnswer()))
                                {
                                    button3.setBackgroundColor(Color.GREEN);
                                }
                                colorchangeback();
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

}
