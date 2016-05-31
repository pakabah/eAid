package com.overcoretech.studenteaid.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.overcoretech.studenteaid.R;

public class PdfRen extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pdf_ren);

            WebView webview = (WebView) findViewById(R.id.webView);
            webview.getSettings().setJavaScriptEnabled(true);
            String pdf = "http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf";
            webview.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);
        }


}
