package com.example.notworking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextView editTextEmail;
    ProgressBar progressBarForgotPass;
    Button resetPasswordButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        getSupportActionBar().hide();


        editTextEmail = (TextView) findViewById(R.id.editTextEmail);
        progressBarForgotPass = (ProgressBar) findViewById(R.id.progressBarForgotPass);
        resetPasswordButton = (Button) findViewById(R.id.resetPass);
        mAuth = FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {

        String email = editTextEmail.getText().toString().trim();

        if(email.isEmpty()) {
            Toast.makeText(this, "Please enter Email address!", Toast.LENGTH_SHORT).show();
            return;
        }


        progressBarForgotPass.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(ForgotPasswordActivity.this,"Please Check Your Email to Reset the Password", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ForgotPasswordActivity.this, LoginPage.class);
                    startActivity(intent);
                    progressBarForgotPass.setVisibility((View.GONE));
                }
                else {
                    Toast.makeText(ForgotPasswordActivity.this,"Failed to Reset the Password", Toast.LENGTH_LONG).show();
                    progressBarForgotPass.setVisibility((View.GONE));

                }

                }

        });




    }



}