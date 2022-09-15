package com.example.notworking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    Button login,signup;
    float v = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("I am Paresh and it is welcome");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("JagdambaEnterprises", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("JagdambaEnterprises", "Failed to read value.", error.toException());
            }
        });

        login =  findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        login.setTranslationX(800);
        signup.setTranslationX(800);

        login.setAlpha(v);
        signup.setAlpha(v);

        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        signup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();


    }
        public void openLoginPage(View view) {
            Intent intent = new Intent(this, LoginPage.class);
            startActivity(intent);
            finish();
        }

    public void openSignupPage(View view) {
        Intent intent = new Intent(this, SignupPage.class);
        startActivity(intent);
        finish();
    }


    }

