package com.cricker.app.Activity;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.cricker.app.R;


public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;
    boolean firstStart;

    RelativeLayout relativeLayout;
    private ValueAnimator valueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //        Splash Screen

        setupBackgroundAnimation();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        firstStart = prefs.getBoolean("firstStart", true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent;
                if (firstStart) {
                    intent = new Intent(SplashActivity.this, UserAgreementActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, HomeActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }

    private void setupBackgroundAnimation() {

        relativeLayout = findViewById(R.id.relativeLayout);
        valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), Color.parseColor("#0030c1"), Color.parseColor("#ed0082"), Color.parseColor("#d66f0e"), Color.parseColor("#ff0558"), Color.parseColor("#1ca055"));
        valueAnimator.setDuration((5 - 1) * 100000000);

        valueAnimator.setCurrentPlayTime((long) (100000000));

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                relativeLayout.setBackgroundColor((Integer) animation.getAnimatedValue());
            }
        });

    }
}
