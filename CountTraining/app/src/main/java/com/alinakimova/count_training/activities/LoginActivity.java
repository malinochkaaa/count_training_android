package com.alinakimova.count_training.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alinakimova.count_training.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends Activity implements View.OnClickListener {

    FirebaseAuth auth;

    Button login_btn;
    Button signin_btn;
    TextInputEditText login_txt;
    TextInputEditText password_txt;

    SharedPreferences pref;
    final static String LOGIN = "login";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);
        signin_btn = findViewById(R.id.signin_btn);
        signin_btn.setOnClickListener(this);
        login_txt = findViewById(R.id.login_txt);
        password_txt = findViewById(R.id.password_txt);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        String savedLogin = pref.getString(LOGIN, null);

        if (savedLogin != null){
            openActivity();
        }

        auth = FirebaseAuth.getInstance();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn:
                login();
                break;
            case R.id.signin_btn:
                signIn();
                break;
        }
    }

    private void login(){
        String user = login_txt.getText().toString();
        String pass = password_txt.getText().toString();
        auth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(task -> {
            if (task.isSuccessful())
            {
                SharedPreferences.Editor ed = pref.edit();
                ed.putString(LOGIN, user);
                ed.commit();
                openActivity();
            } else
            {
                Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void openActivity(){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void signIn(){
        String user = login_txt.getText().toString();
        String pass = password_txt.getText().toString();
        auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(task -> {
            if (task.isSuccessful())
            {
                Toast.makeText(LoginActivity.this, "Registration successful!", Toast.LENGTH_LONG).show();
            } else
            {
                Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
