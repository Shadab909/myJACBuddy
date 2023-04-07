package com.android.myjacbuddy.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.android.myjacbuddy.R;


import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MyJACBuddy);
        setContentView(R.layout.activity_setting);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Settings");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(12);

        ConstraintLayout setting_1 = findViewById(R.id.setting_1);
        setting_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogMethod();
            }
        });

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        // after on CLick we are using finish to close and then just after that
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void dialogMethod(){

        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(this);

        builder.setMessage("Do you want to change your name or class ?");

        builder.setTitle("Alert !");

        builder.setCancelable(false);



        builder
                .setPositiveButton(
                        "Yes",
                        (dialog, which) -> {

                            SharedPreferences sp = getSharedPreferences("Credential", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.remove("userClass");
                            editor.apply();
                            Intent i = new Intent(SettingsActivity.this, ProfileActivity.class);
                            startActivity(i);
                            finish();
                        });

        builder
                .setNegativeButton(
                        "No",
                        (dialog, which) -> {
                            dialog.cancel();
                        });

        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }

}