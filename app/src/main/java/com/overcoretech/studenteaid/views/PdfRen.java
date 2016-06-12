package com.overcoretech.studenteaid.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.overcoretech.studenteaid.R;

import java.util.Random;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

public class PdfRen extends AppCompatActivity implements DownloadFile.Listener {

    private RemotePDFViewPager remotePDFViewPager;
    private PDFPagerAdapter adapter;
    private RelativeLayout root;
    private String pdf;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pdf_ren);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            String code  = bundle.getString("code");

            assert code != null;
            switch(code)
            {
                case "ICT 234":
                    pdf = "http://162.243.96.232/pdf/01_Installation.pdf";
                    break;
                case "INF 281":
                    pdf = "http://162.243.96.232/pdf/scalingphp-sample.pdf";
                    break;
                case "INF 192":
                    pdf = "http://162.243.96.232/pdf/01 Introduction.pdf";
                    break;
                case "GTUB 209":
                    pdf = "http://162.243.96.232/pdf/MIS_Lesson1.pdf";
                    break;
                case "ICT 363":
                    pdf = "http://162.243.96.232/pdf/scalingphp-sample.pdf";
                    break;
                case "IT 452":
                    pdf = "http://162.243.96.232/pdf/Graph_Theory.pdf";
                    break;
                case "ICT 333":
                    pdf = "http://162.243.96.232/pdf/02_Retrieving_Data.pdf";
                    break;
                case "INF 372":
                    pdf = "http://162.243.96.232/pdf/02 - Recognizing Opportunities and Generating Ideasxx-1-1.pdf";
                    break;
                case "ICT 314":
                    pdf = "http://162.243.96.232/pdf/Lecture 2.pdf";
                    break;
                case "ICT 464":
                    pdf = "http://162.243.96.232/pdf/MIS_Lesson1.pdf";
                    break;
                case "IT305":
                    pdf = "http://162.243.96.232/pdf/Lecture 3.pdf";
                    break;
                case "INF 162":
                    pdf = "http://162.243.96.232/pdf/Competitive Advantage with IS.pdf";
                    break;

            }
        }
        String[] pdfs = {"http://162.243.96.232/pdf/scalingphp-sample.pdf","http://162.243.96.232/pdf/01_Installation.pdf","http://162.243.96.232/pdf/Graph_Theory.pdf","http://162.243.96.232/pdf/Lecture.pdf","http://162.243.96.232/pdf/the-devops-2-toolkit-sample.pdf"};
        String randomStr = pdfs[new Random().nextInt(pdfs.length)];
        Log.e("PDF", randomStr);

        root = (RelativeLayout) findViewById(R.id.remote_pdf_root);
            remotePDFViewPager = new RemotePDFViewPager(getApplicationContext(), pdf , this);

        }


    @Override
    public void onSuccess(String url, String destinationPath) {
        adapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url), remotePDFViewPager.getOffscreenPageLimit());
        remotePDFViewPager.setAdapter(adapter);

        setContentView(remotePDFViewPager);
//        updateLayout();

    }

    public void updateLayout(){

        root.removeAllViewsInLayout();

        root.addView(remotePDFViewPager, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        adapter.close();
    }
}
