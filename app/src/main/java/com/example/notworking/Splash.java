package com.example.notworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Splash extends AppCompatActivity {

    ImageView logo;
    LottieAnimationView lottieAnimationView;
    TextView textv;
    float v = 0;
    private static int SPLASH_TIME_OUT=2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences=getSharedPreferences(LoginPage.PREFS_NAME,0);
                boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn",false);

                if (hasLoggedIn) {
                    Intent intent = new Intent(Splash.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                        Intent intent = new Intent(Splash.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                }

            }
        },SPLASH_TIME_OUT);

        logo = findViewById(R.id.logo);
        lottieAnimationView = findViewById(R.id.lottie);
        textv=findViewById(R.id.tv);

        logo.animate().translationX(-1400).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationX(-1400).setDuration(1000).setStartDelay(4000);
        textv.animate().translationX(-1400).setDuration(1000).setStartDelay(4000);

        int unicode=0x1F497;
        String emoji = getEmoji(unicode);

        String text="Made  with  "+emoji+"  by  Paresh";
        textv.setText(text);

//        Thread td = new Thread() {
//
//            public void run() {
//                try {
//                    sleep(2500);
//
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                } finally {
//                    Intent intent = new Intent(Splash.this, LoginActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//
//            ;
//
//        };td.start();
    }

    public String getEmoji(int uni) {
        return new String(Character.toChars(uni));
    }
}