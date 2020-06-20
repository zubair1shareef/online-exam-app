package com.example.quizappcomplete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.quizappcomplete.DataAdapters.ProfQuestionListAdapter;
import com.example.quizappcomplete.DataAdapters.ResultListAdapter;
import com.example.quizappcomplete.Model.Question;
import com.example.quizappcomplete.Model.Result;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfessorViewResultActivity extends AppCompatActivity {

    private static final String TAG = "ProfessorViewResult";
    String quizId;

    RecyclerView mRecyclerView;
    ArrayList<Result> mResults;
    ResultListAdapter mAdapter;

    FirebaseDatabase mDatabase;
    DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_professor_view_result);

        getSupportActionBar ().setTitle ("Results");
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);

        Intent intent = getIntent ();
        quizId = intent.getStringExtra ("quizId");

        mDatabase = FirebaseDatabase.getInstance ();

        mRecyclerView = findViewById (R.id.result_list_recycler);
        mRecyclerView.setLayoutManager (new LinearLayoutManager (this));
        mResults = new ArrayList<> ();
        mAdapter = new ResultListAdapter (mResults);
        mRecyclerView.setAdapter (mAdapter);

        mReference = mDatabase.getReference ("Results/"+quizId);
        mReference.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists ())
                {
                    for (DataSnapshot data: dataSnapshot.getChildren ())
                    {
                        Log.d (TAG, "onDataChange: "+data);
                        Result result = data.getValue (Result.class);

                        mResults.add (result);
                        mAdapter.notifyDataSetChanged ();
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
