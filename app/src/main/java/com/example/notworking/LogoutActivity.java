package com.example.notworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class LogoutActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        getSupportActionBar().hide();

        button = findViewById(R.id.logout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(LoginPage.PREFS_NAME,0);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(LogoutActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(LogoutActivity.this,"You have successfully Logged Out",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}