package com.overcoretech.studenteaid.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.overcoretech.studenteaid.R;
import com.overcoretech.studenteaid.db.DBHelper;
import com.overcoretech.studenteaid.template.CourseTemplate;

public class DetailPasco extends AppCompatActivity {

    Button start;
    String title;
    String code;
    TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pasco);


        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
         title = bundle.getString("title");
            code = bundle.getString("code");
        }

        initToolbar(title);

        start = (Button) findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartPasco.class);
                intent.putExtra("code", code);
                startActivity(intent);
            }
        });

        desc = (TextView) findViewById(R.id.desc);
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        CourseTemplate  courseTemplate = dbHelper.getCourse(code);
        desc.setText(courseTemplate.description);
    }


    private void initToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
