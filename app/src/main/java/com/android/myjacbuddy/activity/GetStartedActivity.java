package com.android.myjacbuddy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.android.myjacbuddy.R;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class GetStartedActivity extends AppCompatActivity {
    private int temp_2 = 0;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        migrate();
        setTheme(R.style.Theme_MyJACBuddy);
        setContentView(R.layout.activity_get_started);
        Objects.requireNonNull(getSupportActionBar()).hide();
        MaterialButton continue_btn = findViewById(R.id.continue_btn);
        sp = getSharedPreferences("Credential",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GetStartedActivity.this,ProfileActivity.class));
                temp_2 = temp_2 + 1;
                editor.putInt("temp_2",temp_2);
                editor.apply();
                finish();
            }
        });
    }


    public void migrate(){
        sp = getSharedPreferences("Credential",MODE_PRIVATE);
        int val = sp.getInt("temp_2",0);
        if (val > 0&&sp.contains("userClass")){
            Intent i = new Intent(GetStartedActivity.this,MainActivity.class);
            startActivity(i);
        }else if (val>0){
            Intent i = new Intent(GetStartedActivity.this,ProfileActivity.class);
            startActivity(i);
        }
    }
}