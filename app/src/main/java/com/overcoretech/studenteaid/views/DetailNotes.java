package com.overcoretech.studenteaid.views;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.overcoretech.studenteaid.R;
import com.overcoretech.studenteaid.db.DBHelper;
import com.overcoretech.studenteaid.template.NotesTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DetailNotes extends AppCompatActivity {

    private String title;
    private String code;
    TextView lecturer;
    TextView description;
    Button download,local;
    String url;
    private ProgressDialog pDialog;
    private static final int progress_bar_type = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notes);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            title = bundle.getString("title");
            code = bundle.getString("code");
        }

        initToolbar(title);

        lecturer = (TextView) findViewById(R.id.lecturer);
        description = (TextView) findViewById(R.id.description);
        local = (Button) findViewById(R.id.local);

        DBHelper dbHelper = new DBHelper(getApplicationContext());
        NotesTemplate notesTemplate =  dbHelper.getNote(code);

        lecturer.setText(notesTemplate.lecturer);
        description.setText(notesTemplate.description);

        download = (Button) findViewById(R.id.download);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PdfRen.class);
                intent.putExtra("code",code);
                startActivity(intent);
            }
        });


        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{
                                android.Manifest.permission.INTERNET,
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                        }, 1217);
                    }
                    return;
                }
                else
                {
                    if(isExternalStorageWritable())
                    {
                        switch(code)
                        {
                            case "ICT 234":
                                url = "http://162.243.96.232/pdf/01_Installation.pdf";
                                break;
                            case "INF 281":
                                url = "http://162.243.96.232/pdf/scalingphp-sample.pdf";
                                break;
                            case "INF 192":
                                url = "http://162.243.96.232/pdf/01 Introduction.pdf";
                                break;
                            case "GTUB 209":
                                url = "http://162.243.96.232/pdf/MIS_Lesson1.pdf";
                                break;
                            case "ICT 363":
                                url = "http://162.243.96.232/pdf/scalingphp-sample.pdf";
                                break;
                            case "IT 452":
                                url = "http://162.243.96.232/pdf/Graph_Theory.pdf";
                                break;
                            case "ICT 333":
                                url = "http://162.243.96.232/pdf/02_Retrieving_Data.pdf";
                                break;
                            case "INF 372":
                                url = "http://162.243.96.232/pdf/02 - Recognizing Opportunities and Generating Ideasxx-1-1.pdf";
                                break;
                            case "ICT 314":
                                url = "http://162.243.96.232/pdf/Lecture 2.pdf";
                                break;
                            case "ICT 464":
                                url = "http://162.243.96.232/pdf/MIS_Lesson1.pdf";
                                break;
                            case "IT305":
                                url = "http://162.243.96.232/pdf/Lecture 3.pdf";
                                break;
                            case "INF 162":
                                url = "http://162.243.96.232/pdf/Competitive Advantage with IS.pdf";
                                break;

                        }
                        DownloadFile(getAlbumStorageDir());
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Sorry External Storage Issues", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type:
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    private File getAlbumStorageDir() {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "pdf");
        if (!file.mkdirs()) {
            Log.e("DIR", "Directory not created");
        }
        return file;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode)
        {
            case 1217:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if(isExternalStorageWritable())
                    {
                        DownloadFile(getAlbumStorageDir());
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Sorry External Storage Issues", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
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

    public void showPdf()
    {
        File file = new File(Environment.getExternalStorageDirectory()+"/Mypdf/Read.pdf");
        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/pdf");
        startActivity(intent);
    }

    private void DownloadFile(File directory) {

         class Downloader extends AsyncTask<File, Void, String> {

             @Override
             protected void onPreExecute() {
                 super.onPreExecute();
                 showDialog(progress_bar_type);
             }

             @Override
             protected void onProgressUpdate(Void... values) {
                 super.onProgressUpdate(values);
             }

             @Override
             protected String doInBackground(File... params) {
                 File paramDirectory = params[0];

                 try {

                     URL urls = new URL(url);
                     HttpURLConnection httpConn = (HttpURLConnection) urls.openConnection();
                     int responseCode = httpConn.getResponseCode();

                     int lenghtOfFile = httpConn.getContentLength();

                     // always check HTTP response code first
                     if (responseCode == HttpURLConnection.HTTP_OK) {
                         String fileName = "";
                         String disposition = httpConn.getHeaderField("Content-Disposition");
                         String contentType = httpConn.getContentType();
                         int contentLength = httpConn.getContentLength();

                         if (disposition != null) {
                             // extracts file name from header field
                             int index = disposition.indexOf("filename=");
                             if (index > 0) {
                                 fileName = disposition.substring(index + 10,
                                         disposition.length() - 1);
                             }
                         } else {
                             // extracts file name from URL
                             fileName = url.substring(url.lastIndexOf("/") + 1,
                                     url.length());
                         }

                         System.out.println("Content-Type = " + contentType);
                         System.out.println("Content-Disposition = " + disposition);
                         System.out.println("Content-Length = " + contentLength);
                         System.out.println("fileName = " + fileName);

                         // opens input stream from the HTTP connection
                         InputStream inputStream = httpConn.getInputStream();
                         String saveFilePath = paramDirectory + File.separator + fileName;

                         // opens an output stream to save into file
                         FileOutputStream outputStream = new FileOutputStream(saveFilePath);

                         int bytesRead = -1;
                         long total = 0;
                         byte[] buffer = new byte[1024];
                         while ((bytesRead = inputStream.read(buffer)) != -1) {
                             total += bytesRead;

                             outputStream.write(buffer, 0, bytesRead);
                         }

                         outputStream.close();
                         inputStream.close();

                         System.out.println("File downloaded");
                     } else {
                         System.out.println("No file to download. Server replied HTTP code: " + responseCode);
                     }
                     httpConn.disconnect();
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
                 return null;
             }

             @Override
             protected void onPostExecute(String s) {
                 dismissDialog(progress_bar_type);
                 super.onPostExecute(s);
             }
         }

        Downloader downloader = new Downloader();
        downloader.execute(directory);
    }

}
