package com.example.quizappcomplete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.quizappcomplete.Model.QuizInfo;
import com.example.quizappcomplete.Model.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ProfessorNewQuizActivity extends AppCompatActivity {

    EditText etTitle, etDate, etTime, etDuration, etBranch, etSemester, etMarksperQuestion, etTotalMarks;
    Button btnSaveQuiz;

    String mTitle, mDate, mTime, mDuartion, mBranch, mSemester, mMarksperQuestion, mTotalMarks, mInstituteCode, mCreatedBy, mCreatedByUid;

    public static String MY_PREFS_NAME= "CurrentUser";
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_new_quiz);

        getSupportActionBar ().setTitle ("New Quiz");
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);

        etTitle = findViewById (R.id.etQuizTitle);
        etDate = findViewById (R.id.etQuizDate);
        etTime = findViewById (R.id.etQuizTime);
        etDuration = findViewById (R.id.etQuizDuration);
        etBranch = findViewById (R.id.etBranch_NewQuiz);
        etSemester = findViewById (R.id.etSemester_NewQuiz);
        etMarksperQuestion = findViewById (R.id.etMarksPerQuestion);
        etTotalMarks = findViewById (R.id.etTotalMarks);
        btnSaveQuiz = findViewById (R.id.btnSaveQuiz);

        mAuth = FirebaseAuth.getInstance ();
        mDatabase = FirebaseDatabase.getInstance ();

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("user", "");
        final User user = gson.fromJson(json, User.class);

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                etDate.setText(sdf.format(myCalendar.getTime()));
            }

        };

        etDate.setOnClickListener(new View.OnClickListener () {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(ProfessorNewQuizActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener (){

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                etTime.setText(String.valueOf(hourOfDay)
                        + " : " + String.valueOf(minute));
            }
        };

        etTime.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(ProfessorNewQuizActivity.this,time, myCalendar.get(Calendar.HOUR_OF_DAY),
                        myCalendar.get(Calendar.MINUTE),
                        DateFormat.is24HourFormat(ProfessorNewQuizActivity.this)).show ();
            }
        });

        btnSaveQuiz.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                mTitle = etTitle.getText ().toString ();
                mDate = etDate.getText ().toString ();
                mTime = etTime.getText ().toString ();
                mDuartion = etDuration.getText ().toString ();
                mBranch = etBranch.getText ().toString ();
                mSemester = etSemester.getText ().toString ();
                mMarksperQuestion = etMarksperQuestion.getText ().toString ();
                mTotalMarks = etTotalMarks.getText ().toString ();
                mInstituteCode = user.getInstituteCode ();
                mCreatedBy = user.getName ();
                mCreatedByUid = mAuth.getUid ();

                QuizInfo quizInfo = new QuizInfo (mTitle,mDate,mTime,mDuartion,mBranch,mSemester,mMarksperQuestion,mTotalMarks,mInstituteCode,mCreatedBy,mCreatedByUid);

                mReference = mDatabase.getReference ("Quiz");
                mReference = mReference.push ();
                mReference.setValue (quizInfo);

                String quizId = mReference.getKey ();

                Intent intent = new Intent (ProfessorNewQuizActivity.this, ProfessorViewQuestionsActivity.class);
                intent.putExtra ("quizId",quizId);
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
}
