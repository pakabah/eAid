package com.overcoretech.studenteaid.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.overcoretech.studenteaid.R;
import com.overcoretech.studenteaid.adapter.QuestionsAdapter;
import com.overcoretech.studenteaid.db.DBHelper;
import com.overcoretech.studenteaid.template.QuestionsTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StartPasco extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView countDown;
    boolean blink;
    FloatingActionButton markFinish;
    String code;

    private static final String FORMAT = "%02d:%02d:%02d";
    long timeBlinkInMilliseconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_pasco);

        initToolbar();

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            code = bundle.getString("code");
        }

        recyclerView = (RecyclerView) findViewById(R.id.relQuestions);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        QuestionsAdapter questionsAdapter = new QuestionsAdapter(initializeData(code));
        recyclerView.setAdapter(questionsAdapter);

        markFinish = (FloatingActionButton) findViewById(R.id.markFinish);
        markFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScoreSummary.class);
                intent.putExtra("code", code);
                startActivity(intent);
                finish();
            }
        });

        countDown = (TextView) findViewById(R.id.countDown);
        timeBlinkInMilliseconds = 30 * 1000;
        new CountDownTimer(3600000, 1000) {

            public void onTick(long millisUntilFinished) {

                if(millisUntilFinished < timeBlinkInMilliseconds)
                {
                    countDown.setTextAppearance(R.style.blinkText);
                    if ( blink ) {
                        countDown.setVisibility(View.VISIBLE);
                        // if blink is true, textview will be visible
                    } else {
                        countDown.setVisibility(View.INVISIBLE);
                    }
                    blink = !blink;
                }

                countDown.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                countDown.setVisibility(View.VISIBLE);
              countDown.setText("Time Up");
            }
        }.start();
    }


    public List<QuestionsTemplate> initializeData(String code)
    {
//        List<QuestionsTemplate> questionsTemplates = new ArrayList<>();
//        questionsTemplates.add(new QuestionsTemplate("1","Test Question","Answer One","Answer Two","Answer Three", "Answer Four"));
//        questionsTemplates.add(new QuestionsTemplate("2","Test Question","Answer One","Answer Two","Answer Three", "Answer Four"));
//        questionsTemplates.add(new QuestionsTemplate("3","Test Question","Answer One","Answer Two","Answer Three", "Answer Four"));
//        questionsTemplates.add(new QuestionsTemplate("4","Test Question","Answer One","Answer Two","Answer Three", "Answer Four"));
//        questionsTemplates.add(new QuestionsTemplate("5","Test Question","Answer One","Answer Two","Answer Three", "Answer Four"));
//        questionsTemplates.add(new QuestionsTemplate("6","Test Question","Answer One","Answer Two","Answer Three", "Answer Four"));
//        questionsTemplates.add(new QuestionsTemplate("7","Test Question","Answer One","Answer Two","Answer Three", "Answer Four"));
//        questionsTemplates.add(new QuestionsTemplate("8","Test Question","Answer One","Answer Two","Answer Three", "Answer Four"));
//        questionsTemplates.add(new QuestionsTemplate("9","Test Question","Answer One","Answer Two","Answer Three", "Answer Four"));
//        questionsTemplates.add(new QuestionsTemplate("10","Test Question","Answer One","Answer Two","Answer Three", "Answer Four"));
//        questionsTemplates.add(new QuestionsTemplate("11","Test Question","Answer One","Answer Two","Answer Three", "Answer Four"));
//        questionsTemplates.add(new QuestionsTemplate("12","Test Question","Answer One","Answer Two","Answer Three", "Answer Four"));
//        questionsTemplates.add(new QuestionsTemplate("13","Test Question","Answer One","Answer Two","Answer Three", "Answer Four"));
//        questionsTemplates.add(new QuestionsTemplate("14","Test Question","Answer One","Answer Two","Answer Three", "Answer Four"));

        DBHelper dbHelper = new DBHelper(getApplicationContext());
        return dbHelper.getQuestions(code);

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(" ");
        }
    }
}
