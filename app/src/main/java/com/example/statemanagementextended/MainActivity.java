package com.example.statemanagementextended;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {


    private EditText editText;
    private Switch switchTheme;
    private CountViewModel countViewModel;
    private TextView textViewCount;
    private CheckBox checkBox;
    private TextView checkboxon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewCount = findViewById(R.id.textViewCount);
        Button buttonIncrement = findViewById(R.id.buttonIncrement);

        editText = findViewById(R.id.editText);
        checkBox = findViewById(R.id.checkBox);
        checkboxon = findViewById(R.id.checkboxon);
        switchTheme = findViewById(R.id.switchTheme);

        countViewModel = new ViewModelProvider(this).get(CountViewModel.class);
        updateCountText();

        if (savedInstanceState != null) {
            textViewCount.setText(savedInstanceState.getString("counter", "Licznik: 0"));
            editText.setText(savedInstanceState.getString("editText", ""));
            checkBox.setChecked(savedInstanceState.getBoolean("checkBox", false));
            checkboxon.setVisibility(checkBox.isChecked() ? View.VISIBLE : View.GONE);
            switchTheme.setChecked(savedInstanceState.getBoolean("switchtheme", false));
        }
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) ->{
            checkboxon.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });
        switchTheme.setOnCheckedChangeListener((buttonView, isChecked) ->{
            setTheme(isChecked);
        });
        updateCountText();

        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countViewModel.incrementCount();
                updateCountText();
            }
        });
    }
    private void setTheme(boolean DarkTheme) {
        if (DarkTheme) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("editText", editText.getText().toString());
        outState.putBoolean("switchTheme", switchTheme.isChecked());
        outState.putString("counter", textViewCount.getText().toString());
        outState.putBoolean("checkBox", checkBox.isChecked());
    }

    private void updateCountText() {
        textViewCount.setText("Licznik: " + countViewModel.getCount());

    }
}