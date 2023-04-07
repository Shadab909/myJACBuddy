package com.android.myjacbuddy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.android.myjacbuddy.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {
    AutoCompleteTextView user_class;
    public SharedPreferences sp;
    String u_class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        migrate();
        setTheme(R.style.Theme_MyJACBuddy);
        setContentView(R.layout.activity_profile);
        Objects.requireNonNull(getSupportActionBar()).hide();
        user_class = findViewById(R.id.autoCompleteTextView);

        ArrayList<String> dropdown = new ArrayList<>();
        dropdown.add("Class 10");
        dropdown.add("Class 12");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.sample_dropdown_layout, dropdown);
        user_class.setAdapter(adapter);

        MaterialButton save_btn = findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                u_class = user_class.getText().toString();

                sp = getSharedPreferences("Credential", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("userClass", u_class);
                editor.apply();

                if (u_class.equals("Select Your Class")) {

                    Toast toast = Toast.makeText(getApplicationContext(), "Please Fill Details Completely", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                } else {
                    Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(i);
                }

            }
        });
    }


    @Override
    public void onBackPressed() {
        if (sp.contains("userClass")) {
            finish();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Please Fill Details Completely", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

    }

    public void migrate(){
        sp = getSharedPreferences("Credential",MODE_PRIVATE);
        if (sp.contains("userClass")){
            Intent i = new Intent(ProfileActivity.this,MainActivity.class);
            startActivity(i);
        }
    }
}