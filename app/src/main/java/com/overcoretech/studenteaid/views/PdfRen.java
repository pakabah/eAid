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

    RemotePDFViewPager remotePDFViewPager;
    PDFPagerAdapter adapter;
    RelativeLayout root;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pdf_ren);
        String[] pdf = {"http://162.243.96.232/pdf/scalingphp-sample.pdf","http://162.243.96.232/pdf/01_Installation.pdf","http://162.243.96.232/pdf/Graph_Theory.pdf","http://162.243.96.232/pdf/Lecture.pdf","http://162.243.96.232/pdf/the-devops-2-toolkit-sample.pdf"};
        String randomStr = pdf[new Random().nextInt(pdf.length)];
        Log.e("PDF", randomStr);

        root = (RelativeLayout) findViewById(R.id.remote_pdf_root);
            remotePDFViewPager = new RemotePDFViewPager(getApplicationContext(), randomStr , this);

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
