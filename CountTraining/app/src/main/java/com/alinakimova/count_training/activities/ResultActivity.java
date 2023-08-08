package com.alinakimova.count_training.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alinakimova.count_training.R;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTxt;
    Button resultBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        int correct = intent.getIntExtra(GameActivity.RESULT, 0);
        resultTxt = findViewById(R.id.results);
        resultTxt.setText("Your result is " + correct + "/5!");
        resultBtn = findViewById(R.id.result_btn);
        resultBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.result_btn) {
            finish();
        }
    }
}