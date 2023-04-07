package com.android.myjacbuddy.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.android.myjacbuddy.R;
import com.android.myjacbuddy.databinding.ActivityMainBinding;
import com.android.myjacbuddy.fragment.DownloadFragment;
import com.android.myjacbuddy.fragment.HomeFragment;
import com.android.myjacbuddy.fragment.InfoFragment;
import com.android.myjacbuddy.utils.ExitDialog;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final int HOME = R.id.navigation_home;
    private static final int INFO = R.id.navigation_notifications;
    private static final int DOWNLOAD = R.id.navigation_dashboard;
    ActivityMainBinding binding;
    HomeFragment homeFragment = new HomeFragment();
    InfoFragment infoFragment = new InfoFragment();
    DownloadFragment downloadFragment = new DownloadFragment();
    Fragment temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MyJACBuddy);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        binding.bottomNavView.setSelectedItemId(R.id.navigation_home);

        binding.bottomNavView.setOnNavigationItemSelectedListener(item -> {
            temp = null;
            switch (item.getItemId()) {
                case HOME:
                    temp = homeFragment;
                    break;
                case INFO:
                    temp = infoFragment;
                    break;
                case DOWNLOAD:
                    temp = downloadFragment;
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.container, temp).commit();
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        if (binding.bottomNavView.getSelectedItemId() == R.id.navigation_home) {

            ExitDialog.exit_dialog(MainActivity.this);

        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
            binding.bottomNavView.setSelectedItemId(R.id.navigation_home);
        }
    }
}