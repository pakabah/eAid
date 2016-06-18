package com.overcoretech.studenteaid.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.overcoretech.studenteaid.MainActivity;
import com.overcoretech.studenteaid.R;
import com.overcoretech.studenteaid.db.DBHelper;
import com.overcoretech.studenteaid.template.CourseTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class ScoreSummary extends AppCompatActivity {

    Button finishBtn,retake,chooseAnother;
    String code;
    TextView title;
    TextView score;
    String mScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_summary);
        initToolbar();

        title = (TextView) findViewById(R.id.title);
        score = (TextView) findViewById(R.id.score);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            code = bundle.getString("code");
        }

        DBHelper dbHelper = new DBHelper(getApplicationContext());
        CourseTemplate courseTemplate = dbHelper.getCourse(code);

        title.setText(courseTemplate.courseName);
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        int Score = sharedPreferences.getInt("currentScore", 0);
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((5 - 1) + 1) + 1;
        Log.e("Final Score", Score+"");
        mScore = randomNum+"/5";
        score.setText(mScore);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        dbHelper.insertScore(title.getText().toString(),formattedDate,mScore);



        finishBtn = (Button) findViewById(R.id.finish);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        retake = (Button) findViewById(R.id.retake);
        retake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartPasco.class);
                intent.putExtra("code", code);
                intent.putExtra("title", title.getText().toString());
                startActivity(intent);
                finish();
            }
        });

        chooseAnother = (Button) findViewById(R.id.another);
        chooseAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(" ");
        }
    }
}
