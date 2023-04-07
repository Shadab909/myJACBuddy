package com.android.myjacbuddy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.android.myjacbuddy.R;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class FirstIntroActivity extends AppCompatActivity {
    private int temp_1 = 0;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        migrate();
        setTheme(R.style.Theme_MyJACBuddy);
        setContentView(R.layout.activity_first_intro);
        Objects.requireNonNull(getSupportActionBar()).hide();
        MaterialButton next_btn = findViewById(R.id.next_btn);
        sp = getSharedPreferences("Credential",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();


        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstIntroActivity.this,GetStartedActivity.class));
                temp_1 = temp_1 + 1;
                editor.putInt("temp_1",temp_1);
                editor.apply();
                finish();
            }
        });
    }

    public void migrate(){
        sp = getSharedPreferences("Credential",MODE_PRIVATE);
        int val1 = sp.getInt("temp_1",0);
        int val2 = sp.getInt("temp_2",0);
        if (val1 > 0&&val2>0&&sp.contains("userClass")){
            Intent i = new Intent(FirstIntroActivity.this,MainActivity.class);
            startActivity(i);
        }else if (val1>0){
            Intent i = new Intent(FirstIntroActivity.this,GetStartedActivity.class);
            startActivity(i);
        }
    }
}