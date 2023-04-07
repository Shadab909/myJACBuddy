package com.android.myjacbuddy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.myjacbuddy.R;

public class SubjectListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MyJACBuddy);
        setContentView(R.layout.activity_subject_list);
    }
}