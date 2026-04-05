package com.example.q1_currencyconverter_siddharth;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
public class MainActivity extends AppCompatActivity {
    private EditText etAmount;
    private Spinner spinnerFrom, spinnerTo;
    private TextView tvResult;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int savedTheme = getSharedPreferences("Settings", MODE_PRIVATE).getInt("Theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        AppCompatDelegate.setDefaultNightMode(savedTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etAmount = findViewById(R.id.etAmount);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        tvResult = findViewById(R.id.tvResult);
        // Currencies: INR, USD, JPY, EUR
        String[] currencies = {"INR", "USD", "JPY", "EUR"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, currencies);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
        findViewById(R.id.btnConvert).setOnClickListener(v -> performConversion());
        findViewById(R.id.btnSettings).setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });
    }
    private void performConversion() {
        if (etAmount.getText().toString().isEmpty()) return;
        double amount;
        try {
            amount = Double.parseDouble(etAmount.getText().toString());
        } catch (NumberFormatException e) {
            return;
        }
        String from = spinnerFrom.getSelectedItem().toString();
        String to = spinnerTo.getSelectedItem().toString();
        // current rates as of 2 april 2026
        double usdRate = 93.10;
        double eurRate = 107.36;
        double jpyRate = 0.61;

        // Convert input to inr first
        double inAmount;
        switch (from) {
            case "USD": inAmount = amount * usdRate; break;
            case "EUR": inAmount = amount * eurRate; break;
            case "JPY": inAmount = amount * jpyRate; break;
            default: inAmount = amount;
        }

        // Convert int to target
        double result;
        switch (to) {
            case "USD": result = inAmount / usdRate; break;
            case "EUR": result = inAmount / eurRate; break;
            case "JPY": result = inAmount / jpyRate; break;
            default: result = inAmount;
        }

        tvResult.setText(String.format("%.2f %s", result, to));

        // swap icon logic
        ImageButton btnSwap = findViewById(R.id.btnSwap);
        btnSwap.setOnClickListener(v -> {
            // 1.current index
            int fromIndex = spinnerFrom.getSelectedItemPosition();
            int toIndex = spinnerTo.getSelectedItemPosition();
            // 2. swapping
            spinnerFrom.setSelection(toIndex);
            spinnerTo.setSelection(fromIndex);
            performConversion();
        });
    }
}