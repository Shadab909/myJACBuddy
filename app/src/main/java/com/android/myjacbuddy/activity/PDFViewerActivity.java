package com.android.myjacbuddy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import com.android.myjacbuddy.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PDFViewerActivity extends AppCompatActivity {
    String link;
    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MyJACBuddy);
        setContentView(R.layout.activity_pdfviewer);

        pdfView = findViewById(R.id.pdf_viewer);

        link = getIntent().getStringExtra("link");
        new RetriveInputStream().execute(link);

    }
    class  RetriveInputStream extends AsyncTask<String , Void , InputStream> {

        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(PDFViewerActivity.this);
            progressDialog.setTitle("Loading....");
            progressDialog.setMessage("please wait....");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            return inputStream;
        }



        @Override
        protected void onPostExecute(InputStream inputStream) {
           pdfView.fromStream(inputStream).load();
           progressDialog.dismiss();
        }
    }
}