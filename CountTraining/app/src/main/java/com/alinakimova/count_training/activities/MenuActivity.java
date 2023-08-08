package com.alinakimova.count_training.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.alinakimova.count_training.R;
import com.alinakimova.count_training.activities.LoginActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageButton logout;
    Button easy, medium, hard;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        logout = findViewById(R.id.logout_btn);
        logout.setOnClickListener(this);
        easy = findViewById(R.id.easy_btn);
        easy.setOnClickListener(this);
        medium = findViewById(R.id.medium_btn);
        medium.setOnClickListener(this);
        hard = findViewById(R.id.hard_btn);
        hard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.logout_btn)
            logout();
        else {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            switch (view.getId()) {
                case R.id.easy_btn:
                    intent.putExtra(GameActivity.MODE, 1);
                    break;
                case R.id.medium_btn:
                    intent.putExtra(GameActivity.MODE, 2);
                    break;
                case R.id.hard_btn:
                    intent.putExtra(GameActivity.MODE, 3);
                    break;
            }
            startActivity(intent);
        }
    }

    private void logout() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor ed = pref.edit();
        ed.clear();
        ed.apply();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


}