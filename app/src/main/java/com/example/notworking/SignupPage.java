package com.example.notworking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupPage extends AppCompatActivity {
    EditText name,email,mobile,area,flatNo,pass,confirmPass;
    TextView logreg;
    Button signup;
    float v=0;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        getSupportActionBar().hide();


        logreg = (TextView) findViewById(R.id.logreg);
        String text = "Already have an account? Login";
        SpannableString s = new SpannableString(text);
        ForegroundColorSpan fcblue = new ForegroundColorSpan(Color.BLUE);

        s.setSpan(fcblue, 25,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        logreg.setText(s);



        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        mobile=findViewById(R.id.mobile);
        area=findViewById(R.id.area);
        flatNo=findViewById(R.id.flatNo);
        pass=findViewById(R.id.pass);
        confirmPass=findViewById(R.id.confirmPass);
        signup=findViewById(R.id.signup);
        progressBar=findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        name.setTranslationX(800);
        email.setTranslationX(800);
        mobile.setTranslationX(800);
        area.setTranslationX(800);
        flatNo.setTranslationX(800);
        pass.setTranslationX(800);
        confirmPass.setTranslationX(800);
        signup.setTranslationY(800);
        logreg.setTranslationY(800);

        name.setAlpha(v);
        email.setAlpha(v);
        mobile.setAlpha(v);
        area.setAlpha(v);
        flatNo.setAlpha(v);
        pass.setAlpha(v);
        confirmPass.setAlpha(v);
        signup.setAlpha(v);
        logreg.setAlpha(v);

        name.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        mobile.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        area.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        flatNo.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1200).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1500).start();
        confirmPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1800).start();
        signup.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(2100).start();
        logreg.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(2100).start();

    }


    public void signupButtonCLicked(View view) {

        String txtname = name.getText().toString().trim();
        String txtmail = email.getText().toString().trim();
        String txtmobile = mobile.getText().toString().trim();
        String txtarea = area.getText().toString().trim();
        String txtflat = flatNo.getText().toString().trim();
        String txtpass = pass.getText().toString().trim();
        String txtconfirm = confirmPass.getText().toString().trim();

        if(TextUtils.isEmpty(txtname)) {
            Toast.makeText(this, "Please enter your name!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(txtmail)) {
            Toast.makeText(this, "Please enter Email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(txtmail).matches()) {
            Toast.makeText(this, "Enter valid email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(txtmobile)) {
            Toast.makeText(this, "Please enter your Mobile No", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(txtarea)) {
            Toast.makeText(this, "Please enter your Area", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(txtflat)) {
            Toast.makeText(this, "Please enter your Flat No", Toast.LENGTH_SHORT).show();
            return;
        }

        if(txtpass.length() < 6) {
            Toast.makeText(this, "Please enter password containing atleast six characters", Toast.LENGTH_SHORT).show();
            return;
        }


        if(!txtconfirm.matches(txtpass)) {
            confirmPass.setError("Doesn't match the password");
            confirmPass.requestFocus();
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(txtmail, txtpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    User user = new User(txtname,txtpass,txtmail,txtmobile,txtarea,txtflat,txtconfirm,0);

                    FirebaseDatabase.getInstance().getReference("User")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(SignupPage.this,"User registered successfully", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(SignupPage.this, LoginPage.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(SignupPage.this,"User Failed to Register", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(SignupPage.this,"User Failed to Register", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }

    public void txtLogInClicked(View view) {
        Intent intent = new Intent(SignupPage.this, LoginPage.class);
        startActivity(intent);
        finish();
    }
}