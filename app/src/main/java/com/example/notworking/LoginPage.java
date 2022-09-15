package com.example.notworking;

import static android.util.Patterns.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    EditText email,pass;
    TextView forget_pass, register;
    Button login;
    ProgressBar progressBarLogIn;
    float v = 0;
    public static String PREFS_NAME="MyPrefsFile";

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        getSupportActionBar().hide();


        progressBarLogIn=(ProgressBar)findViewById(R.id.progressBarLogIn);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        forget_pass = (TextView) findViewById(R.id.forgot_pass);

        register = (TextView) findViewById(R.id.register);
        String text = "Don't have an account? Signup";
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan fcsblue = new ForegroundColorSpan(Color.BLUE);

        ss.setSpan(fcsblue, 23,29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        register.setText(ss);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        forget_pass = findViewById(R.id.forgot_pass);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        email.setTranslationX(800);
        pass.setTranslationX(800);
        forget_pass.setTranslationX(800);
        login.setTranslationX(800);
        register.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        forget_pass.setAlpha(v);
        login.setAlpha(v);
        register.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forget_pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        register.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        mAuth = FirebaseAuth.getInstance();
    }

    public void txtLogInForgotPasswordClicked(View view) {
        Intent intent = new Intent(LoginPage.this, ForgotPasswordActivity.class);
        startActivity(intent);

    }

    public void txtLogInRegisterClicked(View view) {
        Intent intent = new Intent(LoginPage.this, SignupPage.class);
        startActivity(intent);
        finish();
    }

    public void buttonLoginClicked(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences(LoginPage.PREFS_NAME,0);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.putBoolean("hasLoggedIn", true);
        editor.commit();



        String userName = email.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if(TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Please enter Email address!", Toast.LENGTH_SHORT).show();
            email.setError("Please enter email address!");
            email.requestFocus();
            return;
        }

        if (!EMAIL_ADDRESS.matcher(userName).matches()) {
            Toast.makeText(this, "Enter valid email address!", Toast.LENGTH_SHORT).show();
            email.setError("Please enter valid email address!");
            email.requestFocus();
            return;
        }

        if(password.length() < 6) {
            Toast.makeText(this, "Please enter password containing atleast six characters", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBarLogIn.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    String uid= task.getResult().getUser().getUid();

                    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                    firebaseDatabase.getReference().child("User").child(uid).child("usertype").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int usertype = snapshot.getValue(Integer.class);
                            if(usertype==0) {
                                progressBarLogIn.setVisibility(View.GONE);
                                Toast.makeText(LoginPage.this, "You have successfully Logged In",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginPage.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }

                            if(usertype==1) {
                                progressBarLogIn.setVisibility(View.GONE);
                                Toast.makeText(LoginPage.this, "Admin has successfully Logged In",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginPage.this, AdminDashboard.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else {
                    progressBarLogIn.setVisibility(View.GONE);
                    Toast.makeText(LoginPage.this, "User failed to log In",Toast.LENGTH_LONG).show();

                }
            }
        });




    }
}