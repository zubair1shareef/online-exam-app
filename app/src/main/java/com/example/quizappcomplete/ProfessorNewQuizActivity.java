package com.example.quizappcomplete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ProfessorNewQuizActivity extends AppCompatActivity {

    EditText etTitle, etDate, etTime, etDuration, etBranch, etSemester, etMarksperQuestion, etTotalMarks;
    Button btnSaveQuiz;

    String mTitle, mDate, mTime, mDuartion, mBranch, mSemester, mMarksperQuestion, mTotalMarks;

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


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
