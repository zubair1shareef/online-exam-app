package com.example.quizappcomplete;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class ProfessorViewQuestionsActivity extends AppCompatActivity {

    FloatingActionButton fabNewQuestion;
    String quizId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_professor_view_questions);

        getSupportActionBar ().setTitle ("All Questions");
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);

        Intent intent = getIntent ();
        quizId = intent.getStringExtra ("quizId");

        fabNewQuestion = findViewById (R.id.fab_new_question);
        fabNewQuestion.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ProfessorViewQuestionsActivity.this, ProfessorAddQuestionActivity.class);
                intent.putExtra ("quizId",quizId);
                startActivity (intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
