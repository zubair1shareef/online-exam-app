package com.example.quizappcomplete;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.quizappcomplete.DataAdapters.ProfQuestionListAdapter;
import com.example.quizappcomplete.Model.Question;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfessorViewQuestionsActivity extends AppCompatActivity {

    private static final String TAG = "ProfessorViewQuestion" ;
    FloatingActionButton fabNewQuestion;
    String quizId;

    RecyclerView mRecyclerView;
    ArrayList<Question> mQuestionArrayList;
    ArrayList<String> mQuestionNo;
    ProfQuestionListAdapter mAdapter;

    FirebaseDatabase mDatabase;
    DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_professor_view_questions);

        getSupportActionBar ().setTitle ("All Questions");
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);

        Intent intent = getIntent ();
        quizId = intent.getStringExtra ("quizId");

        mDatabase = FirebaseDatabase.getInstance ();

        fabNewQuestion = findViewById (R.id.fab_new_question);
        fabNewQuestion.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ProfessorViewQuestionsActivity.this, ProfessorAddQuestionActivity.class);
                intent.putExtra ("quizId",quizId);
                startActivity (intent);
            }
        });

        mRecyclerView = findViewById (R.id.prof_question_view_list_recycler);
        mRecyclerView.setLayoutManager (new LinearLayoutManager (this));
        mQuestionArrayList = new ArrayList<> ();
        mQuestionNo = new ArrayList<> ();
        mAdapter = new ProfQuestionListAdapter (mQuestionArrayList, this, quizId);
        mRecyclerView.setAdapter (mAdapter);

        mReference = mDatabase.getReference ("Question/"+quizId);

        mReference.addChildEventListener (new ChildEventListener () {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists ())
                {       Log.d (TAG, "onDataChange: "+dataSnapshot);
                        String id = dataSnapshot.getKey ();

                        for(Question ques : mQuestionArrayList)
                        {
                            if(ques.getQuestionno ().equals (id))
                                mQuestionArrayList.remove (ques);
                        }
                        mQuestionNo.remove (id);
                        mAdapter.notifyDataSetChanged ();

                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mReference.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists ())
                {
                    for (DataSnapshot data: dataSnapshot.getChildren ())
                    {
                        Log.d (TAG, "onDataChange: "+data);
                        Question question = data.getValue (Question.class);
                        String id = data.getKey ();

                        if(!mQuestionNo.contains (id)) {
                            mQuestionArrayList.add (question);
                            mQuestionNo.add (id);
                            mAdapter.notifyDataSetChanged ();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
