package com.example.q1_currencyconverter_siddharth;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.q1_currencyconverter_siddharth.R;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        RadioGroup themeGroup = findViewById(R.id.themeGroup);
        themeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbDark) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                saveTheme(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                saveTheme(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }
    private void saveTheme(int mode) {
        getSharedPreferences("Settings", MODE_PRIVATE)
                .edit().putInt("Theme", mode).apply();
    }
}